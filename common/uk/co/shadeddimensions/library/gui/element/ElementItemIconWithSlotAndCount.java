package uk.co.shadeddimensions.library.gui.element;

import org.lwjgl.opengl.GL11;

import net.minecraft.item.ItemStack;
import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementItemIconWithSlotAndCount extends ElementItemIcon
{
    public ElementItemIconWithSlotAndCount(GuiBase parent, int x, int y, ItemStack stack)
    {
        super(parent, x, y, stack);
        sizeX = sizeY = 18;
    }
    
    public ElementItemIconWithSlotAndCount(GuiBase parent, int x, int y, ItemStack stack, boolean big)
    {
        super(parent, x, y, stack);
        sizeX = sizeY = big ? 26 : 18;
    }
    
    @Override
    public void draw()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        gui.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(posX, posY, sizeX == 18 ? 0 : 18, 0, sizeX, sizeY);
        
        int buffer = sizeX == 18 ? 1 : 5;
        gui.drawItemStack(item, posX + buffer, posY + buffer);
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), gui.getTextureManager(), item, posX + buffer, posY + buffer, item.stackSize > 999 ? "+" : null);
    }
}
