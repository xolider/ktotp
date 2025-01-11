buildscript {
    dependencies {
        classpath(libs.semantic.version)
    }
}

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
    `java-library`
    `maven-publish`
}

group = "dev.vicart"
version = "1.0.0-SNAPSHOT"

apply {
    plugin(libs.plugins.semantic.version.get().pluginId)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("ktotp") {
            artifactId = "ktotp"
        }
    }
}
