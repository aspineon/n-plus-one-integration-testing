package io.github.dziadeusz.trees.domain;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
class TreeFacadeImpl implements TreeFacade {

    private final TreeRepository treeRepository;

    public TreeFacadeImpl(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public TreeDto getTreeWithSingleSelect(final String name) {
        final Tree tree = treeRepository.findTreeWithBranchesAndLeafs(name);
        return TreeDto.fromEntity(tree);
    }

    @Override
    @Transactional
    public TreeDto getTreeWithNplusOne(String name) {
        final Tree tree = treeRepository.findByName(name);
        return TreeDto.fromEntity(tree);
    }
}
