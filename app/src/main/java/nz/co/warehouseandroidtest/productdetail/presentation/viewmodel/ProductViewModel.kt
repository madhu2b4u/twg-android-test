package nz.co.warehouseandroidtest.productdetail.presentation.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import nz.co.warehouseandroidtest.common.Event
import nz.co.warehouseandroidtest.common.Result
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse
import javax.inject.Inject
import nz.co.warehouseandroidtest.productdetail.domain.ProductUseCase
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse


class ProductViewModel @Inject constructor(
    private val mProductUseCase: ProductUseCase
) : ViewModel() {

    val barcode = MutableLiveData<String>()

    fun setBarcode(msg:String){
        barcode.value = msg
    }

    val productResult = MediatorLiveData<Event<Result<ProductDetailsResponse>>>()

    fun getProduct(map : Map<String, Any>) {
        viewModelScope.launch {
            productResult.addSource(mProductUseCase.getProductPrice(map)) {
                productResult.value = Event(it)
            }
        }
    }

}