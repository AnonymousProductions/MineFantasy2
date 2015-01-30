package minefantasy.mf2.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.client.render.TextureHelperMF;
import minefantasy.mf2.container.ContainerAnvilMF;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAnvilMF extends GuiContainer
{
    private TileEntityAnvilMF tile;

    public GuiAnvilMF(InventoryPlayer user, TileEntityAnvilMF tile)
    {
        super(new ContainerAnvilMF(user, tile));
        this.ySize = 198;
        this.tile = tile;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	String s = MineFantasyII.isDebug() ? "Anvil Crafting" : StatCollector.translateToLocal(tile.getResultName());
        this.fontRendererObj.drawString(s, 10, 6, 0);
        
        int xPoint = (this.width - this.xSize) / 2;
        int yPoint = (this.height - this.ySize) / 2;
        
        if(!tile.resName.equalsIgnoreCase(""))
        {
	        if(tile.getToolNeeded() != null)
	        {
		        if(x < xPoint && x > xPoint-20 && y < yPoint+20 && y > yPoint)
		        {
		        	String s2 = StatCollector.translateToLocal("tooltype."+tile.getToolNeeded()) + ", "  + (tile.getToolTierNeeded() > -1 ? StatCollector.translateToLocal("attribute.mfcrafttier.name") + " " + tile.getToolTierNeeded() : StatCollector.translateToLocal("attribute.nomfcrafttier.name"));
		        	this.fontRendererObj.drawStringWithShadow(s2, -18, -12, 16777215);
		        }
	        }
	        if(x < xPoint+xSize+20 && x > xPoint+xSize && y < yPoint+20 && y > yPoint)
	        {
	        	String s2 = StatCollector.translateToLocal("tooltype.anvil") + ", " + (tile.getAnvilTierNeeded() > -1 ? StatCollector.translateToLocal("attribute.mfcrafttier.name") + " " + tile.getAnvilTierNeeded() : StatCollector.translateToLocal("attribute.nomfcrafttier.name"));
	        	this.fontRendererObj.drawStringWithShadow(s2, xSize-fontRendererObj.getStringWidth(s2)+18, -12, 16777215);
	        }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TextureHelperMF.getResource("textures/gui/anvil.png"));
        int xPoint = (this.width - this.xSize) / 2;
        int yPoint = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xPoint, yPoint, 0, 0, this.xSize, this.ySize);
    
        if(tile.progressMax > 0 && tile.progress > 0)
        {
        	int progressWidth = (int)(160F/tile.progressMax*tile.progress);
        	this.drawTexturedModalRect(xPoint+8, yPoint+22, 0, 198, progressWidth, 3);
        }
        if(!tile.resName.equalsIgnoreCase(""))
        {
        	GuiHelper.renderToolIcon(this, "anvil", tile.getAnvilTierNeeded(), xPoint+xSize, yPoint);
        	
	        if(tile.getToolNeeded() != null)
	        {
	        	GuiHelper.renderToolIcon(this, tile.getToolNeeded(), tile.getToolTierNeeded(), xPoint-20, yPoint);
	        }
        }
    }
    
    @Override
    public void drawScreen(int x, int y, float f)
    {
        super.drawScreen(x, y, f);
    }
    private void renderItem(ItemStack itemstack, int x, int y, int mouseX, int mouseY)
    {
    	itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, x, y);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), itemstack, x, y);
    }
    private void renderItemName(ItemStack itemstack, int x, int y, int mouseX, int mouseY)
    {
        if (this.func_146978_c(x-guiLeft, y-guiTop, 16, 16, mouseX, mouseY))
        {
            this.renderToolTip(itemstack, mouseX, mouseY);
        }
    }
}