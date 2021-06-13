package nz.co.warehouseandroidtest.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import nz.co.warehouseandroidtest.LiveDataTestUtil
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.common.Status
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.main.domain.MainUseCase
import nz.co.warehouseandroidtest.main.presentation.viewmodel.MainViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import nz.co.warehouseandroidtest.main.data.models.UserResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.delay
import nz.co.warehouseandroidtest.main.data.repository.MainRepository
import nz.co.warehouseandroidtest.main.domain.MainUseCaseImpl


class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel

    lateinit var useCase: MainUseCase

    @Before
    fun init() {
        useCase = mock ()
    }


    @Test
    fun testUserLoadingData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getUserId() } doReturn object :
                LiveData<Result<UserResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }

        viewModel = MainViewModel(useCase)
        viewModel.getUserId()
        val result = viewModel.userResult
        result.observeForever {}
        delay(2000)
        assert(LiveDataTestUtil.getValue(result).peekContent().status == Status.LOADING)

    }

    @Test
    fun testUserErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getUserId() } doReturn object :
                LiveData<Result<UserResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = MainViewModel(useCase)
        viewModel.getUserId()
        val result = viewModel.userResult
        result.observeForever {}
        delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).peekContent().status == Status.ERROR && LiveDataTestUtil.getValue(result).peekContent().message == "error"
        )
    }

    @Test
    fun testUserFetchData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getUserId() } doReturn object :
                LiveData<Result<UserResponse>>() {
                init {
                    value = Result.error("error")
                }
            }

            onBlocking { getUserId() } doReturn object :
                LiveData<Result<UserResponse>>() {
                init {
                    value = Result.success(TestUtils.fakeUserResponse)
                }
            }
        }

        viewModel = MainViewModel(useCase)

        val result = viewModel.userResult
        viewModel.getUserId()
        result.observeForever {}
        assert(
            LiveDataTestUtil.getValue(result).peekContent().status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).peekContent().data == TestUtils.fakeUserResponse
        )

    }

}