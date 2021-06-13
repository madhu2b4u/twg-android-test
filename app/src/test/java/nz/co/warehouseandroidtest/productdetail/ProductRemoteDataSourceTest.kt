package nz.co.warehouseandroidtest.productdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse
import nz.co.warehouseandroidtest.productdetail.data.remote.services.ProductService
import nz.co.warehouseandroidtest.productdetail.data.remote.source.ProductRemoteDataSource
import nz.co.warehouseandroidtest.productdetail.data.remote.source.ProductRemoteDataSourceImpl
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class ProductRemoteDataSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var remoteDataSource: ProductRemoteDataSource
    private lateinit var service: ProductService

    private val response = TestUtils.fakeProductResult

    private val request = TestUtils.mapProductQuery("1234567890")


    @Before
    fun init() {

        service = mock {
            onBlocking { getProductPriceAsync(request) } doReturn GlobalScope.async {
                Response.success(response)
            }
        }

        remoteDataSource = ProductRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testGetProductResponse() = runBlocking {

        service = mock {
            onBlocking { getProductPriceAsync(request) } doReturn GlobalScope.async {
                Response.success(response)
            }
        }

        remoteDataSource = ProductRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        // Will be launched in the mainThreadSurrogate dispatcher
        val result = remoteDataSource.getProductprice(request)

        assert(result == response)
    }

    @Test(expected = Exception::class)
    fun testProductResultThrowUserException() = runBlocking {

        service = mock {
            onBlocking { getProductPriceAsync(request) } doReturn GlobalScope.async {
                Response.error<ProductDetailsResponse>(404, null)
            }
        }

        remoteDataSource = ProductRemoteDataSourceImpl(service, mainCoroutineRule.coroutineContext)

        assert(remoteDataSource.getProductprice(request) == response)

    }
}