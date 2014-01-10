package uk.co.shadeddimensions.library.gui.element;

import java.util.List;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import uk.co.shadeddimensions.library.gui.GuiBase;

public class ElementFluidTank extends ElementProgressBar
{
    byte scale;
    Fluid fluid;

    public ElementFluidTank(GuiBase parent, int x, int y, int progress, int max, Fluid f, int scale)
    {
        super(parent, x, y, progress, max);
        sizeX = 18;
        sizeY = 62;
        this.scale = (byte) scale;
        fluid = f;
    }

    public void setFluid(Fluid f)
    {
        if (fluid == null)
        {
            currentProgress = 0;
        }

        fluid = f;
    }

    public void setFluid(FluidStack stack)
    {
        if (stack == null)
        {
            fluid = null;
            currentProgress = 0;
        }
        else
        {
            fluid = stack.getFluid();
            currentProgress = stack.amount;
        }
    }

    @Override
    public void draw(int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        parent.textureManager().bindTexture(texture);
        drawTexturedModalRect(x, y, 210, 0, sizeX, sizeY);
        
        if (!isDisabled() && fluid != null)
        {
            int height = 0;

            if (currentProgress > 0)
            {
                height = Math.round((float)currentProgress * sizeY / maxProgress);
                height = Math.min(height, sizeY - 2);
            }

            Icon texture = fluid.getIcon();
            int colour = fluid.getColor();
            parent.textureManager().bindTexture(TextureMap.locationBlocksTexture);
            GL11.glColor3ub((byte) (colour >> 16 & 0xFF), (byte) (colour >> 8 & 0xFF), (byte) (colour & 0xFF));
            
            for (int i = 0; i < height; i += 16)
            {
                drawScaledTexturedModelRectFromIcon(x + 1, y + sizeY - height - 1 + i, texture, 16, Math.min(height - i, 16));
            }
        }

        if (scale != 0)
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            parent.textureManager().bindTexture(texture);
            drawTexturedModalRect(x + 1, y + (scale == 1 ? 6 : 14), scale == 1 ? 228 : 210, scale == 1 ? 42 : 62, sizeX, sizeY);
        }
    }

    public void drawScaledTexturedModelRectFromIcon(int x, int y, Icon icon, int width, int height)
    {
        if (icon == null)
        {
            return;
        }

        double minU = icon.getMinU();
        double maxU = icon.getMaxU();
        double minV = icon.getMinV();
        double maxV = icon.getMaxV();

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, this.zLevel, minU, minV + (maxV - minV) * height / 16F);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, minU + (maxU - minU) * width / 16F, minV + (maxV - minV) * height / 16F);
        tessellator.addVertexWithUV(x + width, y + 0, this.zLevel, minU + (maxU - minU) * width / 16F, minV);
        tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, minU, minV);
        tessellator.draw();
    }

    @Override
    public void getTooltip(List<String> list)
    {
        super.getTooltip(list);
        list.set(0, list.get(0) + " mB");
    }
}
