package uk.co.shadeddimensions.library.gui.element;

import java.util.ArrayList;
import java.util.List;

import uk.co.shadeddimensions.library.gui.GuiBase;
import uk.co.shadeddimensions.library.gui.Parser;

public abstract class ElementBaseContainer extends ElementBase
{
    ArrayList<ElementBase> elements;

    public ElementBaseContainer(GuiBase parent, int x, int y, int w, int h)
    {
        super(parent, x, y, w, h);
        elements = new ArrayList<ElementBase>();
    }

    public ElementBaseContainer parseElements(String string)
    {
        for (ElementBase element : new Parser(parent).setMaxWidth(sizeX).parse(string))
        {
            addElement(element);
        }
        
        return this;
    }
    
    public ElementBaseContainer addElement(ElementBase element)
    {
        elements.add(element);
        
        return this;
    }

    @Override
    public void draw(int x, int y)
    {
        for (ElementBase element : elements)
        {
            if (element.isVisible())
            {
                element.drawElement(x, y);
            }
        }
    }

    @Override
    public void mouseClicked(int mouseButton)
    {
        for (ElementBase element : elements)
        {
            if (element.isVisible() && element.intersectsWith(parent.guiLeft() + posX, parent.guiTop() + posY))
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
            if (element.isVisible())
            {
                element.update();
            }
        }
    }
    
    @Override
    public void getTooltip(List<String> list)
    {
        for (ElementBase element : elements)
        {
            if (element.intersectsWith(parent.guiLeft() + posX, parent.guiTop() + posY))
            {
                element.getTooltip(list);
            }
        }
    }
}
