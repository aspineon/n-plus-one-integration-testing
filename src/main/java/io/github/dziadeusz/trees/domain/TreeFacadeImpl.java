package io.github.dziadeusz.trees.domain;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
class TreeFacadeImpl implements TreeFacade {

    private final TreeRepository treeRepository;

    public TreeFacadeImpl(final TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public TreeDto getTreeWithSingleSelect(final String name) {
        final Tree tree = treeRepository.findTreeByNameWithBranchesAndLeafs(name);
        return TreeDto.fromEntity(tree);
    }

    @Override
    @Transactional
    public TreeDto getTreeWithNplusOne(final String name) {
        final Tree tree = treeRepository.findTreeByName(name);
        return TreeDto.fromEntity(tree);
    }
}
