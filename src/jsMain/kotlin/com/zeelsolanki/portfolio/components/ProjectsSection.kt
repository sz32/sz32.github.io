package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ProjectsSection(projects: Projects) {
    Style {
        type(".project-card:hover") style {
            property("transform", "translateY(-5px)")
            property("box-shadow", "0 10px 30px rgba(0,0,0,0.15)")
        }
        type(".project-link:hover") style {
            backgroundColor(Color("#2980b9"))
        }
        type(".playstore-link:hover") style {
            backgroundColor(Color("#45a049"))
        }
    }

    Section(attrs = {
        id("projects")
        classes("projects")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color.white)
        }
    }) {
        H2 { Text(projects.title) }

        Div(attrs = {
            classes("projects-grid")
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "repeat(auto-fit, minmax(320px, 1fr))")
                gap(30.px)
                marginTop(40.px)
            }
        }) {
            projects.items.forEach { project ->
                Div(attrs = {
                    classes("project-card")
                    style {
                        backgroundColor(Color.white)
                        borderRadius(15.px)
                        property("overflow", "hidden")
                        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
                        property("transition", "transform 0.3s ease")
                        border(1.px, LineStyle.Solid, Color("#e0e0e0"))
                    }
                }) {
                    project.image?.let { image ->
                        Div(attrs = {
                            classes("project-image")
                            style {
                                width(100.percent)
                                height(200.px)
                                backgroundColor(Color("#f0f0f0"))
                                property("overflow", "hidden")
                            }
                        }) {
                            Img(src = image, alt = project.name) {
                                style {
                                    width(100.percent)
                                    height(100.percent)
                                    property("object-fit", "cover")
                                }
                            }
                        }
                    }

                    Div(attrs = {
                        classes("project-content")
                        style {
                            padding(25.px)
                        }
                    }) {
                        H3(attrs = {
                            style {
                                fontSize(1.5.cssRem)
                                marginBottom(15.px)
                                color(Color("#2c3e50"))
                            }
                        }) {
                            Text(project.name)
                        }

                        P(attrs = {
                            style {
                                marginBottom(15.px)
                                lineHeight(1.6.number)
                                color(Color("#555"))
                                fontSize(14.px)
                            }
                        }) {
                            Text(project.description)
                        }

                        project.highlights?.let { highlights ->
                            Ul(attrs = {
                                style {
                                    marginBottom(15.px)
                                    paddingLeft(20.px)
                                    property("list-style", "disc")
                                }
                            }) {
                                highlights.forEach { highlight ->
                                    Li(attrs = {
                                        style {
                                            marginBottom(5.px)
                                            fontSize(14.px)
                                            color(Color("#666"))
                                        }
                                    }) {
                                        Text(highlight)
                                    }
                                }
                            }
                        }

                        Div(attrs = {
                            classes("project-technologies")
                            style {
                                display(DisplayStyle.Flex)
                                property("flex-wrap", "wrap")
                                gap(8.px)
                                marginBottom(15.px)
                            }
                        }) {
                            project.technologies.forEach { tech ->
                                Span(attrs = {
                                    style {
                                        backgroundColor(Color("#e8f4f8"))
                                        color(Color("#2196f3"))
                                        padding(5.px, 12.px)
                                        borderRadius(12.px)
                                        fontSize(12.px)
                                        fontWeight(500)
                                    }
                                }) {
                                    Text(tech)
                                }
                            }
                        }

                        Div(attrs = {
                            classes("project-links")
                            style {
                                display(DisplayStyle.Flex)
                                gap(15.px)
                                property("flex-wrap", "wrap")
                            }
                        }) {
                            project.link?.let { link ->
                                A(href = link, attrs = {
                                    classes("project-link")
                                    attr("target", "_blank")
                                    attr("rel", "noopener noreferrer")
                                    style {
                                        display(DisplayStyle.Flex)
                                        alignItems(AlignItems.Center)
                                        gap(8.px)
                                        padding(8.px, 16.px)
                                        backgroundColor(Color("#3498db"))
                                        color(Color.white)
                                        borderRadius(20.px)
                                        textDecoration("none")
                                        fontSize(14.px)
                                        property("transition", "background-color 0.3s ease")
                                    }
                                }) {
                                    I(attrs = { classes("fas", "fa-external-link-alt") })
                                    Text("View Project")
                                }
                            }

                            project.playStoreLink?.let { playStoreLink ->
                                A(href = playStoreLink, attrs = {
                                    classes("playstore-link")
                                    attr("target", "_blank")
                                    attr("rel", "noopener noreferrer")
                                    style {
                                        display(DisplayStyle.Flex)
                                        alignItems(AlignItems.Center)
                                        gap(8.px)
                                        padding(8.px, 16.px)
                                        backgroundColor(Color("#4caf50"))
                                        color(Color.white)
                                        borderRadius(20.px)
                                        textDecoration("none")
                                        fontSize(14.px)
                                        property("transition", "background-color 0.3s ease")
                                    }
                                }) {
                                    I(attrs = { classes("fab", "fa-google-play") })
                                    Text("Play Store")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

