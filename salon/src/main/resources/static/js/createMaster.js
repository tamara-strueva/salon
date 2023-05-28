const form = document.getElementById("form")

async function getValues(event) {
    event.preventDefault();
    
    let lastName = document.getElementById("masterLastName").value 
    let firstName = document.getElementById("masterFirstName").value
    let spesiality = document.getElementById("masterSpeciality").value

    let ln = capitalizeFirstLetter(lastName)
    let fn = capitalizeFirstLetter(firstName)
    let sp = spesiality.toLowerCase()

    var data = JSON.stringify({
        "lastName": ln,
        "firstName": fn,
        "speciality": sp
    })

    fetch(`http://localhost:8082/masters/add`,{
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
