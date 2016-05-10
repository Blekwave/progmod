/**
 * Card scrap pile.
 */
function ScrapPile(game, x, y) {
    this.game = game;
    this.x = x;
    this.y = y;
    this.area = this.game.table.append('g');
    this.cards = [];
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
        .attr('xlink:href', function(d) {
            return this.game.getCardName(d);
        }.bind(this))
        .attr('transform', 'translate(' + this.x + ',' + this.y
                + '),scale(' + this.game.cardScale + ')')
        .on('click', this.processClick.bind(this));

    this.area.selectAll('use')
        .data(this.cards).exit()
        .remove();
};

ScrapPile.prototype.processClick = function(d) {

};
