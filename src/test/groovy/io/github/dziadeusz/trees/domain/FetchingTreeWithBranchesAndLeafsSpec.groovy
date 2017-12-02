package io.github.dziadeusz.trees.domain

import net.ttddyy.dsproxy.QueryCountHolder
import net.ttddyy.dsproxy.asserts.PreparedExecution
import net.ttddyy.dsproxy.asserts.ProxyTestDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ActiveProfiles("dbintegration")
@Transactional(propagation = NOT_SUPPORTED)
@Import(BaseDatabaseIntegrationSpecConfig)
class FetchingTreeWithBranchesAndLeafsSpec extends Specification {

    @Autowired
    TreeFacade uut;
    @Autowired
    ProxyTestDataSource testDataSource;


    def setup() {
        testDataSource.reset()
        QueryCountHolder.clear()
    }

    def 'should get the tree with dependencies with a single select'() {

        when: 'a tree is fetched using a HQL query with join fetches'

        TreeDto tree = uut.getTreeWithSingleSelect(TestConstants.TEST_TREE_NAME);

        then: 'all branches and leafs should be fetched too'

        assertThatTreeHasAllBranchesAndLeafs(tree)

        and: 'only one SQL select should be executed'

        def executions = testDataSource.getQueryExecutions();
        executions.size() == 1
        PreparedExecution queryExecution = executions[0]
        queryExecution.isBatch() == false
        queryExecution.isSuccess() == true
        queryExecution.getAllParameters().value as Set == [TestConstants.TEST_TREE_NAME] as Set
        queryExecution.getQuery() == TestConstants.EXPECTED_SINGLE_QUERY
    }

    def 'should get the tree with dependencies with multiple selects due to the n+1 problem'() {

        when: 'a tree is fetched and within a @Transaction its branches and their leafs are accessed'
        TreeDto tree = uut.getTreeWithNplusOne(TestConstants.TEST_TREE_NAME);

        then: 'all branches and leafs will also be fetched on the fly'

        assertThatTreeHasAllBranchesAndLeafs(tree)

        and:
        'apart from the tree query,' +
                'there will be one query to fetch the lazy loaded branches collection' +
                'and n queries for the lazy loaded leafs collection for each of the n branches'
        QueryCountHolder.getGrandTotal().getSelect() == 12
    }


    private assertThatTreeHasAllBranchesAndLeafs(TreeDto tree) {
        def branches = tree.getBranches()
        def leafs = branches.collect { branch -> branch.getLeafs() }.flatten()
        return branches.name as Set == (1..10).collect { it -> 'branch ' + it } as Set &&
                tree.name == TestConstants.TEST_TREE_NAME &&
                branches.size() == 10 &&
                leafs.size() == 10 &&
                leafs.name as Set == (1..10).collect { it -> 'leaf ' + it } as Set
    }

}
