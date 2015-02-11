package minefantasy.mf2.hunger;

import minefantasy.mf2.api.MineFantasyAPI;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class HungerSystemMF
{
	private static final String prevFoodNBT = "MF_previousFoodLevel";
	private static final String saturationNBT = "MF_saturationTicks";
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if(!event.player.worldObj.isRemote)
        {
        	if(event.phase == TickEvent.Phase.START)
        	{
        		event.player.getEntityData().setInteger(prevFoodNBT, event.player.getFoodStats().getFoodLevel());
        	}
        	if(event.phase == TickEvent.Phase.END)
        	{
        		decrSaturation(event.player);
        		slowHunger(event.player);
        	}
        }
	}
	
	public void slowHunger(EntityPlayer player)
    {
		//init vars
		int food = player.getFoodStats().getFoodLevel();
		
    	int prevFood = food;
    	if(player.getEntityData().hasKey(prevFoodNBT))
    	{
    		prevFood = player.getEntityData().getInteger(prevFoodNBT);
    	}
    	
    	if(food < prevFood)
    	{
    		float sat = getSaturation(player);
    		MineFantasyAPI.debugMsg("Saturation: " + sat);
    		if(sat > 0)
    		{
    			player.getFoodStats().addStats(1, 0.0F);
    		}
    	}
    }
	
	public static void setSaturation(EntityPlayer user, float value)
	{
		user.getEntityData().setFloat(saturationNBT, value);
	}
	public static float getSaturation(EntityPlayer user)
	{
		if(user.getEntityData().hasKey(saturationNBT))
		{
			return user.getEntityData().getFloat(saturationNBT);
		}
		return 0F;
	}
	public static void decrSaturation(EntityPlayer user)
	{
		float sat = getSaturation(user);
		sat --;
		if(sat < 0)
		{
			sat = -1;
		}
		if(!user.worldObj.isRemote)
		{
			setSaturation(user, sat);
		}
	}
	public static void applySaturation(EntityPlayer consumer, float seconds)
	{
		float newValue = seconds*20F;
		float value = getSaturation(consumer);
		
		if(newValue > value)
		{
			setSaturation(consumer, newValue);
		}
	}
}
