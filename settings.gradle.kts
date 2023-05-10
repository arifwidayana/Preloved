pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
rootProject.name = "Preloved"
include(
    ":app",
    ":core",
    ":style",
    ":shared",
    ":navigation",
    ":feature:splash",
    ":feature:home",
    ":feature:account",
    ":feature:notification",
    ":feature:sell",
    ":feature:sale",
    ":feature:bid",
    ":feature:auth:login",
    ":feature:auth:register",
)