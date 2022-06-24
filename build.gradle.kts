import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id("io.gitlab.arturbosch.detekt") version "1.21.0-RC1"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("maven-publish")
    id("java-library")
}

group = "com.programistich"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    ktlint {
        version.set("0.44.0-SNAPSHOT")
        verbose.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    detekt {
        config = rootProject.files("detekt.yml")
    }
}

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("main") {
            groupId = "com.programistich"
            artifactId = "automatization-java-practice"
            version = "0.1"
            from(components["kotlin"])
        }
    }
    repositories {
        maven {
            name = "LocalRepo"
            url = uri("$buildDir/publish")
        }

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/NaUKMA-Programistich/Automatization-Java-Practice-4")
            credentials {
                username = System.getenv("GH_USERNAME")
                password = System.getenv("GH_TOKEN")
            }
        }
    }
}
