document.getElementById("loginForm").addEventListener("submit", function(event) {

    event.preventDefault();

    let userName = document.getElementById("userName").value;
    let password = document.getElementById("password").value;

    fetch("http://localhost:8080/LibraryManagement/librarianLogin", {
        method: "POST",

        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },

        body: "userName=" + encodeURIComponent(userName)
            + "&password=" + encodeURIComponent(password),

        credentials: "include"
    })
    .then(response => response.json())
    .then(data => {

        if (data.success) {

            window.location.href =
            "/librarian/Home/Home.html";

        } else {

            document.getElementById("errorMessage").textContent =
                data.message;
        }
    })
    .catch(error => {

        console.error(error);

        document.getElementById("errorMessage").textContent =
            "Unable to connect to server.";
    });

});