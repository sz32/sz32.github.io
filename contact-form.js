// Google Apps Script Web App URL (you'll need to replace this with your actual URL)
const SCRIPT_URL = 'https://script.google.com/macros/s/AKfycbyfk2QxFZndHtLFtiVWgiIE_uBzVIfIMxORTVuD1PO2b3wmuuk1tIpaVTTv1IJyI317Bg/exec';

async function handleSubmit(event) {
    event.preventDefault();
    
    const form = document.getElementById('contactForm');
    const statusDiv = document.getElementById('formStatus');
    const submitBtn = form.querySelector('.submit-btn');
    
    // Get form data
    const formData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        subject: document.getElementById('subject').value,
        message: document.getElementById('message').value,
        timestamp: new Date().toISOString()
    };

    try {
        // Show loading state
        form.classList.add('submitting');
        submitBtn.disabled = true;
        statusDiv.className = 'form-status';
        statusDiv.style.display = 'none';

        console.log('Sending data to:', SCRIPT_URL); // Debug log

        // Send data to Google Apps Script
        const response = await fetch(SCRIPT_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            mode: 'cors', // Enable CORS
            redirect: 'follow', // Follow redirects
            body: JSON.stringify(formData)
        });

        const result = await response.json();

        console.log('Response status:', response.status); // Debug log

        if (!response.ok) {
            const errorText = await response.text();
            console.error('Error response:', errorText); // Debug log
            throw new Error(`Network response was not ok (${response.status}): ${errorText}`);
        }

        const responseData = await response.json();
        console.log('Success response:', responseData); // Debug log

        // Show success message
        form.reset();
        statusDiv.textContent = 'Message sent successfully!';
        statusDiv.className = 'form-status success';
        statusDiv.style.display = 'block';
    } catch (error) {
        // Show error message
        statusDiv.className = 'form-status error';
        statusDiv.textContent = error.message;
    } finally {
        // Reset form state
        form.classList.remove('submitting');
        submitBtn.disabled = false;
        statusDiv.style.display = 'block';
    }

    return false;
}