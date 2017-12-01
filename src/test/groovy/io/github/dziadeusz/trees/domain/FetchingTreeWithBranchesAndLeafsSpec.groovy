package io.github.dziadeusz.trees.domain

import net.ttddyy.dsproxy.asserts.ProxyTestDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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
class FetchingTreeWithBranchesAndLeafsSpec extends Specification {

    @Autowired
    TreeFacade uut;
    @Autowired
    ProxyTestDataSource testDataSource;


    def setup(){
        testDataSource.reset()
    }

    def 'should get the tree with dependencies with a single select'() {
        when:
        TreeDto tree = uut.getTreeWithSingleSelect("test tree");
        then:
        def branches = tree.getBranches()
        branches.name as Set == (1..10).collect {it -> 'branch ' + it} as Set
        def leafs = branches.collect {branch -> branch.getLeafs()}.flatten()
        tree.name == 'test tree'
        branches.size() == 10
        leafs.size() == 10
        leafs.name as Set == (1..10).collect {it -> 'leaf ' + it} as Set
        testDataSource.getQueryExecutions().size() == 1

    }

    def 'should get the tree with dependencies with multiple selects due to the n+1 problem'() {
        when:
        TreeDto tree = uut.getTreeWithNplusOne("test tree");
        then:
        def branches = tree.getBranches()
        branches.name as Set == (1..10).collect {it -> 'branch ' + it} as Set
        def leafs = branches.collect {branch -> branch.getLeafs()}.flatten()
        tree.name == 'test tree'
        branches.size() == 10
        leafs.size() == 10
        leafs.name as Set == (1..10).collect {it -> 'leaf ' + it} as Set
        testDataSource.getQueryExecutions().size() == 12
    }

    @TestConfiguration
    static class BaseTestConfiguration {
        @Value("\${spring.datasource.url}")
        String url;
        @Value("\${spring.datasource.driver-class-name}")
        String driverClassName;

        @Bean
        TreeFacade treeFacade(TreeRepository treeRepository){
            return new TreeFacadeImpl(treeRepository);
        }
        @Bean
        DataSource testProxyDataSource(){
            final DataSource actualDataSource = DataSourceBuilder
                    .create()
                    .driverClassName(driverClassName)
                    .url(url)
                    .build()
            new ProxyTestDataSource(actualDataSource)
        }
    }

}
