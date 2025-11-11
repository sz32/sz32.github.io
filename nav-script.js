let navigationInitialized = false;
let scrollListener = null;
let resizeListener = null;
let currentObserver = null;

function initializeNavigation() {
    console.log('🔄 initializeNavigation called');

    // Clean up previous initialization
    if (navigationInitialized) {
        console.log('⚠️ Cleaning up previous navigation initialization');
        if (scrollListener) window.removeEventListener('scroll', scrollListener);
        if (resizeListener) window.removeEventListener('resize', resizeListener);
        if (currentObserver) currentObserver.disconnect();
    }

    // Get all sections and navigation items (fresh query after reordering)
    const sections = document.querySelectorAll('section[id]');
    const navItems = document.querySelectorAll('.nav-item');

    console.log('📊 Found', sections.length, 'sections and', navItems.length, 'nav items');

    if (sections.length === 0) {
        console.error('❌ No sections found!');
        return;
    }

    if (navItems.length === 0) {
        console.error('❌ No nav items found!');
        return;
    }

    // Smooth scrolling function
    function smoothScroll(targetElement, duration) {
        const targetPosition = targetElement.offsetTop - 20;
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
                navItems.forEach(nav => nav.classList.remove('active'));
                this.classList.add('active');
                smoothScroll(targetSection, 200);
            }
        });
    });

    // Calculate section position
    function getSectionPosition(section) {
        const rect = section.getBoundingClientRect();
        const viewportHeight = window.innerHeight;
        const visibleHeight = Math.min(rect.bottom, viewportHeight) - Math.max(rect.top, 0);
        const visiblePercentage = Math.max(0, visibleHeight) / viewportHeight;
        const centerPosition = rect.top + (rect.height / 2);
        const distanceFromCenter = Math.abs(centerPosition - (viewportHeight / 2));

        return { visiblePercentage, distanceFromCenter };
    }

    // Get most visible section
    function getMostVisibleSection() {
        let maxVisibleSection = null;
        let maxVisibility = 0;

        sections.forEach(section => {
            const { visiblePercentage, distanceFromCenter } = getSectionPosition(section);
            const visibility = visiblePercentage * (1 / (1 + distanceFromCenter * 0.001));

            if (visibility > maxVisibility) {
                maxVisibility = visibility;
                maxVisibleSection = section;
            }
        });

        return maxVisibleSection;
    }

    // Update active nav item
    function updateActiveNavItem() {
        const currentSection = getMostVisibleSection();

        if (currentSection) {
            const currentSectionId = currentSection.getAttribute('id');
            navItems.forEach(item => {
                const itemHref = item.getAttribute('href').substring(1);
                if (itemHref === currentSectionId) {
                    if (!item.classList.contains('active')) {
                        item.classList.add('active');
                        console.log('✨ Active section:', currentSectionId);
                    }
                } else {
                    item.classList.remove('active');
                }
            });
        }
    }

    // Throttle function
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

    // Add scroll listener
    scrollListener = throttle(updateActiveNavItem, 100);
    window.addEventListener('scroll', scrollListener);

    // Add resize listener
    resizeListener = throttle(updateActiveNavItem, 100);
    window.addEventListener('resize', resizeListener);

    // Initial call
    updateActiveNavItem();

    // Intersection Observer
    const observerOptions = {
        root: null,
        rootMargin: '-10% 0px -10% 0px',
        threshold: Array.from({ length: 11 }, (_, i) => i * 0.1)
    };

    currentObserver = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting && entry.intersectionRatio > 0.3) {
                updateActiveNavItem();
            }
        });
    }, observerOptions);

    // Observe all sections
    sections.forEach(section => {
        currentObserver.observe(section);
    });

    navigationInitialized = true;
    console.log('✅ Navigation fully initialized');
}

// Listen for portfolio loaded event
window.addEventListener('portfolioLoaded', function() {
    console.log('📢 Portfolio loaded event received');
    initializeNavigation();
});

// Fallback for DOMContentLoaded
document.addEventListener('DOMContentLoaded', function() {
    console.log('📄 DOM Content Loaded');
    setTimeout(function() {
        if (!navigationInitialized) {
            console.log('⏰ Fallback initialization');
            initializeNavigation();
        } else {
            console.log('✅ Already initialized');
        }
    }, 500);
});

