package br.com.sf.empresas_android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import br.com.sf.empresas_android.R
import br.com.sf.empresas_android.injection.PerFragment
import br.com.sf.empresas_android.ui.base.DaggerFragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import javax.inject.Inject


@PerFragment
class HomeFragment : DaggerFragment(), HomeContract.View {

    private var unbinder: Unbinder? = null

    @BindView(R.id.etEmail)
    internal lateinit var etEmail: EditText

    @BindView(R.id.etPass)
    internal lateinit var etPass: EditText

    @BindView(R.id.progressBar)
    internal lateinit var progressBar: ProgressBar

    @Inject
    internal lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showLoadingIndicator(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showLoginErrorWarning() {
    }

    @OnClick(R.id.btLogin)
    fun clickLogin() {
        presenter.clickLogin(
            etEmail.text.toString(),
            etPass.text.toString()
        )
    }


    companion object {
        fun newInstance() = HomeFragment()
    }
}