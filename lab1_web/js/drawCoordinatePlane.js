function drawCoordinatePlane (x, y) {
    let r = (x + y) / 2 * 0.0006;
    let ctx = document.getElementById('canvas').getContext('2d');

    ctx.beginPath();
    ctx.strokeStyle = '#113450';
    ctx.lineWidth = 2;
    canvas_arrow(ctx, x / 2, y, x / 2, 0);
    canvas_arrow(ctx, 0, y / 2, x, y / 2);
    ctx.stroke();

    ctx.beginPath();
    ctx.lineWidth = 1;
    ctx.moveTo(x / 2, y / 2 + r);
    ctx.arc(x / 2, y / 2, r, Math.PI/2, Math.PI);
    ctx.lineTo(x / 2,y / 2);
    ctx.closePath();
    ctx.fillStyle = '#197bb0';
    ctx.fill();
    ctx.stroke();
    ctx.beginPath();

    ctx.moveTo(x / 2, y / 2 - r /2 );
    ctx.lineTo(x / 2, y / 2);
    ctx.lineTo(x / 2 + r, y / 2);
    ctx.lineTo(x / 2 + r, y / 2 - r / 2);
    ctx.closePath();
    ctx.fillStyle = '#197bb0';
    ctx.fill();
    ctx.stroke();
    ctx.beginPath();

    ctx.moveTo(x / 2, y / 2);
    ctx.lineTo(x / 2, y / 2 + r / 2);
    ctx.lineTo(x / 2 + r, y / 2);
    ctx.closePath();
    ctx.fillStyle = '#197bb0';
    ctx.fill();
    ctx.stroke();

    ctx.fillStyle = "white";
    ctx.font = "17px sans-serif";
    ctx.fillText('-R', x / 2 + x / 30, y / 2 + r);
    ctx.fillText('-R/2', x / 2 + x / 30, y / 2 + r / 2);

    ctx.fillText('-R', x / 2 - r, y / 2 - y / 30);
    ctx.fillText('-R/2', x / 2 - r / 2, y / 2 - y / 30);

    ctx.fillText('R', x / 2 + x / 30, y / 2 - r);
    ctx.fillText('R/2', x / 2 + x / 30, y / 2 - r / 2);

    ctx.fillText('R', x / 2 + r, y / 2 - y / 30);
    ctx.fillText('R/2', x / 2 + r / 2, y / 2 - y / 30);

    ctx.beginPath();
    ctx.strokeStyle = 'white';
    ctx.moveTo(x / 2, y / 2 + r);
    ctx.lineTo(x / 2 + x / 50, y / 2 + r);
    ctx.moveTo(x / 2, y / 2 + r / 2);
    ctx.lineTo(x / 2 + x / 50, y / 2 + r / 2);
    ctx.moveTo(x / 2, y / 2 - r / 2);
    ctx.lineTo(x / 2 + x / 50, y / 2 - r / 2);
    ctx.moveTo(x / 2, y / 2 - r);

    ctx.lineTo(x / 2 + x / 50, y / 2 - r);
    ctx.moveTo(x / 2 + r, y / 2);
    ctx.lineTo(x / 2 + r, y / 2 - y / 50);
    ctx.moveTo(x / 2 + r / 2, y / 2);
    ctx.lineTo(x / 2 + r / 2, y / 2 - y / 50);
    ctx.moveTo(x / 2 - r / 2, y / 2);
    ctx.lineTo(x / 2 - r / 2, y / 2 - y / 50);
    ctx.moveTo(x / 2 - r, y / 2);
    ctx.lineTo(x / 2 - r, y / 2 - y / 50);

    ctx.stroke();


}

function canvas_arrow(context, fromX, fromY, toX, toY) {
    let headLength = 10;
    let dx = toX - fromX;
    let dy = toY - fromY;
    let angle = Math.atan2(dy, dx);
    context.moveTo(fromX, fromY);
    context.lineTo(toX, toY);
    context.lineTo(toX - headLength * Math.cos(angle - Math.PI / 6), toY - headLength * Math.sin(angle - Math.PI / 6));
    context.moveTo(toX, toY);
    context.lineTo(toX - headLength * Math.cos(angle + Math.PI / 6), toY - headLength * Math.sin(angle + Math.PI / 6));
}