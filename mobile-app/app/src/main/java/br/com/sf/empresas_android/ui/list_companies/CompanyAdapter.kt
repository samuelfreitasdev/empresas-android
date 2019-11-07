package br.com.sf.empresas_android.ui.list_companies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sf.empresas_android.R
import br.com.sf.empresas_android.repository.Company
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide


class CompanyAdapter(private val listener: (Company) -> Unit) :
    RecyclerView.Adapter<CompanyAdapter.ViewHolder>(), Filterable {

    private var filter: ListItemFilter = ListItemFilter()
    private val dataSet = mutableListOf<Company>()
    private var filteredDataSet = mutableListOf<Company>()

    fun addCompanies(products: List<Company>) {
        dataSet.addAll(products)
        getFilter()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.listitem_company, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = filteredDataSet[position]
        holder.bind(product, listener)
    }

    override fun getItemCount(): Int = filteredDataSet.size

    override fun getFilter(): Filter = filter

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.imCompany)
        lateinit var imCompany: ImageView

        @BindView(R.id.tvCompany)
        lateinit var tvCompany: TextView

        @BindView(R.id.tvBusiness)
        lateinit var tvBusiness: TextView

        @BindView(R.id.tvCountry)
        lateinit var tvCountry: TextView

        fun bind(company: Company, listener: (Company) -> Unit) {
            ButterKnife.bind(this, itemView)

            if (company.photo.orEmpty().isNotEmpty()) {
                Glide.with(itemView)
                    .load(company.photo)
                    .into(imCompany)
            }

            tvCompany.text = company.name
            tvBusiness.text = company.type.enterpriseTypeName
            tvCountry.text = company.country

            itemView.setOnClickListener { listener(company) }
        }
    }

    private inner class ListItemFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val filterResults = Filter.FilterResults()
            if (constraint != null && constraint.length > 0) {
                val tempList = ArrayList<Company>()

                // search content in friend list
                for (company in dataSet) {
                    if (company.name.toLowerCase().contains(constraint.toString().toLowerCase())
                        || company.country.toLowerCase().contains(constraint.toString().toLowerCase())
                        || company.type.enterpriseTypeName.toLowerCase().contains(constraint.toString().toLowerCase())
                    ) {
                        tempList.add(company)
                    }
                }

                filterResults.count = tempList.size
                filterResults.values = tempList
            } else {
                filterResults.count = dataSet.size
                filterResults.values = dataSet
            }

            return filterResults
        }

        /**
         * Notify about filtered list to ui
         *
         * @param constraint text
         * @param results    filtered result
         */
        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
            if (results.values is ArrayList<*>) {
                filteredDataSet = results.values as MutableList<Company>
            }
            notifyDataSetChanged()
        }
    }
}