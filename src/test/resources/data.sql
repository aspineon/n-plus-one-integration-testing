-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile
INSERT INTO tree(id, name) values (1, 'test tree');
INSERT INTO branch(id, tree_id, branch_id, name) values (1, 1, null, 'branch 1');
INSERT INTO branch(id, tree_id, branch_id, name) values (2, 1, null, 'branch 2');
INSERT INTO branch(id, tree_id, branch_id, name) values (3, 1, null, 'branch 3');
INSERT INTO branch(id, tree_id, branch_id, name) values (4, 1, null, 'branch 4');
INSERT INTO branch(id, tree_id, branch_id, name) values (5, 1, null, 'branch 5');
INSERT INTO branch(id, tree_id, branch_id, name) values (6, null, 1, 'branch 6');
INSERT INTO branch(id, tree_id, branch_id, name) values (7, null, 6, 'branch 7');
INSERT INTO branch(id, tree_id, branch_id, name) values (8, null, 6, 'branch 8');
INSERT INTO branch(id, tree_id, branch_id, name) values (9, null, 6, 'branch 9');
INSERT INTO branch(id, tree_id, branch_id, name) values (10, null, 9, 'branch 10');
INSERT INTO leaf(id, branch_id, name) values(1, 6, 'leaf 1');
INSERT INTO leaf(id, branch_id, name) values(2, 7, 'leaf 2');
INSERT INTO leaf(id, branch_id, name) values(3, 7, 'leaf 3');
INSERT INTO leaf(id, branch_id, name) values(4, 8, 'leaf 4');
INSERT INTO leaf(id, branch_id, name) values(5, 8, 'leaf 5');
INSERT INTO leaf(id, branch_id, name) values(6, 9, 'leaf 6');
INSERT INTO leaf(id, branch_id, name) values(7, 9, 'leaf 7');
INSERT INTO leaf(id, branch_id, name) values(8, 9, 'leaf 8');
INSERT INTO leaf(id, branch_id, name) values(9, 10, 'leaf 9');
INSERT INTO leaf(id, branch_id, name) values(10, 10, 'leaf 10');