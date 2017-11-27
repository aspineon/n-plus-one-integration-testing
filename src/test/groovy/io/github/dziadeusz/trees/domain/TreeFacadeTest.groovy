package io.github.dziadeusz.trees.domain

import net.ttddyy.dsproxy.asserts.ProxyTestDataSource
import net.ttddyy.dsproxy.support.ProxyDataSource
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.sql.DataSource

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ActiveProfiles("dbintegration")
@Transactional(propagation = NOT_SUPPORTED)
class TreeFacadeTest extends Specification {

    @Autowired
    TreeFacade uut;
    @Autowired
    ProxyTestDataSource testDataSource;

    @TestConfiguration
    static class BaseTestConfiguration {
        @Bean
        TreeFacade treeFacade(TreeRepository treeRepository){
            return new TreeFacadeImpl(treeRepository);
        }
        @Bean
        DataSource testProxyDataSource(){
            DataSource actualDataSource = DataSourceBuilder
                    .create()
                    .driverClassName("org.h2.Driver")
                    .url("jdbc:h2:mem:db")
                    .build()

            ProxyDataSource proxyDataSource = ProxyDataSourceBuilder
                    .create(actualDataSource)
                    .countQuery()
                    .build();
            new ProxyTestDataSource(proxyDataSource)
        }
    }

    def setup(){
        testDataSource.reset()
    }

    def 'should get the tree with dependencies with a single select'() {
        expect:
        TreeDto tree = uut.getTreeWithSingleSelect("test tree");
        treeIsFetchedWithAllDependencies(tree) == true
        testDataSource.getQueryExecutions().size() == 1

    }

    def 'should get the tree with dependencies with multiple selects due to the n+1 problem'() {
        TreeDto tree = uut.getTreeWithNplusOne("test tree");
        treeIsFetchedWithAllDependencies(tree) == true
        testDataSource.getQueryExecutions().size() == 1
    }

    private treeIsFetchedWithAllDependencies(TreeDto tree) {
        tree.name == 'test tree' &&
                tree.getBranches().size() == 5 &&
                tree.getBranches()
    }
}
