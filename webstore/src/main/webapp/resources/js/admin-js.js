window.onload = function(){
    document.getElementById("product-link").onclick = getData("/products", "product");
    document.getElementById("user-link").onclick = getData("/users", "user");
};

function getData(api, key) {
    return () => {
        let endpoint = location.origin + api;

        fetch(endpoint, {
            method: "GET"
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            else {
                alert("Invalid request");
            }
        })
        .then(data => {

            if (key === "product") {
                processProducts(data);
            }
            else if (key === "user") {
                processUsers(data);
            }

        })
        .catch((reason) => {
            console.log(reason);
        });
    };
}

function createInputFormProduct(){
    let div = document.createElement("div");
    div.setAttribute("id", "add-product");

    let inputName = document.createElement("input");
    inputName.setAttribute("name", "name");
    inputName.setAttribute("placeholder", "Input Product Name");
    div.append(inputName);

    let inputDescription = document.createElement("input");
    inputDescription.setAttribute("name", "description");
    inputDescription.setAttribute("placeholder", "Describe product");
    div.append(inputDescription);

    let inputPrice = document.createElement("input");
    inputPrice.setAttribute("name", "price");
    inputPrice.setAttribute("placeholder", "Price");
    div.append(inputPrice);

    let button = document.createElement("button");
    button.setAttribute("id", "btnAdd");
    button.innerHTML = "Add";
    button.onclick = addProduct;

    div.append(button);
    return div;
}

function addProduct(){
    let name = document.querySelector('#add-product > input[name="name"]').value;
    let description = document.querySelector('#add-product > input[name="description"]').value;
    let price = document.querySelector('#add-product > input[name="price"]').value;

    let product = {name: name, description: description, price: price};
    console.log("Add product: " + product);

    if (validateProduct(product) == false) return;

    let endpoint = location.origin + "/products";

    fetch(endpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(product),
    })
    .then(response => {
        if (response.ok) {
            response.json().then(data => {
                appendNewProduct(data)
            });
        }
        else {
            response.json().then(err => {
                alert(err.error + "\n" + err.detail);
            });
        }
    })
    .catch((reason) => {
        console.log(reason);
    });

}

function validateProduct(product){
    //let product = {name: name, description: description, price: parseInt(price)};
    let error = "";
    if (product.name.length == 0){
        error += "Name is required\n";
    }
    if (product.description.length == 0){
        error += "Description is required\n";
    }
    if (isNaN(product.price) || product.price.length == 0){
        error += "Invalid price\n";
    }

    if (error.length > 0){
        alert(error);
        return false;
    }
    return true;

}

function createHiddenProductRow(product){
    let row = document.createElement("tr");
    row.setAttribute("id", "hidden"+product.productId);
    row.setAttribute("class", "hidden-row");
    row.style.visibility = "collapse";

    let colId = document.createElement("td");
    row.append(colId);

    let colName = document.createElement("td");
        let txtName = document.createElement("input");
        txtName.setAttribute("id", "hiddenName" + product.productId);
        txtName.setAttribute("value", product.name);
    colName.append(txtName);
    row.append(colName);

    let coldDes = document.createElement("td");
        let txtDecs = document.createElement("input");
        txtDecs.setAttribute("value", product.description);
    txtDecs.setAttribute("id", "hiddenDecs" + product.productId);
    coldDes.append(txtDecs);
    row.append(coldDes);

    let colPrice = document.createElement("td");
        let txtPrice = document.createElement("input");
        txtPrice.setAttribute("value", product.price);
        txtPrice.setAttribute("id", "hiddenPrice" + product.productId);
    colPrice.append(txtPrice);
    row.append(colPrice);

    let colModify = document.createElement("td");
    let btnSave = document.createElement("button");
    btnSave.innerHTML = "Save"
    btnSave.setAttribute("value", product.productId);
    btnSave.onclick = updateHandler;

    colModify.append(btnSave);

    row.append(colModify);
    return row;
}

function updateHandler(){
    let id = this.getAttribute("value");

    let ok = confirm(`Do you want to update the product that has Id = ${id}?`);
    if (!ok) return;

    let name = document.getElementById("hiddenName" + id).value;
    let description = document.getElementById("hiddenDecs" + id).value;
    let price = document.getElementById("hiddenPrice" + id).value;

    let product = {productId: id, name: name, description: description, price: price};
    console.log("Update: " + product);

    if (validateProduct(product) == false) return;

    let endpoint = location.origin + "/products";
    fetch(endpoint, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(product),
    })
    .then((response => {
        if (response.ok){
            getData("/products", "product")();
        }
        else {
            response.json().then(err => {
                alert(err.error + "\n" + err.detail);
            });
        }
    }));
}

function createRealProductRow(product){
    let row = document.createElement("tr");
    row.setAttribute("id", product.productId);

    let colId = document.createElement("td");
    colId.innerHTML = product.productId;
    row.append(colId);

    let colName = document.createElement("td");
    colName.innerHTML = product.name;
    row.append(colName);

    let coldDes = document.createElement("td");
    coldDes.innerHTML = product.description;
    row.append(coldDes);

    let colPrice = document.createElement("td");
    colPrice.innerHTML = product.price;
    row.append(colPrice);

    let colModify = document.createElement("td");
    let aEdit = document.createElement("button");
    aEdit.innerHTML = "Edit"
    aEdit.setAttribute("value", product.productId);
    aEdit.onclick = editProduct;
    colModify.append(aEdit);

    let aDelete = document.createElement("button");
    aDelete.innerHTML = "Delete"
    aDelete.setAttribute("value", product.productId);
    aDelete.onclick = deleteProduct;
    colModify.append(aDelete);

    row.append(colModify);

    return row;
}

function addProductRow(table, product){
    table.append(createRealProductRow(product));
    table.append(createHiddenProductRow(product));
}

function createTableProductHeader(){
    let header = document.createElement("tr");

    let colId = document.createElement("th");
    colId.innerHTML = "Id";
    header.append(colId);

    let colName = document.createElement("th");
    colName.innerHTML = "Name";
    header.append(colName);

    let coldDes = document.createElement("th");
    coldDes.innerHTML = "Desc";
    header.append(coldDes);

    let colPrice = document.createElement("th");
    colPrice.innerHTML = "Price";
    header.append(colPrice);

    let colEditDisable = document.createElement("th");
    colEditDisable.innerHTML = "Edit / Delete";
    header.append(colEditDisable);

    return header;
}

function appendNewProduct(product){
    let table = document.querySelector("#right table");
    addProductRow(table, product);
}

function processProducts(products){
    let container = document.getElementById("right");
    container.innerHTML = "";

    let table = document.createElement("table");

    let caption = document.createElement("caption");
    caption.append(createInputFormProduct())
    table.append(caption);

    table.append(createTableProductHeader());
    for (let product of products){
        addProductRow(table, product);
    }
    container.append(table);
}

function editProduct(){
    let id = this.getAttribute("value");
    toggleHiddenProduct(id);
}

function toggleHiddenProduct(id) {
    let row = document.getElementById("hidden" + id);
    if (row.style.visibility === "collapse") {
        row.style.visibility = "visible";
    } else {
        row.style.visibility = "collapse";
    }
}

function deleteProduct() {
    let id = this.getAttribute("value");
    let result = confirm(`Do you want to delete the product with id = ${id} `);

    let endpoint = location.origin + "/products?id=" + id;
    if (result) {
        fetch(endpoint, {
            method: "DELETE",
        })
        .then(response => {
            if (response.ok) {
                getData("/products", "product")();
            }
            else {
                alert("Can not delete the product: " + id);
                console.log(response);
            }
        })
        .catch(reason => console.log(reason));
    }
}

function createTableUserHeader(){
    let header = document.createElement("tr");

    let colId = document.createElement("th");
    colId.innerHTML = "#";
    header.append(colId);

    let colName = document.createElement("th");
    colName.innerHTML = "Uid";
    header.append(colName);

    let coldDes = document.createElement("th");
    coldDes.innerHTML = "FullName";
    header.append(coldDes);

    let colPrice = document.createElement("th");
    colPrice.innerHTML = "Email";
    header.append(colPrice);

    let colEditDisable = document.createElement("th");
    colEditDisable.innerHTML = "SuperUser?";
    header.append(colEditDisable);

    return header;
}

function processUsers(users){
    console.log(users);

    let container = document.getElementById("right");
    container.innerHTML = "";

    let table = document.createElement("table");
    table.append(createTableUserHeader());

    let count = 1;
    for (let user of users){
        table.append(createRealUserRow(user, count++));
    }

    container.append(table);
}

function createRealUserRow(user, idx){
    let row = document.createElement("tr");

    let colId = document.createElement("td");
    colId.innerHTML = idx;
    row.append(colId);

    let colName = document.createElement("td");
    colName.innerHTML = user.username;
    row.append(colName);

    let coldDes = document.createElement("td");
    coldDes.innerHTML = `${user.firstName} ${user.lastName}`;
    row.append(coldDes);

    let colPrice = document.createElement("td");
    colPrice.innerHTML = user.email;
    row.append(colPrice);

    let colSupperUser = document.createElement("td");
    colSupperUser.innerHTML = (user.role == "ADMIN")?"YES":"NO";
    row.append(colSupperUser);

    return row;
}

