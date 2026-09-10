plugins {
    id("net.minecraftforge.gradle")
    id("dev.kikugie.stonecutter")
}

sourceSets {
    main {
        java {
            srcDirs("../../../src/main/java")
        }
        resources {
            srcDirs("../../../src/main/resources")
            srcDirs("../src/main/resources")
        }
    }
}