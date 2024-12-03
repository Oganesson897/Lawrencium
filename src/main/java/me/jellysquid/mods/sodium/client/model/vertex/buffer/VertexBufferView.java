package me.jellysquid.mods.sodium.client.model.vertex.buffer;

import me.jellysquid.mods.sodium.client.gl.attribute.BufferVertexFormat;

import java.nio.ByteBuffer;

/**
 * Provides a view into {@link net.minecraft.client.renderer.BufferBuilder} and similar types.
 */
public interface VertexBufferView {
    /**
     * Ensures there is capacity in the buffer for the given number of bytes.
     * @param bytes The number of bytes to allocate space for
     * @return True if the buffer was resized, otherwise false
     */
    boolean lawrencium$ensureBufferCapacity(int bytes);

    /**
     * Returns a handle to the internal storage of this buffer. The buffer can be directly written into at the
     * base address provided by {@link VertexBufferView#lawrencium$getWriterPosition()}.
     *
     * @return A {@link ByteBuffer} in off-heap space
     */
    ByteBuffer lawrencium$getDirectBuffer();

    /**
     * @return The position at which new data should be written to, in bytes
     */
    int lawrencium$getWriterPosition();

    /**
     * Flushes the given number of vertices to this buffer. This ensures that all constraints are still valid, and if
     * so, advances the vertex counter and writer pointer to the end of the data that was written by the caller.
     *  @param vertexCount The number of vertices to flush
     * @param format The format of each vertex
     */
    void lawrencium$flush(int vertexCount, BufferVertexFormat format);

    /**
     * @return The current vertex format of the buffer
     */
    BufferVertexFormat lawrencium$getVertexFormat();
}
