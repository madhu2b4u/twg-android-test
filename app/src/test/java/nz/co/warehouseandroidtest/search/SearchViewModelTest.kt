package nz.co.warehouseandroidtest.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import nz.co.warehouseandroidtest.LiveDataTestUtil
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.common.Status
import nz.co.warehouseandroidtest.common.Result
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.delay
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse
import nz.co.warehouseandroidtest.search.domain.SearchUseCase
import nz.co.warehouseandroidtest.search.presentation.viewmodel.SearchViewModel


class SearchViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: SearchViewModel

    lateinit var useCase: SearchUseCase

    private val response = TestUtils.fakeSearchResult

    private val request = TestUtils.mapSearchQuery("bulb")

    @Before
    fun init() {
        useCase = mock ()
    }


    @Test
    fun testSearchLoadingData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getSearchResults(request) } doReturn object :
                LiveData<Result<SearchResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }

        viewModel = SearchViewModel(useCase)
        viewModel.getSearchResults(request)
        val result = viewModel.searchResults
        result.observeForever {}
        delay(2000)
        assert(LiveDataTestUtil.getValue(result).peekContent().status == Status.LOADING)

    }

    @Test
    fun testSearchErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getSearchResults(request) } doReturn object :
                LiveData<Result<SearchResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = SearchViewModel(useCase)
        viewModel.getSearchResults(request)
        val result = viewModel.searchResults
        result.observeForever {}
        delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).peekContent().status == Status.ERROR && LiveDataTestUtil.getValue(result).peekContent().message == "error"
        )
    }

    @Test
    fun testSearchFetchData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getSearchResults(request) } doReturn object :
                LiveData<Result<SearchResponse>>() {
                init {
                    value = Result.error("error")
                }
            }

            onBlocking { getSearchResults(request)} doReturn object :
                LiveData<Result<SearchResponse>>() {
                init {
                    value = Result.success(response)
                }
            }
        }

        viewModel = SearchViewModel(useCase)
        viewModel.getSearchResults(request)
        val result = viewModel.searchResults
        result.observeForever {}
        assert(
            LiveDataTestUtil.getValue(result).peekContent().status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).peekContent().data == response
        )

    }

}