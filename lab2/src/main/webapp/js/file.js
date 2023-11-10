import Canvas from "./canvas.js";

window.addEventListener('load', function () {
    let canvas = new Canvas("canvas");

    canvas.drawGraph();

    // Найдем все радио-кнопки в группе по имени
    const radioButtons = document.querySelectorAll('input[type="radio"][name="inputR"]');

    // Добавляем обработчик событий к каждой радио-кнопке
    radioButtons.forEach(radioButton => {
        radioButton.addEventListener('change', radioButtonChange);
    });

    // Функция, которая будет вызвана при изменении состояния радио-кнопки
    function radioButtonChange(event) {
        if (event.target.checked) {
            canvas.clear();
            canvas.setRadius(event.target.value);
            canvas.drawGraph();
            canvas.drawSavedPoints();
        }
    }

    canvas.canvas.addEventListener('click', (event) => {
        if (document.querySelector('input[name="inputR"]:checked') == null) {
            return;
        }

        let dumb_x = event.clientX - this.canvas.getBoundingClientRect().left;
        let dumb_y = event.clientY - this.canvas.getBoundingClientRect().top;
        console.log(dumb_x, dumb_y);
        let [normal_x, normal_y] = canvas.dumb_to_normal(dumb_x, dumb_y);

        normal_x = Math.round(normal_x);
        normal_y = Math.round(normal_y * 1000) / 1000;
        let r = document.querySelector('input[name="inputR"]:checked').value;

        if (validX(normal_x) && validY(normal_y)){
            sendRequest(normal_x, normal_y, r)
            document.getElementById("msg").innerHTML = "";
        }else if (!validX(normal_x)) {document.getElementById("msg").innerHTML = "Значение X вне области!"}
        else if (!validY(normal_y)) document.getElementById("msg").innerHTML = "Значение Y вне области!"

    });

    document.getElementById("myForm").addEventListener("submit", function(event) {
        event.preventDefault();

        // Get the values of inputX, inputY, and inputR
        let x = document.getElementById("inputX").value;
        let y = document.getElementById("inputY").value;
        let r = document.querySelector('input[name="inputR"]:checked').value;

        if (!validR(r)) {
            document.getElementById("inputR").setCustomValidity("Неправильные данные в R");
            document.getElementById("inputR").reportValidity();
            return false;
        }
        if (!validY(y)) {
            document.getElementById("inputY").setCustomValidity("Неправильные данные в Y");
            document.getElementById("inputY").reportValidity();
            return false;
        }

        sendRequest(x, y, r);
    });

    let dataTable = document.getElementById('table2').getElementsByTagName('tbody')[0];
    document.getElementById("clearTable").addEventListener("click", function() {
        localStorage.clear;
        localStorage.setItem("storedData", '');
        while (dataTable.firstChild) {
            dataTable.removeChild(dataTable.firstChild);
        }
    });


    function sendRequest(x, y, r) {
        let xhr = new XMLHttpRequest();
        xhr.open("GET", "Controller?X=" + x + "&Y=" + y + "&R=" + r, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let response = JSON.parse(xhr.responseText);

                populateTable(response);


                canvas.drawResult(response);

                let resultsJSON = localStorage.getItem("storedData");
                let storedData = resultsJSON ? JSON.parse(resultsJSON) : [];

                storedData.push(response);

                resultsJSON = JSON.stringify(storedData);
                localStorage.setItem("storedData", resultsJSON);
            }
        };
        xhr.send();
    }
});

function populateTable(data) {
    let tableBody = document.getElementById("tableBody");

    // Create a new row and cells
    let newRow = tableBody.insertRow();
    let cellX = newRow.insertCell(0);
    let cellY = newRow.insertCell(1);
    let cellR = newRow.insertCell(2);
    let cellResult = newRow.insertCell(3);
    let cellCurrentTime = newRow.insertCell(4);
    let cellExecutionTime = newRow.insertCell(5);

    // Populate the cells with data
    cellX.innerHTML = data.x;
    cellY.innerHTML = data.y;
    cellR.innerHTML = data.r;
    cellResult.innerHTML = data.hit;
    cellCurrentTime.innerHTML = data.time;
    cellExecutionTime.innerHTML = data.duration;
}
function validY(inputY){
    if (inputY === undefined){
        return false;
    }
    return -5 <= inputY && inputY <= 3;
}
function validX(inputX){
    if (inputX === undefined){
        return false;
    }
    return -3 <= inputX && inputX <= 5
}
function validR(inputR){
    if (inputR === undefined){
        return false;
    }
    return 1 <= inputR && inputR <= 3;


}
