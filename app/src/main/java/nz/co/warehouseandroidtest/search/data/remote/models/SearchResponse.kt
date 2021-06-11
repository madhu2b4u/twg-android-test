package nz.co.warehouseandroidtest.search.data.remote.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class SearchResponse(
    @Expose @SerializedName("Found")
    val found: String,
    @Expose @SerializedName("HitCount")
    val hitCount: String,
    @Expose @SerializedName("ProdQAT")
    val prodQAT: String,
    @Expose @SerializedName("Results")
    val results: List<SearchResult>,
    @Expose @SerializedName("SearchID")
    val searchID: String
) : Parcelable