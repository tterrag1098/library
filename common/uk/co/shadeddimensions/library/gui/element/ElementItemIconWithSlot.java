package uk.co.shadeddimensions.library.gui.element;

import org.lwjgl.opengl.GL11;

import net.minecraft.item.ItemStack;
import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementItemIconWithSlot extends ElementItemIcon
{
    public ElementItemIconWithSlot(GuiBase parent, int x, int y, ItemStack stack)
    {
        super(parent, x, y, stack);
        w = h = 18;
    }
    
    public ElementItemIconWithSlot(GuiBase parent, int x, int y, ItemStack stack, boolean big)
    {
        super(parent, x, y, stack);
        w = h = big ? 26 : 18;
    }
    
    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, w == 18 ? 0 : 18, 0, w, h);
        
        int buffer = w == 18 ? 1 : 5;
        parent.drawItemStack(item, x + buffer, y + buffer);
    }
}
