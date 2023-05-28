function showOrdersList() {
    fetch("http://localhost:8082/orders/get")
    .then(response => response.json())
    .then(orders => createTableOrders(ordersTable, orders));
}
const ordersTable = document.getElementById('ordersTableBody')

// by master
function showOrdersByMaster() {
    const master  = document.getElementById("master").value

    if(master == "") {
        catchErrors("Поле ввода мастера пустое!")
    } else {
        const mastSp = master.toLowerCase()
        fetch(`http://localhost:8082/orders/getms/${mastSp}`)
        .then(response => response.json())
        .then(orders => {
            if(orders.length == 0) {
                const mastName = capitalizeFirstLetter(master)
                fetch(`http://localhost:8082/orders/getmn/${mastName}`)
                .then(response => response.json())
                // .then(res => console.log(res))
                .then(orderss => {
                    if(orderss.length == 0) {
                        catchErrors("Информация не найдена!")
                    } else {
                        createTableOrders(ordersTable, orderss)
                    }
                })
            } else {
                createTableOrders(ordersTable, orders)
            }
        })
    }
}

// by client
function showOrdersByClient() {
    const client = document.getElementById("client").value

    if(client == "") {
        catchErrors("Поле ввода клиента пустое!")
    } else {
        const clName = capitalizeFirstLetter(client)
        fetch(`http://localhost:8082/orders/getcn/${clName}`)
        .then(response => response.json())
        .then(orders => {
            if(orders.length == 0) {
                const clLastName = capitalizeFirstLetter(client)
                fetch(`http://localhost:8082/orders/getcl/${clLastName}`)
                .then(response => response.json())
                .then(orderss => {
                    if(orderss.length == 0) {
                        catchErrors("Информация не найдена!")
                    } else {
                        createTableOrders(ordersTable, orderss)
                    }
                })
            } else {
                createTableOrders(ordersTable, orders)
            }
        })
    }
}

// by date
function showOrdersByDay() {
    const date = document.getElementById("date").value

    if(date == "") {
        catchErrors("Поле ввода даты пустое!")
    } else { // check format
        fetch(`http://localhost:8082/orders/getd/${date}`)
        .then(response => response.json())
        .then(orders => {
            if(orders.length == 0) {
                catchErrors("Информация не найдена!")
            } else {
                createTableOrders(ordersTable, orders)
            }
        })
    }
}

function showOrdersByService() {
    const date = document.getElementById("service").value

    if(date == "") {
        catchErrors("Поле ввода услуги пустое!")
    } else {
        let sn = date.toLowerCase()
        fetch(`http://localhost:8082/orders/getsn/${sn}`)
        .then(response => response.json())
        .then(orders => {
            if(orders.length == 0) {
                catchErrors("Информация не найдена!")
            } else {
                console.log("&",orders)
                createTableOrders(ordersTable, orders)
            }
        })
    }
}


function createTableOrders(table, data) {
    console.log(data)

    table.innerHTML = "" // clear table
    const form = document.getElementById("edit")
    form.innerHTML = "" // clear for when it is not needed
    const message = document.getElementById("message")
    message.innerHTML = "" // clear message div when it is not needed

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
            li.innerHTML = data[i].services[j].name
            ol.appendChild(li)
        }
        td2.appendChild(ol)
        tr.appendChild(td2)

        const td4 = document.createElement("td")
        td4.innerHTML = data[i].day
        tr.appendChild(td4)

        const td6 = document.createElement("td")
        if(data[i].timeBegin != null) {
            td6.innerHTML = data[i].timeBegin.slice(0, 5)
        }else {
            td6.innerHTML = data[i].timeBegin
        }
        tr.appendChild(td6)

        const td7 = document.createElement("td")
        if(data[i].timeEnd != null) {
            td7.innerHTML = data[i].timeEnd.slice(0, 5)
        } else {
            td7.innerHTML = data[i].timeEnd
        }
        tr.appendChild(td7)

        const td5 = document.createElement("td")
        var button = "<button type='button' class='btn btn-warning' onclick='editOrder(" + data[i].id + ")'>Редактировать</button>"
        button += "<button type='button' class='btn btn-danger' onclick='deleteOrder(" + data[i].id + ")'>Удалить</button>"
        td5.innerHTML = button
        tr.appendChild(td5)
    table.appendChild(tr)
    }
    // clear inputs after any input
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
    const table = document.getElementById("ordersTableBody")
    table.innerHTML = ""

    fetch(`http://localhost:8082/orders/get/${id}`)
    .then(response => response.json())
    .then(order => drawEditForm(order))
}

function drawEditForm(order) {
    var servs = Array.from(order.services).map(cb => cb.name)
    const div = document.getElementById("edit")

    var rows = "<h5>Информация о клиенте</h5>"
    rows += "<input type='text' class='form-control' id='clientFirstName' placeholder='Имя клиента'>"
    rows += "<input type='text' class='form-control' id='clientLastName' placeholder='Фамилия клиента'>"
    rows += "<input type='date' class='form-control' id='clientBirthDate' placeholder='Дата рождения'>"
    rows += "<input type='text' class='form-control' id='clientNumber' placeholder='Номер телефона'>"
    rows += "<h5>Информация о мастере</h5>"
    rows += "<input type='text' class='form-control' id='masterFirstName' placeholder='Имя мастера'>"
    rows += "<input type='text' class='form-control' id='masterLastName' placeholder='Фамилия мастера'>"
    rows += "<h5>Информация об услугах</h5><div id='list'>"
    drawAllServicesList(servs)
    rows += "</div>"
    rows += "<input type='date' class='form-control' id='day' placeholder='Date'>"
    rows += "<input type='time' class='form-control' id='time' placeholder='timebegin'>"

    div.innerHTML = rows

    const mfn = document.getElementById("masterFirstName")
    mfn.value = order.master.firstName
    const mln = document.getElementById("masterLastName")
    mln.value = order.master.lastName

    const cfn = document.getElementById("clientFirstName")
    cfn.value = order.client.firstName
    const cln = document.getElementById("clientLastName")
    cln.value = order.client.lastName
    const cbd = document.getElementById("clientBirthDate")
    cbd.value = order.client.birthDate
    const cpn = document.getElementById("clientNumber")
    cpn.value = order.client.phoneNumber

    const d = document.getElementById("day")
    d.value = order.day
    const tb = document.getElementById("time")
    tb.value = order.timeBegin


    const button = document.createElement("button", {class: "btn btn-outline-success", onclick: "editConfirm(" + order.id + ")"})
    button.innerText = "EDIT"
    button.setAttribute("type", "button")
    button.classList.add("btn", "btn-outline-success")
    button.onclick = function() {
        editConfirm(order.id)
    }
    
    const form = document.getElementById("edit")
    form.appendChild(button)
}

async function editConfirm(id) {
    const mfn = document.getElementById("masterFirstName").value
    const mln = document.getElementById("masterLastName").value

    const cfn = document.getElementById("clientFirstName").value
    const cln = document.getElementById("clientLastName").value
    const cbd = document.getElementById("clientBirthDate").value
    const cpn = document.getElementById("clientNumber").value

    var selectedCheckBoxes = document.querySelectorAll('input.custom-control-input:checked');
    var services = Array.from(selectedCheckBoxes).map(cb => cb.value)
    console.log("@#$%^&*(", services)
    var servs = Array()
    for(let i = 0; i < services.length; i++) {
        // let n = services[i].toLowerCase()
        let ser = await fetch(`http://localhost:8082/services/getone/${services[i]}`)
        let s = await ser.json()
        servs.push(s)
    }
    console.log("!!!", servs)

    var day = document.getElementById("day").value
    var timb = document.getElementById("time").value
    var timeE = createTimeEnd(timb, servs)

    // get master id
    // const fn = capitalizeFirstLetter(mfn)
    const response = await fetch(`http://localhost:8082/masters/getfl/${mfn}/${mln}`)
    const master = await response.json()
    console.log("master", master, mfn, mln)
    if(master.length == 0) {
        catchErrors("Такого мастера нет в базе данных!")
    } else {
        var masterId = getId(master)

        const response2 = await fetch(`http://localhost:8082/clients/getfl/${cfn}/${cln}`)
        const client = await response2.json()
        console.log("cl", client)

        if(client.length == 0) {
            var cl = JSON.stringify({"firstName": cfn, "lastName": cln, "birthDate": cbd, "phoneNumber": cpn})
            await addClient(cl)
            const response3 = await fetch(`http://localhost:8082/clients/getfl/${cfn}/${cln}`)
            const client1 = await response3.json()
            let clientId = getId(client1)
            fetch(`http://localhost:8082/orders/edit/${id}`, {
                method: "PUT",
                body: JSON.stringify({
                    "client": {"id": clientId},
                    "master": {"id": masterId},
                    "services": servs,
                    "date": day,
                    "timeBegin": timb,
                    "timeEnd": timeE
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            })
                
                
            .then(data => {
                console.log(data);
                showOrdersList();
            })
            .catch(error => console.error(error));
        } else {
            let clientId = getId(client)
            fetch(`http://localhost:8082/orders/edit/${id}`, {
                method: "PUT",
                body: JSON.stringify({
                    "client": {"id": clientId},
                    "master": {"id": masterId},
                    "services": servs,
                    "date": day,
                    "timeBegin": timb,
                    "timeEnd": timeE
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            })
            .then(data => {
                console.log(data);
                showOrdersList();
            })
            .catch(error => console.error(error));
        }
    }
}

function addClient(data) {
    fetch(`http://localhost:8082/clients/add`, {
        method: "POST",
        body: data,
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
}

async function drawAllServicesList(ss) {
    const resp =  await fetch(`http://localhost:8082/services/get`)
    const services = await resp.json()
    
    var list = ""
    for(let i = 0; i < services.length; i++) {
        if(ss.indexOf(services[i].name) != -1) {
            var row = "<div class='custom-control custom-checkbox'>"
            row += "<input type='checkbox' class='custom-control-input' value=" + services[i].name + " checked>"
            row += "<label class = 'custom-control-label'>" + services[i].name + "</label></div>"
            list += row
        } else {
            var row = "<div class='custom-control custom-checkbox'>"
            row += "<input type='checkbox' class='custom-control-input' value=" + services[i].name + ">"
            row += "<label class = 'custom-control-label'>" + services[i].name + "</label></div>"
            list += row
        }
    }
    const div = document.getElementById("list")
    div.innerHTML = list
}

function getId(data) {
    for(let i = 0; i < data.length; i++) {
        var id = data[i].id
    }
    return id
}

function createTimeEnd(time, services) {
    let h = Number(time[0])
    let m = Number(time[1])
    for(let i = 0; i < services.length; i++) {
        m += Number(services[i].duration)
    }
    if(m >= 60) {
        h += Math.floor(m/60)
        m = m%60
    }
    console.log("h", h, "m", m)
    return h + ":" + m + ":00"
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
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
}

function toLower(string) {
    return string.toLowerCase();
}