import org.gradle.kotlin.dsl.*

plugins {
    id("fabric-loom")
}

loom {
    modId = project.findProperty("mod_id") as String? ?: "turtle_armor"
    modName = project.findProperty("mod_name") as String? ?: "Complete Turtle Armor"

    // These will be overridden per subproject
    minecraftVersion = project.findProperty("minecraft_version") as String? ?: "1.20.1"
    mappings = project.findProperty("yarn_mappings") as String? ?: "yarn-1.20.1+v1.20.1"
    loaderVersion = project.findProperty("fabric_loader_version") as String? ?: "0.16.9"

    // Mixin config
    mixin = "turtle_armor.mixins.json"

    // Access wideners
    accessWidener = "turtle_armor.accesswidener"
}

dependencies {
    minecraft("com.mojang:minecraft")
    modImplementation("com.fabricmc:fabric-loader")
    modApi("com.fabricmc.fabric-api:fabric-api")
}

// Jar manifest
tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Fabric-Mod" to "true",
            "Mod-Id" to project.findProperty("mod_id") as String? ?: "turtle_armor",
            "Mod-Name" to project.findProperty("mod_name") as String? ?: "Complete Turtle Armor",
            "Mod-Version" to project.findProperty("mod_version") as String? ?: "1.0.0",
            "Mod-Description" to "Adds missing turtle armor pieces (chestplate, leggings, boots) with vanilla-style effects",
            "Mod-Authors" to "YourName",
            "Mod-Contact" to "https://github.com/yourrepo",
            "Mod-License" to "MIT",
            "Mod-Icon" to "assets/turtle_armor/icon.png"
        )
    }
}

// Replace templates in resources
tasks.named("processResources") {
    filesMatching("**/fabric.mod.json") {
        expand(project.properties)
    }
}