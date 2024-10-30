plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose) // Add this line
    alias(libs.plugins.navigation.safeargs) // Add this line
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "se.warting.welcome"
    compileSdk = 34

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(platform(libs.androidx.compose.bom)) // Add this line
    implementation(libs.androidx.ui) // Add this line
    implementation(libs.androidx.ui.graphics) // Add this line
    implementation(libs.androidx.ui.tooling.preview)
    implementation(project(":destination")) // Add this line
    debugImplementation(libs.androidx.ui.tooling) // Add this line
    debugImplementation(libs.androidx.material3) // Add this line

    // Add Jetpack Compose Navigation dependency
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation(libs.kotlinx.serialization.json) // Add this line

    //implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3") // Add this line

    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Add this line
    androidTestImplementation(libs.androidx.ui.test.junit4) // Add this line
    debugImplementation(libs.androidx.ui.test.manifest) // Add this line
}