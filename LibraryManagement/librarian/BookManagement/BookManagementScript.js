var allBooks = [];
var inputValue = document.getElementById("value");
var searchBy = document.getElementById("searchBy");
var bookBody = document.getElementById("bookBody");
var searchButton = document.getElementById("searchButton");

async function loadBooks(){
    try{

        let response = await fetch("http://localhost:8080/LibraryManagement/viewBooks");
        let books = await response.json();
        sessionStorage.setItem("books",JSON.stringify(books));
        displayBooks(JSON.parse(sessionStorage.getItem("books")));
        // allBooks=books;
        inputValue.addEventListener("input", filterBooks);
        searchButton.addEventListener("click", filterBooks);
        
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
                        `;
            bookBody.append(row);
        });
}

function filterBooks(){

    let filteredBooks;
    console.log("filterBooks called");

   


    try{

        let option = searchBy.value;
        let allBooks = JSON.parse(sessionStorage.getItem("books"));

        if (inputValue.value === "") {
        displayBooks(allBooks);
        return;
    }
        


        if(option === "isbn"){
            filteredBooks = allBooks.filter(book => book.isbn.toLowerCase().includes(inputValue.value.trim().toLowerCase()));
        }

        else if(option === "title"){
            filteredBooks = allBooks.filter(book => book.bookTitle.toLowerCase().includes(inputValue.value.trim().toLowerCase()));
        }

        else if(option === "category"){
            filteredBooks = allBooks.filter(book => book.category.toLowerCase().includes(inputValue.value.trim().toLowerCase()));
        }

        else if(option === "author"){
            filteredBooks = allBooks.filter(book => book.author.toLowerCase().includes(inputValue.value.trim().toLowerCase()));
        }

        else{
            filteredBooks = allBooks;
        }
        displayBooks(filteredBooks);

        if(filteredBooks.length == 0){
            throw new Error("Book not found")
        }
    }catch(error){
        bookBody.innerHTML =`<tr>
                                <td colspan = '7' style = "text-align:center";>${error.message}</td>
                            </tr>`;
                                            
                                            
    };

}

document.getElementById("logoutButton").addEventListener("click", function () {

     window.location.href = "/librarian/LibraryMangementLogin/LibraryManagementLoginPage.html";
});
document.addEventListener('DOMContentLoaded',loadBooks);
    