plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "es.rafapuig.pmdm.compose"
    compileSdk {
        version = release(37) {
            compileSdkMinor = 1
        }
    }

    defaultConfig {
        applicationId = "es.rafapuig.pmdm.compose"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    /**
     * Dependencias para añadir directamente al source set main
     */
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    /**
     * Dependencias para añadir al source set test
     */
    testImplementation(libs.junit)

    /**
     * Dependencias para añadir al source set androidTest
     */
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}