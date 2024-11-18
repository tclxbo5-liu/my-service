document.addEventListener("DOMContentLoaded", function() {
    var contactForm = document.getElementById("contact-form");

    contactForm.addEventListener("submit", function(event) {
        event.preventDefault();

        var name = document.getElementById("name").value;
        var email = document.getElementById("email").value;
        var message = document.getElementById("message").value;

        // Perform form validation or AJAX request
        alert("Thank you, " + name + ". Your message has been sent.");
    });
});