package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementFire extends ElementProgressBar
{
    boolean showTooltip;

    public ElementFire(GuiBase parent, int x, int y)
    {
        this(parent, x, y, 0, 100, false);
    }

    public ElementFire(GuiBase parent, int x, int y, int max)
    {
        this(parent, x, y, 0, max, false);
    }

    public ElementFire(GuiBase parent, int x, int y, int progress, int max, boolean tooltip)
    {
        super(parent, x, y, progress, max);
        w = 14;
        h = 14;
        showTooltip = tooltip;
    }

    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, 88, 0, w, h);
        
        int height = 0;

        if (currentProgress > 0)
        {
            height = Math.round((float)currentProgress * h / maxProgress);
        }

        drawTexturedModalRect(x, y + height, 88 + w, height, w, h);

        if (isDisabled())
        {
            drawTexturedModalRect(x, y, 0, 53, 15, 15);
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
