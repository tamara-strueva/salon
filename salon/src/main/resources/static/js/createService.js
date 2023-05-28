const form = document.getElementById("form")

async function getValues(event) {
    event.preventDefault();
    let name = document.getElementById("name").value 
    let description = document.getElementById("description").value
    let duration = document.getElementById("duration").value
    let price = document.getElementById("price").value
    let sname = name.toLowerCase()

    var data = JSON.stringify({
        "name": sname,
        "description": description,
        "duration": duration,
        "basePrice": price
    })

    console.log(data)

    fetch(`http://localhost:8082/services/add`, {
        method: "POST",
        body: data,
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        } 
    })
}

form.addEventListener("submit", getValues)

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}