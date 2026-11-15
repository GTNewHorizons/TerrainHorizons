package com.gtnewhorizons.terrainhorizons.test;

import com.gtnewhorizon.gtnhlib.util.data.BlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.DimensionTH;
import com.gtnewhorizons.terrainhorizons.api.dimension.biome.BiomeGenBuilder;
import com.gtnewhorizons.terrainhorizons.api.dimension.biome.BiomeIdOffsetter;
import com.gtnewhorizons.terrainhorizons.api.dimension.provider.WorldProviderBuilder;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.BiomeMatrixGenerator;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.TerrainConfiguration;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.TerrainPreset;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.mantle.MantleRules;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.modifier.TerrainModifier;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class Testia {
    private Testia() {}

    /**
     * Configures the world provider to add the correct biomes and settings
     *
     * @param builder The world provider builder being configured
     */
    public static void configureWorldProvider(WorldProviderBuilder builder) {
        BiomeGenBase border = createOceanBiome(
            "Moon Ocean Edge",
            TerrainConfiguration.builder()
                .feature(TerrainPreset.BASE_HEIGHT)
                .height(16)
                .endFeature()
                .feature(TerrainPreset.MOUNTAIN_RANGES)
                .width(4)
                .height(32)
                .endFeature()
                .build());
        BiomeGenBase volcanoes = createOceanBiome(
            "Moon Volcanoes",
            TerrainConfiguration.builder()
                .feature(TerrainPreset.BASE_HEIGHT)
                .height(8)
                .endFeature()
                .feature(TerrainPreset.SHIELD_VOLCANOES)
                .replacementBlock(Blocks.lava)
                .width(2)
                .height(32)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.minimum,
                    TerrainModifier.WEIRDNESS.middle)
                .endFeature()
                .feature(TerrainPreset.SHIELD_VOLCANOES)
                .replacementBlock(Blocks.lava)
                .width(4)
                .height(64)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.middle,
                    TerrainModifier.WEIRDNESS.maximum)
                .endFeature()
                .build());
        BiomeGenBase highlands = createLandBiome(
            "Moon Highlands",
            TerrainConfiguration.builder()
                .feature(TerrainPreset.BASE_HEIGHT)
                .height(32)
                .endFeature()
                .feature(TerrainPreset.MOUNTAIN_RANGES)
                .width(3)
                .height(24)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.lowerExtreme,
                    TerrainModifier.WEIRDNESS.upperExtreme)
                .endFeature()
                .feature(TerrainPreset.MOUNTAIN_RANGES)
                .width(32)
                .height(192)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.minimum,
                    TerrainModifier.WEIRDNESS.middle)
                .endFeature()
                .feature(TerrainPreset.CANYONS)
                .width(4)
                .height(32)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.middle,
                    TerrainModifier.WEIRDNESS.maximum)
                .endFeature()
                .build());
        builder.sky(true)
            .fog(0, 0, 0)
            .skyColor(0, 0, 0.001f)
            .avgGround(80)
            // Biome matrix
            .biomeMatrix(
                new BiomeMatrixGenerator(new String[] { "HHHHHH", "HHHHHH", "HHHbHH", "HHbVbH", "HHHbHH", "HHHHHH" })
                    .addBiomeEntry('H', highlands)
                    .addBiomeEntry('b', border)
                    .addBiomeEntry('V', volcanoes))
            // Finish
            .name(new DimensionTH(2, "Testia", "terrainhorizons.dimension.testia"))
            .build();
    }

    /**
     * Creates a biome generator with specific requirements
     *
     * @return The BiomeGenBase used to generated biomes of that type
     */
    protected static BiomeGenBase createLandBiome(String name, TerrainConfiguration terrainConfiguration) {
        BlockMeta andesite = new BlockMeta(Blocks.brick_block);
        BlockMeta anorthosite = new BlockMeta(Blocks.coal_block);

        return new BiomeGenBuilder(BiomeIdOffsetter.getBiomeId()).name(name)
            .height(0.1F, 0.11F)
            .temperature(0.4F)
            .rainfall(0.99F)
            .topBlock(Blocks.stone)
            .fillerBlocks(height -> {
                if (height <= 32) return anorthosite;
                return andesite;
            })
            .terrain(terrainConfiguration)
            .ocean(Blocks.obsidian, Blocks.bookshelf, 1, Blocks.obsidian, 1)
            .surfaceThickness(4)
            .build();
    }

    protected static BiomeGenBase createOceanBiome(String name, TerrainConfiguration terrainConfiguration) {
        BlockMeta basalt = new BlockMeta(Blocks.bookshelf);
        BlockMeta gabbro = new BlockMeta(Blocks.hay_block);

        return new BiomeGenBuilder(BiomeIdOffsetter.getBiomeId()).name(name)
            .height(0.1F, 0.11F)
            .temperature(0.4F)
            .rainfall(0.99F)
            .topBlock(basalt.getBlock())
            .fillerBlocks(height -> {
                if (height <= 32) return gabbro;
                return basalt;
            })
            .terrain(terrainConfiguration)
            .ocean(Blocks.obsidian, Blocks.bookshelf, 80, Blocks.obsidian, 1)
            .oceanCracks(0.3F, Blocks.lava, 4)
            .surfaceThickness(4)
            .build();
    }
}
