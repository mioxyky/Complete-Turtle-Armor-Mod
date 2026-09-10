import org.gradle.kotlin.dsl.*

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.neoforged.net/releases")
        maven("https://maven.fabricmc.net/")
    }
}

plugins {
    id("fabric-loom") version "1.5.8" apply false
    id("net.minecraftforge.gradle") version "7.0.35" apply false
    id("net.neoforged.gradle") version "6.0.21" apply false
}

allprojects {
    group = "com.turtlearmor"
    version = "1.0.0"

    repositories {
        mavenCentral()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.neoforged.net/releases/")
    }
}

// Task to copy all JARs to centralized build/ directory
tasks.register("copyAllJars", Copy {
    val buildDir = layout.buildDirectory

    // Fabric JARs (nested structure: fabric:1.20)
    into(buildDir.dir("Fabric"))
    subprojects.filter { it.name.startsWith("fabric:") }.forEach { project ->
        from(project.tasks.named("jar"))
        val version = project.name.substringAfter("fabric:")
        rename { "TurtleArmor-Fabric-$version.jar" }
    }

    // Forge JARs
    into(buildDir.dir("Forge"))
    subprojects.filter { it.name.startsWith("forge:") }.forEach { project ->
        from(project.tasks.named("jar"))
        val version = project.name.substringAfter("forge:")
        rename { "TurtleArmor-Forge-$version.jar" }
    }

    // NeoForge JARs
    into(buildDir.dir("NeoForge"))
    subprojects.filter { it.name.startsWith("neoforge:") }.forEach { project ->
        from(project.tasks.named("jar"))
        val version = project.name.substringAfter("neoforge:")
        rename { "TurtleArmor-NeoForge-$version.jar" }
    }
})

tasks.named("build").configure {
    dependsOn("copyAllJars")
}