function showServicesList() {
    fetch("http://localhost:8082/services/get")
    .then(response => response.json()) 
    .then(services => createTableServices(servicesTable, services));
}
const servicesTable = document.getElementById('servicesTableBody')

function createTableServices(table, data) {
    console.log(data)

    for(let i = 0; i < data.length; i++) {
        const tr = document.createElement("tr") // <tr>

        const th = document.createElement("th")
        th.innerHTML = data[i].id
        tr.appendChild(th)

        const td1 = document.createElement("td")
        td1.innerHTML = data[i].name
        tr.appendChild(td1)

    table.appendChild(tr)
    }
}