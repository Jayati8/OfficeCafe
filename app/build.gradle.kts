plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.officecafe"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.officecafe"
        minSdk = 19
        targetSdk = 33
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

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")

    //retrofit and Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")

    //ViewModel and livedata
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")

    //New Material design
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("io.github.chaosleung:pinview:1.4.4")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

//    //Android Navigation Architecture
//    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.4")
//    implementation ("androidx.navigation:navigation-ui-ktx:2.7.4")
//
//   // The Kotlin ones
//    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.4")
//    implementation ("androidx.navigation:navigation-ui-ktx:2.7.4")

}