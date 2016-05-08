Polymer({
    is: "route-section",

    properties: {
        /**
         * The path of this route, same path that could be set using page.js.
         */
        path: String,

        /**
         * The element that is going to be called when this path is selected.
         */
        element: String
    },

    created: function() {
        this._elementNode = null;
    },

    render: function(context) {
        if(!this._elementNode) {
            this._elementNode = document.createElement(this.element);
            Polymer.dom(this.root).appendChild(this._elementNode);
        }

        this._elementNode.focus();
    }
});
