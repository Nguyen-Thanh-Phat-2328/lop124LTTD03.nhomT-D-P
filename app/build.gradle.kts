plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.lop124lttd03.nhom_t_d_p"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lop124lttd03.nhom_t_d_p"
        minSdk = 24
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("me.relex:circleindicator:2.1.6")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")



    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")


    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")


    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")

    implementation ("com.jakewharton.rxbinding3:rxbinding:3.1.0")

    implementation ("com.github.bumptech.glide:glide:4.15.1")

    implementation ("com.github.mhiew:android-pdf-viewer:3.2.0-beta.3")

    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

}