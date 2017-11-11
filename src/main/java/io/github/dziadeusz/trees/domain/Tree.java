package io.github.dziadeusz.trees.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "tree")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Tree extends BaseEntity {
    String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    Set<Branch> branches;
}
