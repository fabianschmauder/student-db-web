const listcontainer = document.getElementById("listcontainer");

async function getData() {
    const response = await fetch("http://localhost:8080/students")
    const data = await response.json();
    console.log(data);
    listcontainer.textContent = data.map(student => student.name).join(', ')
    console.log("zeile nach dem fetch")
}

getData().then(() => console.log('fetch data done'))


async function addStudent() {
    const nameElement = document.getElementById("name");
    const studentToAdd = {
        name: nameElement.value
    };
    await fetch("http://localhost:8080/students",{
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(studentToAdd)
    })
    nameElement.value = "";
    await getData();
}
