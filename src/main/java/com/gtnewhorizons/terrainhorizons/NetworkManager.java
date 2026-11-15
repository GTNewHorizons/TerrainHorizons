package com.gtnewhorizons.terrainhorizons;

import com.gtnewhorizons.terrainhorizons.test.TeleportRequestPacket;
import cpw.mods.fml.relauncher.Side;

import static com.gtnewhorizons.terrainhorizons.TerrainHorizons.TERRAIN_HORIZONS_NETWORK;

public class NetworkManager {
    // spotless:off
    public static void registerPackets() {
        int id = 0;

        TERRAIN_HORIZONS_NETWORK.registerMessage(TeleportRequestPacket.Handler.class, TeleportRequestPacket.class, id++,
            Side.SERVER);
    }
    // spotless:on
}
