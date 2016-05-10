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
        var self = this;

        this._pages.items.forEach(function(item, index) {
            page(item.path, function(ctx) {
                self.route = index.toString();
                item.createElement();

                if(self._params)
                    item.elementNode.params = self._params;
                else
                    item.elementNode.params = ctx.params;
                item.render();
            });
        });

        page();
    },

    go: function(route, params) {
        this._params = params;
        page(route);
        this.route = route;
    }
});
