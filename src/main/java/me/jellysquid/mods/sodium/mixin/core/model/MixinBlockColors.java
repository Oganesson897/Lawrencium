package me.jellysquid.mods.sodium.mixin.core.model;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import me.jellysquid.mods.sodium.client.world.biome.BlockColorsExtended;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockColors.class)
public class MixinBlockColors implements BlockColorsExtended {
    @Unique
    private Reference2ReferenceMap<Block, IBlockColor> lawrencium$blocksToColor;

    @Unique
    private static final IBlockColor DEFAULT_PROVIDER = (state, view, pos, tint) -> -1;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        this.lawrencium$blocksToColor = new Reference2ReferenceOpenHashMap<>();
        this.lawrencium$blocksToColor.defaultReturnValue(DEFAULT_PROVIDER);
    }

    @Inject(method = "registerBlockColorHandler", at = @At("HEAD"))
    private void preRegisterColor(IBlockColor provider, Block[] blocks, CallbackInfo ci) {
        // Synchronize because Forge mods register this without enqueuing the call on the main thread
        // and then blame Embeddium for the crash because of the mixin, despite vanilla using a non-concurrent
        // HashMap too
        synchronized (this.lawrencium$blocksToColor) {
            for (Block block : blocks) {
                if(provider != null)
                    this.lawrencium$blocksToColor.put(block, provider);
            }
        }
    }

    @Override
    public IBlockColor lawrencium$getColorProvider(IBlockState state) {
        return this.lawrencium$blocksToColor.get(state.getBlock());
    }

    @Override
    public boolean lawrencium$hasColorProvider(IBlockState state) {
        return this.lawrencium$blocksToColor.get(state.getBlock()) != DEFAULT_PROVIDER;
    }
}
