
var memberBody = document.getElementById("memberBody");
var form = document.getElementById("issueForm");
var addMemberButton = document.getElementById("addMemberButton")
var isEditing = false;

async function loadMembers(){
    try{

        let response = await fetch("http://localhost:8080/LibraryManagement/viewMembers");
        let members = await response.json();
        sessionStorage.setItem("members",JSON.stringify(members));
        displayMembers(JSON.parse(sessionStorage.getItem("members")));
       
        
    }catch(error) {

       memberBody.innerHTML = `<tr>
                <td colspan="7" style="text-align: center;">
                    Failed to load Books ${error.message}
                </td>
            </tr>`;
    };
}

function capitalize(data){

    return data.split(" ").map(word=>word[0].toUpperCase() + word.slice(1).toLowerCase()).join(" ");

}


function displayMembers(members){

        memberBody.innerHTML = "";

        members.forEach(member =>{
        let row = document.createElement("tr");
        row.innerHTML = `<td>${member.memberID}</td>
                                <td>${capitalize(member.memberName)}</td>
                                <td>${member.email.toLowerCase()}</td>
                                <td>${member.phone.toLowerCase()}</td>
                                <td>${capitalize(member.address)}</td>
                                <td>${new Date(member.membershipDate).toLocaleDateString("en-GB")}</td>
                                <td>${capitalize(member.status)}</td>
                                <td><button class="EditMembers">Edit</button><button class="deleteMember">delete</button></td>
                                
                        `;
            memberBody.append(row);
        });
}


document.addEventListener('DOMContentLoaded',loadMembers);

form.addEventListener("submit", addMembers);

function addMembers(event){

    event.preventDefault();



    let formData = new URLSearchParams(new FormData(form)); 

    let url = isEditing
        ? "http://localhost:8080/LibraryManagement/updateMember"
        : "http://localhost:8080/LibraryManagement/addMember";

    fetch(url,{
        method:"post",
        body:formData

    })
    .then(async response=>{
        if(response.ok)
        {
          
            form.reset();
            console.log("successfully");

            loadMembers();
            
        }

        
         
        

            // window.location.reload()
            })
    .catch(error=>`${error}`);
    // loadBooks()
    
}


memberBody.addEventListener("click", function(event) {

    let allMembers = JSON.parse(sessionStorage.getItem("members"));

    if (event.target.classList.contains("EditMembers")) {

        let row = event.target.closest("tr");
        let memberID = Number(row.cells[0].textContent);

        let member = allMembers.find(m => m.memberID === memberID);

        Object.keys(member).forEach(key => {

            let input = form.elements[key];

            if (input) {
                input.value = member[key];
            }

        });

        isEditing = true;
        addBookButton.textContent="updateMember"

    }

    else if(event.target.classList.contains("deleteMember")){

        let row = event.target.closest("tr");
        let memberID = Number(row.cells[0].textContent);
        fetch(`http://localhost:8080/LibraryManagement/deleteMember?memberID=${memberID}`,{
            method:"post"
        })
        .then(async response =>{

            if(!response.ok){

                let message = await response.text();
                throw new Error(message);
                
            }
            loadMembers();
            return response.text();
        })
        .then(message => alert(message))
        .catch(error => alert(error.message));
    }

});


    