package uk.co.shadeddimensions.library.gui.element;

import java.util.ArrayList;
import java.util.List;

import uk.co.shadeddimensions.library.gui.GuiBase;
import uk.co.shadeddimensions.library.gui.Parser;

public abstract class ElementBaseContainer extends ElementBase
{
    protected ArrayList<ElementBase> elements;

    public ElementBaseContainer(GuiBase parent, int x, int y, int w, int h)
    {
        super(parent, x, y, w, h);
        elements = new ArrayList<ElementBase>();
    }

    public ElementBaseContainer parseElements(String string)
    {
        for (ElementBase element : new Parser(gui).setMaxWidth(sizeX).parse(string))
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
    public void draw()
    {
        for (ElementBase element : elements)
        {
            if (element.isVisible())
            {
                element.draw(posX + element.getRelativeX(), posY + element.getRelativeY());
            }
        }
    }
    
    @Override
    public boolean handleMouseClicked(int x, int y, int mouseButton)
    {
        for (ElementBase element : elements)
        {
            if (element.isVisible() && element.intersectsWith(x, y))
            {
                if (element.handleMouseClicked(x, y, mouseButton))
                {
                    return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public void update()
    {
        for (ElementBase element : elements)
        {
            element.update();
        }
    }
    
    @Override
    public void addTooltip(List<String> list)
    {
        for (ElementBase element : elements)
        {
            if (element.intersectsWith(gui.getMouseX(), gui.getMouseY()))
            {
                element.addTooltip(list);
                
                if (!list.isEmpty())
                {
                    return;
                }
            }
        }
    }
}
