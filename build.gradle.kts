plugins {
    kotlin("jvm") version "1.5.31"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    //maven("https://repo.spring.io/snapshot")
    maven("https://repo.spring.io/milestone")
    mavenCentral()
}

dependencies {
    //implementation("io.projectreactor.netty:reactor-netty-core:1.0.13-SNAPSHOT")
    implementation("io.projectreactor.netty:reactor-netty-core:1.0.12")
    //implementation("io.projectreactor.netty:reactor-netty-http:1.0.13-SNAPSHOT")
    implementation("io.projectreactor.netty:reactor-netty-http:1.0.12")
    implementation(kotlin("stdlib"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("com.github.tomakehurst:wiremock-jre8:2.31.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}