package uk.co.shadeddimensions.library.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import uk.co.shadeddimensions.library.container.ContainerBase;
import uk.co.shadeddimensions.library.gui.element.ElementBase;

public class GuiBase extends GuiContainer
{
    protected ResourceLocation texture;
    protected ArrayList<ElementBase> elements;
    protected int texU, texV, mouseX, mouseY;
    protected boolean drawInventoryText, drawContainerText;

    public GuiBase()
    {
        this(new ContainerBase(), null);
    }

    public GuiBase(Container container, ResourceLocation texture)
    {
        super(container);
        this.texture = texture;
        elements = new ArrayList<ElementBase>();
    }

    public GuiBase setDrawInventoryText()
    {
        drawInventoryText = true;
        return this;
    }

    public GuiBase setDrawContainerText()
    {
        drawContainerText = true;
        return this;
    }

    protected void drawBackground()
    {
        if (texture != null)
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.renderEngine.bindTexture(texture);
            drawTexturedModalRect(guiLeft, guiTop, texU, texV, xSize, ySize);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY)
    {
        drawBackground();

        for (ElementBase element : elements)
        {
            if (element.isVisible())
            {
                element.drawElement(guiLeft, guiTop);
            }
        }

        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        if (drawContainerText && inventorySlots instanceof ContainerBase)
        {
            fontRenderer.drawString(StatCollector.translateToLocal(((ContainerBase) inventorySlots).getUnlocalizedName()), 8, 8, 0x404040);
        }

        if (drawInventoryText)
        {
            fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 3, 0x404040);
        }
        
        for (ElementBase element : elements)
        {
            if (element.intersectsWith(guiLeft, guiTop))
            {
                List<String> list = new ArrayList<String>();
                element.getTooltip(list);
                
                if (!list.isEmpty())
                {
                    drawTooltip(list, mouseX - guiLeft, mouseY - guiTop);
                }
            }
        }
    }

    public void drawTooltip(List<String> list, int x, int y)
    {
        drawHoveringText(list, x, y, fontRenderer);
        GL11.glDisable(GL11.GL_LIGHTING);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseB)
    {
        super.mouseClicked(mouseX, mouseY, mouseB);

        for (ElementBase element : elements)
        {
            if (element.isVisible() && element.intersectsWith(guiLeft, guiTop))
            {
                element.mouseClicked(mouseB);
            }
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();        
        elements.clear();
        addElements();
    }

    protected void addElements()
    {

    }

    public int guiLeft()
    {
        return guiLeft;
    }

    public int guiTop()
    {
        return guiTop;
    }

    public FontRenderer fontRenderer()
    {
        return fontRenderer;
    }

    public TextureManager textureManager()
    {
        return Minecraft.getMinecraft().renderEngine;
    }

    public RenderItem itemRenderer()
    {
        return itemRenderer;
    }

    public int mouseX()
    {
        return mouseX;
    }

    public int mouseY()
    {
        return mouseY;
    }

    public void drawItemStack(ItemStack stack, int x, int y)
    {
        if (stack != null)
        {
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            itemRenderer.renderItemAndEffectIntoGUI(fontRenderer(), textureManager(), stack, x, y);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
        }
    }

    public void buttonClicked(String ID, int mouseButton)
    {

    }
}
