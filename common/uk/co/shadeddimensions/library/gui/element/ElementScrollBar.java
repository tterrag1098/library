package uk.co.shadeddimensions.library.gui.element;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.Gui;
import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementScrollBar extends ElementBase
{
    ElementScrollPanel panel;
    int barSize;
    boolean isMouseButtonDown;
    float scroll, oldMouse;

    public ElementScrollBar(GuiBase parent, int x, int y, int w, int h, ElementScrollPanel scroll)
    {
        super(parent, x, y, w, h);
        panel = scroll;
        barSize = (int)(((float) scroll.h / (float) scroll.contentHeight) * h);
    }

    @Override
    public void draw(int x, int y)
    {
        if (panel.contentHeight < panel.h)
        {
            return;
        }
        
        Gui.drawRect(x, y, x + w, y + h, 0x33000000);
        Gui.drawRect(x, y + (int) scroll, x + w, y + (int) scroll + barSize, 0x77000000);
    }

    @Override
    public void mouseClicked(int mouseButton)
    {

    }

    @Override
    protected void update()
    {
        if (Mouse.isButtonDown(0))
        {
            if (parent.mouseX() >= parent.guiLeft() + xPos &&  parent.mouseX() <= parent.guiLeft() + xPos + w && parent.mouseY() >= parent.guiTop() + yPos + (int) scroll &&  parent.mouseY() <= parent.guiTop() + yPos + (int) scroll + barSize)
            {
                if (isMouseButtonDown)
                {
                    scroll += parent.mouseY() - oldMouse;
                    panel.scrollY = -(scroll / h * panel.contentHeight);
                }
                else
                {
                    isMouseButtonDown = true;
                }
            }
            else
            {
                scroll = (-panel.scrollY / panel.contentHeight) * h;
            }
            
            oldMouse = parent.mouseY();
            
        }
        else
        {
            scroll = (-panel.scrollY / panel.contentHeight) * h;
            isMouseButtonDown = false;
        }
        
        if (scroll < 0)
        {
            scroll = 0;
        }
        
        if (scroll > h - barSize)
        {
            scroll = h - barSize;
        }
    }
}
