function searchProduct()
{
    var x = document.getElementById("textFieldSearch_1").value;
    window.location.replace("/products/find/" + x);
}