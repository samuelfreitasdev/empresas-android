package br.com.sf.empresas_android.ui.company_detail

import br.com.sf.empresas_android.repository.Company
import br.com.sf.empresas_android.ui.base.BasePresenter
import br.com.sf.empresas_android.ui.base.BaseView
import br.com.sf.empresas_android.ui.list_companies.CompaniesFragmentContract

interface CompanyDetailContract {

    interface View : BaseView<Presenter> {

        fun showCompany(company: Company)

    }

    interface Presenter : BasePresenter {

        fun setCompany(company: Company)

        fun goBack()

    }

}