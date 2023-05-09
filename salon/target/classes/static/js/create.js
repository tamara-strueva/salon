const form = document.getElementById("form")

function getValues(event) {
    event.preventDefault(); // ??

    var firstName = document.getElementsByName("firstName")[0].value
    var lastName = document.getElementsByName("lastName")[0].value
    var data = JSON.stringify({"firstName": firstName, "lastName": lastName})

    fetch('http://localhost:8082/clients/add', {
        method: "POST",
        body: data,
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
}

form.addEventListener("submit", getValues)
