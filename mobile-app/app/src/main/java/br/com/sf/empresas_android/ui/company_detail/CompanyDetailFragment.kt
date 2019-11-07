package br.com.sf.empresas_android.ui.company_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import br.com.sf.empresas_android.R
import br.com.sf.empresas_android.injection.PerFragment
import br.com.sf.empresas_android.repository.Company
import br.com.sf.empresas_android.ui.base.DaggerFragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bumptech.glide.Glide
import javax.inject.Inject

@PerFragment
class CompanyDetailFragment : DaggerFragment(), CompanyDetailContract.View {

    @BindView(R.id.imCompany)
    lateinit var imCompany: ImageView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tvDescription)
    lateinit var tvDescription: TextView

    @Inject
    internal lateinit var presenter: CompanyDetailContract.Presenter

    private var unbinder: Unbinder? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_company_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpAppBar()

        val company: Company = arguments!!.getParcelable(PARAM)!!
        presenter.setCompany(company)
    }

    private fun setUpAppBar() {
        toolbar.setNavigationOnClickListener { presenter.goBack() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showCompany(company: Company) {
        toolbar.title = company.name

        if (!company.photo.isNullOrBlank()) {
            Glide.with(context!!)
                .load(company.photo)
                .into(imCompany)
        }

        tvDescription.text = company.description
    }

    companion object {
        const val PARAM = "PARAM"
        fun newInstance(company: Company): CompanyDetailFragment {

            val args = Bundle()
                .apply { putParcelable(PARAM, company) }

            return CompanyDetailFragment()
                .apply { arguments = args }
        }
    }

}