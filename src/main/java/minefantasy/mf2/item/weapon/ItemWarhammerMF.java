package minefantasy.mf2.item.weapon;

import java.util.List;

import minefantasy.mf2.api.helpers.TacticalManager;
import minefantasy.mf2.api.weapon.WeaponClass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemWarhammerMF extends ItemHeavyWeapon
{
	private float stunChance = 0.20F;
    
	/**
	 * Warhammers are heavy counterparts to maces: They have more damage and knockback
	 * 
	 * They can also strike heavy forces, being rather satisfying
	 */
    public ItemWarhammerMF(String name, ToolMaterial material, int rarity, float weight)
    {
        super(material, name, rarity, weight);
		this.setMaxDamage((int) (getMaxDamage()*2F));
    }

	@Override
	protected int getParryDamage(float dam) 
	{
		return (int)(dam*2F);
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		return super.getDigSpeed(stack,state)*1.5F;
	}

	@Override
	public void onProperHit(EntityLivingBase user, ItemStack weapon, Entity hit, float dam)
	{
		if(!user.worldObj.isRemote && user.getRNG().nextInt(5) == 0)
		{
			hit.worldObj.createExplosion(user, hit.posX, hit.posY, hit.posZ, 0.0F, false);
			TacticalManager.knockbackEntity(hit, user, 2.0F, 1.5F);
			if(hit instanceof EntityLivingBase)
			{
				((EntityLivingBase)hit).addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 10));
				((EntityLivingBase)hit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 1));
			}
		}
		super.onProperHit(user, weapon, hit, dam);
	}
	@Override
	public boolean playCustomParrySound(EntityLivingBase blocker, Entity attacker, ItemStack weapon) 
	{
		blocker.worldObj.playSoundAtEntity(blocker, "minefantasy2:weapon.wood_parry", 1.0F, 0.75F);
		return true;
	}
	
	@Override
	protected float getKnockbackStrength() 
	{
		return 2.0F;
	}
	@Override
	protected float getStaminaMod() 
	{
		return heavyStaminaCost*maceStaminaCost;
	}
	@Override
	public int modifyHitTime(EntityLivingBase user, ItemStack item)
	{
		return super.modifyHitTime(user, item) + speedModMace;
	}
	
	@Override
	public float getDamageModifier()
	{
		return damageModMace;
	}
	
	@Override
	protected float[] getWeaponRatio(ItemStack implement)
	{
		return hammerRatio;
	}
	/**
	 * gets the time after being hit your guard will be let down
	 */
	@Override
	public int getParryCooldown(DamageSource source, float dam, ItemStack weapon) 
	{
		return maceParryTime + heavyParryTime;
	}
	@Override
	public int getParryModifier(ItemStack weapon, EntityLivingBase user, Entity target)
	{
		return 80;
	}

	@Override
	public WeaponClass getWeaponClass() 
	{
		return WeaponClass.BLUNT;
	}
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
