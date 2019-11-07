package br.com.sf.empresas_android.ui.main

import br.com.sf.empresas_android.repository.Company
import br.com.sf.empresas_android.ui.base.BaseNavigator
import br.com.sf.empresas_android.ui.base.BasePresenter
import br.com.sf.empresas_android.ui.base.BaseView

interface MainContract {

    interface Navigator : BaseNavigator {

        enum class State {
            SINGLE_COLUMN_MASTER, SINGLE_COLUMN_DETAILS, TWO_COLUMNS_EMPTY, TWO_COLUMNS_WITH_DETAILS
        }

        fun goToLogin()

        fun goToCompanyList()

        fun goToCompanyDetail(company: Company)

        fun onBackPressed(): Boolean

    }

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter {

        fun init()

        fun clickLogin()

    }

}
