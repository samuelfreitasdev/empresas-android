package br.com.sf.empresas_android.ui.list_companies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.sf.empresas_android.R
import br.com.sf.empresas_android.R.layout
import br.com.sf.empresas_android.injection.PerFragment
import br.com.sf.empresas_android.repository.Company
import br.com.sf.empresas_android.ui.base.DaggerFragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.google.android.material.appbar.AppBarLayout
import javax.inject.Inject


@PerFragment
class CompaniesFragment : DaggerFragment(), CompaniesFragmentContract.View {

    @BindView(R.id.appBarLayout)
    lateinit var appBar: AppBarLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.rvList)
    lateinit var listCompanies: RecyclerView

    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    @Inject
    internal lateinit var presenter: CompaniesFragmentContract.Presenter

    private lateinit var companyAdapter: CompanyAdapter

    private var unbinder: Unbinder? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layout.fragment_companies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unbinder = ButterKnife.bind(this, view)

        companyAdapter = CompanyAdapter(presenter::clickCompany)

        listCompanies.setHasFixedSize(false)
        listCompanies.layoutManager = LinearLayoutManager(activity)
        listCompanies.adapter = companyAdapter

        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.inflateMenu(R.menu.menu_search)

        val searchItem = toolbar.menu.findItem(R.id.action_search)

        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                toolbar.menu.findItem(R.id.action_search).collapseActionView()
                companyAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                companyAdapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.start()
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

    override fun setCompanies(companies: List<Company>) {
        companyAdapter.addCompanies(companies)
        companyAdapter.filter.filter("")
    }

    override fun showEmptyState() {
    }

    companion object {
        fun newInstance(): CompaniesFragment = CompaniesFragment()
    }

}