function isNum(n) {
    return (!isNaN(parseFloat(n)) && isFinite(n));
}

function textChangeR(str) {
    let text = document.getElementById("R_text");
    text.setAttribute("placeholder", str);
    text.setAttribute("value", str);
}

function textChangeX() {
    const checkboxes = Array.from(document.querySelectorAll(".checkboxClass"));
    let values = "";

    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            values = values.concat(checkboxes[i].name + "; ");
        }
    }

    let text = document.getElementById("X_text");
    text.setAttribute("placeholder", values);
}

function isRCorrect(r) {
    return !(Number(r) < 1 || Number(r) > 5);
}

function checkValueR() {
    if (isRCorrect(document.getElementById("R_text").getAttribute("value"))) {
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
    } else if (y < -5 || y > 3) {
        document.getElementById("y-error").innerText = "Проверьте введенное значение Y";
        return false;
    } else {
        document.getElementById("y-error").innerText = "";
        return true;
    }
}

function isXCorrect(arrX) {
    let result = false;
    for (let i = 0; i < arrX.length - 1; i++) {
        if (Number(arrX[i]) < -5 || Number(arrX[i]) > 3) return false;
        else result = true;
    }
    return result;
}

function checkValueX() {
    const checkboxes = Array.from(document.querySelectorAll(".checkboxClass"));
    if (!(checkboxes.reduce((buffer, current) => buffer || current.checked, false))) {
        document.getElementById("x-error").innerText = "Выберите хотя бы одно значение X";
    }
    else if (!isXCorrect(document.getElementById("X_text").getAttribute("placeholder").split("; "))) {
        document.getElementById("x-error").innerText = "Вы что-то сломали...";
        return false;
    } else
        document.getElementById("x-error").innerText = "";
    return checkboxes.reduce((buffer, current) => buffer || current.checked, false);
}

function validate() {
    let valueX = checkValueX();
    let valueR = checkValueR();
    let valueY = checkValueY();
    if (valueX && valueY && valueR) {
        document.getElementById("data-info").innerText = "Параметры прошли проверку";
        return true;
    }
    else {
        document.getElementById("data-info").innerText = "Параметры не прошли проверку!";
        return false;
    }
}

$(document).ready(function () {
    $('form').submit( function (event) {
        event.preventDefault()
        if (validate()) {
            let X = document.getElementById("X_text").getAttribute("placeholder");
            let Y = document.getElementById("Y_input").value.replace(',', '.');
            let R = document.getElementById("R_text").getAttribute("value");
            $.ajax({
                url: "php/script.php",
                type: "POST",
                data: "X=" + X + "&Y=" + Y + "&R=" + R,
                success: addRows,
            });
        }
        return false;
    })
})
