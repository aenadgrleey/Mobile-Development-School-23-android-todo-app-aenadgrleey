package com.aenadgrleey.todo.refactor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aenadgrleey.todo.domain.models.Importance
import com.aenadgrleey.todo.domain.repository.TodoItemRepository
import com.aenadgrleey.todo.refactor.ui.model.TodoItemDataToUiStateMapper
import com.aenadgrleey.todo.refactor.ui.model.UiAction
import com.aenadgrleey.todo.refactor.ui.model.UiEvent
import com.aenadgrleey.todo.refactor.ui.model.UiState
import com.aenadgrleey.todo.refactor.ui.model.UiStateToTodoItemData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import javax.inject.Provider

@OptIn(FlowPreview::class)
class TodoRefactorViewModel @Inject constructor(
    private val repository: TodoItemRepository,
) : ViewModel() {

    private val uiStateDataMapper = UiStateToTodoItemData()
    private val dataUiStateMapper = TodoItemDataToUiStateMapper()

    val uiEvents: Flow<UiEvent> get() = mUiEvents.receiveAsFlow()
    private var mUiEvents = Channel<UiEvent>()

    val uiState: StateFlow<UiState?> get() = mUiState
    private var mUiState = MutableStateFlow<UiState?>(null)
    private var mTodoItemIsSet = false

    fun onUiAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnImportanceChange -> changeImportance(uiAction.importance)
                is UiAction.OnTextChange -> changeText(uiAction.text)
                is UiAction.OnCompletenessChange -> changeCompleteness(uiAction.completeness)
                is UiAction.OnDeadlineChange -> changeDeadline(uiAction.enabled, uiAction.deadline)
                UiAction.OnSaveRequest -> saveTodoItem()
                UiAction.OnDeleteRequest -> deleteTodoItem()
                UiAction.OnExitRequest -> mUiEvents.send(UiEvent.ExitRequest)
                is UiAction.InitTodoItem -> setTodoItem(uiAction.id)
                UiAction.ResetTodoItem -> mTodoItemIsSet = false
            }
        }
    }

    private fun setTodoItem(todoItemId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!mTodoItemIsSet)
                if (todoItemId != null) {
                    mTodoItemIsSet = true
                    mUiState.value = dataUiStateMapper.map(repository.todoItem(todoItemId))
                } else
                    mUiState.value = UiState(
                        id = null,
                        lastModifiedBy = null,
                        created = null,
                        lastModified = null
                    )
        }
    }

    private fun changeText(text: String) {
        mUiState.value = mUiState.value!!.copy(text = text, textError = false)
    }

    private fun changeImportance(importance: Importance) {
        mUiState.value = mUiState.value!!.copy(importance = importance)
    }

    private fun changeCompleteness(completeness: Boolean) {
        mUiState.value = mUiState.value!!.copy(completeness = completeness)
    }

    private fun changeDeadline(enabled: Boolean, deadline: Date?) {
        mUiState.value = mUiState.value!!.copy(deadlineEnabled = enabled, deadline = deadline)
    }

    private fun saveTodoItem() {
        mUiState.value?.let {
            viewModelScope.launch {
                if (it.text.isNullOrBlank()) {
                    mUiState.value = it.copy(textError = true)
                    return@launch
                }
                mUiEvents.send(UiEvent.ExitRequest)
                repository.addTodoItem(uiStateDataMapper.map(it))
            }
        }
    }

    private fun deleteTodoItem() {
        mUiState.value?.let {
            viewModelScope.launch {
                repository.deleteTodoItem(uiStateDataMapper.map(it))
                mUiEvents.send(UiEvent.ExitRequest)
            }
        }
    }


    class ViewModelFactory @Inject constructor(
        sharedViewModelProvider: Provider<TodoRefactorViewModel>,
    ) : ViewModelProvider.Factory {

        private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
            TodoRefactorViewModel::class.java to sharedViewModelProvider
        )

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return providers[modelClass]!!.get() as T
        }
    }
}