package nz.co.warehouseandroidtest.search.data.remote.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable


@Parcelize
data class Product(
    @Expose @SerializedName("Barcode")
    val barcode: String,
    @Expose @SerializedName("Class0")
    val class0: String,
    @Expose @SerializedName("Class0ID")
    val class0ID: String,
    @Expose @SerializedName("ClassID")
    val classID: String,
    @Expose @SerializedName("Class")
    val classX: String,
    @Expose @SerializedName("Dept")
    val dept: String,
    @Expose @SerializedName("DeptID")
    val deptID: String,
    @Expose @SerializedName("Description")
    var description: String,
    @Expose @SerializedName("ImageURL")
    val imageURL: String,
    @Expose @SerializedName("ItemCode")
    val itemCode: String,
    @Expose @SerializedName("ItemDescription")
    val itemDescription: String,
    @Expose @SerializedName("ProductKey")
    val productKey: String,
    @Expose @SerializedName("SubClass")
    val subClass: String,
    @Expose @SerializedName("SubClassID")
    val subClassID: String,
    @Expose @SerializedName("SubDept")
    val subDept: String,
    @Expose @SerializedName("SubDeptID")
    val subDeptID: String
) : Parcelable