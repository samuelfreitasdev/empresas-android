package br.com.sf.empresas_android.ui.list_companies

import br.com.sf.empresas_android.repository.Company
import br.com.sf.empresas_android.ui.base.BasePresenter
import br.com.sf.empresas_android.ui.base.BaseView

interface CompaniesFragmentContract {

    interface View : BaseView<Presenter> {

        fun setCompanies(companies: List<Company>)

        fun showLoadingIndicator(show: Boolean)

        fun showEmptyState()

    }

    interface Presenter : BasePresenter {

        fun start()

        fun clickCompany(company: Company)

    }

}