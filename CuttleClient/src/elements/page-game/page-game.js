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
        console.log(this.params);
    }
});
