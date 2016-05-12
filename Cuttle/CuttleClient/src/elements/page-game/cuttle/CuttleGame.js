function CuttleGame(client, root) {
    this._client = client;
    this.root = d3.select(root);
    this._audio = this.root.select('audio').node();
    this._logDiv = new LogDiv(this.root);
    this.svg = this.root.select('svg');
    this.width = 800;
    this.height = 600;
    this.cardScale = 0.4;
    this.cardOffset = 55;
    this.margin = { top: 20, left: 20, bottom: 20, right: 20 };
    this.player = null;
    this.enemy = null;
    this._cardData = null;
    this.table = null;
    this.deck = null;
    this.scrap = null;
    this.centralText = null;

    this._createTable();
};

CuttleGame.prototype._createTable = function() {
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

CuttleGame.prototype._createCardData = function(card_by_id) {
    this._cardData = {};
    for(var id in card_by_id) {
        if(card_by_id.hasOwnProperty(id)) {
            var card = card_by_id[id];
            this._cardData[id] = '#' + card.rank +  '_' + card.suit;
        }
    }

    // Add the back card.
    this._cardData[-1] = "#back";
};

CuttleGame.prototype.getCardName = function(id) {
    return this._cardData[id];
};

CuttleGame.prototype.onMessage = function(msg) {
    console.log(msg);

    switch(msg.type) {
        case 'game_start': this._gameStart(msg); break;
        case 'game_end': this._gameEnd(msg); break;
        case 'new_turn': this._newTurn(msg); break;
        case 'prompt': this._prompt(msg); break;
        case 'prompt_update': this._promptUpdate(msg); break;
        case 'action_update': this._actionUpdate(msg); break;
        case 'behavior_update': this._behaviorUpdate(msg); break;
        default: this._invalidType(msg);
    }
};

CuttleGame.prototype._gameStart = function(msg) {
    this._createCardData(msg.card_by_id);
    this.enemy = new Enemy(msg.opponent_id, parseInt(msg.opponent_hand_size, 10), this);
    this.player = new Player(msg.player_id, msg.hand, this);

    this._audio.play();
};

CuttleGame.prototype._gameEnd = function(msg) {
    this._updatePoints(msg);

    if(msg.result == 'win')
        this.centralText.text('You won!!!');
    else if(msg.result == 'tie')
        this.centralText.text('It\'s a tie!');
    else
        this.centralText.text("You lost...");
};

CuttleGame.prototype._newTurn = function(msg) {
    this.player.calls = null;
    this._updatePoints(msg);
};

CuttleGame.prototype._updatePoints = function(msg) {
    this.player.points = msg.player_points;
    this.player.updatePointText();
    this.enemy.points = msg.opponent_points;
    this.enemy.updatePointText();
};

CuttleGame.prototype._prompt = function(msg) {
    this.player.calls = msg.calls;

    switch(msg.prompt_type) {
        case 'play_prompt': this.promptPlay(msg); break;
        case 'reaction_prompt': this.promptReaction(msg); break;
        case 'discard_prompt': this.promptDiscard(msg); break;
        default: this._invalidType(msg);
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

CuttleGame.prototype._promptUpdate = function(msg) {
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

CuttleGame.prototype._actionUpdate = function(msg) {
    this._logDiv.log(JSON.stringify(msg));

    switch(msg.action_type) {
        case 'draw': this._actionUpdateDraw(msg); break;
        case 'point_play': this._actionUpdatePointPlay(msg); break;
        case 'continuous_play': this._actionUpdateContinuousPlay(msg); break;
        case 'discard': this._actionUpdateDiscard(msg); break;
        case 'destroy': this._actionUpdateDestroy(msg); break;
        case 'switch': this._actionUpdateSwitch(msg); break;
        case 'recover': this._actionUpdateRecover(msg); break;
        case 'return': this._actionUpdateReturn(msg); break;
        case 'show_hand': this._actionUpdateShowHand(msg); break;
        case 'hide_hand': this._actionUpdateHideHand(msg); break;
        case 'shuffle_hand': this._actionUpdateShuffleHand(msg); break;
        case 'raiseprotection': this._actionUpdateRaiseProtection(msg); break;
        case 'lowerprotection': this._actionUpdateLowerProtection(msg); break;
        case 'lower_victory_req': this._actionUpdateVictoryReq(msg); break;
        case 'raise_victory_req': this._actionUpdateVictoryReq(msg); break;
        default: this._invalidType(msg);
    }
};

CuttleGame.prototype._actionUpdateDraw = function(msg) {
    if(msg.player == this.player.id)
        this.player.handArea.add(msg.drawn);
    else if(msg.hasOwnProperty('drawn')) // 8 continuous effect.
        this.enemy.handArea.add(msg.drawn);
    else
        this.enemy.handArea.add(-1);
};

CuttleGame.prototype._actionUpdatePointPlay = function(msg) {
    if(msg.player == this.player.id) {
        this.player.handArea.remove(msg.target.id);
        this.player.pointArea.add(msg.target.id);
    }
    else {
        this.enemy.handArea.remove(-1);
        this.enemy.pointArea.add(msg.target.id);
    }
};

CuttleGame.prototype._actionUpdateContinuousPlay = function(msg) {
    if(msg.player == this.player.id) {
        this.player.handArea.remove(msg.target.id);
        this.player.continuousArea.add(msg.target.id);
    }
    else {
        this.enemy.handArea.remove(-1);
        this.enemy.continuousArea.add(msg.target.id);
    }
};

CuttleGame.prototype._actionUpdateDiscard = function(msg) {
    if(msg.player == this.player.id)
        this.player.handArea.remove(msg.target.id);
    else
        this.enemy.handArea.remove(-1);

    this.scrap.add(msg.target.id);
};

CuttleGame.prototype._actionUpdateDestroy = function(msg) {
    var owner = msg.target.owner == this.player.id ? this.player : this.enemy;

    if(msg.target.pile == 'point_board')
        owner.pointArea.remove(msg.target.id);
    else
        owner.continuousArea.remove(msg.target.id);

    this.scrap.add(msg.target.id);
};

CuttleGame.prototype._actionUpdateSwitch = function(msg) {
    if(msg.target.owner == this.player.id) {
        this.player.pointArea.remove(msg.target.id);
        this.enemy.pointArea.add(msg.target.id);
    }
    else {
        this.enemy.pointArea.remove(msg.target.id);
        this.player.pointArea.add(msg.target.id);
    }
};

CuttleGame.prototype._actionUpdateRecover = function(msg) {
    this.scrap.remove(msg.target.id);
    if(msg.player == this.player.id)
        this.player.handArea.add(msg.target.id);
    else
        this.enemy.handArea.add(-1);
};

CuttleGame.prototype._actionUpdateReturn = function(msg) {
    var owner = msg.target.owner == this.player.id ? this.player : this.enemy;
    owner.continuousArea.remove(msg.target.id);
};

CuttleGame.prototype._actionUpdateShowHand = function(msg) {
    if(msg.player == this.enemy.id)
        this.enemy.handArea.replaceCards(msg.player_hand);
};

CuttleGame.prototype._actionUpdateHideHand = function(msg) {
    if(msg.player == this.enemy.id) {
        var hiddenCards = [];
        for(var i = 0; i < this.enemy.handArea.numCards(); ++i)
            hiddenCards.push(-1);

        this.enemy.handArea.replaceCards(hiddenCards);
    }
};

CuttleGame.prototype._actionUpdateShuffleHand = function(msg) {
    if(msg.player == this.player.id)
        this.player.handArea.replaceCards(msg.player_hand);
};

CuttleGame.prototype._actionUpdateRaiseProtection = function(msg) {
    // TODO implement highlight.
};

CuttleGame.prototype._actionUpdateLowerProtection = function(msg) {
    // TODO remove highlight.
};

CuttleGame.prototype._actionUpdateVictoryReq = function(msg) {
    var owner = msg.player == this.player.id ? this.player : this.enemy;

    owner.victoryReq = msg.new_victory_req;
    owner.updatePointText();
};

CuttleGame.prototype._behaviorUpdate = function(msg) {
    this._logDiv.log(JSON.stringify(msg));
};

CuttleGame.prototype.promptResponse = function(b) {
    var response = {'type': 'prompt_response', 'id': b.id};
    this.player.calls = null;
    this._client.send(JSON.stringify(response));
};

CuttleGame.prototype._invalidType = function(msg) {
    console.log('Invalid msg received: ' + msg);
};
