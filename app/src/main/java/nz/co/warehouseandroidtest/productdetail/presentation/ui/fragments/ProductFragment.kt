package nz.co.warehouseandroidtest.productdetail.presentation.ui.fragments


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.*
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetail
import nz.co.warehouseandroidtest.productdetail.data.models.ProductDetailsResponse
import nz.co.warehouseandroidtest.productdetail.presentation.viewmodel.ProductViewModel
import javax.inject.Inject


class ProductFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mProductViewModel: ProductViewModel

    private var barcodeOfProduct = ""

    override fun layoutId(): Int {
        return R.layout.fragment_product
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            mProductViewModel =
                ViewModelProviders.of(requireActivity(), viewModelFactory)
                    .get(ProductViewModel::class.java)

            setViews()

            setObservers()
        }
    }

    private fun setViews() {
        ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        ivBack.visibility = View.VISIBLE

        tvTitle.text  = getString(R.string.product_details)
    }


    private fun setObservers() {

        mProductViewModel.barcode.observe(viewLifecycleOwner, Observer<String> {
            barcodeOfProduct= it
            mProductViewModel.getProduct(mapQuery(barcodeOfProduct))
        })

        mProductViewModel.productResult.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                eventObserver(it)
            }
        })
    }

    private fun eventObserver(it: Result<ProductDetailsResponse>): Unit? {
        return when (it.status) {
            Status.LOADING -> {
                //TODO
            }
            Status.ERROR -> {
                activity?.snackBarError(it.message.toString())
            }
            Status.SUCCESS -> {
                val response = it.data?.productDetail
                response?.let { it1 -> setProductViews(it1) }
            }
        }
    }

    private fun setProductViews(response: ProductDetail) {
        Glide.with(this).load(response.imageURL).error(R.drawable.image_placeholder).into(ivProductImage)
        tvBarcode.text = response.barcode
        tvPrice.text = "$"+response.price.price

        if (response.price.type == "CLR") {
            ivClearance.visibility = View.VISIBLE
        } else {
            ivClearance.visibility = View.GONE
        }
    }


    private fun mapQuery(barcode: String): Map<String, Any> {
        return  mapOf("Barcode" to barcode, "MachineID" to MACHINE_ID, "UserID" to sharedPrefUtil.getString(USERID).toString())
    }




}
