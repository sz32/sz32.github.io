package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun BlogSection(blog: Blog) {
    Style {
        type(".blog-card:hover") style {
            property("transform", "translateY(-5px)")
            property("box-shadow", "0 10px 30px rgba(0,0,0,0.15)")
        }
        type(".blog-link:hover") style {
            color(Color("#2980b9"))
            textDecoration("underline")
        }
    }

    Section(attrs = {
        id("blog")
        classes("blog-posts")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color("#f8f9fa"))
        }
    }) {
        H2 { Text(blog.title) }

        Div(attrs = {
            classes("blog-grid")
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "repeat(auto-fit, minmax(300px, 1fr))")
                gap(30.px)
                marginTop(40.px)
            }
        }) {
            blog.posts.forEach { post ->
                Article(attrs = {
                    classes("blog-card")
                    style {
                        backgroundColor(Color.white)
                        padding(30.px)
                        borderRadius(15.px)
                        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
                        property("transition", "transform 0.3s ease")
                        display(DisplayStyle.Flex)
                        property("flex-direction", "column")
                    }
                }) {
                    Div(attrs = {
                        classes("blog-meta")
                        style {
                            display(DisplayStyle.Flex)
                            justifyContent(JustifyContent.SpaceBetween)
                            alignItems(AlignItems.Center)
                            marginBottom(15.px)
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
                            Text(post.date)
                        }

                        Span(attrs = {
                            style {
                                backgroundColor(Color("#e3f2fd"))
                                color(Color("#1976d2"))
                                padding(4.px, 10.px)
                                borderRadius(12.px)
                                fontSize(12.px)
                            }
                        }) {
                            Text(post.platform)
                        }
                    }

                    H3(attrs = {
                        style {
                            fontSize(1.4.cssRem)
                            marginBottom(15.px)
                            color(Color("#2c3e50"))
                            lineHeight(1.4.number)
                        }
                    }) {
                        Text(post.title)
                    }

                    P(attrs = {
                        style {
                            marginBottom(15.px)
                            lineHeight(1.6.number)
                            color(Color("#555"))
                            fontSize(14.px)
                            property("flex-grow", "1")
                        }
                    }) {
                        Text(post.description)
                    }

                    post.tags?.let { tags ->
                        Div(attrs = {
                            classes("blog-tags")
                            style {
                                display(DisplayStyle.Flex)
                                property("flex-wrap", "wrap")
                                gap(8.px)
                                marginBottom(15.px)
                            }
                        }) {
                            tags.forEach { tag ->
                                Span(attrs = {
                                    style {
                                        backgroundColor(Color("#f0f0f0"))
                                        color(Color("#666"))
                                        padding(4.px, 10.px)
                                        borderRadius(10.px)
                                        fontSize(12.px)
                                    }
                                }) {
                                    Text("#$tag")
                                }
                            }
                        }
                    }

                    A(href = post.link, attrs = {
                        classes("blog-link")
                        attr("target", "_blank")
                        attr("rel", "noopener noreferrer")
                        style {
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.Center)
                            gap(8.px)
                            color(Color("#3498db"))
                            textDecoration("none")
                            fontWeight(600)
                            fontSize(14.px)
                            property("transition", "color 0.3s ease")
                        }
                    }) {
                        Text("Read More")
                        I(attrs = { classes("fas", "fa-arrow-right") })
                    }
                }
            }
        }
    }
}

@Composable
fun AchievementsSection(achievements: Achievements) {
    Style {
        type(".achievement-card:hover") style {
            property("transform", "translateY(-5px)")
            property("box-shadow", "0 10px 25px rgba(0,0,0,0.12)")
        }
    }

    Section(attrs = {
        id("achievements")
        classes("achievements")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color.white)
        }
    }) {
        H2 { Text(achievements.title) }

        Div(attrs = {
            classes("achievements-grid")
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "repeat(auto-fit, minmax(280px, 1fr))")
                gap(30.px)
                marginTop(40.px)
            }
        }) {
            achievements.items.forEach { achievement ->
                Div(attrs = {
                    classes("achievement-card")
                    style {
                        backgroundColor(Color("#fff9e6"))
                        padding(30.px)
                        borderRadius(15.px)
                        property("box-shadow", "0 5px 15px rgba(0,0,0,0.08)")
                        property("transition", "transform 0.3s ease")
                        textAlign("center")
                    }
                }) {
                    achievement.icon?.let { icon ->
                        I(attrs = {
                            classes(icon)
                            style {
                                fontSize(48.px)
                                color(Color("#f39c12"))
                                marginBottom(20.px)
                                display(DisplayStyle.Block)
                            }
                        })
                    }

                    H3(attrs = {
                        style {
                            fontSize(1.3.cssRem)
                            marginBottom(15.px)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text(achievement.title)
                    }

                    P(attrs = {
                        style {
                            lineHeight(1.6.number)
                            color(Color("#555"))
                            fontSize(14.px)
                        }
                    }) {
                        Text(achievement.description)
                    }
                }
            }
        }
    }
}

@Composable
fun CertificationsSection(certifications: Certifications) {
    Style {
        type(".certification-card:hover") style {
            property("box-shadow", "0 5px 20px rgba(0,0,0,0.12)")
        }
        type(".cert-view-btn:hover") style {
            backgroundColor(Color("#2980b9"))
        }
    }

    Section(attrs = {
        id("certifications")
        classes("certifications")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color("#f8f9fa"))
        }
    }) {
        H2 { Text(certifications.title) }

        Div(attrs = {
            classes("certifications-list")
            style {
                property("max-width", "900px")
                property("margin-left", "auto")
                property("margin-right", "auto")
                marginTop(40.px)
            }
        }) {
            certifications.items.forEach { cert ->
                Div(attrs = {
                    classes("certification-card")
                    style {
                        backgroundColor(Color.white)
                        padding(25.px)
                        borderRadius(12.px)
                        property("box-shadow", "0 3px 10px rgba(0,0,0,0.08)")
                        marginBottom(20.px)
                        display(DisplayStyle.Flex)
                        justifyContent(JustifyContent.SpaceBetween)
                        alignItems(AlignItems.Center)
                        property("flex-wrap", "wrap")
                        gap(15.px)
                    }
                }) {
                    Div(attrs = {
                        style {
                            property("flex", "1")
                            minWidth(200.px)
                        }
                    }) {
                        H3(attrs = {
                            style {
                                fontSize(1.2.cssRem)
                                marginBottom(8.px)
                                color(Color("#2c3e50"))
                            }
                        }) {
                            Text(cert.name)
                        }

                        P(attrs = {
                            style {
                                color(Color("#7f8c8d"))
                                fontSize(14.px)
                                marginBottom(5.px)
                            }
                        }) {
                            Text(cert.issuer)
                        }

                        P(attrs = {
                            style {
                                color(Color("#95a5a6"))
                                fontSize(13.px)
                            }
                        }) {
                            I(attrs = {
                                classes("fas", "fa-calendar-alt")
                                style { marginRight(5.px) }
                            })
                            Text(cert.date)
                        }
                    }

                    cert.link?.let { link ->
                        A(href = link, attrs = {
                            classes("cert-view-btn")
                            attr("target", "_blank")
                            attr("rel", "noopener noreferrer")
                            style {
                                padding(10.px, 20.px)
                                backgroundColor(Color("#3498db"))
                                color(Color.white)
                                borderRadius(20.px)
                                textDecoration("none")
                                fontSize(14.px)
                                fontWeight(600)
                                property("transition", "background-color 0.3s ease")
                            }
                        }) {
                            Text("View Certificate")
                        }
                    }
                }
            }
        }
    }
}

