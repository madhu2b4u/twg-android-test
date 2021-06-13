package nz.co.warehouseandroidtest.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import nz.co.warehouseandroidtest.LiveDataTestUtil
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.common.Status
import nz.co.warehouseandroidtest.search.data.remote.source.SearchRemoteDataSource
import nz.co.warehouseandroidtest.search.data.repository.SearchRepository
import nz.co.warehouseandroidtest.search.data.repository.SearchRepositoryImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class SearchRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: SearchRepository

    @Mock
    lateinit var remoteDataSource: SearchRemoteDataSource

    private val response = TestUtils.fakeSearchResult

    private val request = TestUtils.mapSearchQuery("bulb")

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = SearchRepositoryImpl(remoteDataSource)
    }

    @Test
    fun testSearchFromAPI() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.getSearchResults(request)).thenReturn(response)
        val result = repository.getSearchResults(request)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == response)
    }


    @Test(expected = Exception::class)
    fun testGetSearchResultThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.getSearchResults(request))
        repository.getSearchResults(request)

    }

}
