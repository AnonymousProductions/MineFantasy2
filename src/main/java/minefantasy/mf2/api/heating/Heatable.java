package minefantasy.mf2.api.heating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import minefantasy.mf2.MineFantasyII;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class Heatable 
{
	public static final int forgeMaximumMetalHeat = 500;
	public static boolean requiresHeating = true;
	public static HashMap<Item, Heatable> items = new HashMap<Item, Heatable>();
	/**
	 * The min heat the ingot must be to forge with mesured in celcius
	 */
	public final int minTemperature;

	/**
	 * The heat when it becomes unstable mesured in celcius
	 */
	public final int unstableTemperature;

	/**
	 * The max heat until the ingot is destroyed mesured in celcius
	 */
	public final int maxTemperature;

	/**
	 * The item that's used
	 */
	protected ItemStack object;

	public Heatable(ItemStack item, int min, int unstable, int max) {
		this.object = item;
		this.minTemperature = min;
		this.unstableTemperature = unstable;
		this.maxTemperature = max;
	}

	public static void addItem(ItemStack item, int min, int unstable, int max) {
		items.put(item.getItem(), new Heatable(item, min, unstable, max));
	}

	public static boolean canHeatItem(ItemStack item) {
		return loadStats(item) != null;
	}

	public static Heatable loadStats(ItemStack item) 
	{
		if (item == null)
			return null;

		if (items.isEmpty())
			return null;

		Heatable stats = items.get(item.getItem());
		if (stats != null)
		{
			if (stats.object.getItemDamage() == OreDictionary.WILDCARD_VALUE) 
			{
				if (stats.object.getItem() == item.getItem()) 
				{
					return stats;
				}
			} 
			else if (stats.object.isItemEqual(item))
			{
				return stats;
			}
		}

		return null;
	}

	public static final String NBT_ItemID = "MFHeatable_ItemID";
	public static final String NBT_SubID = "MFHeatable_SubID";
	public static final String NBT_ShouldDisplay = "MFHeatable_DisplayTemperature";
	
	public static final String NBT_CurrentTemp = "MFHeatable_Temperature";
	public static final String NBT_WorkableTemp = "MFHeatable_WorkTemp";
	public static final String NBT_UnstableTemp = "MFHeatable_UnstableTemp";
	/**
	 * 0 = nothing, 1 = soft, 2 = unstable
	 */
	public static byte getHeatableStage(ItemStack item) 
	{
		if(item == null || !(item.getItem() instanceof IHotItem))
		{
			return 0;
		}
		if (item != null && item.hasTagCompound())
		{
			int temp = getTemp(item);
			int work = item.getTagCompound().getInteger(NBT_WorkableTemp);
			int unstable = item.getTagCompound().getInteger(NBT_UnstableTemp);
			if (temp > unstable)
				return (byte) 2;
			if (temp > work)
				return (byte) 1;
		}
		return (byte) 0;
	}

	public static int getWorkTemp(ItemStack item) 
	{
		if(item == null || !(item.getItem() instanceof IHotItem))
		{
			return 0;
		}
		NBTTagCompound tag = getNBT(item);

		if (tag.hasKey(NBT_WorkableTemp))
			return tag.getInteger(NBT_WorkableTemp);

		return 0;
	}
	public static int getUnstableTemp(ItemStack item) 
	{
		if(item == null || !(item.getItem() instanceof IHotItem))
		{
			return 0;
		}
		NBTTagCompound tag = getNBT(item);

		if (tag.hasKey(NBT_UnstableTemp))
			return tag.getInteger(NBT_UnstableTemp);

		return 0;
	}
	public static int getTemp(ItemStack item)
	{
		if(item == null || !(item.getItem() instanceof IHotItem))
		{
			return 0;
		}
		NBTTagCompound tag = getNBT(item);

		if (tag.hasKey(NBT_CurrentTemp))
			return tag.getInteger(NBT_CurrentTemp);

		return 0;
	}
	public static ItemStack getItem(ItemStack item) 
	{
		if(item == null || !(item.getItem() instanceof IHotItem))
		{
			return null;
		}
		NBTTagCompound tag = getNBT(item);

		if (tag.hasKey(NBT_ItemID) && tag.hasKey(NBT_SubID)) 
		{
			Item metal = Item.getItemById(tag.getInteger(NBT_ItemID));
			ItemStack newItem = new ItemStack(metal, 1, tag.getInteger(NBT_SubID));
			
			if(item.hasTagCompound() && item.getTagCompound().hasKey("MF_CraftedByName"))
			{
				getNBT(newItem).setString("MF_CraftedByName", item.getTagCompound().getString("MF_CraftedByName"));
			}
			return newItem;
		}

		return null;
	}
	private static NBTTagCompound getNBT(ItemStack item) {
		if (!item.hasTagCompound())
			item.setTagCompound(new NBTTagCompound());
		return item.getTagCompound();
	}

	public static boolean isWorkable(ItemStack inputItem) 
	{
		if(inputItem == null || !(inputItem.getItem() instanceof IHotItem))
		{
			return true;
		}
		if(inputItem != null && inputItem.getItem() instanceof IHotItem)
		{
			return getHeatableStage(inputItem) == 1;
		}
		return true;
	}
}
