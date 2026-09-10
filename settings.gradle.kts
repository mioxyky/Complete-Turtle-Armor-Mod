pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.neoforged.net/releases")
        maven("https://maven.fabricmc.net/")
    }
}

rootProject.name = "turtle-armor"

// Include all subprojects (nested structure)
include(
    // Fabric versions
    "fabric:1.16.5", "fabric:1.17.1", "fabric:1.18.2", "fabric:1.19",
    "fabric:1.20", "fabric:1.20.5", "fabric:1.21", "fabric:1.21.11", "fabric:26",
    // Forge versions (legacy)
    "forge:1.16.5", "forge:1.17.1", "forge:1.18.2", "forge:1.19", "forge:1.20.1",
    // NeoForge versions
    "neoforge:1.20.5", "neoforge:1.21", "neoforge:26"
)