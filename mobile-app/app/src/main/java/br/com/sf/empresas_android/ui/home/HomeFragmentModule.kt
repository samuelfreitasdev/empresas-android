package br.com.sf.empresas_android.ui.home

import br.com.sf.empresas_android.injection.PerFragment
import br.com.sf.empresas_android.ui.list_companies.CompaniesFragment
import br.com.sf.empresas_android.ui.list_companies.CompaniesFragmentContract
import br.com.sf.empresas_android.ui.list_companies.CompaniesFragmentPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class HomeFragmentModule {

    @PerFragment
    @Binds
    internal abstract fun providePresenter(presenter: HomePresenter): HomeContract.Presenter

    @PerFragment
    @Binds
    internal abstract fun provideView(fragment: HomeFragment): HomeContract.View

}