package com.gtnewhorizons.terrainhorizons.api.dimension.biome;

import java.util.ArrayList;
import java.util.List;

import com.gtnewhorizon.gtnhlib.util.data.BlockMeta;
import com.gtnewhorizon.gtnhlib.util.data.ImmutableBlockMeta;
import com.gtnewhorizons.terrainhorizons.api.dimension.cave.CaveShape;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.StratificationFunction;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.TerrainConfiguration;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.details.Terrain3D;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.feature.SurfaceFeature;
import com.gtnewhorizons.terrainhorizons.api.dimension.worldgen.feature.UndergroundFeature;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

/**
 * The builder for biome generation
 */
public class BiomeGenBuilder {

    // Setting basic fields for generation
    private final int id;
    private final ImmutableBlockMeta stone = new BlockMeta(Blocks.stone, 0);

    String name = "unset";
    Height height = new Height(0, 0);
    float temperature = 0.4F;
    float rainfall = 0.0F;
    TerrainConfiguration terrain;
    int oceanHeight = 0;
    ImmutableBlockMeta oceanFiller = stone;
    ImmutableBlockMeta oceanSurface = stone;
    List<SurfaceFeature> surfaceFeatures = new ArrayList<>();
    List<UndergroundFeature> undergroundFeatures = new ArrayList<>();
    StratificationFunction topBlocks;
    StratificationFunction soilBlocks;
    int surfaceThickness = 1;
    boolean enableRain = false;
    ImmutableBlockMeta oceanCrackBlock;
    float oceanCrackThickness;
    int oceanCrackComplexity;
    StratificationFunction fillerBlocks;
    CaveShape caveShape;
    Terrain3D terrain3d;

    List<FlowerEntry> flowers = List.of();
    List<SpawnListEntry> mobsWater = List.of();
    List<SpawnListEntry> mobsCave = List.of();
    List<SpawnListEntry> mobsGeneral = List.of();
    List<SpawnListEntry> mobsMonster = List.of();

    /**
     * Instantiates a builder for a given biome ID
     *
     * @param id The biome ID
     */
    public BiomeGenBuilder(int id) {
        this.id = id;
    }

    /**
     * Set name of biome in builder
     *
     * @param name The required biome name
     * @return Configured builder
     */
    public BiomeGenBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the height of the biome
     *
     * @param low  The lowest point of the biome to generate
     * @param high The highest point of the biome to generate
     * @return Configured builder
     */
    public BiomeGenBuilder height(float low, float high) {
        this.height = new Height(low, high);
        return this;
    }

    /**
     * Set the temperature of the biome
     *
     * @param temp The required temperature of the biome
     * @return Configured builder
     */
    public BiomeGenBuilder temperature(float temp) {
        this.temperature = temp;
        return this;
    }

    /**
     * Set the rainfall of the biome
     *
     * @param rain The required rainfall
     * @return Configured builder
     */
    public BiomeGenBuilder rainfall(float rain) {
        this.rainfall = rain;
        return this;
    }

    /**
     * Set the top block of the biome (Where the block has meta-data)
     *
     * @param topBlocks The required top block (with meta)
     * @return Configured builder
     */
    public BiomeGenBuilder topBlock(StratificationFunction topBlocks) {
        this.topBlocks = topBlocks;
        return this;
    }

    /**
     * Sets the soil block of the biome (ground cover blocks right below the surface)
     * @param soilBlocks The required soil block
     * @return Configured builder
     */
    public BiomeGenBuilder soilBlock(StratificationFunction soilBlocks) {
        this.soilBlocks = soilBlocks;
        return this;
    }

    public BiomeGenBuilder fillerBlocks(StratificationFunction fillerBlocks) {
        this.fillerBlocks = fillerBlocks;
        return this;
    }

    /**
     * Sets the ocean for the biome
     *
     * @param oceanFiller  The filler block for the ocean to use (with meta)
     * @param oceanSurface The surface block of the ocean (water, ice layer etc.) [with meta]
     * @param oceanHeight  The height of the ocean (sea-level)
     * @return Configured builder
     */
    public BiomeGenBuilder ocean(Block oceanFiller, Block oceanSurface, int oceanHeight) {
        this.oceanFiller = new BlockMeta(oceanFiller);
        this.oceanSurface = new BlockMeta(oceanSurface);
        this.oceanHeight = oceanHeight;
        return this;
    }

    public BiomeGenBuilder oceanCracks(float oceanCrackThickness, Block oceanCrackBlock, int oceanCrackComplexity) {
        this.oceanCrackThickness = oceanCrackThickness;
        this.oceanCrackBlock = new BlockMeta(oceanCrackBlock);
        this.oceanCrackComplexity = oceanCrackComplexity;
        return this;
    }

    /**
     * Sets the required terrain configuration for the biome
     *
     * @param terrain The terrain configuration to use in the biome
     * @return Configured builder
     */
    public BiomeGenBuilder terrain(TerrainConfiguration terrain) {
        this.terrain = terrain;
        return this;
    }

    public BiomeGenBuilder surfaceFeature(SurfaceFeature feature) {
        surfaceFeatures.add(feature);
        return this;
    }

    public BiomeGenBuilder undergroundFeature(UndergroundFeature feature) {
        undergroundFeatures.add(feature);
        return this;
    }

    public BiomeGenBuilder caveShape(CaveShape caveShape) {
        this.caveShape = caveShape;
        return this;
    }

    public BiomeGenBuilder surfaceThickness(int surfaceThickness) {
        this.surfaceThickness = surfaceThickness;
        return this;
    }

    public BiomeGenBuilder terrain3d(Terrain3D terrain3d) {
        this.terrain3d = terrain3d;
        return this;
    }

    /**
     * Sets the mobs able to spawn in this biome
     *
     * @param list The list of possible spawns
     * @return Configured builder
     */
    public BiomeGenBuilder mobsAll(List<SpawnListEntry> list) {
        this.mobsGeneral = list;
        this.mobsMonster = list;
        this.mobsWater = list;
        this.mobsCave = list;
        return this;
    }

    /**
     * Sets the general (passive) mobs that can spawn in this biome
     *
     * @param list The list of general mobs that can spawn
     * @return Configured builder
     */
    public BiomeGenBuilder mobsGeneral(List<SpawnListEntry> list) {
        this.mobsGeneral = list;
        return this;
    }

    /**
     * Sets the monster (hostile mobs) mobs that can spawn in this biome
     *
     * @param list The list of monster mobs that can spawn
     * @return Configured builder
     */
    public BiomeGenBuilder mobsMonster(List<SpawnListEntry> list) {
        this.mobsMonster = list;
        return this;
    }

    /**
     * Sets the water (squids etc.) mobs that can spawn in this biome
     *
     * @param list The list of water mobs that can spawn
     * @return Configured builder
     */
    public BiomeGenBuilder mobsWater(List<SpawnListEntry> list) {
        this.mobsWater = list;
        return this;
    }

    /**
     * Sets the cave (bats etc.) mobs that can spawn in this biome
     *
     * @param list The list of cave mobs that can spawn
     * @return Configured builder
     */
    public BiomeGenBuilder mobsCave(List<SpawnListEntry> list) {
        this.mobsCave = list;
        return this;
    }

    /**
     * Sets the flowers that can spawn in this biome
     *
     * @param list The list of flowers that can spawn
     * @return Configured builder
     */
    public BiomeGenBuilder flowers(List<FlowerEntry> list) {
        this.flowers = list;
        return this;
    }

    /**
     * Builds the BiomeGenSpace based on given fields
     *
     * @return BiomeGenSpace configured from previous chained methods
     */
    public BiomeGenTH build() {
        return new BiomeGenTH(id, this);
    }
}
