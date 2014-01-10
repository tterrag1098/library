package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementScrollPanel extends ElementBaseContainer
{
    protected float scrollX, scrollY;
    protected int contentHeight, contentWidth, oldMouseX, oldMouseY;
    protected boolean isMouseButtonDown = false;

    public ElementScrollPanel(GuiBase parent, int x, int y, int w, int h)
    {
        super(parent, x, y, w, h);
        scrollX = scrollY = 0;
    }

    @Override
    public ElementScrollPanel addElement(ElementBase element)
    {
        super.addElement(element);

        if (element.posY + element.sizeY > contentHeight)
        {
            contentHeight = element.posY + element.sizeY;
        }

        if (element.posX + element.sizeX > contentWidth)
        {
            contentWidth = element.posX + element.sizeX;
        }
        
        return this;
    }

    public boolean isCoordinateVisible(int x, int y)
    {
        return x >= posX + parent.guiLeft() && x <= posX + sizeX + parent.guiLeft() && y >= posY + parent.guiTop() && y <= posY + sizeY + parent.guiTop();
    }

    @Override
    public void draw(int x, int y)
    {
        for (ElementBase element : elements)
        {
            int X = x + (int) scrollX, Y = y + (int) scrollY, X2 = X + element.posX, Y2 = Y + element.posY;

            if (element.isVisible() && (isCoordinateVisible(X2, Y2) || isCoordinateVisible(X2 + element.sizeX, Y2) || isCoordinateVisible(X2, Y2 + element.sizeY) || isCoordinateVisible(X2 + element.sizeX, Y2 + element.sizeY)))
            {
                element.drawElement(X, Y);
            }
        }
        
        GL11.glDisable(GL11.GL_DEPTH_TEST); // Stops blocks from being rendered above GUI elements that are rendered afterwards
    }

    @Override
    public void mouseClicked(int mouseButton)
    {
        for (ElementBase element : elements)
        {
            if (element.isVisible() && element.intersectsWith(parent.guiLeft() + posX + (int) scrollX, parent.guiTop() + posY + (int) scrollY))
            {
                element.mouseClicked(mouseButton);
            }
        }
    }

    @Override
    protected void update()
    {
        for (ElementBase element : elements)
        {
            element.update();
        }

        if (Mouse.isButtonDown(0))
        {
            if (parent.mouseX() >= parent.guiLeft() + posX &&  parent.mouseX() <= parent.guiLeft() + posX + sizeX && parent.mouseY() >= parent.guiTop() + posY &&  parent.mouseY() <= parent.guiTop() + posY + sizeY)
            {
                if (isMouseButtonDown)
                {
                    scrollX += parent.mouseX() - oldMouseX;
                    scrollY += parent.mouseY() - oldMouseY;
                }
                else
                {
                    isMouseButtonDown = true;
                }
            }
            
            oldMouseX = parent.mouseX();
            oldMouseY = parent.mouseY();
        }
        else
        {
            isMouseButtonDown = false;
            
            int wheel = Mouse.getDWheel();
            scrollY -= wheel == 0 ? 0 : wheel > 0 ? -10 : 10;
        }

        if (scrollX > 0)
        {
            scrollX = 0;
        }

        if (scrollY > 0)
        {
            scrollY = 0;
        }

        if (contentWidth < sizeX)
        {
            scrollX = 0;
        }
        else if (scrollX < -contentWidth + sizeX)
        {
            scrollX = -contentWidth + sizeX;
        }

        if (contentHeight < sizeY)
        {
            scrollY = 0;
        }
        else if (scrollY < -contentHeight + sizeY)
        {
            scrollY = -contentHeight + sizeY;
        }
    }

    @Override
    public void getTooltip(List<String> list)
    {
        for (ElementBase element : elements)
        {
            if (element.intersectsWith(parent.guiLeft() + posX + (int) scrollX, parent.guiTop() + posY + (int) scrollY))
            {
                if (element instanceof ElementBaseContainer)
                {
                    for (ElementBase cElement : ((ElementBaseContainer) element).elements)
                    {
                        if (cElement.intersectsWith(parent.guiLeft() + (int) scrollX + posX + element.posX, parent.guiTop() + (int) scrollY + posY + element.posY))
                        {
                            cElement.getTooltip(list);
                        }
                    }
                }
                else
                {
                    element.getTooltip(list);
                }
            }
        }
    }
}
