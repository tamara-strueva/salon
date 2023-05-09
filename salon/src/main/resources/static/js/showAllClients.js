function showClientsList() {
    fetch("http://localhost:8082/clients/get")
    .then(response => response.json()) 
    .then(clients => createTableclients(clientsTable, clients));
}
const clientsTable = document.getElementById('clientsTableBody')

function createTableclients(table, data) {
    console.log(data)

    for(let i = 0; i < data.length; i++) {
        const tr = document.createElement("tr") // <tr>

        const th = document.createElement("th")
        th.innerHTML = data[i].id
        tr.appendChild(th)

        const td1 = document.createElement("td")
        td1.innerHTML = data[i].firstName
        tr.appendChild(td1)
        const td2 = document.createElement("td")
        td2.innerHTML = data[i].lastName
        tr.appendChild(td2)
    table.appendChild(tr)
    }
}