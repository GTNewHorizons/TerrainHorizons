package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen;

import net.minecraft.block.Block;

/// A functional interface that determines which stone block should be placed at a given Y level.
@FunctionalInterface
public interface StratificationFunction {

    ImmutableBlockMeta getStrataBlock(int layerY);

    static StratificationFunction of(Block block) {
        BlockMeta bm = new BlockMeta(block);

        return ignored -> bm;
    }

    static StratificationFunction of(ImmutableBlockMeta blockMeta) {
        return ignored -> blockMeta;
    }
}
