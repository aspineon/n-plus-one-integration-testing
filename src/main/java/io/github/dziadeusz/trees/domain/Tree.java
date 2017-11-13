package io.github.dziadeusz.trees.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "tree")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Tree extends BaseEntity {
    String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    Set<Branch> branches;
}
