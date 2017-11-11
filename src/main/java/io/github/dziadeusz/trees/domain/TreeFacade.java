package io.github.dziadeusz.trees.domain;

public interface TreeFacade {

    TreeDto getTreeWithSingleSelect(String name);
    TreeDto getTreeWithNplusOne(String name);
}
