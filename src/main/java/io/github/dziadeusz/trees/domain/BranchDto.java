package io.github.dziadeusz.trees.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class BranchDto {
    String name;
    Set<BranchDto> branches;
    Set<LeafDto> leafs;
}
