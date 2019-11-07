package br.com.sf.empresas_android.repository.company.remote

import br.com.sf.empresas_android.repository.company.CompaniesDataSource
import org.junit.Before
import org.junit.Test

class CompaniesRemoteDataSourceTest {

    private lateinit var dataSource: CompaniesDataSource

    @Before
    fun setUp() {
        dataSource = CompaniesRemoteDataSource()
    }

    @Test
    fun testLogin() {
        val testObserver = dataSource.login(USERNAME, PASS)
            .test()

        testObserver.awaitTerminalEvent()

        testObserver.assertComplete()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun testListCompanies() {
        testLogin()

        dataSource.findCompanies()
            .test()
            .assertNoErrors()
            .assertComplete()

        val a = dataSource.findCompanies2()
            .blockingGet()

        print(a.string())
    }

    companion object {
        const val USERNAME = "testeapple@ioasys.com.br"
        const val PASS = "12341234"
    }
}