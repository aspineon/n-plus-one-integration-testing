package io.github.dziadeusz.trees.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder
public class BranchDto {

    String name;

    Set<LeafDto> leafs;

    public static Set<BranchDto> fromEntities(Set<Branch> branches) {
        return branches.stream().map(BranchDto::fromEntity).collect(Collectors.toSet());
    }

    private static BranchDto fromEntity(Branch branch) {
        return BranchDto
                .builder()
                .name(branch.getName())
                .leafs(LeafDto.fromEntities(branch.getLeafs()))
                .build();
    }
}
