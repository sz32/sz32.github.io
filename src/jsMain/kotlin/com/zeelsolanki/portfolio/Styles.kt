package com.zeelsolanki.portfolio

import org.jetbrains.compose.web.css.*

object AppStyles : StyleSheet() {
    init {
        // Global Styles
        "*" style {
            margin(0.px)
            padding(0.px)
            property("box-sizing", "border-box")
        }

        "body" style {
            fontFamily("Poppins", "sans-serif")
            property("line-height", "1.6")
            color(Color("#333"))
            backgroundColor(Color("#f8f9fa"))
        }

        "section" style {
            padding(80.px, 20.px)
            property("max-width", "1200px")
            property("margin-left", "auto")
            property("margin-right", "auto")
        }

        "h1, h2, h3" style {
            color(Color("#2c3e50"))
        }

        "h2" style {
            fontSize(2.5.cssRem)
            marginBottom(40.px)
            textAlign("center")
            position(Position.Relative)
        }

        ".portfolio-container" style {
            property("min-height", "100vh")
        }

        ".main-content" style {
            property("margin-left", "80px")
        }
    }

    val hero by style {
        display(DisplayStyle.Flex)
        property("flex-direction", "column")
        alignItems(AlignItems.Center)
        justifyContent(JustifyContent.Center)
        minHeight(100.vh)
        textAlign("center")
        property("background", "linear-gradient(135deg, #667eea 0%, #764ba2 100%)")
        color(Color.white)
    }

    val profileImage by style {
        width(200.px)
        height(200.px)
        borderRadius(50.percent)
        property("overflow", "hidden")
        marginBottom(30.px)
        border(5.px, LineStyle.Solid, Color.white)
        property("box-shadow", "0 10px 30px rgba(0,0,0,0.3)")
    }

    val skillsGrid by style {
        display(DisplayStyle.Grid)
        property("grid-template-columns", "repeat(auto-fit, minmax(250px, 1fr))")
        gap(30.px)
        marginTop(40.px)
    }

    val skillCard by style {
        backgroundColor(Color.white)
        padding(30.px)
        borderRadius(15.px)
        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
        property("transition", "transform 0.3s ease")
    }

    val experienceTimeline by style {
        position(Position.Relative)
        paddingLeft(50.px)
    }

    val projectCard by style {
        backgroundColor(Color.white)
        borderRadius(15.px)
        property("overflow", "hidden")
        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
        marginBottom(30.px)
        property("transition", "transform 0.3s ease")
    }

    val contactForm by style {
        property("max-width", "600px")
        property("margin-left", "auto")
        property("margin-right", "auto")
        backgroundColor(Color.white)
        padding(40.px)
        borderRadius(15.px)
        property("box-shadow", "0 5px 15px rgba(0,0,0,0.1)")
    }

    val button by style {
        padding(12.px, 30.px)
        backgroundColor(Color("#3498db"))
        color(Color.white)
        border(0.px)
        borderRadius(25.px)
        fontSize(16.px)
        fontWeight(600)
        cursor("pointer")
        property("transition", "all 0.3s ease")
    }

    val socialLinks by style {
        display(DisplayStyle.Flex)
        gap(20.px)
        justifyContent(JustifyContent.Center)
        marginTop(30.px)
    }

    val socialIcon by style {
        width(50.px)
        height(50.px)
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        justifyContent(JustifyContent.Center)
        borderRadius(50.percent)
        backgroundColor(Color("rgba(255,255,255,0.1)"))
        color(Color.white)
        fontSize(24.px)
        property("transition", "all 0.3s ease")
        textDecoration("none")
    }
}

