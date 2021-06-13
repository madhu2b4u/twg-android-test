package nz.co.warehouseandroidtest.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import nz.co.warehouseandroidtest.LiveDataTestUtil
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.common.Status
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse
import nz.co.warehouseandroidtest.search.data.repository.SearchRepository
import nz.co.warehouseandroidtest.search.domain.SearchUseCase
import nz.co.warehouseandroidtest.search.domain.SearchUseCaseImpl
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var useCase: SearchUseCase

    lateinit var repository: SearchRepository

    private val response = TestUtils.fakeSearchResult

    private val request = TestUtils.mapSearchQuery("bulb")

    @Test
    fun testSearchLoadingData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getSearchResults(request) } doReturn object :
                LiveData<Result<SearchResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        useCase = SearchUseCaseImpl(repository)

        val result = useCase.getSearchResults(request)
        result.observeForever { }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testSearchSuccessData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getSearchResults(request)} doReturn object :
                LiveData<Result<SearchResponse>>() {
                init {
                    value = Result.success(response)
                }
            }
        }
        useCase = SearchUseCaseImpl(repository)

        val result = useCase.getSearchResults(request)

        result.observeForever { }

        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(
                result
            ).data == response
        )

    }

    @Test
    fun testSearchErrorData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getSearchResults(request) } doReturn object :
                LiveData<Result<SearchResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        useCase = SearchUseCaseImpl(repository)
        val result = useCase.getSearchResults(request)
        result.observeForever { }
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                result
            ).message == "error"
        )

    }


}