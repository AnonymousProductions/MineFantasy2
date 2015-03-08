package minefantasy.mf2.mechanics;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.archery.Arrows;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.helpers.PlayerTagData;
import minefantasy.mf2.api.helpers.TacticalManager;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.config.ConfigWeapon;
import minefantasy.mf2.item.food.ItemFoodMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class PlayerTickHandlerMF
{
	private Random rand = new Random();
	//SPRINT JUMPING
	//DEFAULT:= 0:22 (50seconds till starve, 35s till nosprint) (16m in MC time for 4 missing bars)
	//SLOW=5: = 2:20 (5mins till starve, 3:30 till nosprint) (1h 40m in MC time for 4 missing bars)
	//EXHAUSTION SCALE = 3.0F = 1hunger
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if(event.phase == TickEvent.Phase.END)
        {
        	TacticalManager.applyArmourWeight(event.player);
        	if(event.player.ticksExisted % 20 == 0)
        	{
        		if(event.player.isBurning() && TacticalManager.getResistance(event.player, DamageSource.inFire) <= 0.0F)
            	{
            		event.player.extinguish();
            	}
    			Arrows.updateArrowCount(event.player);
        	}
        	playSounds(event.player);
        	
        	if(RPGElements.isSystemActive)
        	{
        		if(event.player.isSprinting() && event.player.ticksExisted % 10 == 0)
        		{
        			SkillList.athletics.addXP(event.player, 1);
        		}
        		else if(event.player.isSneaking() && TacticalManager.isEntityMoving(event.player) && event.player.ticksExisted % 10 == 0)
        		{
        			SkillList.sneak.addXP(event.player, 1);
        		}
        	}
        }
		
        if(event.phase == TickEvent.Phase.START)
        {
        	applyBalance(event.player);
        	ItemFoodMF.onTick(event.player);
        }
    }

	private void playSounds(EntityPlayer user)
	{
		double speed = Math.hypot(user.motionX, user.motionZ);
		//TODO Sounds when walking
		float bulk = ArmourCalculator.getTotalWeightOfWorn(user, false);
		
        if(speed > 0.01D && bulk >= 50)
        {
        	float volume = 0.2F * bulk / 50F;
        	if(rand.nextInt(20) == 0)
        	{
        		user.playSound("mob.irongolem.throw", volume, 1.0F);
        	}
        }
	}
	
	private void applyBalance(EntityPlayer entityPlayer) 
	{
        float weight = 2.0F;
        float pitchBalance = entityPlayer.getEntityData().hasKey("MF_Balance_Pitch") ? entityPlayer.getEntityData().getFloat("MF_Balance_Pitch"): 0F;
        float yawBalance = entityPlayer.getEntityData().hasKey("MF_Balance_Yaw") ? entityPlayer.getEntityData().getFloat("MF_Balance_Yaw"): 0F;
        
        if(pitchBalance > 0)
        {
        	if(pitchBalance < 1.0F && pitchBalance > -1.0F)weight = pitchBalance;
        	pitchBalance -= weight;
        	
        	if(ConfigWeapon.useBalance)
            {
        		entityPlayer.rotationPitch += pitchBalance > 0 ? weight : -weight;
            }
        	
        	if(pitchBalance < 0)pitchBalance = 0;
        }
        if(yawBalance > 0)
        {
        	if(yawBalance < 1.0F && yawBalance > -1.0F)weight = yawBalance;
        	yawBalance -= weight;
        	
        	if(ConfigWeapon.useBalance)
            {
        		MFLogUtil.logDebug("Weapon Balance Move");
        		entityPlayer.rotationYaw += yawBalance > 0 ? weight : -weight;
            }
        	
        	if(yawBalance < 0)yawBalance = 0;
        }
    	entityPlayer.getEntityData().setFloat("MF_Balance_Pitch", pitchBalance);
    	entityPlayer.getEntityData().setFloat("MF_Balance_Yaw", yawBalance);
	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
		if(event.player.worldObj.isRemote)return;
		
		NBTTagCompound persist = PlayerTagData.getPersistedData(event.player);
		MFLogUtil.logDebug("Sync data");
    	ResearchLogic.syncData((EntityPlayer) event.player);
    	
    	if(!persist.hasKey("MF_HasBook"))
    	{
    		persist.setBoolean("MF_HasBook", true);
    		if(event.player.capabilities.isCreativeMode)return;
    		
    		event.player.inventory.addItemStackToInventory(new ItemStack(ToolListMF.researchBook));
    		ResearchLogic.setKnowledgePoints(event.player, ResearchLogic.startersPoints);
    	}
    	if(RPGElements.isSystemActive)
    	{
    		RPGElements.initSkills(event.player);
    	}
    }
}
