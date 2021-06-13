package nz.co.warehouseandroidtest.productdetail

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
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse
import nz.co.warehouseandroidtest.productdetail.data.repository.ProductRepository
import nz.co.warehouseandroidtest.productdetail.domain.ProductUseCase
import nz.co.warehouseandroidtest.productdetail.domain.ProductUseCaseImpl
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var useCase: ProductUseCase

    lateinit var repository: ProductRepository

    private val response = TestUtils.fakeProductResult

    private val request = TestUtils.mapProductQuery("1234567890")

    @Test
    fun testProductLoadingData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getProductPrice(request) } doReturn object :
                LiveData<Result<ProductDetailsResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        useCase = ProductUseCaseImpl(repository)

        val result = useCase.getProductPrice(request)
        result.observeForever { }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testProductSuccessData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getProductPrice(request)} doReturn object :
                LiveData<Result<ProductDetailsResponse>>() {
                init {
                    value = Result.success(response)
                }
            }
        }
        useCase = ProductUseCaseImpl(repository)

        val result = useCase.getProductPrice(request)

        result.observeForever { }

        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(
                result
            ).data == response
        )

    }

    @Test
    fun testProductErrorData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getProductPrice(request) } doReturn object :
                LiveData<Result<ProductDetailsResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        useCase = ProductUseCaseImpl(repository)
        val result = useCase.getProductPrice(request)
        result.observeForever { }
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                result
            ).message == "error"
        )

    }


}