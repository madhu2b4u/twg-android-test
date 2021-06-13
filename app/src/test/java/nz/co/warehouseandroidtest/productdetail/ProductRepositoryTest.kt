package nz.co.warehouseandroidtest.productdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import nz.co.warehouseandroidtest.LiveDataTestUtil
import nz.co.warehouseandroidtest.MainCoroutineRule
import nz.co.warehouseandroidtest.TestUtils
import nz.co.warehouseandroidtest.common.Status
import nz.co.warehouseandroidtest.productdetail.data.remote.source.ProductRemoteDataSource
import nz.co.warehouseandroidtest.productdetail.data.repository.ProductRepository
import nz.co.warehouseandroidtest.productdetail.data.repository.ProductRepositoryImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class ProductRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: ProductRepository

    private val response = TestUtils.fakeProductResult

    private val request = TestUtils.mapProductQuery("1234567890")

    @Mock
    lateinit var remoteDataSource: ProductRemoteDataSource

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = ProductRepositoryImpl(remoteDataSource)
    }

    @Test
    fun testGetProductFromAPI() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.getProductprice(request)).thenReturn(response)
        val result = repository.getProductPrice(request)
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == response)
    }


    @Test(expected = Exception::class)
    fun testGetProductThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.doThrow(Exception("error")).`when`(remoteDataSource.getProductprice(request))
        repository.getProductPrice(request)

    }

}
