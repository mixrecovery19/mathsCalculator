document.addEventListener("DOMContentLoaded", () => {

    const savedPosition = sessionStorage.getItem("scrollPosition");

    const activeSection =
        document.querySelector("[data-active-section='true']");

    const scrollAnchor = activeSection
        ? activeSection.querySelector("[data-scroll-anchor]")
        : null;

    if (scrollAnchor) {

        scrollAnchor.scrollIntoView({
            behavior: "instant",
            block: "center"
        });

    } else {

        const activeSection = document.querySelector("[data-active-section='true']");

        if (activeSection) {

            activeSection.scrollIntoView({
                behavior: "instant",
                block: "start"
            });

        } else if (savedPosition !== null) {

            window.scrollTo({
                top: parseInt(savedPosition),
                behavior: "instant"
            });

        }

    }

    requestAnimationFrame(() => {
        document.body.classList.remove("loading");
    });

    const form = document.querySelector("form");

    if (form) {
        form.addEventListener("submit", () => {
            sessionStorage.setItem("scrollPosition", window.scrollY);
            document.body.classList.add("loading");
        });
    }

});