var bookBody = document.getElementById("bookBody");
var form = document.getElementById("issueForm");
var addBookButton = document.getElementById("addBookButton");
var isEditing = false;

async function loadBooks(){
    try{

        let response = await fetch("http://localhost:8080/LibraryManagement/viewBooks");
        let books = await response.json();
        sessionStorage.setItem("books",JSON.stringify(books));
        displayBooks(JSON.parse(sessionStorage.getItem("books")));
        
    }catch(error) {

       bookBody.innerHTML =  `<tr>
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

        bookBody.innerHTML = "";

        books.forEach(book =>{
        let row = document.createElement("tr");
        row.innerHTML = `<td>${book.bookID}</td>
                                <td>${book.isbn}</td>
                                <td>${capitalize(book.bookTitle)}</td>
                                <td>${capitalize(book.author)}</td>
                                <td>${capitalize(book.category)}</td>
                                <td>${book.price}</td>
                                <td>${book.availableQuantity}</td>
                                <td>${capitalize(book.status)}</td>
                                <td><button class="editBooks">Edit</button><button class="deleteBook">delete</button></td>
                        `;
            bookBody.append(row);
        });
}


document.addEventListener('DOMContentLoaded',loadBooks);

form.addEventListener("submit", addBooks);

function addBooks(event){

    event.preventDefault();

    let formData = new URLSearchParams(new FormData(form)); 

    let url= isEditing 
            ? "http://localhost:8080/LibraryManagement/updateBook"
            :"http://localhost:8080/LibraryManagement/addBook";

    fetch(url,{
        method:"post",
        body:formData

    })
    .then(async response=>{
        if(response.ok)
        {
          
            form.reset();
            console.log("successfully");

            loadBooks();
            
        }
            // window.location.reload()
        })
    .catch(error=>`${error}`);
    // loadBooks()
    
}

bookBody.addEventListener("click",function(event){

    event.preventDefault();
    let allBooks = JSON.parse(sessionStorage.getItem("books"));
    if(event.target.classList.contains("editBooks")){
        let row = event.target.closest("tr");
        let bookID = Number(row.cells[0].textContent);
        let book = allBooks.find(b => b.bookID === bookID);
        Object.keys(book).forEach(key =>{
            let input = form.elements[key];
            if(input){
                input.value = book[key];
            }


    });

    isEditing = true;
    addBookButton.textContent="updateBook"

    }

    else if(event.target.classList.contains('deleteBook')){

        let row = event.target.closest("tr");
        let bookID = Number(row.cells[0].textContent);
        fetch(`http://localhost:8080/LibraryManagement/deleteBook?bookID=${bookID}`,{
            method:"post"
        })
        .then(async response => {

            if(!response.ok){
                let message = await response.text();
                throw new Error(message);
            }
            loadBooks();
            return response.text();
            
        })
        .then(message=>{
            alert(message)
        })
        .catch(error=> alert("Error: " + error.message));
    }


});


    






    