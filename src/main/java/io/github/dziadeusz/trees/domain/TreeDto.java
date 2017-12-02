package io.github.dziadeusz.trees.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class TreeDto {
    String name;
    Set<BranchDto> branches;

    public static TreeDto fromEntity(final Tree tree) {
        return TreeDto.builder()
                .name(tree.getName())
                .branches(BranchDto.fromEntities(tree.getBranches()))
                .build();
    }
}
