package io.github.dziadeusz.trees.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "branch")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Branch extends BaseEntity {
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    Branch branch;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    Set<Leaf> leafs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    Set<Branch> branches;
}
