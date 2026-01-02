import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    /** Modulos de tipo applicacion de Android */
    alias(libs.plugins.android.application) apply false

    /** Modulos de tipo libreria de Android (no son aplicaciones, son dependencias)  */
    alias(libs.plugins.android.library) apply false

    /** Modulos Kotlin-Java (puros, por ejemplo una capa de dominio) */
    alias(libs.plugins.kotlin.jvm) apply false

    alias(libs.plugins.kotlin.android) apply false // Comun a modulos Android (apps y libs)
    alias(libs.plugins.kotlin.compose) apply false // Módulos que usan Compose
    alias(libs.plugins.kotlin.parcelize) apply false

    /** Kotlin Symbol Processor para usar Room y Dagger-Hilt */
    alias(libs.plugins.google.devtools.ksp) apply false

    /** Dagger-Hilt */
    id("com.google.dagger.hilt.android") version "2.57.2" apply false
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}