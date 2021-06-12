package nz.co.warehouseandroidtest.main.presentation.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
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

        setViews()

        setObservers()

    }

    private fun setObservers() {
        setUserObserver()
    }

    private fun setUserObserver(){
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
                        tvUserId.text = id
                        sharedPrefUtil.putString(USERID, id)
                    }
                }
            }
        })
    }

    private fun setViews() {
        val userId = sharedPrefUtil.getString(USERID)

        if (userId.isNullOrEmpty()){
            mMainViewModel.getUserId()
        }else{
            Log.e("userId", userId)
            tvUserId.text = userId
        }

        tv_search.setOnClickListener {
            view.let {
                if (it != null) {
                    Navigation.findNavController(it)
                        .navigate(
                            R.id.action_navigate_search
                        )
                }
            }
        }



    }




}
