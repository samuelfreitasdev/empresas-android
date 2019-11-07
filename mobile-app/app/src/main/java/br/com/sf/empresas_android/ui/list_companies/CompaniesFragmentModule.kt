package br.com.sf.empresas_android.ui.list_companies

import br.com.sf.empresas_android.injection.PerFragment
import dagger.Binds
import dagger.Module

@Module
abstract class CompaniesFragmentModule {

    @PerFragment
    @Binds
    internal abstract fun providePresenter(presenter: CompaniesFragmentPresenter): CompaniesFragmentContract.Presenter

    @PerFragment
    @Binds
    internal abstract fun provideView(fragment: CompaniesFragment): CompaniesFragmentContract.View

}