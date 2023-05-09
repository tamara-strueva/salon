function showClientsByName() {
    const fname = document.getElementById("criteria").value
    console.log(fname)

    fetch(`http://localhost:8082/clients/getn/${fname}`)
    .then(response => response.json()) 
    .then(clients => createListWithIf(clients, fname));
}

function createListWithIf(data, name) {
    const mainUL = document.createElement("ol") // <ol>

    for(let i = 0; i < data.length; i++) {
        const LI = document.createElement('li'); // <li>
        if (data[i].firstName == name) {
            LI.innerHTML = data[i].firstName
        } 
    mainUL.appendChild(LI); // </li>
    }
    document.body.appendChild(mainUL); // </ol>
    console.log(data)

}
