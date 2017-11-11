package io.github.dziadeusz.trees.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tree")
public class Tree extends BaseEntity {
    String name;
}
