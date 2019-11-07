package br.com.sf.empresas_android.ui.main

import androidx.fragment.app.FragmentTransaction
import br.com.sf.empresas_android.R
import br.com.sf.empresas_android.injection.PerActivity
import br.com.sf.empresas_android.repository.Company
import br.com.sf.empresas_android.ui.company_detail.CompanyDetailFragment
import br.com.sf.empresas_android.ui.home.HomeFragment
import br.com.sf.empresas_android.ui.list_companies.CompaniesFragment
import javax.inject.Inject

@PerActivity
class MainNavigator @Inject
internal constructor(private val mainActivity: MainActivity) : MainContract.Navigator {

    override fun goToLogin() {
        clearMaster()

        mainActivity
            .containersLayout
            .state = MainContract.Navigator.State.SINGLE_COLUMN_DETAILS

        val fragment = HomeFragment.newInstance()

        mainActivity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS)
            .commitNow()
    }

    override fun goToCompanyList() {
        clearMaster()

        mainActivity
            .containersLayout
            .state = MainContract.Navigator.State.SINGLE_COLUMN_DETAILS

        val fragment = CompaniesFragment.newInstance()

        mainActivity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS)
            .commitNow()
    }

    override fun goToCompanyDetail(company: Company) {
        clearMaster()

        mainActivity
            .containersLayout
            .state = MainContract.Navigator.State.SINGLE_COLUMN_DETAILS

        val fragment = CompanyDetailFragment.newInstance(company)

        mainActivity
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main__frame_details, fragment, TAG_DETAILS)
            .commitNow()
    }

    private fun clearDetails(): Boolean {
        val details = mainActivity.supportFragmentManager.findFragmentByTag(TAG_DETAILS)
        if (details != null) {
            mainActivity.supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .remove(details)
                .commitNow()
            return true
        }
        return false
    }

    private fun clearMaster() {
        val master = mainActivity.supportFragmentManager.findFragmentByTag(TAG_MASTER)
        if (master != null) {
            mainActivity.supportFragmentManager.beginTransaction().remove(master).commitNow()
        }
    }

    override fun onBackPressed(): Boolean {
        val state = mainActivity.containersLayout.state
        if (state == MainContract.Navigator.State.TWO_COLUMNS_WITH_DETAILS) {
            if (clearDetails()) {
                mainActivity.containersLayout.state = MainContract.Navigator.State.TWO_COLUMNS_EMPTY
                return true
            }
        }
        return false
    }

    companion object {
        private val TAG_DETAILS = "tag_details"
        private val TAG_MASTER = "tag_master"
    }
}
