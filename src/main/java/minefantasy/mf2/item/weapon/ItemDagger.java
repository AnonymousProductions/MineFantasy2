package minefantasy.mf2.item.weapon;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.helpers.TacticalManager;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.api.weapon.IParryable;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import mods.battlegear2.api.PlayerEventChild.OffhandAttackEvent;
import mods.battlegear2.api.weapons.IHitTimeModifier;
import mods.battlegear2.api.weapons.ISpecialEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * @author Anonymous Productions
 */
public class ItemDagger extends ItemWeaponMF
{
	private Random rand = new Random();
	/**
	 * Daggers are fast and weak weapons, but are fast and light
	 * 
	 * These are for the fast player
	 */
    public ItemDagger(String name, ToolMaterial material, int rarity, float weight)
    {
    	super(material, name, rarity, weight);
    	baseDamage /= 2F;
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
		return 2;
	}
	/**
	 * Gets the angle the weapon can parry
	 */
	@Override
	public float getParryAngleModifier(EntityLivingBase user) 
	{
		return 0.75F;
	}
	/**
	 * Gets the multiplier for the parry threshold
	 * @return
	 */
	@Override
	public float getParryDamageModifier(EntityLivingBase user) 
	{
		return 0.5F;
	}
	@Override
	public boolean playCustomParrySound(EntityLivingBase blocker, Entity attacker, ItemStack weapon) 
	{
		blocker.worldObj.playSoundAtEntity(blocker, "mob.zombie.metal", 1.0F, 1.65F + (rand.nextFloat()*0.5F));
		return true;
	}
	
	@Override
	public float getDecayModifier(EntityLivingBase user, ItemStack item)
	{
		return 0.5F;
	}

	@Override
	public float getRegenModifier(EntityLivingBase user, ItemStack item) 
	{
		return user.worldObj.difficultySetting.getDifficultyId() < 3 ? 1.5F : 1.0F;
	}
	
	@Override
	public int modifyHitTime(EntityLivingBase user, ItemStack item)
	{
		if(!StaminaBar.isSystemActive || StaminaBar.isAnyStamina(user, false))
		{
			return speedModKatana;
		}
		return 0;
	}
	/**
	 * gets the time after being hit your guard will be let down
	 */
	@Override
	public int getParryCooldown(DamageSource source, float dam, ItemStack weapon) 
	{
		return daggerParryTime;
	}
	@Override
	protected float getStaminaMod() 
	{
		return daggerStaminaCost;
	}
}
