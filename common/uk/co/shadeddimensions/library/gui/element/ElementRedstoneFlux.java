package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementRedstoneFlux extends ElementProgressBar
{
    public ElementRedstoneFlux(GuiBase parent, int x, int y, int max)
    {
        this(parent, x, y, 0, max);
    }

    public ElementRedstoneFlux(GuiBase parent, int x, int y, int progress, int max)
    {
        super(parent, x, y, progress, max);
        w = 14;
        h = 42;
    }

    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, 228, 0, w, h);
        
        if (!isDisabled())
        {
            int height = 0;

            if (currentProgress > 0)
            {
                height = Math.round((float)currentProgress * h / maxProgress);
            }

            drawTexturedModalRect(x, y + h - height, 228 + w, h - height, w, height);
        }
    }

    @Override
    public void getTooltip(List<String> list)
    {
        super.getTooltip(list);

        list.set(0, list.get(0) + " RF");
    }
}
