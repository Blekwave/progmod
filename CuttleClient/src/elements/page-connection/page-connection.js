Polymer({
    is: "page-connection",
    ready: function() {
        this.listen(this.$.submitButton, "click", "submit");
    },
    submit: function() {
        if(this.$.connectionForm.validate())
            document.querySelector('page-router').go("/game", {
                address: this.$.submitInput.value
            });
    }
});
