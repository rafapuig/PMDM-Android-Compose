plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.3.0"
}

android {
    namespace = "es.rafapuig.pmdm.persistence.retrofit.todolist"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "es.rafapuig.pmdm.persistence.retrofit.todolist"
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }

    buildFeatures {
        compose = true
    }
}



dependencies {

    // Libreria de iconos extendida de Material 3
    implementation(libs.androidx.compose.material.icons.extended)

    // ViewModel en Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    /** Retrofit */
    // https://central.sonatype.com/artifact/com.squareup.retrofit2/retrofit/overview
    // https://square.github.io/retrofit/
    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    // https://github.com/square/retrofit/tree/trunk/retrofit-converters/kotlinx-serialization
    // https://central.sonatype.com/artifact/com.squareup.retrofit2/converter-kotlinx-serialization/3.0.0/overview
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:3.0.0")

    /** Serializacion JSON */
    // https://central.sonatype.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json/overview
    // https://github.com/Kotlin/kotlinx.serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0-RC")


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