package nz.co.warehouseandroidtest.productdetail

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
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse
import nz.co.warehouseandroidtest.productdetail.domain.ProductUseCase
import nz.co.warehouseandroidtest.productdetail.presentation.viewmodel.ProductViewModel


class ProductViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: ProductViewModel

    lateinit var useCase: ProductUseCase

    private val response = TestUtils.fakeProductResult

    private val request = TestUtils.mapProductQuery("1234567890")

    @Before
    fun init() {
        useCase = mock ()
    }


    @Test
    fun testProductLoadingData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getProductPrice(request) } doReturn object :
                LiveData<Result<ProductDetailsResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }

        viewModel = ProductViewModel(useCase)
        viewModel.getProduct(request)
        val result = viewModel.productResult
        result.observeForever {}
        delay(2000)
        assert(LiveDataTestUtil.getValue(result).peekContent().status == Status.LOADING)

    }

    @Test
    fun testProductErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getProductPrice(request) } doReturn object :
                LiveData<Result<ProductDetailsResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = ProductViewModel(useCase)
        viewModel.getProduct(request)
        val result = viewModel.productResult
        result.observeForever {}
        delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).peekContent().status == Status.ERROR && LiveDataTestUtil.getValue(result).peekContent().message == "error"
        )
    }

    @Test
    fun testProductFetchData() = mainCoroutineRule.runBlockingTest {

        useCase = mock {
            onBlocking { getProductPrice(request) } doReturn object :
                LiveData<Result<ProductDetailsResponse>>() {
                init {
                    value = Result.error("error")
                }
            }

            onBlocking { getProductPrice(request)} doReturn object :
                LiveData<Result<ProductDetailsResponse>>() {
                init {
                    value = Result.success(response)
                }
            }
        }

        viewModel = ProductViewModel(useCase)
        viewModel.getProduct(request)
        val result = viewModel.productResult
        result.observeForever {}
        assert(
            LiveDataTestUtil.getValue(result).peekContent().status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).peekContent().data == response
        )

    }

}