package me.jellysquid.mods.sodium.client.model.quad;

import me.jellysquid.mods.sodium.client.model.quad.properties.ModelQuadFlags;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.nio.ByteBuffer;

/**
 * Provides a read-only view of a model quad. For mutable access to a model quad, see {@link ModelQuadViewMutable}.
 */
public interface ModelQuadView {
    /**
     * @return The x-position of the vertex at index {@param idx}
     */
    float lawrencium$getX(int idx);

    /**
     * @return The y-position of the vertex at index {@param idx}
     */
    float lawrencium$getY(int idx);

    /**
     * @return The z-position of the vertex at index {@param idx}
     */
    float lawrencium$getZ(int idx);

    /**
     * @return The integer-encoded color of the vertex at index {@param idx}
     */
    int lawrencium$getColor(int idx);

    /**
     * @return The texture x-coordinate for the vertex at index {@param idx}
     */
    float lawrencium$getTexU(int idx);

    /**
     * @return The texture y-coordinate for the vertex at index {@param idx}
     */
    float lawrencium$getTexV(int idx);

    /**
     * @return The integer bit flags containing the {@link ModelQuadFlags} for this quad
     */
    int lawrencium$getFlags();

    /**
     * @return The integer-encoded normal vector for the vertex at index {@param idx}
     */
    int lawrencium$getNormal(int idx);

    /**
     * @return The color index of this quad.
     */
    int lawrencium$getColorIndex();

    /**
     * Copies this quad's data into the specified buffer starting at the given position.
     * @param buf The buffer to write this quad's data to
     * @param position The starting byte index to write to
     */
    default void copyInto(ByteBuffer buf, int position) {
        for (int i = 0; i < 4; i++) {
            buf.putFloat(position, this.lawrencium$getX(i));
            buf.putFloat(position + 4, this.lawrencium$getY(i));
            buf.putFloat(position + 8, this.lawrencium$getZ(i));
            buf.putInt(position + 12, this.lawrencium$getColor(i));
            buf.putFloat(position + 16, this.lawrencium$getTexU(i));
            buf.putFloat(position + 20, this.lawrencium$getTexV(i));

            position += 28;
        }
    }

    /**
     * @return The sprite texture used by this quad, or null if none is attached
     */
    TextureAtlasSprite rubidium$getSprite();
}
