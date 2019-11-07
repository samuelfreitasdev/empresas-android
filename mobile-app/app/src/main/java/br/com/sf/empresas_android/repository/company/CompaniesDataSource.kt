package br.com.sf.empresas_android.repository.company

import br.com.sf.empresas_android.repository.CompanyListDTO
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody

interface CompaniesDataSource {

    fun login(username: String, pass: String): Completable

    fun findCompanies(): Single<CompanyListDTO>

    fun findCompanies2(): Single<ResponseBody>

}