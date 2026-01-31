plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "es.rafapuig.pmdm.clean.authentication"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "es.rafapuig.pmdm.clean.authentication"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "backend"

    productFlavors {
        create("fake") {
            dimension = "backend"
        }
        create("mock") {
            dimension = "backend"
        }
        create("real") {
            dimension = "backend"
        }
    }

    androidComponents {
        beforeVariants { variantBuilder ->

            val buildType = variantBuilder.buildType
            val flavors = variantBuilder.productFlavors

            val flavorName = flavors.firstOrNull()?.second

            if (buildType == "release" && flavorName != "real") {
                variantBuilder.enable = false
            }
        }
    }

    /*sourceSets {
        create("staging") {
            java.srcDirs("src/staging/java","src/debug/java")
            res.srcDirs("src/staging/res")
            manifest.srcFile("src/staging/AndroidManifest.xml")
        }
    }*/

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        /*create("staging") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            isMinifyEnabled = false
        }*/

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
    // Material3
    //implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.core:core-splashscreen:1.2.0")


    // Libreria de iconos extendida
    implementation(libs.androidx.compose.material.icons.extended)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Koin Android + Compose
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.androidx.compose)


    // Retrofit + converter Kotlinx Serialization
    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)

    implementation(platform(libs.okhttp.bom))
    testImplementation(libs.okhttp.mockwebserver)
    debugImplementation(libs.okhttp.mockwebserver)


    // Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Test
    testImplementation(kotlin("test")) // Para test sencillos te da asserts y @Test

    testImplementation(libs.junit)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.3.2")

    // MockK
    //testImplementation("io.mockk:mockk-core:1.14.7")
    testImplementation("io.mockk:mockk-agent:1.14.9")
    testImplementation("io.mockk:mockk-android:1.14.9")
    //androidTestImplementation("io.mockk:mockk-android:1.14.7")


    // MockK
    //testImplementation("io.mockk:mockk:1.14.7") // Con este funciona
    androidTestImplementation("io.mockk:mockk-agent:1.14.9")
    androidTestImplementation("io.mockk:mockk-android:1.14.9")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

}