plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.navigation.safeargs)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "se.warting.legacy"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

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
}

kotlin {
    jvmToolchain(jdkVersion = 21)
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.material)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(projects.destination)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.fragment.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}