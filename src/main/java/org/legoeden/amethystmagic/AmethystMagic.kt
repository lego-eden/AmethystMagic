package org.legoeden.amethystmagic

import org.bukkit.plugin.java.JavaPlugin
import org.legoeden.amethystmagic.listeners.PlayerCrystalUseListener

class AmethystMagic : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        logger.info("Loading...")
        registerListeners()
        logger.info("Success")
    }

    private fun registerListeners() {
        logger.info("Registering listeners...")
        server.pluginManager.registerEvents(PlayerCrystalUseListener(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
