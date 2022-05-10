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