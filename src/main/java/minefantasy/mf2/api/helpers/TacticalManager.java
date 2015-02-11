package minefantasy.mf2.api.helpers;

import java.util.Random;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.armour.IElementalResistance;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.api.weapon.IParryable;
import minefantasy.mf2.mechanics.CombatMechanics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;

/**
 * This calculates different tactical contexts for combat like flanking and blocking
 */
public class TacticalManager 
{
	private static Random rand = new Random();
	public static boolean shouldSlow = true;
	public static float minWeightSpeed = 10F;
	public static float arrowDeflectChance = 1.0F;
	
	/**
	 * Determines if you cant block with no stamina
	 */
	public static boolean	shouldStaminaBlock = false;
	/**
	 * Determines if the defender is hit in the front (180degree arc)
	 * @param attacker the attacker
	 * @param defender the defender
	 * @return true if the hit is on the front
	 */
	public static boolean canBlock(Entity attacker, EntityLivingBase defender)
	{
		return canBlock(attacker, defender, 180);
	}
	/**
	 * Determines if the defender is hit in the front with a custom arc
	 * @param attacker the attacker
	 * @param defender the defender
	 * @param arc the arc that can be blocked
	 * @return true if the hit is on the front
	 */
	public static boolean canBlock(Entity attacker, EntityLivingBase defender, float blockAngle)
	{
		if(attacker == null || defender == null)return false;
		
		float yaw = calculateHitAngle(attacker, defender);
		
        return yaw < blockAngle && yaw > -blockAngle;
	}
	
	/**
	 * Determines if an entity is flanking another
	 * @param source the source of the attack
	 * @param attacker the attacking entity
	 * @param defender the entity on view is questioned
	 * @param angle the angle the defender is flanked by
	 * @return true if the attacker hit within the back of defender between the angle
	 */
	public static boolean isFlankedBy(Entity attacker, EntityLivingBase defender, float angle)
	{
		float yaw = calculateHitAngle(attacker, defender);
		float blockAngle = (360-angle)/2;
		
		return !(yaw < blockAngle && yaw > -blockAngle);//Flanking is like reverse block
	}
	
	public static boolean canParry(DamageSource source, EntityLivingBase user, Entity entityHitting, ItemStack weapon)
	{
		if(shouldStaminaBlock && StaminaBar.isSystemActive && StaminaBar.doesAffectEntity(user) && !StaminaBar.isAnyStamina(user, false))
		{
			return false;
		}
		if(user instanceof EntityPlayer)
		{
			if(!((EntityPlayer)user).isBlocking())
			{
				return false;
			}
		}
		if(!CombatMechanics.isParryAvailable(user))
		{
			return false;
		}
		int confusion = 0;
		if(user.getActivePotionEffect(Potion.confusion) != null)
		{
			confusion = user.getActivePotionEffect(Potion.confusion).getAmplifier() +1;
		}
		float arc = source.isProjectile() ? 10 : 20;//DEFAULT
		
		arc *= getHighgroundModifier(user, entityHitting, 1.5F);
		
		if(weapon != null && weapon.getItem() instanceof IParryable)
		{
			IParryable parry = (IParryable)weapon.getItem();
			if(!parry.canUserParry(user))
			{
				return false;
			}
			arc = parry.getParryAngle(source, user, weapon);
			
			if(!parry.canParry(source, user, weapon))
			{
				return false;
			}
		}
		if(confusion > 0 && rand.nextInt(confusion+1) != 0)
		{
			return false;
		}
		return arc > 0 && canBlock(entityHitting, user, arc);
	}
	public static float getHighgroundModifier(Entity target, Entity hitter, float value)
	{
		if(target == null || hitter == null)
		{
			return 1.0F;
		}
		float gap = 0.5F;
		if(target.posY > hitter.posY + gap)//Blocker on high ground
		{
			return 1.0F  * value;
		}
		if(target.posY < hitter.posY - gap)//Attacker on high ground
		{
			return 1.0F / value;
		}
		return 1.0F;
	}
	/**
	 * Knocks the target back from the source
	 * @param target the enemy to be knocked back
	 * @param source the source of the knockback
	 * @param power the power a - pulls
	 * @param height the height they jump
	 */
	public static void knockbackEntity(Entity target, Entity source, float power, float height) 
	{
        target.addVelocity((double) (-MathHelper.sin(source.rotationYaw * (float) Math.PI / 180.0F) * (float) power * 0.5F), height, (double) (MathHelper.cos(source.rotationYaw * (float) Math.PI / 180.0F) * (float) power * 0.5F));
    }
	
	/**
	 * Causes the attacker to fly towards the target
	 * @param attacker the entity that moves
	 * @param target the target attacker aims for
	 * @param power the forward momentum
	 * @param height the height of the jump
	 */
	public static void lungeEntity(Entity attacker, Entity target, float power, float height) 
	{
        attacker.addVelocity((double) (-MathHelper.sin(attacker.rotationYaw * (float) Math.PI / 180.0F) * (float) power * 0.5F), height, (double) (MathHelper.cos(attacker.rotationYaw * (float) Math.PI / 180.0F) * (float) power * 0.5F));
    }
	
	public static boolean isRanged(DamageSource source) 
	{
		if(source == null)
		{
			return false;
		}
		if(source.isProjectile() || source instanceof EntityDamageSourceIndirect)
		{
			return true;
		}
		if(source.getEntity() != null && source.getSourceOfDamage() != null)
		{
			return source.getEntity() != source.getSourceOfDamage();
		}
		return false;
	}
	
	/**
	 * This gets the angle that "attacker" hit "defender" relating 0 to front
	 * @param attacker the entity that hit defender
	 * @param defender the hit entity in question
	 * @return the angle defender was hit (0 = front)
	 */
	private static float calculateHitAngle(Entity attacker, EntityLivingBase defender) 
	{
		if(attacker == null)
		{
			return 0F;
		}
		double xGap = attacker.posX - defender.posX;
        double zGap;

        for (zGap = attacker.posZ - defender.posZ; xGap * xGap + zGap * zGap < 1.0E-4D; zGap = (Math.random() - Math.random()) * 0.01D)
        {
            xGap = (Math.random() - Math.random()) * 0.01D; //makes the zgap
        }

        float yaw = (float)(Math.atan2(zGap, xGap) * 180.0D / Math.PI) - defender.rotationYaw;
        yaw = yaw - 90;

        
        //CONVERTS THE ANGLES
        while(yaw < -180)
        {
            yaw += 360;
        }
        while(yaw >= 180)
        {
            yaw -= 360;
        }
        
        return yaw;
	}
	
	/**
	 * Modifies the movement of an entity based on armour
	 */
	public static void applyArmourWeight(EntityLivingBase entityLiving)
	{
		if(shouldSlow )
		{
			//Default speed is 100%
			float totalSpeed = 100F;
			
			totalSpeed += ArmourCalculator.getSpeedModForWeight(entityLiving);
			//Limit the slowest speed to 1%
			if(totalSpeed <= minWeightSpeed)
			{
				totalSpeed = minWeightSpeed;
			}
			//apply speed mod
			if(entityLiving.onGround)
			{
				entityLiving.motionX *= (totalSpeed/100F);
				entityLiving.motionZ *= (totalSpeed/100F);
			}
		}
	}
	/**
	 * Gets the modifer for magic resistance 1.0=no effect
	 */
	public static float resistMagic(EntityLivingBase user, DamageSource source) 
	{
		float resistance = 100F;
		
		for(int a = 0; a < 4; a ++)
		{
			ItemStack armour = user.getEquipmentInSlot(a+1);
			if(armour != null && armour.getItem() instanceof IElementalResistance)
			{
				float modifier = ((IElementalResistance)armour.getItem()).getMagicResistance(armour, source);
				modifier *= ArmourCalculator.sizes[3-a];
				resistance -= modifier;
			}
		}
		
		return resistance/100F;
	}
	
	/**
	 * Gets the modifer for fire resistance 1.0=no effect
	 */
	public static float resistFire(EntityLivingBase user, DamageSource source) 
	{
		float resistance = 100F;
		
		for(int a = 0; a < 4; a ++)
		{
			ItemStack armour = user.getEquipmentInSlot(a+1);
			if(armour != null && armour.getItem() instanceof IElementalResistance)
			{
				float modifier = ((IElementalResistance)armour.getItem()).getFireResistance(armour, source);
				modifier *= ArmourCalculator.sizes[3-a];
				
				resistance -= modifier;
			}
		}
		
		return resistance/100F;
	}
	public static boolean resistArrow(EntityLivingBase user, DamageSource source, float dam) 
	{
		dam *= (1.00D - ArmourCalculator.getTotalArmourPercent(user));
		
		float threshold = 0.25F;
		float resistance = 1.0F;
		
		for(int a = 0; a < 4; a ++)
		{
			ItemStack armour = user.getEquipmentInSlot(4-a);
			if(armour != null && armour.getItem() instanceof IElementalResistance)
			{
				float modifier = ((IElementalResistance)armour.getItem()).getArrowDeflection(armour, source);
				modifier *= ArmourCalculator.sizes[a];
				
				resistance += modifier;
			}
		}
		threshold *= resistance;
		
		if(!user.worldObj.isRemote)
		MineFantasyAPI.debugMsg("Arrow Damage: " + dam + " Projectile Threshold: " + threshold);
		
		return dam <= threshold && dam > 0;
	}
	
	/**
	 * Gets the modifer for base resistance(non magic/fire)
	 */
	public static float resistBase(EntityLivingBase user, DamageSource source) 
	{
		float resistance = 100F;
		
		for(int a = 0; a < 4; a ++)
		{
			ItemStack armour = user.getEquipmentInSlot(a+1);
			if(armour != null && armour.getItem() instanceof IElementalResistance)
			{
				float modifier = ((IElementalResistance)armour.getItem()).getBaseResistance(armour, source);
				modifier *= ArmourCalculator.sizes[3-a];
				resistance -= modifier;
			}
		}
		return resistance/100F;
	}
	
	
	public static float getResistance(EntityLivingBase user, DamageSource source)
	{
		if(source.isFireDamage())
    	{
			return resistFire(user, source);
    	}
    	if(source.isMagicDamage() || source == DamageSource.wither)
    	{
    		 return resistMagic(user, source);
    	}
		return resistBase(user, source);
	}
	
	/**
	 * Returns if a target should not be set
	 * @param entity the attacker
	 * @param target the target chosen
	 */
	public static boolean shouldNotAttack(Entity attacker, EntityLivingBase target) 
	{
		//TODO aggro
		return false;
	}
	public static void throwPlayerOffBalance(EntityPlayer entityPlayer, float balance, boolean throwDown) 
	{
		float amplify = 30.0F;
    	
    	float offsetX = -balance/2;
    	float offsetY = balance;
    	
    	float yawBalance = offsetX*amplify;
    	float pitchBalance = offsetY*amplify;
    	entityPlayer.moveStrafing += offsetX;
    	if(offsetY > 0)
    	{
    		entityPlayer.moveForward += offsetY;
    	}
    	if(newBalanceSystem)
    	{
	    	entityPlayer.getEntityData().setFloat("MF_Balance_Pitch", pitchBalance);
	        entityPlayer.getEntityData().setFloat("MF_Balance_Yaw", yawBalance);
    	}
    	else
    	{
    		entityPlayer.rotationPitch += pitchBalance;
    		entityPlayer.rotationYaw += yawBalance;
    	}
	}
	public static boolean newBalanceSystem = false;
}
