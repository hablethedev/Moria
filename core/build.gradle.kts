import org.gradle.kotlin.dsl.dependencies

plugins {
    id("buildlogic.java-conventions")
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api("net.kyori:adventure-api:5.2.0")
    api("net.kyori:adventure-text-minimessage:5.2.0")
    api("org.incendo:cloud-core:2.0.0")
}