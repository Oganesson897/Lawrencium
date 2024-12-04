package me.jellysquid.mods.sodium.client;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.oganesson.lawrencium.Lawrencium;
import me.oganesson.lawrencium.Tags;
import org.apache.logging.log4j.Logger;

public class SodiumClientMod {
    public static final String MODID = "lawrencium";
    public static final String MODNAME = "Lawrencium";
    public static final String MOD_VERSION = Tags.VERSION;

    public static Logger logger() {
        return Lawrencium.getLOGGER();
    }

    public static SodiumGameOptions options() {
        return Lawrencium.INSTANCE.options();
    }

    public static String getVersion() {
        return MOD_VERSION;
    }

    public static boolean isDirectMemoryAccessEnabled() {
        return Lawrencium.INSTANCE.options().advanced.allowDirectMemoryAccess;
    }

}
