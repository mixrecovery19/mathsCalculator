document.addEventListener("DOMContentLoaded", () => {

    // Restore scroll position

    const savedPosition =

        sessionStorage.getItem(

            "scrollPosition"

        );

    if (savedPosition !== null) {

        window.scrollTo({

            top: parseInt(savedPosition),

            behavior: "instant"

        });

    }

    // Fade page back in smoothly

    requestAnimationFrame(() => {

        document.body.classList.remove(

            "loading"

        );

    });

    // Form submit handling

    document.querySelector("form")

        .addEventListener("submit", () => {

        sessionStorage.setItem("scrollPosition", window.scrollY
        );

        document.body.classList.add(

            "loading"

        );

    });

});
