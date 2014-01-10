package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementRightArrow extends ElementProgressBar
{
    boolean showTooltip;

    public ElementRightArrow(GuiBase parent, int x, int y)
    {
        this(parent, x, y, 0, 100, false);
    }

    public ElementRightArrow(GuiBase parent, int x, int y, int max)
    {
        this(parent, x, y, 0, max, false);
    }

    public ElementRightArrow(GuiBase parent, int x, int y, int progress, int max, boolean tooltip)
    {
        super(parent, x, y, progress, max);
        w = 22;
        h = 15;
        showTooltip = tooltip;
    }

    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, 44, 0, w, h);
        
        int width = 0;

        if (currentProgress > 0)
        {
            width = Math.round((float)currentProgress * w / maxProgress);
        }

        drawTexturedModalRect(x, y, 44 + w, 0, width, h);

        if (isDisabled())
        {
            drawTexturedModalRect(x + 3, y, 0, 53, 15, 15);
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
