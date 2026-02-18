package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun ContactSection(contact: Contact) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    var submitStatus by remember { mutableStateOf<String?>(null) }

    val scope = MainScope()

    Style {
        type(".contact-link:hover") style {
            color(Color("#3498db"))
            textDecoration("underline")
        }
        type(".social-link:hover") style {
            property("transform", "translateY(-3px) scale(1.1)")
            property("box-shadow", "0 5px 15px rgba(52,152,219,0.4)")
        }
        type(".submit-btn:hover:not(:disabled)") style {
            backgroundColor(Color("#2980b9"))
            property("transform", "translateY(-2px)")
            property("box-shadow", "0 5px 15px rgba(52,152,219,0.3)")
        }
        type(".footer-social:hover") style {
            backgroundColor(Color("#3498db"))
            property("transform", "translateY(-3px)")
        }
    }

    Section(attrs = {
        id("contact")
        classes("contact")
        attr("data-aos", "fade-up")
        style {
            backgroundColor(Color.white)
        }
    }) {
        H2 { Text(contact.title) }

        P(attrs = {
            style {
                textAlign("center")
                marginBottom(40.px)
                fontSize(1.1.cssRem)
                color(Color("#555"))
                property("max-width", "600px")
                property("margin-left", "auto")
                property("margin-right", "auto")
            }
        }) {
            Text(contact.description)
        }

        Div(attrs = {
            classes("contact-container")
            style {
                display(DisplayStyle.Grid)
                property("grid-template-columns", "1fr 1fr")
                gap(50.px)
                property("max-width", "1000px")
                property("margin-left", "auto")
                property("margin-right", "auto")
            }
        }) {
            // Contact Form
            Form(attrs = {
                id("contactForm")
                classes("contact-form")
                style {
                    backgroundColor(Color("#f8f9fa"))
                    padding(40.px)
                    borderRadius(15.px)
                    property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
                }

                onSubmit { event ->
                    event.preventDefault()
                    if (name.isNotBlank() && email.isNotBlank() && message.isNotBlank()) {
                        isSubmitting = true
                        submitStatus = null

                        scope.launch {
                            val success = submitContactForm(name, email, message)
                            isSubmitting = false

                            if (success) {
                                submitStatus = "success"
                                name = ""
                                email = ""
                                message = ""
                            } else {
                                submitStatus = "error"
                            }
                        }
                    }
                }
            }) {
                Div(attrs = {
                    classes("form-group")
                    style { marginBottom(20.px) }
                }) {
                    Label(forId = "name", attrs = {
                        style {
                            display(DisplayStyle.Block)
                            marginBottom(8.px)
                            fontWeight(600)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text("Name")
                    }
                    Input(type = InputType.Text) {
                        id("name")
                        name("name")
                        required()
                        value(name)
                        onInput { event -> name = event.value }
                        style {
                            width(100.percent)
                            padding(12.px)
                            border(1.px, LineStyle.Solid, Color("#ddd"))
                            borderRadius(8.px)
                            fontSize(16.px)
                            property("transition", "border-color 0.3s ease")
                        }
                    }
                }

                Div(attrs = {
                    classes("form-group")
                    style { marginBottom(20.px) }
                }) {
                    Label(forId = "email", attrs = {
                        style {
                            display(DisplayStyle.Block)
                            marginBottom(8.px)
                            fontWeight(600)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text("Email")
                    }
                    Input(type = InputType.Email) {
                        id("email")
                        name("email")
                        required()
                        value(email)
                        onInput { event -> email = event.value }
                        style {
                            width(100.percent)
                            padding(12.px)
                            border(1.px, LineStyle.Solid, Color("#ddd"))
                            borderRadius(8.px)
                            fontSize(16.px)
                            property("transition", "border-color 0.3s ease")
                        }
                    }
                }

                Div(attrs = {
                    classes("form-group")
                    style { marginBottom(20.px) }
                }) {
                    Label(forId = "message", attrs = {
                        style {
                            display(DisplayStyle.Block)
                            marginBottom(8.px)
                            fontWeight(600)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text("Message")
                    }
                    TextArea(attrs = {
                        id("message")
                        name("message")
                        required()
                        value(message)
                        onInput { event -> message = event.value }
                        style {
                            width(100.percent)
                            padding(12.px)
                            border(1.px, LineStyle.Solid, Color("#ddd"))
                            borderRadius(8.px)
                            fontSize(16.px)
                            minHeight(150.px)
                            property("resize", "vertical")
                            property("font-family", "inherit")
                            property("transition", "border-color 0.3s ease")
                        }
                    })
                }

                Button(attrs = {
                    classes("submit-btn")
                    type(ButtonType.Submit)
                    if (isSubmitting) {
                        disabled()
                    }
                    style {
                        width(100.percent)
                        padding(15.px)
                        backgroundColor(Color("#3498db"))
                        color(Color.white)
                        border(0.px)
                        borderRadius(8.px)
                        fontSize(16.px)
                        fontWeight(600)
                        cursor("pointer")
                        property("transition", "all 0.3s ease")

                        if (isSubmitting) {
                            opacity(0.7)
                            property("cursor", "not-allowed")
                        }
                    }
                }) {
                    Text(if (isSubmitting) "Sending..." else "Send Message")
                }

                submitStatus?.let { status ->
                    Div(attrs = {
                        id("formStatus")
                        style {
                            marginTop(20.px)
                            padding(15.px)
                            borderRadius(8.px)
                            textAlign("center")

                            if (status == "success") {
                                backgroundColor(Color("#d4edda"))
                                color(Color("#155724"))
                            } else {
                                backgroundColor(Color("#f8d7da"))
                                color(Color("#721c24"))
                            }
                        }
                    }) {
                        Text(
                            if (status == "success")
                                "✓ Message sent successfully! I'll get back to you soon."
                            else
                                "✗ Failed to send message. Please try again or email me directly."
                        )
                    }
                }
            }

            // Contact Info & Social Links
            Div(attrs = {
                classes("contact-info-section")
                style {
                    display(DisplayStyle.Flex)
                    property("flex-direction", "column")
                    gap(30.px)
                }
            }) {
                Div(attrs = {
                    classes("contact-details")
                    style {
                        backgroundColor(Color("#f8f9fa"))
                        padding(30.px)
                        borderRadius(15.px)
                        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
                    }
                }) {
                    H3(attrs = {
                        style {
                            fontSize(1.5.cssRem)
                            marginBottom(20.px)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text("Get In Touch")
                    }

                    Div(attrs = {
                        style {
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.Center)
                            gap(15.px)
                            marginBottom(20.px)
                        }
                    }) {
                        I(attrs = {
                            classes("fas", "fa-envelope")
                            style {
                                fontSize(24.px)
                                color(Color("#3498db"))
                            }
                        })
                        A(href = "mailto:${contact.email}", attrs = {
                            classes("contact-link")
                            style {
                                color(Color("#2c3e50"))
                                textDecoration("none")
                                fontSize(16.px)
                                property("transition", "color 0.3s ease")
                            }
                        }) {
                            Text(contact.email)
                        }
                    }

                    H4(attrs = {
                        style {
                            fontSize(1.2.cssRem)
                            marginTop(30.px)
                            marginBottom(15.px)
                            color(Color("#2c3e50"))
                        }
                    }) {
                        Text("Connect With Me")
                    }

                    Div(attrs = {
                        classes("social-links")
                        style {
                            display(DisplayStyle.Flex)
                            property("flex-wrap", "wrap")
                            gap(15.px)
                        }
                    }) {
                        contact.socialLinks.forEach { social ->
                            A(href = social.url, attrs = {
                                classes("social-link")
                                attr("target", "_blank")
                                attr("rel", "noopener noreferrer")
                                style {
                                    display(DisplayStyle.Flex)
                                    alignItems(AlignItems.Center)
                                    justifyContent(JustifyContent.Center)
                                    width(50.px)
                                    height(50.px)
                                    borderRadius(50.percent)
                                    backgroundColor(Color("#3498db"))
                                    color(Color.white)
                                    fontSize(20.px)
                                    textDecoration("none")
                                    property("transition", "all 0.3s ease")
                                }
                            }) {
                                I(attrs = { classes(social.icon) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Footer(footer: Footer) {
    Footer(attrs = {
        style {
            backgroundColor(Color("#2c3e50"))
            color(Color.white)
            textAlign("center")
            padding(40.px, 20.px)
        }
    }) {
        Div(attrs = {
            classes("social-links")
            style {
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.Center)
                gap(20.px)
                marginBottom(20.px)
            }
        }) {
            footer.socialLinks.forEach { social ->
                A(href = social.url, attrs = {
                    classes("footer-social")
                    attr("target", "_blank")
                    attr("rel", "noopener noreferrer")
                    style {
                        display(DisplayStyle.Flex)
                        alignItems(AlignItems.Center)
                        justifyContent(JustifyContent.Center)
                        width(45.px)
                        height(45.px)
                        borderRadius(50.percent)
                        backgroundColor(Color("rgba(255,255,255,0.1)"))
                        color(Color.white)
                        fontSize(20.px)
                        textDecoration("none")
                        property("transition", "all 0.3s ease")
                    }
                }) {
                    I(attrs = { classes(social.icon) })
                }
            }
        }

        P(attrs = {
            style {
                fontSize(14.px)
                color(Color("rgba(255,255,255,0.8)"))
            }
        }) {
            Text(footer.copyright)
        }
    }
}

