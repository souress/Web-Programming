function clickShot() {
    const canvas = document.getElementById("canvas");
    canvas.addEventListener("click", function (event) {
        let xCoord = event.offsetX;
        let yCoord = event.offsetY;
        let r = document.getElementById("form:select-r").value;

        if (r !== null) {
            let x = (xCoord - 200) / 150 * r;
            let y = -1 * (yCoord - 200) / 150 * r;
            document.getElementById("form:x-click").value = x.toFixed(3)
            document.getElementById("form:y-input").value = y.toFixed(3)
            document.getElementById("form:send").click()
        }
    })
}

function drawCircle(x, y, r, color) {
    let xCoord = (150 * x) / r + 200;
    let yCoord = (-150 * y) / r + 200;
    let ctx = document.getElementById("canvas").getContext('2d');
    ctx.beginPath();
    ctx.arc(xCoord, yCoord, 3, 0, 2 * Math.PI);
    ctx.fillStyle = color;
    ctx.fill();
    ctx.stroke();
}