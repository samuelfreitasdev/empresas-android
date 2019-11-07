package br.com.sf.empresas_android.ui.company_detail

import br.com.sf.empresas_android.injection.PerFragment
import dagger.Binds
import dagger.Module

@Module
abstract class CompanyDetailFragmentModule {

    @PerFragment
    @Binds
    internal abstract fun providePresenter(presenter: CompanyDetailPresenter): CompanyDetailContract.Presenter

    @PerFragment
    @Binds
    internal abstract fun provideView(fragment: CompanyDetailFragment): CompanyDetailContract.View

}