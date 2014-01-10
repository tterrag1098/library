package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementBubbles extends ElementProgressBar
{
    boolean showTooltip;

    public ElementBubbles(GuiBase parent, int x, int y)
    {
        this(parent, x, y, 0, 100, false);
    }

    public ElementBubbles(GuiBase parent, int x, int y, int max)
    {
        this(parent, x, y, 0, max, false);
    }

    public ElementBubbles(GuiBase parent, int x, int y, int progress, int max, boolean tooltip)
    {
        super(parent, x, y, progress, max);
        w = 10;
        h = 28;
        showTooltip = tooltip;
    }

    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, 16, 26, w, h);
        
        if (!isDisabled())
        {
            int height = 0;

            if (currentProgress > 0)
            {
                height = Math.round((float)currentProgress * h / maxProgress);
            }

            drawTexturedModalRect(x, y + h - height, 16 + w, 26 + h - height, w + 1, height);
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