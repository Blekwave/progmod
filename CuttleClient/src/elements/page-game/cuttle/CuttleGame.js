function CuttleGame(client, root) {
    this.client = client;
    this.root = root;
    this.audio = this.root.select('audio').node();
    this.svg = this.root.select('svg');
    this.logDiv = new LogDiv(this.root);
    this.width = 800;
    this.height = 600;
    this.cardScale = 0.4;
    this.cardOffset = 55;
    this.margin = { top: 20, left: 20, bottom: 20, right: 20 };
    this.player = null;
    this.enemy = null;
    this.cardById = null;
    this.table = null;
    this.deck = null;
    this.scrap = null;
    this.centralText = null;

    this.createTable();
};

CuttleGame.prototype.createTable = function() {
    this.svg
        .style('border', '5px solid #755648');

    this.svg.append('rect')
        .attr('width', '100%')
        .attr('height', '100%')
        .attr('fill', '#87A878');

    this.table = this.svg.append('g')
        .attr('transform',
                'translate(' + this.margin.left + ',' + this.margin.top + ')');

    // Deck.
    this.deck = this.table.append('use')
        .attr('xlink:href', '#back')
        .attr('transform', 'translate(' + (this.width - 100) + ',' +
                (this.height / 4) + '),scale(' + this.cardScale  + ')')
        .on('click', function() {
            if(this.player.calls) {
                this.player.calls.forEach(function(d) {
                    if(d.behavior_type == 'draw')
                        this.promptResponse(d);
                }.bind(this));
            }
        }.bind(this));

    // Scrap pile.
    this.scrap = new ScrapPile(this, this.width - 100, this.height / 2);

    // Message.
    this.centralText = this.svg.append('text')
        .attr('x', this.width / 2)
        .attr('y', this.height / 2)
        .attr('font-size', '25')
        .attr('text-anchor', 'middle')
        .text('Connecting to server');
};

CuttleGame.prototype.createCardData = function(card_by_id) {
    this.cardData = {};
    for(var id in card_by_id) {
        if(card_by_id.hasOwnProperty(id)) {
            var card = card_by_id[id];
            this.cardData[id] = '#' + card.rank +  '_' + card.suit;
        }
    }

    // Add the back card.
    this.cardData[-1] = "#back";
};

CuttleGame.prototype.getCardName = function(id) {
    return this.cardData[id];
};

CuttleGame.prototype.message = function(msg) {
    console.log(msg);

    switch(msg.type) {
        case 'game_start': this.gameStart(msg); break;
        case 'game_end': this.gameEnd(msg); break;
        case 'new_turn': this.newTurn(msg); break;
        case 'prompt': this.prompt(msg); break;
        case 'prompt_update': this.promptUpdate(msg); break;
        case 'action_update': this.actionUpdate(msg); break;
        case 'behavior_update': this.behaviorUpdate(msg); break;
        default: this.invalidType(msg);
    }
};

CuttleGame.prototype.gameStart = function(msg) {
    this.createCardData(msg.card_by_id);
    this.enemy = new Enemy(msg.opponent_id, parseInt(msg.opponent_hand_size, 10), this);
    this.player = new Player(msg.player_id, msg.hand, this);

    this.audio.play();
};

CuttleGame.prototype.gameEnd = function(msg) {
    this.updatePoints(msg);

    if(msg.result == 'win')
        this.centralText.text('You won!!!');
    else if(msg.result == 'tie')
        this.centralText.text('It\'s a tie!');
    else
        this.centralText.text("You lost...");
};

CuttleGame.prototype.newTurn = function(msg) {
    this.player.calls = null;
    this.updatePoints(msg);
};

CuttleGame.prototype.updatePoints = function(msg) {
    this.player.points = msg.player_points;
    this.player.updatePointText();
    this.enemy.points = msg.opponent_points;
    this.enemy.updatePointText();
};

CuttleGame.prototype.prompt = function(msg) {
    this.player.calls = msg.calls;

    switch(msg.prompt_type) {
        case 'play_prompt': this.promptPlay(msg); break;
        case 'reaction_prompt': this.promptReaction(msg); break;
        case 'discard_prompt': this.promptDiscard(msg); break;
        default: this.invalidType(msg);
    }
};

CuttleGame.prototype.promptPlay = function(msg) {
    // Just wait for the player
};


CuttleGame.prototype.promptReaction = function(msg) {
    this.promptMessage(msg);
};

CuttleGame.prototype.promptDiscard = function(msg) {
    this.promptMessage(msg);
};

CuttleGame.prototype.promptMessage = function(msg) {
    var div = new PromptDiv(this, msg.calls, false, 355, 285);
};

CuttleGame.prototype.promptUpdate = function(msg) {
    if(msg.prompt_type == 'play_prompt') {
        if(msg.player == this.player.id)
            this.centralText.text('Your turn');
        else
            this.centralText.text('Opponent\'s turn');
    }
    else if(msg.prompt_type == 'reaction_prompt' || msg.prompt_type == 'discard_prompt') {
        if(msg.player == this.player.id)
            this.centralText.text('Your turn');
        else
            this.centralText.text('Waiting for opponent...');
    }
}

CuttleGame.prototype.actionUpdate = function(msg) {
    this.logDiv.log(JSON.stringify(msg));

    switch(msg.action_type) {
        case 'draw': this.actionUpdateDraw(msg); break;
        case 'point_play': this.actionUpdatePointPlay(msg); break;
        case 'continuous_play': this.actionUpdateContinuousPlay(msg); break;
        case 'discard': this.actionUpdateDiscard(msg); break;
        case 'destroy': this.actionUpdateDestroy(msg); break;
        case 'switch': this.actionUpdateSwitch(msg); break;
        case 'recover': this.actionUpdateRecover(msg); break;
        case 'return': this.actionUpdateReturn(msg); break;
        case 'show_hand': this.actionUpdateShowHand(msg); break;
        case 'hide_hand': this.actionUpdateHideHand(msg); break;
        case 'shuffle_hand': this.actionUpdateShuffleHand(msg); break;
        case 'raiseprotection': this.actionUpdateRaiseProtection(msg); break;
        case 'lowerprotection': this.actionUpdateLowerProtection(msg); break;
        case 'lower_victory_req': this.actionUpdateVictoryReq(msg); break;
        case 'raise_victory_req': this.actionUpdateVictoryReq(msg); break;
        default: this.invalidType(msg);
    }
};

CuttleGame.prototype.actionUpdateDraw = function(msg) {
    if(msg.player == this.player.id)
        this.player.handArea.add(msg.drawn);
    else if(msg.hasOwnProperty('drawn')) // 8 continuous effect.
        this.enemy.handArea.add(msg.drawn);
    else
        this.enemy.handArea.add(-1);
};

CuttleGame.prototype.actionUpdatePointPlay = function(msg) {
    if(msg.player == this.player.id) {
        this.player.handArea.remove(msg.target.id);
        this.player.pointArea.add(msg.target.id);
    }
    else {
        this.enemy.handArea.remove(-1);
        this.enemy.pointArea.add(msg.target.id);
    }
};

CuttleGame.prototype.actionUpdateContinuousPlay = function(msg) {
    if(msg.player == this.player.id) {
        this.player.handArea.remove(msg.target.id);
        this.player.continuousArea.add(msg.target.id);
    }
    else {
        this.enemy.handArea.remove(-1);
        this.enemy.continuousArea.add(msg.target.id);
    }
};

CuttleGame.prototype.actionUpdateDiscard = function(msg) {
    if(msg.player == this.player.id)
        this.player.handArea.remove(msg.target.id);
    else
        this.enemy.handArea.remove(-1);

    this.scrap.add(msg.target.id);
};

CuttleGame.prototype.actionUpdateDestroy = function(msg) {
    var owner = msg.target.owner == this.player.id ? this.player : this.enemy;

    if(msg.target.pile == 'point_board')
        owner.pointArea.remove(msg.target.id);
    else
        owner.continuousArea.remove(msg.target.id);

    this.scrap.add(msg.target.id);
};

CuttleGame.prototype.actionUpdateSwitch = function(msg) {
    if(msg.target.owner == this.player.id) {
        this.player.pointArea.remove(msg.target.id);
        this.enemy.pointArea.add(msg.target.id);
    }
    else {
        this.enemy.pointArea.remove(msg.target.id);
        this.player.pointArea.add(msg.target.id);
    }
};

CuttleGame.prototype.actionUpdateRecover = function(msg) {
    this.scrap.remove(msg.target.id);
    if(msg.player == this.player.id)
        this.player.handArea.add(msg.target.id);
    else
        this.enemy.handArea.add(-1);
};

CuttleGame.prototype.actionUpdateReturn = function(msg) {
    var owner = msg.target.owner == this.player.id ? this.player : this.enemy;
    owner.continuousArea.remove(msg.target.id);
};

CuttleGame.prototype.actionUpdateShowHand = function(msg) {
    if(msg.player == this.enemy.id)
        this.enemy.handArea.replaceCards(msg.player_hand);
};

CuttleGame.prototype.actionUpdateHideHand = function(msg) {
    if(msg.player == this.enemy.id) {
        var hiddenCards = [];
        for(var i = 0; i < this.enemy.handArea.numCards(); ++i)
            hiddenCards.push(-1);

        this.enemy.handArea.replaceCards(hiddenCards);
    }
};

CuttleGame.prototype.actionUpdateShuffleHand = function(msg) {
    if(msg.player == this.player.id)
        this.player.handArea.replaceCards(msg.player_hand);
};

CuttleGame.prototype.actionUpdateRaiseProtection = function(msg) {
    // TODO implement highlight.
};

CuttleGame.prototype.actionUpdateLowerProtection = function(msg) {
    // TODO remove highlight.
};

CuttleGame.prototype.actionUpdateVictoryReq = function(msg) {
    var owner = msg.player == this.player.id ? this.player : this.enemy;

    owner.victoryReq = msg.new_victory_req;
    owner.updatePointText();
};

CuttleGame.prototype.behaviorUpdate = function(msg) {
    this.logDiv.log(JSON.stringify(msg));
};

CuttleGame.prototype.promptResponse = function(b) {
    var response = {'type': 'prompt_response', 'id': b.id};
    this.player.calls = null;
    this.client.send(JSON.stringify(response));
};

CuttleGame.prototype.invalidType = function(msg) {
    console.log('Invalid msg received: ' + msg);
};
