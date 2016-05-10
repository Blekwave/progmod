Polymer({
    is: "page-router",
    properties: {
        route: {
            type: String,
            notify: true
        }
    },

    created: function() {
        this._routes = {};
    },

    ready: function() {
        this._pages = Polymer.dom(this.root).querySelector("iron-pages");
    },

    attached: function() {
        this._pages.items.forEach(function(item, index) {
            page(item.path, function(ctx) {
                this.route = index.toString();
                item.createElement();

                if(this._params)
                    item.elementNode.params = this._params;
                else
                    item.elementNode.params = ctx.params;

                item.render();
            }.bind(this));
        }.bind(this));

        page();
    },

    go: function(route, params) {
        this._params = params;
        page(route);
    }
});
