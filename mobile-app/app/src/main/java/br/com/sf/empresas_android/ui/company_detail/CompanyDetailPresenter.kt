package br.com.sf.empresas_android.ui.company_detail

import br.com.sf.empresas_android.injection.PerFragment
import br.com.sf.empresas_android.repository.Company
import br.com.sf.empresas_android.ui.main.MainContract
import javax.inject.Inject

@PerFragment
class CompanyDetailPresenter @Inject constructor(
    private val view: CompanyDetailContract.View,
    private val navigator: MainContract.Navigator
) : CompanyDetailContract.Presenter {

    override fun setCompany(company: Company) {
        view.showCompany(company)
    }

    override fun goBack() {
        navigator.goToCompanyList()
    }

    override fun destroy() {
    }
}