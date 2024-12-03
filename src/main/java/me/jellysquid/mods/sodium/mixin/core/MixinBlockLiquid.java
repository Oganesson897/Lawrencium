package me.jellysquid.mods.sodium.mixin.core;

import me.jellysquid.mods.sodium.client.world.VanillaFluidBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mixin(BlockLiquid.class)
public abstract class MixinBlockLiquid implements VanillaFluidBlock {
    @Unique
    private final IFluidBlock sodium$fluidBlock = new Implementation((Block)(Object)this);

    @Override
    public IFluidBlock lawrencium$getFakeFluidBlock() {
        return sodium$fluidBlock;
    }
}