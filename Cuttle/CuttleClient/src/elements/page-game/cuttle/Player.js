function Player(id, hand, game) {
    this._game = game;
    this._calls = null;
    this.id = id;
    this.points = 0;
    this.victoryReq = 21;
    this.pointArea = new CardArea(game, 0, 300);
    this.continuousArea = new CardArea(game, 0, 400);
    this.handArea = new CardArea(game, 0, 530);

    hand.forEach(function(d) {
        this.handArea.add(d);
    }, this);

    this.pointText = this._game.svg.append('text')
        .attr('x', this._game.width - 45)
        .attr('y', this._game.height / 1.3)
        .attr('font-size', '20')
        .attr('text-anchor', 'middle')
        .text('0/20');
};

Player.prototype.setCalls = function(calls) {
    this._calls = calls;
};

Player.prototype.updatePointText = function() {
    this.pointText.text(this.points + '/' + this.victoryReq);
};
