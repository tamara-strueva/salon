function showOrdersList() {
    fetch("http://localhost:8082/orders/get")
    .then(response => response.json()) 
    .then(orders => createTableOrders(ordersTable, orders));
    // .then(orders => console.log(orders)
}

const ordersTable = document.getElementById('ordersTableBody')

function createTableOrders(table, data) {
    console.log(data)

    table.innerHTML = ""

    for(let i = 0; i < data.length; i++) {
        const tr = document.createElement("tr", {class: "d-flex"}) // <tr>

        const th = document.createElement("th")
        th.innerHTML = data[i].id
        tr.appendChild(th)

        const td1 = document.createElement("td")
        td1.innerHTML = data[i].master.firstName
        tr.appendChild(td1)

        const td3 = document.createElement("td")
        td3.innerHTML = data[i].client.firstName + " " + data[i].client.lastName
        tr.appendChild(td3)

        const td2 = document.createElement("td")
        td2.innerHTML = data[i].services[0].name
        // console.log(data[i].services)
        // createListOfServices(data[i])
        tr.appendChild(td2)

        const td4 = document.createElement("td")
        td4.innerHTML = data[i].day
        tr.appendChild(td4)

    table.appendChild(tr)
    }
}

function createListOfServices(data) {
    // const ol = document.createElement("ol")
    for(let i = 0; i < data.length; i++) {
        innerHTML = data[i].name
        // const li = document.createElement("li")
        // li.innerHTML = data[i].name
        // ol.appendChild(li)
    }
    // document.body.appendChild(ol)
}


// const mainUL = document.createElement("ol") // <ol>
//     for(let i = 0; i < data.length; i++) {
//         const LI = document.createElement('li'); // <li>
//         if (data[i].firstName == name) {
//             LI.innerHTML = data[i].firstName
//         } 
//     mainUL.appendChild(LI); // </li>
//     }
//     document.body.appendChild(mainUL); // </ol>
//     console.log(data)