const form = document.getElementById("form")

// async function isClientExists(client) {
//     console.log("client", client)
//     var nclient = JSON.parse(client)

//     const firstName = nclient.firstName
//     const lastName = nclient.lastName
//     console.log(firstName, lastName) 

//     let clientList = await (await fetch(`http://localhost:8082/clients/getfl/${firstName}/${lastName}`)).json()

//     console.log("list " + clientList.length, clientList)

//     if(clientList.length == 0) {
//         return clientList
//     }
//     return clientList
// } 

async function getValues(event) {
    event.preventDefault(); // ??

    // client
    var firstName = document.getElementById("clientFirstName").value
    var lastName = document.getElementById("clientLastName").value
    var phoneNumber = document.getElementById("clientPhone").value

    // master
    var firstNameM = document.getElementById("masterFirstName").value
    var lastNameM = document.getElementById("masterLastName").value

    // services (checkboxes)
    // date of creation добавить при посте в бд

    var client = JSON.stringify({"firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber})
    // var master = JSON.stringify({"firstName": firstNameM, "lastName": lastNameM})

    var data = JSON.stringify({
        "client": {"firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber}, 
        "master": {"firstName": firstNameM, "lastName": lastNameM}
    })

    console.log("client", client)
    // var nclient = JSON.parse(client)

    // const clientFirstName = nclient.firstName
    // const clientLastName = nclient.lastName
    // console.log(firstName, lastName)

    let clientList = await (await fetch(`http://localhost:8082/clients/getfl/${firstName}/${lastName}`)).json()

    console.log("list " + clientList.length, clientList)

    if(clientList.length == 0) {
        console.log("bad") // добавить в бд

    }
    else {
        console.log(data) // просто создать
        fetch(`http://localhost:8082/orders/add`, {
            method: "POST",
            body: JSON.stringify({
                "client": {"firstName": firstName, "lastName": lastName, "phoneNumber": phoneNumber}, 
                "master": {"firstName": firstNameM, "lastName": lastNameM}
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            } 
        })
        // console.log(body)
    }

    // fetch('http://localhost:8082/clients/add', {
    //     method: "POST",
    //     body: data,
    //     headers: {
    //         "Content-type": "application/json; charset=UTF-8"
    //     }
    // })
}

form.addEventListener("submit", getValues)
