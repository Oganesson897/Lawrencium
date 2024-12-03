package me.jellysquid.mods.sodium.mixin.core.pipeline;

import me.jellysquid.mods.sodium.client.model.quad.ModelQuadView;
import me.jellysquid.mods.sodium.client.model.quad.properties.ModelQuadFlags;
import me.jellysquid.mods.sodium.client.render.vertex.VertexFormatDescription;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BakedQuad.class)
public class MixinBakedQuad implements ModelQuadView {

    @Shadow
    @Final
    protected TextureAtlasSprite sprite;

    @Shadow
    @Final
    protected int tintIndex;

    @Shadow public int[] getVertexData() {
        throw new AssertionError();
    }

    @Shadow @Final protected VertexFormat format;
    @Unique
    protected int lawrencium$cachedFlags;

    @Unique
    private VertexFormatDescription lawrencium$formatDescription;

    @Inject(method = "<init>([IILnet/minecraft/util/EnumFacing;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;ZLnet/minecraft/client/renderer/vertex/VertexFormat;)V", at = @At("RETURN"))
    private void init(int[] vertexData, int colorIndex, EnumFacing face, TextureAtlasSprite sprite, boolean shade, VertexFormat format, CallbackInfo ci) {
        this.lawrencium$formatDescription = VertexFormatDescription.get(format);
        if(!UnpackedBakedQuad.class.isAssignableFrom(this.getClass())) {
            this.lawrencium$cachedFlags = ModelQuadFlags.getQuadFlags((BakedQuad) (Object) this);
        }
    }

    @Unique
    private int lawrencium$vertexOffset(int idx) {
        return idx * this.format.getIntegerSize();
    }

    @Override
    public float lawrencium$getX(int idx) {
        int positionIndex = this.lawrencium$formatDescription.getIndex(VertexFormatDescription.Element.POSITION);
        if (positionIndex == -1) {
            return 0;
        }
        return Float.intBitsToFloat(this.getVertexData()[lawrencium$vertexOffset(idx) + positionIndex]);
    }

    @Override
    public float lawrencium$getY(int idx) {
        int positionIndex = this.lawrencium$formatDescription.getIndex(VertexFormatDescription.Element.POSITION);
        if (positionIndex == -1) {
            return 0;
        }
        return Float.intBitsToFloat(this.getVertexData()[lawrencium$vertexOffset(idx) + positionIndex + 1]);
    }

    @Override
    public float lawrencium$getZ(int idx) {
        int positionIndex = this.lawrencium$formatDescription.getIndex(VertexFormatDescription.Element.POSITION);
        if (positionIndex == -1) {
            return 0;
        }
        return Float.intBitsToFloat(this.getVertexData()[lawrencium$vertexOffset(idx) + positionIndex + 2]);
    }

    @Override
    public int lawrencium$getColor(int idx) {
        int colorIndex = this.lawrencium$formatDescription.getIndex(VertexFormatDescription.Element.COLOR);
        if (colorIndex == -1) {
            return 0;
        }
        return this.getVertexData()[lawrencium$vertexOffset(idx) + colorIndex];
    }

    @Override
    public TextureAtlasSprite rubidium$getSprite() {
        return this.sprite;
    }

    @Override
    public float lawrencium$getTexU(int idx) {
        int textureIndex = this.lawrencium$formatDescription.getIndex(VertexFormatDescription.Element.TEXTURE);
        if (textureIndex == -1) {
            return 0;
        }
        return Float.intBitsToFloat(this.getVertexData()[lawrencium$vertexOffset(idx) + textureIndex]);
    }

    @Override
    public float lawrencium$getTexV(int idx) {
        int textureIndex = this.lawrencium$formatDescription.getIndex(VertexFormatDescription.Element.TEXTURE);
        if (textureIndex == -1) {
            return 0;
        }
        return Float.intBitsToFloat(this.getVertexData()[lawrencium$vertexOffset(idx) + textureIndex + 1]);
    }

    @Override
    public int lawrencium$getFlags() {
        return this.lawrencium$cachedFlags;
    }

    @Override
    public int lawrencium$getNormal(int idx) {
        int normalIndex = this.lawrencium$formatDescription.getIndex(VertexFormatDescription.Element.NORMAL);
        if (normalIndex == -1) {
            return 0;
        }
        return this.getVertexData()[lawrencium$vertexOffset(idx) + normalIndex];
    }

    @Override
    public int lawrencium$getColorIndex() {
        return this.tintIndex;
    }
}
