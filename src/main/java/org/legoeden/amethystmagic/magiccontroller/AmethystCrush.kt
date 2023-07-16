package org.legoeden.amethystmagic.magiccontroller

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.UUID

class AmethystCrush(plugin: JavaPlugin, player: Player, map: HashMap<UUID, AmethystCrush>) : BukkitRunnable() {
    private var maxHeld = 2
    private var maxCountdown = 5
    private var plugin: JavaPlugin
    private var player: Player
    private var map: HashMap<UUID, AmethystCrush>
    private var held: Int
    private var countdown: Int

    init {
        this.plugin = plugin
        this.player = player
        this.held = maxHeld
        this.countdown = maxCountdown
        this.map = map
        this.runTaskTimer(plugin, 0, 2)
    }

    override fun run() {
        player.sendMessage("$countdown, $held")
        if (held > 0) {
            if (countdown > 0) {
                player.world.playSound(player, Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 0.5F + 0.5F * (1 - (countdown.toFloat()) / maxCountdown))
//                player.world.playSound(player, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0F, 1.5F + 0.5F * (1 - (countdown.toFloat()) / maxCountdown))
            } else if (countdown == 0) {
                if (player.inventory.itemInMainHand.type != Material.AMETHYST_SHARD) return

                player.world.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1.0F, 1.0F)
                player.world.playSound(player, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 5.0F)
                player.world.playSound(player, Sound.BLOCK_NOTE_BLOCK_FLUTE, 1.0F, 5.0F)
                player.world.playSound(player, Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F)
//                player.world.playSound(player, Sound.BLOCK_NOTE_BLOCK_SNARE, 1.0F, 2.0F)
                player.world.playSound(player, Sound.BLOCK_GLASS_BREAK, 0.5F, 1.0F)
                player.inventory.itemInMainHand.amount--
            } else if (countdown <= -4) {
                map.remove(player.uniqueId)
                this.cancel()
            }
            countdown--
            held--

        } else {
            map.remove(player.uniqueId)
            this.cancel()
        }
    }

    fun resetHeldTimer() {
        held = maxHeld
    }
}