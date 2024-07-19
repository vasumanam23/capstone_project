document.addEventListener('DOMContentLoaded', function() {
    // Select the banner image
    var bannerImage = document.querySelector('.banner-container img');

    // Add a click event listener to the banner image
    bannerImage.addEventListener('click', function() {
        // Redirect to another page
        window.location.href = 'https://www.example.com'; // Replace with the desired URL
    });
});
