package minefantasy.mf2.mechanics;

import java.util.Random;

import minefantasy.mf2.config.ConfigHardcore;
import minefantasy.mf2.item.armour.ItemArmourMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MonsterUpgrader 
{
	private static final float zombieWepChance = 10F;
	private static final float zombieKnightChance = 500F;
	private static final float zombieBruteChance =  300F;
	
	private static final float creeperJockeyChance = 60F;
	private static final float witchRiderChance = 100F;
	
	private static Random rand = new Random();
	public static final String zombieArmourNBT = "MF_ZombieArmour";
	
	public void upgradeMob(EntityLivingBase mob)
    {
		int diff = mob.worldObj.getDifficulty().getDifficultyId();

		if(ConfigHardcore.upgradeZombieWep)
		{
			if(mob instanceof EntitySkeleton)
			{
				if(((EntitySkeleton)mob).getSkeletonType() == 1)
				{
					giveEntityWeapon(mob, 5, rand.nextInt(7));
				}
				else if(CombatMechanics.swordSkeleton && rand.nextInt(3) == 0)
				{
					mob.setCurrentItemOrArmor(0, new ItemStack(ToolListMF.swords[4]));
					((EntitySkeleton)mob).setCombatTask();
				}
			}
			else if(mob instanceof EntityZombie)
			{
				int tier = 2;
				if(mob instanceof EntityPigZombie)
				{
					tier = 4;
					giveEntityWeapon(mob, tier, rand.nextInt(7));
				}
				else
				{
					if(mob.getHeldItem() != null && mob.getHeldItem().getItem() == Items.iron_sword)
					{
						giveEntityWeapon(mob, tier, 0);
					}
					else
					{
						float mod = diff >= 2 ? ConfigHardcore.zombieWepChance*2 : diff < 1 ? ConfigHardcore.zombieWepChance/2 : ConfigHardcore.zombieWepChance;
						float chance = rand.nextFloat()*100F*mod;
						if(chance >= (100F-zombieWepChance))
						{
							giveEntityWeapon(mob, tier, rand.nextInt(4));
						}
					}
				}
				if(rand.nextFloat()*(zombieKnightChance*ConfigHardcore.zombieWepChance) < diff)
				{
					createZombieKnight((EntityZombie)mob);
				}
				else if(rand.nextFloat()*(zombieBruteChance*ConfigHardcore.zombieWepChance) < diff)
				{
					createZombieBrute((EntityZombie)mob);
				}
				else if(ConfigHardcore.fastZombies)
				{
					mob.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3F);
				}
			}
		}
		else if(mob instanceof EntitySpider)
		{
			if(mob.riddenByEntity == null)
			{
				if(rand.nextFloat()*(witchRiderChance*ConfigHardcore.zombieWepChance) < diff)
				{
					EntityWitch rider = new EntityWitch(mob.worldObj);
					rider.setPosition(mob.posX, mob.posY, mob.posZ);
					mob.worldObj.spawnEntityInWorld(rider);
					rider.mountEntity(mob);
				}
				else if(rand.nextFloat()*(creeperJockeyChance*ConfigHardcore.zombieWepChance) < diff)
				{
					EntityCreeper rider = new EntityCreeper(mob.worldObj);
					rider.setPosition(mob.posX, mob.posY, mob.posZ);
					mob.worldObj.spawnEntityInWorld(rider);
					rider.mountEntity(mob);
				}
			}
		}
		else
		{
			if(mob.getHeldItem() != null && mob.getHeldItem().getItem() == Items.iron_sword)
			{
				giveEntityWeapon(mob, 2, 0);
			}
		}
		if(mob instanceof EntityPigZombie)
		{
			mob.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0F);
		}
    }
	

	private void createZombieKnight(EntityZombie mob) 
	{
		if(mob.isChild())return;
		int tier = rand.nextInt(10) == 0 ? 3 : 2;
		if(mob instanceof EntityPigZombie)
		{
			tier = 4;
		}
		mob.setVillager(false);
		giveEntityArmour(mob, tier, 1);//Steel Plate
		mob.setCurrentItemOrArmor(0, new ItemStack(ToolListMF.greatswords[tier-1]));
		mob.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F);
	}
	private void createZombieBrute(EntityZombie mob) 
	{
		if(mob.isChild())return;
		int tier = 1;
		int weapontier = 2;
		if(mob instanceof EntityPigZombie)
		{
			tier = 4;
			weapontier = 4;
		}
		giveEntityArmour(mob, tier, 0);//Iron Chain
		mob.setCurrentItemOrArmor(0, new ItemStack(ToolListMF.waraxes[weapontier]));
		mob.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35F);
	}


	private void giveEntityWeapon(EntityLivingBase mob, int tier, int weaponType)
	{
		if(tier < 0)return;
		
		Item[] weapon = ToolListMF.swords;
		if(weaponType == 1)
		{
			weapon = ToolListMF.waraxes;
		}
		if(weaponType == 2)
		{
			weapon = ToolListMF.maces;
		}
		if(weaponType == 3)
		{
			weapon = ToolListMF.daggers;
		}
		mob.setCurrentItemOrArmor(0, new ItemStack(weapon[tier]));
	}
	private void giveEntityArmour(EntityLivingBase mob, int tier, int suit)
	{
		mob.getEntityData().setBoolean(zombieArmourNBT, true);
		
		ItemArmourMF[] pool = ArmourListMF.chainmail;
		if(suit == 1)
		{
			pool = ArmourListMF.fieldplate;
		}
		for(int a = 0; a < 4; a ++)
		{
			mob.setCurrentItemOrArmor(4-a, ArmourListMF.armour(pool, tier, a));
		}
	}
	
	@SubscribeEvent
	public void updateLiving(LivingUpdateEvent event)
	{
		EntityLivingBase living = event.entityLiving;
		
		if(isEnabled() && !living.worldObj.isRemote && !living.getEntityData().hasKey("giveMFWeapon"))
		{
			living.getEntityData().setBoolean("giveMFWeapon", true);
			upgradeMob(event.entityLiving);
		}
	}


	private boolean isEnabled()
	{
		return true;
	}
}
