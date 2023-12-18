import Canvas from "./canvas.js";

window.addEventListener("DOMContentLoaded", function () {

    window.canvas = new Canvas("canvas");

    window.redrawCanvasAndDrawPoints = function() {
        let resultsJSON = sessionStorage.getItem("storedData");
        let storedData = resultsJSON ? JSON.parse(resultsJSON) : [];
        window.canvas.clear();
        window.canvas.drawGraph();
        window.canvas.drawSavedPoints(storedData);
    }

    redrawCanvasAndDrawPoints();

    canvas.canvas.addEventListener('click', (event) => {
        let dumb_x = event.clientX - this.canvas.canvas.getBoundingClientRect().left;
        let dumb_y = event.clientY - this.canvas.canvas.getBoundingClientRect().top;
        let [normal_x, normal_y] = canvas.dumb_to_normal(dumb_x, dumb_y);

        normal_x = Math.round(normal_x * 1000) / 1000;
        normal_y = Math.round(normal_y * 1000) / 1000;
        // let r = document.querySelector('input[name="inputR"]:checked').value;
        let selectElement = document.getElementById('myForm:rSelect');
        let r = selectElement.options[selectElement.selectedIndex].value;
        console.log(normal_x, normal_y);
        if (validX(normal_x) && validY(normal_y)) {
            sendAjaxGetRequest(normal_x, normal_y, r);
            document.getElementById("msg").innerHTML = "";
        } else if (!validX(normal_x)) {
            document.getElementById("msg").innerHTML = "Значение X вне области!"
        } else if (!validY(normal_y)) {
            document.getElementById("msg").innerHTML = "Значение Y вне области!";
        }
    });

});

function sendAjaxGetRequest(x, y) {
    console.log(x, y);
    document.getElementById("graphSelect:graphX").value = x;
    document.getElementById("graphSelect:graphY").value = y;
    // console.log("sending coords to the server...");
    updateBeanValues();
}

function validY(inputY){
    if (inputY === undefined){
        return false;
    }
    return -3 <= inputY && inputY <= 5;
}

function validX(inputX){
    if (inputX === undefined){
        return false;
    }
    return -4 <= inputX && inputX <= 4;
}

function validR(inputR){
    if (inputR === undefined){
        return false;
    }
    return 1 <= inputR && inputR <= 3;
}

window.addPoint = function(x, y, r, success) {
    // Создаем объект для точки
    let result = {x: x, y: y, r: r, success: success};

    // Получаем текущий список точек из sessionStorage
    let resultsJSON = sessionStorage.getItem("storedData");
    let storedData = resultsJSON ? JSON.parse(resultsJSON) : [];

    // Добавляем новую точку к списку
    storedData.push(result);

    // Сохраняем обновленный список точек в sessionStorage
    sessionStorage.setItem("storedData", JSON.stringify(storedData));
}