package uk.co.shadeddimensions.library.util;

import net.minecraft.item.ItemStack;
import cofh.api.energy.IEnergyContainerItem;

public class ItemHelper
{
    public static boolean isWrench(ItemStack stack)
    {
        return stack == null ? false : stack.itemID == 256; // TODO
    }

    public static boolean isEnergyContainerItem(ItemStack stack)
    {
        return stack != null && stack.getItem() instanceof IEnergyContainerItem;
    }
}
