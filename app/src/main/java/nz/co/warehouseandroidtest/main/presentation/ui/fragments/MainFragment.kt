package nz.co.warehouseandroidtest.main.presentation.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.*
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
                        activity?.snackBarError(it.message.toString())
                    }
                    Status.SUCCESS -> {
                        val response = it.data
                        val id = response?.userID.toString()
                        Log.e("userId", id)
                        sharedPrefUtil.putString(USERID, id)
                        navigateToSearchProduct()
                    }
                }
            }
        })
    }

    private fun setViews() {
        val userId = sharedPrefUtil.getString(USERID)
        if (userId.isNullOrEmpty()){
            mMainViewModel.getUserId()
        }
    }

    private fun navigateToSearchProduct(){
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
