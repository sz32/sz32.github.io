package com.zeelsolanki.portfolio

import kotlinx.serialization.Serializable

@Serializable
data class PortfolioData(
    val meta: Meta,
    val sectionOrder: List<String>,
    val navigation: List<NavigationItem>,
    val profile: Profile,
    val skills: Skills,
    val tools: Tools,
    val experience: Experience,
    val projects: Projects,
    val blog: Blog,
    val achievements: Achievements,
    val certifications: Certifications,
    val education: Education,
    val workProcess: WorkProcess,
    val statistics: Statistics,
    val contact: Contact,
    val footer: Footer
)

@Serializable
data class Meta(
    val title: String,
    val lastUpdated: String
)

@Serializable
data class NavigationItem(
    val id: String,
    val label: String,
    val icon: String
)

@Serializable
data class Profile(
    val name: String,
    val title: String,
    val profileImage: String,
    val typedRoles: List<String>,
    val contactInfo: List<ContactInfo>,
    val description: String,
    val resumeUrl: String? = null
)

@Serializable
data class ContactInfo(
    val icon: String,
    val value: String
)

@Serializable
data class Skills(
    val title: String,
    val categories: List<SkillCategory>
)

@Serializable
data class SkillCategory(
    val name: String,
    val items: List<String>,
    val icon: Boolean? = null
)

@Serializable
data class Tools(
    val title: String,
    val categories: List<ToolCategory>
)

@Serializable
data class ToolCategory(
    val name: String,
    val items: List<String>
)

@Serializable
data class Experience(
    val title: String,
    val jobs: List<Job>
)

@Serializable
data class Job(
    val company: String,
    val position: String,
    val duration: String,
    val location: String,
    val description: String? = null,
    val achievements: List<String>? = null,
    val skills: List<String>? = null
)

@Serializable
data class Projects(
    val title: String,
    val items: List<Project>
)

@Serializable
data class Project(
    val name: String,
    val description: String,
    val technologies: List<String>,
    val link: String? = null,
    val playStoreLink: String? = null,
    val image: String? = null,
    val highlights: List<String>? = null
)

@Serializable
data class Blog(
    val title: String,
    val posts: List<BlogPost>
)

@Serializable
data class BlogPost(
    val title: String,
    val date: String,
    val platform: String,
    val link: String,
    val description: String,
    val tags: List<String>? = null
)

@Serializable
data class Achievements(
    val title: String,
    val items: List<Achievement>
)

@Serializable
data class Achievement(
    val title: String,
    val description: String,
    val icon: String? = null
)

@Serializable
data class Certifications(
    val title: String,
    val items: List<Certification>
)

@Serializable
data class Certification(
    val name: String,
    val issuer: String,
    val date: String,
    val link: String? = null
)

@Serializable
data class Education(
    val title: String,
    val items: List<EducationItem>
)

@Serializable
data class EducationItem(
    val degree: String,
    val institution: String,
    val duration: String,
    val location: String,
    val grade: String? = null,
    val description: String? = null
)

@Serializable
data class WorkProcess(
    val title: String,
    val steps: List<ProcessStep>
)

@Serializable
data class ProcessStep(
    val title: String,
    val description: String,
    val icon: String
)

@Serializable
data class Statistics(
    val title: String,
    val stats: List<Stat>
)

@Serializable
data class Stat(
    val value: String,
    val label: String,
    val icon: String
)

@Serializable
data class Contact(
    val title: String,
    val description: String,
    val email: String,
    val formEndpoint: String,
    val socialLinks: List<SocialLink>
)

@Serializable
data class SocialLink(
    val platform: String,
    val url: String,
    val icon: String
)

@Serializable
data class Footer(
    val copyright: String,
    val socialLinks: List<SocialLink>
)

