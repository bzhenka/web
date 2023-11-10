window.addEventListener('load', function () {
    let resultsJSON = localStorage.getItem("storedData");
    let storedData = resultsJSON ? JSON.parse(resultsJSON) : [];

    const tableBody = document.getElementById("tableBody");
    tableBody.innerHTML = "";
    storedData.forEach(function(element) {
        populateTable(element);
    });

    document.getElementById("myForm").addEventListener("submit", function(event) {
        event.preventDefault();
        let tz = Intl.DateTimeFormat().resolvedOptions().timeZone;

        // Get the values of inputX, inputY, and inputR
        var x = document.getElementById("inputX").value;
        var y = document.getElementById("inputY").value;
        var r = document.getElementById("inputR").value;

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
        

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "php/server.php?coordinateX=" + x + "&coordinateY=" + y + "&coordinateR=" + r + "&tz=" + tz, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {

                var response = JSON.parse(xhr.responseText);

                populateTable(response);

                let resultsJSON = localStorage.getItem("storedData");
                var storedData = resultsJSON ? JSON.parse(resultsJSON) : [];

                storedData.push(response);

                resultsJSON = JSON.stringify(storedData);
                localStorage.setItem("storedData", resultsJSON);
            }
        };
        xhr.send();
    });
    var dataTable = document.getElementById('table2').getElementsByTagName('tbody')[0];
    document.getElementById("clearTable").addEventListener("click", function() {
        localStorage.clear;
        localStorage.setItem("storedData", '');
        while (dataTable.firstChild) {
            dataTable.removeChild(dataTable.firstChild);
        }
    });
});

function populateTable(data) {
    var tableBody = document.getElementById("tableBody");

    // Create a new row and cells
    var newRow = tableBody.insertRow();
    var cellX = newRow.insertCell(0);
    var cellY = newRow.insertCell(1);
    var cellR = newRow.insertCell(2);
    var cellResult = newRow.insertCell(3);
    var cellCurrentTime = newRow.insertCell(4);
    var cellExecutionTime = newRow.insertCell(5);

    // Populate the cells with data
    cellX.innerHTML = data.x;
    cellY.innerHTML = data.y;
    cellR.innerHTML = data.r;
    cellResult.innerHTML = data.coordsStatus;
    cellCurrentTime.innerHTML = data.currentTime;
    cellExecutionTime.innerHTML = data.benchmarkTime;
}
function validY(inputY){
    if (inputY === undefined){
        return false;
    }
    else if (-3<=inputY &&inputY<=3){
        return true;
    }
    return false;
    
}
function validR(inputR){
    if (inputR === undefined){
        return false;
    }
    else if (2<=inputR && inputR<=5){
        return true;
    }
    return false;
    
}