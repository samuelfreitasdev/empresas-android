package br.com.sf.empresas_android.ui.main

import android.os.Bundle
import br.com.sf.empresas_android.R
import br.com.sf.empresas_android.ui.base.BaseActivity
import br.com.sf.empresas_android.ui.widgets.ContainersLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    internal lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var navigator: MainContract.Navigator

    @BindView(R.id.activity_main__containers_layout)
    lateinit var containersLayout: ContainersLayout

    private var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unbinder = ButterKnife.bind(this)

        if (savedInstanceState == null) {
            this.presenter.init()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder!!.unbind()
    }

    override fun onBackPressed() {
        if (!navigator.onBackPressed()) {
            finish()
        }
    }

}
