package uk.co.shadeddimensions.library.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

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
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }
    
    public String getUnlocalizedName()
    {
        return "container.empty";
    }
}
