package io.github.dziadeusz.trees.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface TreeRepository extends JpaRepository<Tree,Long>{
    Tree findByName(String name);

    @Query("select t from Tree t left join fetch t.branches b left join fetch b.leafs l where t.name = :name")
    Tree findTreeWithBranchesAndLeafs(@Param("name") String name);
}
