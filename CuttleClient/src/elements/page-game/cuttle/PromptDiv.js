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
        .attr('class', function(d, i) {
            var c = '';
            if(i == 0) c += 'borderTop ';
            if(i == divData.length - 1) c += 'borderBottom ';
            return c;
        })
        .on('click', function(d) {
            div.remove();
            game.promptResponse(d);
        });

    div.attr('class', 'cuttleDiv')
        .style('opacity', '0');

    div.node().addEventListener('mouseout', function(event) {
        var e = event.toElement || event.relatedTarget;
        if(e.parentNode == div.node() || e == div.node())
            return;

        if(disappear)
            div.remove();
    });

    game.root.node().appendChild(div.node());

    var coords;
    if(x && y)
        coords = [x, y];
    else
        coords = d3.mouse(game.root.node());

    var bound = div.node().getBoundingClientRect();

    div
        .style('left', coords[0] + 'px')
        .style('top', (coords[1] - bound.height + 25) + 'px')
        .style('opacity', '0.95');
};
