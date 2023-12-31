package com.aenadgrleey.todo.data.remote.retrofit

import com.aenadgrleey.todo.data.remote.network.URLs
import com.aenadgrleey.todo.data.remote.retrofit.requests.TodoItemRequest
import com.aenadgrleey.todo.data.remote.retrofit.requests.TodoItemsListRequest
import com.aenadgrleey.todo.data.remote.retrofit.responses.TodoItemResponse
import com.aenadgrleey.todo.data.remote.retrofit.responses.TodoItemsListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/*
Retrofit client for remote operations
 */
internal interface RetrofitClient {
    @GET(URLs.ITEMS_LIST_URL)
    suspend fun getTodoItems(): Response<TodoItemsListResponse>

    @GET("${URLs.ITEMS_LIST_URL}/{uuid}")
    suspend fun getTodoItem(@Path(value = "uuid", encoded = true) uuid: String): Response<TodoItemResponse>

    @PATCH(URLs.ITEMS_LIST_URL)
    suspend fun sendTodoItems(
        @Header("X-Last-Known-Revision") revision: Int,
        @Body request: TodoItemsListRequest,
    ): Response<TodoItemsListResponse>

    @POST(URLs.ITEMS_LIST_URL)
    suspend fun addTodoItem(
        @Header("X-Last-Known-Revision") revision: Int,
        @Body request: TodoItemRequest,
    ): Response<TodoItemResponse>

    @PUT("${URLs.ITEMS_LIST_URL}/{uuid}")
    suspend fun updateTodoItem(
        @Header("X-Last-Known-Revision") revision: Int,
        @Body request: TodoItemRequest,
        @Path(value = "uuid") uuid: String = request.item.id!!,
    ): Response<TodoItemResponse>

    @DELETE("${URLs.ITEMS_LIST_URL}/{uuid}")
    suspend fun deleteTodoItem(
        @Header("X-Last-Known-Revision") revision: Int,
        @Path(value = "uuid") uuid: String,
    ): Response<TodoItemResponse>

}
