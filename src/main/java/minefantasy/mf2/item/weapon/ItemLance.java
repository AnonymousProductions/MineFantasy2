package minefantasy.mf2.item.weapon;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.helpers.TacticalManager;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import mods.battlegear2.api.PlayerEventChild.OffhandAttackEvent;
import mods.battlegear2.api.shield.IShield;
import mods.battlegear2.api.weapons.IExtendedReachWeapon;
import mods.battlegear2.api.weapons.IHitTimeModifier;
import mods.battlegear2.api.weapons.ISpecialEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * @author Anonymous Productions
 */
public class ItemLance extends ItemSpearMF
{
	private float joustDamage;
	/**
	 */
    public ItemLance(String name, ToolMaterial material, int rarity, float weight)
    {
    	super(name, material, rarity, weight);
    	setMaxDamage(getMaxDamage()*2);
    	joustDamage = baseDamage*2.5F;
    	baseDamage = 2.0F;
    }
    
    @Override
    public void addInformation(ItemStack weapon, EntityPlayer user, List list, boolean extra) 
    {
    	super.addInformation(weapon, user, list, extra);
    	
    	list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus."+ 0, decimal_format.format(joustDamage), StatCollector.translateToLocal("attribute.weapon.joustDam")));
    }

    @Override
	public boolean allowOffhand(ItemStack mainhand, ItemStack offhand) 
    {
		return offhand==null || offhand.getItem() instanceof IShield;
	}
    
	@Override
	public float getReachModifierInBlocks(ItemStack stack)
	{
		return 3.0F;
	}
	
	@Override
	public float modifyDamage(ItemStack item, EntityLivingBase wielder, Entity hit, float initialDam, boolean properHit)
	{
		float dam = super.modifyDamage(item, wielder, hit, initialDam, properHit);
		if(hit instanceof EntityLivingBase)
		{
			return joust((EntityLivingBase)hit, wielder, dam);
		}
		return dam;
	}
	
	@Override
	public boolean canWeaponParry()
	{
		return false;
	}
	//Higher stamina means more precice hits: Full stamina hits are perfect
	@Override
	public float getBalance(EntityLivingBase user)
	{
		if(StaminaBar.isSystemActive)
		{
			return 0.0F + (2*(1-StaminaBar.getStaminaDecimal(user)));
		}
		return 0.0F;
	}
	
	@Override
	protected float getKnockbackStrength() 
	{
		return 5.0F;
	}
	@Override
	protected float getStaminaMod() 
	{
		return 5.0F;
	}
	public boolean canBlock() 
	{
		return false;
	}
	
	public float joust(EntityLivingBase target, EntityLivingBase attacker, float dam)
	{
		float speedMod = 20F;
		float speedCap = 5F;
		
    	if(attacker.isRiding())
    	{
    		Entity mount = attacker.ridingEntity;
    		float speed = (float)Math.hypot(mount.motionX, mount.motionZ) * speedMod;
    		if(speed > speedCap)speed = speedCap;
    		
    		dam += joustDamage / speedCap * speed;
    		
            if(attacker instanceof EntityPlayer)
            {
            	((EntityPlayer) attacker).onCriticalHit(target);
            }
            
    		if(target.isRiding() && speed > (speedCap/2F))
    		{
    			target.dismountEntity(target.ridingEntity);
    			target.mountEntity(null);
    		}
    	}
    	return dam;
	}
	@Override
	public int modifyHitTime(EntityLivingBase user, ItemStack item)
	{
		return super.modifyHitTime(user, item) + speedModSpear*2;
	}
}
