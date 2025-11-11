# 🚀 JSON-Driven Portfolio Website

A modern, fully customizable portfolio website with a **data-driven architecture**. All content is managed through a single JSON file, making it incredibly easy to update and maintain without touching any HTML, CSS, or JavaScript code.

---

## 📋 Table of Contents

- [Features](#-features)
- [Live Demo](#-live-demo)
- [How It Works](#-how-it-works)
- [File Structure](#-file-structure)
- [Getting Started](#-getting-started)
- [Customization Guide](#-customization-guide)
- [Test Files Explained](#-test-files-explained)
- [Documentation Files](#-documentation-files)
- [Contact Form Setup](#-contact-form-setup)
- [Fork & Deploy](#-fork--deploy)
- [Contributing](#-contributing)

---

## ✨ Features

- 🎨 **Data-Driven Design** - All content loads from a single JSON file
- 📱 **Fully Responsive** - Works seamlessly on all devices
- 🎭 **Smooth Animations** - Beautiful AOS (Animate On Scroll) effects
- 🧭 **Smart Navigation** - Vertical navigation with active section highlighting
- 📧 **Contact Form** - Integrated with Google Sheets for form submissions
- 🎯 **Section Reordering** - Change section order without touching HTML
- 🔄 **Easy Updates** - Edit JSON, refresh page - that's it!
- 🎨 **Modern UI** - Gradient backgrounds, glass-morphism, smooth transitions
- ⚡ **Fast Loading** - Optimized for performance
- 🛠️ **Test Files** - Built-in test pages for debugging and validation

---

## 🌐 Live Demo

Open `index.html` in any modern web browser or set up a local server:

```bash
# Using Python (recommended)
python3 -m http.server 8080

# Then visit: http://localhost:8080
```

---

## 🧠 How It Works

### Architecture Overview

```
┌─────────────────────────────────────────────┐
│         portfolio-data.json                 │
│    (Single source of truth for all data)    │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│       portfolio-loader.js                   │
│  - Fetches JSON data                        │
│  - Renders all sections dynamically         │
│  - Handles section reordering               │
│  - Initializes animations                   │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────┐
│           index.html                        │
│  - Main HTML structure                      │
│  - Empty section placeholders               │
│  - Content is injected at runtime           │
└─────────────────────────────────────────────┘
```

### Loading Process

1. **Page Load**: Browser loads `index.html`
2. **Fetch Data**: `portfolio-loader.js` fetches `portfolio-data.json`
3. **Parse JSON**: Data is parsed and validated
4. **Render Sections**: Each section is dynamically rendered
5. **Reorder DOM**: Sections are reordered based on `sectionOrder` array
6. **Initialize Features**: Navigation, animations, and contact form are initialized
7. **Ready**: Portfolio is fully interactive

---

## 📁 File Structure

### Core Files

| File                      | Purpose                                                   | Size   |
|---------------------------|-----------------------------------------------------------|--------|
| **index.html**            | Main HTML structure with empty section containers         | ~5 KB  |
| **portfolio-data.json**   | All portfolio content (profile, skills, projects, etc.)   | ~20 KB |
| **portfolio-loader.js**   | Loads JSON and dynamically renders all content            | ~17 KB |
| **styles.css**            | Main stylesheet for layout and design                     | ~15 KB |
| **nav-styles.css**        | Vertical navigation styles                                | ~3 KB  |
| **nav-script.js**         | Navigation functionality (scroll tracking, smooth scroll) | ~5 KB  |
| **contact-form.js**       | Contact form validation and submission                    | ~6 KB  |
| **google-apps-script.gs** | Backend script for Google Sheets integration              | ~2 KB  |

### Test Files

| File                          | Purpose                                            |
|-------------------------------|----------------------------------------------------|
| **test-json.html**            | Validates JSON loading and displays data structure |
| **test-download-resume.html** | Tests resume download button functionality         |
| **test-playstore-link.html**  | Tests Play Store link rendering in projects        |

### Documentation Files

| File                        | Purpose                                              |
|-----------------------------|------------------------------------------------------|
| **docs/SETUP.md**           | Complete setup guide with features and customization |
| **docs/QUICK-REFERENCE.md** | Quick reference for common edits                     |
| **docs/SUCCESS.txt**        | Setup completion checklist                           |

### Assets

| Folder      | Contents                            |
|-------------|-------------------------------------|
| **images/** | Profile pictures and project images |

---

## 🚀 Getting Started

### 1. Clone or Download

```bash
# Clone the repository
git clone https://github.com/yourusername/portfolio.git
cd portfolio

# Or download and extract the ZIP
```

### 2. Start Local Server

```bash
# Option 1: Python (recommended)
python3 -m http.server 8080

# Option 2: Python 2.x
python -m SimpleHTTPServer 8080

# Option 3: Node.js (if you have it)
npx http-server -p 8080

# Option 4: PHP
php -S localhost:8080
```

### 3. View Portfolio

Open your browser and navigate to:
```
http://localhost:8080
```

### 4. Test JSON Loading

Visit the test page to verify everything is working:
```
http://localhost:8080/test-json.html
```

---

## 🎨 Customization Guide

### Basic Content Updates

All content is in **`portfolio-data.json`**. Open it in any text editor and modify:

#### 1. Update Profile Information

```json
"profile": {
  "name": "Your Name Here",
  "title": "Your Job Title",
  "description": "A brief description about yourself...",
  "profileImage": "images/profile.jpg",
  "resumeUrl": "path/to/your/resume.pdf",
  "typedStrings": [
    "Your skill or tagline 1",
    "Your skill or tagline 2"
  ]
}
```

#### 2. Modify Skills

```json
"skills": {
  "heading": "Technical Skills",
  "categories": [
    {
      "name": "Languages",
      "items": ["JavaScript", "Python", "Java"]
    },
    {
      "name": "Frameworks",
      "items": ["React", "Node.js", "Django"]
    }
  ]
}
```

#### 3. Add Projects

```json
"projects": {
  "items": [
    {
      "name": "My Awesome Project",
      "type": "regular",
      "description": "What this project does...",
      "responsibilities": [
        "Feature 1",
        "Feature 2"
      ],
      "playstoreUrl": "https://play.google.com/store/apps/details?id=com.example"
    }
  ]
}
```

#### 4. Update Experience

```json
"experience": {
  "jobs": [
    {
      "company": "Company Name",
      "location": "City, Country",
      "position": "Job Title",
      "duration": "Jan 2020 - Present",
      "responsibilities": [
        "Key responsibility 1",
        "Key achievement 2"
      ]
    }
  ]
}
```

#### 5. Change Section Order

```json
"sectionOrder": [
  "profile",
  "projects",    // Moved up
  "skills",      // Moved down
  "experience",
  "blog",
  "achievements"
]
```

Save the file and refresh your browser - changes appear instantly! ✨

### Advanced Customization

#### Add New Section Type

1. Add section data to `portfolio-data.json`
2. Create HTML section in `index.html`:
   ```html
   <section id="my-section" class="my-section" data-aos="fade-up">
   </section>
   ```
3. Create render function in `portfolio-loader.js`:
   ```javascript
   function renderMySection(data) {
     const section = document.getElementById('my-section');
     section.innerHTML = `
       <h2>${data.heading}</h2>
       <p>${data.content}</p>
     `;
   }
   ```
4. Call it in `initPortfolio()` function
5. Add `"my-section"` to `sectionOrder` array

#### Customize Animations

Edit AOS attributes in `portfolio-loader.js`:
```javascript
data-aos="fade-up"           // Animation type
data-aos-delay="300"         // Delay in milliseconds
data-aos-duration="1000"     // Animation duration
```

---

## 🧪 Test Files Explained

### test-json.html

**Purpose**: Validates that your JSON data loads correctly and displays a summary.

**What it shows**:
- ✅ JSON loading status
- 📊 Data structure overview
- 🔢 Count of items in each section
- 📋 Current section order
- ⚠️ Any loading errors

**When to use**: 
- After modifying `portfolio-data.json`
- When debugging content issues
- To verify JSON syntax is valid

**How to use**:
```bash
# Visit in browser
http://localhost:8080/test-json.html
```

### test-download-resume.html

**Purpose**: Tests the resume download button styling and functionality.

**What it shows**:
- 📄 Resume button with proper styling
- 🎨 Visual test of button states (hover, click)
- 📱 Responsive button behavior

**When to use**:
- Before adding a resume to your portfolio
- To test download functionality
- To verify button styling matches design

### test-playstore-link.html

**Purpose**: Tests how Play Store links render in project cards.

**What it shows**:
- 📱 Project cards with Play Store badges
- 🔗 Link functionality
- 🎨 Badge styling and positioning

**When to use**:
- Before adding Play Store links to projects
- To test mobile app project cards
- To verify badge appearance

---

## 📚 Documentation Files

### docs/SETUP.md

**Comprehensive setup guide** covering:
- ✅ What was created in the portfolio
- 🎯 Key features overview
- 📝 Step-by-step usage instructions
- 🎨 Common customization examples
- 🔧 Detailed configuration options

**Read this**: When setting up the portfolio for the first time.

### docs/QUICK-REFERENCE.md

**Quick reference guide** for:
- 🚀 Most common edits (profile, skills, projects)
- 📋 JSON snippets ready to copy-paste
- ⚡ Quick edit workflows
- 🎯 Direct path to each section

**Read this**: When making quick content updates.

### docs/SUCCESS.txt

**Setup checklist** showing:
- ✅ All files created
- 🚀 Quick start commands
- ✏️ Update workflow
- 📋 Section ordering tips

**Read this**: To verify successful setup.

---

## 📧 Contact Form Setup

The portfolio includes a fully functional contact form that saves submissions to Google Sheets.

### Setup Process

1. **Create Google Sheet**:
   - Go to [Google Sheets](https://sheets.google.com)
   - Create a new spreadsheet named "Portfolio Contacts"

2. **Add Apps Script**:
   - In your spreadsheet: Extensions → Apps Script
   - Delete default code
   - Copy content from `google-apps-script.gs`
   - Paste into the script editor
   - Update the email address to yours:
     ```javascript
     GmailApp.sendEmail(
       'your-email@gmail.com', // Change this!
       'New Portfolio Contact Form Submission',
       emailBody
     );
     ```

3. **Deploy as Web App**:
   - Click Deploy → New deployment
   - Select type: Web app
   - Execute as: Me
   - Who has access: Anyone
   - Click Deploy
   - Copy the Web App URL

4. **Update Portfolio**:
   - Open `contact-form.js`
   - Replace the `SCRIPT_URL` with your Web App URL:
     ```javascript
     const SCRIPT_URL = 'YOUR_WEB_APP_URL_HERE';
     ```

5. **Test**:
   - Fill out the contact form on your portfolio
   - Check your Google Sheet for the submission
   - Check your email for notification

### Features

- ✅ Real-time field validation
- ✅ Email format checking
- ✅ Required field enforcement
- ✅ Success/error messages
- ✅ Form submission animation
- ✅ Email notifications
- ✅ Google Sheets logging

---

## 🍴 Fork & Deploy

### Fork This Repository

1. **Fork on GitHub**:
   ```bash
   # Visit the repository on GitHub
   # Click the "Fork" button in the top right
   ```

2. **Clone Your Fork**:
   ```bash
   git clone https://github.com/YOUR-USERNAME/portfolio.git
   cd portfolio
   ```

3. **Make It Yours**:
   - Open `portfolio-data.json`
   - Replace all content with your information
   - Update profile image in `images/profile.jpg`
   - Add your resume (if applicable)

### Run Locally

```bash
# Start local server
python3 -m http.server 8080

# View at http://localhost:8080
```

### Deploy to GitHub Pages

1. **Enable GitHub Pages**:
   ```bash
   # In your repository on GitHub:
   # Settings → Pages
   # Source: Deploy from branch
   # Branch: main (or master)
   # Folder: / (root)
   # Click Save
   ```

2. **Your site will be live at**:
   ```
   https://YOUR-USERNAME.github.io/portfolio/
   ```

3. **Update Content**:
   ```bash
   # Make changes to portfolio-data.json
   git add portfolio-data.json
   git commit -m "Update portfolio content"
   git push origin main
   
   # Changes go live in ~1 minute
   ```

### Deploy to Netlify

1. **Via GitHub** (Recommended):
   - Go to [Netlify](https://netlify.com)
   - Click "New site from Git"
   - Connect your GitHub repository
   - Click "Deploy site"
   - Your site is live! 🎉

2. **Via Drag & Drop**:
   - Go to [Netlify Drop](https://app.netlify.com/drop)
   - Drag your portfolio folder
   - Your site is live instantly!

### Deploy to Vercel

```bash
# Install Vercel CLI
npm i -g vercel

# Deploy
cd portfolio
vercel

# Follow the prompts
# Your site is live!
```

### Deploy to Cloudflare Pages

1. Go to [Cloudflare Pages](https://pages.cloudflare.com)
2. Connect your GitHub account
3. Select your portfolio repository
4. Click "Begin setup"
5. Click "Save and Deploy"
6. Your site is live!

### Custom Domain

After deploying to any platform:

1. Buy a domain (Namecheap, Google Domains, etc.)
2. Add custom domain in your hosting platform settings
3. Update DNS records as instructed
4. Wait for DNS propagation (5-60 minutes)
5. Your portfolio is live on your custom domain! 🌐

---

## 🛠️ Troubleshooting

### JSON Not Loading?

1. Check browser console (F12) for errors
2. Validate JSON syntax at [JSONLint](https://jsonlint.com)
3. Ensure local server is running (not opening file directly)
4. Check file encoding is UTF-8

### Sections Not Appearing?

1. Verify section ID in `index.html` matches `sectionOrder` in JSON
2. Check browser console for JavaScript errors
3. Ensure section data exists in `portfolio-data.json`

### Contact Form Not Working?

1. Verify Google Apps Script is deployed as web app
2. Check `SCRIPT_URL` in `contact-form.js` is correct
3. Ensure web app has "Anyone" access permission
4. Check browser console for submission errors

### Animations Not Working?

1. Ensure AOS library is loaded (check browser console)
2. Verify internet connection (AOS loads from CDN)
3. Check `data-aos` attributes are present in rendered HTML

---

## 🤝 Contributing

Contributions are welcome! Feel free to:

- 🐛 Report bugs
- 💡 Suggest new features
- 🔧 Submit pull requests
- 📖 Improve documentation

---

## 📄 License

This project is open source and available under the MIT License.

---

## 💬 Support

Having issues? Need help customizing?

- 📧 Check the documentation files in `docs/`
- 🧪 Use test files to debug issues
- 🌐 Open an issue on GitHub

---

## 🎯 Quick Start Checklist

- [ ] Clone/download repository
- [ ] Start local server (`python3 -m http.server 8080`)
- [ ] View portfolio (`http://localhost:8080`)
- [ ] Test JSON loading (`http://localhost:8080/test-json.html`)
- [ ] Update `portfolio-data.json` with your info
- [ ] Replace `images/profile.jpg` with your photo
- [ ] Set up contact form (follow guide above)
- [ ] Test all sections work correctly
- [ ] Deploy to GitHub Pages/Netlify/Vercel
- [ ] Share your awesome portfolio! 🚀

---

**Last Updated**: November 12, 2025
**Version**: 2.0.0

Made with ❤️ by developers, for developers

