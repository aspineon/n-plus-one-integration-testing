package io.github.dziadeusz.trees.domain

import net.ttddyy.dsproxy.QueryCountHolder
import net.ttddyy.dsproxy.asserts.PreparedExecution
import net.ttddyy.dsproxy.asserts.ProxyTestDataSource
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder
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
        QueryCountHolder.clear()
    }

    def 'should get the tree with dependencies with a single select'() {

        when: 'a tree is fetched using a HQL query with join fetches'

        TreeDto tree = uut.getTreeWithSingleSelect("test tree");

        then: 'all branches and leafs should be fetched too'

        def branches = tree.getBranches()
        branches.name as Set == (1..10).collect {it -> 'branch ' + it} as Set
        def leafs = branches.collect {branch -> branch.getLeafs()}.flatten()
        tree.name == 'test tree'
        branches.size() == 10
        leafs.size() == 10
        leafs.name as Set == (1..10).collect {it -> 'leaf ' + it} as Set

        and: 'only one SQL select should be executed'

        def executions = testDataSource.getQueryExecutions();
        executions.size() == 1
        PreparedExecution queryExecution = executions[0]
        queryExecution.isBatch() == false
        queryExecution.isSuccess() == true
        queryExecution.getAllParameters().value as Set == ["test tree"] as Set
        queryExecution.getQuery() == expectedSingleQuery()
    }



    def 'should get the tree with dependencies with multiple selects due to the n+1 problem'() {

        when: 'a tree is fetched and within a @Transaction its branches and their leafs are accessed'
        TreeDto tree = uut.getTreeWithNplusOne("test tree");

        then: 'all branches and leafs will also be fetched on the fly'

        def branches = tree.getBranches()
        branches.name as Set == (1..10).collect {it -> 'branch ' + it} as Set
        def leafs = branches.collect {branch -> branch.getLeafs()}.flatten()
        tree.name == 'test tree'
        branches.size() == 10
        leafs.size() == 10
        leafs.name as Set == (1..10).collect {it -> 'leaf ' + it} as Set

        and: 'apart from the tree query,' +
             'there will be one query to fetch the lazy loaded branches collection' +
             'and n queries for the lazy loaded leafs collection for each of the n branches'
        QueryCountHolder.getGrandTotal().getSelect() == 12
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
            def testDatasource = ProxyDataSourceBuilder.create(actualDataSource)
                    .countQuery().build()
            new ProxyTestDataSource(testDatasource)
        }
    }

    private expectedSingleQuery() {
                'select' +
                ' tree0_.id as id1_2_0_,' +
                ' branches1_.id as id1_0_1_,' +
                ' leafs2_.id as id1_1_2_,' +
                ' tree0_.uuid as uuid2_2_0_,' +
                ' tree0_.version as version3_2_0_,' +
                ' tree0_.name as name4_2_0_,' +
                ' branches1_.uuid as uuid2_0_1_,' +
                ' branches1_.version as version3_0_1_,' +
                ' branches1_.name as name4_0_1_,' +
                ' branches1_.tree_id as tree_id5_0_1_,' +
                ' branches1_.tree_id as tree_id5_0_0__,' +
                ' branches1_.id as id1_0_0__,' +
                ' leafs2_.uuid as uuid2_1_2_,' +
                ' leafs2_.version as version3_1_2_,' +
                ' leafs2_.branch_id as branch_i5_1_2_,' +
                ' leafs2_.name as name4_1_2_,' +
                ' leafs2_.branch_id as branch_i5_1_1__,' +
                ' leafs2_.id as id1_1_1__ ' +
                'from' +
                ' tree tree0_ ' +
                'left outer join' +
                ' branch branches1_ ' +
                    'on tree0_.id=branches1_.tree_id ' +
                'left outer join' +
                ' leaf leafs2_ ' +
                    'on branches1_.id=leafs2_.branch_id ' +
                'where' +
                ' tree0_.name=?'
    }

}
