package me.oganesson.lawrencium

import io.github.chaosunity.forgelin.Forgelin
import me.jellysquid.mods.sodium.client.SodiumClientMod
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(
    modid = Tags.MOD_ID,
    name = Tags.MOD_NAME,
    version = Tags.VERSION,
    modLanguageAdapter = Forgelin.ADAPTER,
    dependencies = "required-after:forgelin_continuous;required-after:loliasm;required-after:mixinbooter"
)
object Lawrencium {

    @kotlin.jvm.JvmStatic
    var LOGGER: Logger = LogManager.getLogger("Lawrencium")

    private var CONFIG: SodiumGameOptions? = null

    fun options(): SodiumGameOptions? {
        if (CONFIG == null) {
            CONFIG = loadConfig()
        }
        return CONFIG
    }

    private fun loadConfig(): SodiumGameOptions {
        return SodiumGameOptions.load(
            Minecraft.getMinecraft().gameDir.toPath().resolve("config").resolve(SodiumClientMod.MODID + "-options.json")
        )
    }

    fun getVersion(): String {
        return SodiumClientMod.MOD_VERSION
    }

    fun isDirectMemoryAccessEnabled(): Boolean {
        return options()!!.advanced.allowDirectMemoryAccess
    }

}