package minefantasy.mf2.item.archery;

import minefantasy.mf2.api.archery.IArrowHandler;
import minefantasy.mf2.api.archery.ISpecialBow;
import minefantasy.mf2.entity.EntityArrowMF;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * This class is an example used to fire custom arrows. 
 */
public class ArrowFirerMF implements IArrowHandler
{

	@Override
	public boolean onFireArrow(World world, ItemStack arrow, ItemStack bow, EntityPlayer user, float charge, boolean infinite) 
	{
		if(!(arrow.getItem() instanceof ItemArrowMF))
		{
			return false;
		}
		ItemArrowMF ammo = (ItemArrowMF)arrow.getItem();
		//TODO Arrow entity instance
        EntityArrowMF entArrow = ammo.getFiredArrow(new EntityArrowMF(world, user, charge*2.0F), arrow);

        int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, bow);
        entArrow.setPower(1+(0.25F*var9));

        int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, bow);

        if (var10 > 0)
        {
            entArrow.setKnockbackStrength(var10);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, bow) > 0)
        {
            entArrow.setFire(100);
        }

        if(infinite)
        {
        	entArrow.canBePickedUp = 2;
        }
        
        if(bow != null && bow.getItem() != null && bow.getItem() instanceof ISpecialBow)
        {
        	entArrow = (EntityArrowMF) ((ISpecialBow)bow.getItem()).modifyArrow(entArrow);
        }
        if (!world.isRemote)
        {
            world.spawnEntityInWorld(entArrow);
        }
        
		return true;
	}

}
