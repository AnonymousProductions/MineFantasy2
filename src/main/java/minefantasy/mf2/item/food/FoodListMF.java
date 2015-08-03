package minefantasy.mf2.item.food;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.ItemComponentMF;
import minefantasy.mf2.item.ItemMFBowl;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class FoodListMF
{
	//MORSELS
	public static Item wolf_raw = (new ItemFoodMF("wolf_raw", 2, 0.2F, true));
	public static Item wolf_cooked = (new ItemFoodMF("wolf_cooked", 6, 0.6F, true));
	public static Item horse_raw = (new ItemFoodMF("horse_raw", 4, 0.4F, true)).setPotionEffect(Potion.hunger.id, 50, 0, 0.5F);
	public static Item horse_cooked = (new ItemFoodMF("horse_cooked", 10, 1.0F, true));
	
	public static Item generic_meat_uncooked = (new ItemFoodMF("generic_meat_uncooked", 2, 0.2F, true));
	public static Item generic_meat_cooked = (new ItemFoodMF("generic_meat_cooked", 	5, 0.5F, true));
	public static Item generic_meat_strip_uncooked = (new ItemFoodMF("generic_meat_strip_uncooked", 2, 0.2F, true));
	public static Item generic_meat_strip_cooked = (new ItemFoodMF("generic_meat_strip_cooked", 	5, 0.5F, true));
	public static Item generic_meat_chunk_uncooked = (new ItemFoodMF("generic_meat_chunk_uncooked", 2, 0.2F, true));
	public static Item generic_meat_chunk_cooked = (new ItemFoodMF("generic_meat_chunk_cooked", 	5, 0.5F, true));
	public static Item generic_meat_mince_uncooked = (new ItemFoodMF("generic_meat_mince_uncooked", 2, 0.2F, true));
	public static Item generic_meat_mince_cooked = (new ItemFoodMF("generic_meat_mince_cooked", 	5, 0.5F, true));
	
	public static Item tea_leaf = (new ItemFoodMF("tea_leaf", 1, 0F, false)).setStaminaModifier(10, 1);
	public static Item spice_mild = (new ItemFoodMF("spice_mild", 1, 0F, false));
	public static Item flour = new ItemComponentMF("flour", 0).setCreativeTab(CreativeTabMF.tabFood);
	
	public static Item breadroll = (new ItemFoodMF("breadroll", 5, 1.0F, false)).setMaxStackSize(8);
	
	public static Item curds = new ItemUnfinishedFood("curds");
	public static Item cheese_slice = (new ItemFoodMF("cheese_slice",       6, 1.0F, 2F, false, 0)).setMaxStackSize(1);
	
	//T1 (basic mixing)
	//Util: Roast, Prep Block (Stone-Bronze Age)
	public static Item stew = (new ItemFoodMF("stew"      , 10, 1.0F, 5F, false, 0)).setStaminaModifier	  (10F, 1.0F).setReturnItem(Items.bowl).setMaxStackSize(1);
	public static Item salad = (new ItemFoodMF("salad"  , 6,  1.0F, 3F, false, 0)).setStaminaModifier (20F, 1.0F).setReturnItem(Items.bowl).setMaxStackSize(1);
	public static Item oats = (new ItemFoodMF("oats"    , 6, 1.0F, 4F, false, 0)).setStaminaModifier (15F, 1.0F).setReturnItem(Items.bowl).setMaxStackSize(1);
	
	//T2 (Basic baking, stone oven, processed mixing)
	//Util: Stone Oven, Prep Block (Bronze Age - Early Iron Age)
	public static Item cheese_roll = (new ItemFoodMF("cheese_roll",10, 1.0F, 10F, false, 0)).setStaminaModifier (25F, 1.0F).setMaxStackSize(1);
	public static Item jerky = (new ItemFoodMF("jerky",            8,  1.0F, 5F, false, 0)).setStaminaModifier (20F, 0.5F).setStaminaRegenModifier(2, 5F).setMaxStackSize(8);
	public static Item sweetroll_uniced = (new ItemFoodMF("sweetroll_uniced", 1,  1.0F, 2F, false, 0)).setStaminaRegenModifier (5F, 1F).setStaminaRestore(5F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	public static Item sweetroll = (new ItemMultiFood("sweetroll",                2, 2,  1.0F, 2F, 0)).setStaminaRegenModifier (10F, 1.5F).setStaminaRestore(10F).setEatTime(16).setAlwaysEdible();
	
	public static Item sandwitch_meat = (new ItemMultiFood("sandwitch_meat",       2, 10, 1.0F, 10F, 1)).setStaminaModifier(20F, 1.0F);
	
	//T3 (Quality baking, metal oven)
	//Util: Metal Oven, Prep Block, Steel Tools (Mid Iron Age)
	public static Item pieslice_meat = (new ItemFoodMF("pieslice_meat",        10, 1.0F, 15F, false, 0)).setStaminaModifier(50F, 1.0F).setMaxStackSize(1);
	
	public static Item pieslice_apple = (new ItemFoodMF("pieslice_apple",      5,  1.0F, 1F, false, 0)).setStaminaRegenModifier(8, 3F).setStaminaRestore(20F).setEatTime(16).setAlwaysEdible().setAlwaysEdible().setMaxStackSize(1);
	public static Item pieslice_berry = (new ItemFoodMF("pieslice_berry",      4 , 1.0F, 1F, false, 0)).setStaminaRegenModifier(10, 2.5F).setStaminaRestore(15F).setEatTime(16).setAlwaysEdible().setAlwaysEdible().setMaxStackSize(1);
	
	//T4 (Advanced baking, multiple processes, temperature regulation)
	//Util : Metal Oven, Prep Block, Full tool set, Proper kitchen setup (Mid Iron Age)
	public static Item pieslice_shepards = (new ItemFoodMF("pieslice_shepards",       10, 1.0F, 15F, false, 1)).setStaminaModifier(100F, 1.0F).setMaxStackSize(1);
	
	public static Item cake_slice = (new ItemFoodMF("cake_slice",            2, 0.8F, 2F, false, 0)).setStaminaRegenModifier(10F, 4F).setStaminaRestore(20F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	public static Item carrotcake_slice = (new ItemFoodMF("carrotcake_slice",2, 0.8F, 2F, false, 0)).setStaminaRegenModifier(10F, 4F).setStaminaRestore(40F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	public static Item choccake_slice = (new ItemFoodMF("choccake_slice",    2, 0.8F, 2F, false, 0)).setStaminaRegenModifier(20F, 2F).setStaminaRestore(50F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	
	//T5 (Advanced baking, multiple process, temperature regulation, many ingreedients)
	
	//T6 (Perfeted meals, extremely difficylt to create)
	public static Item bfcake_slice = (new ItemFoodMF("bfcake_slice",      4, 1.0F, 2F, false, 1)).setStaminaRegenModifier(20F, 4F).setStaminaRestore(100F).setEatTime(16).setAlwaysEdible().setMaxStackSize(1);
	
	//MISC
	public static Item cake_tin = new ItemComponentMF("cake_tin", 0).setCreativeTab(CreativeTabMF.tabFood);
	public static Item pie_tray = new ItemComponentMF("pie_tin", 0).setCreativeTab(CreativeTabMF.tabFood);
	public static Item icing = new ItemComponentMF("icing", 0).setCreativeTab(CreativeTabMF.tabFood).setContainerItem(Items.bowl);
	public static Item berries = (new ItemFoodMF("berries", 2, 2.0F, false)).setEatTime(10).setStaminaRestore(10F).setAlwaysEdible();
	public static Item berriesJuicy = (new ItemFoodMF("berriesJuicy", 3, 5.0F, false)).setEatTime(10).setStaminaRestore(25F).setRarity(1).setAlwaysEdible();
	
	public static Item sweetroll_raw = new ItemUnfinishedFood("sweetroll_raw");
	public static Item cake_raw = new ItemUnfinishedFood("cake_raw");
	public static Item cake_carrot_raw = new ItemUnfinishedFood("cake_carrot_raw");
	public static Item cake_choc_raw = new ItemUnfinishedFood("cake_choc_raw");
	public static Item cake_bf_raw = new ItemUnfinishedFood("cake_bf_raw");
	
	public static Item cake_uniced = new ItemUnfinishedFood("cake_uniced").setContainerItem(cake_tin);
	public static Item cake_carrot_uniced = new ItemUnfinishedFood("cake_carrot_uniced").setContainerItem(cake_tin);
	public static Item cake_choc_uniced = new ItemUnfinishedFood("cake_choc_uniced").setContainerItem(cake_tin);
	public static Item cake_bf_uniced = new ItemUnfinishedFood("cake_bf_uniced").setContainerItem(cake_tin);
	
	public static Item pie_meat_uncooked = new ItemUnfinishedFood("pie_meat_uncooked");
	
	public static Item pie_apple_uncooked = new ItemUnfinishedFood("pie_apple_uncooked");
	public static Item pie_berry_uncooked = new ItemUnfinishedFood("pie_berry_uncooked");
	public static Item pie_shepard_uncooked = new ItemUnfinishedFood("pie_shepard_uncooked");
	
	public static Item pie_meat_cooked = new ItemUnfinishedFood("pie_meat_cooked").setContainerItem(pie_tray);
	
	public static Item pie_apple_cooked = new ItemUnfinishedFood("pie_apple_cooked").setContainerItem(pie_tray);
	public static Item pie_berry_cooked = new ItemUnfinishedFood("pie_berry_cooked").setContainerItem(pie_tray);
	public static Item pie_shepard_cooked = new ItemUnfinishedFood("pie_shepard_cooked").setContainerItem(pie_tray);
	
	public static Item hard_bowl = new ItemMFBowl("hard_bowl").setCreativeTab(CreativeTabMF.tabFood);
	public static Item salt = new ItemComponentMF("salt", 0).setCreativeTab(CreativeTabMF.tabFood).setContainerItem(hard_bowl);
	public static Item bowl_water_salt = new ItemComponentMF("bowl_water_salt", 0).setCreativeTab(CreativeTabMF.tabFood);
	
	public static Item dough = new ItemUnfinishedFood("dough");
	public static Item pastry = new ItemUnfinishedFood("pastry");
	public static Item raw_bread = new ItemUnfinishedFood("raw_bread");
	//SPECIAL RECIPES
	
	private static Item[] foods ={wolf_cooked,wolf_raw,horse_raw,horse_cooked,generic_meat_uncooked,generic_meat_cooked,generic_meat_cooked,generic_meat_strip_uncooked,generic_meat_strip_cooked,generic_meat_chunk_uncooked,generic_meat_chunk_cooked,generic_meat_mince_uncooked,generic_meat_mince_cooked,tea_leaf,spice_mild,flour,breadroll,curds,cheese_slice,stew,salad,oats,cheese_roll,jerky,sweetroll_uniced,sweetroll,sandwitch_meat,pieslice_meat,pieslice_apple,pieslice_berry,pieslice_shepards,cake_slice,carrotcake_slice,choccake_slice,bfcake_slice,cake_tin,pie_tray,icing,berries,berriesJuicy,sweetroll_raw,cake_raw,cake_carrot_raw,cake_choc_raw,cake_bf_raw,cake_uniced,cake_carrot_uniced,cake_choc_uniced,cake_bf_uniced,pie_meat_uncooked,pie_apple_uncooked,pie_berry_uncooked,pie_shepard_uncooked,pie_meat_cooked,pie_apple_cooked,pie_berry_cooked,pie_shepard_cooked,hard_bowl,salt,bowl_water_salt,dough,pastry,raw_bread};
	
	public static void init(FMLInitializationEvent event)
	{
		if(event.getSide() == Side.CLIENT)
    	{
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
			String MODID = MineFantasyII.MODID;
			
			for (Item food : foods) {
	    		renderItem.getItemModelMesher().register(food, 0, new ModelResourceLocation(MODID + ":" + food.getUnlocalizedName(), "inventory"));
	    		}
    	}
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
