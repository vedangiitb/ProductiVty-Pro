package com.example.productivtypro.data

import java.util.Random

val motivationalQuotes = listOf(
    "The only way to do great work is to love what you do." to "- Steve Jobs",
    "Believe you can and you're halfway there." to "- Theodore Roosevelt",
    "Don't watch the clock; do what it does. Keep going." to "- Sam Levenson",
    "Your time is limited, don't waste it living someone else's life." to "- Steve Jobs",
    "Success is not final, failure is not fatal: It is the courage to continue that counts." to "- Winston Churchill",
    "The only limit to our realization of tomorrow will be our doubts of today." to "- Franklin D. Roosevelt",
    "It's not whether you get knocked down, it's whether you get up." to "- Vince Lombardi",
    "The future belongs to those who believe in the beauty of their dreams." to "- Eleanor Roosevelt",
    "Success usually comes to those who are too busy to be looking for it." to "- Henry David Thoreau",
    "You are never too old to set another goal or to dream a new dream." to "- C.S. Lewis",
    "The only person you are destined to become is the person you decide to be." to "- Ralph Waldo Emerson",
    "Hard work beats talent when talent doesn't work hard." to "- Tim Notke",
    "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart." to "- Roy T. Bennett",
    "Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle." to "- Christian D. Larson",
    "The only limit to our realization of tomorrow will be our doubts of today." to "- Franklin D. Roosevelt",
    "The only way to achieve the impossible is to believe it is possible." to "- Charles Kingsleigh"
)

val authors = motivationalQuotes.map { it.second }
val random = Random()

fun getRandomQuote(): Pair<String, String> {
    val randomIndex = random.nextInt(motivationalQuotes.size)
    return motivationalQuotes[randomIndex]
}
