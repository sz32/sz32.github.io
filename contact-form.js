// Enhanced Contact Form Handler with Validation and Animations
const SCRIPT_URL = 'https://script.google.com/macros/s/AKfycbxkg3RMgdbQfwirZyNbO1gvpV9TLOacqBigUml-HHcTds1SSyyQSug-9hSBA4PeNbvIFQ/exec';

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('contactForm');
    const formStatus = document.getElementById('formStatus');
    const inputs = form.querySelectorAll('input, textarea');

    // Add real-time validation
    inputs.forEach(input => {
        input.addEventListener('blur', function() {
            validateField(this);
        });

        input.addEventListener('input', function() {
            if (this.classList.contains('error')) {
                validateField(this);
            }
        });

        // Add focus animation
        input.addEventListener('focus', function() {
            this.parentElement.style.transform = 'scale(1.01)';
        });

        input.addEventListener('blur', function() {
            this.parentElement.style.transform = 'scale(1)';
        });
    });

    // Validate individual field
    function validateField(field) {
        const value = field.value.trim();
        let isValid = true;
        let errorMessage = '';

        // Remove previous validation classes
        field.classList.remove('error', 'success');

        if (!value && field.hasAttribute('required')) {
            isValid = false;
            errorMessage = 'This field is required';
        } else if (field.type === 'email' && value) {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(value)) {
                isValid = false;
                errorMessage = 'Please enter a valid email address';
            }
        } else if (field.hasAttribute('minlength')) {
            const minLength = parseInt(field.getAttribute('minlength'));
            if (value.length < minLength && value.length > 0) {
                isValid = false;
                errorMessage = `Minimum ${minLength} characters required`;
            }
        }

        if (isValid && value) {
            field.classList.add('success');
        } else if (!isValid) {
            field.classList.add('error');
        }

        return isValid;
    }

    // Validate all fields
    function validateForm() {
        let isValid = true;
        inputs.forEach(input => {
            if (!validateField(input)) {
                isValid = false;
            }
        });
        return isValid;
    }

    // Show status message
    function showStatus(message, type) {
        formStatus.textContent = message;
        formStatus.className = `form-status ${type}`;
        formStatus.style.display = 'flex';

        // Auto-hide success messages after 5 seconds
        if (type === 'success') {
            setTimeout(() => {
                formStatus.style.display = 'none';
            }, 5000);
        }
    }

    // Hide status message
    function hideStatus() {
        formStatus.style.display = 'none';
        formStatus.className = 'form-status';
    }

    // Reset form
    function resetForm() {
        form.reset();
        inputs.forEach(input => {
            input.classList.remove('error', 'success');
        });
    }

    console.log('Enhanced contact form initialized ✨');
});

// Handle form submission
async function handleSubmit(event) {
    event.preventDefault();

    const form = document.getElementById('contactForm');
    const formStatus = document.getElementById('formStatus');
    const submitBtn = form.querySelector('.submit-btn');

    // Get form data
    const formData = {
        name: document.getElementById('name').value.trim(),
        email: document.getElementById('email').value.trim(),
        subject: document.getElementById('subject').value.trim(),
        message: document.getElementById('message').value.trim()
    };

    // Basic validation
    if (!formData.name || !formData.email || !formData.subject || !formData.message) {
        showStatusMessage('Please fill in all fields', 'error');
        return false;
    }

    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(formData.email)) {
        showStatusMessage('Please enter a valid email address', 'error');
        return false;
    }

    // Disable button and show loading state
    submitBtn.disabled = true;
    form.classList.add('submitting');
    hideStatusMessage();

    try {
        // Simulate API call (replace with your actual API endpoint)
        const response = await fetch(SCRIPT_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain;charset=utf-8',
            },
            mode: 'cors',
            redirect: 'follow',
            body: JSON.stringify(formData)
        });

        if (response.ok) {
            showStatusMessage('Thank you! Your message has been sent successfully. I\'ll get back to you soon!', 'success');
            form.reset();

            // Remove validation classes
            const inputs = form.querySelectorAll('input, textarea');
            inputs.forEach(input => {
                input.classList.remove('error', 'success');
            });
        } else {
            throw new Error('Failed to send message');
        }
    } catch (error) {
        console.error('Error:', error);
        showStatusMessage('Oops! Something went wrong. Please try again or contact me directly via email.', 'error');
    } finally {
        // Re-enable button and hide loading state
        submitBtn.disabled = false;
        form.classList.remove('submitting');
    }

    return false;
}

function showStatusMessage(message, type) {
    const formStatus = document.getElementById('formStatus');
    formStatus.textContent = message;
    formStatus.className = `form-status ${type}`;
    formStatus.style.display = 'flex';

    // Auto-hide success messages after 6 seconds
    if (type === 'success') {
        setTimeout(() => {
            formStatus.style.display = 'none';
        }, 6000);
    }
}

function hideStatusMessage() {
    const formStatus = document.getElementById('formStatus');
    formStatus.style.display = 'none';
}

// Add subtle animations when scrolling to contact section
const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            const formGroups = entry.target.querySelectorAll('.form-group');
            formGroups.forEach((group, index) => {
                setTimeout(() => {
                    group.style.opacity = '1';
                    group.style.transform = 'translateY(0)';
                }, index * 100);
            });
        }
    });
}, { threshold: 0.1 });

const contactForm = document.getElementById('contactForm');
if (contactForm) {
    // Set initial state for animation
    const formGroups = contactForm.querySelectorAll('.form-group');
    formGroups.forEach(group => {
        group.style.opacity = '0';
        group.style.transform = 'translateY(20px)';
        group.style.transition = 'all 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
    });

    observer.observe(contactForm);
}

