import com.github.gradle.node.npm.proxy.ProxySettings
import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    id("org.jetbrains.intellij") version "1.13.3"
    id("com.github.node-gradle.node") version "5.0.0"
}

group = "sandipchitale"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.2.5")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
}

node {
    version.set("16.14.0")
    npmVersion.set("")
    npmInstallCommand.set("install")
    distBaseUrl.set("https://nodejs.org/dist")
    download.set(true)
    workDir.set(file("${project.projectDir}/.gradle/nodejs"))
    npmWorkDir.set(file("${project.projectDir}/.gradle/npm"))
    nodeProjectDir.set(file("${project.projectDir}/src/frontend"))
    nodeProxySettings.set(ProxySettings.SMART)
}

var ngbuild = tasks.register<NpmTask>("ngbuild") {
    group = "build"
    description = "This launches the ng build."

    dependsOn(tasks.npmInstall)

    npmCommand.set(listOf("run", "gradle-ng-build"))
    workingDir.set(file("${project.projectDir}/src/frontend"))

    inputs.dir(file("src/frontend/"))
    outputs.dir(file("${buildDir}/generated/app/"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    prepareSandbox {
        dependsOn("ngbuild")
        from("${buildDir}/generated/app/") {
            into("${intellij.pluginName.get()}/app")
        }
    }

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("232.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
