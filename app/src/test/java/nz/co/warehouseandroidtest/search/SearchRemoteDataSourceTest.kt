package nz.co.warehouseandroidtest.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse
import nz.co.warehouseandroidtest.search.data.remote.services.SearchService
import nz.co.warehouseandroidtest.search.data.remote.source.SearchRemoteDataSource
import nz.co.warehouseandroidtest.search.data.remote.source.SearchRemoteDataSourceImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchRemoteDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var remoteDataSource: SearchRemoteDataSource
    private lateinit var service: SearchService

    private val response = TestUtils.fakeSearchResult

    private val request = TestUtils.mapSearchQuery("bulb")


    @Before
    fun init() {

        service = mock {
            onBlocking { getSearchResultAsync(TestUtils.mapSearchQuery("bulb")) } doReturn GlobalScope.async {
                Response.success(response)
            }
        }

        remoteDataSource = SearchRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testGetSearchResponse() = runBlocking {

        service = mock {
            onBlocking { getSearchResultAsync(request) } doReturn GlobalScope.async {
                Response.success(response)
            }
        }

        remoteDataSource =
            SearchRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.getSearchResults(request)

        assert(result == response)
    }

    @Test(expected = Exception::class)
    fun testSearchResultThrowUserException() = runBlocking {

        service = mock {
            onBlocking { getSearchResultAsync(request) } doReturn GlobalScope.async {
                Response.error<SearchResponse>(404, null)
            }
        }

        remoteDataSource =
            SearchRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        assert(remoteDataSource.getSearchResults(request) == response)

    }
}