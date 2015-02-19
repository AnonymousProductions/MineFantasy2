package minefantasy.mf2.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minefantasy.mf2.api.crafting.anvil.CraftingManagerAnvil;
import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
import minefantasy.mf2.api.crafting.carpenter.CraftingManagerCarpenter;
import minefantasy.mf2.api.crafting.carpenter.ICarpenterRecipe;
import minefantasy.mf2.api.knowledge.KnowledgeType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumChatFormatting;

public class MineFantasyAPI 
{
	/**
	 * This variable saves if MineFantasy is in debug mode
	 */
	public static boolean isInDebugMode;

	public static void debugMsg(String msg)
	{
		if(isInDebugMode)
		{
			System.out.println("[MineFantasyAPI](Debug) " + msg);
		}
	}

	public static void removeAllRecipes(Item result)
	{
		List recipeList = CraftingManager.getInstance().getRecipeList();
		for(int i = 0; i < recipeList.size(); i++)
		{
			IRecipe recipe = (IRecipe)recipeList.get(i);
			if(recipe != null && recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem() == result)
			{
				debugMsg("Removed Recipe for " + recipe.getRecipeOutput().getDisplayName());
			}
		}
	}
	
	/**
	 * Adds a shaped recipe for anvils with all variables
	 * 
	 * @param result The output item
	 * @param hot True if the result is hot(Does not apply to blocks)
	 * @param experiance the experiance gained from crafting
	 * @param toolType the tool type required to hit ("hammer", "hvyHammer", etc)
	 * @param hammerType the hammer tier required for creation:
	 * @param anvil the anvil required bronze 0, iron 1, steel 2
	 * @param forgeTime The time taken to forge(default is 200. each hit is about 100)
	 * @param input The input for the item (Exactly the same as regular recipes)
	 */
	public static void addAnvilRecipe(ItemStack result, boolean hot, String toolType, int hammerType, int anvil, int forgeTime, Object... input)
	{
		CraftingManagerAnvil.getInstance().addRecipe(result, "", hot, 0F, toolType, hammerType, anvil, forgeTime, input);
	}
	public static void addAnvilRecipe(ItemStack result, boolean hot, int hammerType, int anvil, int forgeTime, Object... input)
	{
		addAnvilRecipe(result, hot, "hammer", hammerType, anvil, forgeTime, input);
	}
	
	/**
	 * Adds a shaped recipe for anvils with all variables
	 * 
	 * @param result The output item
	 * @param research The research required
	 * @param hot True if the result is hot(Does not apply to blocks)
	 * @param experiance the experiance gained from crafting
	 * @param toolType the tool type required to hit ("hammer", "hvyHammer", etc)
	 * @param hammerType the hammer tier required for creation:
	 * @param anvil the anvil required bronze 0, iron 1, steel 2
	 * @param forgeTime The time taken to forge(default is 200. each hit is about 100)
	 * @param input The input for the item (Exactly the same as regular recipes)
	 */
	public static IAnvilRecipe addAnvilRecipe(ItemStack result, String research, boolean hot, String toolType, int hammerType, int anvil, int forgeTime, Object... input)
	{
		return CraftingManagerAnvil.getInstance().addRecipe(result, research, hot, 0F, toolType, hammerType, anvil, forgeTime, input);
	}
	public static IAnvilRecipe addAnvilRecipe(ItemStack result, String research, boolean hot, int hammerType, int anvil, int forgeTime, Object... input)
	{
		return addAnvilRecipe(result, research, hot, "hammer", hammerType, anvil, forgeTime, input);
	}
	
	/**
	 * Adds a shaped recipe for carpenter benches with all variables
	 * 
	 * @param result The output item
	 * @param sound The sound it makes ("minefantasy2:blocks.carpentermallet"), "step.wood", etc
	 * @param experience the experience gained from crafting
	 * @param toolType the tool type required to hit
	 * @param toolTier the tools tier required for creation:
	 * @param craftTime The time taken to craft(default is 200. each hit is about 100)
	 * @param input The input for the item (Exactly the same as regular recipes)
	 */
	public static void addCarpenterRecipe(ItemStack result, String sound, String toolType, int toolTier, int craftTime, Object... input)
	{
		CraftingManagerCarpenter.getInstance().addRecipe(result, "", sound, 0F, toolType, toolTier, -1, craftTime, input);
	}
	public static void addCarpenterRecipe(ItemStack result, String sound, int craftTime, Object... input)
	{
		addCarpenterRecipe(result, "", sound, "hands", -1, craftTime, input);
	}
	public static void addShapelessCarpenterRecipe(ItemStack result, String sound, String toolType, int toolTier, int craftTime, Object... input)
	{
		CraftingManagerCarpenter.getInstance().addShapelessRecipe(result, "", sound, 0F, toolType, toolTier, -1, craftTime, input);
	}
	public static void addShapelessCarpenterRecipe(ItemStack result, String sound, int craftTime, Object... input)
	{
		addShapelessCarpenterRecipe(result,  sound, "hands", -1, craftTime, input);
	}
	
	
	/**
	 * Adds a shaped recipe for carpenter benches with all variables
	 * 
	 * @param result The output item
	 * @param research The research required
	 * @param sound The sound it makes ("minefantasy2:blocks.carpentermallet"), "step.wood", etc
	 * @param experience the experience gained from crafting
	 * @param toolType the tool type required to hit
	 * @param toolTier the tools tier required for creation:
	 * @param craftTime The time taken to craft(default is 200. each hit is about 100)
	 * @param input The input for the item (Exactly the same as regular recipes)
	 */
	public static ICarpenterRecipe addCarpenterRecipe(ItemStack result, String research, String sound, String toolType, int toolTier, int craftTime, Object... input)
	{
		return CraftingManagerCarpenter.getInstance().addRecipe(result, research, sound, 0F, toolType, toolTier, -1, craftTime, input);
	}
	public static ICarpenterRecipe addCarpenterRecipe(ItemStack result, String research, String sound, int craftTime, Object... input)
	{
		return addCarpenterRecipe(result, research, sound, "hands", -1, craftTime, input);
	}
	public static ICarpenterRecipe addShapelessCarpenterRecipe(ItemStack result, String research, String sound, String toolType, int toolTier, int craftTime, Object... input)
	{
		return CraftingManagerCarpenter.getInstance().addShapelessRecipe(result, research, sound, 0F, toolType, toolTier, -1, craftTime, input);
	}
	public static ICarpenterRecipe addShapelessCarpenterRecipe(ItemStack result, String research, String sound, int craftTime, Object... input)
	{
		return addShapelessCarpenterRecipe(result, research,  sound, "hands", -1, craftTime, input);
	}
}
