package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementText extends ElementBase
{
    int colour;
    String displayText, hoverText;
    boolean shadow;

    public ElementText(GuiBase parent, int x, int y, String text, String hover)
    {
        this(parent, x, y, text, hover, 0x404040, false);
    }
    
    public ElementText(GuiBase parent, int x, int y, String text, String hover, int c, boolean s)
    {
        super(parent, x, y, parent.fontRenderer().getStringWidth(text), parent.fontRenderer().FONT_HEIGHT);
        displayText = text;
        hoverText = hover;
        colour = c;
        shadow = s;
    }

    @Override
    public void draw(int x, int y)
    {
        if (shadow)
        {
            parent.fontRenderer().drawStringWithShadow(displayText, x, y, colour);
        }
        else
        {
            parent.fontRenderer().drawString(displayText, x, y, colour);
        }
    }

    @Override
    public void mouseClicked(int mouseButton)
    {

    }
    
    @Override
    public void getTooltip(List<String> list)
    {
        if (hoverText != null)
        {
            list.add(hoverText);
        }
    }

    @Override
    protected void update()
    {

    }
}
