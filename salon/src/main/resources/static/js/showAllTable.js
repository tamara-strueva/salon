// function showClientsList() {
//     fetch("http://localhost:8082/clients/get")
//     .then(response => response.json()) 
//     .then(clients => createTable(clientsTable, clients));
// }
// const clientsTable = document.getElementById('clientsTableBody')

// function showMastersList() {
//     fetch("http://localhost:8082/masters/get")
//     .then(response => response.json()) 
//     .then(masters => createTable(mastersTable, masters));
// }
// const mastersTable = document.getElementById('mastersTableBody')

// function showServicesList() {
//     fetch("http://localhost:8082/services/get")
//     .then(response => response.json()) 
//     .then(services => createTableServices(servicesTable, services));
// }
// const servicesTable = document.getElementById('servicesTableBody')


// <tr> <th>id</th> <td>fn</td> <td>ln</td></tr> 
// clients
// function createTableclients(table, data) {
//     console.log(data)

//     for(let i = 0; i < data.length; i++) {
//         const tr = document.createElement("tr") // <tr>

//         const th = document.createElement("th")
//         th.innerHTML = data[i].id
//         tr.appendChild(th)

//         const td1 = document.createElement("td")
//         td1.innerHTML = data[i].firstName
//         tr.appendChild(td1)
//         const td2 = document.createElement("td")
//         td2.innerHTML = data[i].lastName
//         tr.appendChild(td2)
//     table.appendChild(tr)
//     }
// }

// function createTableMasters(table, data) {
//     console.log(data)

//     for(let i = 0; i < data.length; i++) {
//         const tr = document.createElement("tr") // <tr>

//         const th = document.createElement("th")
//         th.innerHTML = data[i].id
//         tr.appendChild(th)

//         const td1 = document.createElement("td")
//         td1.innerHTML = data[i].firstName
//         tr.appendChild(td1)
//         const td2 = document.createElement("td")
//         td2.innerHTML = data[i].speciality
//         tr.appendChild(td2)
//     table.appendChild(tr)
//     }
// }

// function createTableServices(table, data) {
//     console.log(data)

//     for(let i = 0; i < data.length; i++) {
//         const tr = document.createElement("tr") // <tr>

//         const th = document.createElement("th")
//         th.innerHTML = data[i].id
//         tr.appendChild(th)

//         const td1 = document.createElement("td")
//         td1.innerHTML = data[i].name
//         tr.appendChild(td1)

//     table.appendChild(tr)
//     }
// }