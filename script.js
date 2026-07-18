document.addEventListener("DOMContentLoaded", () => {
    // =====================================================
    // HEADER BEIM SCROLLEN
    // =====================================================

    const header = document.querySelector("header");

    const updateHeader = () => {
        if (!header) return;

        if (window.scrollY > 40) {
            header.classList.add("scrolled");
        } else {
            header.classList.remove("scrolled");
        }
    };

    updateHeader();
    window.addEventListener("scroll", updateHeader);


    // =====================================================
    // SMOOTH SCROLLING
    // =====================================================

    const navigationLinks = document.querySelectorAll('a[href^="#"]');

    navigationLinks.forEach((link) => {
        link.addEventListener("click", (event) => {
            const targetId = link.getAttribute("href");

            if (!targetId || targetId === "#") return;

            const targetSection = document.querySelector(targetId);

            if (!targetSection) return;

            event.preventDefault();

            targetSection.scrollIntoView({
                behavior: "smooth",
                block: "start"
            });
        });
    });


    // =====================================================
    // FAQ AUF- UND ZUKLAPPEN
    // =====================================================

    const faqItems = document.querySelectorAll(".faq-item");

    faqItems.forEach((item) => {
        const question = item.querySelector(".faq-question");

        if (!question) return;

        question.addEventListener("click", () => {
            const isActive = item.classList.contains("active");

            faqItems.forEach((otherItem) => {
                otherItem.classList.remove("active");
            });

            if (!isActive) {
                item.classList.add("active");
            }
        });
    });


    // =====================================================
    // SCROLL-TO-TOP BUTTON
    // =====================================================

    let scrollTopButton = document.querySelector(".scroll-top");

    if (!scrollTopButton) {
        scrollTopButton = document.createElement("button");
        scrollTopButton.className = "scroll-top";
        scrollTopButton.type = "button";
        scrollTopButton.setAttribute("aria-label", "Nach oben scrollen");
        scrollTopButton.innerHTML = "↑";

        document.body.appendChild(scrollTopButton);
    }

    const updateScrollTopButton = () => {
        if (window.scrollY > 500) {
            scrollTopButton.classList.add("visible");
        } else {
            scrollTopButton.classList.remove("visible");
        }
    };

    updateScrollTopButton();
    window.addEventListener("scroll", updateScrollTopButton);

    scrollTopButton.addEventListener("click", () => {
        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });
    });


    // =====================================================
    // SCROLL-ANIMATIONEN
    // =====================================================

    const animatedElements = document.querySelectorAll(
        ".feature-card, " +
        ".gallery-item, " +
        ".roadmap-item, " +
        ".faq-item, " +
        ".contact-info, " +
        ".contact-form, " +
        ".download-card, " +
        ".trailer-wrapper"
    );

    animatedElements.forEach((element) => {
        element.classList.add("reveal");
    });

    const revealObserver = new IntersectionObserver(
        (entries, observer) => {
            entries.forEach((entry) => {
                if (entry.isIntersecting) {
                    entry.target.classList.add("visible");
                    observer.unobserve(entry.target);
                }
            });
        },
        {
            threshold: 0.15
        }
    );

    animatedElements.forEach((element) => {
        revealObserver.observe(element);
    });


    // =====================================================
    // MOBILE MENÜ
    // =====================================================

    const menuButton = document.querySelector(
        ".menu-toggle, .mobile-menu-button, .nav-toggle"
    );

    const navigation = document.querySelector(
        ".nav-links, .navigation-links, nav ul"
    );

    if (menuButton && navigation) {
        menuButton.addEventListener("click", () => {
            navigation.classList.toggle("mobile-open");
            menuButton.classList.toggle("active");
        });

        navigation.querySelectorAll("a").forEach((link) => {
            link.addEventListener("click", () => {
                navigation.classList.remove("mobile-open");
                menuButton.classList.remove("active");
            });
        });
    }


    // =====================================================
    // TRAILER-BUTTON
    // =====================================================

    const playButton = document.querySelector(".play-button");

    if (playButton) {
        playButton.addEventListener("click", () => {
            const trailerUrl = playButton.dataset.video;

            if (trailerUrl) {
                window.open(trailerUrl, "_blank", "noopener,noreferrer");
            } else {
                alert("Der offizielle Trailer wird bald veröffentlicht.");
            }
        });
    }


    // =====================================================
    // KONTAKTFORMULAR
    // =====================================================

    const contactForm = document.querySelector(".contact-form form");

    if (contactForm) {
        contactForm.addEventListener("submit", (event) => {
            event.preventDefault();

            const submitButton = contactForm.querySelector(
                'button[type="submit"], .contact-submit'
            );

            if (submitButton) {
                const originalText = submitButton.textContent;

                submitButton.disabled = true;
                submitButton.textContent = "Nachricht vorbereitet ✓";

                setTimeout(() => {
                    submitButton.disabled = false;
                    submitButton.textContent = originalText;
                }, 2500);
            }

            alert(
                "Das Kontaktformular ist vorbereitet. Später verbinden wir es mit Formspree oder einer E-Mail-Adresse."
            );
        });
    }


    // =====================================================
    // AKTIVES NAVIGATIONSELEMENT
    // =====================================================

    const sections = document.querySelectorAll("section[id]");
    const menuLinks = document.querySelectorAll('nav a[href^="#"]');

    const updateActiveNavigation = () => {
        let currentSectionId = "";

        sections.forEach((section) => {
            const sectionTop = section.offsetTop - 180;
            const sectionHeight = section.offsetHeight;

            if (
                window.scrollY >= sectionTop &&
                window.scrollY < sectionTop + sectionHeight
            ) {
                currentSectionId = section.id;
            }
        });

        menuLinks.forEach((link) => {
            link.classList.remove("active");

            if (link.getAttribute("href") === `#${currentSectionId}`) {
                link.classList.add("active");
            }
        });
    };

    updateActiveNavigation();
    window.addEventListener("scroll", updateActiveNavigation);
});
