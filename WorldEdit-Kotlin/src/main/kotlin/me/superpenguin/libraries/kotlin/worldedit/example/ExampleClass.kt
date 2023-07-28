package me.superpenguin.libraries.kotlin.worldedit.example

import me.superpenguin.libraries.kotlin.worldedit.WorldEditUtil
import me.superpenguin.libraries.kotlin.worldedit.paste
import org.bukkit.Bukkit
import org.bukkit.Location

private class ExampleClass {
    /**
     * Paste a schematic from a file at 0, 0, 0 in "world"
     */
    fun pasteSchematicFromFile() {
        val location = Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0)
        val filePath = "plugins/MyPlugin/schematic.schem"
        WorldEditUtil.getClipboardFromFile(filePath)?.paste(location)
            ?: Bukkit.getLogger().info("Could not find a schematic at $filePath")
    }

    /**
     * Configure and paste a schematic from a file at 0, 0, 0 in "world"
     */
    fun configureAndPasteSchematic() {
        val location = Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0)
        val filePath = "plugins/MyPlugin/schematic.schem"
        WorldEditUtil.getClipboardFromFile(filePath)?.paste(location) {
              it.copyEntities(true)
                .ignoreAirBlocks(true)
        } ?: Bukkit.getLogger().info("Could not find a schematic at $filePath")
    }
}