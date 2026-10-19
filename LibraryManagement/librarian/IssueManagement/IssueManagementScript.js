let allIssuedBooks = [];
let issueBookBody = document.getElementById("issueBookBody");
let searchBy = document.getElementById("searchBy");
let inputValue = document.getElementById("inputValue");
let searchButton = document.getElementById("searchButton");

function loadIssueBooks(){

    fetch("http://localhost:8080/LibraryManagement/viewAllBooks")
    .then(response=>response.json())
    .then(issueBooks=>{
        displayIssueBooks(issueBooks);
        allIssuedBooks = issueBooks;
        inputValue.addEventListener("keydown",function(event){
            if(event.key == "Enter"){
                event.preventDefault();
                filterIssuedBooks();
            }
        });
        searchButton.addEventListener("click",filterIssuedBooks);
    }).catch(error => {
                issueBookBody.innerHTML = `<tr>
                                                <td colspan=7 style="text-align:center";>fail to load ${error}</td>
                                            </tr>`;
    });
}

function formatDate(date){

    if(date==null){
        return "-";
    }

    return new Date(date).toLocaleDateString("en-GB");
}

function capitalize(data){

    return data.split(" ").map(word=>word[0].toUpperCase() + word.slice(1).toLowerCase()).join(" ");

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

    try{

        let option = searchBy.value;

        if(option === "memberID"){

            filteredIssueBooks = allIssuedBooks.filter(issuedBook => issuedBook[1].memberID === Number(inputValue.value));

        }

        else if(option === "returnDate"){

            

            filteredIssueBooks = allIssuedBooks.filter(issuedBook => formatDate(issuedBook[5]) === inputValue.value);

        }

        else{

           filteredIssueBooks = allIssuedBooks;
        }



        displayIssueBooks(filteredIssueBooks);

        if(filteredIssueBooks.length == 0){
            throw new Error("Issued Book not found");
        }

    }catch(error){

        issueBookBody.innerHTML = `<tr>
                                        <td colspan=7 style="text-align:center"; ${error}</td>
                                    </tr>`;
    }

}

document.getElementById("logoutButton").addEventListener("click", function () {

     window.location.href = "/librarian/LibraryMangementLogin/LibraryManagementLoginPage.html";
});

document.addEventListener("DOMContentLoaded",loadIssueBooks)