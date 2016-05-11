Polymer({
    is: "page-connection",
    ready: function() {
        this.listen(this.$.submitButton, "click", "submit");
        this.$.submitInput.value = document.location.hostname + ":42001";
    },
    submit: function() {
        if(this.$.connectionForm.validate()) {
            var addr = this.$.submitInput.value;
            if(addr.indexOf('ws://') == -1)
                addr = 'ws://' + addr;

            document.querySelector('page-router').go("/game", {
                address: addr
            });
        }
   }
});
