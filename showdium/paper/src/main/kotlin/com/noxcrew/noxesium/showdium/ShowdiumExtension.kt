package com.noxcrew.noxesium.showdium

import com.noxcrew.noxesium.paper.NoxesiumPaper
import org.bukkit.plugin.java.JavaPlugin

/**
 * Sets up the example extension.
 */
public class ShowdiumExtension : JavaPlugin() {
    override fun onLoad() {
        val noxesiumPaper = getPlugin(NoxesiumPaper::class.java)
        noxesiumPaper.registerEntrypoint { ShowdiumPaperEntrypoint() }
    }
}
