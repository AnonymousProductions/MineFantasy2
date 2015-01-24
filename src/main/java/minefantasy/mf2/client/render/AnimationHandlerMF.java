package minefantasy.mf2.client.render;

import minefantasy.mf2.item.weapon.ItemWeaponMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class AnimationHandlerMF 
{
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
        if(event.phase == TickEvent.Phase.START)
        {
            tickStart(event.player);
        }
    }

    public void tickStart(EntityPlayer entityPlayer)
    {
    	ItemStack held = entityPlayer.getHeldItem();
    	if(held != null)
    	{
    		int parry = ItemWeaponMF.getParry(held);
    		if(parry > 0)
    		{
    			ItemWeaponMF.setParry(held, parry-1);
    		}
    	}
    }
}
