package br.com.sf.empresas_android.ui.home

import br.com.sf.empresas_android.injection.PerFragment
import br.com.sf.empresas_android.repository.company.CompaniesDataSource
import br.com.sf.empresas_android.ui.list_companies.CompaniesFragmentContract
import br.com.sf.empresas_android.ui.main.MainContract
import br.com.sf.empresas_android.util.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerFragment
class HomePresenter @Inject constructor(
    private val view: HomeContract.View,
    private val navigator: MainContract.Navigator,
    private val companiesDataSource: CompaniesDataSource,
    private val schedulerProvider: BaseSchedulerProvider
) : HomeContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun clickLogin(username: String, password: String) {

        view.showLoadingIndicator(true)

        val disposable = companiesDataSource.login(username, password)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe {
                view.showLoadingIndicator(false)
                navigator.goToCompanyList()
            }

        compositeDisposable.add(disposable)
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}