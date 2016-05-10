/**
 * Div that displays options to be choosen and calls promptResponse with the data.
 * If have x and y, will use it instead of the mouse position.
 */
PromptDiv = function(game, divData, disappear, x, y) {
    var div = d3.select(document.createElement('div'));
    div.selectAll('p')
        .data(divData).enter()
        .append('p')
        .text(function(d) {
            var text = d.behavior_type;
            if(d.hasOwnProperty('target'))
                text += ' ' + game.getCardName(d.target.id);

            return text;
        })
        .on('click', function(d) {
            div.remove();
            game.promptResponse(d);
        });

    var coords;
    if(x && y)
        coords = [x, y];
    else
        coords = d3.mouse(game.root.node());

    div.style('position', 'absolute')
        .style('background-color', 'red')
        .style('padding', '10px')
        .style('left', coords[0] + 'px')
        .style('top', coords[1] + 'px');

    div.node().addEventListener('mouseout', function(event) {
        var e = event.toElement || event.relatedTarget;
        if(e.parentNode == div.node() || e == div.node())
            return;

        if(disappear)
            div.remove();
    });

    game.root.node().appendChild(div.node());
};
