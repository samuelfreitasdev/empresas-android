package br.com.sf.empresas_android.injection.app

import android.app.Application
import android.content.Context
import br.com.sf.empresas_android.repository.DataManagerModule
import br.com.sf.empresas_android.util.schedulers.BaseSchedulerProvider
import br.com.sf.empresas_android.util.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataManagerModule::class])
internal class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider.instance

}