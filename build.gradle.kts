import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.31"
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "io.neilshirsat"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    flatDir {
        dirs("lib")
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.slf4j:slf4j-api:1.7.36")

    implementation("ch.qos.logback:logback-core:1.2.11")
    implementation("ch.qos.logback:logback-classic:1.2.11")

    implementation("com.formdev:flatlaf:2.1")
    implementation("com.formdev:flatlaf-intellij-themes:2.1")

    implementation("io.vertx:vertx-web:4.2.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2.2")

    implementation("commons-io:commons-io:2.11.0")

    implementation(fileTree(mapOf("dir" to "lib", "include" to listOf("*.jar"))))
    //implementation(files("jogl-all.jar"))
    //implementation(files("gluegen-rt.jar"))
    //implementation(files("jcef.jar"))
    //implementation(files("gluegen-rt-natives-windows-amd64.jar"))
    //implementation(files("jogl-all-natives-windows-amd64.jar"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "io.neilshirsat.catan.render.MainFrame"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}