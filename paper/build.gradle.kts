import jdk.jfr.internal.JVM.include
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("buildlogic.java-conventions")
    alias(libs.plugins.shadow)
    alias(libs.plugins.kotlin.jvm)
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.24"
}

dependencies {
    implementation(project(":core"))
    //compileOnly("io.papermc.paper:paper-api:26.3.build.222-stable")
    implementation("org.incendo:cloud-paper:2.0.1")
    testImplementation(kotlin("test"))

    paperweight.paperDevBundle("26.3.build.+")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }

    jar {
        archiveClassifier.set("slim")
    }

    build {
        dependsOn(shadowJar)
    }
}
repositories {
    mavenCentral()
    maven {
        name = "Paper Maven"
        url = uri("https://repo.papermc.io/repository/maven-public")
    }
}
kotlin {
    jvmToolchain(25)
}