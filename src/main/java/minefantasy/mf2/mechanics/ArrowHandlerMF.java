package minefantasy.mf2.mechanics;

import minefantasy.mf2.api.archery.Arrows;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.config.ConfigStamina;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class ArrowHandlerMF 
{
	/**
	 * When the arrow is pulled back it initiates
	 */
	@SubscribeEvent
	public void readyBow(ArrowNockEvent event)
	{
		if(Arrows.arrows == null || Arrows.arrows.size() <= 0)
		{
			return;
		}
		
		EntityPlayer user = event.entityPlayer;
		ItemStack bow = event.result;
		
		/*Checks over registered arrows and finds one to load
		* The Quiver can be used to determine this
		*/
		ItemStack preset = Arrows.getPresetArrow(user);
		if(preset != null && (user.inventory.hasItemStack(preset) || getIsInfinite(user, bow)))
		{
			user.setItemInUse(bow, bow.getMaxItemUseDuration());//Starts pullback
			loadArrow(user, bow, preset);//adds the arrow to NBT for rendering and later use
			event.setCanceled(true);
			return;
		}
		
		for(int a = 0; a < Arrows.arrows.size(); a ++)
		{
			ItemStack arrow = Arrows.arrows.get(a);
			if(user.inventory.hasItemStack(arrow))
			{
				user.setItemInUse(bow, bow.getMaxItemUseDuration());//Starts pullback
				loadArrow(user, bow, arrow);//adds the arrow to NBT for rendering and later use
				event.setCanceled(true);
				return;
			}
		}
	}

	/**
	 * This method adds arrows to the bow
	 */
	public static void loadArrow(EntityPlayer player, ItemStack bow, ItemStack arrow)
	{
		if(player.worldObj.isRemote)
		{
			return;
		}
		NBTTagCompound nbt = getOrApplyNBT(bow);
		
		if(arrow == null)
		{
			nbt.removeTag("loadedArrow");
		}
		else
		{
			NBTTagCompound loaded = new NBTTagCompound();
			arrow.writeToNBT(loaded);
			nbt.setTag("loadedArrow", loaded);
		}
	}
	
	/**
	 * Gets the NBT, if there is none, it creates it
	 */
	private static NBTTagCompound getOrApplyNBT(ItemStack bow) 
	{
		if(!bow.hasTagCompound())
		{
			bow.setTagCompound(new NBTTagCompound());
		}
		return bow.getTagCompound();
	}
	
	@SubscribeEvent (priority=EventPriority.HIGHEST)
	public void applyExhaustArrow(ArrowLooseEvent event)
	{
		if(StaminaBar.isSystemActive && !StaminaBar.isAnyStamina(event.entityPlayer, false))
		{
			if(ConfigStamina.weaponDrain < 1.0F)
			event.charge *= ConfigStamina.weaponDrain;
		}
	}
	/**
	 * This initates when firing a bow
	 * @param event
	 */
	@SubscribeEvent
	public void fireArrow(ArrowLooseEvent event)
	{
		Arrows.updateArrowCount(event.entityPlayer);
		
		ItemStack bow = event.bow;
		float power = event.charge;
		EntityPlayer user = event.entityPlayer;
		World world = event.entity.worldObj;
		boolean infinite = getIsInfinite(user, bow);
		
		float charge = power / 20.0F;
        charge = (charge * charge + charge * 2.0F) / 3.0F;

        if (charge < 0.1D)
        {
            return;
        }

        if (charge > 1.0F)
        {
            charge = 1.0F;
        }
        
        
		//Default is flint arrow
		ItemStack arrow = new ItemStack(Items.arrow);
		if(Arrows.getLoadedArrow(bow) != null)
		{
			//if an arrow is on the bow, it uses that
			arrow = Arrows.getLoadedArrow(bow);
		}
		
		if(Arrows.handlers != null && Arrows.handlers.size() > 0)
		{
			for(int a = 0; a < Arrows.handlers.size(); a ++)
			{
				//If the Arrow handler succeeds at firing an arrow
				if(Arrows.handlers.get(a).onFireArrow(world, arrow, bow, user, charge, infinite))
				{
					if(!user.capabilities.isCreativeMode)
		            {
		            	bow.damageItem(1, user);
		            }
					if(!infinite)
					{
						consumePlayerItem(user, arrow);
					}
					world.playSoundAtEntity(user, "minefantasy2:weapon.bowFire", 0.5F, 1.0F / (world.rand.nextFloat() * 0.4F + 1.2F) + charge * 0.5F);
					loadArrow(user, bow, null);
					event.setCanceled(true);
					break;
				}
			}
		}
	}
	private boolean getIsInfinite(EntityPlayer user, ItemStack bow) 
	{
		return user.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, bow) > 0;
	}

	//Used to take an item/subId from the inventory
	private boolean consumePlayerItem(EntityPlayer player, ItemStack item) 
    {
    	for(int a = 0; a < player.inventory.getSizeInventory(); a ++)
    	{
    		ItemStack i = player.inventory.getStackInSlot(a);
    		if(i != null && i.isItemEqual(item))
    		{
    			player.inventory.decrStackSize(a, 1);
    			return true;
    		}
    	}
    	return false;
	}
	
	@SubscribeEvent
	public void pickupItem(ItemPickupEvent event)
	{
		Arrows.updateArrowCount(event.player);
	}
}
