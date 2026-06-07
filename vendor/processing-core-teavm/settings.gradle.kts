pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://jogamp.org/deployment/maven")
    }
}

rootProject.name = "processing-core-teavm"
