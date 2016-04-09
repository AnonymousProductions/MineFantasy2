package minefantasy.mf2.mechanics;

import java.util.Map;
import java.util.Random;

import minefantasy.mf2.api.armour.CogworkArmour;
import minefantasy.mf2.api.armour.IElementalResistance;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.helpers.ArrowEffectsMF;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.api.helpers.TacticalManager;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.weapon.IDamageModifier;
import minefantasy.mf2.api.weapon.IKnockbackWeapon;
import minefantasy.mf2.api.weapon.IParryable;
import minefantasy.mf2.api.weapon.IPowerAttack;
import minefantasy.mf2.api.weapon.ISpecialEffect;
import minefantasy.mf2.api.weapon.IWeaponSpeed;
import minefantasy.mf2.api.weapon.IWeightedWeapon;
import minefantasy.mf2.config.ConfigArmour;
import minefantasy.mf2.config.ConfigExperiment;
import minefantasy.mf2.config.ConfigStamina;
import minefantasy.mf2.config.ConfigWeapon;
import minefantasy.mf2.entity.Shockwave;
import minefantasy.mf2.item.weapon.ItemBattleaxeMF;
import minefantasy.mf2.item.weapon.ItemDagger;
import minefantasy.mf2.item.weapon.ItemKatanaMF;
import minefantasy.mf2.item.weapon.ItemWaraxeMF;
import minefantasy.mf2.item.weapon.ItemWeaponMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import minefantasy.mf2.network.packet.ParryPacket;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CombatMechanics
{
	public static final String parryCooldownNBT = "MF_Parry_Cooldown";
	public static final String posthitCooldownNBT = "MF_PostHit";
	private static Random rand = new Random();
	public static final float	specialUndeadModifier	= 2.0F;
	public static final float	specialDragonModifier	= 0.5F;
	public static final float	specialWerewolfModifier	= 8.0F;
	public static boolean	swordSkeleton	= true;
	
	protected float jumpEvade_cost = 30;
	protected float evade_cost = 10;
	
	@SubscribeEvent
	public void initAttack(LivingAttackEvent event)
	{
		/*
		if(event.source == DamageSource.fall && event.entityLiving instanceof EntityPlayer && RPGElements.isSystemActive)
		{
			SkillList.acrobatics.addXP((EntityPlayer) event.entityLiving, (int)event.ammount+1);
		}
		
		if(RPGElements.isSystemActive && event.entityLiving instanceof EntityPlayer && !event.source.isUnblockable())
		{
			for(int a = 0; a < 4; a++)
			{
				ItemStack piece = event.entityLiving.getEquipmentInSlot(a+1);
				if(piece != null)
				{
					int AC = ArmourCalculator.getArmourClass(piece).equalsIgnoreCase("heavy") ? 2 : ArmourCalculator.getArmourClass(piece).equalsIgnoreCase("light") ? 0 : 1;
					
					Skill skill = (AC == 2) ? SkillList.heavyarmour : (AC == 0) ? SkillList.lightarmour : SkillList.mediumarmour;
					skill.addXP((EntityPlayer) event.entityLiving, (int)(event.ammount/5F));
				}
			}
		}
		*/
		
		EntityLivingBase hitter = getHitter(event.source);
		int spd = EventManagerMF.getHitspeedTime(hitter);
		if(hitter != null && !hitter.worldObj.isRemote)
		{
			if(spd > 0 && !(event.entityLiving instanceof EntityPlayer))
			{	event.setCanceled(true);
				return;
			}
		}
		DamageSource src = event.source;
		EntityLivingBase hit = event.entityLiving;
		World world = hit.worldObj;
		float damage = modifyDamage(src, world, hit, event.ammount, false);
		
		if(event.source.isProjectile() && !event.source.isFireDamage() && damage < event.ammount && hit.getTotalArmorValue() > 0)//only if dam has been reduced
    	{
    		if(ConfigArmour.resistArrow && !event.isCanceled() && damage <= 0)//TacticalManager.resistArrow(event.entityLiving, event.source, damage))
    		{
    			if(event.source.getSourceOfDamage() != null && !event.source.getSourceOfDamage().getEntityData().hasKey("arrowDeflectMF") && !(event.source.getEntity() instanceof EntityEnderPearl))
    			{
    				event.source.getSourceOfDamage().getEntityData().setBoolean("arrowDeflectMF", true);
    				event.entityLiving.worldObj.playSoundAtEntity(event.entityLiving, "random.break", 1.0F, 0.5F);
    			}
    			event.setCanceled(true);
    		}
    	}
		
		if(damage <= 0)
		{
			event.setCanceled(true);
		}
		if(hitter != null && hitter instanceof EntityLivingBase)
		{
			int hitTime = 5;
			if(hitter.getHeldItem() != null)
			{
				ItemStack weapon = hitter.getHeldItem();
				if(weapon.getItem() instanceof IWeaponSpeed)
				{
					hitTime += ((IWeaponSpeed)weapon.getItem()).modifyHitTime(hitter, weapon);
				}
			}
			if(hitTime > 0)
			EventManagerMF.setHitTime(hitter, hitTime);
		}
	}

	/**
	 * gets the melee hitter
	 */
	private EntityLivingBase getHitter(DamageSource source)
	{
		if(source != null && source.getEntity() != null && source.getEntity() == source.getSourceOfDamage() && source.getEntity() instanceof EntityLivingBase)
		{
			return (EntityLivingBase) source.getEntity();
		}
		return null;
	}

	@SubscribeEvent
    public void onHit(LivingHurtEvent event)
    {
		DamageSource src = event.source;
		EntityLivingBase hit = event.entityLiving;
		if(src != null && src == DamageSource.fall)
		{
			onFall(hit, event.ammount);
		}
		World world = hit.worldObj;
		float damage = modifyDamage(src, world, hit, event.ammount, true);
		
		if(damage > 0 && hit.isSprinting())
		{
			hit.setSprinting(false);
		}
		//TODO: Zombie armour
    	if(event.entityLiving.getEntityData().hasKey(MonsterUpgrader.zombieArmourNBT) && event.entityLiving instanceof EntityZombie)
    	{
    		float percent = 1.0F;
    		ItemStack[] armours = new ItemStack[4];
    		for(int a = 1; a < 5; a ++)
    		{
    			armours[a-1]=event.entityLiving.getEquipmentInSlot(a);
    		}
    		damage = ISpecialArmor.ArmorProperties.ApplyArmor(event.entityLiving, armours, event.source, damage);
    	}
    	//TODO: Stick arrows (EXPERIMENTAL)
    	if(ConfigExperiment.stickArrows && event.source.getSourceOfDamage() != null && event.source.getSourceOfDamage() instanceof EntityArrow)
    	{
    		if(!event.entity.worldObj.isRemote)
    		{
    			ArrowEffectsMF.stickArrowIn(event.entity, ArrowEffectsMF.getDroppedArrow(event.source.getSourceOfDamage()), event.source.getSourceOfDamage());
    		}
    	}
    	if(damage > 0)
    	{
    		onOfficialHit(src, hit, damage);
    	
	    	if (event.source instanceof EntityDamageSource && !(event.source instanceof EntityDamageSourceIndirect)  && !event.source.damageType.equals("battlegearExtra")) 
			{
				Entity entityHitter = ((EntityDamageSource) event.source).getEntity();
				
				if (entityHitter instanceof EntityLivingBase) 
				{
					EntityLivingBase attacker = (EntityLivingBase) entityHitter;
					StaminaMechanics.onAttack(attacker, hit);
					ItemStack weapon = attacker.getHeldItem();
					HitSoundGenerator.makeHitSound(weapon, event.entityLiving);
				}
			}
    	}
		event.ammount = damage;
    }
	
	private void onFall(EntityLivingBase fallen, float height) 
	{
		float weight = ArmourCalculator.getTotalWeightOfWorn(fallen, false);
		if(weight > 100)
		{
			weight -= 100F;
			float power = (height/4F) * (weight/100F);
			newShockwave(fallen, fallen.posX, fallen.posY, fallen.posZ, power, false, true);
		}
	}
	public Shockwave newShockwave(Entity source, double x, double y, double z, float power, boolean fire, boolean smoke)
    {
    	Shockwave explosion = new Shockwave("humanstomp", source.worldObj, source, x, y, z, power);
        explosion.isFlaming = fire;
        explosion.isSmoking = smoke;
        explosion.initiate();
        explosion.decorateWave(true);
        return explosion;
    }

	private float modifyDamage(DamageSource src, World world, EntityLivingBase hit, float dam, boolean properHit)
	{
		Entity source = src.getSourceOfDamage();
		Entity hitter = src.getEntity();
		
		if(properHit && hit instanceof EntityPlayer)
		{
			dam = modifyPlayerDamage((EntityPlayer)hit, dam);
		}
		
		if(source != null && hitter != null && hitter instanceof EntityLivingBase)
		{
			dam = modifyUserHitDamage(dam, (EntityLivingBase)hitter, source, hitter == source, hit, properHit);
		}
		if(src.isExplosion() && isSkeleton(hit))
		{
			dam *= 5F;
		}
		
		//TODO: Elemental resistance
    	dam *= TacticalManager.getResistance(hit, src);
    	if(src.isFireDamage())
    	{
    		if(dam <= 0.0F)
    		{
    			hit.extinguish();
    		}
    	}
    	dam = onUserHit(hit, hitter, src, dam, properHit);
		return dam;
	}

	private boolean isSkeleton(Entity target) 
	{
		return target instanceof EntitySkeleton;
	}

	//TODO: damage modifier
	private float modifyUserHitDamage(float dam, EntityLivingBase user, Entity source, boolean melee, Entity target, boolean properHit)
	{
		dam = modifyMobDamage(user, dam);
		//Power Attack
		if(melee)
		{
			int powerAttack = initPowerAttack(user, target, properHit);
			if(powerAttack == 1)
			{
				dam *= (2F/1.5F);
				onPowerAttack(dam, user, target, properHit);
				
				if(isSkeleton(target))
				{
					dam *= 1.5F;
					if(properHit)
					{
						if(rand.nextInt(2) == 0)
						{
							target.entityDropItem(new ItemStack(Items.bone), 0.5F);
						}
					}
				}
			}
			if(powerAttack == -1)
			{
				dam /= 2F;
			}
			/*
			if(RPGElements.isSystemActive && user instanceof EntityPlayer)
			{
				WeaponClass WC = WeaponClass.findClassForAny(user.getHeldItem());
				if(WC != null)
				{
					float mod = RPGElements.getWeaponModifier((EntityPlayer) user, WC.parentSkill);
					MFLogUtil.logDebug("Weapon Class: " + WC.name + ", mod = " + mod);
					dam *= mod;
				}
			}
			*/
		}
		if(user instanceof EntityLivingBase)
		{
			EntityLivingBase player = user;
			
			//TODO: Stamina Traits
			if(StaminaBar.isSystemActive)
			{
				if(StaminaBar.getStaminaValue(player) <= 0)
				{
					dam *= ConfigStamina.weaponDrain;
				}
			}
		}
		if(user.hurtResistantTime > 12)
		{
			dam *= 0.5F;
		}
		
		ItemStack weapon = user.getHeldItem();
    	if(weapon != null)
    	{
    		if(weapon.getItem() instanceof IDamageModifier)
    		{
    			//TODO: IDamageModifier, this mods the damage for weapons
    			dam = ((IDamageModifier)weapon.getItem()).modifyDamage(weapon, user, target, dam, properHit);
    		}
    		CustomMaterial material = CustomToolHelper.getCustomMetalMaterial(weapon);
    		if(material != null && material.name.equalsIgnoreCase("silver"))
    		{
    			dam = hurtUndead(user, target, dam, properHit);
    		}
    		if(!weapon.getUnlocalizedName().contains("dragonforged") && target instanceof EntityLivingBase && TacticalManager.isDragon((EntityLivingBase)target))
    		{
    			dam *= specialDragonModifier;
    		}
    	}
    	
		return dam;
	}
	
	private void onPowerAttack(float dam, EntityLivingBase user, Entity target, boolean properHit)
	{
		ItemStack weapon = user.getHeldItem();
		int ticks = 20;
		if(weapon != null && weapon.getItem() instanceof IPowerAttack)
		{
			((IPowerAttack)weapon.getItem()).onPowerAttack(dam, user, target, properHit);
			ticks = ((IPowerAttack)weapon.getItem()).getParryModifier(weapon, user, target);
		}
	   if(target instanceof EntityLivingBase)
	   {
	 	   if(ticks > getParryCooldown((EntityLivingBase)target))
	 	   {
	 		   setParryCooldown((EntityLivingBase)target, ticks);
	 	   }
	   }
	   if(!user.worldObj.isRemote)
	   {
		   user.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 20, 5));
		   user.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 10));
	   }
	   if(user instanceof EntityPlayer)
	   {
		   TacticalManager.lungeEntity(user, target, 0.5F, 0F);
		   TacticalManager.throwPlayerOffBalance((EntityPlayer) user, 0.5F, true);
	   }
	   
	   target.worldObj.playSoundAtEntity(target, "minefantasy2:weapon.critical", 1.0F, 1.0F);
	}

	private static final float power_attack_base = 25F;
	/**
	 * 0 = false
	 * 1 = true
	 * -1 = failure
	 */
	private static int initPowerAttack(EntityLivingBase user, Entity target, boolean properHit)
	{
		if(!canExecutePower(user))
		{
			return 0;
		}
		if(StaminaBar.isSystemActive && StaminaBar.doesAffectEntity(user))
		{
			float points = power_attack_base * (StaminaBar.getBaseDecayModifier(user, true, true)*0.5F + 0.5F);
			if(StaminaBar.isStaminaAvailable(user, points, properHit))
			{
				if(properHit)
				{
					ItemWeaponMF.applyFatigue(user, points);
				}
				return getPostHitCooldown(user) > 0 ? -1 : 1;
			}
			else
			{
				return 0;
			}
		}
		return 1;
	}

	private static boolean canExecutePower(EntityLivingBase user)
	{
		if(user.isInWater())
		{
			return false;
		}
		if(user instanceof EntityPlayer)
		{
			if(!isFightStance(user))return false;
		}
		return user.fallDistance > 0 && !user.isOnLadder();
	}

	private static boolean isFightStance(EntityLivingBase user) 
	{
		return user.isSneaking();
	}

	private float modifyMobDamage(EntityLivingBase user, float dam)
	{
		if(user instanceof EntityZombie && user.isChild())
		{
			dam *= 0.65F;
		}
		return dam + getStrengthEnhancement(user);
	}

	private boolean isMaterialUndeadKiller(ItemStack weapon, ToolMaterial material) 
    {
		CustomMaterial mat = CustomToolHelper.getCustomMetalMaterial(weapon);
		if(mat != null && mat.name.equalsIgnoreCase("silver"))
		{
			return true;
		}
		return material == BaseMaterialMF.silver.getToolConversion() || material == BaseMaterialMF.ornate.getToolConversion();
	}
	private float hurtUndead(Entity entityHitting, Entity entityHit, float dam, boolean properHit) 
    {
    	if(entityHit instanceof EntityLivingBase && TacticalManager.isUnholyCreature((EntityLivingBase)entityHit))
		{
    		EntityLivingBase living = (EntityLivingBase)entityHit;
    		dam *= specialUndeadModifier;
    		if(properHit)
			{
	    		entityHit.playSound("random.fizz", 0.5F, 0.5F);
	    		living.addPotionEffect(new PotionEffect(Potion.weakness.id, 1200, 2));
	    		if(rand.nextInt(10) == 0)
	    		{
	    			living.setFire(3);
	    		}
	    		if(living.getHealth() <= living.getHealth()/2F && rand.nextInt(20) == 0)
	    		{
	    			dam *= 100F;
	    			living.worldObj.createExplosion(living, living.posX, living.posY+living.getEyeHeight(), living.posZ, 0.0F, false);
	    		}
			}
		}
		if(entityHit.getClass().getName().contains("Werewolf"))
		{
			dam *= specialWerewolfModifier;
			
			if(properHit)
			{
	    		entityHit.playSound("random.fizz", 0.6F, 0.5F);
			}
		}
    	return dam;
	}
	
	private void onOfficialHit(DamageSource src, EntityLivingBase target, float damage)
	{
		Entity source = src.getSourceOfDamage();
		Entity hitter = src.getEntity();
		
		if(source != null && hitter != null && hitter instanceof EntityLivingBase)
		{
			if(source == hitter)
			{
				EntityLivingBase user = (EntityLivingBase)hitter;
				ItemStack weapon = user.getHeldItem();
				if(weapon != null)
				{
					onWeaponHit(user, weapon, target, damage);
				}
				if(RPGElements.isSystemActive && user instanceof EntityPlayer )
				{
					SkillList.combat.addXP((EntityPlayer)user, (int)(damage/5F));
				}
			}
			else
			{
				if(source instanceof EntityArrow && src.isProjectile())
				{
					onArrowHit(source, target, hitter, damage);
				}
			}
		}
		CombatMechanics.setPostHitCooldown(target, 10);
	}
	private void onWeaponHit(EntityLivingBase user, ItemStack weapon, Entity target, float dam)
	{
		if(target instanceof EntityLivingBase && weapon.getItem() instanceof ISpecialEffect)
		{
			((ISpecialEffect)weapon.getItem()).onProperHit(user, weapon, target, dam);
		}
		
		if(weapon.getItem() instanceof IWeaponSpeed)
		{
			target.hurtResistantTime += ((IWeaponSpeed)weapon.getItem()).modifyHitTime(user, weapon);
		}
		if(weapon.getItem() instanceof IKnockbackWeapon)
		{
			float kb = ((IKnockbackWeapon)weapon.getItem()).getAddedKnockback(user, weapon);
			
			if(kb > 0)
			{
				TacticalManager.knockbackEntity(target, user, kb, 0F);
			}
		}
		if (ConfigWeapon.useBalance && user instanceof EntityPlayer)
        {
			applyBalance((EntityPlayer)user);
        }
	}
	private void onArrowHit(Entity arrow, Entity target, Entity shooter, float damage)
	{
		/*
		if(RPGElements.isSystemActive && shooter instanceof EntityPlayer)
		{
			SkillList.archery.addXP((EntityPlayer) shooter, (int)damage);
		}
		*/
	}

	protected static void performEffects(Map<PotionEffect, Float> map, EntityLivingBase entityHit) 
    {
        double roll = Math.random();
        if(map == null || map.isEmpty())
        {
        	return;
        }
        
        for(PotionEffect effect:map.keySet())
        {
            //add effects if they aren't already applied, with corresponding chance factor
            if(!entityHit.isPotionActive(effect.getPotionID()) && map.get(effect) > roll)
            {
                entityHit.addPotionEffect(new PotionEffect(effect));
            }
        }
    }
	
	private static boolean debugParry = true;
	private static final float	parryFatigue	= 5F;
	private float onUserHit(EntityLivingBase user, Entity entityHitting, DamageSource source, float dam, boolean properHit) 
    {
    	ItemStack weapon = user.getHeldItem();
    	if((properHit || source.isProjectile()) && weapon != null && !source.isUnblockable() &&!source.isExplosion())
    	{
    		float threshold = 10;//DEFAULT PARRY THRESHOLD
    		float weaponFatigue = 2.0F;//DEFAULT FATIGUE
    		int ticks = 18;//DEFAULT TICKS
    		IParryable parry = null;
    		
    		if(weapon.getItem() instanceof IParryable)
    		{
    			parry = (IParryable)weapon.getItem();
    			
    			ticks = parry.getParryCooldown(source, dam, weapon);
    			threshold = parry.getMaxDamageParry(user, weapon);
    			weaponFatigue = parry.getParryStaminaDecay(source, weapon);
    		}
    		if(StaminaBar.isSystemActive && !StaminaBar.isAnyStamina(user, false))
    		{
    			threshold /= 2;
    		}
    	    threshold *= TacticalManager.getHighgroundModifier(user, entityHitting, 1.15F);
    	    
    	    if(ArmourCalculator.advancedDamageTypes && !user.worldObj.isRemote)
    		{
    	    	threshold = ArmourCalculator.adjustACForDamage(source, threshold, 1.0F, 0.75F, 0.5F);
    		}
    	    
    	    if(debugParry && !user.worldObj.isRemote){MFLogUtil.logDebug("Init Parry: Damage = " + dam + " Threshold = " + threshold);}
    		
    	    //USED FOR PARRYING its harder to block arrows
           if(TacticalManager.canParry(source, user, entityHitting, weapon))
           {
        	   float previousDam = dam;
        	   dam = Math.max(0F, dam - threshold);
        	   
        	   if(debugParry && !user.worldObj.isRemote){MFLogUtil.logDebug("Parried: dam = " + dam);}
        	   
        	   if(properHit || dam <= 0)
        	   {
	        	   user.hurtResistantTime = user.maxHurtResistantTime;
	        	   user.hurtTime = 0;
	       		   
        		   int result = onParry(source, user, entityHitting, dam, previousDam, parry);
	        	   
        		   if(result == 1)
        		   {
        			   dam = 0;
        		   }
        		   
        		   if(StaminaBar.isSystemActive && StaminaBar.doesAffectEntity(user) && !StaminaBar.isAnyStamina(user, false))
	        	   {
	        		   ticks *= 3;
	        	   }
	        	   if(ticks > getParryCooldown(user))
	        	   {
	        		   setParryCooldown(user, ticks);
	        	   }
	        	   
	        	   ItemWeaponMF.applyFatigue(user, TacticalManager.getHighgroundModifier(user, entityHitting, 2.0F)*(dam+1F)*parryFatigue*weaponFatigue);
	        	   if(parry == null)
		    	   {
		    		   user.worldObj.playSoundAtEntity(user, getDefaultParrySound(weapon), 1.0F, 1.25F + (rand.nextFloat()*0.5F));
		    	   }
	        	   else if(!parry.playCustomParrySound(user, entityHitting, weapon))
		    	   {
		    		   user.worldObj.playSoundAtEntity(user, "mob.zombie.metal", 1.0F, 1.25F + (rand.nextFloat()*0.5F));
		    	   }
		    	   if(user instanceof EntityPlayer)
		    	   {
		   			   ((EntityPlayer)user).stopUsingItem();
		    		   ItemWeaponMF.setParry(weapon, 20);
		    	   }
		    	   
		    	   	if(entityHitting != null && entityHitting instanceof EntityLivingBase)
			   		{
		    	   		EntityLivingBase hitter = (EntityLivingBase)entityHitting;
			   			int hitTime = 5;
			   			if(hitter.getHeldItem() != null)
			   			{
			   				ItemStack attackingWep = hitter.getHeldItem();
			   				if(attackingWep.getItem() instanceof IWeaponSpeed)
			   				{
			   					hitTime += ((IWeaponSpeed)attackingWep.getItem()).modifyHitTime(hitter, weapon);
			   				}
			   			}
			   			if(hitTime > 0)
			   			{
			   				MFLogUtil.logDebug("Recoil hitter: " + hitter.getCommandSenderName() + " for " + hitTime*3 + " ticks.");
			   				EventManagerMF.setHitTime(hitter, hitTime * 3);
			   			}
			   		}
        	   }
           }
    	}
    	if(StaminaBar.isSystemActive && StaminaBar.doesAffectEntity(user) && !StaminaBar.isAnyStamina(user, false))
    	{
    		dam *= Math.max(1.0F, ConfigStamina.exhaustDamage);
    	}
    	
    	//Fire dura degrade
    	if(properHit && source.isFireDamage() && dam > 0)
    	{
    		for(int a = 0; a < 4; a++)
			{
				ItemStack armour = user.getEquipmentInSlot(a+1);
				if(armour != null)
				{
					int dura = (int)(dam)+1;
					if(!user.worldObj.isRemote && !isArmourFireImmune(armour, source))
					{
						MFLogUtil.logDebug("Armour Flame Damage: " + dura);
						if(armour.getItemDamage() + dura < armour.getMaxDamage())
						{
							armour.damageItem(dura, user);
						}
						else
						{
							armour.setItemDamage(armour.getMaxDamage());
						}
					}
					if(armour.getItemDamage() >= armour.getMaxDamage())
					{
						user.setCurrentItemOrArmor(a+1, null);
						user.worldObj.playSoundEffect(user.posX, user.posY+user.getEyeHeight() - (0.4F*a), user.posZ, "random.break", 1.0F, 1.0F);
					}
				}
			}
    	}
    	
    	
    	if(!source.isUnblockable() && (properHit || source.isProjectile()) && dam > 0 && ArmourCalculator.useThresholdSystem)//THRESHOLD SYSTEM
    	{
	    	float AC = ArmourCalculator.getACThreshold(user, source);
	    	if(source.isFireDamage())
	    	{
	    		AC *= 0.5F;
	    	}
	    	if(AC > 0)
	    	{
	    		if(dam > 0)
	    		{
	    			for(int a = 0; a < 4; a++)
	    			{
	    				ItemStack armour = user.getEquipmentInSlot(a+1);
	    				if(armour != null)
	    				{
	    					int dura = ArmourCalculator.getDamageToDura(user, source, armour, dam);
	    					if(!user.worldObj.isRemote)
	    					{
	    						if(armour.getItemDamage() + dura < armour.getMaxDamage())
	    						{
	    							armour.damageItem(dura, user);
	    						}
	    						else
	    						{
	    							armour.setItemDamage(armour.getMaxDamage());
	    						}
	    					}
	    					if(armour.getItemDamage() >= armour.getMaxDamage())
	    					{
	    						user.setCurrentItemOrArmor(a+1, null);
	    						user.worldObj.playSoundEffect(user.posX, user.posY+user.getEyeHeight() - (0.4F*a), user.posZ, "random.break", 1.0F, 1.0F);
	    					}
	    				}
	    			}
	    		}
	    		
	    		MFLogUtil.logDebug("Init AC Reduction: " + AC);
	    		dam -= AC;
	    		if(dam < 0)dam = 0;
	    	}
    	}
    	
    	if(dam > 0 && user instanceof EntityPlayer || (entityHitting != null && entityHitting instanceof EntityPlayer))
    	{
    		if(!user.worldObj.isRemote)
    		{
    			String type = "Mixed";
    			float[] f = ArmourCalculator.getRatioForSource(source);
    			if(f == null)
    			{
    				type = "Basic";
    			}
    			else
    			{
	    			if(f[0] > f[1] && f[0] > f[2])type = "Cutting";
	    			if(f[2] > f[1] && f[2] > f[0])type = "Piercing";
	    			if(f[1] > f[0] && f[1] > f[2])type = "Blunt";
    			}
    				
    			MFLogUtil.logDebug(dam + "x "+ type +" Damage inflicted to: " + user.getCommandSenderName() + " (" + user.getEntityId() + ")");
    		}
    	}
		return dam;
	}
	private boolean isArmourFireImmune(ItemStack armour, DamageSource src)
	{
		if(armour != null && armour.getItem() instanceof IElementalResistance)
		{
			return ((IElementalResistance)armour.getItem()).getFireResistance(armour, src) >= 100F;
		}
		return false;
	}

	private String getDefaultParrySound(ItemStack weapon)
	{
		if(weapon.getUnlocalizedName().contains("wood") || weapon.getUnlocalizedName().contains("Wood") || weapon.getUnlocalizedName().contains("stone") || weapon.getUnlocalizedName().contains("Stone"))
		{
			return "minefantasy2:weapon.wood_parry";
		}
		return "mob.zombie.metal";
	}

	/**
	 * @return 0 for normal parry and 1 for evade
	 */
	private int onParry(DamageSource source, EntityLivingBase user, Entity attacker, float dam, float prevDam, IParryable parry)
	{
		/*
		if(RPGElements.isSystemActive && user instanceof EntityPlayer)
		{
			SkillList.block.addXP((EntityPlayer)user, 10 + (int)prevDam*2);
		}
		*/
		if(RPGElements.isSystemActive && user instanceof EntityPlayer )
		{
			SkillList.combat.addXP((EntityPlayer)user, (int)(prevDam/3F));
		}
		if(parry != null)
		{
			parry.onParry(source, user, attacker, dam);
		}
		
		boolean groundBlock = user.onGround;
		ItemStack weapon = user.getHeldItem();
		
		//Redirect
		if(!user.worldObj.isRemote && !TacticalManager.isRanged(source))
		{
			if(canEvade(user))
			{
				float powerMod = attacker.isSprinting() ? 4.0F : 2.5F;
				
				attacker.setSprinting(false);
				TacticalManager.lungeEntity(attacker, user, powerMod, 0.0F);
				TacticalManager.lungeEntity(user, attacker, 3F, 0.0F);
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * Determines if an evade can be made (jump or normal)
	 */
	private boolean canEvade(EntityLivingBase user)
	{
		float stamModifier = 1.0F;
		if(user instanceof EntityPlayer)
		{
			if(!ResearchLogic.hasInfoUnlocked((EntityPlayer)user, "parrypro"))
			{
				return false;
			}

			if(!isFightStance(user))
			{
				return false;
			}
		}
		else
		{
			if(rand.nextInt(10) != 0)//Mobs can evade
			{
				return false;
			}
		}
		
		if(!user.onGround && !tryJumpEvade(user, stamModifier))
		{
			return false;
		}
		return tryGroundEvade(user, stamModifier);
	}
	/**
	 * If the player can slip past enemies
	 * Should be any armour but heavy
	 */
	private boolean tryGroundEvade(EntityLivingBase user, float cost) 
	{
		return ItemWeaponMF.tryPerformAbility(user, evade_cost*cost, true, false);
	}

	/**
	 * If the player can jump over enemies in evading
	 * Only ment for unarmoured/Lightarmour
	 */
	private boolean tryJumpEvade(EntityLivingBase user, float cost) 
	{
		return ItemWeaponMF.tryPerformAbility(user, jumpEvade_cost*cost, true, false);
	}

	@SubscribeEvent
	public void updateLiving(LivingUpdateEvent event)
	{
		EntityLivingBase living = event.entityLiving;
		
		tickParryCooldown(living);
		tickPostHitCooldown(living);
		if(living instanceof EntityLiving)
		{
			EntityLiving mob = (EntityLiving)living;
			ItemStack held = mob.getHeldItem();
			
			{
				EntityLivingBase tar = mob.getAttackTarget();
				
				if(tar != null && tar instanceof EntityPlayer && ((EntityPlayer)tar).isBlocking())
				{
					double dist = mob.getDistanceSqToEntity(tar);
					
					if(tar instanceof EntityZombie && mob.onGround && mob.getRNG().nextInt(10) == 0 &&  dist > 1D && dist < 4D)
					{
						mob.motionY = 0.5F;
					}
				}
			}
			if(isAxe(held))
			{
				EntityLivingBase tar = mob.getAttackTarget();
				
				if(tar != null)
				{
					double dist = mob.getDistanceSqToEntity(tar);
					
					if(mob.onGround && mob.getRNG().nextInt(5) == 0 &&  dist > 1D && dist < 4.0D)
					{
						mob.motionY = 0.5F;
					}
				}
				if(mob.getRNG().nextInt(100) == 0 && !mob.isSprinting() && !mob.isChild())
				{
					mob.setSprinting(true);
				}
			}
			if(isFastblade(held))
			{
				EntityLivingBase tar = mob.getAttackTarget();
				
				if(tar != null)
				{
					double dist = mob.getDistanceSqToEntity(tar);
					
					if(mob.onGround && mob.getRNG().nextInt(20) == 0 &&  dist > 1D && dist < 4.0D)
					{
						mob.motionY = 0.5F;
					}
				}
				if(mob.getRNG().nextInt(20) == 0 && !mob.isSprinting() && !mob.isChild())
				{
					mob.setSprinting(true);
				}
			}
			if(living.isBurning() && !living.isImmuneToFire())
			{
				panic(living, 0.25F, 5);
			}
		}
	}
	private boolean isAxe(ItemStack held) 
	{
		return held != null && held.getItem() instanceof ItemWaraxeMF || held != null && held.getItem() instanceof ItemBattleaxeMF;
	}
	private boolean isFastblade(ItemStack held) 
	{
		return held != null && held.getItem() instanceof ItemDagger || held != null && held.getItem() instanceof ItemKatanaMF;
	}
	
	private void applyBalance(EntityPlayer entityPlayer) 
	{
		MFLogUtil.logDebug("Weapon Balance Init");
		ItemStack weapon = entityPlayer.getHeldItem();
        float balance = 0.0F;
        
        if(weapon != null && weapon.getItem() instanceof IWeightedWeapon)
        {
        	balance = ((IWeightedWeapon)weapon.getItem()).getBalance(entityPlayer);
        }
        
        if (ConfigWeapon.useBalance && balance > 0 && entityPlayer != null)
        {
        	TacticalManager.throwPlayerOffBalance(entityPlayer, balance, true);
        }
	}
	
	public static void setParryCooldown(EntityLivingBase user, int ticks)
	{
		user.getEntityData().setInteger(parryCooldownNBT, ticks);
		
		if(!user.worldObj.isRemote && user instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)user;
			((WorldServer)player.worldObj).getEntityTracker().func_151248_b(player, new ParryPacket(ticks, player).generatePacket());
		}
	}
	public static int getParryCooldown(EntityLivingBase user)
	{
		if(user.getEntityData().hasKey(parryCooldownNBT))
		{
			return user.getEntityData().getInteger(parryCooldownNBT);
		}
		return 0;
	}
	public static void tickParryCooldown(EntityLivingBase user)
	{
		int ticks = getParryCooldown(user);
		if(ticks > 0)
		{
			setParryCooldown(user, ticks-1);
		}
	}
	public static boolean isParryAvailable(EntityLivingBase user)
	{
		return getParryCooldown(user)<= 0;
	}
	
	public static void setPostHitCooldown(EntityLivingBase user, int ticks)
	{
		user.getEntityData().setInteger(posthitCooldownNBT, ticks);
	}
	public static int getPostHitCooldown(EntityLivingBase user)
	{
		if(user.getEntityData().hasKey(posthitCooldownNBT))
		{
			return user.getEntityData().getInteger(posthitCooldownNBT);
		}
		return 0;
	}
	public static void tickPostHitCooldown(EntityLivingBase user)
	{
		int ticks = getPostHitCooldown(user);
		if(ticks > 0)
		{
			setPostHitCooldown(user, ticks-1);
		}
	}
	@SubscribeEvent
	public void jump(LivingJumpEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			if(StaminaBar.isSystemActive && StaminaBar.doesAffectEntity(event.entityLiving))
			{
				StaminaMechanics.onJump(event.entityLiving);
			}
		}
	}
	
	/*
	 * Causes the victim to 'Spaz out' which never stops being funny (Apply every tick)
	 */
	public static void panic(EntityLivingBase victim, float speed, int directionTimer)
	{
		double moveX = victim.getEntityData().getDouble("MF2_PanicX");
		double moveZ = victim.getEntityData().getDouble("MF2_PanicZ");
    	victim.setJumping(true);
    	
    	if((moveX == 0 && moveZ == 0) || rand.nextInt(directionTimer) == 0)
    	{
    		 moveX = (rand.nextDouble()-0.5D)*0.85D * speed;
    		 moveZ = (rand.nextDouble()-0.5D)*0.85D * speed;
    		 
    		 victim.getEntityData().setDouble("MF2_PanicX", moveX);
    		 victim.getEntityData().setDouble("MF2_PanicZ", moveZ);
    		 if(victim.onGround)victim.motionY = 0.25F;
    		 victim.rotationYaw = (float)(Math.atan2(moveX, moveZ));
    	}
    	victim.swingItem();
    	victim.limbSwing = 1.0F;
    	victim.moveEntity(moveX, 0D, moveZ);
	}
	
	
	
	private float modifyPlayerDamage(EntityPlayer hit, float dam) 
	{
		if(ResearchLogic.hasInfoUnlocked(hit, KnowledgeListMF.toughness))
		{
			dam *= 0.75F;//25% Resist
		}
		return dam;
	}
	
	/**
	 * How much strength is added (directly adds to melee dmg)
	 */
	public static float getStrengthEnhancement(EntityLivingBase user)
	{
		float dam = 0F;
		if(CogworkArmour.hasPoweredSuit(user))
		{
			dam += 3F;
		}
		return Math.max(-0.5F, dam);
	}
}
