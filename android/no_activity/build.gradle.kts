import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "es.rafapuig.pmdm.android.no_activity"
    compileSdk {
        version = release(37)
    }

    /*
    El bloque defaultConfig define la configuración común de tu aplicación
    para todas las variantes de compilación (debug, release, etc.), salvo que alguna la sobrescriba.
     */
    defaultConfig {
        applicationId = "es.rafapuig.pmdm.android.no_activity" // Es el identificador único de tu aplicación
        /*
        - Google Play identifica tu app por este valor.
        - Dos aplicaciones instaladas en el mismo dispositivo no pueden tener el mismo applicationId.

        Una vez publiques la aplicación en Google Play, no deberías cambiarlo,
        porque Play Store consideraría que es una aplicación distinta
         */

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

        /*
        Indica qué clase ejecutará los tests instrumentados (androidTest).

        Estos tests:
        - se ejecutan en un dispositivo físico o emulador
        - tienen acceso al framework Android.
         */
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    /* buildTypes define los diferentes tipos de compilación de tu aplicación
    Android crea por defecto dos:
     - debug: para desarrollo y pruebas.
     - release: para publicar la aplicación. */
    buildTypes {
        // define cómo se construye la versión release de la aplicación
        /* Aquí se configuran las opciones específicas de la versión release,
        que es la que normalmente se sube a Google Play */
        release {
            // Nueva DSL de Android Gradle Plugin para controlar la optimización de forma más específica
            optimization {
                enable = false
            }
        }
    }

    /* Ese bloque indica con qué versión de Java se compila el código Java de tu proyecto
    y qué versión de bytecode se genera */
    //compileOptions {
        /* Indica qué sintaxis de Java puedes utilizar al escribir código
        Con VERSION_17 puedes usar las características disponibles hasta Java 17.
        Si intentaras usar una característica de Java 21, el compilador daría un error */
      //  sourceCompatibility = JavaVersion.VERSION_17

        /* Indica qué versión de bytecode generará el compilador.
        Es decir, las clases (.class) serán compatibles con Java 11 */
       // targetCompatibility = JavaVersion.VERSION_17

        /* ¿Y si solo escribo Kotlin?

        En un proyecto moderno con Jetpack Compose es muy frecuente no tener ni una sola clase Java.

        Aun así, este bloque sigue siendo útil porque:
        - algunas librerías contienen código Java
        - Gradle puede compilar código Java generado
        - algunos procesadores de anotaciones trabajan con Java. */
   // }
}

/*
kotlin {
    compilerOptions {
        //jvmToolchain(17)
        //jvmTarget = JvmTarget.JVM_17
    }
}*/

/*
Este bloque indica qué bibliotecas (dependencias) necesita tu proyecto para compilar y ejecutarse
Cuando sincronizas el proyecto, Gradle las descarga desde Maven Central o Google Maven
 */
dependencies {
    /**
     * Dependencias para añadir directamente al source set main
     */
    /* las dependencias implementation son para bibliotecas que forman parte de la applicacion */
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    /**
     * Dependencias para añadir al source set test
     */
    testImplementation(libs.junit) // Solo sirve para los tests unitarios


    /**
     * Dependencias para añadir al source set androidTest
     */
    /* Esta dependencia solo existe para los tests instrumentados.
    Estos tests se ejecutan:
     - en un emulador
     - o en un dispositivo físico.

    Permite ejecutar los tests instrumentados con AndroidJUnitRunner */
    androidTestImplementation(libs.androidx.junit)

    /* Espresso sirve para automatizar la interfaz gráfica */
    androidTestImplementation(libs.androidx.espresso.core)

    /* ¿Qué significa libs?
    Probablemente, tengas un archivo llamado:

    gradle/libs.versions.toml

    Allí defines las versiones. */
}