package minefantasy.mf2.item.weapon;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemHalbeardMF extends ItemSpearMF
{
	/**
	 * The halbeard is the heavy counterpart for the spear: It has increased damage, knockback distance and parry arc
	 * 
	 * Halbeards use a swinging attack rather than a stab, but will still stab when sprinting
	 */
	private String Name;
    public ItemHalbeardMF(String name, ToolMaterial material, int rarity, float weight)
    {
    	super(name, material, rarity, weight);
    	setMaxDamage(getMaxDamage()*2);
    	baseDamage *= 1.5F;
    	Name = name;
    }
    
    public String getName(){
    	return Name;
    }

    @Override
	public boolean allowOffhand(ItemStack mainhand, ItemStack offhand) 
    {
		return false;
	}
    
	@Override
	public float getReachModifierInBlocks(ItemStack stack)
	{
		return 3.0F;
	}
	
	@Override
	public boolean playCustomParrySound(EntityLivingBase blocker, Entity attacker, ItemStack weapon) 
	{
		blocker.worldObj.playSoundAtEntity(blocker, "minefantasy2:weapon.wood_parry", 1.0F, 0.7F);
		return true;
	}
	
	@Override
	protected int getParryDamage(float dam) 
	{
		return (int)dam;
	}
	/**
	 * Gets the angle the weapon can parry
	 */
	@Override
	public float getParryAngleModifier(EntityLivingBase user) 
	{
		return user.isSneaking() ? 1.5F: 1.0F;
	}
	@Override
	public float getBalance()
	{
		return 0.8F;
	}
	
	@Override
	protected float getKnockbackStrength() 
	{
		return 3.5F;
	}
	@Override
	public int modifyHitTime(EntityLivingBase user, ItemStack item)
	{
		return super.modifyHitTime(user, item) + speedModSpear;
	}
	/**
	 * gets the time after being hit your guard will be let down
	 */
	@Override
	public int getParryCooldown(DamageSource source, float dam, ItemStack weapon) 
	{
		return spearParryTime + heavyParryTime;
	}
	
	@Override
	protected float getStaminaMod() 
	{
		return heavyStaminaCost*spearStaminaCost;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
