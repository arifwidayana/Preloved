plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

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
            buildConfigField("String", "BASE_URL", "\"https://staging-api.momby.id/\"")
        }
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