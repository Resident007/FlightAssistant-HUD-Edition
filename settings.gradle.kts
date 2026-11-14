pluginManagement {
    repositories {
        mavenLocal()
        maven("https://jitpack.io")

        gradlePluginPortal()
        mavenCentral()

        // Modstitch
        maven("https://maven.isxander.dev/releases")

        // Loom platform
        maven("https://maven.fabricmc.net/")

        // MDG platform
        maven("https://maven.neoforged.net/releases/")

        // Stonecutter
        maven("https://maven.kikugie.dev/releases")
        maven("https://maven.kikugie.dev/snapshots")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.7.10"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    create(rootProject) {
        fun mc(version: String, vararg loaders: String) {
            for (it in loaders) version("$version-$it", version)
        }
        mc("26.3", "fabric")

        // 26.x ships without obfuscation, so it builds with the non-remapping Loom instead of Modstitch
        mapBuilds { _, node ->
            if (node.version.startsWith("26.")) "build.unobf.gradle.kts" else "build.gradle.kts"
        }
    }
}

rootProject.name = "FlightAssistant-HUD-Edition"
