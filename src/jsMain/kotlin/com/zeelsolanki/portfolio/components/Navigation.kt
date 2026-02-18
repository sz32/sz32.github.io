package com.zeelsolanki.portfolio

import androidx.compose.runtime.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlinx.browser.window

@Composable
fun VerticalNavigation(navItems: List<NavigationItem>) {
    var activeSection by remember { mutableStateOf("profile") }
    var isMobileMenuOpen by remember { mutableStateOf(false) }

    Style {
        type(".nav-item:hover") style {
            backgroundColor(Color("#3498db"))
        }
        type(".mobile-menu-toggle") style {
            display(DisplayStyle.None)
        }
        type("@media (max-width: 768px)") style {
            type(".vertical-nav") style {
                width(100.percent)
                height(60.px)
                property("flex-direction", "row")
                justifyContent(JustifyContent.SpaceBetween)
                paddingLeft(20.px)
                paddingRight(20.px)
                property("padding-top", "0")
            }
            type(".mobile-menu-toggle") style {
                display(DisplayStyle.Block)
            }
        }
    }

    Nav(attrs = {
        classes("vertical-nav")
        style {
            position(Position.Fixed)
            left(0.px)
            top(0.px)
            height(100.vh)
            width(80.px)
            backgroundColor(Color("#2c3e50"))
            display(DisplayStyle.Flex)
            property("flex-direction", "column")
            alignItems(AlignItems.Center)
            paddingTop(20.px)
            property("z-index", "1000")
        }
    }) {
        Ul(attrs = {
            style {
                property("list-style", "none")
                display(DisplayStyle.Flex)
                property("flex-direction", "column")
                gap(20.px)
            }
        }) {
            navItems.forEachIndexed { index, item ->
                Li {
                    A(href = "#${item.id}", attrs = {
                        classes("nav-item")
                        if (index == 0 && activeSection == "profile") classes("active")

                        onClick { event ->
                            event.preventDefault()
                            activeSection = item.id
                            scrollToSection(item.id)
                            isMobileMenuOpen = false
                        }

                        style {
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.Center)
                            justifyContent(JustifyContent.Center)
                            width(50.px)
                            height(50.px)
                            borderRadius(50.percent)
                            color(Color.white)
                            textDecoration("none")
                            property("transition", "all 0.3s ease")
                            position(Position.Relative)

                            if (activeSection == item.id) {
                                backgroundColor(Color("#3498db"))
                            } else {
                                backgroundColor(Color("transparent"))
                            }
                        }
                    }) {
                        I(attrs = {
                            classes(item.icon)
                            style { fontSize(20.px) }
                        })

                        Span(attrs = {
                            classes("tooltip")
                            style {
                                position(Position.Absolute)
                                left(70.px)
                                backgroundColor(Color("#2c3e50"))
                                color(Color.white)
                                padding(8.px, 15.px)
                                borderRadius(5.px)
                                property("white-space", "nowrap")
                                opacity(0)
                                property("pointer-events", "none")
                                property("transition", "opacity 0.3s ease")
                            }
                        }) {
                            Text(item.label)
                        }
                    }
                }
            }
        }

        // Mobile menu toggle
        Button(attrs = {
            classes("mobile-menu-toggle")
            onClick { isMobileMenuOpen = !isMobileMenuOpen }
            style {
                display(DisplayStyle.None)
                backgroundColor(Color("transparent"))
                border(0.px)
                color(Color.white)
                fontSize(24.px)
                cursor("pointer")
            }
        }) {
            I(attrs = { classes("fas", if (isMobileMenuOpen) "fa-times" else "fa-bars") })
        }
    }
}

private fun scrollToSection(sectionId: String) {
    val element = window.document.getElementById(sectionId)
    element?.scrollIntoView(js("{behavior: 'smooth', block: 'start'}"))
}

