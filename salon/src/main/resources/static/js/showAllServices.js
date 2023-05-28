function showServicesList() {
    fetch("http://localhost:8082/services/get")
    .then(response => response.json()) 
    .then(services => createTableServices(servicesTable, services));
}
const servicesTable = document.getElementById('servicesTableBody')
 
function showServicesByName() {
    const sname = document.getElementById("name").value
    if(sname == "") {
        catchErrors("Поле ввода специальности пустое!")
    } else {
        const nsname = sname.toLowerCase()
        fetch(`http://localhost:8082/services/getn/${nsname}`)
        .then(response => response.json())
        .then(services => {
            if(services.length == 0) {
                catchErrors("Информация не найдена!")
            } else {
                createTableServices(servicesTable, services)
            }
        })
    }
}

function createTableServices(table, data) {
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
        td1.innerHTML = data[i].name
        tr.appendChild(td1)

        const td2 = document.createElement("td")
        if(data[i].duration < 60) {
            td2.innerHTML = data[i].duration + " мин."
        } else {
            let h = Math.floor(data[i].duration/60)
            let m = data[i].duration%60
            td2.innerHTML = h + " ч. " + m + " мин."
        }
        tr.appendChild(td2)

        const td3 = document.createElement("td")
        td3.innerHTML = data[i].basePrice + " руб."
        tr.appendChild(td3)

        const td5 = document.createElement("td")
        var button = "<button type='button' class='btn btn-warning' onclick='editService(" + data[i].id + ")'>Редактировать</button>"
        button += "<button type='button' class='btn btn-danger' onclick='deleteService(" + data[i].id + ")'>Удалить</button>"
        td5.innerHTML = button
        tr.appendChild(td5)
 
    table.appendChild(tr)
    }
    document.getElementById("name").value = ""
}

function deleteService(id) {
    fetch(`http://localhost:8082/services/delete/${id}`, {
        method: "DELETE"
    })
    .then(showServicesList())
}

function editService(id) {
    const table = document.getElementById("servicesTableBody")
    table.innerHTML = ""

    fetch(`http://localhost:8082/services/get/${id}`)
    .then(response => response.json())
    .then(service => drawEditForm(service))
}

function drawEditForm(service) {
    const div = document.getElementById("edit") // найдет form
    // draw inputs
    var rows = "<form id='editForm'>"
    rows += "<input type='text' class='form-control' id='sName' placeholder='Название'>"
    rows += "<input type='number' class='form-control' id='sDuration' placeholder='Продолжительность'>"
    rows += "<input type='number' class='form-control' id='sPrice' placeholder='Цена'>"
    // rows += "<input type='text' class='form-control' id='mExperiance' placeholder='Опыт работы'>"
    rows += "</form>"

    div.innerHTML = rows

    const sName = document.getElementById("sName")
    sName.value = service.name
    const sDuration = document.getElementById("sDuration")
    sDuration.value = service.duration
    const sPrice = document.getElementById("sPrice")
    sPrice.value = service.basePrice

    // "<button type='button' class='btn btn-outline-success' onclick='editConfirm( " + master.id + "'>edit</button>"
    const button = document.createElement("button", {class: "btn btn-outline-success", onclick: "editConfirm(" + service.id + ")"})
    button.innerText = "EDIT"
    button.setAttribute("type", "button")
    button.classList.add("btn", "btn-outline-success")
    button.onclick = function() {
        editConfirm(service.id)
    }
    
    const form = document.getElementById("editForm")
    form.appendChild(button)
}

function editConfirm(id) {
    let name = document.getElementById("sName").value
    let dur = document.getElementById("sDuration").value
    let pr = document.getElementById("sPrice").value
    // let sp = document.getElementById("mSpeciality").value

    fetch(`http://localhost:8082/services/edit/${id}`, {
        method: "PUT",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({"name": name, "duration": dur, "basePrice": pr})
    })
    // .then(response => response.json())
    .then(data => {
        console.log(data);
        showServicesList();
    })
    .catch(error => console.error(error));
}

function catchErrors(textErr) {
    const message = document.getElementById("message")
    console.log("message", message)
    text = "<div class='alert alert-danger' role='alert'>"
    text += textErr
    text += "</div>"
    message.innerHTML = text
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
