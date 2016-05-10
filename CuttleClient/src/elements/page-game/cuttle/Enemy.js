function Enemy(id, handSize, game) {
    this.id = id;
    this.game = game;
    this.handSize = handSize;

    this.handArea = new CardArea(game, 0, -70);
    for(var i = 0; i < this.handSize; ++i)
        this.handArea.add(-1);

    this.continuousArea = new CardArea(game, 0, 50);
    this.pointArea = new CardArea(game, 0, 150);
};
