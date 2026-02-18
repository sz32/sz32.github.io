package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun ProfileSection(profile: Profile) {
    Style {
        type(".download-resume-btn:hover") style {
            property("transform", "translateY(-3px)")
            property("box-shadow", "0 8px 25px rgba(0,0,0,0.3)")
        }
    }

    Section(attrs = {
        id("profile")
        classes("hero")
        attr("data-aos", "fade-down")
        style {
            display(DisplayStyle.Flex)
            property("flex-direction", "column")
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.Center)
            minHeight(100.vh)
            textAlign("center")
            property("background", "linear-gradient(135deg, #667eea 0%, #764ba2 100%)")
            color(Color.white)
            padding(80.px, 20.px)
        }
    }) {
        Div(attrs = {
            classes("hero-content")
            style {
                property("max-width", "800px")
                margin(0.px)
                property("margin-left", "auto")
                property("margin-right", "auto")
            }
        }) {
            // Profile Image
            Div(attrs = {
                classes("profile-image")
                attr("data-aos", "zoom-in")
                style {
                    width(200.px)
                    height(200.px)
                    borderRadius(50.percent)
                    property("overflow", "hidden")
                    property("margin-left", "auto")
                    property("margin-right", "auto")
                    marginBottom(30.px)
                    border(5.px, LineStyle.Solid, Color.white)
                    property("box-shadow", "0 10px 30px rgba(0,0,0,0.3)")
                }
            }) {
                Img(src = profile.profileImage, alt = profile.name) {
                    style {
                        width(100.percent)
                        height(100.percent)
                        property("object-fit", "cover")
                    }
                }
            }

            // Name
            H1(attrs = {
                style {
                    fontSize(3.5.cssRem)
                    marginBottom(10.px)
                    fontWeight(700)
                }
            }) {
                Text(profile.name)
            }

            // Title
            H2(attrs = {
                style {
                    fontSize(2.cssRem)
                    marginBottom(20.px)
                    fontWeight(400)
                    opacity(0.9)
                }
            }) {
                Text(profile.title)
            }

            // Typed Roles
            Div(attrs = {
                classes("typed-text")
                attr("data-aos", "fade-up")
                attr("data-aos-delay", "300")
                style {
                    fontSize(1.5.cssRem)
                    marginBottom(30.px)
                    minHeight(40.px)
                }
            }) {
                Span(attrs = { id("typed") }) {
                    Text(profile.typedRoles.joinToString(" | "))
                }
            }

            // Contact Info
            Div(attrs = {
                classes("contact-info")
                style {
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                    gap(30.px)
                    marginBottom(30.px)
                    property("flex-wrap", "wrap")
                }
            }) {
                profile.contactInfo.forEach { info ->
                    Div(attrs = {
                        style {
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.Center)
                            gap(10.px)
                        }
                    }) {
                        I(attrs = {
                            classes(info.icon)
                            style { fontSize(18.px) }
                        })
                        Span { Text(info.value) }
                    }
                }
            }

            // Description
            P(attrs = {
                style {
                    fontSize(1.1.cssRem)
                    lineHeight(1.8.number)
                    marginBottom(30.px)
                    opacity(0.95)
                }
            }) {
                Text(profile.description)
            }

            // Resume Download Button
            profile.resumeUrl?.let { resumeUrl ->
                Div(attrs = {
                    classes("resume-download")
                    style { marginTop(30.px) }
                }) {
                    A(href = resumeUrl, attrs = {
                        classes("download-resume-btn")
                        attr("target", "_blank")
                        attr("rel", "noopener noreferrer")
                        style {
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.Center)
                            gap(10.px)
                            padding(15.px, 40.px)
                            backgroundColor(Color.white)
                            color(Color("#667eea"))
                            borderRadius(50.px)
                            textDecoration("none")
                            fontSize(1.1.cssRem)
                            fontWeight(600)
                            property("transition", "all 0.3s ease")
                            property("box-shadow", "0 5px 15px rgba(0,0,0,0.2)")
                            property("margin-left", "auto")
                            property("margin-right", "auto")
                            property("width", "fit-content")
                        }
                    }) {
                        I(attrs = { classes("fas", "fa-download") })
                        Span { Text("Download Resume") }
                    }
                }
            }
        }
    }
}

