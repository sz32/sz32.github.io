package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun SkillsSection(skills: Skills) {
    Style {
        // Skill card hover effect
        type(".skill-card:hover") style {
            property("transform", "translateY(-10px)")
            property("box-shadow", "0 10px 30px rgba(0,0,0,0.15)")
        }
    }

    Section(attrs = {
        id("skills")
        classes("skills")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color("#f8f9fa"))
        }
    }) {
        H2 { Text(skills.title) }

        Div(attrs = {
            classes("skills-grid")
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "repeat(auto-fit, minmax(280px, 1fr))")
                gap(30.px)
                marginTop(40.px)
            }
        }) {
            skills.categories.forEach { category ->
                Div(attrs = {
                    classes("skill-card")
                    style {
                        backgroundColor(Color.white)
                        padding(30.px)
                        borderRadius(15.px)
                        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
                        property("transition", "transform 0.3s ease")
                    }
                }) {
                    H3(attrs = {
                        style {
                            fontSize(1.5.cssRem)
                            marginBottom(20.px)
                            color(Color("#3498db"))
                        }
                    }) {
                        Text(category.name)
                    }

                    Ul(attrs = {
                        style {
                            property("list-style", "none")
                            display(DisplayStyle.Flex)
                            property("flex-direction", "column")
                            gap(10.px)
                        }
                    }) {
                        category.items.forEach { skill ->
                            Li(attrs = {
                                style {
                                    display(DisplayStyle.Flex)
                                    alignItems(AlignItems.Center)
                                    gap(10.px)
                                    padding(8.px, 0.px)
                                }
                            }) {
                                I(attrs = {
                                    classes("fas", "fa-check-circle")
                                    style {
                                        color(Color("#2ecc71"))
                                        fontSize(14.px)
                                    }
                                })
                                Text(skill)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToolsSection(tools: Tools) {
    Style {
        // Tool card hover effect
        type(".tool-card:hover") style {
            property("transform", "translateY(-5px)")
        }
    }

    Section(attrs = {
        id("tools")
        classes("tools")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color.white)
        }
    }) {
        H2 { Text(tools.title) }

        Div(attrs = {
            classes("tools-grid")
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "repeat(auto-fit, minmax(280px, 1fr))")
                gap(30.px)
                marginTop(40.px)
            }
        }) {
            tools.categories.forEach { category ->
                Div(attrs = {
                    classes("tool-card")
                    style {
                        backgroundColor(Color("#f8f9fa"))
                        padding(30.px)
                        borderRadius(15.px)
                        property("box-shadow", "0 3px 10px rgba(0,0,0,0.08)")
                        property("transition", "transform 0.3s ease")
                    }
                }) {
                    H3(attrs = {
                        style {
                            fontSize(1.5.cssRem)
                            marginBottom(20.px)
                            color(Color("#667eea"))
                        }
                    }) {
                        Text(category.name)
                    }

                    Div(attrs = {
                        style {
                            display(DisplayStyle.Flex)
                            property("flex-wrap", "wrap")
                            gap(10.px)
                        }
                    }) {
                        category.items.forEach { tool ->
                            Span(attrs = {
                                style {
                                    backgroundColor(Color.white)
                                    padding(8.px, 16.px)
                                    borderRadius(20.px)
                                    fontSize(14.px)
                                    color(Color("#2c3e50"))
                                    property("box-shadow", "0 2px 5px rgba(0,0,0,0.05)")
                                }
                            }) {
                                Text(tool)
                            }
                        }
                    }
                }
            }
        }
    }
}

