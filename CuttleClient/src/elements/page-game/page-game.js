Polymer({
    is: "page-game",

    properties: {
        params: {
            type: Object,
            notify: true,
            reflectToAttribute: true,
            observer: '_paramsChanged'
        }
    },

    _paramsChanged: function() {
        this._startGame(this.params.address);
    },

    _startGame: function(address) {
        this._client = new CuttleClient(this.$.div, address);
    }
});
