package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun main() {
    val scope = MainScope()

    renderComposable(rootElementId = "root") {
        var portfolioData by remember { mutableStateOf<PortfolioData?>(null) }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            scope.launch {
                try {
                    portfolioData = loadPortfolioData()
                    isLoading = false
                } catch (e: Exception) {
                    console.error("Error loading portfolio data", e)
                    isLoading = false
                }
            }
        }

        Style(AppStyles)

        when {
            isLoading -> LoadingScreen()
            portfolioData != null -> PortfolioApp(portfolioData!!)
            else -> ErrorScreen()
        }
    }
}

@Composable
fun PortfolioApp(data: PortfolioData) {
    Div(attrs = { classes("portfolio-container") }) {
        // Navigation
        VerticalNavigation(data.navigation)

        // Main Content
        Main(attrs = { classes("main-content") }) {
            data.sectionOrder.forEach { sectionId ->
                when (sectionId) {
                    "profile" -> ProfileSection(data.profile)
                    "skills" -> SkillsSection(data.skills)
                    "tools" -> ToolsSection(data.tools)
                    "experience" -> ExperienceSection(data.experience)
                    "projects" -> ProjectsSection(data.projects)
                    "blog" -> BlogSection(data.blog)
                    "achievements" -> AchievementsSection(data.achievements)
                    "certifications" -> CertificationsSection(data.certifications)
                    "education" -> EducationSection(data.education)
                    "work-process" -> WorkProcessSection(data.workProcess)
                    "statistics" -> StatisticsSection(data.statistics)
                    "contact" -> ContactSection(data.contact)
                }
            }
        }

        // Footer
        Footer(data.footer)
    }
}

@Composable
fun LoadingScreen() {
    Div(attrs = {
        classes("loading-screen")
        style {
            display(DisplayStyle.Flex)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            height(100.vh)
            fontSize(24.px)
        }
    }) {
        Text("Loading Portfolio...")
    }
}

@Composable
fun ErrorScreen() {
    Div(attrs = {
        classes("error-screen")
        style {
            display(DisplayStyle.Flex)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            height(100.vh)
            fontSize(24.px)
            color(Color.red)
        }
    }) {
        Text("Error loading portfolio data. Please refresh the page.")
    }
}

