package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.IGuiBase;

public class ElementRedstoneFlux extends ElementProgressBar
{
    public ElementRedstoneFlux(IGuiBase parent, int x, int y, int max)
    {
        this(parent, x, y, 0, max);
    }

    public ElementRedstoneFlux(IGuiBase parent, int x, int y, int progress, int max)
    {
        super(parent, x, y, progress, max);
        sizeX = 14;
        sizeY = 42;
    }

    @Override
    public void addTooltip(List<String> list)
    {
        super.addTooltip(list);

        list.set(0, list.get(0) + " RF");
    }

    @Override
    public void draw()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        gui.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(posX, posY, 228, 0, sizeX, sizeY);

        if (!isDisabled())
        {
            int height = 0;

            if (currentProgress > 0)
            {
                height = Math.round((float) currentProgress * sizeY / maxProgress);
            }

            drawTexturedModalRect(posX, posY + sizeY - height, 228 + sizeX, sizeY - height, sizeX, height);
        }
    }
}
