package com.aenadgrleey.tobedone.data.network.requests

import com.aenadgrleey.tobedone.data.models.TodoItemData
import com.google.gson.annotations.SerializedName

class TodoItemsListRequest(
    @SerializedName("list")
    val todoItemsList: List<TodoItemData>,
)