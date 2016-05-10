function Player(id, hand, game) {
    this.id = id;
    this.game = game;
    this.pointArea = new CardArea(game, 0, 300);
    this.continuousArea = new CardArea(game, 0, 400);
    this.handArea = new CardArea(game, 0, 530);
    this.calls = null;

    hand.forEach(function(d) {
        this.handArea.add(d);
    }, this);
};

Player.prototype.setCalls = function(calls) {
    this.calls = calls;
};
