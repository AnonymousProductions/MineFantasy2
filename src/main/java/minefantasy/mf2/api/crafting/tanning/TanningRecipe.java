package minefantasy.mf2.api.crafting.tanning;

import java.util.ArrayList;

import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TanningRecipe 
{
	public static final ArrayList<TanningRecipe>recipeList = new ArrayList<TanningRecipe>();
	
	public static void addRecipe(Object input, float time, ItemStack output)
	{
		addRecipe(input, time, -1, output);
	}
	public static void addRecipe(Object input, float time, int tier, ItemStack output)
	{
		addRecipe(input, time, tier, "knife", output);
	}
	public static void addRecipe(Object input, float time, int tier, String toolType, ItemStack output)
	{
		recipeList.add(new TanningRecipe(convertItem(input), time, tier, toolType, output));
	}
	
	public static ItemStack convertItem(Object input)
	{
		if(input instanceof ItemStack)
		{
			return (ItemStack)input;
		}
		if(input instanceof Item)
		{
			return new ItemStack((Item)input, 1, OreDictionary.WILDCARD_VALUE);
		}
		if(input instanceof Block)
		{
			return new ItemStack((Block)input, 1, OreDictionary.WILDCARD_VALUE);
		}
		MFLogUtil.logWarn("Tanning Recipe found invalid item!");
		return null;
	}
	public static TanningRecipe getRecipe(ItemStack item)
	{
		if(item == null)return null;
		
		for(int a = 0; a < recipeList.size(); a++)
		{
			TanningRecipe recipe = recipeList.get(a);
			if(recipe.input.getItem() == item.getItem())
			{
				if(recipe.input.getItemDamage() == OreDictionary.WILDCARD_VALUE || recipe.input.getItemDamage() == item.getItemDamage())
				{
					return recipe;
				}
			}
		}
		return null;
	}
	
	public int tier;
	public float time;
	public ItemStack input, output;
	public String toolType;
	
	public TanningRecipe(ItemStack input, float time, int tier, String toolType, ItemStack output)
	{
		this.time = time;
		this.tier = tier;
		this.input = input;
		this.output = output;
		this.toolType = toolType;
	}
}
