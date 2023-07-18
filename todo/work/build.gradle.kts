plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("todo.work")
}

dependencies {
    implementation(project(":core:di"))
    implementation(project(":core:data"))
    implementation(project(":todo:domain"))
    implementation(Dependencies.Other.workManager)
}