package com.gtnewhorizons.terrainhorizons.test;

import com.gtnewhorizon.gtnhlib.util.data.BlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.DimensionTH;
import com.gtnewhorizons.terrainhorizons.api.dimension.biome.BiomeGenBuilder;
import com.gtnewhorizons.terrainhorizons.api.dimension.biome.BiomeIdOffsetter;
import com.gtnewhorizons.terrainhorizons.api.dimension.provider.WorldProviderBuilder;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.BiomeMatrixGenerator;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.TerrainConfiguration;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.TerrainPreset;
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
        BiomeGenBase border = createLavaOceanBiome(
            "Testia Lava Ocean Edge",
            TerrainConfiguration.builder()
                .feature(TerrainPreset.MOUNTAIN_RANGES)
                .width(4)
                .height(32)
                .endFeature()
                .build());
        BiomeGenBase volcanoes = createLavaOceanBiome(
            "Testia Volcanoes",
            TerrainConfiguration.builder()
                .feature(TerrainPreset.BASE_HEIGHT)
                .height(-32)
                .endFeature()
                .feature(TerrainPreset.SHIELD_VOLCANOES)
                .replacementBlock(Blocks.lava)
                .width(2)
                .height(48)
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
            "Testia Highlands",
            TerrainConfiguration.builder()
                .feature(TerrainPreset.BASE_HEIGHT)
                .height(8)
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
        BiomeGenBase lowlands = createLandBiome(
            "Testia Lowlands",
            TerrainConfiguration.builder()
                .feature(TerrainPreset.BASE_HEIGHT)
                .height(-40)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.lowerExtreme,
                    TerrainModifier.WEIRDNESS.upperExtreme)
                .endFeature()
                .feature(TerrainPreset.MOUNTAIN_RANGES)
                .width(3)
                .height(40)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.lowerExtreme,
                    TerrainModifier.WEIRDNESS.upperExtreme)
                .endFeature()
                .feature(TerrainPreset.MOUNTAIN_RANGES)
                .width(32)
                .height(32)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.minimum,
                    TerrainModifier.WEIRDNESS.middle)
                .endFeature()
                .feature(TerrainPreset.CANYONS)
                .width(4)
                .height(16)
                .modifier(
                    TerrainModifier.WEIRDNESS,
                    TerrainModifier.WEIRDNESS.middle,
                    TerrainModifier.WEIRDNESS.maximum)
                .endFeature()
                .build());
        builder.sky(true)
            .fog(0.4, 0.4, 0.4)
            .skyColor(0.3, 0.5, 0.5f)
            .avgGround(0)
            // Biome matrix
            .biomeMatrix(
                new BiomeMatrixGenerator(new String[] { "LLLLLLLL", "LLLLLLLL", "LLHHHHHH", "LLHHHHHH", "LLHHHBHH", "LLHHBVBH", "LLHHHBHH", "LLHHHHHH" })
                    .addBiomeEntry('H', highlands)
                    .addBiomeEntry('B', border)
                    .addBiomeEntry('V', volcanoes)
                    .addBiomeEntry('L', lowlands))
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
        BlockMeta andesite = new BlockMeta(Blocks.stone);
        BlockMeta anorthosite = new BlockMeta(Blocks.iron_ore);
        BlockMeta grass = new BlockMeta(Blocks.grass);
        BlockMeta snow = new BlockMeta(Blocks.snow);
        BlockMeta dirt = new BlockMeta(Blocks.dirt);
        BlockMeta ice = new BlockMeta(Blocks.ice);
        BlockMeta gravel = new BlockMeta(Blocks.gravel);
        BlockMeta sand = new BlockMeta(Blocks.sand);
        BlockMeta sandstone = new BlockMeta(Blocks.sandstone);

        return new BiomeGenBuilder(BiomeIdOffsetter.getBiomeId()).name(name)
            .height(0.1F, 0.11F)
            .temperature(0.4F)
            .rainfall(0.99F)
            .topBlock(height -> {
                if (height < 0) {
                    return gravel;
                } else if (height < 4) {
                    return sand;
                } else if (height < 64) {
                    return grass;
                } else if (height < 128) {
                    return snow;
                }
                return ice;
            })
            .soilBlock(height -> {
                if (height < 0) {
                    return gravel;
                } else if (height < 4) {
                    return sandstone;
                } else if (height < 64) {
                    return dirt;
                } else if (height < 128) {
                    return snow;
                }
                return ice;
            })
            .fillerBlocks(height -> {
                if (height <= -32) return anorthosite;
                return andesite;
            })
            .terrain(terrainConfiguration)
            .ocean(Blocks.water, Blocks.water, 0)
            .surfaceThickness(4)
            .build();
    }

    protected static BiomeGenBase createLavaOceanBiome(String name, TerrainConfiguration terrainConfiguration) {
        BlockMeta basalt = new BlockMeta(Blocks.obsidian);
        BlockMeta gabbro = new BlockMeta(Blocks.stone);

        return new BiomeGenBuilder(BiomeIdOffsetter.getBiomeId()).name(name)
            .height(0.1F, 0.11F)
            .temperature(0.4F)
            .rainfall(0.99F)
            .topBlock(_ -> basalt)
            .soilBlock(_ -> basalt)
            .fillerBlocks(height -> {
                if (height <= -32) return gabbro;
                return basalt;
            })
            .terrain(terrainConfiguration)
            .ocean(Blocks.lava, basalt.getBlock(), 0)
            .oceanCracks(0.3F, Blocks.air, 4)
            .surfaceThickness(4)
            .build();
    }
}
