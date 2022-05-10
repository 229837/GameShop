async function displayAll_Comment() {
    const path = "http://localhost:8080/comments";
    const response = await fetch(path);
    const data = await response.json();
    var code = "";
    for(var i = 0; i < data.length; i++) {
        comment = data[i];
        code += comment.uuid + " " + comment.owner + " " + comment.comment + " " + comment.rating + " " + comment.dateTime + "</br>";
    }
    document.getElementById("mainContent").innerHTML = code;
}

async function findByUUID_Comment() {
    var searchText = document.getElementById("textFieldSearchComment").value;
    const path = "http://localhost:8080/comments/find/" + searchText;
    const response = await fetch(path);
    const data = await response.json();
    comment = data;
    var code = comment.uuid + " " + comment.owner + " " + comment.comment + " " + comment.rating + " " + comment.dateTime + "</br>";
    document.getElementById("mainContent").innerHTML = code;
}

async function displayAll_Product() {
    const path = "http://localhost:8080/products";
    const response = await fetch(path);
    const data = await response.json();

    var code = "";
    for(var i=0; i<data.length; i++) {
        product = data[i];
        code += product.id + " " + product.name + " " + product.price + " " + product.genre + "</br>";
    }

    document.getElementById("mainContent").innerHTML = code;
}

async function findByUUID_Product() {
    var searchText = document.getElementById("textFieldSearch_1").value;
    const path = "http://localhost:8080/products/find/" + searchText;
    const response = await fetch(path);
    const data = await response.json();

    product = data;
    var code = product.id + " " + product.name + " " + product.price + " " + product.genre + "</br>";

    document.getElementById("mainContent").innerHTML = code;
}