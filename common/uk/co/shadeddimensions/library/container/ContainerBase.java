package uk.co.shadeddimensions.library.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class ContainerBase extends Container
{
    Object object;
    
    public ContainerBase()
    {
        
    }
    
    public ContainerBase(Object obj)
    {
        object = obj;
    }
    
    public ContainerBase addPlayerInventorySlots(EntityPlayer player)
    {
        // TODO
        return this;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return object instanceof IInventory ? ((IInventory) object).isUseableByPlayer(entityplayer) : true;
    }
    
    public String getUnlocalizedName()
    {
        return "container.empty";
    }
}
