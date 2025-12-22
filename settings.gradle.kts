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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PMDM-Compose"
include(":app")

include(":myfirstcomposeapp")
include(":intro")
include(":previewsdemo")
include(":learningcompose")
include(":exercises:compose_exercises")

include(":hardware:sensormotion")
include(":hardware:sensors")

include(":lifecycle")
include(":viewmodel")

include(":multimedia")
include(":persistence:states_todolist")
include(":persistence:room_todolist")
include(":persistence:room_todolist_repository")
include(":DI:di_todolist")
include(":DI:room_todolist_repository_di")
include(":hardware:level")
