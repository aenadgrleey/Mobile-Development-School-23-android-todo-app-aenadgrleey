package com.aenadgrleey.todo.data.local

import android.content.Context
import com.aenadgrleey.core.di.AppContext
import com.aenadgrleey.todo.data.local.db.LocalDatabase
import com.aenadgrleey.todo.data.local.models.TodoItemDataDb
import com.aenadgrleey.todo.data.local.utils.TodoItemDataToTodoItemDataDbMapper
import com.aenadgrleey.todo.domain.local.TodoItemsLocalDataSource
import com.aenadgrleey.todo.domain.models.TodoItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/*
 Implementation of local storage that uses Room database
 */
class DatabaseDataSourceImpl @Inject constructor(
    @AppContext
    context: Context,
) : TodoItemsLocalDataSource {

    private val mapper = TodoItemDataToTodoItemDataDbMapper()

    private val dao = LocalDatabase.getDatabase(context).todoItemsDao()

    override fun getTodoItems(excludeCompleted: Boolean): Flow<List<TodoItemData>> =
        dao.getTodoItems(excludeCompleted).map { it.map(TodoItemDataDb::toTodoItemData) }

    override suspend fun getTodoItems(): List<TodoItemData> = dao.getTodoItems().map(TodoItemDataDb::toTodoItemData)
    override suspend fun todoItem(id: String): TodoItemData? = dao.todoItem(id)?.toTodoItemData()

    override fun clearDatabase() {
        dao.clearDatabase()
    }

    override fun completedItemsCount(): Flow<Int> = dao.completedItemsCount()

    override suspend fun addTodoItem(todoItem: TodoItemData) {
        dao.addTodoItem(mapper.map(todoItem))
    }

    override suspend fun deleteTodoItem(todoItem: TodoItemData) {
        dao.deleteTodoItem(mapper.map(todoItem))
    }

}