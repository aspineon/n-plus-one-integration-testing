package io.github.dziadeusz.trees.domain


class TestConstants {
    public static final String TEST_TREE_NAME = 'test tree'
    public static final String EXPECTED_SINGLE_QUERY =
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
