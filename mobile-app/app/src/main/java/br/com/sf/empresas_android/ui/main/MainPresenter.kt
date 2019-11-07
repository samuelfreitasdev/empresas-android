package br.com.sf.empresas_android.ui.main

import br.com.sf.empresas_android.injection.PerActivity
import javax.inject.Inject

@PerActivity
internal class MainPresenter @Inject
constructor(private var view: MainContract.View, private var navigator: MainContract.Navigator) :
    MainContract.Presenter {

    override fun init() {
        navigator.goToLogin()
    }

    override fun clickLogin() {
        navigator.goToCompanyList()
    }

    override fun destroy() {
    }
}
