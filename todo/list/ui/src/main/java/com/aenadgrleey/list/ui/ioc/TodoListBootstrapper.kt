package com.aenadgrleey.list.ui.ioc

import com.aenadgrleey.core.di.ViewScope
import com.aenadgrleey.list.ui.TodoListViewModel
import com.aenadgrleey.list.ui.model.UiAction
import javax.inject.Inject

@ViewScope
class TodoListBootstrapper
@Inject constructor(
    viewModel: TodoListViewModel,
    toolbarController: ExpendableToolbarViewController,
    fabController: FabViewController,
    recyclerViewController: TodosRecyclerViewController,
    refreshViewController: SwipeRefreshViewController,
    coordinatorViewController: CoordinatorViewController,
) {
    init {
        toolbarController.setUpToolbar()
        fabController.setUpFab()
        recyclerViewController.setUpRecycler()
        refreshViewController.setUpSwipeRefreshLayout()
        coordinatorViewController.setUpCoordinator()
        viewModel.onUiAction(UiAction.RefreshTodoItems)
    }
}