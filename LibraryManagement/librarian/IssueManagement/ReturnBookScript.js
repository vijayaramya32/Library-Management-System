//! for dropdown

// var bookSearch = document.getElementById("bookSearch");

// var memberSearch=document.getElementById("memberSearch");
// var listOfMembers=document.getElementById("members");


//let form = document.getElementById("issueForm");

//! members dropdown

// async function loadMembers(){
//     try{

//         let response = await fetch("http://localhost:8080/LibraryManagement/viewMembers");
//         let members = await response.json();
//         sessionStorage.setItem("members",JSON.stringify(members));
//         displayMembers(JSON.parse(sessionStorage.getItem("members")));

//     }catch(error){

//     listOfMembers.innerHTML=`<option>${error}</option>`;

//     }

// }

// function displayMembers(members){

//     members.forEach(member=>{

//         let option = document.createElement("option");
//         option.value=member.memberID;
//         option.textContent=member.memberName;
//         listOfMembers.append(option);
//     });
// }

//  document.getElementById("memberId").addEventListener("input",function(){

//     let allMembers = JSON.parse(sessionStorage.getItem("members"));
//      let searchValue = this.value.toLowerCase();
//     let filteredMembers = allMembers.filter(member=>member.memberName.toLowerCase().includes(searchValue));
//     displayMembers(filteredMembers);
//  });

//loadMembers();



//!IssueBooks

var listOfIssueBooks = document.getElementById("issueBooks");
var inputValue = document.getElementById("inputValue");
// let memberID = document.getElementById("memberId");
var searchButton = document.getElementById("searchButton");
var issueTable = document.getElementById("issueTable");
var selectAll = document.getElementById("selectAll");


async function loadIssueBooks(){
try{
    let response = await fetch("http://localhost:8080/LibraryManagement/viewAllBooks");
    let books = await response.json();
    console.log(books);
    sessionStorage.setItem("books",JSON.stringify(books));
    displayIssueBooks(JSON.parse(sessionStorage.getItem("books")));
    searchButton.addEventListener("click",function(){
        issueTable.classList.remove("hidden");
        displayIssueBooks(books);
    });
    inputValue.addEventListener("keydown",function(event){
            if(event.key == "Enter"){
                event.preventDefault();
                issueTable.classList.remove("hidden");
                displayIssueBooks(books);
            }
        });

        selectAll.addEventListener("change",function(){

            let checkboxes = document.querySelectorAll("input[name='issueID']")

            checkboxes.forEach(checkbox => checkbox.checked = selectAll.checked)
        })


        // allBooks=books;
        
    }catch(error) {

       listOfIssueBooks.innerHTML = `<td colspan="7" style="text-align:center";>${error}</td>`
    };

}

loadIssueBooks();

function formatDate(date){

    if(date == null){

        return "-"
    }

    return new Date(date).toLocaleDateString("en-GB");
}

function capitalize(data){

    return data.split(" ").map(word=>word[0].toUpperCase() + word.slice(1).toLowerCase()).join(" ");

}

function displayIssueBooks(books){

    listOfIssueBooks.innerHTML = "";

    let filteredBooks = books.filter(
        issueBook =>issueBook[1].memberID === Number(inputValue.value) && issueBook[6].toLowerCase() === "issued"
    );

    if (filteredBooks.length === 0) {

        listOfIssueBooks.innerHTML = `
            <tr>
                <td colspan="8" style="text-align:center;">
                    No issued books found for this Member ID
                </td>
            </tr>
        `;

        return;
    }


    filteredBooks.forEach(issueBook => {

            let row = document.createElement('tr');
            row.innerHTML = `<td><input type="checkbox" name="issueID" value="${issueBook[0]}"/></td>
                                    <td>${issueBook[0]}</td>
                                    <td>${capitalize(issueBook[1].memberName)}</td>
                                    <td>${capitalize(issueBook[2].bookTitle)}</td>
                                    <td>${formatDate(issueBook[3])}</td>
                                    <td>${formatDate(issueBook[4])}</td>
                                    <td>${formatDate(issueBook[5])}</td>
                                    <td>${capitalize(issueBook[6])}</td>`;

            listOfIssueBooks.append(row);
            


            
            //! Dropdown

            //  let option = document.createElement('option');
            //  option.value=issueBook[0];
            // option.textContent=issueBook[2].bookTitle.toLowerCase();
            // listOfIssueBooks.append(option);
        });

}

//! Dropdown

// memberID.addEventListener("blur", function () {

//     let allIssueBooks = JSON.parse((sessionStorage.getItem("books")));

    
//     let filteredMembers = allIssueBooks.filter(issueBook => issueBook[1].memberID === Number(memberID.value) && issueBook[6].toLowerCase() === "issued");
//     displayIssueBooks(filteredMembers);
// });




function returnBooks(){

    //! let formData = new URLSearchParams(new FormData(form));

     const checkboxes = document.querySelectorAll(
        'input[name="issueID"]:checked'
    );


    const issueIDs = Array.from(checkboxes).map(
        checkbox => Number(checkbox.value)
    );

    const memberID = Number(inputValue.value);

    if (issueIDs.length === 0) {
        alert("Please select at least one book");
        return;
    }


     fetch("http://localhost:8080/LibraryManagement/returnBook",{
        method:'post',
     headers: {
        
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        memberID: memberID,
        issueIDs:issueIDs})
    })
        .then(async response=>
                    {
                       if (!response.ok) {
                            let message = await response.text();
                            throw new Error(message);
                        }
                        window.location.href="/librarian/IssueManagement/IssueManagement.html";
                        loadIssueBooks();
                        return response.text();

                })
        .then(message => {
            alert(message);
        })
        .catch(error => {
                alert("Error: " + error.message);
        });

}



 










