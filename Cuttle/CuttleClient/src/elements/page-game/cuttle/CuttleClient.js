function CuttleClient(root, address) {
    this._socket = null;
    this._game = null;

    var audio = document.createElement('audio');
    audio.src = 'res/cuttlematch.mp3'
    audio.controls = 'controls';
    audio.loop = 'loop';
    audio.muted = 'muted';
    audio.load();

    d3.text("res/cards.svg", function(onError, data) {
        root.appendChild(audio);
        d3.select(root).append('br');
        d3.select(root).append('div')
            .attr('class', 'svgDiv')
            .html(data);

        this._game = new CuttleGame(this, root);
        this.start(address);
    }.bind(this));
};

CuttleClient.prototype.start = function(address) {
    if(!address)
        address = 'ws://' + document.location.hostname + ':42001';

    this._socket = new WebSocket(address);
    this._socket.onopen = this._onOpen.bind(this);
    this._socket.onmessage = this._onMessage.bind(this);
    this._socket.onclose = this._onClose.bind(this);
    this._socket.onerror = this._onError.bind(this);
}

CuttleClient.prototype._onOpen = function(event) {
    console.log('Connected to ' + event.currentTarget.url);
    this._game.centralText.text('Waiting for opponent')
}

CuttleClient.prototype._onMessage = function(event) {
    this._game.onMessage(JSON.parse(event.data));
}

CuttleClient.prototype._onClose = function(event) {
    console.log('Connection closed: ' + event.currentTarget.url);
    this._game.centralText.text('Connection closed');
}

CuttleClient.prototype._onError = function(error) {
    console.log(error);
    alert("Connection error");
}

CuttleClient.prototype.send = function(msg) {
    console.log(msg);
    this._socket.send(msg);
}
