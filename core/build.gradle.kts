import org.gradle.kotlin.dsl.dependencies

plugins {
    id("buildlogic.java-conventions")
    alias(libs.plugins.kotlin.jvm)
    kotlin("plugin.serialization") version "2.4.10"
}

dependencies {
    api("net.kyori:adventure-api:5.2.0")
    api("net.kyori:adventure-text-minimessage:5.2.0")
    api("org.incendo:cloud-core:2.0.0")

    api("com.akuleshov7:ktoml-core:0.7.1")
}