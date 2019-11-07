package br.com.sf.empresas_android.ui.main

import br.com.sf.empresas_android.injection.PerFragment
import br.com.sf.empresas_android.ui.company_detail.CompanyDetailFragment
import br.com.sf.empresas_android.ui.company_detail.CompanyDetailFragmentModule
import br.com.sf.empresas_android.ui.home.HomeFragment
import br.com.sf.empresas_android.ui.home.HomeFragmentModule
import br.com.sf.empresas_android.ui.list_companies.CompaniesFragment
import br.com.sf.empresas_android.ui.list_companies.CompaniesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {

    @PerFragment
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    internal abstract fun provideHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [CompaniesFragmentModule::class])
    internal abstract fun provideCompaniesFragment(): CompaniesFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [CompanyDetailFragmentModule::class])
    internal abstract fun provideCompanyDetailFragment(): CompanyDetailFragment

}
