package nz.co.warehouseandroidtest.main.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.BaseFragment
import nz.co.warehouseandroidtest.common.Status
import nz.co.warehouseandroidtest.common.USERID
import nz.co.warehouseandroidtest.common.ViewModelFactory
import nz.co.warehouseandroidtest.main.presentation.viewmodel.MainViewModel
import javax.inject.Inject


class MainFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mMainViewModel: MainViewModel

    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMainViewModel =
            ViewModelProviders.of(requireActivity(), viewModelFactory)
                .get(MainViewModel::class.java)

        val userId = sharedPrefUtil.getString(USERID)

        if (userId.isNullOrEmpty()){
            mMainViewModel.getUserId()
        }

        mMainViewModel.userResult.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {

                when (it.status) {
                    Status.LOADING -> {
                        //TODO
                    }
                    Status.ERROR -> {
                        //TODO
                    }
                    Status.SUCCESS -> {
                        val response = it.data
                        val id = response?.userID.toString()
                        sharedPrefUtil.putString(USERID, id )
                    }
                }
            }
        })

    }



}
