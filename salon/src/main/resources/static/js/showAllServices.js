function showServicesList() {
    fetch("http://localhost:8082/services/get")
    .then(response => response.json()) 
    .then(services => createTableServices(servicesTable, services));
}
const servicesTable = document.getElementById('servicesTableBody')

function showServicesByName() {
    const sname = document.getElementById("name").value 
    console.log(sname)

    fetch(`http://localhost:8082/services/getn/${sname}`)
    .then(response => response.json())
    .then(services => createTableServices(servicesTable, services))
}

function createTableServices(table, data) {
    console.log(data)

    table.innerHTML = ""

    for(let i = 0; i < data.length; i++) {
        const tr = document.createElement("tr", {class: "d-flex"}) // <tr>

        const th = document.createElement("th")
        th.innerHTML = data[i].id
        tr.appendChild(th)

        const td1 = document.createElement("td")
        td1.innerHTML = data[i].name
        tr.appendChild(td1)

        const td2 = document.createElement("td")
        td2.innerHTML = data[i].duration + " мин."
        tr.appendChild(td2)

        const td3 = document.createElement("td")
        td3.innerHTML = data[i].basePrice + " руб."
        tr.appendChild(td3)

    table.appendChild(tr)
    }
    document.getElementById("master").value = ""
    document.getElementById("day").value = ""
    document.getElementById("timeBegin").value = ""
}

// function showServicesByName() {
//     const sname = document.getElementById("name").value 
//     console.log(sname)

//     fetch(`http://localhost:8082/services/getn/${sname}`)
//     .then(response => response.json())
//     .then(services => createTableServices(servicesTable, services))
// }

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}