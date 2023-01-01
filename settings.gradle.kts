pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()

    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Depression_App"
include(":androidApp")
include(":desktopApp")
include(":shared")