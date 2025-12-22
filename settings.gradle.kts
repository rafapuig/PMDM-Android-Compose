pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PMDM-Compose"
include(":app")

include(":compose:myfirstcomposeapp")
include(":compose:intro")
include(":compose:previewsdemo")
include(":compose:learningcompose")
include(":exercises:compose_exercises")

include(":hardware:level")
include(":hardware:sensormotion")
include(":hardware:sensors")

include(":lifecycle")
include(":viewmodel")

include(":persistence:states_todolist")
include(":persistence:room_todolist")
include(":persistence:room_todolist_repository")
include(":DI:di_todolist")
include(":DI:room_todolist_repository_di")
include(":multimedia")
