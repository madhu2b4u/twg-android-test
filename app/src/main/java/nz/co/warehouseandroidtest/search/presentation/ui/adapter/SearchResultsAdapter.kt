package nz.co.warehouseandroidtest.search.presentation.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_search_result.view.*
import nz.co.warehouseandroidtest.R
import nz.co.warehouseandroidtest.search.data.remote.models.Product


class SearchResultsAdapter() : RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {


    private var listOfProducts = ArrayList<Product>()

    private var clickFunction: ((post: Product, pos:Int) -> Unit)? = null

    fun clickListener(clickFunction: (Product, Int) -> Unit) {
        this.clickFunction = clickFunction
    }

    fun clearItems(){
        listOfProducts.clear()
        notifyDataSetChanged()
    }

    fun updateProducts(products: ArrayList<Product>){
        listOfProducts.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_search_result, parent, false)
        return SearchResultsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfProducts.size
    }

    override fun onBindViewHolder(holderSuggested: SearchResultsViewHolder, position: Int) {
        return holderSuggested.bind(listOfProducts[position],position)
    }

    inner class SearchResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product, position: Int) {
            with(itemView) {
                setOnClickListener {
                    clickFunction?.invoke(product,position)
                }
                if (!TextUtils.isEmpty(product.description)) {
                    tvProductDesc.text = product.description
                }

                if (!TextUtils.isEmpty(product.imageURL)) {
                    Glide.with(context).load(product.imageURL).error(R.drawable.image_placeholder).into(ivProduct)
                }
            }
        }
    }
}




