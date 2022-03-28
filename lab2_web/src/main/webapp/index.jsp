<%@ page import="util.Data" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="jdk.nashorn.internal.ir.debug.JSONWriter" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Web_Lab_2</title>
    <link href="img/fav.ico" rel="icon">
    <script src="https://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
    <script src="js/validation.js"></script>
    <script src="js/drawCoordinatePlane.js"></script>
    <script src="js/clickShot.js"></script>
    <link rel="stylesheet" href="style/style.css">
</head>
<body id="body">
<form id="form">
    <table class="table" width="" border="2" align="center">
        <tr>
            <th colspan="3"><a class="a" href="https://github.com/souress">Клименко Кирилл Владимирович</a>, P3214,
                Вариант: 3322
            </th>
        </tr>
        <tr>
            <td colspan="3">
                <canvas id="canvas" class="shooting-range" width="400" height="400"></canvas>
                <script>clickShot()</script>
            </td>
            <script>
                drawCoordinatePlane(document.getElementById('canvas').getAttribute('width'), document.getElementById('canvas').getAttribute('height'))
                if (sessionStorage.getItem("canvas")) {
                    drawSavedCanvas();
                }
            </script>
        </tr>
        <tr>
            <th width="20%">Параметр</th>
            <th width="40%">Область допустимых значений</th>
            <th width="40%">Введенное значение</th>
        </tr>
        <tr>
            <th>X</th>
            <th>[-3;5]</th>
            <th>
                <input type="radio" id="X-3" name="X" value="-3">
                <label for="X-3">-3</label>

                <input type="radio" id="X-2" name="X" value="-2">
                <label for="X-2">-2</label>

                <input type="radio" id="X-1" name="X" value="-1">
                <label for="X-1">-1</label>

                <input type="radio" id="X0" name="X" value="0">
                <label for="X0">0</label>

                <input type="radio" id="X1" name="X" value="1">
                <label for="X1">1</label>
                <br>
                <input type="radio" id="X2" name="X" value="2">
                <label for="X2">2</label>

                <input type="radio" id="X3" name="X" value="3">
                <label for="X3">3</label>

                <input type="radio" id="X4" name="X" value="4">
                <label for="X4">4</label>

                <input type="radio" id="X5" name="X" value="5">
                <label for="X5">5</label>
                <br>
                <p><span id="x-error" class="error-message"></span></p>
            </th>
        </tr>
        <tr>
            <th>Y</th>
            <th>(-3;3)</th>
            <th>
                <label for="Y_input"></label><input id="Y_input" name="changeY" type="text" maxlength="17"
                                                    placeholder="Введите число">
                <br>
                <p><span id="y-error" class="error-message"></span></p>
            </th>
        </tr>
        <tr>
            <th>R</th>
            <th>[1; 3]</th>
            <th>
                <select id="r_value" name="r_value">
                    <option disabled selected>Выберите значение R</option>
                    <option value="1">1</option>
                    <option value="1.5">1.5</option>
                    <option value="2">2</option>
                    <option value="2.5">2.5</option>
                    <option value="3">3</option>
                </select>
                <br>
                <p><span id="r-error" class="error-message"></span></p>
            </th>
        </tr>
        <tr>
            <td align="center" colspan="3">
                <input type="submit" name="start" value="Отправить">
                <input type="button" name="erase" value="Очистить холст" onclick="drawCoordinatePlane(document.getElementById('canvas').getAttribute('width'), document.getElementById('canvas').getAttribute('height'));saveCanvas();">
                <br>
                <span id="data-info"></span>
            </td>
        </tr>
    </table>
</form>
<script src="js/drawTable.js"></script>
<script src="js/addRows.js"></script>

<%
    Object rawHistory = session.getAttribute("history");
    LinkedList<Data> history;
    if (rawHistory instanceof LinkedList) {
        history = (LinkedList<Data>) rawHistory;
        for (Iterator<Data> it = history.descendingIterator(); it.hasNext(); ) {
            Data attempt = it.next();
            if (attempt != null) {
                out.println("<script>addRows(" + attempt.dataToJSON() + ");</script>");
            }
        }
    }
%>
</body>
</html>