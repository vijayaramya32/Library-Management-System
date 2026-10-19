let allMembers = [];
let memberBody = document.getElementById('memberBody');
let searchButton = document.getElementById('searchButton');
let inputValue = document.getElementById("value");
let searchBy = document.getElementById("searchBy");

async function loadMembers(){
    try{
        let response = await fetch("http://localhost:8080/LibraryManagement/viewMembers");
        let members  = await response.json();
        sessionStorage.setItem("members", JSON.stringify(members));
        displayMembers(JSON.parse(sessionStorage.getItem("members")));
        inputValue.addEventListener('input',filterMembers);
        searchButton.addEventListener('click',filterMembers);
    }catch(error){
            memberBody.innerHTML = `<tr>
                                        <td colspan='7' style="text-align:center";>Failed to load Members ${error}</td>
                                    </tr>`;
    }
}

function capitalize(data){

    return data.split(" ").map(word=>word[0].toUpperCase() + word.slice(1).toLowerCase()).join(" ");

}

function displayMembers(members){

    memberBody.innerHTML = "";
    members.forEach(member => {
        let row = document.createElement("tr");
        row.innerHTML = `<td>${member.memberID}</td>
                        <td>${capitalize(member.memberName)}</td>
                        <td>${member.email.toLowerCase()}</td>
                        <td>${member.phone}</td>
                        <td>${capitalize(member.address)}</td>
                        <td>${new Date(member.membershipDate).toLocaleDateString("en-GB")}</td>
                        <td>${capitalize(member.status)}</td>`;

        memberBody.append(row);

        });
}

function filterMembers(){

    let filteredMembers;
    
    try{
        
        let option = searchBy.value;

        let allMembers = JSON.parse(sessionStorage.getItem("members"));

        if(option === "memberID"){
            filteredMembers = allMembers.filter(member => member.memberID === Number(inputValue.value));
        }
        
        else if(option === "memberName"){
            filteredMembers = allMembers.filter(member => member.memberName.toLowerCase().includes(inputValue.value.trim().toLowerCase()));
        }
        
        else if(option === "email"){
            filteredMembers = allMembers.filter(member => member.email.toLowerCase().includes(inputValue.value.trim().toLowerCase()));
        }

        else if(option === "phone"){
            filteredMembers = allMembers.filter(member => member.phone.toLowerCase().includes(inputValue.value.trim().toLowerCase()));
        }

        else{
            filteredMembers=allMembers; 
        }
        displayMembers(filteredMembers);

        if(filteredMembers.length==0){
            throw new Error("member not found")
        }

    }catch(error){

        memberBody.innerHTML = `<tr>
                                    <td colspan='7' style=">${error.message}</td>
                                </tr>`;

    }

}

document.getElementById("logoutButton").addEventListener("click", function () {

     window.location.href = "/librarian/LibraryMangementLogin/LibraryManagementLoginPage.html";
});

document.addEventListener("DOMContentLoaded",loadMembers);