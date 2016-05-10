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
        element: String,

        /**
         * The proper element that was created.
         */
        elementNode: {
            type: Object,
            notify: true,
            value: null
        }
    },

    createElement: function() {
        if(!this.elementNode) {
            this.elementNode = document.createElement(this.element);
            Polymer.dom(this.root).appendChild(this.elementNode);
        }
    },

    render: function() {
        this.elementNode.focus();
    }
});
