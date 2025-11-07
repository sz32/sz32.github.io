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

    // Calculate section position relative to viewport
    function getSectionPosition(section) {
        const rect = section.getBoundingClientRect();
        const viewportHeight = window.innerHeight;
        const visibleHeight = Math.min(rect.bottom, viewportHeight) - Math.max(rect.top, 0);
        const visiblePercentage = Math.max(0, visibleHeight) / viewportHeight;
        const centerPosition = rect.top + (rect.height / 2);
        const distanceFromCenter = Math.abs(centerPosition - (viewportHeight / 2));
        
        return {
            visiblePercentage,
            distanceFromCenter
        };
    }

    // Get the most visible section with improved accuracy
    function getMostVisibleSection() {
        let maxVisibleSection = null;
        let maxVisibility = 0;
        let minDistance = Infinity;

        sections.forEach(section => {
            const { visiblePercentage, distanceFromCenter } = getSectionPosition(section);
            
            // Prioritize sections that are both visible and close to the center
            const visibility = visiblePercentage * (1 / (1 + distanceFromCenter * 0.001));
            
            if (visibility > maxVisibility) {
                maxVisibility = visibility;
                maxVisibleSection = section;
                minDistance = distanceFromCenter;
            }
        });

        return maxVisibleSection;
    }

    // Update active navigation item
    function updateActiveNavItem() {
        const currentSection = getMostVisibleSection();
        
        if (currentSection) {
            const currentSectionId = currentSection.getAttribute('id');
            navItems.forEach(item => {
                const itemHref = item.getAttribute('href').substring(1);
                if (itemHref === currentSectionId) {
                    if (!item.classList.contains('active')) {
                        item.classList.add('active');
                        // Add smooth transition effect
                        item.style.transition = 'all 0.3s ease';
                    }
                } else {
                    item.classList.remove('active');
                }
            });
        }
    }

    // Throttle function to optimize scroll performance
    function throttle(func, limit) {
        let inThrottle;
        let lastFunc;
        let lastRan;
        
        return function() {
            const context = this;
            const args = arguments;
            
            if (!inThrottle) {
                func.apply(context, args);
                lastRan = Date.now();
                inThrottle = true;
            } else {
                clearTimeout(lastFunc);
                lastFunc = setTimeout(function() {
                    if ((Date.now() - lastRan) >= limit) {
                        func.apply(context, args);
                        lastRan = Date.now();
                    }
                }, limit - (Date.now() - lastRan));
            }
        };
    }

    // Add scroll event listener with improved throttling
    window.addEventListener('scroll', throttle(updateActiveNavItem, 100));

    // Add resize event listener to handle window resizing
    window.addEventListener('resize', throttle(updateActiveNavItem, 100));

    // Initial call to set active nav item on page load
    updateActiveNavItem();

    // Intersection Observer for optimized performance
    const observerOptions = {
        root: null,
        rootMargin: '-10% 0px -10% 0px',
        threshold: Array.from({ length: 11 }, (_, i) => i * 0.1) // More granular thresholds
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                // Only update if the section is significantly visible
                if (entry.intersectionRatio > 0.4) {
                    updateActiveNavItem();
                }
            }
        });
    }, observerOptions);

    // Observe all sections
    sections.forEach(section => observer.observe(section));
});