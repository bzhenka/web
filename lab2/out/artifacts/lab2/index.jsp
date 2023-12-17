<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type="module" src="js/file.js"></script>
    <link rel="stylesheet" href="css/main.css">

</head>
<body>
<h1>Андреева Божена</h1>
<h2>P3231 ВАРИАНТ-8284823</h2>
<form id="myForm">
    <table >
        <tr>
            <td class="date" id="dateForm">

                <p>Введите данные: </p>
                <label for="inputX">X: </label>
                <select id="inputX" name="inputX">
                    <option value="-3">-3</option>
                    <option value="-2">-2</option>
                    <option value="-1">-1</option>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select> <br>

                <label for="inputY">Y:</label>
                <input type="text" id="inputY" placeholder="-5 to 3" min="-5" max="3" step="1" required oninput="this.setCustomValidity('');"><br>

                <label for="inputR">R:</label>
                <label><input type="radio" name="inputR" id="inputR" value="1">1</label>
                <label><input type="radio" name="inputR" id="inputR_1" value="1.5">1.5</label>
                <label><input type="radio" name="inputR" id="inputR_2" value="2">2</label>
                <label><input type="radio" name="inputR" id="inputR_3" value="2.5">2.5</label>
                <label><input type="radio" name="inputR" id="inputR_4" value="3">3</label>

                <button id="submitButton" >Отправить</button>
            </td>
            <td class="picture">
                <canvas id="canvas"></canvas>
                <div id="msg" style="font-weight:700;color:#989898"></div>
            </td>
        </tr>
    </table>
</form>
<table id="table2" class="table2">
    <thead>
    <tr class="result" id="result">
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Результат</th>
        <th>Текущее время</th>
        <th>Время работы</th>
    </tr>
    </thead>
    <tbody id="tableBody">
        <jsp:include page="table.jsp"/>
    </tbody>

</table>
<table>
    <tr>
        <td>
<%--            <button id="clearTable">Очистить</button>--%>
        </td>
    </tr>
</table>
</body>
</html>