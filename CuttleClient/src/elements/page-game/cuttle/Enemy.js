function Enemy(id, handSize, game) {
    this.id = id;
    this.points = 0;
    this.victoryReq = 21;
    this._game = game;

    this.handArea = new CardArea(game, 695, -70, true);
    for(var i = 0; i < handSize; ++i)
        this.handArea.add(-1);

    this.continuousArea = new CardArea(game, 0, 50);
    this.pointArea = new CardArea(game, 0, 150);

    this._pointText = this._game.svg.append('text')
        .attr('x', this._game.width - 45)
        .attr('y', this._game.height / 4)
        .attr('font-size', '20')
        .attr('text-anchor', 'middle')
        .text('0/20');
};

Enemy.prototype.updatePointText = function() {
    this._pointText.text(this.points + '/' + this.victoryReq);
};
