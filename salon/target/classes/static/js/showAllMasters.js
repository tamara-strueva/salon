function showMastersList() {
    fetch("http://localhost:8082/masters/get")
    .then(response => response.json()) 
    .then(masters => createTableMasters(mastersTable, masters));
}
const mastersTable = document.getElementById('mastersTableBody')

function createTableMasters(table, data) {
    console.log(data)

    table.innerHTML = ""

    for(let i = 0; i < data.length; i++) {
        const tr = document.createElement("tr", {class: "d-flex"}) // <tr>

        const th = document.createElement("th")
        th.innerHTML = data[i].id
        tr.appendChild(th)

        const td1 = document.createElement("td")
        td1.innerHTML = data[i].firstName
        tr.appendChild(td1)

        const td3 = document.createElement("td")
        td3.innerHTML = data[i].lastName
        tr.appendChild(td3)

        const td2 = document.createElement("td")
        td2.innerHTML = data[i].speciality
        tr.appendChild(td2)

        const td4 = document.createElement("td")
        td4.innerHTML = data[i].workExperience
        tr.appendChild(td4)

    table.appendChild(tr)
    }
}

function showMastersByName() {
    const name = document.getElementById("firstName").value

    fetch(`http://localhost:8082/masters/getn/${name}`)
    .then(response => response.json()) 
    .then(masters => createTableMastersName(mastersTable, masters, name));
}

function createTableMastersName(table, data, fname) {
    console.log(data)

    table.innerHTML = ""

    for(let i = 0; i < data.length; i++) {
        if(data[i].firstName == fname) {
            const tr = document.createElement("tr", {class: "d-flex"}) // <tr>

            const th = document.createElement("th")
            th.innerHTML = data[i].id
            tr.appendChild(th)

            const td1 = document.createElement("td")
            td1.innerHTML = data[i].firstName
            tr.appendChild(td1)

            const td3 = document.createElement("td")
            td3.innerHTML = data[i].lastName
            tr.appendChild(td3)

            const td2 = document.createElement("td")
            td2.innerHTML = data[i].speciality
            tr.appendChild(td2)

            const td4 = document.createElement("td")
            td4.innerHTML = data[i].workExperience
            tr.appendChild(td4)

        table.appendChild(tr)
        }
    }
}



function showMastersByLastName() {
    const lname = document.getElementById("lastName").value

    fetch(`http://localhost:8082/masters/getl/${lname}`)
    .then(response => response.json()) 
    .then(masters => createTableMastersLastName(mastersTable, masters, lname));
}

function createTableMastersLastName(table, data, name) {
    console.log(data)

    table.innerHTML = "" // очищает тело таблицы

    for(let i = 0; i < data.length; i++) {
        if(data[i].lastName == name) {
            const tr = document.createElement("tr", {class: "d-flex"}) // <tr>

            const th = document.createElement("th")
            th.innerHTML = data[i].id
            tr.appendChild(th)

            const td1 = document.createElement("td")
            td1.innerHTML = data[i].firstName
            tr.appendChild(td1)

            const td3 = document.createElement("td")
            td3.innerHTML = data[i].lastName
            tr.appendChild(td3)

            const td2 = document.createElement("td")
            td2.innerHTML = data[i].speciality
            tr.appendChild(td2)

            const td4 = document.createElement("td")
            td4.innerHTML = data[i].workExperience
            tr.appendChild(td4)

        table.appendChild(tr)
        }
    }
}



function showMastersBySpeciality() {
    const speciality = document.getElementById("speciality").value

    fetch(`http://localhost:8082/masters/gets/${speciality}`)
    .then(response => response.json()) 
    .then(masters => createTableMastersSpeciality(mastersTable, masters, speciality));
}

function createTableMastersSpeciality(table, data, speciality) {
    console.log(data)

    table.innerHTML = "" // очищает тело таблицы

    for(let i = 0; i < data.length; i++) {
        if(data[i].speciality == speciality) {
            const tr = document.createElement("tr", {class: "d-flex"}) // <tr>

            const th = document.createElement("th")
            th.innerHTML = data[i].id
            tr.appendChild(th)

            const td1 = document.createElement("td")
            td1.innerHTML = data[i].firstName
            tr.appendChild(td1)

            const td3 = document.createElement("td")
            td3.innerHTML = data[i].lastName
            tr.appendChild(td3)

            const td2 = document.createElement("td")
            td2.innerHTML = data[i].speciality
            tr.appendChild(td2)

            const td4 = document.createElement("td")
            td4.innerHTML = data[i].workExperience
            tr.appendChild(td4)

        table.appendChild(tr)
        }
    }
}
