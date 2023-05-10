// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(Library.classpathToolsBuild)
        classpath(Library.classpathGoogleAnalytics)
        classpath(Library.classpathGoogleCrashlytics)
        classpath(Library.classpathGoogleMonitoringPerformance)
    }
}
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("androidx.navigation.safeargs") version "2.4.2" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0" apply false
}