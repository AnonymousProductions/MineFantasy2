package minefantasy.mf2.item.weapon;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.api.weapon.WeaponClass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemSwordMF extends ItemWeaponMF
{
	private Random rand = new Random();
	/**
	 * Swords are the average weapon. They do regular damage and are extremely effective at parrying balancing both attack and defense
	 * 
	 * These are for the average player
	 */
	private String NAME;
    public ItemSwordMF(String name, ToolMaterial material, int rarity, float weight)
    {
    	super(material, name, rarity, weight);
    	NAME=name;
    }
    
    public String getName(){
    	return NAME;
    }
	
	@Override
	public boolean canBlock() 
	{
		return true;
	}
	
	/**
	 * Determines if the weapon can parry
	 */
	@Override
	public boolean canWeaponParry() 
	{
		return true;
	}
	
	@Override
	protected int getParryDamage(float dam) 
	{
		return 1;
	}
	/**
	 * Gets the angle the weapon can parry
	 */
	@Override
	public float getParryAngleModifier(EntityLivingBase user) 
	{
		return 1.0F;
	}
	/**
	 * Gets the multiplier for the parry threshold
	 * @return
	 */
	@Override
	public float getParryDamageModifier(EntityLivingBase user) 
	{
		return user instanceof EntityPlayer ? 2.0F : 1.5F;
	}
	/**
	 * Determines if the weapon can do those cool ninja evades
	 * @return
	 */
	@Override
	public boolean canWeaponEvade() 
	{
		return true;
	}
	
	@Override
	protected boolean canAnyMobParry() 
	{
		return true;
	}
	@Override
	public int modifyHitTime(EntityLivingBase user, ItemStack item)
	{
		return super.modifyHitTime(user, item) + speedModSword;
	}
	/**
	 * gets the time after being hit your guard will be let down
	 */
	@Override
	public int getParryCooldown(DamageSource source, float dam, ItemStack weapon) 
	{
		return swordParryTime;
	}
	@Override
	protected float getStaminaMod() 
	{
		return swordStaminaCost;
	}
	@Override
	public WeaponClass getWeaponClass() 
	{
		return WeaponClass.BLADE;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
