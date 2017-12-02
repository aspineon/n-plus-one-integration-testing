package io.github.dziadeusz.trees.domain

import net.ttddyy.dsproxy.asserts.ProxyTestDataSource
import net.ttddyy.dsproxy.support.ProxyDataSource
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

import javax.sql.DataSource

@TestConfiguration
class BaseDatabaseIntegrationSpecConfig {
    @Value("\${spring.datasource.url}")
    String url
    @Value("\${spring.datasource.driver-class-name}")
    String driverClassName

    @Bean
    TreeFacade treeFacade(TreeRepository treeRepository) {
        new TreeFacadeImpl(treeRepository)
    }

    @Bean
    DataSource testProxyDataSource() {
        final DataSource actualDataSource = DataSourceBuilder
                .create()
                .driverClassName(driverClassName)
                .url(url)
                .build()
        final ProxyDataSource testDatasource = ProxyDataSourceBuilder.create(actualDataSource)
                .countQuery().build()
        new ProxyTestDataSource(testDatasource)
    }
}
