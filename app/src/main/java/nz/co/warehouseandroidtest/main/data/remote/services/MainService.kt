package nz.co.warehouseandroidtest.main.data.remote.services

import kotlinx.coroutines.Deferred
import nz.co.warehouseandroidtest.main.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface MainService {

    @GET("bolt/newuser.json")
    fun getUserIdAsync(): Deferred<Response<UserResponse>>
}