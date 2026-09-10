import org.gradle.kotlin.dsl.*

plugins {
    id("net.minecraftforge.gradle") version "7.0.35"
    id("dev.kikugie.stonecutter")
}

minecraft {
    // Stonecutter will inject these per-version
    mappings = stonecutter.mappings
    runs {
        client {
            workingDirectory = project.file("run")
        }
        server {
            workingDirectory = project.file("run")
        }
        data {
            workingDirectory = project.file("run")
        }
    }
}

dependencies {
    minecraft("net.minecraftforge:forge")
}

// Jar manifest
tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Mod-Id" to project.findProperty("mod_id") as String? ?: "turtle_armor",
            "Mod-Name" to project.findProperty("mod_name") as String? ?: "Complete Turtle Armor",
            "Mod-Version" to project.findProperty("mod_version") as String? ?: "1.0.0",
            "Mod-Description" to "Adds missing turtle armor pieces (chestplate, leggings, boots) with vanilla-style effects",
            "Mod-Authors" to "YourName",
            "Mod-Contact" to "https://github.com/yourrepo",
            "Mod-License" to "MIT"
        )
    }
}

// Stonecutter: replace templates
tasks.named("processResources") {
    filesMatching("**/mods.toml") {
        expand(project.properties)
    }
}