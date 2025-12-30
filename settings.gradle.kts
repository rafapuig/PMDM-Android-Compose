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

dependencyResolutionManagement {
    versionCatalogs {
        create("ktorLibs") {
            from("io.ktor:ktor-version-catalog:3.3.3")
        }
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
include(":viewmodels:ui_state_holder")
include(":viewmodels:ui_state_holder_uiState_class")
include(":viewmodels:vm_scope_APIs")

include(":clean_architecture:subscribers")
include(":clean_architecture:todolist_repository_flows_impl")

include(":persistence:states_todolist")
include(":persistence:room_todolist")
include(":persistence:room_todolist_repository")

include(":persistence:retrofit_todolist")
include(":persistence:retrofit_todolist_repository")

include(":DI:di_todolist")
include(":DI:room_todolist_repository_di")
include(":multimedia")
include(":persistence:ktor_client_movies")
include(":client_api_services:ktor_the_meal_db")
include(":exercises:viewmodel_exercises")
include(":persistence:datastore_counter")

include(":clean_architecture:counter:domain")
include(":clean_architecture:counter:presentation:mvvm")
include(":clean_architecture:counter:data:data_store")
include(":clean_architecture:counter:app_mvvm_with_data_store")
include(":clean_architecture:counter:presentation:mvi")
include(":clean_architecture:counter:presentation:core")
include(":clean_architecture:counter:app_mvi_one_off_events_as_channel_flow_with_data_store")
