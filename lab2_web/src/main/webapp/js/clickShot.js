function clickShot() {
    const canvas = document.getElementById("canvas");
    canvas.addEventListener("click", function (event) {
        let xCoord = event.offsetX;
        let yCoord = event.offsetY;
        let r = $('#r_value').val();

        if (r !== null) {
            $('#r-error').html("");
            let x = (xCoord - 200) / 150 * r;
            let y = -1 * (yCoord - 200) / 150 * r;
            $.ajax({
                url: "./controller",
                type: "GET",
                data: "X=" + x + "&Y=" + y + "&R=" + r,
                success: function (data) {
                    addRows(data);
                    let xCoord = 150 * data.x / data.r + 200;
                    let yCoord = -150 * data.y / data.r + 200;
                    if (data.result === "Есть") {
                        drawCircle(xCoord, yCoord, "green");
                    } else drawCircle(xCoord, yCoord, "red");
                    saveCanvas();
                },
            });
        } else $('#r-error').html("Выберите значение R!");
    })
}

function drawCircle(x, y, color) {
    let ctx = document.getElementById("canvas").getContext('2d');
    ctx.beginPath();
    ctx.arc(x, y, 3, 0, 2 * Math.PI);
    ctx.fillStyle = color;
    ctx.fill();
    ctx.stroke();
}