package minefantasy.mf2.client.render;

import java.awt.Color;

import minefantasy.mf2.api.archery.Arrows;
import minefantasy.mf2.api.archery.IDisplayMFArrows;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.block.tileentity.TileEntityCarpenterMF;
import minefantasy.mf2.client.gui.GuiHelper;
import minefantasy.mf2.config.ConfigClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class MineFantasyHUD extends Gui
{
	private static Minecraft mc = Minecraft.getMinecraft();
	public void renderGameOverlay(float partialTicks, int mouseX, int mouseY) 
	{
		if(mc.thePlayer != null)
		{
			renderArmourRating(mc.thePlayer);
			
			if(StaminaBar.isSystemActive && !mc.thePlayer.capabilities.isCreativeMode)
			{
				renderStaminaBar(mc.thePlayer);
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			
			int[] coords = getClickedBlock(partialTicks, mouseX, mouseY);
			if(coords == null)return;
			
			int x = coords[0];
			int y = coords[1];
			int z = coords[2];
			EntityPlayer player = mc.thePlayer;
			World world = player.worldObj;
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile != null)
			{
				if(tile instanceof TileEntityAnvilMF)
				{
					this.renderCraftMetre(world, player, (TileEntityAnvilMF)tile);
				}
				if(tile instanceof TileEntityCarpenterMF)
				{
					this.renderCraftMetre(world, player, (TileEntityCarpenterMF)tile);
				}
			}
		}
	}
	
	public int[] getClickedBlock(float ticks, int mouseX, int mouseY)
	{
		if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            int j = mc.objectMouseOver.blockX;
            int k = mc.objectMouseOver.blockY;
            int l = mc.objectMouseOver.blockZ;
            
            return new int[]{j, k, l};
        }
		return null;
	}

	private void renderArmourRating(EntityPlayer player)
	{
		ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
		
        
        int[] orientationAR = getOrientsFor(width, height, ConfigClient.AR_xOrient, ConfigClient.AR_yOrient);
        int xPosAR = orientationAR[0] + ConfigClient.AR_xPos;
        int yPosAR = orientationAR[1] + ConfigClient.AR_yPos;
        
		int AR = ArmourCalculator.getTotalArmourRating(player);
        mc.fontRenderer.drawStringWithShadow("AR: "+ AR, xPosAR, yPosAR, Color.WHITE.getRGB());
        
        ItemStack held = player.getHeldItem();
        if(held != null && (held.getItem() instanceof IDisplayMFArrows || held.getItem() == Items.bow))
        {
        	ItemStack arrow = Arrows.getPresetArrow(player);
        	
        	if(arrow != null)
        	{
        		String text = arrow.getDisplayName();
        		if(ConfigClient.displayArrowCount)
        		{
        			text += " x"+Arrows.getArrowCount(player);
        		}
        		
        		int[] orientationAC = getOrientsFor(width, height, ConfigClient.AC_xOrient, ConfigClient.AC_yOrient);
                int xPosAC = orientationAR[0] + ConfigClient.AC_xPos;
                int yPosAC = orientationAR[1] + ConfigClient.AC_yPos;
                
        		mc.fontRenderer.drawStringWithShadow(text, xPosAC, yPosAC, Color.WHITE.getRGB());
        	}
        }
	}
	
	private void renderStaminaBar(EntityPlayer player)
	{
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        float staminaMax = StaminaBar.getTotalMaxStamina(player);
        float staminaAt = StaminaBar.getStaminaValue(player);
        
		float staminaPercentage = staminaMax > 0 ? Math.min(1.0F, staminaAt / staminaMax) : 0F;
		
		float flash = StaminaBar.getFlashTime(player);
		int stam = (int)Math.min(81F, (81F * staminaPercentage));
		ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        bindTexture("textures/gui/hud_overlay.png");
        
        int[] orientation = getOrientsFor(width, height, ConfigClient.stam_xOrient, ConfigClient.stam_yOrient);
        int xPos = orientation[0] + ConfigClient.stam_xPos;
        int yPos = orientation[1] + ConfigClient.stam_yPos;
        this.drawTexturedModalRect(xPos, yPos, 0, 0, 81, 5);
        
        int modifier = modifyMetre(stam, 81, ConfigClient.stam_direction);
        this.drawTexturedModalRect(xPos + modifier, yPos, 0, 5, stam, 5);
        
        if(flash > 0 && player.ticksExisted % 10 < 5)
        {
        	this.drawTexturedModalRect(xPos, yPos, 0, 10, 81, 5);
        }
        /*
        else if(StaminaBar.getBonusStaminaRegenTicks(player) > 0 && player.ticksExisted % 10 < 5)
        {
        	this.drawTexturedModalRect(xPos, yPos, 0, 15, 81, 5);
        }
        */
        
        String stamTxt = (int)staminaAt + " / " + (int)staminaMax;
        boolean bonus = StaminaBar.getBonusStamina(player) > 0;
        
        if(mc.currentScreen != null && mc.currentScreen instanceof GuiInventory)
        {
        	mc.fontRenderer.drawStringWithShadow(stamTxt, xPos + 41 - (mc.fontRenderer.getStringWidth(stamTxt)/2), yPos-2, bonus ? Color.CYAN.getRGB() : Color.WHITE.getRGB());
        }
        GL11.glDisable(GL11.GL_BLEND);
	}
	
	private int modifyMetre(int i, int max, int cfg)
	{
		if(cfg == 0)
		{
			if(max == i)
			{
				return (max-i)/2;
			}
			else
			{
				return (max-i-1)/2;
			}
		}
		if(cfg == 1)
		{
			if(max == i)
			{
				return max-i;
			}
			else
			{
				return max-i-1;
			}
		}
		return 0;
	}

	public void bindTexture(String image) 
	{
		mc.renderEngine.bindTexture(TextureHelperMF.getResource(image));
	}
	
	/**
	 * Gets the 2 config values to get the X,Y orient
	 */
	public static int[] getOrientsFor(int screenX, int screenY, int cfgX, int cfgY)
	{
		int xOrient = cfgX == -1 ? 0 : cfgX == 1 ? screenX : screenX/2;
		int yOrient = cfgY == -1 ? 0 : cfgY == 1 ? screenY : screenY/2;
		
		return new int[]{xOrient, yOrient};
	}
	
	private void renderCraftMetre(World world, EntityPlayer player, TileEntityAnvilMF tile) 
	{
		boolean knowsCraft = tile.doesPlayerKnowCraft(mc.thePlayer);
		GL11.glPushMatrix();
		ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        
        bindTexture("textures/gui/hud_overlay.png");
        int xPos = width/2 + -86;
        int yPos = height - 69;
        
        this.drawTexturedModalRect(xPos, yPos, 84, 0, 172, 20);
        this.drawTexturedModalRect(xPos+6, yPos+12, 90, 20, tile.getProgressBar(160), 3);
        
        String s = knowsCraft ? tile.getResultName() : "????";
        mc.fontRenderer.drawString(s, xPos + 86 - (mc.fontRenderer.getStringWidth(s) / 2), yPos+3, 0);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        
        if(knowsCraft && !tile.resName.equalsIgnoreCase("") && tile.getToolNeeded() != null)
        {
        	GuiHelper.renderToolIcon(this, tile.getToolNeeded(), tile.getToolTierNeeded(), xPos-20, yPos);
        	if(tile.getAnvilTierNeeded() > -1)
        	{
        		GuiHelper.renderToolIcon(this, "anvil", tile.getAnvilTierNeeded(), xPos+172, yPos);
        	}
        }
        
        GL11.glPopMatrix();
	}
	
	
	private void renderCraftMetre(World world, EntityPlayer player, TileEntityCarpenterMF tile) 
	{
		boolean knowsCraft = tile.doesPlayerKnowCraft(mc.thePlayer);
		GL11.glPushMatrix();
		ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        
        bindTexture("textures/gui/hud_overlay.png");
        int xPos = width/2 + -86;
        int yPos = height - 69;
        
        this.drawTexturedModalRect(xPos, yPos, 84, 0, 172, 20);
        this.drawTexturedModalRect(xPos+6, yPos+12, 90, 20, tile.getProgressBar(160), 3);
        
        String s = knowsCraft ? tile.getResultName() : "????";
        mc.fontRenderer.drawString(s, xPos + 86 - (mc.fontRenderer.getStringWidth(s) / 2), yPos+3, 0);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        
        if(knowsCraft && !tile.resName.equalsIgnoreCase("") && tile.getToolNeeded() != null)
        {
        	GuiHelper.renderToolIcon(this, tile.getToolNeeded(), tile.getToolTierNeeded(), xPos-20, yPos);
        }
        
        GL11.glPopMatrix();
	}
}
