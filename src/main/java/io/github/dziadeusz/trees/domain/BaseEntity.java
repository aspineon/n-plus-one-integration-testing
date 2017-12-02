package io.github.dziadeusz.trees.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Getter
@EqualsAndHashCode(of = "uuid")
@FieldDefaults(level = AccessLevel.PRIVATE)
class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String uuid = UUID.randomUUID().toString();

    @Version
    Long version;

}
