package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ExperienceSection(experience: Experience) {
    Section(attrs = {
        id("experience")
        classes("experience")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color("#f8f9fa"))
        }
    }) {
        H2 { Text(experience.title) }

        Div(attrs = {
            classes("experience-timeline")
            style {
                position(Position.Relative)
                paddingLeft(50.px)
                marginTop(40.px)
            }
        }) {
            // Timeline line
            Div(attrs = {
                style {
                    position(Position.Absolute)
                    left(15.px)
                    top(0.px)
                    bottom(0.px)
                    width(2.px)
                    backgroundColor(Color("#3498db"))
                }
            })

            experience.jobs.forEach { job ->
                Div(attrs = {
                    classes("job-card")
                    style {
                        position(Position.Relative)
                        backgroundColor(Color.white)
                        padding(30.px)
                        borderRadius(15.px)
                        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
                        marginBottom(30.px)
                    }
                }) {
                    // Timeline dot
                    Div(attrs = {
                        style {
                            position(Position.Absolute)
                            left(-50.px)
                            top(30.px)
                            width(12.px)
                            height(12.px)
                            borderRadius(50.percent)
                            backgroundColor(Color("#3498db"))
                            border(3.px, LineStyle.Solid, Color.white)
                            property("box-shadow", "0 0 0 3px #3498db")
                        }
                    })

                    H3(attrs = {
                        style {
                            fontSize(1.8.cssRem)
                            marginBottom(10.px)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text(job.position)
                    }

                    H4(attrs = {
                        style {
                            fontSize(1.3.cssRem)
                            marginBottom(10.px)
                            color(Color("#3498db"))
                            fontWeight(600)
                        }
                    }) {
                        Text(job.company)
                    }

                    Div(attrs = {
                        classes("job-meta")
                        style {
                            display(DisplayStyle.Flex)
                            gap(20.px)
                            marginBottom(15.px)
                            property("flex-wrap", "wrap")
                            fontSize(14.px)
                            color(Color("#7f8c8d"))
                        }
                    }) {
                        Span(attrs = {
                            style {
                                display(DisplayStyle.Flex)
                                alignItems(AlignItems.Center)
                                gap(8.px)
                            }
                        }) {
                            I(attrs = { classes("fas", "fa-calendar-alt") })
                            Text(job.duration)
                        }

                        Span(attrs = {
                            style {
                                display(DisplayStyle.Flex)
                                alignItems(AlignItems.Center)
                                gap(8.px)
                            }
                        }) {
                            I(attrs = { classes("fas", "fa-map-marker-alt") })
                            Text(job.location)
                        }
                    }

                    job.description?.let { desc ->
                        P(attrs = {
                            style {
                                marginBottom(15.px)
                                lineHeight(1.6.number)
                                color(Color("#555"))
                            }
                        }) {
                            Text(desc)
                        }
                    }

                    job.achievements?.let { achievements ->
                        H5(attrs = {
                            style {
                                fontSize(1.1.cssRem)
                                marginTop(15.px)
                                marginBottom(10.px)
                                color(Color("#2c3e50"))
                            }
                        }) {
                            Text("Key Achievements:")
                        }

                        Ul(attrs = {
                            style {
                                paddingLeft(20.px)
                                property("list-style", "disc")
                            }
                        }) {
                            achievements.forEach { achievement ->
                                Li(attrs = {
                                    style {
                                        marginBottom(8.px)
                                        lineHeight(1.6.number)
                                        color(Color("#555"))
                                    }
                                }) {
                                    Text(achievement)
                                }
                            }
                        }
                    }

                    job.skills?.let { skills ->
                        Div(attrs = {
                            classes("job-skills")
                            style {
                                display(DisplayStyle.Flex)
                                property("flex-wrap", "wrap")
                                gap(10.px)
                                marginTop(15.px)
                            }
                        }) {
                            skills.forEach { skill ->
                                Span(attrs = {
                                    style {
                                        backgroundColor(Color("#e3f2fd"))
                                        color(Color("#1976d2"))
                                        padding(6.px, 12.px)
                                        borderRadius(15.px)
                                        fontSize(13.px)
                                    }
                                }) {
                                    Text(skill)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

