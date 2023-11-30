// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}