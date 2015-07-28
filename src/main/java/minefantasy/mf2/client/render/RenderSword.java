package minefantasy.mf2.client.render;

import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.api.weapon.IParryable;
import minefantasy.mf2.item.weapon.ItemWeaponMF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fml.client.FMLClientHandler;

import org.lwjgl.opengl.GL11;

public class RenderSword implements IItemRenderer 
{
	private Minecraft mc;
    private RenderItem itemRenderer;

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
    {
    	boolean hasParried = false;
    	EntityLivingBase user = ((EntityLivingBase)data[1]);
    	ItemStack weapon = user.getHeldItem();
    	
    	if(user instanceof EntityPlayer && weapon != null)
    	{
    		hasParried = ItemWeaponMF.getParry(weapon) > 0;
    	}
    	else if(!(user instanceof EntityPlayer) && weapon != null && weapon.getItem() instanceof IParryable)
    	{
    		hasParried = user.hurtResistantTime > 0;
    	}
    	
        GL11.glPushMatrix();

        if (mc == null)
        {
            mc = FMLClientHandler.instance().getClient();
            itemRenderer = Minecraft.getMinecraft().getRenderItem();
        }
        this.mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
        Tessellator tessellator = Tessellator.getInstance();

        if (type == ItemRenderType.EQUIPPED) 
        {
        	if(hasParried)
        	{
        		if(user instanceof EntityPlayer)
        		{
        			GL11.glRotatef(-45, 0, 0, 1);
		        	GL11.glTranslatef(-0.5F, 0.5F, 0);
        		}
        		else
        		{
		        	GL11.glRotatef(-90, -1, 0, 0);
		        	GL11.glTranslatef(0F, 0F, -0.25F);
        		}
        	}
	        IIcon icon = item.getIconIndex();

            ItemRenderer.renderItemIn2D(tessellator,
            		icon.getMaxU(),
                    icon.getMinV(),
                    icon.getMinU(),
                    icon.getMaxV(),
                    icon.getIconWidth(),
                    icon.getIconHeight(), 1F/16F);
            if (item != null && item.hasEffect()) 
            {
            	TextureHelperMF.renderEnchantmentEffects(item);
            }

        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) 
        {
        	if(hasParried)
        	{
	        	GL11.glRotatef(-45, 0, 0, 1);
	        	GL11.glTranslatef(-0.5F, 0.5F, 0);
        	}
            IIcon icon = item.getIconIndex();

            ItemRenderer.renderItemIn2D(tessellator,
            		icon.getMaxU(),
                    icon.getMinV(),
                    icon.getMinU(),
                    icon.getMaxV(),
                    icon.getIconWidth(),
                    icon.getIconHeight(), 1F/16F);

            if (item != null && item.hasEffect()) {
               TextureHelperMF.renderEnchantmentEffects(item);
            }
        }

        GL11.glPopMatrix();
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
}