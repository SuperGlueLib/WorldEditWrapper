package me.superpenguin.libraries.kotlin.worldedit

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.world.World

fun World.asBukkitWorld(): org.bukkit.World = BukkitAdapter.adapt(this)