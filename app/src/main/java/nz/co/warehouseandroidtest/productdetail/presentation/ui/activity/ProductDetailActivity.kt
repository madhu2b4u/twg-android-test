package nz.co.warehouseandroidtest.productdetail.presentation.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.ViewModelFactory
import nz.co.warehouseandroidtest.productdetail.presentation.viewmodel.ProductViewModel
import javax.inject.Inject

class ProductDetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mProductViewModel: ProductViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val barcodeResult = intent.getStringExtra("barcode")

        mProductViewModel  = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductViewModel::class.java)

        mProductViewModel.setBarcode(barcodeResult)

    }
}