package io.github.dziadeusz.trees.domain;

import org.springframework.stereotype.Component;

@Component
class TreeFacadeImpl implements TreeFacade {

    private final TreeRepository treeRepository;

    public TreeFacadeImpl(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public TreeDto getTreeWithSingleSelect(String name) {
        return null;
    }

    @Override
    public TreeDto getTreeWithNplusOne(String name) {
        return null;
    }
}
