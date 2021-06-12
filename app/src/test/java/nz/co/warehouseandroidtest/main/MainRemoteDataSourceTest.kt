package nz.co.warehouseandroidtest.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.main.data.models.UserResponse
import nz.co.warehouseandroidtest.main.data.remote.services.MainService
import nz.co.warehouseandroidtest.main.data.remote.source.MainRemoteDataSource
import nz.co.warehouseandroidtest.main.data.remote.source.MainRemoteDataSourceImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainRemoteDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var remoteDataSource: MainRemoteDataSource
    private lateinit var service: MainService

    private val fakeUserResponse = TestUtils.fakeUserResponse


    @Before
    fun init() {

        service = mock {
            onBlocking { getUserIdAsync() } doReturn GlobalScope.async {
                Response.success(fakeUserResponse)
            }
        }

        remoteDataSource = MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testGetUserResponse() = runBlocking {

        service = mock {
            onBlocking { getUserIdAsync() } doReturn GlobalScope.async {
                Response.success(fakeUserResponse)
            }
        }

        remoteDataSource =
            MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.getUserId()

        assert(result == fakeUserResponse)
    }

    @Test(expected = Exception::class)
    fun testUserThrowUserException() = runBlocking {

        service = mock {
            onBlocking { getUserIdAsync() } doReturn GlobalScope.async {
                Response.error<UserResponse>(404, null)
            }
        }

        remoteDataSource =
            MainRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        assert(remoteDataSource.getUserId() == fakeUserResponse)

    }
}