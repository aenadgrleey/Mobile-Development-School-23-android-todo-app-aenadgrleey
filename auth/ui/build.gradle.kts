plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("auth")
    defaultConfig {
        manifestPlaceholders["YANDEX_CLIENT_ID"] = "0d0970774e284fa8ba9ff70b6b06479a"
    }
}

dependencies {
    implementation(project(":resources"))
    implementation(project(":core:di"))
    implementation(project(":auth:domain"))
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.fragments)
    implementation(Dependencies.Other.yandexAuthSdk)
}