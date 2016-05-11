/**
 * Card scrap pile.
 */
function ScrapPile(game, x, y) {
    this._game = game;
    this._x = x;
    this._y = y;
    this._width = this._game.width - this._game.margin.left - this._game.margin.right;
    this._area = this._game.table.append('g');
    this._cards = [];
    this._showPile = false;
};

ScrapPile.prototype.add = function(id) {
    this._cards.push(id);

    this._update();
};

ScrapPile.prototype.remove = function(id) {
    var index = this._cards.indexOf(id);
    if(index > -1)
        this._cards.splice(index, 1);

    this._update();
};

ScrapPile.prototype._update = function() {
    this._area.selectAll('use')
        .data(this._cards).enter()
        .append('use')
        .on('click', this._processClick.bind(this));

    this._area.selectAll('use')
        .data(this._cards).exit()
        .remove();

    var offset = -10;
    while(offset * this._cards.length > this._width) // Reduce until fits.
        offset += .1;

    this._area.selectAll('use')
        .data(this._cards)
        .attr('xlink:href', function(d) {
            return this._game.getCardName(d);
        }.bind(this))
        .attr('transform', function(d, i) {
            var t = '';

            if(this._showPile)
                t += 'translate(' + (this._x + offset * (this._cards.length - i - 1))
                        + ',' + this._y + '),';
            else
                t += 'translate(' + this._x + ',' + this._y + '),';

            t += 'scale(' + this._game.cardScale + ')';
            return t;
        }.bind(this));
};

ScrapPile.prototype._processClick = function(d) {
    this._showPile = this._showPile ? false : true;
    this._update();
};
