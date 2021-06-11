package nz.co.warehouseandroidtest.main.data.remote.source

import kotlinx.coroutines.withContext
import nz.co.warehouseandroidtest.di.qualifiers.IO
import nz.co.warehouseandroidtest.main.data.remote.services.MainService
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class MainRemoteDataSourceImpl @Inject constructor(
    private val service: MainService,
    @IO private val context: CoroutineContext
) : MainRemoteDataSource {
    override suspend fun getUserId()= withContext(context) {

        val response = service.getUserIdAsync().await()
        if (response.isSuccessful)
            response.body()?: throw Exception("no response")
        else
            throw Exception("invalid request with code ${response.code()}")
    }


}
