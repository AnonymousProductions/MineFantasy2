package minefantasy.mf2.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.material.BaseMaterialMF;

public class CarpenterRecipes
{
	private static final String basic = "step.wood";
	private static final String chopping = "dig.wood";
	private static final String engineering = "engineering";
	private static final String sewing = "step.cloth";
	private static final String carving = "dig.stone";
	private static final String sawing = "step.gravel";
	private static final String mallet = "minefantasy2:block.carpentermallet";
	private static final String mixing = "step.wood";
	
	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(BlockListMF.carpenter), new Object[]{
			"WW",
			"PP",
			'W', Blocks.planks,
			'P', ComponentListMF.plank,
		});
		addWoodworks();
		addStonemason();
		addCooking();
		addMisc();
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.pickStone), engineering, 24 , new Object[]
		{
			"V I",
			"SSI",
			"V I",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.axeStone), engineering, 20 , new Object[]
		{
			"VII",
			"SSI",
			"V  ",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.spadeStone), engineering, 10 , new Object[]
		{
			"V  ",
			"SSI",
			"V  ",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.hoeStone), engineering, 16 , new Object[]
		{
			"V I",
			"SSI",
			"V  ",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.hammerStone), engineering, 12 , new Object[]
		{
			"I",
			"V",
			"S",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.tongsStone), engineering, 15 , new Object[]
		{
			" I ",
			"SVI",
			" S ",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.knifeStone), engineering, 18 , new Object[]
		{
			"I ",
			"I ",
			"SV",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.needleBone), carving, "knife", -1, 10 , new Object[]
		{
			"B",
			'B', Items.bone
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.swordStone), engineering, 40 , new Object[]
		{
			"VI  ",
			"SIII",
			"VI  ",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.waraxeStone), engineering, 30 , new Object[]
		{
			"VII",
			"SSI",
			"V I",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.maceStone), engineering, 35 , new Object[]
		{
			"V II",
			"SSII",
			"V   ",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.spearStone), engineering, 20 , new Object[]
		{
			" V   ",
			"SSSSI",
			" V   ",
			'V', ComponentListMF.vine,
			'S', ComponentListMF.plank,
			'I', ComponentListMF.sharp_rock,
		});
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.swordTraining), carving, "knife", 1, 40 , new Object[]
		{
			" I   ",
			"SIIII",
			" I   ",
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.waraxeTraining), carving, "knife", 1, 30 , new Object[]
		{
			" II",
			"SSI",
			"  I",
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.maceTraining), carving, "knife", 1, 35 , new Object[]
		{
			"  II",
			"SSII",
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.spearTraining), carving, "knife", 1, 20 , new Object[]
		{
			"SSSSI",
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_crude, 2), sewing, "needle", -1, 10 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideSmall,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_crude, 4), sewing, "needle", -1, 20 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideMedium,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_crude, 6), sewing, "needle", -1, 30 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideLarge,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_wool, 4), sewing, "needle", 1, 10 , new Object[]
		{
			"CCC",
			'C', Blocks.wool,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_tough), sewing, "needle", 2, 20 , new Object[]
		{
			"L",
			"B",
			'L', Items.leather,
			'B', ToolListMF.bandage_wool
		});
		
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.padding), sewing, "needle", 2, 50 , new Object[]
		{
			" L ",
			"FFF",
			" C ",
			'L', Items.leather,
			'F', Items.feather,
			'C', Blocks.wool
		});
		
		MineFantasyAPI.addShapelessCarpenterRecipe(new ItemStack(ComponentListMF.fireclay), basic, 2 , new Object[]
		{
			ComponentListMF.kaolinite_dust,
			Items.clay_ball
		});
		MineFantasyAPI.addShapelessCarpenterRecipe(new ItemStack(ComponentListMF.fireclay_brick), basic, 3 , new Object[]
		{
			ComponentListMF.fireclay
		});
		
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 2, 0), sewing, "needle", 2, 50 , new Object[]
		{
			" U ",
			"UPU",
			" U ",
			'P', Items.leather_helmet,
			'U', Items.leather
		});
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 2, 1), sewing, "needle", 2, 80 , new Object[]
		{
			" U ",
			"UPU",
			" U ",
			'P', Items.leather_chestplate,
			'U', Items.leather
		});
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 2, 2), sewing, "needle", 2, 70 , new Object[]
		{
			" U ",
			"UPU",
			" U ",
			'P', Items.leather_leggings,
			'U', Items.leather
		});
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 2, 3), sewing, "needle", 2, 40 , new Object[]
		{
			" U ",
			"UPU",
			" U ",
			'P', Items.leather_boots,
			'U', Items.leather
		});
		
		
		MineFantasyAPI.addShapelessCarpenterRecipe(new ItemStack(ComponentListMF.sharp_rock, 9), mallet, "hammer", -1, 5 , new Object[]
		{
			Blocks.cobblestone
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.cobblestone), engineering, 5 , new Object[]
		{
			"RRR",
			"RRR",
			"RRR",
			'R', ComponentListMF.sharp_rock
		});
	}

	private static void addWoodworks()
	{
		for(ItemStack planks : OreDictionary.getOres("plankWood"))
		{
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(Items.bowl, 16), carving, "knife", -1, 60, new Object[]{
				"W W",
				" W ",
				'W', planks,
			});
			
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.plank, 4), sawing, "saw", -1, 25, new Object[]{
				"W",
				'W', planks,
			});
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.malletWood), basic, 15, new Object[]
			{
				"W",
				"P",
				
				'P', ComponentListMF.plank,
				'W', planks,
			});
		}
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.spoonWood), carving, "knife", -1, 15, new Object[]
		{
			"W",
			"P",
			
			'W', ComponentListMF.plank,
			'P', Items.stick,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.chest), mallet, "mallet", -1, 6, new Object[]{
			"WWW",
			"W W",
			"WWW",
			'W', ComponentListMF.plank,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Items.wooden_door), mallet, "mallet", -1, 12, new Object[]{
			"WW",
			"WW",
			"WW",
			'W', ComponentListMF.plank,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.trapdoor), mallet, "mallet", -1, 8, new Object[]{
			"WW",
			'W', ComponentListMF.plank,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.ladder, 16), engineering, 10, new Object[]{
			"W W",
			"WWW",
			"W W",
			'W', ComponentListMF.plank,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.fence, 4), mallet, "mallet", -1, 5, new Object[]{
			"WWW",
			"WWW",
			'W', ComponentListMF.plank,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.fence_gate), mallet, "mallet", -1, 10, new Object[]{
			"WFW",
			'W', ComponentListMF.plank,
			'F', Blocks.fence,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Items.bed), mallet, "mallet", -1, 20, new Object[]{
			"CCC",
			"WWW",
			'W', ComponentListMF.plank,
			'C', Blocks.wool,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.noteblock), engineering, "mallet", -1, 10, new Object[]{
			"WWW",
			"WRW",
			"WWW",
			'W', ComponentListMF.plank,
			'R', Items.redstone,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.jukebox), engineering, "mallet", -1, 20, new Object[]{
			"WWW",
			"WRW",
			"WNW",
			'W', ComponentListMF.plank,
			'R', Items.repeater,
			'N', Blocks.noteblock,
		});
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.torch, 8), carving, "knife", 2, 16, new Object[]{
			"C",
			"W",
			'C', Items.coal,
			'W', ComponentListMF.plank,
		});
	}
	
	private static void addStonemason()
	{
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.furnace), mallet, "hammer", -1, 10, new Object[]{
			"WWW",
			"W W",
			"WWW",
			'W', Blocks.cobblestone,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.anvil[0]), basic, "hammer", -1, 50, new Object[]{
			"WW ",
			"WWW",
			" W ",
			'W', Blocks.cobblestone,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.dispenser), engineering, "hammer", 2, 60, new Object[]{
			"CCC",
			"CBC",
			"CRC",
			'C', Blocks.cobblestone,
			'R', Items.redstone,
			'B', Items.string
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Blocks.dropper), engineering, "hammer", 2, 60, new Object[]{
			"CCC",
			"C C",
			"CRC",
			'C', Blocks.cobblestone,
			'R', Items.redstone,
			'B', Items.string
		});
	}
	
	private static void addCooking()
	{
		String meats = "isMeatCooked";
		OreDictionary.registerOre(meats, Items.cooked_beef);
		OreDictionary.registerOre(meats, Items.cooked_chicken);
		OreDictionary.registerOre(meats, Items.cooked_porkchop);
		OreDictionary.registerOre(meats, FoodListMF.wolf_cooked);
		OreDictionary.registerOre(meats, FoodListMF.horse_cooked);
		OreDictionary.registerOre(meats, Items.cooked_fished);
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.oats), chopping, "knife", -1, 40, new Object[]{
			"M",
			"B",
			'M', Items.wheat,
			'B', Items.bowl
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.breadroll, 4), chopping, "knife", -1, 30, new Object[]{
			"M",
			'M', Items.bread,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.sweetroll_raw), basic, 12, new Object[]{
			" S ",
			"BEB",
			" F ",
			'S', Items.sugar,
			'B', FoodListMF.berries,
			'E', Items.egg,
			'F', FoodListMF.flour,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.icing), mixing, "spoon", -1, 10, new Object[]{
			"S",
			"B",
			'S', Items.sugar,
			'B', Items.bowl,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.sweetroll), basic, "knife", -1, 20, new Object[]{
			"I",
			"R",
			'I', FoodListMF.icing,
			'R', FoodListMF.sweetroll_uniced,
		});
		for(ItemStack food: OreDictionary.getOres(meats))
		{
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.stew), chopping, "knife", -1, 40, new Object[]{
				"M",
				"B",
				'M', food,
				'B', Items.bowl
			});
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.jerky, 2), chopping, "knife", 2, 100, new Object[]{
				"M",
				'M', food,
			});
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pie_meat_uncooked), chopping, "knife", 2, 300, new Object[]{
				"FFF",
				"MMM",
				"FEF",
				" T ",
				'F', FoodListMF.flour,
				'E', Items.egg,
				'M', food,
				'T', FoodListMF.pie_tray,
			});
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.sandwitch_meat), chopping, "knife", 2, 200, new Object[]{
				"B",
				"M",
				"B",
				'M', food,
				'B', FoodListMF.breadroll
			});
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pie_shepard_uncooked), chopping, "knife", 3, 500, new Object[]{
				"PPP",
				"MMM",
				"FEF",
				" T ",
				'P', Items.potato,
				'F', FoodListMF.flour,
				'E', Items.egg,
				'M', food,
				'T', FoodListMF.pie_tray,
			});
		}
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pie_apple_uncooked), chopping, "knife", 2, 300, new Object[]{
			"FSF",
			"MMM",
			"FEF",
			" T ",
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', Items.apple,
			'S', Items.sugar,
			'T', FoodListMF.pie_tray,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pie_berry_uncooked), chopping, "knife", 2, 300, new Object[]{
			"FSF",
			"MMM",
			"FEF",
			" T ",
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', FoodListMF.berries,
			'S', Items.sugar,
			'T', FoodListMF.pie_tray,
		});
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cake_raw), mixing, "spoon", -1, 20, new Object[]{
			"SMS",
			"SES",
			"FFF",
			" T ",
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', Items.milk_bucket,
			'S', Items.sugar,
			'T', FoodListMF.cake_tin,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cake_carrot_raw), mixing, "spoon", -1, 25, new Object[]{
			"SMS",
			"SES",
			"CCC",
			"FTF",
			'C', Items.carrot,
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', Items.milk_bucket,
			'S', Items.sugar,
			'T', FoodListMF.cake_tin,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cake_choc_raw), mixing, "spoon", -1, 25, new Object[]{
			"SMS",
			"SES",
			"CCC",
			"FTF",
			'C', new ItemStack(Items.dye, 1, 3),//ItemDye
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', Items.milk_bucket,
			'S', Items.sugar,
			'T', FoodListMF.cake_tin,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cake_bf_raw), mixing, "spoon", -1, 30, new Object[]{
			"SMMS",
			"SEES",
			"CBBC",
			"FTFF",
			'B', FoodListMF.berriesJuicy,
			'C', new ItemStack(Items.dye, 1, 3),//ItemDye
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', Items.milk_bucket,
			'S', Items.sugar,
			'T', FoodListMF.cake_tin,
		});
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.cake_vanilla), basic, "knife", -1, 60, new Object[]{
			"III",
			" R ",
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_uniced,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.cake_carrot), basic, "knife", -1, 60, new Object[]{
			"III",
			" R ",
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_carrot_uniced,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.cake_chocolate), basic, "knife", -1, 60, new Object[]{
			"ICI",
			" R ",
			'C', new ItemStack(Items.dye, 1, 3),
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_carrot_uniced,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.cake_bf), basic, "knife", -1, 100, new Object[]{
			"BBB",
			"III",
			"CRC",
			'C', new ItemStack(Items.dye, 1, 3),
			'B', FoodListMF.berries,
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_bf_uniced,
		});
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cheese_roll), chopping, "knife", 1, 100, new Object[]{
			"C",
			"R",
			'C', FoodListMF.cheese_slice,
			'R', FoodListMF.breadroll,
		});
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.flour), chopping, 1, new Object[]{
			"C",
			'C', Items.wheat,
		});
	}
	
	private static void addMisc()
	{
		//Fletching
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.fletching, 16), chopping, 4, new Object[]
		{
			"PFP",
			'F', Items.feather,
			'P', Items.paper,
		});
		
		for(int id = 0; id < ToolListMF.weaponMats.length; id ++)
		{
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.arrows[id]), chopping, 1, new Object[]
			{
				"H",
				"S",
				"F",
				'H', ComponentListMF.arrowheads[id],
				'S', Items.stick,
				'F', ComponentListMF.fletching,
			});
		}
		
		//BOMBS
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.bomb_casing_uncooked, 2), basic, 2, new Object[]
		{
			" C ",
			"C C",
			" C ",
			'C', Items.clay_ball,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.mine_casing_uncooked), basic, 2, new Object[]
		{
			" P ",
			"C C",
			" C ",
			
			'P', Blocks.stone_pressure_plate,
			'C', Items.clay_ball,
		});
		
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.bomb_fuse, 8), basic, 4, new Object[]
		{
			"R",
			"C",
			"S",
			'S', Items.string,
			'C', ComponentListMF.coalDust,
			'R', Items.redstone,
		});
	}
}
