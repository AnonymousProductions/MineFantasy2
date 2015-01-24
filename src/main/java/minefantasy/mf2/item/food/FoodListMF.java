package minefantasy.mf2.item.food;

import minefantasy.mf2.item.ItemBandage;
import minefantasy.mf2.item.ItemComponentMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class FoodListMF
{
	//MORSELS
	public static Item wolf_raw = (ItemFoodMF)(new ItemFoodMF("wolf_raw", 2, 0.2F, true));
	public static Item wolf_cooked = (ItemFoodMF)(new ItemFoodMF("wolf_cooked", 6, 0.6F, true));
	public static Item horse_raw = (ItemFoodMF)(new ItemFoodMF("horse_raw", 4, 0.4F, true)).setPotionEffect(Potion.hunger.id, 50, 0, 0.5F);
	public static Item horse_cooked = (ItemFoodMF)(new ItemFoodMF("horse_cooked", 10, 1.0F, true));
	
	public static Item tea_leaf = (ItemFoodMF)(new ItemFoodMF("tea_leaf", 1, 0F, false)).setStaminaModifier(10, 1);
	public static Item spice_mild = (ItemFoodMF)(new ItemFoodMF("spice_mild", 1, 0F, false));
	public static Item flour = new ItemComponentMF("flour", 0).setCreativeTab(CreativeTabMF.tabFood);
	
	public static Item breadroll = (ItemFoodMF)(new ItemFoodMF("breadroll", 5, 1.0F, false)).setMaxStackSize(1);
	
	public static Item cheese_slice = (ItemFoodMF)(new ItemFoodMF("cheese_slice",       6, 1.0F, 2F, false, 0)).setMaxStackSize(1);
	
	//T1 (basic mixing)
	//Util: Roast, Prep Block (Stone-Bronze Age)
	public static Item stew = (ItemFoodMF)(new ItemFoodMF("stew"      , 10, 1.0F, 5F, false, 0)).setStaminaModifier	  (10F, 1.0F).setReturnItem(Items.bowl).setMaxStackSize(1);
	public static Item salad = (ItemFoodMF)(new ItemFoodMF("salad"  , 6,  1.0F, 3F, false, 0)).setStaminaModifier (20F, 1.0F).setReturnItem(Items.bowl).setMaxStackSize(1);
	public static Item oats = (ItemFoodMF)(new ItemFoodMF("oats"    , 6, 1.0F, 4F, false, 0)).setStaminaModifier (15F, 1.0F).setReturnItem(Items.bowl).setMaxStackSize(1);
	
	//T2 (Basic baking, stone oven, processed mixing)
	//Util: Stone Oven, Prep Block (Bronze Age - Early Iron Age)
	public static Item cheese_roll = (ItemFoodMF)(new ItemFoodMF("cheese_roll",10, 1.0F, 10F, false, 0)).setStaminaModifier (25F, 1.0F).setMaxStackSize(1);
	public static Item jerky = (ItemFoodMF)(new ItemFoodMF("jerky",            8,  1.0F, 5F, false, 0)).setStaminaModifier (20F, 0.5F).setStaminaRegenModifier(2, 5F).setMaxStackSize(8);
	public static Item sweetroll_uniced = (ItemFoodMF) (new ItemFoodMF("sweetroll_uniced", 1,  1.0F, 2F, false, 0)).setStaminaRegenModifier (5F, 1F).setStaminaRestore(5F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	public static Item sweetroll = (ItemFoodMF) (new ItemMultiFood("sweetroll",                2, 2,  1.0F, 2F, 0)).setStaminaRegenModifier (10F, 1.5F).setStaminaRestore(10F).setEatTime(16).setAlwaysEdible();
	
	public static Item sandwitch_meat = (ItemFoodMF)(new ItemMultiFood("sandwitch_meat",       2, 10, 1.0F, 10F, 1)).setStaminaModifier(20F, 1.0F).setTextureName("minefantasy2:food/sandwitch");
	
	//T3 (Quality baking, metal oven)
	//Util: Metal Oven, Prep Block, Steel Tools (Mid Iron Age)
	public static Item meatpie_slice = (ItemFoodMF)(new ItemFoodMF("meatpie_slice",        10, 1.0F, 15F, false, 0)).setStaminaModifier(50F, 1.0F).setMaxStackSize(1);
	
	public static Item pieslice_apple = (ItemFoodMF)(new ItemFoodMF("pieslice_apple",      5,  1.0F, 1F, false, 0)).setStaminaRegenModifier(8, 3F).setStaminaRestore(20F).setEatTime(16).setAlwaysEdible().setAlwaysEdible().setTextureName("minefantasy2:food/applepie_slice").setMaxStackSize(1);
	public static Item pieslice_berry = (ItemFoodMF)(new ItemFoodMF("pieslice_berry",      4 , 1.0F, 1F, false, 0)).setStaminaRegenModifier(10, 2.5F).setStaminaRestore(15F).setEatTime(16).setAlwaysEdible().setAlwaysEdible().setTextureName("minefantasy2:food/berrypie_slice").setMaxStackSize(1);
	
	//T4 (Advanced baking, multiple processes, temperature regulation)
	//Util : Metal Oven, Prep Block, Full tool set, Proper kitchen setup (Mid Iron Age)
	public static Item pieslice_shepards = (ItemFoodMF)(new ItemFoodMF("pieslice_shepards",       10, 1.0F, 15F, false, 1)).setStaminaModifier(100F, 1.0F).setTextureName("minefantasy2:food/shepardspie_slice").setMaxStackSize(1);
	
	public static Item cake_slice = (ItemFoodMF)(new ItemFoodMF("cake_slice",            2, 0.8F, 2F, false, 0)).setStaminaRegenModifier(10F, 4F).setStaminaRestore(20F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	public static Item carrotcake_slice = (ItemFoodMF)(new ItemFoodMF("carrotcake_slice",2, 0.8F, 2F, false, 0)).setStaminaRegenModifier(10F, 4F).setStaminaRestore(40F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	public static Item choccake_slice = (ItemFoodMF)(new ItemFoodMF("choccake_slice",    2, 0.8F, 2F, false, 0)).setStaminaRegenModifier(20F, 2F).setStaminaRestore(50F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	
	//T5 (Advanced baking, multiple process, temperature regulation, many ingreedients)
	
	//T6 (Perfeted meals, extremely difficylt to create)
	public static Item bfcake_slice = (ItemFoodMF)(new ItemFoodMF("bfcake_slice",      4, 1.0F, 2F, false, 1)).setStaminaRegenModifier(20F, 4F).setStaminaRestore(100F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	
	//MISC
	public static Item cake_tin = new ItemComponentMF("cake_tin", 0).setCreativeTab(CreativeTabMF.tabFood);
	public static Item pie_tray = new ItemComponentMF("pie_tin", 0).setCreativeTab(CreativeTabMF.tabFood);
	public static Item icing = new ItemComponentMF("icing", 0).setCreativeTab(CreativeTabMF.tabFood).setContainerItem(Items.bowl);
	public static Item berries = (ItemFoodMF)(new ItemFoodMF("berries", 2, 2.0F, false)).setEatTime(10).setStaminaRestore(10F).setAlwaysEdible();
	public static Item berriesJuicy = (ItemFoodMF)(new ItemFoodMF("berriesJuicy", 3, 5.0F, false)).setEatTime(10).setStaminaRestore(25F).setRarity(1).setAlwaysEdible();
	
	public static Item sweetroll_raw = new ItemUnfinishedFood("sweetroll_raw");
	public static Item cake_raw = new ItemUnfinishedFood("cake_raw");
	public static Item cake_carrot_raw = new ItemUnfinishedFood("cake_carrot_raw");
	public static Item cake_choc_raw = new ItemUnfinishedFood("cake_choc_raw");
	public static Item cake_bf_raw = new ItemUnfinishedFood("cake_bf_raw");
	
	public static Item cake_uniced = new ItemUnfinishedFood("cake_uniced").setContainerItem(cake_tin);
	public static Item cake_carrot_uniced = new ItemUnfinishedFood("cake_carrot_uniced").setContainerItem(cake_tin);
	public static Item cake_choc_uniced = new ItemUnfinishedFood("cake_choc_uniced").setContainerItem(cake_tin);
	public static Item cake_bf_uniced = new ItemUnfinishedFood("cake_bf_uniced").setContainerItem(cake_tin);
	
	public static Item pie_meat_uncooked = new ItemUnfinishedFood("pie_meat_uncooked").setTextureName("minefantasy2:food/unfinished/pie_meat_uncooked");
	
	public static Item pie_apple_uncooked = new ItemUnfinishedFood("pie_apple_uncooked");
	public static Item pie_berry_uncooked = new ItemUnfinishedFood("pie_berry_uncooked");
	public static Item pie_shepard_uncooked = new ItemUnfinishedFood("pie_shepard_uncooked");
	
	public static Item pie_meat_cooked = new ItemUnfinishedFood("pie_meat_cooked").setTextureName("minefantasy2:food/unfinished/pie_meat_cooked").setContainerItem(pie_tray);
	
	public static Item pie_apple_cooked = new ItemUnfinishedFood("pie_apple_cooked").setContainerItem(pie_tray);
	public static Item pie_berry_cooked = new ItemUnfinishedFood("pie_berry_cooked").setContainerItem(pie_tray);
	public static Item pie_shepard_cooked = new ItemUnfinishedFood("pie_shepard_cooked").setContainerItem(pie_tray);
	
	//SPECIAL RECIPES
	
	public static void init()
	{
	}
	/**
	 * FOOD TYPES:
	 * Sugar: Stamina Regen
	 * Dairy: Saturation
	 * Grain: Stamina Max
	 * 
	 * Protien: Saturation / Stamina Max
	 * Fruit: Stamina Max / Stamina Speed
	 * Vegetable: Stamina Speed / Saturation
	 */

}
