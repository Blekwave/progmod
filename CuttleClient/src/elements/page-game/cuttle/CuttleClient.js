function CuttleClient(root, address) {
    this.root = d3.select(root);
    this.audio = document.createElement('audio');
    this._socket = null;

    this.audio.src = 'res/cuttlematch.mp3'
    this.audio.controls = 'controls';
    this.audio.loop = 'loop';
    this.audio.muted = 'muted';
    this.audio.load();

    d3.text("res/cards.svg", function(error, data) {
        this.root.node().appendChild(this.audio);
        this.root.append('br');
        this.root.append('div')
            .attr('class', 'svgDiv')
            .html(data);

        this.game = new CuttleGame(this, this.root);
        this.start(address);
    }.bind(this));
};

CuttleClient.prototype.start = function(address) {
    if(!address)
        address = "ws://localhost:42001";

    this._socket = new WebSocket(address);
    this._socket.onopen = this.open.bind(this);
    this._socket.onmessage = this.message.bind(this);
    this._socket.onclose = this.close.bind(this);
    this._socket.onerror = this.error.bind(this);
}

CuttleClient.prototype.open = function(event) {
    console.log('Connected to ' + event.currentTarget.url);
}

CuttleClient.prototype.message = function(event) {
    this.game.message(JSON.parse(event.data));
}

CuttleClient.prototype.close = function(event) {
    console.log('Connection closed: ' + event.currentTarget.url);
    this.game.centralText.text('Connection closed');
}

CuttleClient.prototype.error = function(error) {
    console.log(error);
    alert("Connection error");
}

CuttleClient.prototype.send = function(msg) {
    console.log(msg);
    this._socket.send(msg);
}
