package uk.co.shadeddimensions.library.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

public class GuiBetterSlider extends GuiButton
{
    public float sliderValue = 1.0F;
    public boolean dragging;

    public GuiBetterSlider(int id, int x, int y, String displayText, float initialValue)
    {
        super(id, x, y, 150, 20, displayText);
        sliderValue = initialValue;
    }

    public GuiBetterSlider(int id, int x, int y, String displayText, float initialValue, int w)
    {
        super(id, x, y, w, 20, displayText);
        sliderValue = initialValue;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over this button.
     */
    @Override
    protected int getHoverState(boolean par1)
    {
        return 0;
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    @Override
    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
    {
        if (drawButton)
        {
            if (dragging)
            {
                sliderValue = (float) (par2 - (xPosition + 4)) / (float) (width - 8);

                if (sliderValue < 0.0F)
                {
                    sliderValue = 0.0F;
                }

                if (sliderValue > 1.0F)
                {
                    sliderValue = 1.0F;
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexturedModalRect(xPosition + (int) (sliderValue * (width - 8)), yPosition, 0, 66, 4, 20);
            drawTexturedModalRect(xPosition + (int) (sliderValue * (width - 8)) + 4, yPosition, 196, 66, 4, 20);
        }
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent e).
     */
    @Override
    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        if (super.mousePressed(par1Minecraft, par2, par3))
        {
            sliderValue = (float) (par2 - (xPosition + 4)) / (float) (width - 8);

            if (sliderValue < 0.0F)
            {
                sliderValue = 0.0F;
            }

            if (sliderValue > 1.0F)
            {
                sliderValue = 1.0F;
            }

            dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    @Override
    public void mouseReleased(int par1, int par2)
    {
        dragging = false;
    }
}
