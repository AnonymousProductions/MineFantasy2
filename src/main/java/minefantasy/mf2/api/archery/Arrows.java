package minefantasy.mf2.api.archery;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class Arrows 
{
	/**
	 * List of fireable arrows
	 */
	public static List<ItemStack> arrows = new ArrayList<ItemStack>();
	/**
	 * List of handlers
	 */
	public static List<IArrowHandler> handlers = new ArrayList<IArrowHandler>();
	
	/**
	 * Adds an arrow that can be fired
	 */
	public static void addArrow(ItemStack item)
	{
		arrows.add(item);
	}
	
	/**
	 * Adds an arrow that can be fired not considering sub Ids
	 */
	public static void addArrow(Item item)
	{
		addArrow(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
	}
	
	/**
	 * Adds a handler
	 */
	public static void addHandler(IArrowHandler handler)
	{
		handlers.add(handler);
	}
	
	/**
	 * Gets the arrow loaded on the bow used for rendering and firing
	 */
	public static ItemStack getLoadedArrow(ItemStack bow)
	{
		if(bow != null && bow.hasTagCompound())
		{
			if(bow.getTagCompound().hasKey("loadedArrow"))
			{
				return ItemStack.loadItemStackFromNBT(bow.getTagCompound().getCompoundTag("loadedArrow"));
			}
		}
		return null;
	}
	
	/**
	 * Gets the preset arrow to fire(From right-clicking supported items). 
	 */
	public static ItemStack getPresetArrow(EntityPlayer user)
	{
		if(user.getEntityData().hasKey("MF_PresetArrow"))
		{
			NBTTagCompound nbt = user.getEntityData().getCompoundTag("MF_PresetArrow");
			return ItemStack.loadItemStackFromNBT(nbt);
		}
		return null;
	}
	/**
	 * Sets the preset arrow for the player to use: This will try be used first (Make sure the bow can fire this however, or it wont work)
	 */
	public static void presetArrow(EntityPlayer user, ItemStack selection)
	{
		selection = selection.copy();
		selection.stackSize = 1;
		NBTTagCompound arrow = new NBTTagCompound();
		selection.writeToNBT(arrow);
		user.getEntityData().setTag("MF_PresetArrow", arrow);
		
		updateArrowCount(user);
	}
	
	/**
	 * This gets the count of how many arrows are in the inventory(from the preset arrow)
	 */
	public static int getArrowCount(EntityPlayer player)
	{
		if(player.getEntityData().hasKey("MF_ArrowCount"))
		{
			return player.getEntityData().getInteger("MF_ArrowCount");
		}
		return 0;
	}
	/**
	 * This updates the nbt to recount the arrows(used after firing bows, picking up and ticking)
	 * @param player
	 */
	public static boolean displayArrowCount = true;
	public static void updateArrowCount(EntityPlayer player)
	{
		if(displayArrowCount && player.worldObj.isRemote)
		{
			player.getEntityData().setInteger("MF_ArrowCount", countArrows(player));
		}
	}

	private static int countArrows(EntityPlayer player)
	{
		if(!displayArrowCount)
		{
			return -1;
		}
		int count = 0;
		ItemStack arrow = Arrows.getPresetArrow(player);
		if(arrow != null)
		{
			for(ItemStack item : player.inventory.mainInventory)
			{
				if(item != null && item.isItemEqual(arrow))
				{
					count += item.stackSize;
				}
			}
		}
		return count;
	}
}
