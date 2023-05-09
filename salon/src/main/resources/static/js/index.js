function show() {
    fetch("http://localhost:8082/clients/get")
    .then(response => response.json()) 
    .then(clients => console.log(clients))
}

function add(){
    // let formData = new FormData();
    // formData.append('title', 'BezKoder Tutorial');
    // formData.append('description', 'Tut Desc');
    // let formData = new FormData()
    var firstName = document.getElementsByName("firstName")[0].value
    var lastName = document.getElementsByName("lastName")[0].value
    var data = JSON.stringify({"firstName": firstName, "lastName": lastName})
    // console.log(lastName)
    // console.log(JSON.stringify({"firstName": firstName, "lastName": lastName}))
    fetch('http://localhost:8082/clients/add', {method: "POST",
    body: data})
    // .then(data => data.json())
    .then(data => console.log(data))
    }
    // try {
    //     const response = await fetch('http://localhost:8082/clients/add', {
    //         method: "post",
    //         body: formData
    //     });

    //     if (!response.ok) {
    //         const message = 'Error with Status Code: ' + response.status;
    //         throw new Error(message);
    //     }

    //     const data = await response.json();
    //     console.log(data);
    //     } catch (error) {
    //     console.log('Error: ' + err);
    //     }