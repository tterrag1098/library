package uk.co.shadeddimensions.library.gui.element;

import org.lwjgl.opengl.GL11;

import net.minecraft.item.ItemStack;
import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementItemIconWithSlot extends ElementItemIcon
{
    public ElementItemIconWithSlot(GuiBase parent, int x, int y, ItemStack stack)
    {
        super(parent, x, y, stack);
        sizeX = sizeY = 18;
    }
    
    public ElementItemIconWithSlot(GuiBase parent, int x, int y, ItemStack stack, boolean big)
    {
        super(parent, x, y, stack);
        sizeX = sizeY = big ? 26 : 18;
    }
    
    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, sizeX == 18 ? 0 : 18, 0, sizeX, sizeY);
        
        int buffer = sizeX == 18 ? 1 : 5;
        parent.drawItemStack(item, x + buffer, y + buffer);
    }
}
