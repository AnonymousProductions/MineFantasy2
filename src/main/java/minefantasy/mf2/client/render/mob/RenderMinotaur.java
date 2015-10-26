package minefantasy.mf2.client.render.mob;

import org.lwjgl.opengl.GL11;

import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.entity.mob.EntityDragon;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMinotaur extends RenderBiped
{
	public RenderMinotaur(ModelBiped model, float shadow)
    {
        super(model, shadow);
    }
    
    @Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
    	if(entity instanceof EntityDragon)
    	{
    		return TextureHelperMF.getResource("textures/models/monster/minotaur/"+((EntityDragon)entity).getTexture() + ".png");
    	}
		return TextureHelperMF.getResource("textures/models/monster/minotaur/minotaurBrown.png");
	}
    
    @Override
    public void doRender(EntityLiving entity, double x, double y, double z, float f, float f1)
    {
    	super.doRender(entity, x, y, z, f, 1);
    }
}
