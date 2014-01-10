package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import uk.co.shadeddimensions.library.gui.GuiBase;

public abstract class ElementBase extends Gui
{
    protected GuiBase parent;
    protected int xPos, yPos, w, h;
    protected ResourceLocation texture = new ResourceLocation("alzlib", "textures/gui/elements.png");
    protected boolean disabled = false, visible = true;
    
    public ElementBase(GuiBase parent, int x, int y, int w, int h)
    {
        this.parent = parent;
        this.xPos = x;
        this.yPos = y;
        this.w = w;
        this.h = h;
    }

    public void drawElement(int x, int y)
    {
        update();
        draw(x + xPos, y + yPos);
    }
    
    public boolean intersectsWith(int x, int y)
    {
        return parent.mouseX() >= x + xPos && parent.mouseX() < x + xPos + w && parent.mouseY() >= y + yPos && parent.mouseY() < y + yPos + h;
    }
    
    /**
     * Gets the tooltip of this gui element.
     */
    public void getTooltip(List<String> list)
    {
        
    }
    
    public boolean isVisible()
    {
        return visible;
    }
    
    public void setVisible(boolean b)
    {
        visible = b;
    }
    
    public boolean isDisabled()
    {
        return disabled;
    }
    
    public void setDisabled(boolean state)
    {
        disabled = state;
    }
    
    public abstract void draw(int x, int y);
    public abstract void mouseClicked(int mouseButton);
    protected abstract void update();
}
