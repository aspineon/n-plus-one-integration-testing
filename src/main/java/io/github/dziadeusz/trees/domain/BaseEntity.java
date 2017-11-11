package io.github.dziadeusz.trees.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of="uuid")
@Getter
class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String uuid = UUID.randomUUID().toString();
}
