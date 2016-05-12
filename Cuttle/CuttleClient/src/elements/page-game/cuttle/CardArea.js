/**
 * Card area in the game table.
 */
function CardArea(game, x, y, reversed) {
    this._game = game;
    this._x = x;
    this._y = y;
    this._reversed = reversed;
    this._width = this._game.width - this._game.margin.left - this._game.margin.right;
    this._height = this._game.height - this._game.margin.top - this._game.margin.bottom;
    this._area = this._game.table.append('g')
        .attr('transform', 'translate(' + x + ',' + y + ')');
    this._cards = [];
};

CardArea.prototype.numCards = function() {
    return this._cards.length;
}

CardArea.prototype.add = function(id) {
    this._cards.push(id);

    this._update();
};

CardArea.prototype.remove = function(id) {
    var index = this._cards.indexOf(id);
    if(index > -1)
        this._cards.splice(index, 1);

    this._update();
};

CardArea.prototype.replaceCards = function(cards) {
    this._cards = cards.slice();

    this._update();
};

CardArea.prototype._update = function() {
    this._area.selectAll('use')
        .data(this._cards).enter()
        .append('use')
        .on('click', this._processClick.bind(this));

    this._area.selectAll('use')
        .data(this._cards).exit()
        .remove();

    var offset = this._game.cardOffset;
    while(offset * this._cards.length > this._width) // Reduce until fits.
        offset -= .1;
    if(this._reversed)
        offset *= -1;

    this._area.selectAll('use')
        .data(this._cards)
        .attr('xlink:href', function(d) {
            return this._game.getCardName(d);
        }.bind(this))
        .attr('transform', function(d, i) {
            return 'translate(' + offset * i + ',0),scale(' + this._game.cardScale + ')';
        }.bind(this));
};

CardArea.prototype._processClick = function(d) {
    var divData = [];
    if(this._game.player.calls) {
        this._game.player.calls.forEach(function(b) {
            if(b.hasOwnProperty('card') && b.card.id == d)
                divData.push(b);
        });
    }

    if(divData.length > 0) {
        var div = new PromptDiv(this._game, divData, true);
    }
};
