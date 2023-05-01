import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jlleitschuh.gradle.ktlint")
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

    signingConfigs {
        create("productionSigningKey") {
            storeFile = file(SigningHelper.getValue(SigningHelper.RELEASE_STORE_FILE))
            storePassword = SigningHelper.getValue(SigningHelper.RELEASE_STORE_PASSWORD)
            keyAlias = SigningHelper.getValue(SigningHelper.RELEASE_KEY_ALIAS)
            keyPassword = SigningHelper.getValue(SigningHelper.RELEASE_KEY_PASSWORD)
        }
    }

    buildTypes {
        named("debug") {
            isMinifyEnabled = false
            isJniDebuggable = true
            signingConfig = signingConfigs.getByName("productionSigningKey")
        }
        named("release") {
            isMinifyEnabled = false
            isJniDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("productionSigningKey")
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
            buildConfigField("String", "BASE_URL", CoreHelper.getValue(CoreHelper.BASE_URL))
            buildConfigField("String", "END_POINT_LOGIN", CoreHelper.getValue(CoreHelper.END_POINT_LOGIN))
            buildConfigField("String", "END_POINT_REGISTER", CoreHelper.getValue(CoreHelper.END_POINT_REGISTER))
            buildConfigField("String", "END_POINT_USER", CoreHelper.getValue(CoreHelper.END_POINT_USER))
            buildConfigField("String", "END_POINT_CHANGE_PASSWORD", CoreHelper.getValue(CoreHelper.END_POINT_CHANGE_PASSWORD))
            buildConfigField("String", "END_POINT_SELLER_PRODUCT", CoreHelper.getValue(CoreHelper.END_POINT_SELLER_PRODUCT))
            buildConfigField("String", "END_POINT_DETAIL_SELLER_PRODUCT", CoreHelper.getValue(CoreHelper.END_POINT_DETAIL_SELLER_PRODUCT))
            buildConfigField("String", "END_POINT_SELLER_ORDER", CoreHelper.getValue(CoreHelper.END_POINT_SELLER_ORDER))
            buildConfigField("String", "END_POINT_DETAIL_SELLER_ORDER", CoreHelper.getValue(CoreHelper.END_POINT_DETAIL_SELLER_ORDER))
            buildConfigField("String", "END_POINT_SELLER_CATEGORY", CoreHelper.getValue(CoreHelper.END_POINT_SELLER_CATEGORY))
            buildConfigField("String", "END_POINT_SELLER_BANNER", CoreHelper.getValue(CoreHelper.END_POINT_SELLER_BANNER))
            buildConfigField("String", "END_POINT_BUYER_PRODUCT", CoreHelper.getValue(CoreHelper.END_POINT_BUYER_PRODUCT))
            buildConfigField("String", "END_POINT_DETAIL_BUYER_PRODUCT", CoreHelper.getValue(CoreHelper.END_POINT_DETAIL_BUYER_PRODUCT))
            buildConfigField("String", "END_POINT_BUYER_ORDER", CoreHelper.getValue(CoreHelper.END_POINT_BUYER_ORDER))
            buildConfigField("String", "END_POINT_DETAIL_BUYER_ORDER", CoreHelper.getValue(CoreHelper.END_POINT_DETAIL_BUYER_ORDER))
            buildConfigField("String", "END_POINT_BUYER_WISHLIST", CoreHelper.getValue(CoreHelper.END_POINT_BUYER_WISHLIST))
            buildConfigField("String", "END_POINT_DETAIL_BUYER_WISHLIST", CoreHelper.getValue(CoreHelper.END_POINT_DETAIL_BUYER_WISHLIST))
            buildConfigField("String", "END_POINT_HISTORY", CoreHelper.getValue(CoreHelper.END_POINT_HISTORY))
            buildConfigField("String", "END_POINT_NOTIFICATION", CoreHelper.getValue(CoreHelper.END_POINT_NOTIFICATION))
            buildConfigField("String", "END_POINT_DETAIL_NOTIFICATION", CoreHelper.getValue(CoreHelper.END_POINT_DETAIL_NOTIFICATION))
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
    api(Library.shimmer)

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

    // Paging3
    api(Library.pagingRuntime)

    // Glide
    api(Library.glide)
    kapt(Library.glideCompiler)

    // ImagePicker
    api(Library.imagePicker)

    // Coil
    api(Library.coil)

    // Navigation Fragment
    api(Library.navigationUi)
    api(Library.navigationFragment)

    // Mock Test
    api(Library.mock)

    // Styling Module
    api(project(":style"))
}