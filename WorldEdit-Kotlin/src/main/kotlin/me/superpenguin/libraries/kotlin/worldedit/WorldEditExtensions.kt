package me.superpenguin.libraries.kotlin.worldedit

import com.sk89q.worldedit.entity.Player
import com.sk89q.worldedit.extent.clipboard.Clipboard
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat
import com.sk89q.worldedit.function.operation.Operations
import com.sk89q.worldedit.function.pattern.Pattern
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldedit.regions.Region
import com.sk89q.worldedit.session.ClipboardHolder
import com.sk89q.worldedit.session.PasteBuilder
import com.sk89q.worldedit.world.block.BaseBlock
import org.bukkit.Location
import org.bukkit.Material
import java.io.File

private fun Region.getWorldOrThrow() = this.world ?: throw NullPointerException("The region does not specify a world")

// World Modification
/** Functional equivalent of //set, sets a region to the specified pattern/block */
fun Region.setTo(type: Pattern) {
    WorldEditUtil.getNewEditSession(getWorldOrThrow().asBukkitWorld())
        .use { it.setBlocks(this, type) }
}
fun Region.setTo(type: Material): Unit = setTo(type.getDefaultBlockState())

/** Functional equivalent of //replace, replaces from blocks to the specified pattern */
fun Region.replace(from: Set<BaseBlock>, to: Pattern) {
    WorldEditUtil.getNewEditSession(getWorldOrThrow().asBukkitWorld())
        .use { it.replaceBlocks(this, from, to) }
}
fun Region.replace(from: List<Material>, to: Pattern): Unit = replace(from.map { it.getDefaultBlockState().toBaseBlock() }.toSet(), to)
fun Region.replace(from: List<Material>, to: Material): Unit = replace(from, to.getDefaultBlockState())
fun Region.replace(vararg from: Material, to: Pattern): Unit = replace(from.toList(), to)

// Player Sessions
fun Player.getSession() = WorldEditUtil.getSessionManager().get(this)
fun Player.hasSelection() = getSession().let { it.isSelectionDefined(it.selectionWorld ?: return false) }
/** The region object returned should not be mutated */
fun Player.getSelection() = WorldEditUtil.getWorldEdit().sessionManager.get(this).selection

// Clipboard & Schematics
fun Player.getClipboardOrNull() = getSession().runCatching { clipboard }.getOrNull()
fun Clipboard.paste(loc: Location, configuration: ((PasteBuilder) -> PasteBuilder)? = null) = WorldEditUtil.getNewEditSession(loc.world
        ?: throw NullPointerException("Cannot paste without a target world"))
    .use {
        Operations.complete(
            ClipboardHolder(this)
                .createPaste(it)
                .to(BlockVector3.at(loc.blockX, loc.blockY, loc.blockZ))
                .apply { configuration?.invoke(this) }
                .build()
        )
    }
fun Clipboard.saveAsSchematic(file: File) = file.outputStream().use { out ->
    BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(out).use {
        it.write(this)
    }
}