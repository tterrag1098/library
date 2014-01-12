package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementProgressBar extends ElementBase
{
    protected int currentProgress, maxProgress;

    public ElementProgressBar(GuiBase parent, int x, int y, int max)
    {
        this(parent, x, y, 0, max);
    }

    public ElementProgressBar(GuiBase parent, int x, int y, int progress, int max)
    {
        super(parent, x, y, 42, 4);
        currentProgress = progress;
        maxProgress = max;
    }

    public void incrementProgress(int progress)
    {
        if (!isDisabled())
        {
            currentProgress += progress;
        }
    }

    public void decrementProgress(int progress)
    {
        if (!isDisabled())
        {
            currentProgress -= progress;
        }
    }

    public void setProgress(int progress)
    {
        if (!isDisabled())
        {
            currentProgress = progress;
        }
    }

    public void setMaximum(int max)
    {
        maxProgress = max;
    }

    @Override
    public void draw()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        gui.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(posX, posY, 44, 18, sizeX, sizeY);
        
        int width = 0;

        if (currentProgress > 0)
        {
            width = Math.round((float)currentProgress * sizeX / maxProgress);
        }

        drawTexturedModalRect(posX, posY, 44, 18 + 4, width, sizeY);
    }

    @Override
    public void update()
    {
        incrementProgress(1);

        if (currentProgress > maxProgress)
        {
            setProgress(0);
        }
    }

    @Override
    public void addTooltip(List<String> list)
    {
        list.add(currentProgress + " / " + maxProgress);
    }
}
