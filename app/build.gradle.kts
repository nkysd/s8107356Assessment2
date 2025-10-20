plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("com.google.dagger.hilt.android")
    kotlin("kapt") // for annotation processing

    id("androidx.navigation.safeargs.kotlin") version "2.7.7"

    id("kotlin-parcelize")
}

android {
    namespace = "com.example.s8107356assessment2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.s8107356assessment2"
        minSdk = 24
        targetSdk = 36
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

    // For DetailsFragment test
    sourceSets {
        getByName("androidTest") {
            res.srcDirs("src/main/res")
            manifest.srcFile("src/androidTest/AndroidManifest.xml")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // For JUnit test
    testImplementation("junit:junit:4.13.2")
    // For LiveData test
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    // For coroutine test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    // For Mockito (to mock classes)
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("org.mockito:mockito-core:5.2.0")

    // For Android JUnit testing (Android-specific @Test)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    // For testing Fragments with launchFragmentInContainer
    androidTestImplementation("androidx.fragment:fragment-testing:1.6.1")
    // For testing Navigation components (e.g., SafeArgs, NavController)
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.5")
    // For testing UI with Espresso (e.g., check text in TextViews)
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



    // Kotlin extensions and core Android KTX library
    implementation("androidx.core:core-ktx:1.12.0")

    // Navigation component for Fragment
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    // Navigation component for UI
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    // Retrofit core library for networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson converter to parse JSON responses into data classes
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Kotlin Coroutines core support (background threading)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    // Coroutines support for Android main thread
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Hilt for Android
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}