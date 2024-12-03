package me.oganesson.lawrencium

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
import org.jetbrains.annotations.Nullable
import zone.rong.mixinbooter.IEarlyMixinLoader
import java.util.Collections

@IFMLLoadingPlugin.Name("Lawrencium")
@IFMLLoadingPlugin.MCVersion("1.12.2")
class LawrenciumCore : IFMLLoadingPlugin, IEarlyMixinLoader {
    override fun getMixinConfigs(): List<String> {
        return Collections.singletonList("mixins.lawrencium.early.json")
    }

    override fun getASMTransformerClass(): Array<String>? {
        return null
    }

    override fun getModContainerClass(): String? {
        return null
    }

    @Nullable
    override fun getSetupClass(): String? {
        return null
    }

    override fun injectData(data: Map<String?, Any?>?) {
    }

    override fun getAccessTransformerClass(): String? {
        return null
    }
}