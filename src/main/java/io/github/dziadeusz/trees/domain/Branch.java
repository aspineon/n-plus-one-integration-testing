package io.github.dziadeusz.trees.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "branch")
@Builder

public class Branch extends BaseEntity {
    String
}
