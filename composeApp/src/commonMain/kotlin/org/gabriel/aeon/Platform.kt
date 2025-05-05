package org.gabriel.aeon

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform