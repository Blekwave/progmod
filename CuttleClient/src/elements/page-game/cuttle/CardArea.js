/**
 * Card area in the game table.
 */
function CardArea(game, x, y, reversed) {
    this.game = game;
    this.x = x;
    this.y = y;
    this.reversed = reversed;
    this.width = this.game.width - this.game.margin.left - this.game.margin.right;
    this.height = this.game.height - this.game.margin.top - this.game.margin.bottom;
    this.area = this.game.table.append('g')
        .attr('transform', 'translate(' + x + ',' + y + ')');
    this.cards = [];
};

CardArea.prototype.numCards = function() {
    return this.cards.length;
}

CardArea.prototype.add = function(id) {
    this.cards.push(id);

    this.update();
};

CardArea.prototype.remove = function(id) {
    var index = this.cards.indexOf(id);
    if(index > -1)
        this.cards.splice(index, 1);

    this.update();
};

CardArea.prototype.replaceCards = function(cards) {
    this.cards = cards.slice();

    this.update();
};

CardArea.prototype.update = function() {
    this.area.selectAll('use')
        .data(this.cards).enter()
        .append('use')
        .on('click', this.processClick.bind(this));

    this.area.selectAll('use')
        .data(this.cards).exit()
        .remove();

    var offset = this.game.cardOffset;
    while(offset * this.cards.length > this.width) // Reduce until fits.
        offset -= .1;
    if(this.reversed)
        offset *= -1;

    this.area.selectAll('use')
        .data(this.cards)
        .attr('xlink:href', function(d) {
            return this.game.getCardName(d);
        }.bind(this))
        .attr('transform', function(d, i) {
            return 'translate(' + offset * i + ',0),scale(' + this.game.cardScale + ')';
        }.bind(this));
};

CardArea.prototype.processClick = function(d) {
    var divData = [];
    if(this.game.player.calls) {
        this.game.player.calls.forEach(function(b) {
            if(b.hasOwnProperty('card') && b.card.id == d)
                divData.push(b);
        });
    }

    if(divData.length > 0) {
        var div = new PromptDiv(this.game, divData, true);
    }
};
