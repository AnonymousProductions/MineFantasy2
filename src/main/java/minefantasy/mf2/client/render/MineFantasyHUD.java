package minefantasy.mf2.client.render;

import java.awt.Color;

import minefantasy.mf2.api.archery.Arrows;
import minefantasy.mf2.api.archery.IDisplayMFArrows;
import minefantasy.mf2.api.crafting.IBasicMetre;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.helpers.GuiHelper;
import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.block.tileentity.TileEntityCarpenterMF;
import minefantasy.mf2.block.tileentity.TileEntityTanningRack;
import minefantasy.mf2.config.ConfigClient;
import minefantasy.mf2.item.weapon.ItemWeaponMF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
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
			
			BlockPos pos = new BlockPos(x,y,z);
			TileEntity tile = world.getTileEntity(pos);
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
				if(tile instanceof TileEntityTanningRack)
				{
					this.renderCraftMetre(world, player, (TileEntityTanningRack)tile);
				}
				if(tile instanceof IBasicMetre)
				{
					this.renderCraftMetre(world, player, (IBasicMetre)tile);
				}
			}
		}
	}
	
	public int[] getClickedBlock(float ticks, int mouseX, int mouseY)
	{
		if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            
			int j = mc.objectMouseOver.getBlockPos().getX();
            int k = mc.objectMouseOver.getBlockPos().getY();
            int l = mc.objectMouseOver.getBlockPos().getZ();
            
            return new int[]{j, k, l};
        }
		return null;
	}
	private void renderArmourRating(EntityPlayer player)
	{
		ScaledResolution scaledresolution = new ScaledResolution(MineFantasyHUD.mc, MineFantasyHUD.mc.displayWidth, MineFantasyHUD.mc.displayHeight);
		int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
		
        
        int[] orientationAR = getOrientsFor(width, height, ConfigClient.AR_xOrient, ConfigClient.AR_yOrient);
        int xPosAR = orientationAR[0] + ConfigClient.AR_xPos;
        int yPosAR = orientationAR[1] + ConfigClient.AR_yPos;
        
		int AR = ArmourCalculator.getTotalArmourRating(player);
        if(ArmourCalculator.usePercentage)
        {
        	mc.fontRendererObj.drawStringWithShadow("AR: "+ ItemWeaponMF.decimal_format.format(AR)+"%", xPosAR, yPosAR, Color.WHITE.getRGB());
        }
        else
        {
        	mc.fontRendererObj.drawStringWithShadow("AR: "+ (int)AR, xPosAR, yPosAR, Color.WHITE.getRGB());
        }
        ItemStack held = player.getHeldItem();
        if(held != null && (held.getItem() instanceof IDisplayMFArrows || held.getItem() == Items.bow))
        {
        	ItemStack arrow = Arrows.getPresetArrow(player);
        	
        	if(arrow != null)
        	{
        		String text = arrow.getDisplayName();
        		if(Arrows.displayArrowCount)
        		{
        			text += " x"+Arrows.getArrowCount(player);
        		}
        		
        		int[] orientationAC = getOrientsFor(width, height, ConfigClient.AC_xOrient, ConfigClient.AC_yOrient);
                int xPosAC = orientationAR[0] + ConfigClient.AC_xPos;
                int yPosAC = orientationAR[1] + ConfigClient.AC_yPos;
                
        		mc.fontRendererObj.drawStringWithShadow(text, xPosAC, yPosAC, Color.WHITE.getRGB());
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
		ScaledResolution scaledresolution = new ScaledResolution(MineFantasyHUD.mc, MineFantasyHUD.mc.displayWidth, MineFantasyHUD.mc.displayHeight);
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
        	mc.fontRendererObj.drawStringWithShadow(stamTxt, xPos + 41 - (mc.fontRendererObj.getStringWidth(stamTxt)/2), yPos-2, bonus ? Color.CYAN.getRGB() : Color.WHITE.getRGB());
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
		ScaledResolution scaledresolution = new ScaledResolution(MineFantasyHUD.mc, MineFantasyHUD.mc.displayWidth, MineFantasyHUD.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        
        bindTexture("textures/gui/hud_overlay.png");
        int xPos = width/2 + -86;
        int yPos = height - 69;
        
        this.drawTexturedModalRect(xPos, yPos, 84, 0, 172, 20);
        this.drawTexturedModalRect(xPos+6, yPos+12, 90, 20, tile.getProgressBar(160), 3);
        
        String s = knowsCraft ? tile.getResultName() : "????";
        mc.fontRendererObj.drawString(s, xPos + 86 - (mc.fontRendererObj.getStringWidth(s) / 2), yPos+3, 0);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        
        if(knowsCraft && !tile.resName.equalsIgnoreCase("") && tile.getToolNeeded() != null)
        {
        	boolean available = ToolHelper.isToolSufficient(player.getHeldItem(), tile.getToolNeeded(), tile.getToolTierNeeded());
        	GuiHelper.renderToolIcon(this, tile.getToolNeeded(), tile.getToolTierNeeded(), xPos-20, yPos, available);
        	if(tile.getAnvilTierNeeded() > -1)
        	{
        		GuiHelper.renderToolIcon(this, "anvil", tile.getAnvilTierNeeded(), xPos+172, yPos, tile.tier >= tile.getAnvilTierNeeded());
        	}
        }
        
        GL11.glPopMatrix();
	}
	
	
	private void renderCraftMetre(World world, EntityPlayer player, TileEntityCarpenterMF tile) 
	{
		boolean knowsCraft = tile.doesPlayerKnowCraft(mc.thePlayer);
		GL11.glPushMatrix();
		ScaledResolution scaledresolution = new ScaledResolution(MineFantasyHUD.mc, MineFantasyHUD.mc.displayWidth, MineFantasyHUD.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        
        bindTexture("textures/gui/hud_overlay.png");
        int xPos = width/2 + -86;
        int yPos = height - 69;
        
        this.drawTexturedModalRect(xPos, yPos, 84, 0, 172, 20);
        this.drawTexturedModalRect(xPos+6, yPos+12, 90, 20, tile.getProgressBar(160), 3);
        
        String s = knowsCraft ? tile.getResultName() : "????";
        mc.fontRendererObj.drawString(s, xPos + 86 - (mc.fontRendererObj.getStringWidth(s) / 2), yPos+3, 0);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        
        if(knowsCraft && !tile.resName.equalsIgnoreCase("") && tile.getToolNeeded() != null)
        {
        	boolean available = ToolHelper.isToolSufficient(player.getHeldItem(), tile.getToolNeeded(), tile.getToolTierNeeded());
        	GuiHelper.renderToolIcon(this, tile.getToolNeeded(), tile.getToolTierNeeded(), xPos-20, yPos, available);
        }
        
        GL11.glPopMatrix();
	}
	
	private void renderCraftMetre(World world, EntityPlayer player, TileEntityTanningRack tile) 
	{
		boolean knowsCraft = tile.doesPlayerKnowCraft(mc.thePlayer);
		GL11.glPushMatrix();
		ScaledResolution scaledresolution = new ScaledResolution(MineFantasyHUD.mc, MineFantasyHUD.mc.displayWidth, MineFantasyHUD.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        
        bindTexture("textures/gui/hud_overlay.png");
        int xPos = width/2 + -86;
        int yPos = height - 69;
        
        this.drawTexturedModalRect(xPos, yPos, 84, 0, 172, 20);
        this.drawTexturedModalRect(xPos+6, yPos+12, 90, 20, tile.getProgressBar(160), 3);
        
        String s = knowsCraft ? tile.getResultName() : "????";
        ItemStack result = tile.items[1];
        if(result != null && result.stackSize > 1)
        {
        	s += " x"+result.stackSize;
        }
        mc.fontRendererObj.drawString(s, xPos + 86 - (mc.fontRendererObj.getStringWidth(s) / 2), yPos+3, 0);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        
        String resName = tile.getResultName();
        
        if(knowsCraft && !resName.equalsIgnoreCase("") && tile.toolType != null)
        {
        	boolean available = ToolHelper.isToolSufficient(player.getHeldItem(), "knife", -1);
        	GuiHelper.renderToolIcon(this, tile.toolType, tile.tier, xPos-20, yPos, available);
        }
        
        GL11.glPopMatrix();
	}
	
	private void renderCraftMetre(World world, EntityPlayer player, IBasicMetre tile) 
	{
		GL11.glPushMatrix();
		ScaledResolution scaledresolution = new ScaledResolution(MineFantasyHUD.mc, MineFantasyHUD.mc.displayWidth, MineFantasyHUD.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        
        bindTexture("textures/gui/hud_overlay.png");
        int xPos = width/2 + -86;
        int yPos = height - 69;
        
        this.drawTexturedModalRect(xPos, yPos, 84, 0, 172, 20);
        this.drawTexturedModalRect(xPos+6, yPos+12, 90, 20, tile.getMetreScale(160), 3);
        
        String s = tile.getLocalisedName();
        mc.fontRendererObj.drawString(s, xPos + 86 - (mc.fontRendererObj.getStringWidth(s) / 2), yPos+3, 0);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
	}
}
