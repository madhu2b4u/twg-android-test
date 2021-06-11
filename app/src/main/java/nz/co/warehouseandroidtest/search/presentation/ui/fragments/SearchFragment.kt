package nz.co.warehouseandroidtest.search.presentation.ui.fragments


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.common.*
import nz.co.warehouseandroidtest.search.data.remote.models.Product
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResponse
import nz.co.warehouseandroidtest.search.data.remote.models.SearchResult
import nz.co.warehouseandroidtest.search.presentation.ui.adapter.SearchResultsAdapter
import nz.co.warehouseandroidtest.search.presentation.viewmodel.SearchViewModel
import javax.inject.Inject


class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val limit = 20

    private var startIndex = 0

    private lateinit var mSearchViewModel: SearchViewModel

    private lateinit var searchResultsAdapter : SearchResultsAdapter

    private val data: ArrayList<Product> = ArrayList()

    override fun layoutId(): Int {
        return R.layout.fragment_search
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       setViews()

        lifecycleScope.launchWhenStarted {
            mSearchViewModel =
                ViewModelProviders.of(requireActivity(), viewModelFactory)
                    .get(SearchViewModel::class.java)

            setObservers()

        }
    }

    private fun setObservers() {
        mSearchViewModel.searchResults.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                when (it.status) {
                    Status.LOADING -> {
                        //TODO
                    }
                    Status.ERROR -> {
                        //TODO
                    }
                    Status.SUCCESS -> {
                        etSearch.setText("")
                        val response = it.data
                        response?.let { it1 -> setResultData(it1) }
                    }
                }
            }
        })
    }

    private fun setResultData(searchResult: SearchResponse) {
        data.clear()
        val ifFound: String = searchResult.found
        if (ifFound == "Y") {
            searchResult.results.forEach {
                data.add(it.products[0])
            }
            searchResultsAdapter.updateProducts(data)
        }
    }

    private fun setViews() {
        tvTitle.text  = getString(R.string.search_product)

        searchResultsAdapter = SearchResultsAdapter()
        rvSearchResult.adapter = searchResultsAdapter

        ivBack.visibility = View.VISIBLE

        ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        ivSearch.setOnClickListener {

            val query = etSearch.text.toString()
            hideKeyboard()

            if (query.isNotEmpty()){
                mSearchViewModel.getSearchResults(mapQuery(query))
            }
        }

    }

    private fun mapQuery(query: String): Map<String, Any> {
       return  mapOf("Search" to query, "MachineID" to MACHINE_ID, "UserID" to sharedPrefUtil.getString(USERID).toString(), "Branch" to BRANCH_ID, "Start" to startIndex, "Limit" to limit)
    }


}
