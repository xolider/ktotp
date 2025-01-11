plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
    `java-library`
    `maven-publish`
}

group = "dev.vicart"
version = "1.0-SNAPSHOT"

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