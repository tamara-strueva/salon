function getScheduleByMaster() {
    const master = document.getElementById("master").value
    // const masterName = master
    // console.log(master)
    const masterFullName = master.split(" ")
    const masterLastName = masterFullName[0]
    const masterName = masterFullName[1]
    const nmasterLastName = capitalizeFirstLetter(masterLastName)
    const nmasterName = capitalizeFirstLetter(masterName)
    console.log(nmasterLastName, nmasterName)

    fetch(`http://localhost:8082/schedules/getm/${nmasterName}/${nmasterLastName}`)
    .then(response => response.json())
    .then(schedule => createTableMasters(scheduleTable, schedule, master))
    // .then(schedule => console.log(schedule))
}
const scheduleTable = document.getElementById("schedulesTableBody")

function getScheduleByDay() {

}

function getScheduleByTimeBegin() {

}

function createTableMasters(table, data, name) {
    console.log(data)

    table.innerHTML = ""

    for(let i = 0; i < data.length; i++) {
        const tr = document.createElement("tr", {class: "d-flex"}) // <tr>

        const th = document.createElement("th")
        th.innerHTML = data[i].id
        tr.appendChild(th)

        const td = document.createElement("td")
        td.innerHTML = name
        tr.appendChild(td)

        // настроить нормальный формат??
        const td1 = document.createElement("td")
        td1.innerHTML = data[i].day
        tr.appendChild(td1)

        const td3 = document.createElement("td")
        td3.innerHTML = data[i].timeBegin
        tr.appendChild(td3)

        const td2 = document.createElement("td")
        td2.innerHTML = data[i].timeEnd
        tr.appendChild(td2)

    table.appendChild(tr)
    }
    document.getElementById("master").value = ""
    document.getElementById("day").value = ""
    document.getElementById("timeBegin").value = ""

}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
