package com.gtnewhorizons.terrainhorizons.test;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.gtnewhorizons.terrainhorizons.TerrainHorizons.TERRAIN_HORIZONS_NETWORK;

/**
 * Simple debug teleporter to reach Testia
 */
public class ItemTeleporter extends Item {

    /**
     * Defines the right click interaction to trigger a teleportation
     *
     * @param stack  The item stack defining the held item
     * @param world  The current world of the using player
     * @param player The player using the item
     * @return The item stack initially used
     */
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
            TERRAIN_HORIZONS_NETWORK.sendToServer(new TeleportRequestPacket(2, 0, 256, 0));
        }
        player.swingItem();
        return stack;
    }
}
