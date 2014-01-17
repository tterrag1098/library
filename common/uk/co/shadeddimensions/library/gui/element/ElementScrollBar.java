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
    }

    @Override
    public void draw()
    {
        int scr = Math.min(sizeY - barSize - 1, (int) scroll);
        Gui.drawRect(posX, posY, posX + sizeX, posY + sizeY - 1, 0x33000000);
        Gui.drawRect(posX, posY + scr, posX + sizeX, posY + scr + barSize, 0x77000000);
    }

    @Override
    public void update()
    {
        if (panel.contentHeight < panel.sizeY || !isVisible() || isDisabled())
        {
            return;
        }
        
        barSize = (int)(((float) panel.sizeY / (float) panel.contentHeight) * sizeY);
        
        if (Mouse.isButtonDown(0))
        {
            if (intersectsWith(gui.getMouseX(), gui.getMouseY()))
            {
                if (gui.getMouseY() + gui.getGuiTop() > posY + (int) scroll && gui.getMouseY() + gui.getGuiTop() < posY + (int) scroll + barSize)
                {
                    isMouseButtonDown = true;
                }
            }

            if (isMouseButtonDown)
            {
                scroll += gui.getMouseY() - oldMouse;

                if (scroll < 0)
                {
                    scroll = 0;
                }

                if (scroll > sizeY - barSize)
                {
                    scroll = sizeY - barSize;
                }

                panel.scrollY = -(scroll / sizeY * panel.contentHeight);
            }
        }
        else
        {
            scroll = (-panel.scrollY / panel.contentHeight) * sizeY;
            isMouseButtonDown = false;
        }
        
        if (!isMouseButtonDown)
        {
            scroll = (-panel.scrollY / panel.contentHeight) * sizeY;
        }
        
        oldMouse = gui.getMouseY();

        if (scroll < 0)
        {
            scroll = 0;
        }

        if (scroll > sizeY - barSize)
        {
            scroll = sizeY - barSize;
        }
    }
    
    @Override
    public boolean isVisible()
    {
        visible = panel.isVisible();
        return super.isVisible();
    }
    
    @Override
    public boolean isDisabled()
    {
        disabled = panel.isDisabled();
        return super.isDisabled();
    }
}
