package me.superpenguin.libraries.kotlin.worldedit

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.bukkit.BukkitPlayer
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player

fun Player.asWorldEditPlayer(): BukkitPlayer = BukkitAdapter.adapt(this)
fun World.asWorldEditWorld(): com.sk89q.worldedit.world.World = BukkitAdapter.adapt(this)
fun Material.asBlockType() = BukkitAdapter.asBlockType(this)
fun Material.getDefaultBlockState() = asBlockType()?.defaultState ?: throw UnsupportedOperationException("This material cannot be converted to a base block")
