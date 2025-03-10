plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.gitrepoexplore"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gitrepoexplore"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        buildConfig = true
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
        // Retrofit for API calls
        implementation (libs.retrofit)
        implementation (libs.converter.gson)

        // Coroutines for background tasks
        implementation (libs.kotlinx.coroutines.android)
        implementation (libs.kotlinx.coroutines.core)

        // ViewModel and LiveData (Jetpack)
        implementation (libs.androidx.lifecycle.viewmodel.ktx)
        implementation (libs.androidx.lifecycle.livedata.ktx)

        // Hilt for Dependency Injection
        kapt (libs.hilt.android)
    // Dagger - Hilt
    implementation(libs.hilt.android)

    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.activity.ktx)




        // Paging for efficient pagination
        implementation (libs.androidx.paging.runtime.ktx)

        // Navigation Component
        implementation (libs.androidx.navigation.fragment.ktx)
        implementation (libs.androidx.navigation.ui.ktx)

        // RecyclerView
        implementation (libs.androidx.recyclerview)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")


}
kapt {
    correctErrorTypes = true
}