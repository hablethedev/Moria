pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        maven {
            name = "Paper Maven"
            url = uri("https://repo.papermc.io/repository/maven-public")
        }
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public")
        maven("https://maven.fabricmc.net")
        maven("https://libraries.minecraft.net")
    }
}

include("core")
include("paper")
include("fabric")

rootProject.name = "MoriaMP"