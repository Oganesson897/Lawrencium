package me.jellysquid.mods.sodium.client.render.texture;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.common.Loader;
import zone.rong.loliasm.client.sprite.ondemand.IAnimatedSpriteActivator;

public class SpriteUtil {
    private static final boolean USING_CENSORED_ASM = Loader.isModLoaded("loliasm");

    public static void markSpriteActive(TextureAtlasSprite sprite) {
        if (USING_CENSORED_ASM && sprite instanceof IAnimatedSpriteActivator) {
            ((IAnimatedSpriteActivator) sprite).setActive(true);
        }
    }
}
