document.addEventListener("DOMContentLoaded", () => {
   
    const savedPosition = sessionStorage.getItem("scrollPosition");        

    if (savedPosition !== null) {
        window.scrollTo({
            top: parseInt(savedPosition), behavior: "instant"

        });
    } 

    requestAnimationFrame(() => {document.body.classList.remove("loading");});      
    document.querySelector("form").addEventListener("submit", () => {
        sessionStorage.setItem("scrollPosition", window.scrollY);
        document.body.classList.add("loading");       
    });
});
