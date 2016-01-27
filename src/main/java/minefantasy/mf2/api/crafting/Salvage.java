package minefantasy.mf2.api.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import minefantasy.mf2.api.helpers.CustomToolHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Salvage
{
	private static Random random = new Random();
	private static HashMap<ItemStack, Object[]> salvageList = new HashMap<ItemStack, Object[]>();
	
	
	public static void addSalvage(Block input, Object... components)
	{
		addSalvage(Item.getItemFromBlock(input), components);
	}
	public static void addSalvage(Item input, Object... components)
	{
		addSalvage(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), components);
	}
	public static void addSalvage(ItemStack item, Object...components)
    {
		salvageList.put(item, components);
    }

	/**
	 * Break an item to its parts
	 * @return a list of items
	 */
	public static List<ItemStack> salvage(EntityPlayer user, ItemStack item)
    {
		return salvage(user, item, 1.0F);
    }
	
    public static List<ItemStack> salvage(EntityPlayer user, ItemStack item, float dropRate)
    {
    	Object[] entryList = getSalvage(item);
    	if(entryList == null)
    	{
    		return null;
    	}
    	
    	float durability = 1F;
    	if(item.isItemDamaged())
    	{
    		durability = (float)(item.getMaxDamage() - item.getItemDamage()) / (float)item.getMaxDamage();
    	}
    	
    	float chanceModifier = 1.25F;//80% Succcess rate
    	float chance = dropRate*durability;//Modifier for skill and durability
    	
    	return dropItems(user, entryList, chanceModifier, chance);
    }

	private static List<ItemStack> dropItems(EntityPlayer user, Object[] entryList, float chanceModifier, float chance) 
	{
		List<ItemStack>items = new ArrayList<ItemStack>();
		for(Object entry : entryList)
    	{
			if(entry != null)
			{
	    		if(entry instanceof Item && random.nextFloat()*chanceModifier < chance)
	    		{
	    			items = dropItemStack(user, items, new ItemStack((Item)entry), chanceModifier, chance);
	    		}
	    		if(entry instanceof Block && random.nextFloat()*chanceModifier < chance)
	    		{
	    			items = dropItemStack(user, items, new ItemStack((Block)entry), chanceModifier, chance);
	    		}
	    		if(entry instanceof ItemStack)
	    		{
	    			items = dropItemStack(user, items, (ItemStack)entry, chanceModifier, chance);
	    		}
			}
    	}
		return items;
	}
	
	private static List<ItemStack> dropItemStack(EntityPlayer user, List<ItemStack>items, ItemStack entry, float chanceModifier, float chance) 
	{
		for(int a = 0; a < entry.stackSize; a++)
		{
			if(random.nextFloat()*chanceModifier < chance)
			{
				boolean canSalvage = true;
				
				if(entry.getItem() instanceof ISalvageDrop)
				{
					canSalvage = ((ISalvageDrop)entry.getItem()).canSalvage(user, entry);
				}
				if(canSalvage)
				{
					ItemStack newitem = entry.copy();
					newitem.stackSize = 1;
					items.add(newitem);
				}
			}
		}
		return items;
	}
	public static Object[] getSalvage(ItemStack item)
    {
		if(item != null && item.getItem() instanceof ISpecialSalvage)
		{
			Object[] special = ((ISpecialSalvage)item.getItem()).getSalvage(item);
			if(special != null)
			{
				return special;
			}
		}
        Iterator iterator = salvageList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!doesMatch(item, (ItemStack)entry.getKey()));

        return (Object[])entry.getValue();
    }
	private static boolean doesMatch(ItemStack item1, ItemStack item2)
    {
		if(!CustomToolHelper.doesMatchForRecipe(item1, item2))
        {
        	return false;
        }
        return item2.getItem() == item1.getItem() && (item2.getItemDamage() == OreDictionary.WILDCARD_VALUE || item2.getItemDamage() == item1.getItemDamage());
    }
}
