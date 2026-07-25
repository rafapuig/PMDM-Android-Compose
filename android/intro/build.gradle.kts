plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "es.rafapuig.pmdm.intro"
    compileSdk {
        version = release(37) {
            compileSdkMinor = 1
        }
    }

    /*
    El bloque defaultConfig define la configuración común de tu aplicación
    para todas las variantes de compilación (debug, release, etc.), salvo que alguna la sobrescriba.
     */

    defaultConfig {
        /*
        - Google Play identifica tu app por este valor.
        - Dos aplicaciones instaladas en el mismo dispositivo no pueden tener el mismo applicationId.

        Una vez publiques la aplicación en Google Play, no deberías cambiarlo,
        porque Play Store consideraría que es una aplicación distinta
         */
        applicationId = "es.rafapuig.pmdm.intro" // Es el identificador único de tu aplicación

        minSdk = 26 // Indica la versión mínima de Android que puede instalar la aplicación

        /*
        Indica la versión de Android para la que la aplicación ha sido diseñada y probada.
        No impide que funcione en versiones superiores,
        pero Android aplicará el comportamiento correspondiente al targetSdk
         */
        targetSdk = 37

        /*
        Es un número entero interno.
        El usuario nunca lo ve.
        Google Play lo utiliza para saber si una versión es más nueva.
         */
        versionCode = 1 // 1, 2, 3, ...

        versionName = "1.0" // Es la versión que verá el usuario. Puedes poner el formato que quieras.

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        /*
        Indica qué clase ejecutará los tests instrumentados (androidTest).

        Estos tests:
        - se ejecutan en un dispositivo físico o emulador
        - tienen acceso al framework Android.
         */
    }

    buildTypes {
        // define cómo se construye la versión release de la aplicación
        release {
            isMinifyEnabled = false // Optimización de código
            isShrinkResources = false //eliminación de recursos no utilizados
            proguardFiles( // reglas de ProGuard/R8
                /*
                Carga un archivo de reglas proporcionado por Android
                Existen principalmente dos archivos por defecto:

                - proguard-android.txt
                - proguard-android-optimize.txt

                El segundo aplica optimizaciones adicionales además de la ofuscación
                 */
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro" // Este es tu archivo de reglas personalizado
                /*
                Aquí puedes añadir excepciones para evitar que R8 elimine u ofusque ciertas clases.

                Por ejemplo:

                -keep class com.miapp.models.** { *; }

                Esto le dice a R8:

                "No elimines ni cambies el nombre de las clases del paquete com.miapp.models."

                Es muy útil cuando utilizas librerías como:

                Retrofit
                Gson
                Moshi
                Room
                Firebase
                etc.
                */
            )
        }
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

    buildFeatures {
        compose = true
    }
}

dependencies {
    /**
     * Dependencias para añadir directamente al source set main
     */
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    /**
     * Dependencias para añadir al source set debug (solo para version de desarrollo)
     */
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    /**
     * Dependencias para añadir al source set test
     */
    testImplementation(libs.junit)

    /**
     * Dependencias para añadir al source set androidTest
     */
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

}