package minefantasy.mf2.item.weapon;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.api.weapon.WeaponClass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
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
public class ItemKatanaMF extends ItemHeavyWeapon
{
	private Random rand = new Random();
	/**
	 * Katanas are heavy counterparts to Tantos, unlike most heavy weapons: these act more like light-weapons
	 */
	private String Name;
    public ItemKatanaMF(String name, ToolMaterial material, int rarity, float weight)
    {
    	super(material, name, rarity, weight);
    	baseDamage /= 1.5F;
    	Name=name;
    }
    
    public String getName(){
    	return Name;
    }
    
	@Override
	public boolean canBlock() 
	{
		return true;
	}
	
	@Override
	public boolean allowOffhand(ItemStack mainhand, ItemStack offhand)
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
	 * gets the time after being hit your guard will be let down
	 */
	@Override
	public int getParryCooldown(EntityLivingBase user) 
	{
		return 8;
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
		return 1.5F;
	}
	@Override
	public boolean playCustomParrySound(EntityLivingBase blocker, Entity attacker, ItemStack weapon) 
	{
		blocker.worldObj.playSoundAtEntity(blocker, "mob.zombie.metal", 1.0F, 1.15F + (rand.nextFloat()*0.5F));
		return true;
	}
	
	@Override
	public float getBalance()
	{
		return 0.0F;
	}
	@Override
	public float getDecayModifier(EntityLivingBase user, ItemStack item)
	{
		return 0.75F;
	}

	@Override
	public float getRegenModifier(EntityLivingBase user, ItemStack item) 
	{
		return user.worldObj.getDifficulty().getDifficultyId() < 3 ? 1.25F : 1.0F;
	}
	
	@Override
	public void onProperHit(EntityLivingBase user, ItemStack weapon, Entity hit, float dam)
	{
		if(user.motionY < 0 && !user.onGround && (!(user instanceof EntityPlayer) || user.isSneaking()) && tryPerformAbility(user, cleave_cost))
		{
			hurtInRange(user, 4D);
			user.setSneaking(false);
		}
		super.onProperHit(user, weapon, hit, dam);
	}
	
	@Override
	public int modifyHitTime(EntityLivingBase user, ItemStack item)
	{
		if(!StaminaBar.isSystemActive || StaminaBar.isAnyStamina(user, false))
		{
			return speedModKatana;
		}
		return speedModHeavy/2;
	}
	@Override
	public float getParryStaminaDecay(DamageSource source, ItemStack weapon)
	{
		return 1.0F;
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
		return katanaStaminaCost;
	}
	@Override
	public WeaponClass getWeaponClass() 
	{
		return WeaponClass.BLADE;
	}
	@Override
	protected float[] getWeaponRatio(ItemStack implement)
	{
		return new float[]{1F, 0F};
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
