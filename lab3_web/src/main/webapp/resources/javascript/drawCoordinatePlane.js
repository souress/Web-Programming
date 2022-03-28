function drawCoordinatePlane (x, y) {
    let r = 150;
    let ctx = document.getElementById('canvas').getContext('2d');

    ctx.clearRect(0, 0, x, y);
    ctx.beginPath();
    ctx.strokeStyle = '#113450';
    ctx.lineWidth = 2;
    canvas_arrow(ctx, x / 2, y, x / 2, 0);
    canvas_arrow(ctx, 0, y / 2, x, y / 2);
    ctx.stroke();

    ctx.beginPath();
    ctx.lineWidth = 1;
    ctx.moveTo(x / 2, y / 2);
    ctx.arc(x / 2, y / 2, r, Math.PI/2, Math.PI);
    ctx.lineTo(x / 2,y / 2);
    ctx.closePath();
    ctx.fillStyle = '#fae74d';
    ctx.fill();
    ctx.stroke();
    ctx.beginPath();

    ctx.moveTo(x / 2, y / 2);
    ctx.lineTo(x / 2 + r / 2, y / 2);
    ctx.lineTo(x / 2 + r / 2, y / 2 + r);
    ctx.lineTo(x / 2, y / 2 + r);
    ctx.closePath();
    ctx.fillStyle = '#fae74d';
    ctx.fill();
    ctx.stroke();
    ctx.beginPath();

    ctx.moveTo(x / 2, y / 2);
    ctx.lineTo(x / 2, y / 2 - r);
    ctx.lineTo(x / 2 + r / 2, y / 2);
    ctx.closePath();
    ctx.fillStyle = '#fae74d';
    ctx.fill();
    ctx.stroke();

    ctx.fillStyle = "black";
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

function drawCoordinatePlaneWithDots(list){
    drawSavedCanvas();
    for(let i in list){
        if(i !== "diff" && !Number.isNaN(i)) drawCircle(list[i].x, list[i].y, list[i].r, list[i].result ? "green" : "red")
    }
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

function saveCanvas() {
    let canvas = document.getElementById("canvas");
    sessionStorage.setItem("canvas", canvas.toDataURL());
}

function drawSavedCanvas() {
    if (sessionStorage.getItem("canvas") === null) return false;
    else {
        let loadedCanvas = sessionStorage.getItem("canvas");
        let ctx = document.getElementById("canvas").getContext("2d");
        let img = new Image;
        img.src = loadedCanvas;
        img.onload = function () {
            ctx.drawImage(img, 0, 0);
        };
        return true;
    }
}

function eraseCanvas() {
    drawCoordinatePlane(document.getElementById("canvas").getAttribute("width"), document.getElementById("canvas").getAttribute("height"));
    saveCanvas();
}