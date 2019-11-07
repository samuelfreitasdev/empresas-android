package br.com.sf.empresas_android.ui.list_companies

import br.com.sf.empresas_android.injection.PerFragment
import br.com.sf.empresas_android.repository.Company
import br.com.sf.empresas_android.repository.CompanyListDTO
import br.com.sf.empresas_android.repository.company.CompaniesDataSource
import br.com.sf.empresas_android.ui.main.MainContract
import br.com.sf.empresas_android.util.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@PerFragment
class CompaniesFragmentPresenter @Inject constructor(
    private val view: CompaniesFragmentContract.View,
    private val navigator: MainContract.Navigator,
    private val companiesDataSource: CompaniesDataSource,
    private val schedulerProvider: BaseSchedulerProvider
) : CompaniesFragmentContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun start() {

        view.showLoadingIndicator(true)

        val disposable = companiesDataSource.findCompanies()
            .subscribeOn(schedulerProvider.io())
            .map { t: CompanyListDTO -> t.enterprises }
            .observeOn(schedulerProvider.ui())
            .subscribe({ companies ->

                if (companies.isNotEmpty()) {
                    view.setCompanies(companies = companies)
                } else {
                    view.showEmptyState()
                }

                view.showLoadingIndicator(false)
            }, {
                Timber.e(it)
                view.showEmptyState()
                view.showLoadingIndicator(false)
            })

        compositeDisposable.add(disposable)
    }

    override fun clickCompany(company: Company) {
        navigator.goToCompanyDetail(company)
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}