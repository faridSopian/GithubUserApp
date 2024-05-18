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
        viewBinding = true
        buildConfig = true
    }
    dynamicFeatures += setOf(":theme")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":core"))
}