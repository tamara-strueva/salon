function showOrdersList() {
    fetch("http://localhost:8082/orders/get")
    .then(response => response.json())
    .then(orders => createTableOrders(ordersTable, orders));
}
const ordersTable = document.getElementById('ordersTableBody')

// http://localhost:8082/orders/getm by master
// http://localhost:8082/orders/getc by client
// by date

function createTableOrders(table, data) {
    console.log(data)

    table.innerHTML = ""

    for(let i = 0; i < data.length; i++) {
        const tr = document.createElement("tr", {class: "d-flex"}) // <tr>

        const th = document.createElement("th")
        th.innerHTML = data[i].id
        tr.appendChild(th)

        const td1 = document.createElement("td")
        td1.innerHTML = data[i].master.firstName + " " + data[i].master.lastName + " (" + data[i].master.speciality + ")"
        tr.appendChild(td1)

        const td3 = document.createElement("td")
        td3.innerHTML = data[i].client.firstName + " " + data[i].client.lastName
        tr.appendChild(td3)

        const td2 = document.createElement("td")
        const ol = document.createElement("ol")
        for (let j = 0; j < data[i].services.length; j++) {
            const li = document.createElement("li")
            li.innerHTML = data[i].services[0].name
            ol.appendChild(li)
        }
        td2.appendChild(ol)
        tr.appendChild(td2)

        const td4 = document.createElement("td")
        td4.innerHTML = data[i].day
        tr.appendChild(td4)

        const td5 = document.createElement("td")
        var button = "<button type='button' class='btn btn-warning' onclick='editOrder(" + data[i].id + ")'>Редактировать</button>"
        button += "<button type='button' class='btn btn-danger' onclick='deleteOrder(" + data[i].id + ")'>Удалить</button>"
        td5.innerHTML = button
        tr.appendChild(td5)
    table.appendChild(tr)
    }
    document.getElementById("master").value = ""
    document.getElementById("client").value = ""
    document.getElementById("service").value = ""
    document.getElementById("date").value = ""
}

function deleteOrder(id) {
    fetch(`http://localhost:8082/orders/delete/${id}`, {
        method: "DELETE"
    })
    .then(showOrdersList())
}

function editOrder(id){
    fetch(`http:localhost:8082/orders/get/${id}`)
}
