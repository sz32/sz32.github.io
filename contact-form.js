// Google Apps Script Web App URL (you'll need to replace this with your actual URL)
const SCRIPT_URL = 'https://script.google.com/macros/s/AKfycbwPXUdm4lvkXDSvU2oYOwRfsCZRw1EgMpwO5ptvaVfTLAsDGlVGXkbVFkIhJMc4xuXLiA/exec';

async function handleSubmit(event) {
    event.preventDefault();
    
    const form = document.getElementById('contactForm');
    const statusDiv = document.getElementById('formStatus');
    const submitBtn = form.querySelector('.submit-btn');
    
    // Get form data
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());
    data.timestamp = new Date().toISOString();

    try {
        // Show loading state
        form.classList.add('submitting');
        submitBtn.disabled = true;
        statusDiv.innerHTML = '<div class="loading">Sending message...</div>';
        statusDiv.style.display = 'block';

        const response = await fetch(SCRIPT_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain;charset=utf-8',
            },
            mode: 'cors',
            redirect: 'follow',
            body: JSON.stringify(data)
        });

        // Clear form and show success message
        form.reset();
        statusDiv.innerHTML = '<div class="success">Message sent successfully!</div>';
    } catch (error) {
        console.error('Submission error:', error);
        statusDiv.innerHTML = '<div class="error">Failed to send message. Please try again.</div>';
    } finally {
        form.classList.remove('submitting');
        submitBtn.disabled = false;
    }

    return false;
}