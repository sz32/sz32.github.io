package com.zeelsolanki.portfolio

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import kotlin.js.Promise

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

suspend fun loadPortfolioData(): PortfolioData {
    val response = window.fetch("portfolio-data.json").await()
    val text = response.text().await()
    return json.decodeFromString(PortfolioData.serializer(), text)
}

suspend fun submitContactForm(name: String, email: String, message: String): Boolean {
    return try {
        // Simple form submission - for now just return true
        // In production, you would implement proper API call here
        console.log("Submitting form for: $name, $email")
        true
    } catch (e: Exception) {
        console.error("Error submitting form", e)
        false
    }
}


