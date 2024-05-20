import com.android.build.api.variant.BuildConfigField
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

apply {
    from("../shared_dependencies.gradle")
}

android {
    namespace = "com.bangkitacademy.githubuserapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bangkitacademy.githubuserapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //memuat nilai dari file .properties
        val keystoreFile = project.rootProject.file( "local.properties" )
        val properti = Properties()
        properti.load(keystoreFile.inputStream())

        buildConfigField("String", "apiKey", "\"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\"")
        buildConfigField("String", "baseUrl", "\"https://api.github.com/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    dynamicFeatures += setOf(":theme")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":core"))
    implementation("androidx.core:core-splashscreen:1.0.1")
    testImplementation("junit:junit:4.13.2")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14")
}