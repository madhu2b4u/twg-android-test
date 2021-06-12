package nz.co.warehouseandroidtest.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import nz.co.warehouseandroidtest.LiveDataTestUtil
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.common.Status
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.main.data.models.UserResponse
import nz.co.warehouseandroidtest.main.data.repository.MainRepository
import nz.co.warehouseandroidtest.main.domain.MainUseCase
import nz.co.warehouseandroidtest.main.domain.MainUseCaseImpl
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var useCase: MainUseCase

    lateinit var repository: MainRepository

    @Test
    fun testEpisodesLoadingData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getUserId() } doReturn object :
                LiveData<Result<UserResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        useCase = MainUseCaseImpl(repository)

        val result = useCase.getUserId()
        result.observeForever { }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testEpisodesSuccessData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getUserId() } doReturn object :
                LiveData<Result<UserResponse>>() {
                init {
                    value = Result.success(TestUtils.fakeUserResponse)
                }
            }
        }
        useCase = MainUseCaseImpl(repository)

        val result = useCase.getUserId()

        result.observeForever { }

        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(
                result
            ).data == TestUtils.fakeUserResponse
        )

    }

    @Test
    fun testEpisodesErrorData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getUserId() } doReturn object :
                LiveData<Result<UserResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        useCase = MainUseCaseImpl(repository)
        val result = useCase.getUserId()
        result.observeForever { }
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                result
            ).message == "error"
        )

    }


}