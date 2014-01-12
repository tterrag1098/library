package uk.co.shadeddimensions.library.gui.slot;

import uk.co.shadeddimensions.library.util.ItemHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEnergy extends Slot
{
    public SlotEnergy(IInventory inventory, int x, int y, int z)
    {
        super(inventory, x, y, z);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return ItemHelper.isEnergyContainerItem(stack);
    }
}
