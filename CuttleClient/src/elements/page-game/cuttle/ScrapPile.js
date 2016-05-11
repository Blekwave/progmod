/**
 * Card scrap pile.
 */
function ScrapPile(game, x, y) {
    this.game = game;
    this.x = x;
    this.y = y;
    this.width = this.game.width - this.game.margin.left - this.game.margin.right;
    this.area = this.game.table.append('g');
    this.cards = [];
    this.showPile = false;
};

ScrapPile.prototype.add = function(id) {
    this.cards.push(id);

    this.update();
};

ScrapPile.prototype.remove = function(id) {
    var index = this.cards.indexOf(id);
    if(index > -1)
        this.cards.splice(index, 1);

    this.update();
};

ScrapPile.prototype.update = function() {
    this.area.selectAll('use')
        .data(this.cards).enter()
        .append('use')
        .on('click', this.processClick.bind(this));

    this.area.selectAll('use')
        .data(this.cards).exit()
        .remove();

    var offset = -10;
    while(offset * this.cards.length > this.width) // Reduce until fits.
        offset += .1;

    this.area.selectAll('use')
        .data(this.cards)
        .attr('xlink:href', function(d) {
            return this.game.getCardName(d);
        }.bind(this))
        .attr('transform', function(d, i) {
            var t = '';

            if(this.showPile)
                t += 'translate(' + (this.x + offset * (this.cards.length - i - 1))
                        + ',' + this.y + '),';
            else
                t += 'translate(' + this.x + ',' + this.y + '),';

            t += 'scale(' + this.game.cardScale + ')';
            return t;
        }.bind(this));
};

ScrapPile.prototype.processClick = function(d) {
    this.showPile = this.showPile ? false : true;
    this.update();
};
