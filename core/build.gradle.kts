import org.jetbrains.kotlin.konan.properties.Properties
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import java.io.FileInputStream

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jlleitschuh.gradle.ktlint")
}

val corePropertiesFile = rootProject.file("core.properties")
val coreProperties = Properties()

coreProperties.load(FileInputStream(corePropertiesFile))

android {
    namespace = "com.arifwidayana.core"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    flavorDimensions += listOf("default")
    productFlavors {
        create("production") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", coreProperties["BASE_URL"].toString())
            buildConfigField("String", "END_POINT_LOGIN", coreProperties["END_POINT_LOGIN"].toString())
            buildConfigField("String", "END_POINT_REGISTER", coreProperties["END_POINT_REGISTER"].toString())
            buildConfigField("String", "END_POINT_SELLER_CATEGORY", coreProperties["END_POINT_SELLER_CATEGORY"].toString())
            buildConfigField("String", "END_POINT_BUYER_PRODUCT", coreProperties["END_POINT_BUYER_PRODUCT"].toString())
            buildConfigField("String", "END_POINT_DETAIL_BUYER_PRODUCT", coreProperties["END_POINT_DETAIL_BUYER_PRODUCT"].toString())
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    debug.set(true)
    verbose.set(true)
    android.set(false)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(true)
    enableExperimentalRules.set(true)
    additionalEditorconfigFile.set(file("/some/additional/.editorconfig"))
    disabledRules.set(setOf("final-newline", "no-wildcard-imports"))
    baseline.set(file("my-project-ktlint-baseline.xml"))
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
    }
    kotlinScriptAdditionalPaths {
        include(fileTree("scripts/"))
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

dependencies {
    api(Library.androidCoreKtx)
    api(Library.androidAppCompat)
    api(Library.googleMaterial)
    api(Library.constraintLayout)
    api(Library.swipeRefreshLayout)

    testApi(Library.junit4)
    androidTestApi(Library.androidJunit)
    androidTestApi(Library.androidEspressoCore)

    // View Model and lifecycle stuffs
    api(Library.lifecycleViewModel)
    api(Library.lifecycleLivedata)
    api(Library.lifecycleRuntime)

    // Retrofit
    api(Library.retrofitConverterGson)
    api(Library.retrofit)

    // Coroutine
    api(Library.coroutinesCore)
    api(Library.coroutinesAndroid)

    // Datastore
    api(Library.datastore)

    // Gson
    api(Library.gson)

    // Koin
    api(Library.koin)

    // Glide
    api(Library.glide)
    kapt(Library.glideCompiler)

    // Coil
    api(Library.coil)

    // Room
    api(Library.roomKtx)
    api(Library.roomRuntime)
    kapt(Library.roomCompiler)

    // Navigation Fragment
    api(Library.navigationUi)
    api(Library.navigationFragment)

    // Mock Test
    api(Library.mock)

    // styling module
    api(project(":style"))
}