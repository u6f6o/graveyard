import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.js.translate.context.Namer.kotlin

val kotlin_version: String by extra
buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.10"
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin(module = "gradle-plugin", version = kotlin_version))
    }
}

plugins {
    application
}
apply {
    plugin("kotlin")
}

application {
    mainClassName = "graveyard.HelloWorldKt"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compile(group = "ch.qos.logback", name = "logback-classic", version = "1.2.1")
    compile(group = "io.ktor", name = "ktor-server-netty", version = "0.9.0")
    compile(kotlin(module = "stdlib-jdk8", version = kotlin_version))
}

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/kotlinx")
    maven(url = "https://dl.bintray.com/kotlin/ktor")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}