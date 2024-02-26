plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.paypay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.paypay"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.code.gson:gson:2.10")

    implementation ("com.google.dagger:hilt-android:2.48")
    testImplementation("junit:junit:4.12")
    kapt ("com.google.dagger:hilt-compiler:2.48")

//    def lifecycle_version = "2.7.0"
//    def arch_version = "2.2.0"

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation( "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

    val room_version = "2.6.1"

    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")

    implementation ("androidx.room:room-ktx:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt ("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
//    ksp ("androidx.room:room-compiler:$room_version")
    // optional - RxJava2 support for Room
    implementation ("androidx.room:room-rxjava2:$room_version")
    // optional - RxJava3 support for Room
    implementation ("androidx.room:room-rxjava3:$room_version")
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation ("androidx.room:room-guava:$room_version")
    // optional - Test helpers
    testImplementation ("androidx.room:room-testing:$room_version")
    // optional - Paging 3 Integration
    implementation ("androidx.room:room-paging:$room_version")






    testImplementation("junit:junit:4.13.2")

    // Assertions
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.ext:truth:1.5.0")
    androidTestImplementation ("com.google.truth:truth:1.0")

    // Mockito
    testImplementation ("org.mockito:mockito-core:3.7.7")
    testImplementation ("org.mockito:mockito-inline:3.4.0")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}