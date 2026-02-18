package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun EducationSection(education: Education) {
    Style {
        type(".education-card:hover") style {
            property("box-shadow", "0 8px 25px rgba(0,0,0,0.12)")
        }
    }

    Section(attrs = {
        id("education")
        classes("education")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color.white)
        }
    }) {
        H2 { Text(education.title) }

        Div(attrs = {
            classes("education-list")
            style {
                property("max-width", "900px")
                property("margin-left", "auto")
                property("margin-right", "auto")
                marginTop(40.px)
            }
        }) {
            education.items.forEach { item ->
                Div(attrs = {
                    classes("education-card")
                    style {
                        backgroundColor(Color("#f8f9fa"))
                        padding(30.px)
                        borderRadius(15.px)
                        property("box-shadow", "0 5px 15px rgba(0,0,0,0.08)")
                        marginBottom(25.px)
                    }
                }) {
                    H3(attrs = {
                        style {
                            fontSize(1.6.cssRem)
                            marginBottom(10.px)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text(item.degree)
                    }

                    H4(attrs = {
                        style {
                            fontSize(1.2.cssRem)
                            marginBottom(10.px)
                            color(Color("#3498db"))
                            fontWeight(600)
                        }
                    }) {
                        Text(item.institution)
                    }

                    Div(attrs = {
                        classes("education-meta")
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
                            Text(item.duration)
                        }

                        Span(attrs = {
                            style {
                                display(DisplayStyle.Flex)
                                alignItems(AlignItems.Center)
                                gap(8.px)
                            }
                        }) {
                            I(attrs = { classes("fas", "fa-map-marker-alt") })
                            Text(item.location)
                        }

                        item.grade?.let { grade ->
                            Span(attrs = {
                                style {
                                    display(DisplayStyle.Flex)
                                    alignItems(AlignItems.Center)
                                    gap(8.px)
                                }
                            }) {
                                I(attrs = { classes("fas", "fa-award") })
                                Text(grade)
                            }
                        }
                    }

                    item.description?.let { desc ->
                        P(attrs = {
                            style {
                                lineHeight(1.6.number)
                                color(Color("#555"))
                                fontSize(14.px)
                            }
                        }) {
                            Text(desc)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WorkProcessSection(workProcess: WorkProcess) {
    Style {
        type(".process-card:hover") style {
            property("transform", "translateY(-5px)")
            property("box-shadow", "0 10px 30px rgba(0,0,0,0.15)")
        }
    }

    Section(attrs = {
        id("work-process")
        classes("work-process")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color("#f8f9fa"))
        }
    }) {
        H2 { Text(workProcess.title) }

        Div(attrs = {
            classes("process-grid")
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "repeat(auto-fit, minmax(250px, 1fr))")
                gap(30.px)
                marginTop(40.px)
            }
        }) {
            workProcess.steps.forEachIndexed { index, step ->
                Div(attrs = {
                    classes("process-card")
                    style {
                        backgroundColor(Color.white)
                        padding(30.px)
                        borderRadius(15.px)
                        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
                        textAlign("center")
                        position(Position.Relative)
                        property("transition", "transform 0.3s ease")
                        paddingTop(40.px)
                    }
                }) {
                    Div(attrs = {
                        classes("step-number")
                        style {
                            position(Position.Absolute)
                            top(-15.px)
                            left(50.percent)
                            property("transform", "translateX(-50%)")
                            width(40.px)
                            height(40.px)
                            backgroundColor(Color("#3498db"))
                            color(Color.white)
                            borderRadius(50.percent)
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.Center)
                            justifyContent(JustifyContent.Center)
                            fontSize(18.px)
                            fontWeight(700)
                            property("box-shadow", "0 3px 10px rgba(52,152,219,0.3)")
                        }
                    }) {
                        Text("${index + 1}")
                    }

                    I(attrs = {
                        classes(step.icon)
                        style {
                            fontSize(48.px)
                            color(Color("#3498db"))
                            marginTop(20.px)
                            marginBottom(20.px)
                            display(DisplayStyle.Block)
                        }
                    })

                    H3(attrs = {
                        style {
                            fontSize(1.4.cssRem)
                            marginBottom(15.px)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text(step.title)
                    }

                    P(attrs = {
                        style {
                            lineHeight(1.6.number)
                            color(Color("#555"))
                            fontSize(14.px)
                        }
                    }) {
                        Text(step.description)
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticsSection(statistics: Statistics) {
    Style {
        type(".stat-card:hover") style {
            backgroundColor(Color("rgba(255,255,255,0.15)"))
            property("transform", "scale(1.05)")
        }
    }

    Section(attrs = {
        id("statistics")
        classes("statistics")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color("#2c3e50"))
            color(Color.white)
        }
    }) {
        H2(attrs = {
            style {
                color(Color.white)
            }
        }) {
            Text(statistics.title)
        }

        Div(attrs = {
            classes("stats-grid")
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "repeat(auto-fit, minmax(200px, 1fr))")
                gap(40.px)
                marginTop(40.px)
            }
        }) {
            statistics.stats.forEach { stat ->
                Div(attrs = {
                    classes("stat-card")
                    style {
                        textAlign("center")
                        padding(30.px)
                        backgroundColor(Color("rgba(255,255,255,0.1)"))
                        borderRadius(15.px)
                        property("transition", "all 0.3s ease")
                    }
                }) {
                    I(attrs = {
                        classes(stat.icon)
                        style {
                            fontSize(48.px)
                            marginBottom(15.px)
                            display(DisplayStyle.Block)
                            color(Color("#3498db"))
                        }
                    })

                    H3(attrs = {
                        style {
                            fontSize(2.5.cssRem)
                            marginBottom(10.px)
                            color(Color.white)
                            fontWeight(700)
                        }
                    }) {
                        Text(stat.value)
                    }

                    P(attrs = {
                        style {
                            fontSize(1.1.cssRem)
                            color(Color("rgba(255,255,255,0.8)"))
                        }
                    }) {
                        Text(stat.label)
                    }
                }
            }
        }
    }
}

