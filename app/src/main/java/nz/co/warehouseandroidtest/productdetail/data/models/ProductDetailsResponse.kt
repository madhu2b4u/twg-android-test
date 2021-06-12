package nz.co.warehouseandroidtest.productdetail.data.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class ProductDetailsResponse(
    @Expose @SerializedName("Action")
    val action: String,
    @Expose @SerializedName("Found")
    val found: String,
    @Expose @SerializedName("MachineID")
    val machineID: String,
    @Expose @SerializedName("ProdQAT")
    val prodQAT: String,
    @Expose @SerializedName("Product")
    val productDetail: ProductDetail,
    @Expose @SerializedName("ScanBarcode")
    val scanBarcode: String,
    @Expose @SerializedName("ScanDateTime")
    val scanDateTime: String,
    @Expose @SerializedName("ScanID")
    val scanID: String,
    @Expose @SerializedName("UserDescription")
    val userDescription: String,
    @Expose @SerializedName("UserID")
    val userID: String
) : Parcelable