package org.legoeden.amethystmagic.listeners

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.plugin.java.JavaPlugin
import org.legoeden.amethystmagic.magiccontroller.AmethystCrush
import java.util.UUID

class PlayerCrystalUseListener(plugin: JavaPlugin) : Listener {
    private var timers = HashMap<UUID, AmethystCrush>()
    private var plugin: JavaPlugin

    init {
        this.plugin = plugin
    }

    @EventHandler
    private fun amethystRightClick(event: PlayerInteractEvent) {
        if (event.hand == EquipmentSlot.OFF_HAND || event.action.isLeftClick) return
        val player = event.player
        if (player.inventory.itemInMainHand.type != Material.AMETHYST_SHARD) return
        addTimer(player)
    }

    private fun addTimer(player: Player) {
        try {
            (timers[player.uniqueId] as AmethystCrush).resetHeldTimer()
        } catch (e: Exception) {
            timers[player.uniqueId] = AmethystCrush(plugin, player, timers)
        }
    }
}