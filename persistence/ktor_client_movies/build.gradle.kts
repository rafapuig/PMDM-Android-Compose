import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

// Cargar las propiedades del local.properties
val localProperties = Properties()
val localPropertiesFile = File("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use {
        localProperties.load(it)
    }
}


android {
    namespace = "es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "API_KEY",
                project.properties["TMDB_API_KEY"].toString()
                //localProperties.getProperty("TMDB_API_KEY")
            )
            buildConfigField(
                "String",
                "API_ACCESS_TOKEN",
                project.properties["TMDB_API_ACCESS_TOKEN"].toString()
                //localProperties.getProperty("TMDB_API_ACCESS_TOKEN")
            )
        }
        debug {
            buildConfigField(
                "String",
                "API_KEY",
                localProperties.getProperty("TMDB_API_KEY")
            )
            buildConfigField(
                "String",
                "API_ACCESS_TOKEN",
                localProperties.getProperty("TMDB_API_ACCESS_TOKEN")
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

kotlin {
    jvmToolchain(11)
}

dependencies {

    // Ktor client https://ktor.io/docs/client-dependencies.html#client-dependency
    implementation("io.ktor:ktor-client-core:3.3.3")
    // Ktor engine https://ktor.io/docs/client-engines.html#jvm-android
    implementation("io.ktor:ktor-client-android:3.3.3")
    // Kotlin serialization https://github.com/Kotlin/kotlinx.serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0-RC")
    // https://ktor.io/docs/client-serialization.html
    implementation("io.ktor:ktor-client-content-negotiation:3.3.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.3")
    // Logging
    implementation("io.ktor:ktor-client-logging:3.3.3")

    // Libreria de iconos extendida de Material 3
    implementation(libs.androidx.compose.material.icons.extended)

    //Coil
    implementation("io.coil-kt.coil3:coil-compose:3.3.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.3.0")

    // ViewModel en Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}