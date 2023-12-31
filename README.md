# ToBeDone

ToBeDone is a versatile application that allows users to securely store
personal notes in both the cloud and locally, providing convenient
access and data backup.

## Tech stack

Following technologies were used during development process:

* Kotlin Coroutines and Flow

* View and Compose for the user interface

* Material Design components for a sleek and intuitive UI

* Dagger 2 for dependency injection

* Room for local data storage

* Retrofit and Gson for API communication

* YandexAuthSdk for authentication

* WorkManager for background tasks

* DataStore for efficient data management.

* Gradle with Kotlin DSL as a build tool

## Architecture

1) App follows a modern multi-module paradigm, which is a structured
   approach to organizing the codebase, ensuring modularity and
   maintainability

2) Throughout the development process, the principles of SOLID and CLEAN
   architecture have been meticulously adhered to. These principles have
   been instrumental in shaping the codebase to be not only extensible but
   also highly readable, making it easier for developers to collaborate and
   maintain the application

3) By implementing the MVI pattern and combining it with the power of
   Jetpack Compose, ToBeDone achieves a highly declarative and maintainable
   architecture. This not only enhances the user experience but also makes
   it easier for developers to extend and improve the application over
   time.

   ### *Currently remote API is not working so cloud sync is not available 

## App interface

|       **Dark them**                                                                                                                                 |                   **Light theme**                                                                                                                                  |
|----------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Todo list**                                                                                                                                          |                                                                                                                                                     |
| <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/dark_main_1.png" width="300">   | <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/light_main_1.png" width="300">   |
| <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/dark_main_2.png" width="300">   | <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/light_main_2.png" width="300">   |
| **Todo edit**                                                                                                                                          |                                                                                                                                                     |
| <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/dark_edit.png" width="300">     | <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/light_edit.png" width="300">     |
|  **App settings**                                                                                                                                       |                                                                                                                                                     |
| <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/dark_settings.png" width="300"> | <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/light_settings.png" width="300"> |
|  **Authorization**                                                                                                                                      |                                                                                                                                                     |
| <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/dark_auth.png" width="300">     | <img src="https://github.com/aenadgrleey/Mobile-Development-School-23-android-todo-app-aenadgrleey/blob/main/media/light_auth.png" width="300">     |
