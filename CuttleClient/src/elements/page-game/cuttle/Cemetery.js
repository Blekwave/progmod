/**
 * Card cemetery.
 */
function Cemetery(game, x, y) {
    this.game = game;
    this.x = x;
    this.y = y;
    this.area = this.game.table.append('g');
    this.cards = [];
};

Cemetery.prototype.add = function(id) {
    console.log(id);
    this.cards.push(id);

    this.update();
};

Cemetery.prototype.remove = function(id) {
    var index = this.cards.indexOf(id);
    if(index > -1)
        this.cards.splice(index, 1);

    this.update();
};

Cemetery.prototype.update = function() {
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

Cemetery.prototype.processClick = function(d) {

};
