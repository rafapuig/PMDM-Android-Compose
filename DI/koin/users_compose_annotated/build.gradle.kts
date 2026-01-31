plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Kotlin Symbol Processor (Para anotaciones de Koin)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "es.rafapuig.pmdm.di.users.koin.compose"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "es.rafapuig.pmdm.di.users.koin.compose"
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

    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(11)
}

// Compile time check
ksp {
    arg("KOIN_CONFIG_CHECK","true")
}

dependencies {

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.androidx.compose)
    // Koin Annotations -> https://insert-koin.io/docs/setup/annotations
    implementation(libs.koin.annotations)
    // Koin Annotations KSP Compiler
    ksp(libs.koin.ksp.compiler)


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