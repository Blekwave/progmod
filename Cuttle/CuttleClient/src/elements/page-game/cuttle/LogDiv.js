function LogDiv(root) {
    this._root = root;
    this._div = this._root.append('div')
        .attr('class', 'scrollDiv');
};

LogDiv.prototype.log = function(msg) {
    this._div.append('p').text(msg);
};
