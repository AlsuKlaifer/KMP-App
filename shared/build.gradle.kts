plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)

    alias(libs.plugins.kotlinSerialization)
    // alias(libs.plugins.buildKonfig)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.jvmTarget.get()
            }
        }
    }
    task("testClasses")
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {

            implementation(libs.bundles.ktorClientCommon)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)

            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.serialization)

            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.sqldelight.sqlite.adapter)

            implementation(libs.kotlinx.immutable)

            implementation(libs.koin.core)

            implementation(libs.moko)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.client.android)
            implementation(libs.okhttp3.logging.interceptor)
            implementation(libs.kotlinx.coroutines.android)

            implementation(libs.sqldelight.android.driver)

            implementation(libs.koin.core)
            implementation(libs.koin.android)

            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.compose.ui.tooling)

            implementation(libs.androidx.lifecycle.runtime)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.androidx.lifecycle.viewmodel.compose)

            implementation(libs.glide.compose)
            implementation(libs.composeShimmer)
            implementation(libs.compose.navigation)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
            implementation(libs.sqldelight.native.driver)
            implementation("co.touchlab:stately-common:2.0.5")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

//buildkonfig {
//    packageName = "com.example.newsapp"
//
////    defaultConfigs {
////        buildConfigField(STRING, "appid", "56fc6c6cb76c0864b4cd055080568268")
////    }
//}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.newsapp.")
        }
    }
}

android {
    namespace = "com.example.newsapp"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        buildConfig = false
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}