package io.github.dziadeusz.trees.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class TreeDto {
    String name;
    Set<BranchDto> branches;
}
