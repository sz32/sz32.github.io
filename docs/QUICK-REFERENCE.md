# 📋 Quick Reference - Portfolio JSON

## 🚀 Quick Start

### View Portfolio
```
http://localhost:8080/
```

### Test JSON Loading
```
http://localhost:8080/test-json.html
```

### Edit Content
```
Open: portfolio-data.json
Edit: Any content you want to change
Save: File
Refresh: Browser
```

---

## 🎯 Most Common Edits

### 1. Update Profile Info
**Location:** `profile` section
```json
"profile": {
  "name": "Your Name",
  "title": "Your Job Title",
  "description": "Your bio..."
}
```

### 2. Add/Remove Skills
**Location:** `skills.categories`
```json
{
  "name": "Category Name",
  "items": ["Skill 1", "Skill 2", "Skill 3"]
}
```

### 3. Update Experience
**Location:** `experience.jobs`
```json
{
  "company": "Company Name",
  "location": "City",
  "position": "Job Title",
  "duration": "Jan 2020 - Present",
  "responsibilities": ["Task 1", "Task 2"]
}
```

### 4. Add Project
**Location:** `projects.items`
```json
{
  "name": "Project Name",
  "type": "regular",
  "description": "What it does",
  "responsibilities": ["What you did 1", "What you did 2"]
}
```

### 5. Add Blog Post
**Location:** `blog.posts`
```json
{
  "title": "Article Title",
  "description": "Short description",
  "url": "https://link-to-article.com",
  "icon": "fab fa-medium",
  "delay": 0
}
```

### 6. Change Section Order
**Location:** `sectionOrder` (top of file)
```json
"sectionOrder": [
  "profile",
  "skills",
  "projects",   // Rearrange these
  "experience", // in any order
  "blog",       // you want
  ...
]
```

### 7. Update Contact Info
**Location:** `contact.contactInfo`
```json
{
  "icon": "fas fa-envelope",
  "title": "Email",
  "value": "your@email.com"
}
```

### 8. Update Social Links
**Location:** `footer.socialLinks`
```json
{
  "icon": "fab fa-linkedin",
  "url": "https://linkedin.com/in/yourprofile"
}
```

---

## 🎨 Icon Reference (Font Awesome)

### Common Icons
```
fas fa-user          - Profile
fas fa-code          - Code/Programming
fas fa-briefcase     - Work/Experience
fas fa-project-diagram - Projects
fas fa-trophy        - Achievements
fas fa-certificate   - Certifications
fas fa-graduation-cap - Education
fas fa-envelope      - Email
fas fa-phone         - Phone
fas fa-map-marker-alt - Location
fab fa-linkedin      - LinkedIn
fab fa-github        - GitHub
fab fa-medium        - Medium
```

Find more: https://fontawesome.com/icons

---

## ⚠️ JSON Syntax Rules

### ✅ Valid
```json
{
  "key": "value",
  "number": 123,
  "array": ["item1", "item2"],
  "object": { "nested": "value" }
}
```

### ❌ Invalid
```json
{
  "key": "value",  // No comments allowed!
  "lastItem": "value",  // No trailing comma
  'singleQuotes': 'not allowed'  // Use double quotes
}
```

---

## 🔧 Troubleshooting

### Problem: Changes not showing
**Solution:** Hard refresh (Cmd+Shift+R or Ctrl+Shift+F5)

### Problem: Page is blank
**Solution:** Check browser console (F12) for errors

### Problem: JSON not loading
**Solution:** 
1. Visit test-json.html to check
2. Validate JSON at jsonlint.com
3. Check for syntax errors

### Problem: Section missing
**Solution:** 
1. Check if section ID is in `sectionOrder`
2. Verify section data exists in JSON

---

## 📊 File Sizes

- portfolio-data.json: ~20 KB
- portfolio-loader.js: ~17 KB
- index.html: ~33 KB
- Total: ~70 KB (very lightweight!)

---

## 🎓 Tips

1. **Always validate JSON** after editing
2. **Backup before major changes**
3. **Test on test-json.html** before viewing full portfolio
4. **Use proper quotes** - always double quotes `"`
5. **No trailing commas** in JSON arrays/objects
6. **Keep structure consistent** with existing format

---

## 📱 Responsive Design

The portfolio automatically adapts to:
- 📱 Mobile phones
- 📱 Tablets
- 💻 Laptops
- 🖥️ Desktops

No changes needed - it just works!

---

## 🎯 Next Steps

1. ✏️ Open `portfolio-data.json`
2. 🔍 Find the section you want to edit
3. ✨ Make your changes
4. 💾 Save the file
5. 🔄 Refresh your browser
6. 🎉 Enjoy your updated portfolio!

---

## 📞 Need Help?

- Check SETUP.md for detailed guide
- Check README.md for documentation
- Visit test-json.html to debug
- Validate JSON at jsonlint.com

**Remember:** The portfolio is now DATA-DRIVEN. All content lives in portfolio-data.json!

