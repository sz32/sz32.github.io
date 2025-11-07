// Google Apps Script code
function doPost(e) {
  try {
    // Parse the incoming data
    const data = JSON.parse(e.postData.contents);
    
    // Get the active spreadsheet and sheet
    const ss = SpreadsheetApp.getActiveSpreadsheet();
    const sheet = ss.getSheetByName('Contact Form Responses') || ss.insertSheet('Contact Form Responses');
    
    // Set headers if sheet is empty
    if (sheet.getLastRow() === 0) {
      sheet.appendRow(['Timestamp', 'Name', 'Email', 'Subject', 'Message']);
    }
    
    // Append the form data
    sheet.appendRow([
      data.timestamp,
      data.name,
      data.email,
      data.subject,
      data.message
    ]);
    
    // Send email notification
    const emailBody = `
      New contact form submission:
      
      Name: ${data.name}
      Email: ${data.email}
      Subject: ${data.subject}
      Message: ${data.message}
      Timestamp: ${data.timestamp}
    `;
    
    GmailApp.sendEmail(
      'solankizeel91@gmail.com', // Your email address
      'New Portfolio Contact Form Submission',
      emailBody
    );
    
    return ContentService.createTextOutput(JSON.stringify({
      success: true,
      message: 'Data successfully recorded'
    })).setMimeType(ContentService.MimeType.JSON);
    
  } catch (error) {
    return ContentService.createTextOutput(JSON.stringify({
      success: false,
      error: error.toString()
    })).setMimeType(ContentService.MimeType.JSON);
  }
}

function doGet(e) {
  return ContentService.createTextOutput('The script is working!');
}