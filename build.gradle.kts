// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20-RC" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.20-RC" apply false


}
buildscript {
    dependencies {
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.8.0-1.0.8")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")

    }
}