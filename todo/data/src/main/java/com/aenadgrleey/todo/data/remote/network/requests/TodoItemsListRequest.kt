package com.aenadgrleey.todo.data.remote.network.requests

import com.aenadgrleey.todo.domain.models.TodoItemData
import com.google.gson.annotations.SerializedName

internal class TodoItemsListRequest(
    @SerializedName("list")
    val todoItemsList: List<TodoItemData>,
)