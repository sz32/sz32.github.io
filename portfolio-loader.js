// Portfolio Data Loader
let portfolioData = null;

// Load portfolio data from JSON
async function loadPortfolioData() {
    try {
        const response = await fetch('portfolio-data.json');
        portfolioData = await response.json();
        return portfolioData;
    } catch (error) {
        console.error('Error loading portfolio data:', error);
        return null;
    }
}

// Render Navigation
function renderNavigation(navigation) {
    const navList = document.querySelector('.vertical-nav ul');
    navList.innerHTML = navigation.map((item, index) => `
        <li>
            <a href="#${item.id}" class="nav-item ${index === 0 ? 'active' : ''}" data-section="${item.id}">
                <i class="${item.icon}"></i>
                <span class="tooltip">${item.label}</span>
            </a>
        </li>
    `).join('');
}

// Render Profile Section
function renderProfile(profile) {
    const profileSection = document.getElementById('profile');
    profileSection.innerHTML = `
        <div class="hero-content">
            <div class="profile-image" data-aos="zoom-in">
                <img src="${profile.profileImage}" alt="${profile.name}" />
            </div>
            <h1>${profile.name}</h1>
            <h2>${profile.title}</h2>
            <div class="typed-text" data-aos="fade-up" data-aos-delay="300">
                <span id="typed"></span>
            </div>
            <div class="contact-info">
                ${profile.contactInfo.map(info => `
                    <p><i class="${info.icon}"></i> ${info.value}</p>
                `).join('')}
            </div>
            <div class="profile-description" data-aos="fade-up" data-aos-delay="400">
                <p>${profile.description}</p>
            </div>
        </div>
    `;

    // Initialize Typed.js with dynamic strings
    if (typeof Typed !== 'undefined') {
        new Typed('#typed', {
            strings: profile.typedRoles,
            typeSpeed: 50,
            backSpeed: 30,
            backDelay: 1500,
            loop: true
        });
    }
}

// Render Skills Section
function renderSkills(skills, tools) {
    const skillsSection = document.getElementById('skills');

    const skillsHTML = skills.categories.map(category => {
        if (category.name === "AI Development Tools") {
            return `
                <div class="skill-category ai-tools">
                    <h3>${category.name}</h3>
                    <ul>
                        ${category.items.map(item => `
                            <li><i class="${item.icon}"></i> ${item.name}</li>
                        `).join('')}
                    </ul>
                    <div class="ai-benefits">
                        ${category.benefits.map(benefit => `<span>${benefit}</span>`).join('')}
                    </div>
                </div>
            `;
        } else {
            return `
                <div class="skill-category">
                    <h3>${category.name}</h3>
                    <ul>
                        ${category.items.map(item => `<li>${item}</li>`).join('')}
                    </ul>
                </div>
            `;
        }
    }).join('');

    const toolsHTML = tools.categories.map(category => `
        <div class="tool-category">
            <h3>${category.name}</h3>
            <ul>
                ${category.items.map(item => `
                    <li><i class="${item.icon}"></i>${item.name}</li>
                `).join('')}
            </ul>
        </div>
    `).join('');

    skillsSection.innerHTML = `
        <h2>${skills.title}</h2>
        <div class="skills-grid">
            ${skillsHTML}
        </div>
    `;

    // Add tools section after skills
    const toolsSection = document.createElement('section');
    toolsSection.className = 'tools';
    toolsSection.setAttribute('data-aos', 'fade-up');
    toolsSection.innerHTML = `
        <h2>${tools.title}</h2>
        <div class="tools-grid">
            ${toolsHTML}
        </div>
    `;
    skillsSection.parentNode.insertBefore(toolsSection, skillsSection.nextSibling);
}

// Render Experience Section
function renderExperience(experience) {
    const experienceSection = document.getElementById('experience');
    experienceSection.innerHTML = `
        <h2>${experience.title}</h2>
        ${experience.jobs.map(job => `
            <div class="job">
                <h3>${job.company} | ${job.location}</h3>
                <p class="job-title">${job.position}</p>
                <p class="job-duration">${job.duration}</p>
                <ul>
                    ${job.responsibilities.map(resp => `<li>${resp}</li>`).join('')}
                </ul>
            </div>
        `).join('')}
    `;
}

// Render Projects Section
function renderProjects(projects) {
    const projectsSection = document.getElementById('projects');

    const projectsHTML = projects.items.map(project => {
        if (project.type === 'sdk-showcase') {
            return `
                <div class="project-card sdk-showcase" data-aos="fade-up">
                    <h3>${project.name}</h3>
                    <p>${project.description}</p>

                    ${project.sections.map(section => `
                        <div class="sdk-section">
                            <h4><i class="${section.icon}"></i> ${section.title}</h4>
                            <ul>
                                ${section.points.map(point => `<li>${point}</li>`).join('')}
                            </ul>
                        </div>
                    `).join('')}

                    <div class="tech-stack">
                        ${project.techStack.map(tech => `<span>${tech}</span>`).join('')}
                    </div>

                    <div class="sdk-metrics">
                        ${project.metrics.map(metric => `
                            <div class="metric">
                                <span class="metric-value">${metric.value}</span>
                                <span class="metric-label">${metric.label}</span>
                            </div>
                        `).join('')}
                    </div>
                </div>
            `;
        } else {
            return `
                <div class="project-card">
                    <h3>${project.name}</h3>
                    <p>${project.description}</p>
                    <ul>
                        ${project.responsibilities.map(resp => `<li>${resp}</li>`).join('')}
                    </ul>
                </div>
            `;
        }
    }).join('');

    projectsSection.innerHTML = `
        <h2>${projects.title}</h2>
        ${projectsHTML}
    `;
}

// Render Blog Section
function renderBlog(blog) {
    const blogSection = document.getElementById('blog');
    blogSection.innerHTML = `
        <h2>${blog.title}</h2>
        <div class="blog-grid">
            ${blog.posts.map(post => `
                <a href="${post.url}" class="blog-card" data-aos="fade-up" ${post.delay ? `data-aos-delay="${post.delay}"` : ''} target="_blank">
                    <div class="blog-content">
                        <i class="${post.icon}"></i>
                        <h3>${post.title}</h3>
                        <p>${post.description}</p>
                        <span class="read-more">Read on Medium <i class="fas fa-arrow-right"></i></span>
                    </div>
                </a>
            `).join('')}
        </div>
    `;
}

// Render Achievements Section
function renderAchievements(achievements) {
    const achievementsSection = document.getElementById('achievements');
    achievementsSection.innerHTML = `
        <h2>${achievements.title}</h2>
        <div class="achievements-grid">
            ${achievements.items.map(achievement => `
                <div class="achievement-card" data-aos="fade-up" ${achievement.delay ? `data-aos-delay="${achievement.delay}"` : ''}>
                    <div class="achievement-icon">
                        <i class="${achievement.icon}"></i>
                    </div>
                    <h3>${achievement.title}</h3>
                    <p>${achievement.description}</p>
                    <ul>
                        ${achievement.metrics.map(metric => `
                            <li><span class="highlight">${metric.value}</span> ${metric.text}</li>
                        `).join('')}
                    </ul>
                    <div class="achievement-footer">
                        ${achievement.tags.map(tag => `<span class="tag">${tag}</span>`).join('')}
                    </div>
                </div>
            `).join('')}
        </div>
    `;
}

// Render Certifications Section
function renderCertifications(certifications) {
    const certificationsSection = document.getElementById('certifications');
    certificationsSection.innerHTML = `
        <h2>${certifications.title}</h2>
        <div class="cert-grid">
            ${certifications.items.map(cert => `
                <div class="cert-card">
                    <i class="${cert.icon}"></i>
                    <h3>${cert.title}</h3>
                    <p>${cert.description}</p>
                </div>
            `).join('')}
        </div>
    `;
}

// Render Education Section
function renderEducation(education) {
    const educationSection = document.getElementById('education');
    educationSection.innerHTML = `
        <h2>${education.title}</h2>
        <div class="education-timeline">
            ${education.items.map(item => `
                <div class="education-card" data-aos="fade-right" ${item.delay ? `data-aos-delay="${item.delay}"` : ''}>
                    <div class="education-icon">
                        <i class="fas fa-graduation-cap"></i>
                        <div class="education-line"></div>
                    </div>
                    <div class="education-content">
                        <div class="education-header">
                            <h3>${item.degree}</h3>
                            <span class="education-year">
                                <i class="fas fa-calendar-alt"></i>
                                ${item.years}
                            </span>
                        </div>
                        <div class="education-school">
                            <i class="fas fa-university"></i>
                            ${item.institution}
                        </div>
                        <div class="education-details">
                            ${item.tags.map(tag => `<div class="education-tag">${tag}</div>`).join('')}
                        </div>
                        <div class="education-achievements">
                            <ul>
                                ${item.achievements.map(achievement => `<li>${achievement}</li>`).join('')}
                            </ul>
                        </div>
                    </div>
                </div>
            `).join('')}
        </div>
    `;
}

// Render Work Process Section
function renderWorkProcess(workProcess) {
    const workProcessSection = document.getElementById('work-process');
    workProcessSection.innerHTML = `
        <h2>${workProcess.title}</h2>
        <div class="process-grid">
            ${workProcess.steps.map(step => `
                <div class="process-card">
                    <i class="${step.icon}"></i>
                    <h3>${step.title}</h3>
                    <p>${step.description}</p>
                </div>
            `).join('')}
        </div>
    `;
}

// Render Statistics Section
function renderStatistics(statistics) {
    const statisticsSection = document.getElementById('statistics');
    statisticsSection.innerHTML = `
        <h2>${statistics.title}</h2>
        <div class="stats-grid">
            ${statistics.items.map(stat => `
                <div class="stat-card">
                    <i class="${stat.icon}"></i>
                    <h3>${stat.value}</h3>
                    <p>${stat.label}</p>
                </div>
            `).join('')}
        </div>
    `;
}

// Render Contact Section
function renderContact(contact) {
    const contactSection = document.getElementById('contact');
    contactSection.innerHTML = `
        <h2>${contact.title}</h2>
        <div class="contact-container">
            <div class="contact-info-detailed">
                ${contact.contactInfo.map(info => `
                    <div class="contact-item">
                        <i class="${info.icon}"></i>
                        <h3>${info.title}</h3>
                        <p>${info.value}</p>
                    </div>
                `).join('')}
            </div>
            <form class="contact-form" id="contactForm" onsubmit="return handleSubmit(event)">
                <div class="form-group">
                    <label for="name" class="sr-only">Full Name</label>
                    <input type="text" id="name" name="name" placeholder="Your Full Name" required minlength="2" aria-label="Full Name">
                </div>
                <div class="form-group">
                    <label for="email" class="sr-only">Email Address</label>
                    <input type="email" id="email" name="email" placeholder="Your Email Address" required aria-label="Email Address">
                </div>
                <div class="form-group">
                    <label for="subject" class="sr-only">Subject</label>
                    <input type="text" id="subject" name="subject" placeholder="Subject" required minlength="3" aria-label="Subject">
                </div>
                <div class="form-group">
                    <label for="message" class="sr-only">Message</label>
                    <textarea id="message" name="message" placeholder="Write your message here..." required minlength="10" aria-label="Message"></textarea>
                </div>
                <div id="formStatus" class="form-status"></div>
                <div class="form-group">
                    <button type="submit" class="submit-btn" aria-label="Send Message">
                        <span class="btn-text">
                            Send Message
                            <i class="fas fa-paper-plane"></i>
                        </span>
                        <span class="btn-loading" style="display: none;">
                            <i class="fas fa-spinner"></i>
                            Sending...
                        </span>
                    </button>
                </div>
            </form>
        </div>
    `;
}

// Render Footer
function renderFooter(footer) {
    const footerElement = document.querySelector('footer');
    footerElement.innerHTML = `
        <div class="footer-content">
            <div class="social-links">
                ${footer.socialLinks.map(link => `
                    <a href="${link.url}" target="_blank" class="social-link"><i class="${link.icon}"></i></a>
                `).join('')}
            </div>
            <p>${footer.copyright}</p>
        </div>
    `;
}

// Reorder sections based on sectionOrder array
function reorderSections(sectionOrder) {
    const body = document.querySelector('body');
    const nav = document.querySelector('.vertical-nav');
    const footer = document.querySelector('footer');

    // Get all sections
    const sections = {};
    sectionOrder.forEach(sectionId => {
        const section = document.getElementById(sectionId);
        if (section) {
            sections[sectionId] = section;
            section.remove(); // Remove from DOM
        }
    });

    // Re-add sections in the correct order
    sectionOrder.forEach(sectionId => {
        if (sections[sectionId]) {
            body.insertBefore(sections[sectionId], footer);
        }
    });
}

// Main initialization function
async function initPortfolio() {
    const data = await loadPortfolioData();

    if (!data) {
        console.error('Failed to load portfolio data');
        return;
    }

    // Update page title
    document.title = data.meta.title;

    // Render all sections
    renderNavigation(data.navigation);
    renderProfile(data.profile);
    renderSkills(data.skills, data.tools);
    renderExperience(data.experience);
    renderProjects(data.projects);
    renderBlog(data.blog);
    renderAchievements(data.achievements);
    renderCertifications(data.certifications);
    renderEducation(data.education);
    renderWorkProcess(data.workProcess);
    renderStatistics(data.statistics);
    renderContact(data.contact);
    renderFooter(data.footer);

    // Reorder sections based on JSON configuration
    reorderSections(data.sectionOrder);

    // Reinitialize AOS (scroll animations)
    if (typeof AOS !== 'undefined') {
        AOS.refresh();
    }

    console.log('Portfolio loaded successfully from JSON!');
}

// Initialize when DOM is ready
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initPortfolio);
} else {
    initPortfolio();
}

