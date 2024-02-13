import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.detekt)
    alias(libs.plugins.devToolsKsp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotilnAndroid)
}

android {
    namespace = "com.example.warehouse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.warehouse"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }

    tasks.register<Detekt>("detektAll") {

        description = "Custom DETEKT build for all modules"
        setSource(file(rootDir))
        parallel = true
        ignoreFailures = false
        autoCorrect = false
        debug = true

        include("**/*.kt")
        exclude("**/*.kts")
        exclude("**/resources/**")
        exclude("**/generated/**")
        exclude("**/build/**")
    }

    configure<DetektExtension> {
        dependencies {
            detektPlugins(libs.detekt.formatting)
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Junit
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    // Hilt
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt)


    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Module
    implementation(project(":libs:resources"))
    implementation(project(":feature:home"))
}
