package com.aenadgrleey.tobedone.ioc

import javax.inject.Inject

class TodoActivityBootstrapper @Inject constructor(
    navigationController: NavigationController,
    themeController: ThemeController,
    permissionController: PermissionController,
    workerController: WorkerController,
) {
    init {
        navigationController.setUpNavigationControl()
        themeController.setUpThemeControl()
        permissionController.setUpPermissionControl()
        workerController.setUpWorkers()
    }
}