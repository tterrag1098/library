package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementButton extends ElementBase
{
    String ID, displayText, hoverText;

    public ElementButton(GuiBase parent, int x, int y, int w, String id, String text, String hover)
    {
        super(parent, x, y, w, 20);
        ID = id;
        displayText = text;
        hoverText = hover;
    }
    
    public ElementButton(GuiBase parent, int x, int y, int w, String id, String text)
    {
        this(parent, x, y, w, id, text, null);
    }
    
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, 0, 196 + (!isDisabled() ? intersectsWith(x, y) ? sizeY * 2 : sizeY : 0), sizeX / 2, sizeY);
        drawTexturedModalRect(x + sizeX / 2, y, 200 - sizeX / 2, 196 + (!isDisabled() ? intersectsWith(x, y) ? sizeY * 2 : sizeY : 0), sizeX / 2, sizeY);
        parent.fontRenderer().drawStringWithShadow(displayText, x + (sizeX / 2 - parent.fontRenderer().getStringWidth(displayText) / 2), y + (sizeY / 2 - parent.fontRenderer().FONT_HEIGHT / 2), (!isDisabled() ? intersectsWith(x + parent.guiLeft(), y + parent.guiTop()) ? 16777120 : 14737632 : -6250336));
    }

    @Override
    public void mouseClicked(int mouseButton)
    {
        if (!isDisabled() && isVisible())
        {
            Minecraft.getMinecraft().sndManager.playSoundFX("random.click", 1.0F, 1.0F);
            parent.buttonClicked(ID, mouseButton);
        }
    }

    @Override
    protected void update()
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
}
