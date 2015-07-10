package minefantasy.mf2.recipe;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class CarpenterRecipes
{
	private static final String basic = "step.wood";
	private static final String chopping = "dig.wood";
	//private static final String engineering = "engineering";
	private static final String sewing = "step.cloth";
	private static final String stonemason = "dig.stone";
	private static final String snipping = "mob.sheep.shear";
	private static final String sawing = "minefantasy2:block.sawcarpenter";
	private static final String nailHammer = "minefantasy2:block.hammercarpenter";
	private static final String woodHammer = "minefantasy2:block.carpentermallet";
	private static final String mixing = "step.wood";
	
	public static void init()
	{
	
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.researchBook), "", sewing, "hands", -1, 1 , new Object[]
		{
			"B",
			'B', Items.book,
		});
		
		KnowledgeListMF.carpenterRecipe =
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.carpenter), new Object[]{
			"PBP",
			"P P",
			'B', Blocks.crafting_table,
			'P', ComponentListMF.plank,
		});
		addWoodworks();
		addStonemason();
		addCooking();
		addMisc();
		
		KnowledgeListMF.threadR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.thread, 4), "commodities", sewing, "hands", -1, 5 , new Object[]
		{
			"W",
			"S",
			'W', Blocks.wool,
			'S', Items.stick,
		});
		KnowledgeListMF.stringR = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(Items.string), "commodities", sewing, "hands", -1, 10 , new Object[]
		{
			"T",
			"T",
			"T",
			"T",
			'T', ComponentListMF.thread
		});
		
		KnowledgeListMF.lStripsR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.leather_strip, 4), "commodities", snipping, "shears", -1, 10 , new Object[]
		{
			"L",
			'L', Items.leather,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.swordTraining), nailHammer, "knife", 1, 40 , new Object[]
		{
			"NI  ",
			"SIII",
			"NI  ",
			'N', ComponentListMF.nail,
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.waraxeTraining), nailHammer, "knife", 1, 30 , new Object[]
		{
			" II ",
			"SSIN",
			"  I ",
			'N', ComponentListMF.nail,
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.maceTraining), nailHammer, "knife", 1, 35 , new Object[]
		{
			"  II ",
			"SSIIN",
			'N', ComponentListMF.nail,
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.spearTraining), nailHammer, "knife", 1, 20 , new Object[]
		{
			"  N ",
			"SSSI",
			"  N ",
			'N', ComponentListMF.nail,
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		
		KnowledgeListMF.badBandageR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_crude, 2), "bandage", sewing, "needle", -1, 10 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideSmall,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_crude, 4), "bandage", sewing, "needle", -1, 20 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideMedium,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_crude, 6), "bandage", sewing, "needle", -1, 30 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideLarge,
		});
		KnowledgeListMF.bandageR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_wool, 4), "bandage", sewing, "needle", 1, 10 , new Object[]
		{
			"CTC",
			'T', ComponentListMF.thread,
			'C', Blocks.wool,
		});
		KnowledgeListMF.goodBandageR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bandage_tough), "bandageadv", sewing, "needle", 2, 20 , new Object[]
		{
			"T",
			"L",
			"B",
			'T', ComponentListMF.thread,
			'L', ComponentListMF.leather_strip,
			'B', ToolListMF.bandage_wool
		});
		
		KnowledgeListMF.reHelmetR = 
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 1, 0),"craftArmourLight", sewing, "needle", 2, 50 , new Object[]
		{
			"TTT",
			"UPU",
			'T', ComponentListMF.thread,
			'P', Items.leather_helmet,
			'U', Items.leather
		});
		KnowledgeListMF.reChestR = 
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 1, 1),"craftArmourLight", sewing, "needle", 2, 80 , new Object[]
		{
			"TTT",
			"UPU",
			'T', ComponentListMF.thread,
			'P', Items.leather_chestplate,
			'U', Items.leather
		});
		KnowledgeListMF.reLegsR = 
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 1, 2),"craftArmourLight", sewing, "needle", 2, 70 , new Object[]
		{
			"TTT",
			"UPU",
			'T', ComponentListMF.thread,
			'P', Items.leather_leggings,
			'U', Items.leather
		});
		KnowledgeListMF.reBootsR = 
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 1, 3),"craftArmourLight", sewing, "needle", 2, 40 , new Object[]
		{
			"TTT",
			"UPU",
			'T', ComponentListMF.thread,
			'P', Items.leather_boots,
			'U', Items.leather
		});
		
		//PADDING
		KnowledgeListMF.padding[0]=
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 4, 0),"craftArmourLight", sewing, "needle", 2, 50 , new Object[]
		{
			"SWS",
			"FPF",
			"SFS",
			'P', Items.leather_helmet,
			'W', Blocks.wool,
			'F', Items.feather,
			'S', ComponentListMF.thread,
		});
		KnowledgeListMF.padding[1]=
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 4, 1),"craftArmourLight", sewing, "needle", 2, 80 , new Object[]
		{
			"SWS",
			"FPF",
			"SFS",
			'P', Items.leather_chestplate,
			'W', Blocks.wool,
			'F', Items.feather,
			'S', ComponentListMF.thread,
		});
		KnowledgeListMF.padding[2]=
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 4, 2),"craftArmourLight", sewing, "needle", 2, 70 , new Object[]
		{
			"SWS",
			"FPF",
			"SFS",
			'P', Items.leather_leggings,
			'W', Blocks.wool,
			'F', Items.feather,
			'S', ComponentListMF.thread,
		});
		KnowledgeListMF.padding[3]=
		MineFantasyAPI.addCarpenterRecipe(ArmourListMF.armour(ArmourListMF.leather, 4, 3),"craftArmourLight", sewing, "needle", 2, 40 , new Object[]
		{
			"SWS",
			"FPF",
			"SFS",
			'P', Items.leather_boots,
			'W', Blocks.wool,
			'F', Items.feather,
			'S', ComponentListMF.thread,
		});
				
		KnowledgeListMF.repairBasicR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.repair_basic), "repair_basic", sewing, "needle", 1, 20 , new Object[]
		{
			"TTT",
			"FNH",
			"SLS",
			'T', ComponentListMF.thread,
			'S', ComponentListMF.leather_strip,
			'L', Items.leather,
			'F', Items.flint,
			'H', ToolListMF.hammers[1],
			'N', ComponentListMF.nail,
		});
		KnowledgeListMF.repairAdvancedR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.repair_advanced), "repair_advanced", sewing, "needle", 2, 50 , new Object[]
		{
			"SCS",
			"PKH",
			"CSC",
			'K', BlockListMF.repair_basic,
			'P', ComponentListMF.plates[0],
			'H', ToolListMF.hammers[3],
			'C', Items.slime_ball,
			'S', Items.string,
		});
		KnowledgeListMF.repairOrnateR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.repair_ornate), "repair_ornate", sewing, "needle", 3, 100 , new Object[]
		{
			"GDG",
			"LKL",
			"GLG",
			'K', BlockListMF.repair_advanced,
			'G', Items.gold_ingot,
			'L', new ItemStack(Items.dye, 1, 4),
			'D', Items.diamond,
		});
	}

	private static void addWoodworks()
	{
		for(ItemStack wood: OreDictionary.getOres("plankWood"))
		{
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.plank, 4), "", sawing, "saw", 1, 10 , new Object[]
			{
				"W",
				'W', wood
			});
		}
		KnowledgeListMF.refinedPlankBlockR=
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.refined_planks), "", nailHammer, "hammer", 1, 10 , new Object[]
		{
			"NN",
			"PP",
			"PP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
		});
		
		KnowledgeListMF.bellowsRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.bellows), "", nailHammer, "hammer", 1, 50 , new Object[]
		{
			"NNN",
			"PPP",
			"LL ",
			"PP ",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
			'L', Items.leather,
		});
		
		KnowledgeListMF.strongRackR = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.advTanner), "", nailHammer, "hammer", 1, 80 , new Object[]
		{
			"NNN",
			"PPP",
			"P P",
			"PPP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
		});
		for(ItemStack planks : OreDictionary.getOres("plankWood"))
		{
			KnowledgeListMF.malletR =
			GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.malletWood), new Object[]
			{
				"W",
				"P",
				
				'P', ComponentListMF.plank,
				'W', planks,
			});
		}
		KnowledgeListMF.spoonR =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.spoonWood), new Object[]
		{
			"W",
			"P",
			
			'W', ComponentListMF.plank,
			'P', Items.stick,
		});
	}
	
	private static void addStonemason()
	{
		KnowledgeListMF.crucibleRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.crucible), "crucible", stonemason, "hammer", -1, 20 , new Object[]{
			"SSS",
			"S S",
			"SSS",
			'S', Blocks.stone,
		});
		KnowledgeListMF.advCrucibleRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.crucibleadv), "crucible2", stonemason, 40 , new Object[]{
			"SSS",
			"SCS",
			"SSS",
			'S', ComponentListMF.fireclay,
			'C', BlockListMF.crucible
		});
		
		KnowledgeListMF.chimneyRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.chimney_stone, 8), "", stonemason, "hammer", -1, 30 , new Object[]{
			"S S",
			"S S",
			"S S",
			'S', Blocks.stone,
		});
		KnowledgeListMF.wideChimneyRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.chimney_stone_wide), "", stonemason, "hammer", -1, 10 , new Object[]{
			"S",
			"C",
			'C', BlockListMF.chimney_stone,
			'S', Blocks.stone,
		});
		KnowledgeListMF.extractChimneyRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.chimney_stone_extractor), "", stonemason, "hammer", -1, 15 , new Object[]{
			"C",
			'C', BlockListMF.chimney_stone_wide,
		});
	}
	
	private static void addCooking()
	{
		String meats = "rawMeat";
		String meatsUC = "c";
		OreDictionary.registerOre(meats, Items.cooked_beef);
		OreDictionary.registerOre(meats, Items.cooked_chicken);
		OreDictionary.registerOre(meats, Items.cooked_porkchop);
		OreDictionary.registerOre(meats, FoodListMF.wolf_cooked);
		OreDictionary.registerOre(meats, FoodListMF.horse_cooked);
		OreDictionary.registerOre(meats, Items.cooked_fished);
		OreDictionary.registerOre(meats, new ItemStack(Items.cooked_fished, 1, 1));
		
		OreDictionary.registerOre(meatsUC, Items.beef);
		OreDictionary.registerOre(meatsUC, Items.chicken);
		OreDictionary.registerOre(meatsUC, Items.porkchop);
		OreDictionary.registerOre(meatsUC, FoodListMF.wolf_raw);
		OreDictionary.registerOre(meatsUC, FoodListMF.horse_raw);
		OreDictionary.registerOre(meatsUC, Items.fish);
		OreDictionary.registerOre(meatsUC, new ItemStack(Items.fish, 1, 1));
		
		KnowledgeListMF.curdRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.curds), "", basic, "hands", -1, 10, new Object[]{
			"T",
			"S",
			"M",
			'T', FoodListMF.salt,
			'S', Items.sugar,
			'M', Items.milk_bucket,
		});
		
		KnowledgeListMF.oatsRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.oats), "", chopping, "knife", -1, 20, new Object[]{
			"M",
			"S",
			"B",
			'S', Items.wheat_seeds,
			'M', Items.wheat,
			'B', Items.bowl
		});
		KnowledgeListMF.doughRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.dough), "", basic, "hands", -1, 10, new Object[]{
			"W",
			"F",
			'W', Items.water_bucket,
			'F', FoodListMF.flour,
		});
		KnowledgeListMF.pastryRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pastry), "", basic, "hands", -1, 10, new Object[]{
			" S ",
			"FEF",
			'F', FoodListMF.flour,
			'E', Items.egg,
			'S', FoodListMF.salt,
		});
		KnowledgeListMF.breadRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.raw_bread), "", basic, "hands", -1, 15, new Object[]{
			"DDD",
			'D', FoodListMF.dough,
		});
		KnowledgeListMF.sweetrollRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.sweetroll_raw), "sweetroll", basic, 12, new Object[]{
			" S ",
			"BEB",
			" F ",
			'S', Items.sugar,
			'B', FoodListMF.berries,
			'E', Items.egg,
			'F', FoodListMF.flour,
		});
		KnowledgeListMF.icingRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.icing), "", mixing, "spoon", -1, 10, new Object[]{
			"S",
			"B",
			'S', Items.sugar,
			'B', Items.bowl,
		});
		KnowledgeListMF.iceSR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.sweetroll), "sweetroll", basic, "knife", -1, 15, new Object[]{
			"I",
			"R",
			'I', FoodListMF.icing,
			'R', FoodListMF.sweetroll_uniced,
		});
		for(ItemStack food: OreDictionary.getOres(meatsUC))
		{
			KnowledgeListMF.meatRecipes.add(
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.generic_meat_uncooked), "", chopping, "knife", -1, 15, new Object[]{
				"M",
				'M', food,
			}));
		}
		for(ItemStack food: OreDictionary.getOres(meats))
		{
			KnowledgeListMF.meatRecipes.add(
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.generic_meat_cooked), "", chopping, "knife", -1, 15, new Object[]{
				"M",
				'M', food,
			}));
		}
		KnowledgeListMF.meatStripR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.generic_meat_strip_uncooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_uncooked,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.generic_meat_strip_cooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_cooked,
		});
		KnowledgeListMF.meatHunkR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.generic_meat_chunk_uncooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_strip_uncooked,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.generic_meat_chunk_cooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_strip_cooked,
		});
		KnowledgeListMF.minceR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.generic_meat_mince_uncooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_chunk_uncooked,
		});
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.generic_meat_mince_cooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_chunk_cooked,
		});
		
		KnowledgeListMF.stewRecipe =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.stew), "", chopping, "knife", -1, 15, new Object[]{
			"M",
			"B",
			'M', FoodListMF.generic_meat_chunk_cooked,
			'B', Items.bowl
		});
		KnowledgeListMF.jerkyRecipe =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.jerky, 1), "jerky", chopping, "knife", 2, 20, new Object[]{
			"S",
			"M",
			'S', FoodListMF.salt,
			'M', FoodListMF.generic_meat_strip_cooked,
		});
		KnowledgeListMF.meatPieRecipe =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pie_meat_uncooked), "meatpie", chopping, "knife", 2, 150, new Object[]{
			" P ",
			"MMM",
			" P ",
			" T ",
			'P', FoodListMF.pastry,
			'M', FoodListMF.generic_meat_mince_cooked,
			'T', FoodListMF.pie_tray,
		});
		KnowledgeListMF.sandwitchRecipe =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.sandwitch_meat), "sandwitch", chopping, "knife", 2, 50, new Object[]{
			"B",
			"M",
			"B",
			'M', FoodListMF.generic_meat_cooked,
			'B', FoodListMF.breadroll
		});
		KnowledgeListMF.shepardRecipe =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pie_shepard_uncooked), "shepardpie", chopping, "knife", 3, 200, new Object[]{
			"PFP",
			"MMM",
			" F ",
			" T ",
			'P', Items.potato,
			'F', FoodListMF.pastry,
			'M', FoodListMF.generic_meat_mince_cooked,
			'T', FoodListMF.pie_tray,
		});
			
		KnowledgeListMF.appleR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pie_apple_uncooked), "applepie", chopping, "knife", 2, 120, new Object[]{
			"SPS",
			"MMM",
			"SPS",
			" T ",
			'S', Items.sugar,
			'P', FoodListMF.pastry,
			'M', Items.apple,
			'T', FoodListMF.pie_tray,
		});
		KnowledgeListMF.berryR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.pie_berry_uncooked), "berrypie", chopping, "knife", 2, 100, new Object[]{
			"SPS",
			"MMM",
			"SPS",
			" T ",
			'S', Items.sugar,
			'P', FoodListMF.pastry,
			'M', FoodListMF.berries,
			'T', FoodListMF.pie_tray,
		});
		
		KnowledgeListMF.cakeR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cake_raw), "cake", mixing, "spoon", -1, 20, new Object[]{
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
		KnowledgeListMF.carrotCakeR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cake_carrot_raw), "carrotcake", mixing, "spoon", -1, 25, new Object[]{
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
		KnowledgeListMF.chocoCakeR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cake_choc_raw), "chococake", mixing, "spoon", -1, 25, new Object[]{
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
		KnowledgeListMF.bfCakeR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cake_bf_raw), "bfcake", mixing, "spoon", -1, 30, new Object[]{
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
		
		KnowledgeListMF.cakeI =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.cake_vanilla), "cake", basic, "knife", -1, 60, new Object[]{
			"III",
			" R ",
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_uniced,
		});
		KnowledgeListMF.carrotCakeI =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.cake_carrot), "carrotcake", basic, "knife", -1, 60, new Object[]{
			"III",
			" R ",
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_carrot_uniced,
		});
		KnowledgeListMF.chocoCakeI =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.cake_chocolate), "chococake", basic, "knife", -1, 60, new Object[]{
			"ICI",
			" R ",
			'C', new ItemStack(Items.dye, 1, 3),
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_carrot_uniced,
		});
		KnowledgeListMF.bfCakeI =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(BlockListMF.cake_bf), "bfcake", basic, "knife", -1, 100, new Object[]{
			"BBB",
			"III",
			"CRC",
			'C', new ItemStack(Items.dye, 1, 3),
			'B', FoodListMF.berries,
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_bf_uniced,
		});
		
		KnowledgeListMF.cheeserollR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.cheese_roll), "cheeseroll", chopping, "knife", 1, 30, new Object[]{
			"C",
			"R",
			'C', FoodListMF.cheese_slice,
			'R', FoodListMF.breadroll,
		});
		
		KnowledgeListMF.flourRecipe = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(FoodListMF.flour), "", chopping, 1, new Object[]{
			"C",
			'C', Items.wheat,
		});
	}
	
	private static void addMisc()
	{
		//Fletching
		KnowledgeListMF.fletchingR = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.fletching, 16), "arrows", chopping, 4, new Object[]
		{
			"PFP",
			'F', Items.feather,
			'P', Items.paper,
		});
		
		for(int id = 0; id < ToolListMF.weaponMats.length; id ++)
		{
			KnowledgeListMF.arrowR.add(
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.arrows[id]), "arrows", chopping, 1, new Object[]
			{
				"H",
				"S",
				"F",
				'H', ComponentListMF.arrowheads[id],
				'S', Items.stick,
				'F', ComponentListMF.fletching,
			}));
		}
		for(int id = 0; id < ToolListMF.weaponMats.length-1; id ++)
		{
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.bodkinArrows[id]), "arrowsBodkin", chopping, 1, new Object[]
			{
				"H",
				"S",
				"F",
				'H', ComponentListMF.bodkinheads[id],
				'S', Items.stick,
				'F', ComponentListMF.fletching,
			});
			MineFantasyAPI.addCarpenterRecipe(new ItemStack(ToolListMF.broadArrows[id]), "arrowsBroad", chopping, 1, new Object[]
			{
				"H",
				"S",
				"F",
				'H', ComponentListMF.broadheads[id],
				'S', Items.stick,
				'F', ComponentListMF.fletching,
			});
		}
		
		//BOMBS
		KnowledgeListMF.bombCaseCeramicR = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.bomb_casing_uncooked, 2), "bombCeramic", basic, 2, new Object[]
		{
			" C ",
			"C C",
			" C ",
			'C', Items.clay_ball,
		});
		KnowledgeListMF.mineCaseCeramicR = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.mine_casing_uncooked), "mineCeramic", basic, 2, new Object[]
		{
			" P ",
			"C C",
			" C ",
			
			'P', Blocks.stone_pressure_plate,
			'C', Items.clay_ball,
		});
		KnowledgeListMF.bombCaseCrystalR = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.bomb_casing_crystal), "bombCrystal", basic, 10, new Object[]
		{
			" D ",
			"R R",
			" B ",
			'B', Items.glass_bottle,
			'D', ComponentListMF.diamond_shards,
			'R', Items.redstone
		});
		KnowledgeListMF.mineCaseCrystalR = 
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.mine_casing_crystal), "mineCrystal", basic, 10, new Object[]
		{
			" P ",
			"RDR",
			" B ",
			'P', Blocks.heavy_weighted_pressure_plate,
			'B', Items.glass_bottle,
			'D', ComponentListMF.diamond_shards,
			'R', Items.redstone
		});
		
		KnowledgeListMF.bombFuseR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.bomb_fuse, 8), "bombs", basic, 4, new Object[]
		{
			"R",
			"C",
			"S",
			'S', ComponentListMF.thread,
			'C', ComponentListMF.coalDust,
			'R', Items.redstone,
		});
		KnowledgeListMF.longFuseR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.bomb_fuse_long), "bombs", basic, 1, new Object[]
		{
			"F",
			"R",
			"F",
			'F', ComponentListMF.bomb_fuse,
			'R', Items.redstone,
		});
		
		KnowledgeListMF.blackpowderRec =
		MineFantasyAPI.addShapelessCarpenterRecipe(new ItemStack(ComponentListMF.blackpowder, 4), "blackpowder", "basic", 2, new Object[]
		{
			new ItemStack(ComponentListMF.coalDust),
			new ItemStack(ComponentListMF.coalDust),
			new ItemStack(ComponentListMF.sulfur),
			new ItemStack(ComponentListMF.nitre),
		});
		KnowledgeListMF.advblackpowderRec =
		MineFantasyAPI.addShapelessCarpenterRecipe(new ItemStack(ComponentListMF.blackpowder_advanced), "advblackpowder", "basic", 2, new Object[]
		{
			new ItemStack(ComponentListMF.blackpowder),
			new ItemStack(Items.redstone),
			new ItemStack(Items.glowstone_dust)
		});
		
		KnowledgeListMF.magmaRefinedR =
		MineFantasyAPI.addCarpenterRecipe(new ItemStack(ComponentListMF.magma_cream_refined, 8), "firebomb", "basic", 5, new Object[]
		{
			"PCP",
			"CDC",
			"PCP",
			'D', ComponentListMF.dragon_heart,
			'C', Items.magma_cream,
			'P', Items.blaze_powder
		});
	}
}
