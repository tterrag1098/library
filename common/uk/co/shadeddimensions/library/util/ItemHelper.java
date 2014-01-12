package uk.co.shadeddimensions.library.util;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cofh.api.energy.IEnergyContainerItem;

public class ItemHelper
{
    public static boolean isWrench(ItemStack stack)
    {
        return stack == null ? false : !(stack.getItem() instanceof ItemBlock); // TODO
    }

    public static boolean isEnergyContainerItem(ItemStack stack)
    {
        return stack != null && stack.getItem() instanceof IEnergyContainerItem;
    }
}
