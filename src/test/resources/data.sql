-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile
INSERT INTO tree(id, uuid, name) values (1,'54bdf8ec-0f91-44c4-9f12-5cf4e83b47a3', 'test tree');
INSERT INTO branch(id, uuid, tree_id, name) values (1,'1bfa8f78-0d96-4c73-8533-4c1d8ecd74ff', 1, 'branch 1');
INSERT INTO branch(id, uuid, tree_id, name) values (2,'05c049c7-4838-4abc-9ce9-9329c23646db', 1, 'branch 2');
INSERT INTO branch(id, uuid, tree_id, name) values (3,'ff8a1d33-a21f-400a-a2cc-9febd8823bfe', 1, 'branch 3');
INSERT INTO branch(id, uuid, tree_id, name) values (4,'2fbfbdb2-3ef7-4eea-8cc1-548ee75a7e02', 1, 'branch 4');
INSERT INTO branch(id, uuid, tree_id, name) values (5,'0ebff45c-9282-4377-b52c-0c1174634fc4', 1, 'branch 5');
INSERT INTO branch(id, uuid, tree_id, name) values (6,'d15270eb-9ab1-4921-b3af-af03ac1efcba', 1, 'branch 6');
INSERT INTO branch(id, uuid, tree_id, name) values (7,'416abc45-f695-49c4-b583-e7c6afeae78c', 1, 'branch 7');
INSERT INTO branch(id, uuid, tree_id, name) values (8,'0e54101c-0315-470e-86d5-a0961e21e5d9', 1, 'branch 8');
INSERT INTO branch(id, uuid, tree_id, name) values (9,'dc04e11e-9106-4c9e-9952-37aec8797d7d', 1, 'branch 9');
INSERT INTO branch(id, uuid, tree_id, name) values (10,'a577a5b4-ed94-4e90-bdc3-75cf28448309', 1, 'branch 10');
INSERT INTO leaf(id, uuid, branch_id, name) values(1,'7f558352-fb78-4b11-9ca7-a292769eb632', 1, 'leaf 1');
INSERT INTO leaf(id, uuid, branch_id, name) values(2,'447c7c3d-b20d-4538-a6ab-01353ea2f2c3', 1, 'leaf 2');
INSERT INTO leaf(id, uuid, branch_id, name) values(3,'047a6b39-e95d-4bf2-941b-c8b307272444', 1, 'leaf 3');
INSERT INTO leaf(id, uuid, branch_id, name) values(4,'4e307639-b5f2-4bc1-9c71-e524317003b8', 4, 'leaf 4');
INSERT INTO leaf(id, uuid, branch_id, name) values(5,'da37a053-b3cb-4d49-96a6-af7c385d9363', 5, 'leaf 5');
INSERT INTO leaf(id, uuid, branch_id, name) values(6,'ef668791-ef15-44d7-9b1b-7e9ab30dc7b9', 6, 'leaf 6');
INSERT INTO leaf(id, uuid, branch_id, name) values(7,'1d7a8623-8f19-4c31-900e-a83adf637499', 7, 'leaf 7');
INSERT INTO leaf(id, uuid, branch_id, name) values(8,'825ed54b-143b-4254-bf78-791f470c03cd', 8, 'leaf 8');
INSERT INTO leaf(id, uuid, branch_id, name) values(9,'a6c44b70-2298-4eb4-b486-ea56f87768d7', 9, 'leaf 9');
INSERT INTO leaf(id, uuid, branch_id, name) values(10,'3312dc1a-8747-421c-9829-aa3a4547340b', 10, 'leaf 10');











