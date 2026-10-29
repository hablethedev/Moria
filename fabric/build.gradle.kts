plugins {
    id("buildlogic.java-conventions")
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.kotlin.jvm)
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${project.providers.gradleProperty("minecraft_version").get()}")
    implementation("net.fabricmc:fabric-loader:${project.providers.gradleProperty("fabric_loader_version").get()}")
    implementation("net.fabricmc.fabric-api:fabric-api:${project.providers.gradleProperty("fabric_api_version").get()}")
    implementation("net.fabricmc:fabric-language-kotlin:1.14.2+kotlin.2.4.21")

    val core = project(":core")
    implementation(core)
    include(core)

    val adventure = "net.kyori:adventure-platform-fabric:7.2.0"
    implementation(adventure)
    include(adventure)

    include("me.lucko:fabric-permissions-api:${project.providers.gradleProperty("fabric_permissions_version").get()}")
    implementation(
        "me.lucko:fabric-permissions-api:${
            project.providers.gradleProperty("fabric_permissions_version").get()
        }"
    )

    val cloudFabric = "org.incendo:cloud-fabric:2.0.0"
    include(cloudFabric)
    implementation(cloudFabric)

    val ktoml = "com.akuleshov7:ktoml-core:0.7.1"

    implementation(ktoml)
    include(ktoml)

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}