function LogDiv(root) {
    this.root = root;
    this.div = this.root.append('div')
        .attr('class', 'scrollDiv');
};

LogDiv.prototype.log = function(msg) {
    this.div.append('p').text(msg);
};
