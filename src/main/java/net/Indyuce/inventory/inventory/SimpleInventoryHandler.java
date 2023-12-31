package net.Indyuce.inventory.inventory;

import io.lumine.mythic.lib.UtilityMethods;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import net.Indyuce.inventory.MMOInventory;
import net.Indyuce.inventory.slot.CustomSlot;
import net.Indyuce.inventory.util.Utils;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SimpleInventoryHandler extends InventoryHandler {

    /**
     * Used when MMOInventory does not use the custom inventory and when the
     * items must be placed in the vanilla player's inventory
     */
    public SimpleInventoryHandler(MMOPlayerData playerData) {
        super(playerData);
    }

    @Override
    protected void insertItemAtSlot(@NotNull CustomSlot slot, @Nullable ItemStack item) {
        getPlayer().getInventory().setItem(slot.getIndex(), item);
    }

    @Override
    public Collection<InventoryItem> retrieveItems() {
        final Set<InventoryItem> set = new HashSet<>();

        // For each special slot
        for (CustomSlot slot : MMOInventory.plugin.getSlotManager().getCustomSlots()) {

            // Get that item
            ItemStack item = getPlayer().getInventory().getItem(slot.getIndex());

            // Is the item not a 'default' MMOInventory display thingy
            if (!UtilityMethods.isAir(item) && !Utils.isGuiItem(item)) set.add(new InventoryItem(item, slot));
        }

        return set;
    }
}
