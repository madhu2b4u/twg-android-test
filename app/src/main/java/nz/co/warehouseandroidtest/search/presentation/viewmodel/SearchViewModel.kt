package nz.co.warehouseandroidtest.search.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.warehouseandroidtest.common.Event
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse
import nz.co.warehouseandroidtest.search.domain.SearchUseCase
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    private val mSearchUseCase: SearchUseCase
) : ViewModel() {

    val searchResults = MediatorLiveData<Event<Result<SearchResponse>>>()

    fun getSearchResults(map : Map<String, Any>) {
        viewModelScope.launch {
            searchResults.addSource(mSearchUseCase.getSearchResults(map)) {
                searchResults.value = Event(it)
            }
        }
    }

}