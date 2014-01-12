package uk.co.shadeddimensions.library.gui.element;

import net.minecraft.item.ItemStack;
import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementItemIconWithCount extends ElementItemIcon
{
    public ElementItemIconWithCount(GuiBase parent, int x, int y, ItemStack stack)
    {
        super(parent, x, y, stack);
    }
    
    @Override
    public void draw()
    {
        super.draw();        
        gui.getItemRenderer().renderItemOverlayIntoGUI(gui.getFontRenderer(), gui.getTextureManager(), item, posX, posY, item.stackSize > 999 ? "+" : null);
    }
}
