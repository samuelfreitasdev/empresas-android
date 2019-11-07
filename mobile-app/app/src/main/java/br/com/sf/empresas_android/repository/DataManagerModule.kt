package br.com.sf.empresas_android.repository

import br.com.sf.empresas_android.repository.company.CompaniesDataSource
import br.com.sf.empresas_android.repository.company.remote.CompaniesRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataManagerModule {

    @Singleton
    @Provides
    fun provideCompaniesDataSource(): CompaniesDataSource {
        return CompaniesRemoteDataSource()
    }

}
