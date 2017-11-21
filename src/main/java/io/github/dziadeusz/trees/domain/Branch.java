package io.github.dziadeusz.trees.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "branch")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Branch extends BaseEntity {
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_id")
    Tree tree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    Branch branch;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    Set<Leaf> leafs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    Set<Branch> branches;
}
