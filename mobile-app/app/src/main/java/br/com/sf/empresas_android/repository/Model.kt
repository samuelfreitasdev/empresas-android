package br.com.sf.empresas_android.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Login(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

@Parcelize
data class Company(
    @SerializedName("enterprise_name") val name: String,
    @SerializedName("enterprise_type") val type: CompanyType,
    @SerializedName("description") val description: String,
    @SerializedName("country") val country: String,
    @SerializedName("photo") val photo: String?
) : Parcelable

data class CompanyListDTO(
    @SerializedName("enterprises") val enterprises: List<Company> = emptyList()
)

@Parcelize
data class CompanyType(
    @SerializedName("id") val id: Int,
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String
) : Parcelable

data class SessionHeader(
    val uid: String,
    val accessToken: String,
    val client: String
)