const form = document.getElementById("form")

async function getValues(event) {
    event.preventDefault();

    // client
    var firstName = document.getElementById("clientFirstName").value
    var lastName = document.getElementById("clientLastName").value
    var phoneNumber = document.getElementById("clientPhone").value
    var birthDate = document.getElementById("clientBirthdate").value
    console.log("client", lastName, firstName, phoneNumber, birthDate)

    // master
    var firstNameM = document.getElementById("masterFirstName").value
    var lastNameM = document.getElementById("masterLastName").value
    console.log("master", lastNameM, firstNameM)

    // services
    var selectedCheckBoxes = document.querySelectorAll('input.custom-control-input:checked');
    var checkedBoxes = Array.from(selectedCheckBoxes).map(cb => cb.value);
    console.log("**", checkedBoxes)
 
    var servs = Array()
    var price = 0
    for(let i = 0; i < checkedBoxes.length; i++) {
        let n = checkedBoxes[i].toLowerCase()
        console.log("sname", n)
        let s = await (await fetch(`http://localhost:8082/services/getone/${n}`)).json()
        // price 
        price += s.basePrice
        servs.push(s)
    }
    console.log("servs list", servs)

    // day and time
    var day = document.getElementById("day").value
    var timeBegin = document.getElementById("time").value + ":00"

    var time = timeBegin.split(":")
    var timeEnd = createTimeEnd(time, servs)
    console.log("time", day, timeBegin, timeEnd)

    const response2 = await fetch(`http://localhost:8082/masters/getfl/${firstNameM}/${lastNameM}`)
    const master = await response2.json()

    if(master.length == 0) {
        catchErrors("Такого мастера нет в базе данных")
    } else {
        var masterId = getId(master)
        const response = await fetch(`http://localhost:8082/clients/getfl/${firstName}/${lastName}`)
        const client = await response.json()
        
        if(client.length == 0) {
            var cl = JSON.stringify({"firstName": firstName, "lastName": lastName, "birthDate": birthDate, "phoneNumber": phoneNumber})
            await addClient(cl)
            const response3 = await fetch(`http://localhost:8082/clients/getfl/${firstName}/${lastName}`)
            const client1 = await response3.json()
            let clientId = getId(client1)

            const data = JSON.stringify({
                "client": {"id": clientId}, 
                "master": {"id": masterId},
                "services": servs,
                "day": day,
                "timeBegin": timeBegin,
                "timeEnd": timeEnd
                // date of creation??
            })
            console.log("*******1", data)

            fetch(`http://localhost:8082/orders/add`, {
                method: "POST",
                body: data,
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                } 
            })

        } else {
            var clientId = getId(client)

            const data = JSON.stringify({
                "client": {"id": clientId}, 
                "master": {"id": masterId},
                "services": servs,
                "day": day,
                "timeBegin": timeBegin,
                "timeEnd": timeEnd
                // date of creation??
            })
            console.log("*******2", data)

            fetch(`http://localhost:8082/orders/add`, {
                method: "POST",
                body: data,
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                } 
            })
        }
    }
    // const data = JSON.stringify({
    //     "client": {"id": clientId}, 
    //     "master": {"id": masterId},
    //     "services": servs,
    //     "day": day,
    //     "timeBegin": timeBegin,
    //     "timeEnd": timeEnd
    //     // date of creation??
    // })
    // console.log("*******", data)

    // fetch(`http://localhost:8082/orders/add`, {
    //     method: "POST",
    //     body: data,
    //     headers: {
    //         "Content-type": "application/json; charset=UTF-8"
    //     } 
    // })
    
    
    // for(let i = 0; i < checkedBoxes.length; i++) {
    //     let n = checkedBoxes[i]
    //     await fetch(`http:localhost:8082/services/getone/${n}`)
    //     .then(response => response.json())
    //     .then(servise => servs.push(servise))
    // }

    // await(fetch("http://localhost:8082/orders/add", {
    //     method: "POST",
    //     body: JSON.stringify({
    //         // "client": {"firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber}, 
    //         "client": clientList[0].id
    //         "master": {"firstName": firstNameM, "lastName": lastNameM},
    //         "services": servs,
    //         "day": day,
    //         "timeBegin": timeBegin,
    //         "TimeEnd": timeEnd
    //     }),
    //     headers: {
    //         "Content-type": "application/json; charset=UTF-8"
    //     } 
    // }))
    // }
}

form.addEventListener("submit", getValues)

function addClient(data) {
    fetch(`http://localhost:8082/clients/add`, {
        method: "POST",
        body: data,
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function getCheckedCheckBoxes() {
    var selectedCheckBoxes = document.querySelectorAll('input.checkbox:checked');
    var checkedValues = Array.from(selectedCheckBoxes).map(cb => cb.value);
    console.log(checkedValues);
    return checkedValues; // для использования в нужном месте
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

async function createServiselist(listCB) {
    var servs = Array()

    for(let i = 0; i < listCB.length; i++) {
        let n = listCB[i]
        let s = await (await fetch(`http://localhost:8082/services/getone/${n}`)).json()
        // let clientList = await (await fetch(`http://localhost:8082/clients/getfl/${firstName}/${lastName}`)).json()
        servs.push(s)
    }
    return servs
}

async function getService(sn) {
    let s = await (await fetch(`http://localhost:8082/services/getone/${sn}`)).json()
    console.log("S", s)
    return s
}

function getId(data) {
    for(let i = 0; i < data.length; i++) {
        var id = data[i].id
    }
    return id
}

async function drawAllServicesList() {
    const resp =  await fetch(`http://localhost:8082/services/get`)
    const services = await resp.json()
    console.log("***", services)
    
    var list = ""
    for(let i = 0; i < services.length; i++) {
        var row = "<div class='custom-control custom-checkbox'>"
        row += "<input type='checkbox' class='custom-control-input' name='service' value=" + services[i].name + ">"
        row += "<label class = 'custom-control-label'>" + services[i].name + "</label></div>"
        list += row
    }
    const div = document.getElementById("list")
    div.innerHTML = list
}



// @PostMapping("/add")
//     public void saveOrder(@RequestBody Order order) {      
//         try{
//             Set<Servise> services = order.getServices();
//             if(!services.isEmpty()) {
//                 order.setServices(services);
//                 for(Servise servise: services) {
//                     servise.getOrders().add(order);
//                 }
//                 orderService.saveOrder(order);
//             } else {
//                 orderService.saveOrder(order);
//             }
//         } catch (NoSuchElementException exception) {
            
//         }
//     }