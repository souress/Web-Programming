function isNum(n) {
    return (!isNaN(parseFloat(n)) && isFinite(n));
}

function isRCorrect(r) {
    return !(Number(r) < 1 || Number(r) > 3);
}

function checkValueR() {
    let select = document.getElementById('r_value');
    let value = select.options[select.selectedIndex].value;
    if (isRCorrect(value) && isNum(value)) {
        document.getElementById("r-error").innerText = "";
        return true;
    } else {
        document.getElementById("r-error").innerText = "Проверьте введенное значение R";
        return false;
    }
}

function checkValueY() {
    let y = document.getElementById("Y_input").value.replace(',', '.');
    if (y === undefined) {
        document.getElementById("y-error").innerText = "Проверьте введенное значение Y";
        return false;
    } else if (!isNum(y)) {
        document.getElementById("y-error").innerText = "Проверьте введенное значение Y";
        return false;
    } else if (y <= -3 || y >= 3) {
        document.getElementById("y-error").innerText = "Проверьте введенное значение Y";
        return false;
    } else {
        document.getElementById("y-error").innerText = "";
        return true;
    }
}


function checkValueX() {
    if (document.querySelector('input[type="radio"]:checked')) {
        let radio = document.querySelector('input[type="radio"]:checked').value;
        if (/(-[1-3)|([0-5])/.test(radio.value)) {
            document.getElementById("x-error").innerText = "Проверьте введенное значение X";
            return false;
        } else {
            document.getElementById("x-error").innerText = "";
            return true;
        }
    } else {
        document.getElementById("x-error").innerText = "Выберите X";
        return false;
    }
}

function validate() {
    let valueX = checkValueX();
    let valueR = checkValueR();
    let valueY = checkValueY();
    if (valueX && valueY && valueR) {
        document.getElementById("data-info").innerText = "Параметры прошли проверку";
        return true;
    } else {
        document.getElementById("data-info").innerText = "Параметры не прошли проверку!";
        return false;
    }
}

$(document).ready(function () {
    $('form').submit(function (event) {
        event.preventDefault();
        if (validate()) {
            let X = document.querySelector('input[type="radio"]:checked').value;
            let Y = document.getElementById("Y_input").value.replace(',', '.');
            let R = document.getElementById("r_value").options[document.getElementById("r_value").selectedIndex].value;
            $.ajax({
                url: "./controller",
                type: "GET",
                data: "X=" + X + "&Y=" + Y + "&R=" + R,
                success: addRows ,
            });
        }
        return false;
    })
})
