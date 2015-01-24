package minefantasy.mf2.client.render;

/**
 *
 * @author Anonymous Productions
 * 
 * Sources are provided for educational reasons.
 * though small bits of code, or methods can be used in your own creations.
 * 
 * Code based off Battlegear Spears by Nerd Boy.
 */
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderSpear implements IItemRenderer 
{
	private Minecraft mc;
    private RenderItem itemRenderer;
    private boolean isHalbeard;
    
    public RenderSpear()
    {
    	this(false);
    }
    public RenderSpear(boolean halbeard)
    {
    	isHalbeard = halbeard;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) 
    {
        return type.equals(ItemRenderType.EQUIPPED) || type.equals(ItemRenderType.EQUIPPED_FIRST_PERSON);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
    {
        GL11.glPushMatrix();

        if (mc == null)
        {
            mc = FMLClientHandler.instance().getClient();
            itemRenderer = new RenderItem();
        }
        this.mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
        Tessellator tessellator = Tessellator.instance;

        if (type == ItemRenderType.EQUIPPED)
        {
        	boolean rotate = getRotationFor(false, data);
        	GL11.glTranslatef(-1.0F, -1.0F, 0);
            GL11.glScalef(3,3,1);
            IIcon icon = item.getIconIndex();
            
            GL11.glPushMatrix();
            
            float x = icon.getMaxU();
            float x2 = icon.getMinU();
            
            if(rotate)
            {
            	x2 = icon.getMaxU();
                x = icon.getMinU();
            }
            
            RenderManager.instance.itemRenderer.renderItemIn2D(tessellator,
            		x,
                    icon.getMinV(),
                    x2,
                    icon.getMaxV(),
                    icon.getIconWidth(),
                    icon.getIconHeight(), 1F/16F);

            if (item != null && item.hasEffect(0)) 
            {
            	TextureHelperMF.renderEnchantmentEffects(tessellator);
            }
            
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) 
        {
        	boolean rotate = getRotationFor(true, data);
        	GL11.glTranslatef(-1.0F, -1.0F, 0);
            GL11.glScalef(3,3,1);
            IIcon icon = item.getIconIndex();

            float x = icon.getMaxU();
            float x2 = icon.getMinU();
            
            if(rotate)
            {
            	x2 = icon.getMaxU();
                x = icon.getMinU();
            }
            
            RenderManager.instance.itemRenderer.renderItemIn2D(tessellator,
            		x,
                    icon.getMinV(),
                    x2,
                    icon.getMaxV(),
                    icon.getIconWidth(),
                    icon.getIconHeight(), 1F/16F);

            if (item != null && item.hasEffect(0)) {
               TextureHelperMF.renderEnchantmentEffects(tessellator);
            }
        }

        GL11.glPopMatrix();
    }
	private boolean getRotationFor(boolean FP, Object... data)
	{
		boolean charge = false;
    	for(int a = 0; a < data.length; a ++)
    	{
    		if(data[a] instanceof EntityLivingBase)
    		{
    			if(((EntityLivingBase)data[a]).isSwingInProgress && !isHalbeard)
    			{
    				return true;
    			}
    			if(((EntityLivingBase)data[a]).isSprinting() && FP)
    			{
    				charge = true;
    			}
    		}
    		if(data[a] instanceof EntityPlayer)
    		{
    			EntityPlayer player = (EntityPlayer)data[a];
    			if(player.isUsingItem())
    			{
    				charge = false;
    			}
    		}
    	}
		return charge;
	}
}