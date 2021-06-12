package nz.co.warehouseandroidtest.search.data.remote.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class SearchResult(
    @Expose @SerializedName("Description")
    val description: String,
    @Expose @SerializedName("Products")
    val products: List<Product>
) : Parcelable