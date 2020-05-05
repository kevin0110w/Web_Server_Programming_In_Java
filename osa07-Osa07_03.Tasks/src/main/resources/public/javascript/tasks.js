var url = contextRoot + "tasks";
var http = new XMLHttpRequest();

http.onreadystatechange = function() {
    if (this.readyState != 4 || this.status != 200) {
        return
    };

    var task = JSON.parse(this.responseText);
    console.log(task);
    for (i = 0; i < task.length; i++) {
        var text1 = document.createElement("tr");
        text1.appendChild(document.createTextNode(task[i].name));
        document.getElementById("tasktable").appendChild(text1);
    };
};

http.open("GET", url, true);
http.send();

function addTask() {
    var data = {
        name: document.getElementById("name").value
    };

    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send(JSON.stringify(data));
};