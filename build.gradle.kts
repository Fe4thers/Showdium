import java.io.ByteArrayOutputStream
import org.gradle.jvm.tasks.Jar
import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun getGitCommit(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

plugins {

    kotlin("jvm") version "2.2.0" apply false
    alias(libs.plugins.moddev) apply false
    alias(libs.plugins.loom) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.paperweight) apply false
    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.run.paper) apply false
    java  // Add this line!
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}
group = "com.github.Fe4thers"
version = "v1.0.1"

val javaVersion: Int = 21

allprojects {

    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://maven.enginehub.org/repo/")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.terraformersmc.com/")
        maven("https://maven.shedaniel.me/")
        maven("https://maven.covers1624.net/")
        maven("https://repo.viaversion.com")
        maven("https://maven.noxcrew.com/public")
        maven("https://maven.isxander.dev/releases/")
        mavenCentral()
        maven {
            setUrl("https://api.modrinth.com/maven")
            content {
                includeGroup("maven.modrinth")
            }
        }
    }
}

subprojects {
    apply<JavaLibraryPlugin>()
    apply<SpotlessPlugin>()

    tasks {
        withType<Jar> {
            from("LICENSE") {
                rename { return@rename "${it}_${rootProject.name}" }
            }
        }

        withType<JavaCompile> {
            options.release.set(javaVersion)
            options.encoding = Charsets.UTF_8.name()
            sourceCompatibility = javaVersion.toString()
            targetCompatibility = javaVersion.toString()
        }

        withType<AbstractArchiveTask> {
            archiveBaseName.set("noxesium-${project.name}")
        }
    }

    extensions.configure<SpotlessExtension> {
        java {
            palantirJavaFormat("2.50.0")
        }
        kotlin {
            ktlint("1.5.0")
            suppressLintsFor {
                step = "ktlint"
                shortCode = "standard:package-name"
            }
            suppressLintsFor {
                step = "ktlint"
                shortCode = "standard:annotation"
            }
            suppressLintsFor {
                step = "ktlint"
                shortCode = "standard:property-naming"
            }
        }
    }

    extensions.configure<JavaPluginExtension> {
        withSourcesJar()
        toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }

    // Set this late as the Kotlin plugin may not be applied yet!
    afterEvaluate {
        tasks {
            withType<KotlinCompile> {
                explicitApiMode.set(ExplicitApiMode.Strict)

                compilerOptions {
                    jvmTarget.set(JvmTarget.fromTarget(javaVersion.toString()))
                }
            }
        }
    }
}


java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.Fe4thers"
            artifactId = "showdium"
            version = project.version.toString()

            from(components["java"])
        }
    }
}
