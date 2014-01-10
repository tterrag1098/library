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

        if (element.yPos + element.h > contentHeight)
        {
            contentHeight = element.yPos + element.h;
        }

        if (element.xPos + element.w > contentWidth)
        {
            contentWidth = element.xPos + element.w;
        }
        
        return this;
    }

    public boolean isCoordinateVisible(int x, int y)
    {
        return x >= xPos + parent.guiLeft() && x <= xPos + w + parent.guiLeft() && y >= yPos + parent.guiTop() && y <= yPos + h + parent.guiTop();
    }

    @Override
    public void draw(int x, int y)
    {
        for (ElementBase element : elements)
        {
            int X = x + (int) scrollX, Y = y + (int) scrollY, X2 = X + element.xPos, Y2 = Y + element.yPos;

            if (element.isVisible() && (isCoordinateVisible(X2, Y2) || isCoordinateVisible(X2 + element.w, Y2) || isCoordinateVisible(X2, Y2 + element.h) || isCoordinateVisible(X2 + element.w, Y2 + element.h)))
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
            if (element.isVisible() && element.intersectsWith(parent.guiLeft() + xPos + (int) scrollX, parent.guiTop() + yPos + (int) scrollY))
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
            if (parent.mouseX() >= parent.guiLeft() + xPos &&  parent.mouseX() <= parent.guiLeft() + xPos + w && parent.mouseY() >= parent.guiTop() + yPos &&  parent.mouseY() <= parent.guiTop() + yPos + h)
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

        if (contentWidth < w)
        {
            scrollX = 0;
        }
        else if (scrollX < -contentWidth + w)
        {
            scrollX = -contentWidth + w;
        }

        if (contentHeight < h)
        {
            scrollY = 0;
        }
        else if (scrollY < -contentHeight + h)
        {
            scrollY = -contentHeight + h;
        }
    }

    @Override
    public void getTooltip(List<String> list)
    {
        for (ElementBase element : elements)
        {
            if (element.intersectsWith(parent.guiLeft() + xPos + (int) scrollX, parent.guiTop() + yPos + (int) scrollY))
            {
                if (element instanceof ElementBaseContainer)
                {
                    for (ElementBase cElement : ((ElementBaseContainer) element).elements)
                    {
                        if (cElement.intersectsWith(parent.guiLeft() + (int) scrollX + xPos + element.xPos, parent.guiTop() + (int) scrollY + yPos + element.yPos))
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
