# Portfolio JSON Configuration Guide

This portfolio is now fully data-driven using a JSON configuration file. All content is loaded dynamically from `portfolio-data.json`.

## 📁 Files

- **portfolio-data.json** - Contains all portfolio data (profile, skills, experience, projects, etc.)
- **portfolio-loader.js** - JavaScript that loads and renders the JSON data
- **index.html** - Main HTML structure (content is dynamically loaded)

## 🔧 How It Works

1. When the page loads, `portfolio-loader.js` fetches `portfolio-data.json`
2. The script dynamically renders all sections based on the JSON data
3. Sections are displayed in the order specified in the `sectionOrder` array
4. All content (text, links, icons, etc.) comes from the JSON file

## 📝 Updating Portfolio Content

### To update any content:

1. Open `portfolio-data.json`
2. Modify the relevant section (see structure below)
3. Save the file
4. Refresh the webpage - changes will appear automatically!

### JSON Structure

```json
{
  "sectionOrder": [...],      // Order of sections on the page
  "navigation": [...],         // Navigation menu items
  "profile": {...},           // Profile section data
  "skills": {...},            // Technical skills
  "tools": {...},             // Development tools
  "experience": {...},        // Work experience
  "projects": {...},          // Project portfolio
  "blog": {...},              // Blog posts/articles
  "achievements": {...},      // Key achievements
  "certifications": {...},    // Certifications
  "education": {...},         // Education history
  "workProcess": {...},       // Work methodology
  "statistics": {...},        // Career statistics
  "contact": {...},           // Contact information
  "footer": {...}             // Footer content
}
```

## 🎨 Customization Examples

### Change Section Order

Edit the `sectionOrder` array in `portfolio-data.json`:

```json
"sectionOrder": [
  "profile",
  "projects",     // Move projects before skills
  "skills",
  "experience",
  ...
]
```

### Add a New Project

Add to the `projects.items` array:

```json
{
  "name": "New Project Name",
  "type": "regular",
  "description": "Project description...",
  "responsibilities": [
    "Task 1",
    "Task 2"
  ]
}
```

### Update Profile Information

Modify the `profile` section:

```json
"profile": {
  "name": "Your Name",
  "title": "Your Title",
  "description": "Your bio...",
  ...
}
```

### Add Blog Posts

Add to the `blog.posts` array:

```json
{
  "title": "Article Title",
  "description": "Article description",
  "url": "https://medium.com/@yourhandle/article-url",
  "icon": "fab fa-medium",
  "delay": 500
}
```

### Modify Skills

Edit `skills.categories` to add/remove/modify skill categories:

```json
{
  "name": "New Category",
  "items": [
    "Skill 1",
    "Skill 2",
    "Skill 3"
  ]
}
```

## 🚀 Benefits of This Approach

1. **Easy Updates** - Change content without touching HTML/CSS/JS
2. **Maintainable** - All data in one centralized location
3. **Flexible** - Easy to reorder sections or add new ones
4. **Portable** - JSON can be used for other purposes (API, mobile app, etc.)
5. **Version Control** - Track content changes easily

## 🛠️ Advanced Customization

### Adding a New Section Type

1. Add the section data to `portfolio-data.json`
2. Create a render function in `portfolio-loader.js`:
   ```javascript
   function renderMyNewSection(data) {
     const section = document.getElementById('my-section');
     section.innerHTML = `...`;
   }
   ```
3. Call it in the `initPortfolio()` function
4. Add to `sectionOrder` array

### Custom Section Order per Device

You can modify `portfolio-loader.js` to check screen size and load different section orders:

```javascript
const isMobile = window.innerWidth < 768;
const order = isMobile ? data.mobileSectionOrder : data.sectionOrder;
reorderSections(order);
```

## 📱 Testing

1. Open `index.html` in a web browser
2. Open browser console (F12) to see loading status
3. Make changes to `portfolio-data.json`
4. Refresh the page to see updates

## ⚠️ Important Notes

- Keep the JSON structure valid (use a JSON validator if needed)
- Maintain the same property names as defined in the structure
- Icons use Font Awesome classes (e.g., "fas fa-code")
- AOS animation attributes are preserved (data-aos, data-aos-delay)

## 🎯 Quick Edits Checklist

- [ ] Update profile name and title
- [ ] Change contact information
- [ ] Add/remove skills
- [ ] Update work experience
- [ ] Add new projects
- [ ] Update blog posts
- [ ] Modify achievements
- [ ] Change section order
- [ ] Update social media links
- [ ] Modify statistics

---

**Last Updated:** November 10, 2025

