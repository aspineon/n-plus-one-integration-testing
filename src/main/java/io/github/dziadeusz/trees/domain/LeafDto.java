package io.github.dziadeusz.trees.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder
public class LeafDto {

    String name;

    public static Set<LeafDto> fromEntities(final Set<Leaf> leafs) {
        return leafs.stream().map(LeafDto::fromEntity).collect(Collectors.toSet());
    }

    private static LeafDto fromEntity(Leaf leaf) {
        return LeafDto.builder().name(leaf.getName()).build();
    }
}
