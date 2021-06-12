package nz.co.warehouseandroidtest.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import nz.co.warehouseandroidtest.LiveDataTestUtil
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.common.Status
import nz.co.warehouseandroidtest.main.data.remote.source.MainRemoteDataSource
import nz.co.warehouseandroidtest.main.data.repository.MainRepository
import nz.co.warehouseandroidtest.main.data.repository.MainRepositoryImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class MainRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: MainRepository

    @Mock
    lateinit var remoteDataSource: MainRemoteDataSource

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = MainRepositoryImpl(remoteDataSource)
    }

    @Test
    fun testGetCategoriesFromAPI() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.getUserId()).thenReturn(TestUtils.fakeUserResponse)
        val result = repository.getUserId()
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils.fakeUserResponse)
    }


    @Test(expected = Exception::class)
    fun testGetCategoriesThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.getUserId())
        repository.getUserId()

    }

}
