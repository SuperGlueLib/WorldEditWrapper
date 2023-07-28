package me.superpenguin.libraries.kotlin.worldedit

import com.sk89q.worldedit.EditSession
import com.sk89q.worldedit.WorldEdit
import com.sk89q.worldedit.extent.clipboard.Clipboard
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats
import com.sk89q.worldedit.function.pattern.RandomPattern
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import java.io.File

object WorldEditUtil {
    fun getWorldEdit(): WorldEdit = WorldEdit.getInstance()
    fun getNewEditSession(world: World): EditSession = getWorldEdit().newEditSession(world.asWorldEditWorld())
    fun getNewEditSession(player: Player): EditSession = getWorldEdit().newEditSession(player.asWorldEditPlayer())
    fun getSessionManager() = getWorldEdit().sessionManager

    fun randomPatternOf(vararg materials: Material) = randomPatternOf(*materials.map { it to 1.0 }.toTypedArray())
    fun randomPatternOf(vararg entries: Pair<Material, Double>) = entries.fold(RandomPattern()) { acc, entry ->
        acc.apply { add(entry.first.getDefaultBlockState(), entry.second) }
    }

    fun getClipboardFromFile(path: String) = getClipboardFromFile(File(path))
    fun getClipboardFromFile(file: File): Clipboard? {
        var clipboard: Clipboard? = null
        file.inputStream().use {
            ClipboardFormats.findByFile(file)?.getReader(it)?.use {
                clipboard = it.read()
            }
        }
        return clipboard
    }



}
