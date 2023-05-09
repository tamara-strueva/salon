function showMastersList() {
    fetch("http://localhost:8082/masters/get")
    .then(response => response.json()) 
    .then(masters => createTableMasters(mastersTable, masters));
}
const mastersTable = document.getElementById('mastersTableBody')

function createTableMasters(table, data) {
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
        td2.innerHTML = data[i].speciality
        tr.appendChild(td2)
    table.appendChild(tr)
    }
}