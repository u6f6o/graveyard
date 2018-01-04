import org.gradle.internal.os.OperatingSystem;

plugins {
    application
    kotlin("jvm") version "1.2.10"
}

application {
    mainClassName = "graveyard.HelloWorldKt"
}

dependencies {
    compile(kotlin("stdlib"))
    compile(group = "io.netty", name = "netty-all", version = "4.1.19.Final")
    compile(group = "io.netty", name = "netty-transport-native-epoll", version = "4.1.19.Final")
    compile(group = "io.netty", name = "netty-transport-native-kqueue", version = "4.1.19.Final")
}

repositories {
    jcenter()
}