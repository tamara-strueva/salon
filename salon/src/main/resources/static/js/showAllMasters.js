function showMastersList() {
    fetch("http://localhost:8082/masters/get")
    .then(response => response.json()) 
    .then(masters => createTableMasters(mastersTable, masters));
}
const mastersTable = document.getElementById('mastersTableBody')

function showMastersByName() {
    const name = document.getElementById("firstName").value
    
    if(name == "") {
        catchErrors("Поле ввода имени пустое!")
    } else {
        const nname = capitalizeFirstLetter(name)
        fetch(`http://localhost:8082/masters/getn/${nname}`)
        .then(response => response.json()) 
        .then(masters => {
            if(masters.length == 0) {
                catchErrors("Информация не найдена!")
            } else {
                createTableMasters(mastersTable, masters)
            }
        })
    }
}


function showMastersByLastName() {
    const lname = document.getElementById("lastName").value

    if(lname == "") {
        catchErrors("Поле ввода фамилии пустое!")
    } else {
        const nlname = capitalizeFirstLetter(lname)
        fetch(`http://localhost:8082/masters/getl/${nlname}`)
        .then(response => response.json()) 
        .then(masters => {
            if(masters.length == 0) {
                catchErrors("Информация не найдена!")
            } else {
                createTableMasters(mastersTable, masters)
            }
        })
    }
}

function showMastersBySpeciality() {
    const speciality = document.getElementById("speciality").value
    if(speciality == ""){
        catchErrors("Поле ввода специальности пустое!")
    } else {
        const sp = speciality.toLowerCase()
        fetch(`http://localhost:8082/masters/gets/${sp}`)
        .then(response => response.json()) 
        .then(masters => {
            if(masters.length == 0) {
                catchErrors("Информация не найдена!")
            } else {
                createTableMasters(mastersTable, masters)
            }
        })
    }
    
}

function showMastersByNameAndSpeciality() {
    const name = document.getElementById("firstName").value
    const speciality = document.getElementById("speciality").value
    
    if(name == "" || speciality== ""){
        catchErrors("Поля ввода имени и специальности пустые!")
    } else {
        const sp = speciality.toLowerCase()
        const nname = capitalizeFirstLetter(name)
        fetch(`http://localhost:8082/masters/getns/${nname}/${sp}`)
        .then(response => response.json()) 
        .then(masters => {
            if(masters.length == 0) {
                catchErrors("Информация не найдена!")
            } else {
                createTableMasters(mastersTable, masters)
            }
        })
    }
    
}

function createTableMasters(table, data) {
    console.log(data)

    table.innerHTML = ""
    const form = document.getElementById("edit")
    form.innerHTML = ""
    const message = document.getElementById("message")
    message.innerHTML = ""

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

        // const td4 = document.createElement("td")
        // td4.innerHTML = data[i].rate
        // tr.appendChild(td4)

        const td5 = document.createElement("td")
        var button = "<button type='button' class='btn btn-warning' onclick='editMaster(" + data[i].id + ")'>Редактировать</button>"
        button += "<button type='button' class='btn btn-danger' onclick='deleteMaster(" + data[i].id + ")'>Удалить</button>"
        td5.innerHTML = button
        tr.appendChild(td5)

    table.appendChild(tr)
    }
    document.getElementById("firstName").value = ""
    document.getElementById("lastName").value = ""
    document.getElementById("speciality").value = ""
}

function deleteMaster(id) {
    fetch(`http://localhost:8082/masters/delete/${id}`, {
        method: "DELETE"
    })
    .then(showMastersList())
}

function editMaster(id) {
    const table = document.getElementById("mastersTableBody")
    table.innerHTML = ""

    fetch(`http://localhost:8082/masters/get/${id}`)
    .then(response => response.json())
    .then(master => drawEditForm(master))
}

function drawEditForm(master) {
    const div = document.getElementById("edit") // найдет form

    var rows = "<form id='editForm'>"
    rows += "<input type='text' class='form-control' id='mLastName' placeholder='Фамилия мастера'>"
    rows += "<input type='text' class='form-control' id='mFirstName' placeholder='Имя мастера'>"
    rows += "<input type='text' class='form-control' id='mSpeciality' placeholder='Специальность'>"
    rows += "<input type='number' class='form-control' id='mExperiance' placeholder='Опыт работы'>"
    rows += "<div id='container'"
    rows += "</div>"
    rows += "</form>"

    div.innerHTML = rows

    const mLastName = document.getElementById("mLastName")
    mLastName.value = master.lastName
    const mFirstName = document.getElementById("mFirstName")
    mFirstName.value = master.firstName
    const mSpeciality = document.getElementById("mSpeciality")
    mSpeciality.value = master.speciality
    const mExperiance = document.getElementById("mExperiance")
    mExperiance.value = master.workExperience

    // "<button type='button' class='btn btn-outline-success' onclick='editConfirm( " + master.id + "'>edit</button>"
    const button = document.createElement("button", {class: "btn btn-outline-success", onclick: "editConfirm(" + master.id + ")"})
    button.innerText = "EDIT"
    button.setAttribute("type", "button")
    button.classList.add("btn", "btn-outline-success")
    button.onclick = function() {
        editConfirm(master.id)
    }
    
    const form = document.getElementById("editForm")
    form.appendChild(button)
}

function editConfirm(id) {
    let fn = document.getElementById("mFirstName").value
    let ln = document.getElementById("mLastName").value
    let exp = document.getElementById("mExperiance").value
    let sp = document.getElementById("mSpeciality").value

    fetch(`http://localhost:8082/masters/edit/${id}`, {
        method: "PUT",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({"firstName": fn, "lastName": ln, "workExperiance": exp, "speciality": sp})
    })
    // .then(response => response.json())
    .then(data => {
        console.log(data);
        showMastersList();
    })
    .catch(error => console.error(error));
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function catchErrors(textErr) {
    const message = document.getElementById("message")
    console.log("message", message)
    text = "<div class='alert alert-danger' role='alert'>"
    text += textErr
    text += "</div>"
    message.innerHTML = text
}
