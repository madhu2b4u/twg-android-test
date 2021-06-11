package nz.co.warehouseandroidtest.search.data.repository

import androidx.lifecycle.LiveData
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse


interface SearchRepository {


    suspend fun getSearchResults(map : Map<String, Any>): LiveData<Result<SearchResponse>>

}