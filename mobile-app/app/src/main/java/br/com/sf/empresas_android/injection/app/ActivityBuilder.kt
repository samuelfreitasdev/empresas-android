package br.com.sf.empresas_android.injection.app

import br.com.sf.empresas_android.injection.PerActivity
import br.com.sf.empresas_android.ui.main.MainActivity
import br.com.sf.empresas_android.ui.main.MainActivityModule
import br.com.sf.empresas_android.ui.main.MainActivityProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityProvider::class])
    internal abstract fun bindMainActivity(): MainActivity

}
