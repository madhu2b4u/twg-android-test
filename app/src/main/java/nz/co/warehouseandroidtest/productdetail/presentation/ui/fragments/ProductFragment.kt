package nz.co.warehouseandroidtest.productdetail.presentation.ui.fragments


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.BaseFragment
import nz.co.warehouseandroidtest.common.ViewModelFactory
import nz.co.warehouseandroidtest.productdetail.presentation.viewmodel.ProductViewModel
import javax.inject.Inject


class ProductFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mProductViewModel: ProductViewModel

    override fun layoutId(): Int {
        return R.layout.fragment_product
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            mProductViewModel =
                ViewModelProviders.of(requireActivity(), viewModelFactory)
                    .get(ProductViewModel::class.java)

        }
    }

}
