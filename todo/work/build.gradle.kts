plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("todo.work")
}

dependencies {
    implementation(project(":core:di"))
    implementation(project(":core:domain"))
    implementation(project(":todo:domain"))
    implementation(project(":todo:notifications:domain"))
    implementation(Dependencies.Other.workManager)
}