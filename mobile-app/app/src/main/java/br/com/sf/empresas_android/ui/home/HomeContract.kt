package br.com.sf.empresas_android.ui.home

import br.com.sf.empresas_android.ui.base.BasePresenter
import br.com.sf.empresas_android.ui.base.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator(show: Boolean)

        fun showLoginErrorWarning()
    }

    interface Presenter : BasePresenter {

        fun clickLogin(username: String, password: String)

    }

}