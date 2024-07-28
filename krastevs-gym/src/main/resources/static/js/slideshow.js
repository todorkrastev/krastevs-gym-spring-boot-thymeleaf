let slideIndex = 1;
showSlides(slideIndex);
checkArrowsAndDots();

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    let i;
    let slides = document.getElementsByClassName("slides");
    let dots = document.getElementsByClassName("dot");
    if (n > slides.length) {slideIndex = 1}
    if (n < 1) {slideIndex = slides.length}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";
    dots[slideIndex-1].className += " active";
}

function checkArrowsAndDots() {
    let slides = document.getElementsByClassName("slides");
    let dotsContainer = document.querySelector(".dots");
    if (slides.length <= 1) {
        document.querySelector(".prev").style.display = "none";
        document.querySelector(".next").style.display = "none";
    }
    if (slides.length === 0 || slides.length === 1) {
        dotsContainer.style.display = "none";
    }
}