package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.feature;

import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.GalaxiaPlanetGenerator;
import net.minecraft.world.World;

/// A feature that is generated underground, on planets
public interface UndergroundFeature {

    /// Generates the feature, if needed. Called once per underground [ExtendedBlockStorage]/[Cube].
    ///
    /// @param world The world
    /// @param generator The generator that is currently generating this world
    /// @param cx The chunk X coordinate
    /// @param cy The ebs/cube Y coordinate (1 unit = 16 blocks)
    /// @param cz The chunk Z coordinate
    void generateUndergroundFeature(World world, GalaxiaPlanetGenerator generator, int cx, int cy, int cz);

}
