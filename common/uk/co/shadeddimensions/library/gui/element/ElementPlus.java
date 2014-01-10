package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementPlus extends ElementProgressBar
{
    boolean showTooltip, horizontal;

    public ElementPlus(GuiBase parent, int x, int y)
    {
        this(parent, x, y, 0, 100, false, false);
    }

    public ElementPlus(GuiBase parent, int x, int y, int max)
    {
        this(parent, x, y, 0, max, false, false);
    }

    public ElementPlus(GuiBase parent, int x, int y, int progress, int max, boolean tooltip, boolean horiz)
    {
        super(parent, x, y, progress, max);
        sizeX = 13;
        sizeY = 13;
        showTooltip = tooltip;
        horizontal = horiz;
    }

    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, 116, 0, sizeX, sizeY);
        
        if (horizontal)
        {
            int width = 0;

            if (currentProgress > 0)
            {
                width = Math.round((float)currentProgress * sizeX / maxProgress);
            }

            drawTexturedModalRect(x, y, 116 + sizeX, 0, width, sizeY);
        }
        else
        {
            int height = 0;

            if (currentProgress > 0)
            {
                height = Math.round((float)currentProgress * sizeY / maxProgress);
            }

            drawTexturedModalRect(x, y + sizeY - height, 116 + sizeX, sizeY - height, sizeX, height);
        }

        if (isDisabled())
        {
            drawTexturedModalRect(x - 1, y - 1, 0, 53, 15, 15);
        }
    }

    @Override
    public void getTooltip(List<String> list)
    {
        if (showTooltip)
        {
            super.getTooltip(list);
        }
    }
}
