package com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.feature;

import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.GalaxiaPlanetGenerator;
import net.minecraft.world.World;

/// A feature that is generated on the surface of a planet.
public interface SurfaceFeature {

    /// Generates the feature, if needed. Called once per chunk.
    ///
    /// @param world The world
    /// @param generator The generator that is currently generating this world
    /// @param cx The chunk X coordinate
    /// @param cz The chunk Z coordinate
    void generateSurfaceFeature(World world, GalaxiaPlanetGenerator generator, int cx, int cz);
}
