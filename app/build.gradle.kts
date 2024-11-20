plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.testing.bussbooking"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.testing.bussbooking"
        minSdk = 26
        targetSdk = 34
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // Add osmdroid library
    implementation(libs.osmdroid)
    //Material library
    implementation ("com.google.android.material:material:1.9.0")
    // Add Google Play Services Location library
    implementation("com.google.android.gms:play-services-location:18.0.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}