package minefantasy.mf2.api;

import java.util.ArrayList;
import java.util.List;

import minefantasy.mf2.api.crafting.anvil.CraftingManagerAnvil;
import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
import minefantasy.mf2.api.crafting.carpenter.CraftingManagerCarpenter;
import minefantasy.mf2.api.crafting.carpenter.ICarpenterRecipe;
import minefantasy.mf2.api.heating.ForgeFuel;
import minefantasy.mf2.api.heating.ForgeItemHandler;
import minefantasy.mf2.api.heating.Heatable;
import minefantasy.mf2.api.refine.Alloy;
import minefantasy.mf2.api.refine.AlloyRecipes;
import minefantasy.mf2.api.refine.BlastFurnaceRecipes;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;

public class MineFantasyAPI 
{
	/**
	 * This variable saves if MineFantasy is in debug mode
	 */
	public static boolean isInDebugMode;

	public static void debugMsg(String msg)
	{
		MFLogUtil.logDebug(msg);
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
	/** {@link MineFantasyAPI#addCarpenterRecipe} */
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
	/** {@link MineFantasyAPI#addCarpenterRecipe} */
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
	/** {@link MineFantasyAPI#addCarpenterRecipe} */
	public static void addCarpenterRecipe(ItemStack result, String sound, int craftTime, Object... input)
	{
		addCarpenterRecipe(result, "", sound, "hands", -1, craftTime, input);
	}
	/** {@link MineFantasyAPI#addCarpenterRecipe} */
	public static void addShapelessCarpenterRecipe(ItemStack result, String sound, String toolType, int toolTier, int craftTime, Object... input)
	{
		CraftingManagerCarpenter.getInstance().addShapelessRecipe(result, "", sound, 0F, toolType, toolTier, -1, craftTime, input);
	}
	/** {@link MineFantasyAPI#addCarpenterRecipe} */
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
	/** {@link MineFantasyAPI#addCarpenterRecipe} */
	public static ICarpenterRecipe addCarpenterRecipe(ItemStack result, String research, String sound, int craftTime, Object... input)
	{
		return addCarpenterRecipe(result, research, sound, "hands", -1, craftTime, input);
	}
	/** {@link MineFantasyAPI#addCarpenterRecipe} */
	public static ICarpenterRecipe addShapelessCarpenterRecipe(ItemStack result, String research, String sound, String toolType, int toolTier, int craftTime, Object... input)
	{
		return CraftingManagerCarpenter.getInstance().addShapelessRecipe(result, research, sound, 0F, toolType, toolTier, -1, craftTime, input);
	}
	/** {@link MineFantasyAPI#addCarpenterRecipe} */
	public static ICarpenterRecipe addShapelessCarpenterRecipe(ItemStack result, String research, String sound, int craftTime, Object... input)
	{
		return addShapelessCarpenterRecipe(result, research,  sound, "hands", -1, craftTime, input);
	}

	public static void addBlastFurnaceRecipe(Block input, ItemStack output) 
	{
		BlastFurnaceRecipes.smelting().addRecipe(input, output);
	}
	public static void addBlastFurnaceRecipe(Item input, ItemStack output) 
	{
		BlastFurnaceRecipes.smelting().addRecipe(input, output);
	}
	public static void addBlastFurnaceRecipe(ItemStack input, ItemStack output) 
	{
		BlastFurnaceRecipes.smelting().addRecipe(input, output);
	}
	/**
	 * This fuel handler is for MineFantasy equipment, it uses real fuel(like coal) not wood
	 */
	private static List<IFuelHandler> fuelHandlers = Lists.newArrayList();
	public static void registerFuelHandler(IFuelHandler handler)
    {
        fuelHandlers.add(handler);
    }
    public static int getFuelValue(ItemStack itemStack)
    {
        int fuelValue = 0;
        for (IFuelHandler handler : fuelHandlers)
        {
            fuelValue = Math.max(fuelValue, handler.getBurnTime(itemStack));
        }
        return fuelValue;
    }
    
    /**
	 * Adds an alloy for any Crucible
	 * @param out The result
	 * @param in The list of required items
	 */
	public static void addAlloy(ItemStack out, Object... in)
	{
		AlloyRecipes.addAlloy(out, convertList(in));
	}
	
	/**
	 * Adds an alloy with a minimal furnace level
	 * @param out The result
	 * @param level The minimal furnace level
	 * @param in The list of required items
	 */
	public static void addAlloy(ItemStack out, int level, Object... in)
	{
		AlloyRecipes.addAlloy(out, level, convertList(in));
	}
	
	
	/**
	 * Adds an alloy with a minimal furnace level
	 * @param dupe the amount of times the ratio can be added
	 * @param out The result
	 * @param level The minimal furnace level
	 * @param in The list of required items
	 */
	public static Alloy[] addRatioAlloy(int dupe, ItemStack out, int level, Object... in)
	{
		return AlloyRecipes.addRatioRecipe(out, level, convertList(in), dupe);
	}
	
	/**
	 * Adds an alloy with any smelter
	 * @param dupe the amount of times the ratio can be added
	 * @param out The result
	 * @param level The minimal furnace level
	 * @param in The list of required items
	 */
	public static Alloy[] addRatioAlloy(int dupe, ItemStack out, Object... in)
	{
		return AlloyRecipes.addRatioRecipe(out, 0, convertList(in), dupe);
	}
	
	/**
	 * Adds a custom alloy
	 * @param alloy the Alloy to add
	 * Use this if you want your alloy to have special properties
	 * @see Alloy
	 */
	public static void addAlloy(Alloy alloy)
	{
		AlloyRecipes.addAlloy(alloy);
	}
	
	private static List convertList(Object[] in) 
	{
		ArrayList arraylist = new ArrayList();
        Object[] aobject = in;
        int i = in.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).copy());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("MineFantasy: Invalid alloy!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }
        
        return arraylist;
	}
	
	/**
	 * Adds a fuel for forges
	 * 
	 * @param item
	 *            the item ID to be used
	 * @param meta
	 *            the item Meta to be used
	 * @param dura
	 *            the amount of ticks said item gives
	 * @param temperature
	 *            the base temperature given
	 * @param willLight
	 *            weather the fuel lights forges(like lava)
	 */
	public static void addForgeFuel(Item item, int meta, float dura, int temperature, boolean willLight) {
		addForgeFuel(new ItemStack(item, 1, meta), dura, temperature, willLight);
	}

	/**
	 * Adds a fuel for forges
	 * 
	 * @param item
	 *            the item ID to be used
	 * @param meta
	 *            the item Meta to be used
	 * @param dura
	 *            the amount of ticks said item gives
	 * @param temperature
	 *            the base temperature given
	 */
	public static void addForgeFuel(Item item, int meta, float dura, int temperature) {
		addForgeFuel(item, meta, dura, temperature, false);
	}

	/**
	 * Adds a fuel for forges
	 * 
	 * @param item
	 *            the item to be used
	 * @param dura
	 *            the amount of ticks said item gives
	 * @param temperature
	 *            the base temperature given
	 * @param willLight
	 *            weather the fuel lights forges(like lava)
	 */
	public static void addForgeFuel(ItemStack item, float dura, int temperature, boolean willLight) {
		ForgeItemHandler.forgeFuel.add(new ForgeFuel(item, dura, temperature, willLight));
		if ((int) (temperature * 1.25) > ForgeItemHandler.forgeMaxTemp) {
			ForgeItemHandler.forgeMaxTemp = (int) (temperature * 1.25);
		}
	}

	/**
	 * Adds a fuel for forges
	 * 
	 * @param item
	 *            the item to be used
	 * @param dura
	 *            the amount of ticks said item gives
	 * @param temperature
	 *            the base temperature given
	 * @param willLight
	 *            weather the fuel lights forges(like lava)
	 */
	public static void addForgeFuel(Item id, float dura, int temperature, boolean willLight) {
		addForgeFuel(new ItemStack(id, 2, OreDictionary.WILDCARD_VALUE), dura, temperature, willLight);
	}

	/**
	 * Adds a fuel for forges
	 * 
	 * @param item
	 *            the item to be used
	 * @param dura
	 *            the amount of ticks said item gives
	 * @param temperature
	 *            the base temperature given
	 */
	public static void addForgeFuel(ItemStack item, float dura, int temperature) {
		addForgeFuel(item, dura, temperature, false);
	}

	/**
	 * Adds a fuel for forges
	 * 
	 * @param item
	 *            the item to be used
	 * @param dura
	 *            the amount of ticks said item gives
	 * @param temperature
	 *            the base temperature given
	 */
	public static void addForgeFuel(Item id, float dura, int temperature) {
		addForgeFuel(id, dura, temperature, false);
	}

	/**
	 * Allows an item to be heated
	 * 
	 * @param item
	 *            the item to heat
	 * @param min
	 *            the minimum heat to forge with(celcius)
	 * @param max
	 *            the maximum heat until the item is ruined(celcius)
	 */
	public static void addHeatableItem(ItemStack item, int min, int unstable, int max) {
		Heatable.addItem(item, min, unstable, max);
	}

	/**
	 * Allows an item to be heated ignoring subId
	 * 
	 * @param id
	 *            the item to heat
	 * @param min
	 *            the minimum heat to forge with(celcius)
	 * @param max
	 *            the maximum heat until the item is ruined(celcius)
	 */
	public static void addHeatableItem(Item id, int min, int unstable, int max) {
		Heatable.addItem(new ItemStack(id, 1, OreDictionary.WILDCARD_VALUE), min, unstable, max);
	}
	
	public static void addHeatableItems(String oredict, int min, int unstable, int max)
	{
		for(ItemStack item : OreDictionary.getOres(oredict))
		{
			addHeatableItem(item, min, unstable, max);
		}
	}

	private static ItemStack convertItem(Object object) 
	{
		if(object instanceof ItemStack)
		{
			return ((ItemStack)object);
		}
		if(object instanceof Item)
		{
			return new ItemStack((Item)object, 1, OreDictionary.WILDCARD_VALUE);
		}
		if(object instanceof Block)
		{
			return new ItemStack((Block)object, 1, OreDictionary.WILDCARD_VALUE);
		}
		return null;
	}
}
