document.addEventListener('DOMContentLoaded', function() {
    // Get all sections and navigation items once
    const sections = document.querySelectorAll('section[id]');
    const navItems = document.querySelectorAll('.nav-item');

    // Smooth scrolling function with improved animation
    function smoothScroll(targetElement, duration) {
        const targetPosition = targetElement.offsetTop - 20; // Added small offset for better positioning
        const startPosition = window.pageYOffset;
        const distance = targetPosition - startPosition;
        let startTime = null;

        function animation(currentTime) {
            if (startTime === null) startTime = currentTime;
            const timeElapsed = currentTime - startTime;
            const run = ease(timeElapsed, startPosition, distance, duration);
            window.scrollTo(0, run);
            if (timeElapsed < duration) requestAnimationFrame(animation);
        }

        function ease(t, b, c, d) {
            t /= d / 2;
            if (t < 1) return c / 2 * t * t + b;
            t--;
            return -c / 2 * (t * (t - 2) - 1) + b;
        }

        requestAnimationFrame(animation);
    }

    // Handle navigation clicks
    navItems.forEach(item => {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('href').substring(1);
            const targetSection = document.getElementById(targetId);
            
            if (targetSection) {
                // Remove active class from all items and add to clicked item
                navItems.forEach(nav => nav.classList.remove('active'));
                this.classList.add('active');
                
                // Smooth scroll to target section
                smoothScroll(targetSection, 200);
            }
        });
    });

    // Get the section that is most in view
    function getCurrentSection() {
        let maxVisibleSection = null;
        let maxVisibleAmount = 0;

        sections.forEach(section => {
            const rect = section.getBoundingClientRect();
            const viewportHeight = window.innerHeight;
            
            // Calculate how much of the section is visible
            const visibleHeight = Math.min(rect.bottom, viewportHeight) - Math.max(rect.top, 0);
            const visibleAmount = Math.max(0, visibleHeight) / viewportHeight;

            if (visibleAmount > maxVisibleAmount) {
                maxVisibleAmount = visibleAmount;
                maxVisibleSection = section;
            }
        });

        return maxVisibleSection;
    }

    // Update active navigation item based on scroll position
    function updateActiveNavItem() {
        const currentSection = getCurrentSection();
        
        if (currentSection) {
            const currentSectionId = currentSection.getAttribute('id');
            navItems.forEach(item => {
                const itemHref = item.getAttribute('href').substring(1);
                if (itemHref === currentSectionId) {
                    item.classList.add('active');
                } else {
                    item.classList.remove('active');
                }
            });
        }
    }

    // Throttle function to limit scroll event firing
    function throttle(func, limit) {
        let inThrottle;
        return function() {
            const args = arguments;
            const context = this;
            if (!inThrottle) {
                func.apply(context, args);
                inThrottle = true;
                setTimeout(() => inThrottle = false, limit);
            }
        }
    }

    // Add scroll event listener with throttling
    window.addEventListener('scroll', throttle(updateActiveNavItem, 100));

    // Initial call to set active nav item
    updateActiveNavItem();

    // Intersection Observer for better performance
    const observerOptions = {
        root: null,
        rootMargin: '-20% 0px -20% 0px',
        threshold: [0.25, 0.5, 0.75]
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting && entry.intersectionRatio > 0.5) {
                const sectionId = entry.target.getAttribute('id');
                navItems.forEach(item => {
                    const itemHref = item.getAttribute('href').substring(1);
                    if (itemHref === sectionId) {
                        item.classList.add('active');
                    } else {
                        item.classList.remove('active');
                    }
                });
            }
        });
    }, observerOptions);

    // Observe all sections
    sections.forEach(section => observer.observe(section));

    // Log sections for debugging
    console.log('Available sections:', Array.from(sections).map(s => s.id));
});