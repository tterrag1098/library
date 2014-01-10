package uk.co.shadeddimensions.library.gui.element;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementIcon extends ElementBase
{
    int texU, texV;
    
    public ElementIcon(GuiBase parent, int x, int y, int icon)
    {
        super(parent, x, y, 0, 0);
        
        switch (icon)
        {
            default:
            case 0:
                w = h = 18;
                break;
                
            case 1:
                texU = 18;
                w = h = 26;
                break;
                
            case 2:
                texU = 44;
                w = 22;
                h = 15;
                break;
                
            case 3:
                texU = 88;
                w = 14;
                h = 13;
                break;
                
            case 4:
                texU = 116;
                w = h = 13;
                break;
                
            case 5:
                texV = 53;
                w = h = 15;
                break;
        }
    }

    @Override
    public void mouseClicked(int mouseButton)
    {

    }

    @Override
    protected void update()
    {

    }

    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, texU, texV, w, h);
    }
}
