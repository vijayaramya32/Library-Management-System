// var bookSearch = document.getElementById("bookSearch");


//!member dropdown

// var memberSearch=document.getElementById("memberSearch");
// var listOfMembers=document.getElementById("members");


// let form = document.getElementById("issueForm");

//! books dropdown



//!

// function displayBooks(books){

//     listOfBooks.innerHTML = "";

//     books.forEach(book => {

//                 let option = document.createElement('option');

//                 option.value = book.bookID;
//                 option.textContent=book.bookTitle;

//                 listOfBooks.append(option);
            
//         });


// }

var listOfBooks=document.getElementById("books");
var btn = document.getElementById("btn-btn");
var inputValue = document.getElementById("inputValue");
var issueBookBody = document.getElementById("issueBookBody");

async function loadIssueBooks(){
try{
    let response = await fetch("http://localhost:8080/LibraryManagement/viewAllBooks");
    let books = await response.json();
    sessionStorage.setItem("Issuedbooks",JSON.stringify(books));   
    }catch(error) {

       listOfBooks.innerHTML = `<td colspan='7' style='text-align:center';>${error}</td>`
    };

}

loadIssueBooks();

function formatDate(date){

    if(date == null){
        return "-"
    }

    return new Date(date).toLocaleDateString("en-GB");
}


function displayIssueBooks(issueBooks){

    issueBookBody.innerHTML="";
    issueBooks.forEach(issueBook => {
        let row = document.createElement("tr");
        row.innerHTML = `<td>${issueBook[0]}</td>
	                                <td>${capitalize(issueBook[1].memberName)}</td>
	                                <td>${capitalize(issueBook[2].bookTitle)}</td>
	                                <td>${formatDate(issueBook[3])}</td>
	                                <td>${formatDate(issueBook[4])}</td>
                                    <td>${formatDate(issueBook[5])}</td>
                                    <td>${capitalize(issueBook[6])}</td>`; 
                        

	        
        issueBookBody.append(row);
    });
}

function filterIssuedBooks(){

    let filteredIssueBooks;
    let allIssuedBooks = JSON.parse(sessionStorage.getItem("Issuedbooks"));
    filteredIssueBooks = allIssuedBooks.filter(issuedBook => issuedBook[1].memberID === Number(inputValue.value));
    displayIssueBooks(filteredIssueBooks);
    document.getElementById("booksTable").style.display = "none";
    document.getElementById("issueTable").classList.remove("hidden");

}

async function loadBooks(){
    try{

        let response = await fetch("http://localhost:8080/LibraryManagement/viewBooks");
        let books = await response.json();
        sessionStorage.setItem("books",JSON.stringify(books));
        displayBooks(JSON.parse(sessionStorage.getItem("books")));
        // allBooks=books;
        
        
    }catch(error) {

       bookBody.innerHTML = `<tr>
                <td colspan="7" style="text-align: center;">
                    Failed to load Books ${error.message}
                </td>
            </tr>`;
    };

}



function capitalize(data){

    return data.trim()
        .split(/\s+/).map(word=>word[0].toUpperCase() + word.slice(1).toLowerCase()).join(" ");

}

function displayBooks(books){

        listOfBooks.innerHTML = "";
        books.forEach(book =>{
        let row = document.createElement("tr");
        row.innerHTML = `<td><input type="checkbox" name="issueBook" value="${book.bookID}"</td>
                                <td>${book.bookID}</td>
                                <td>${book.isbn}</td>
                                <td>${capitalize(book.bookTitle)}</td>
                                <td>${capitalize(book.author)}</td>
                                <td>${capitalize(book.category)}</td>
                                <td>${book.price}</td>
                                <td>${book.availableQuantity}</td>
                                <td>${capitalize(book.status)}</td>
                        `;
            listOfBooks.append(row);
        });
}

document.addEventListener('DOMContentLoaded',loadBooks);


// document.getElementById("bookId").addEventListener('input',function(){

//     let allBooks =JSON.parse(sessionStorage.getItem("books"));
//     let searchValue = this.value.toLowerCase();
//     let filteredBooks = allBooks.filter(book => book.bookTitle.toLowerCase().includes(searchValue) );

//     displayBooks(filteredBooks);
// })

// 


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

// document.getElementById("memberId").addEventListener("input",function(){

//     let allMembers = JSON.parse(sessionStorage.getItem("members"));
//     let searchValue = this.value.toLowerCase();
//     let filteredMembers = allMembers.filter(member=>member.memberID===Number(memberID.value));
//     displayMembers(filteredMembers);
// });

// loadMembers();

// form.addEventListener("submit", issueBook);








function issueBook() {

        //! let formData = new URLSearchParams(new FormData(form));

         const checkboxes = document.querySelectorAll(
        'input[name="issueBook"]:checked'
    );


    const bookIDs = Array.from(checkboxes).map(
        checkbox => Number(checkbox.value)
    );

    const memberID = Number(inputValue.value);

    if (bookIDs.length === 0) {
        alert("Please select at least one book");
        return;
    }
    


        fetch("http://localhost:8080/LibraryManagement/issueBook",{

            method:'post',
            headers: {
        
            "Content-Type": "application/json"
            },
        body: JSON.stringify({
            memberID: memberID,
            bookIDs:bookIDs})
    })
    .then(async response=>
            {

             await loadIssueBooks();
            if (!response.ok) {
                    let message = await response.text();
                    throw new Error(message);
            }
                filterIssuedBooks();
                return response.text();

        })
        .then(message => {
            alert(message);
        })
        .catch(error => {
                alert("Error: " + error.message);
        });

}




