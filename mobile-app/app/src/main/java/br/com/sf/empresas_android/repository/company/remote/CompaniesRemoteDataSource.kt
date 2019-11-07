package br.com.sf.empresas_android.repository.company.remote

import br.com.sf.empresas_android.repository.CompanyListDTO
import br.com.sf.empresas_android.repository.Login
import br.com.sf.empresas_android.repository.SessionHeader
import br.com.sf.empresas_android.repository.company.CompaniesDataSource
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


class CompaniesRemoteDataSource : CompaniesDataSource {

    private val service = createService()

    private var sessionHeader = SessionHeader("", "", "")

    override fun login(username: String, pass: String) =
        service.login(Login(username, pass))
            .flatMapCompletable { response: retrofit2.Response<Any> ->
                saveAuthHeaders(response); return@flatMapCompletable Completable.complete()
            }

    override fun findCompanies() = service.findCompanies(
        sessionHeader.uid,
        sessionHeader.client,
        sessionHeader.accessToken
    )

    override fun findCompanies2() = service.findCompanies2(
        sessionHeader.uid,
        sessionHeader.client,
        sessionHeader.accessToken
    )

    private fun createService(): Service {
        val interceptor = HttpLoggingInterceptor()
            .apply { this.level = HttpLoggingInterceptor.Level.BASIC }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(Service::class.java);
    }

    private fun saveAuthHeaders(response: retrofit2.Response<Any>) {
        val uid = response.headers()["uid"].orEmpty()
        val client = response.headers()["client"].orEmpty()
        val accessToken = response.headers()["access-token"].orEmpty()
        sessionHeader = SessionHeader(uid, accessToken, client)
    }

    private interface Service {

        @POST("users/auth/sign_in")
        fun login(@Body login: Login): Single<retrofit2.Response<Any>>

        @GET("enterprises")
        fun findCompanies(
            @Header("uid") uid: String,
            @Header("client") client: String,
            @Header("access-token") accessToken: String
        ): Single<CompanyListDTO>

        @GET("enterprises")
        fun findCompanies2(
            @Header("uid") uid: String,
            @Header("client") client: String,
            @Header("access-token") accessToken: String
        ): Single<ResponseBody>

    }

    companion object {
        const val SERVICE_URL = "https://empresas.ioasys.com.br/api/v1/"
    }
}