function Player(id, hand, game) {
    this.id = id;
    this.game = game;
    this.points = 0;
    this.victoryReq = 20;
    this.pointArea = new CardArea(game, 0, 300);
    this.continuousArea = new CardArea(game, 0, 400);
    this.handArea = new CardArea(game, 0, 530);
    this.calls = null;

    hand.forEach(function(d) {
        this.handArea.add(d);
    }, this);

    this.pointText = this.game.svg.append('text')
        .attr('x', this.game.width - 45)
        .attr('y', this.game.height / 1.3)
        .attr('font-size', '20')
        .attr('text-anchor', 'middle')
        .text('0/20');
};

Player.prototype.setCalls = function(calls) {
    this.calls = calls;
};

Player.prototype.updatePointText = function() {
    this.pointText.text(this.points + '/' + this.victoryReq);
};
