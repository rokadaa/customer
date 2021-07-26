import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
    id("pl.allegro.tech.build.axion-release") version "1.13.3"

}

group = "com.example"
version = scmVersion.version
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("pl.allegro.tech.build:axion-release-plugin:1.13.3")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<BootBuildImage>("bootBuildImage") {
    println("$project")
    println("${project.displayName}:${project.version}")
    println("URL ${System.getenv("url")}")
    println("Actor ${System.getenv("username")}")
    println("Token ${System.getenv("password")}")
    println("URL ${System.getenv("GHCR_URL")}")
    println("Actor ${System.getenv("github.actor")}")
    println("Token ${System.getenv("GHCR_TOKEN")}")
    imageName = "ghcr.io/rokadaa/${project.name}:${project.version}"
    isPublish = true
    docker {
        publishRegistry {
            url = System.getenv("url")
            username = System.getenv("username")
            password = System.getenv("password")
        }
    }
}
