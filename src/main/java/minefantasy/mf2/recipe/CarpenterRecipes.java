package minefantasy.mf2.recipe;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.crafting.Salvage;
import minefantasy.mf2.api.crafting.refine.PaintOilRecipe;
import minefantasy.mf2.api.rpg.Skill;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.config.ConfigHardcore;
import minefantasy.mf2.item.ItemComponentMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CustomToolListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class CarpenterRecipes
{
	private static final String basic = "step.wood";
	private static final String chopping = "dig.wood";
	private static final String primitive = "minefantasy2:block.craftprimitive";
	private static final String sewing = "step.cloth";
	private static final String stonemason = "dig.stone";
	private static final String snipping = "mob.sheep.shear";
	private static final String sawing = "minefantasy2:block.sawcarpenter";
	private static final String grinding = "dig.gravel";
	private static final String nailHammer = "minefantasy2:block.hammercarpenter";
	private static final String woodHammer = "minefantasy2:block.carpentermallet";
	private static final String mixing = "step.wood";
	private static final String spanner = "minefantasy2:block.twistbolt";
	
	private static final Skill artisanry = SkillList.artisanry;
	private static final Skill engineering = SkillList.engineering;
	private static final Skill construction = SkillList.construction;
	private static final Skill provisioning = SkillList.provisioning;
	
	public static void init()
	{
		addDusts();
		addWoodworks();
		addStonemason();
		addCooking();
		addMisc();
		addEngineering();
		if(ConfigHardcore.HCCallowRocks)
		{
			addPrimitive();
		}
		else
		{
			addNonPrimitiveStone();
		}
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.researchBook), "", sewing, "hands", -1, 1 , new Object[]
		{
			"B",
			'B', Items.book,
		});
		KnowledgeListMF.spoonR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.spoonWood), "", basic, "hands", -1, 4 , new Object[]
		{
			"P",
			"S",
			'P', ComponentListMF.plank,
			'S', Items.stick
		});
		KnowledgeListMF.malletR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.malletWood), "", basic, "hands", -1, 6 , new Object[]
		{
			"PP",
			"S ",
			'P', ComponentListMF.plank,
			'S', Items.stick
		});
		Salvage.addSalvage(ToolListMF.spoonWood, ComponentListMF.plank, Items.stick);
		Salvage.addSalvage(ToolListMF.malletWood, new ItemStack(ComponentListMF.plank, 2), Items.stick);
		
		KnowledgeListMF.carpenterRecipe =
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.carpenter), new Object[]{
			"PBP",
			"P P",
			'B', Blocks.crafting_table,
			'P', ComponentListMF.plank,
		});
		Salvage.addSalvage(BlockListMF.carpenter, new ItemStack(ComponentListMF.plank, 4), Blocks.crafting_table);
		
		if(ConfigHardcore.HCCallowRocks)
		{
			KnowledgeListMF.sharpRocksR =
			MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.sharp_rock, 8), "", stonemason, "hammer", -1, 10 , new Object[]
			{
				"S",
				'S', Blocks.cobblestone,
			});
			KnowledgeListMF.dryrocksR =
			MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.dryrocks), "", stonemason, "hands", -1, 10 , new Object[]
			{
				"S ",
				" S",
				'S', ComponentListMF.sharp_rock,
			});
		}
		else
		{
			KnowledgeListMF.dryrocksR =
			MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.dryrocks), "craftCrafters", stonemason, "hammer", -1, 10 , new Object[]
			{
				"S",
				'S', Blocks.cobblestone,
			});
		}
		Salvage.addSalvage(ToolListMF.dryrocks, Blocks.cobblestone);
		
		KnowledgeListMF.threadR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.thread, 4), "commodities", sewing, "hands", -1, 5 , new Object[]
		{
			"W",
			"S",
			'W', Blocks.wool,
			'S', Items.stick,
		});
		KnowledgeListMF.stringR = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(Items.string), "commodities", sewing, "hands", -1, 10 , new Object[]
		{
			"T",
			"T",
			"T",
			"T",
			'T', ComponentListMF.thread
		});
		
		KnowledgeListMF.lStripsR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.leather_strip, 4), "commodities", snipping, "shears", -1, 10 , new Object[]
		{
			"L",
			'L', Items.leather,
		});
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(ToolListMF.swordTraining), nailHammer, "knife", 1, 40 , new Object[]
		{
			"NI  ",
			"SIII",
			"NI  ",
			'N', ComponentListMF.nail,
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(ToolListMF.waraxeTraining), nailHammer, "knife", 1, 30 , new Object[]
		{
			" II ",
			"SSIN",
			"  I ",
			'N', ComponentListMF.nail,
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(ToolListMF.maceTraining), nailHammer, "knife", 1, 35 , new Object[]
		{
			"  II ",
			"SSIIN",
			'N', ComponentListMF.nail,
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(ToolListMF.spearTraining), nailHammer, "knife", 1, 20 , new Object[]
		{
			"  N ",
			"SSSI",
			"  N ",
			'N', ComponentListMF.nail,
			'S', ComponentListMF.plank,
			'I', Blocks.planks,
		});
		Salvage.addSalvage(ToolListMF.swordTraining, new ItemStack(Blocks.planks, 5), new ItemStack(ComponentListMF.nail, 2), ComponentListMF.plank);
		Salvage.addSalvage(ToolListMF.waraxeTraining, new ItemStack(Blocks.planks, 4), ComponentListMF.nail, new ItemStack(ComponentListMF.plank, 2));
		Salvage.addSalvage(ToolListMF.maceTraining, new ItemStack(Blocks.planks, 4), ComponentListMF.nail, new ItemStack(ComponentListMF.plank, 2));
		Salvage.addSalvage(ToolListMF.waraxeTraining, Blocks.planks, new ItemStack(ComponentListMF.nail, 2), new ItemStack(ComponentListMF.plank, 3));
		
		KnowledgeListMF.badBandageR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(ToolListMF.bandage_crude, 2), "bandage", sewing, "needle", -1, 10 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideSmall,
		});
		
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(ToolListMF.bandage_crude, 4), "bandage", sewing, "needle", -1, 20 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideMedium,
		});
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(ToolListMF.bandage_crude, 6), "bandage", sewing, "needle", -1, 30 , new Object[]
		{
			"LLL",
			'L', ComponentListMF.rawhideLarge,
		});
		KnowledgeListMF.bandageR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(ToolListMF.bandage_wool, 4), "bandage", sewing, "needle", 1, 10 , new Object[]
		{
			"CTC",
			'T', ComponentListMF.thread,
			'C', Blocks.wool,
		});
		
		KnowledgeListMF.goodBandageR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(ToolListMF.bandage_tough), "bandageadv", sewing, "needle", 2, 20 , new Object[]
		{
			"T",
			"L",
			"B",
			'T', ComponentListMF.thread,
			'L', ComponentListMF.leather_strip,
			'B', ToolListMF.bandage_wool
		});
		
		
		
		KnowledgeListMF.roughHelmetR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 1, 0),"craftArmourBasic", sewing, "needle", -1, 25 , new Object[]
		{
			"TLT",
			"S S",
			'T', ComponentListMF.thread,
			'S', ComponentListMF.leather_strip,
			'L', Items.leather
		});
		KnowledgeListMF.roughChestR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 1, 1),"craftArmourBasic", sewing, "needle", -1, 40 , new Object[]
		{
			"S S",
			"LLL",
			"TLT",
			'T', ComponentListMF.thread,
			'S', ComponentListMF.leather_strip,
			'L', Items.leather
		});
		KnowledgeListMF.roughLegsR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 1, 2),"craftArmourBasic", sewing, "needle", -1, 35 , new Object[]
		{
			"TLT",
			"L L",
			"S S",
			'T', ComponentListMF.thread,
			'S', ComponentListMF.leather_strip,
			'L', Items.leather
		});
		KnowledgeListMF.roughBootsR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 1, 3),"craftArmourBasic", sewing, "needle", -1, 20 , new Object[]
		{
			"T T",
			"S S",
			'T', ComponentListMF.thread,
			'S', ComponentListMF.leather_strip,
		});
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 1, 0), new ItemStack(ComponentListMF.thread, 2), new ItemStack(ComponentListMF.leather_strip, 2), Items.leather);
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 1, 1), new ItemStack(ComponentListMF.thread, 4), new ItemStack(ComponentListMF.leather_strip, 2), new ItemStack(Items.leather, 4));
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 1, 2), new ItemStack(ComponentListMF.thread, 4), new ItemStack(ComponentListMF.leather_strip, 2), new ItemStack(Items.leather, 3));
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 1, 3), new ItemStack(ComponentListMF.thread, 4), new ItemStack(ComponentListMF.leather_strip, 2));
				
		
		KnowledgeListMF.reHelmetR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 2, 0),"craftArmourLight", sewing, "needle", 0, 50 , new Object[]
		{
			"TTT",
			"UPU",
			'T', ComponentListMF.thread,
			'P', ArmourListMF.armour(ArmourListMF.leather, 1, 0),
			'U', Items.leather
		});
		KnowledgeListMF.reChestR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 2, 1),"craftArmourLight", sewing, "needle", 0, 80 , new Object[]
		{
			"TTT",
			"UPU",
			'T', ComponentListMF.thread,
			'P', ArmourListMF.armour(ArmourListMF.leather, 1, 1),
			'U', Items.leather
		});
		KnowledgeListMF.reLegsR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 2, 2),"craftArmourLight", sewing, "needle", 0, 70 , new Object[]
		{
			"TTT",
			"UPU",
			'T', ComponentListMF.thread,
			'P', ArmourListMF.armour(ArmourListMF.leather, 1, 2),
			'U', Items.leather
		});
		KnowledgeListMF.reBootsR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 2, 3),"craftArmourLight", sewing, "needle", 0, 40 , new Object[]
		{
			"TTT",
			"UPU",
			'T', ComponentListMF.thread,
			'P', ArmourListMF.armour(ArmourListMF.leather, 1, 3),
			'U', Items.leather
		});
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 2, 0), ArmourListMF.armourItem(ArmourListMF.leather, 1, 0), new ItemStack(ComponentListMF.thread, 3), new ItemStack(Items.leather, 2));
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 2, 1), ArmourListMF.armourItem(ArmourListMF.leather, 1, 1), new ItemStack(ComponentListMF.thread, 3), new ItemStack(Items.leather, 2));
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 2, 2), ArmourListMF.armourItem(ArmourListMF.leather, 1, 2), new ItemStack(ComponentListMF.thread, 3), new ItemStack(Items.leather, 2));
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 2, 3), ArmourListMF.armourItem(ArmourListMF.leather, 1, 3), new ItemStack(ComponentListMF.thread, 3), new ItemStack(Items.leather, 2));
		
		//PADDING
		KnowledgeListMF.padding[0]=
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 5, 0),"craftArmourLight", sewing, "needle", 2, 50 , new Object[]
		{
			"SWS",
			"FPF",
			"SFS",
			'P', ArmourListMF.armour(ArmourListMF.leather, 1, 0),
			'W', Blocks.wool,
			'F', Items.feather,
			'S', ComponentListMF.thread,
		});
		KnowledgeListMF.padding[1]=
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 5, 1),"craftArmourLight", sewing, "needle", 2, 80 , new Object[]
		{
			"SWS",
			"FPF",
			"SFS",
			'P', ArmourListMF.armour(ArmourListMF.leather, 1, 1),
			'W', Blocks.wool,
			'F', Items.feather,
			'S', ComponentListMF.thread,
		});
		KnowledgeListMF.padding[2]=
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 5, 2),"craftArmourLight", sewing, "needle", 2, 70 , new Object[]
		{
			"SWS",
			"FPF",
			"SFS",
			'P', ArmourListMF.armour(ArmourListMF.leather, 1, 2),
			'W', Blocks.wool,
			'F', Items.feather,
			'S', ComponentListMF.thread,
		});
		KnowledgeListMF.padding[3]=
		MineFantasyAPI.addCarpenterRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 5, 3),"craftArmourLight", sewing, "needle", 2, 40 , new Object[]
		{
			"SWS",
			"FPF",
			"SFS",
			'P', ArmourListMF.armour(ArmourListMF.leather, 1, 3),
			'W', Blocks.wool,
			'F', Items.feather,
			'S', ComponentListMF.thread,
		});
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 5, 0), ArmourListMF.armourItem(ArmourListMF.leather, 1, 0), new ItemStack(ComponentListMF.thread, 4), new ItemStack(Items.feather, 3), Blocks.wool);
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 5, 1), ArmourListMF.armourItem(ArmourListMF.leather, 1, 1), new ItemStack(ComponentListMF.thread, 4), new ItemStack(Items.feather, 3), Blocks.wool);
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 5, 2), ArmourListMF.armourItem(ArmourListMF.leather, 1, 2), new ItemStack(ComponentListMF.thread, 4), new ItemStack(Items.feather, 3), Blocks.wool);
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 5, 3), ArmourListMF.armourItem(ArmourListMF.leather, 1, 3), new ItemStack(ComponentListMF.thread, 4), new ItemStack(Items.feather, 3), Blocks.wool);
				
		ItemStack bronzeHammer = CustomToolListMF.standard_hammer.construct("bronze");
		ItemStack steelHammer = CustomToolListMF.standard_hammer.construct("steel");
		KnowledgeListMF.repairBasicR =
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.repair_basic), "repair_basic", sewing, "needle", 1, 20 , new Object[]
		{
			"TTT",
			"FNH",
			"SLS",
			'T', ComponentListMF.thread,
			'S', ComponentListMF.leather_strip,
			'L', Items.leather,
			'F', Items.flint,
			'H', bronzeHammer,
			'N', ComponentListMF.nail,
		});
		ItemStack bronzePlate = ComponentListMF.plate.createComm("bronze");
		KnowledgeListMF.repairAdvancedR =
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.repair_advanced), "repair_advanced", sewing, "needle", 2, 50 , new Object[]
		{
			"SCS",
			"PKH",
			"CSC",
			'K', BlockListMF.repair_basic,
			'P', bronzePlate,
			'H', steelHammer,
			'C', Items.slime_ball,
			'S', Items.string,
		});
		KnowledgeListMF.repairOrnateR =
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.repair_ornate), "repair_ornate", sewing, "needle", 3, 100 , new Object[]
		{
			"GDG",
			"LKL",
			"GLG",
			'K', BlockListMF.repair_advanced,
			'G', Items.gold_ingot,
			'L', new ItemStack(Items.dye, 1, 4),
			'D', Items.diamond,
		});
		
		Salvage.addSalvage(BlockListMF.repair_basic, new ItemStack(ComponentListMF.thread, 3), bronzeHammer, ComponentListMF.nail, Items.flint, Items.leather, new ItemStack(ComponentListMF.leather_strip, 2));
		Salvage.addSalvage(BlockListMF.repair_advanced, BlockListMF.repair_basic, bronzePlate, steelHammer, new ItemStack(Items.slime_ball, 3), new ItemStack(Items.string, 3));
		Salvage.addSalvage(BlockListMF.repair_ornate, BlockListMF.repair_advanced, new ItemStack(Items.gold_ingot, 4), Items.diamond, new ItemStack(Items.dye, 3, 4));
	}

	private static void addDusts() 
	{
		KnowledgeListMF.potRecipe = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.clay_pot_uncooked, 8), "", basic, "hands", -1, 5 , new Object[]
		{
			"C  C",
			" CC ",
			'C', Items.clay_ball,
		});
		KnowledgeListMF.mouldRecipe = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.ingot_mould_uncooked), "crucible", basic, "hands", -1, 10 , new Object[]
		{
			"CCC",
			" C ",
			'C', Items.clay_ball,
		});
		KnowledgeListMF.jugRecipe = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(FoodListMF.jug_uncooked, 4), "", basic, "hands", -1, 8 , new Object[]
		{
			"C  ",
			"C C",
			" C ",
			'C', Items.clay_ball,
		});
		GameRegistry.addSmelting(ComponentListMF.clay_pot_uncooked, new ItemStack(ComponentListMF.clay_pot), 0F);
		GameRegistry.addSmelting(ComponentListMF.ingot_mould_uncooked, new ItemStack(ComponentListMF.ingot_mould), 0F);
		GameRegistry.addSmelting(FoodListMF.jug_uncooked, new ItemStack(FoodListMF.jug_empty), 0F);
		
		KnowledgeListMF.coalDustR = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.coalDust), "", grinding, "pestle", -1, 2 , new Object[]
		{
			"C",
			"P",
			'C', Items.coal,
			'P', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.kaoDustR = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.kaolinite_dust), "", grinding, "pestle", -1, 3 , new Object[]
		{
			"C",
			"P",
			'C', ComponentListMF.kaolinite,
			'P', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.flourRecipe = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(FoodListMF.flour), "bread", grinding, "pestle", -1, 2 , new Object[]
		{
			"C",
			"P",
			'C', Items.wheat,
			'P', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.bcrumbsR = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(FoodListMF.breadcrumbs), "bread", grinding, "pestle", -1, 2 , new Object[]
		{
			"C",
			"P",
			'C', FoodListMF.breadroll,
			'P', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.bcrumbsR2 = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(FoodListMF.breadcrumbs, 3), "bread", grinding, "pestle", -1, 2 , new Object[]
		{
			" C ",
			"PPP",
			'C', Items.bread,
			'P', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.sugarGrindR = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(FoodListMF.sugarpot), "icing", grinding, "pestle", -1, 3 , new Object[]
		{
			"C",
			"P",
			'C', Items.reeds,
			'P', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.blackpowderRec = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.blackpowder, 2), "blackpowder", basic, "hands", -1, 2 , new Object[]
		{
			"NS",
			"CC",
			"PP",
			'C', ComponentListMF.coalDust,
			'N', ComponentListMF.nitre,
			'S', ComponentListMF.sulfur,
			'P', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.crudeBombR = 
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ToolListMF.bomb_crude), "blackpowder", primitive, "hands", -1, 5 , new Object[]
		{
			"T",
			"B",
			"P",
			
			'B', ComponentListMF.blackpowder,
			'T', ComponentListMF.thread,
			'P', Items.paper,
		});
		KnowledgeListMF.advblackpowderRec = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.blackpowder_advanced), "advblackpowder", basic, "hands", -1, 10 , new Object[]
		{
			" B ",
			"RGR",
			" P ",
			'B', ComponentListMF.blackpowder,
			'G', Items.glowstone_dust,
			'R', Items.redstone,
			'P', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.magmaRefinedR = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.magma_cream_refined), "firebomb", grinding, "pestle", -1, 10 , new Object[]
		{
			"B",
			"H",
			"C",
			"P",
			'H', ComponentListMF.dragon_heart,
			'B', Items.blaze_powder,
			'C', Items.magma_cream,
			'P', ComponentListMF.clay_pot,
		});
		Salvage.addSalvage(ComponentListMF.magma_cream_refined, ComponentListMF.dragon_heart, Items.blaze_powder, Items.magma_cream, ComponentListMF.clay_pot);
		KnowledgeListMF.shrapnelR = 
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.shrapnel), "shrapnel", grinding, "pestle", -1, 7 , new Object[]
		{
			"F",
			"P",
			'F', Items.flint,
			'P', ComponentListMF.clay_pot,
		});
	}

	private static void addWoodworks()
	{
		for(ItemStack wood: OreDictionary.getOres("plankWood"))
		{
			MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ComponentListMF.plank, 4), "", sawing, "saw", 1, 10 , new Object[]
			{
				"W",
				'W', wood
			});
		}
		KnowledgeListMF.nailPlanksR=
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.nailed_planks), "refined_planks", nailHammer, "hammer", 1, 5 , new Object[]
		{
			"NN",
			"PP",
			"PP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plank,
		});
		KnowledgeListMF.refinedPlankBlockR=
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.refined_planks), "refined_planks", nailHammer, "hammer", 1, 10 , new Object[]
		{
			"NN",
			"PP",
			"PP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
		});
		KnowledgeListMF.nailStairR=
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.nailed_planks_stair), "refined_planks", nailHammer, "hammer", 1, 5 , new Object[]
		{
			"N ",
			"P ",
			"PP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plank,
		});
		KnowledgeListMF.refinedStairR=
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.refined_planks_stair), "refined_planks", nailHammer, "hammer", 1, 10 , new Object[]
		{
			"N ",
			"P ",
			"PP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
		});
		Salvage.addSalvage(BlockListMF.nailed_planks, new ItemStack(ComponentListMF.nail, 2), new ItemStack(ComponentListMF.plank, 4));
		Salvage.addSalvage(BlockListMF.refined_planks, new ItemStack(ComponentListMF.nail, 2), new ItemStack(ComponentListMF.plankRefined, 4));
		Salvage.addSalvage(BlockListMF.nailed_planks_stair, ComponentListMF.nail, new ItemStack(ComponentListMF.plank, 3));
		Salvage.addSalvage(BlockListMF.refined_planks_stair, ComponentListMF.nail, new ItemStack(ComponentListMF.plankRefined, 3));
		
		KnowledgeListMF.bellowsRecipe = 
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.bellows), "", nailHammer, "hammer", 1, 50 , new Object[]
		{
			"NNN",
			"PPP",
			"LL ",
			"PP ",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
			'L', Items.leather,
		});
		Salvage.addSalvage(BlockListMF.bellows, new ItemStack(ComponentListMF.nail, 3), new ItemStack(ComponentListMF.plankRefined, 5), new ItemStack(Items.leather, 2));
		KnowledgeListMF.woodTroughRecipe = 
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.trough_wood), "", nailHammer, "hammer", 0, 30 , new Object[]
		{
			"P P",
			"PPP",
			'P', ComponentListMF.plank,
		});
		KnowledgeListMF.rockTroughRecipe = 
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.trough_rock), "", nailHammer, "hammer", 1, 60 , new Object[]
		{
			"P P",
			"PPP",
			'P', BlockListMF.reinforced_stone,
		});
		KnowledgeListMF.strongwoodTroughRecipe = 
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.trough_strongwood), "", nailHammer, "hammer", 2, 100 , new Object[]
		{
			"N N",
			"P P",
			"PPP",
			"NNN",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
		});
		Salvage.addSalvage(BlockListMF.trough_wood, new ItemStack(ComponentListMF.plank, 5));
		Salvage.addSalvage(BlockListMF.trough_rock, new ItemStack(BlockListMF.reinforced_stone, 5));
		Salvage.addSalvage(BlockListMF.trough_strongwood, new ItemStack(ComponentListMF.plankRefined, 5), new ItemStack(ComponentListMF.nail, 5));
		
		KnowledgeListMF.strongRackR = 
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.advTanner), "", nailHammer, "hammer", 1, 80 , new Object[]
		{
			"NNN",
			"PPP",
			"P P",
			"PPP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
		});
		Salvage.addSalvage(BlockListMF.advTanner, new ItemStack(ComponentListMF.plankRefined, 8), new ItemStack(ComponentListMF.nail, 3));
		
		KnowledgeListMF.easyPaintPlank = 
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(ComponentListMF.plankRefined, 4), "paint_brush", sewing, "brush", -1, 2 , new Object[]
		{
			" O  ",
			"PPPP",
			'O', ComponentListMF.plant_oil,
			'P', ComponentListMF.plank,
		});
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.refined_planks), "paint_brush", sewing, "brush", -1, 3 , new Object[]
		{
			"O",
			"P",
			'O', ComponentListMF.plant_oil,
			'P', BlockListMF.nailed_planks,
		});
		
		PaintOilRecipe.addRecipe(BlockListMF.nailed_planks, BlockListMF.refined_planks);
		PaintOilRecipe.addRecipe(BlockListMF.nailed_planks_stair, BlockListMF.refined_planks_stair);
		
		KnowledgeListMF.clayWallR =
		MineFantasyAPI.addCarpenterRecipe(construction, new ItemStack(BlockListMF.clayWall, 4), "clay_wall", nailHammer, "hammer", 1, 2 , new Object[]
		{
			"NPN",
			"PCP",
			"NPN",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plank,
			'C', Blocks.clay
		});
		Salvage.addSalvage(BlockListMF.clayWall,  ComponentListMF.nail, ComponentListMF.plank, Items.clay_ball);
	}
	
	private static void addStonemason()
	{
		KnowledgeListMF.bloomeryR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.bloomery), "bloomery", stonemason, "hammer", -1, 10 , new Object[]{
			" S ",
			"S S",
			"SCS",
			'C', Blocks.coal_block,
			'S', Blocks.stone,
		});
		KnowledgeListMF.crucibleRecipe = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.crucible), "crucible", stonemason, "hammer", -1, 20 , new Object[]{
			"SSS",
			"S S",
			"SSS",
			'S', Blocks.stone,
		});
		KnowledgeListMF.advCrucibleRecipe = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.crucibleadv), "crucible2", stonemason, 40 , new Object[]{
			"SSS",
			"SCS",
			"SSS",
			'S', ComponentListMF.fireclay,
			'C', BlockListMF.crucible
		});
		Salvage.addSalvage(BlockListMF.crucible, new ItemStack(Blocks.stone, 8));
		Salvage.addSalvage(BlockListMF.crucibleadv, new ItemStack(ComponentListMF.fireclay, 8), BlockListMF.crucible);
		
		KnowledgeListMF.chimneyRecipe = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.chimney_stone, 8), "", stonemason, "hammer", -1, 30 , new Object[]{
			"S S",
			"S S",
			"S S",
			'S', Blocks.stone,
		});
		KnowledgeListMF.wideChimneyRecipe = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.chimney_stone_wide), "", stonemason, "hammer", -1, 10 , new Object[]{
			"S",
			"C",
			'C', BlockListMF.chimney_stone,
			'S', Blocks.stone,
		});
		KnowledgeListMF.extractChimneyRecipe = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(BlockListMF.chimney_stone_extractor), "", stonemason, "hammer", -1, 15 , new Object[]{
			"C",
			'C', BlockListMF.chimney_stone_wide,
		});
		Salvage.addSalvage(BlockListMF.chimney_stone, Blocks.stone);
		Salvage.addSalvage(BlockListMF.chimney_stone_wide, BlockListMF.chimney_stone, Blocks.stone);
		Salvage.addSalvage(BlockListMF.chimney_stone_extractor, BlockListMF.chimney_stone_wide);
	}
	
	private static void addCooking()
	{
		String meatRaw = "rawMeat";
		String cookedMeat = "cookedMeat";
		OreDictionary.registerOre(cookedMeat, Items.cooked_beef);
		OreDictionary.registerOre(cookedMeat, Items.cooked_chicken);
		OreDictionary.registerOre(cookedMeat, Items.cooked_porkchop);
		OreDictionary.registerOre(cookedMeat, FoodListMF.wolf_cooked);
		OreDictionary.registerOre(cookedMeat, FoodListMF.horse_cooked);
		OreDictionary.registerOre(cookedMeat, Items.cooked_fished);
		OreDictionary.registerOre(cookedMeat, new ItemStack(Items.cooked_fished, 1, 1));
		addOreD("listAllporkcooked", cookedMeat);
		addOreD("listAllmuttoncooked", cookedMeat);
		addOreD("listAllbeefcooked", cookedMeat);
		addOreD("listAllchickencooked", cookedMeat);
		addOreD("listAllfishcooked", cookedMeat);
		
		OreDictionary.registerOre(meatRaw, FoodListMF.guts);
		OreDictionary.registerOre(meatRaw, Items.beef);
		OreDictionary.registerOre(meatRaw, Items.chicken);
		OreDictionary.registerOre(meatRaw, Items.porkchop);
		OreDictionary.registerOre(meatRaw, FoodListMF.wolf_raw);
		OreDictionary.registerOre(meatRaw, FoodListMF.horse_raw);
		OreDictionary.registerOre(meatRaw, Items.fish);
		OreDictionary.registerOre(meatRaw, new ItemStack(Items.fish, 1, 1));
		addOreD("listAllporkraw", meatRaw);
		addOreD("listAllmuttonraw", meatRaw);
		addOreD("listAllbeefraw", meatRaw);
		addOreD("listAllchickenraw", meatRaw);
		addOreD("listAllfishraw", meatRaw);
		
		KnowledgeListMF.curdRecipe = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.curds), "", basic, "hands", -1, 10, new Object[]{
			"T",
			"S",
			"M",
			'T', FoodListMF.salt,
			'S', FoodListMF.sugarpot,
			'M', FoodListMF.jug_milk,
		});
		
		KnowledgeListMF.oatsRecipe = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.oats), "", chopping, "knife", -1, 20, new Object[]{
			"M",
			"W",
			"S",
			"B",
			'S', Items.wheat_seeds,
			'W', Items.wheat,
			'M', FoodListMF.jug_milk,
			'B', Items.bowl
		});
		KnowledgeListMF.doughRecipe = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.dough), "", basic, "hands", -1, 10, new Object[]{
			"W",
			"F",
			'W', FoodListMF.jug_water,
			'F', FoodListMF.flour,
		});
		KnowledgeListMF.pastryRecipe = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.pastry), "", basic, "hands", -1, 10, new Object[]{
			" S ",
			"FEF",
			'F', FoodListMF.flour,
			'E', Items.egg,
			'S', FoodListMF.salt,
		});
		KnowledgeListMF.breadRecipe = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.raw_bread), "", basic, "hands", -1, 15, new Object[]{
			"DDD",
			'D', FoodListMF.dough,
		});
		KnowledgeListMF.sweetrollRecipe = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.sweetroll_raw), "sweetroll", basic, 5, new Object[]{
			" M ",
			"FES",
			"BBB",
			'M', FoodListMF.jug_milk,
			'S', FoodListMF.sugarpot,
			'B', FoodListMF.berries,
			'E', Items.egg,
			'F', FoodListMF.flour,
		});
		KnowledgeListMF.icingRecipe = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.icing), "", mixing, "spoon", -1, 10, new Object[]{
			"W",
			"S",
			"B",
			'W', FoodListMF.jug_water,
			'S', FoodListMF.sugarpot,
			'B', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.custardRecipe = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.custard), "icing", mixing, "spoon", -1, 10, new Object[]{
			" M ",
			"SES",
			" B ",
			'E', Items.egg,
			'M', FoodListMF.jug_milk,
			'S', FoodListMF.sugarpot,
			'B', ComponentListMF.clay_pot,
		});
		KnowledgeListMF.iceSR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.sweetroll), "sweetroll", basic, "knife", -1, 15, new Object[]{
			"I",
			"R",
			'I', FoodListMF.icing,
			'R', FoodListMF.sweetroll_uniced,
		});
		KnowledgeListMF.eclairDoughR = 
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.eclair_raw), "eclair", basic, 8, new Object[]{
			"SSS",
			"PPP",
			'P', FoodListMF.pastry,
			'S', FoodListMF.sugarpot,
		});
		KnowledgeListMF.eclairIceR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.eclair_empty), "eclair", basic, "knife", 2, 20, new Object[]{
			"C",
			"I",
			"R",
			'C', FoodListMF.chocolate,
			'I', FoodListMF.chocolate,
			'R', FoodListMF.eclair_uniced,
		});
		KnowledgeListMF.eclairFillR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.eclair), "eclair", basic, "knife", 2, 20, new Object[]{
			"C",
			"R",
			'C', FoodListMF.custard,
			'R', FoodListMF.eclair_empty,
		});
		for(ItemStack food: OreDictionary.getOres(meatRaw))
		{
			int size = 1;
			KnowledgeListMF.meatRecipes.add(
			MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.generic_meat_uncooked, size), "", chopping, "knife", -1, 15, new Object[]{
				"M",
				'M', food,
			}));
		}
		for(ItemStack food: OreDictionary.getOres(cookedMeat))
		{
			int size = getSize(food);
			KnowledgeListMF.meatRecipes.add(
			MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.generic_meat_cooked, size), "", chopping, "knife", -1, 15, new Object[]{
				"M",
				'M', food,
			}));
		}
		KnowledgeListMF.meatStripR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.generic_meat_strip_uncooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_uncooked,
		});
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.generic_meat_strip_cooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_cooked,
		});
		KnowledgeListMF.meatHunkR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.generic_meat_chunk_uncooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_strip_uncooked,
		});
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.generic_meat_chunk_cooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_strip_cooked,
		});
		KnowledgeListMF.minceR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.generic_meat_mince_uncooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_chunk_uncooked,
		});
		KnowledgeListMF.gutsRecipe =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.guts), "", chopping, "knife", 1, 8, new Object[]{
			"MMMM",
			'M', Items.rotten_flesh,
		});
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.generic_meat_mince_cooked), "", chopping, "knife", -1, 5, new Object[]{
			"M",
			'M', FoodListMF.generic_meat_chunk_cooked,
		});
		
		KnowledgeListMF.stewRecipe =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.stew), "", chopping, "knife", -1, 15, new Object[]{
			"M",
			"B",
			'M', FoodListMF.generic_meat_chunk_cooked,
			'B', Items.bowl
		});
		KnowledgeListMF.jerkyRecipe =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.jerky, 1), "jerky", chopping, "knife", 2, 20, new Object[]{
			"S",
			"M",
			'S', FoodListMF.salt,
			'M', FoodListMF.generic_meat_strip_cooked,
		});
		KnowledgeListMF.saussageR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.saussage_raw, 4), "saussage", chopping, "knife", 2, 30, new Object[]{
			" G ",
			"MMM",
			"BES",
			'G', FoodListMF.guts,
			'E', Items.egg,
			'S', FoodListMF.salt,
			'B', FoodListMF.breadcrumbs,
			'M', FoodListMF.generic_meat_mince_uncooked,
		});
		KnowledgeListMF.meatPieRecipe =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.pie_meat_uncooked), "meatpie", chopping, "knife", 2, 150, new Object[]{
			" P ",
			"MMM",
			" P ",
			" T ",
			'P', FoodListMF.pastry,
			'M', FoodListMF.generic_meat_mince_cooked,
			'T', FoodListMF.pie_tray,
		});
		KnowledgeListMF.breadSliceR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.breadSlice, 12), "", "step.cloth", "knife", -1, 10, new Object[]{
			"B",
			'B', Items.bread,
		});
		KnowledgeListMF.sandwitchRecipe =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.sandwitch_meat), "sandwitch", chopping, "hands", -1, 4, new Object[]{
			"B",
			"C",
			"M",
			"B",
			'C', FoodListMF.cheese_slice,
			'M', FoodListMF.generic_meat_cooked,
			'B', FoodListMF.breadSlice
		});
		KnowledgeListMF.sandwitchBigRecipe =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.sandwitch_big), "sandwitchBig", chopping, "knife", 1, 10, new Object[]{
			"CSC",
			"MBM",
			'S', FoodListMF.salt,
			'C', FoodListMF.cheese_slice,
			'M', FoodListMF.generic_meat_cooked,
			'B', Items.bread
		});
		KnowledgeListMF.shepardRecipe =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.pie_shepard_uncooked), "shepardpie", chopping, "knife", 3, 200, new Object[]{
			"PFP",
			"MMM",
			"CFC",
			" T ",
			'C', Items.carrot,
			'P', Items.potato,
			'F', FoodListMF.pastry,
			'M', FoodListMF.generic_meat_mince_cooked,
			'T', FoodListMF.pie_tray,
		});
			
		KnowledgeListMF.appleR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.pie_apple_uncooked), "applepie", chopping, "knife", 2, 120, new Object[]{
			"SPS",
			"MMM",
			"SPS",
			" T ",
			'S', FoodListMF.sugarpot,
			'P', FoodListMF.pastry,
			'M', Items.apple,
			'T', FoodListMF.pie_tray,
		});
		KnowledgeListMF.berryR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.pie_berry_uncooked), "berrypie", chopping, "knife", 2, 100, new Object[]{
			"SPS",
			"MMM",
			"SPS",
			" T ",
			'S', FoodListMF.sugarpot,
			'P', FoodListMF.pastry,
			'M', FoodListMF.berries,
			'T', FoodListMF.pie_tray,
		});
		
		KnowledgeListMF.cakeR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.cake_raw), "cake", mixing, "spoon", -1, 20, new Object[]{
			"SMS",
			"SES",
			"FFF",
			" T ",
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', FoodListMF.jug_milk,
			'S', FoodListMF.sugarpot,
			'T', FoodListMF.cake_tin,
		});
		KnowledgeListMF.carrotCakeR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.cake_carrot_raw), "carrotcake", mixing, "spoon", -1, 25, new Object[]{
			"SMS",
			"SES",
			"CCC",
			"FTF",
			'C', Items.carrot,
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', FoodListMF.jug_milk,
			'S', FoodListMF.sugarpot,
			'T', FoodListMF.cake_tin,
		});
		KnowledgeListMF.chocoCakeR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.cake_choc_raw), "chococake", mixing, "spoon", -1, 25, new Object[]{
			"SMS",
			"SES",
			"CCC",
			"FTF",
			'C', new ItemStack(Items.dye, 1, 3),//ItemDye
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', FoodListMF.jug_milk,
			'S', FoodListMF.sugarpot,
			'T', FoodListMF.cake_tin,
		});
		KnowledgeListMF.bfCakeR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.cake_bf_raw), "bfcake", mixing, "spoon", -1, 30, new Object[]{
			"SMMS",
			"SEES",
			"CBBC",
			"FTFF",
			'B', FoodListMF.berriesJuicy,
			'C', new ItemStack(Items.dye, 1, 3),//ItemDye
			'F', FoodListMF.flour,
			'E', Items.egg,
			'M', FoodListMF.jug_milk,
			'S', FoodListMF.sugarpot,
			'T', FoodListMF.cake_tin,
		});
		
		KnowledgeListMF.cakeI =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(BlockListMF.cake_vanilla), "cake", basic, "knife", -1, 60, new Object[]{
			"III",
			" R ",
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_uniced,
		});
		KnowledgeListMF.carrotCakeI =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(BlockListMF.cake_carrot), "carrotcake", basic, "knife", -1, 60, new Object[]{
			"III",
			" R ",
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_carrot_uniced,
		});
		KnowledgeListMF.chocoCakeI =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(BlockListMF.cake_chocolate), "chococake", basic, "knife", -1, 60, new Object[]{
			"ICI",
			" R ",
			'C', new ItemStack(Items.dye, 1, 3),
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_choc_uniced,
		});
		KnowledgeListMF.bfCakeI =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(BlockListMF.cake_bf), "bfcake", basic, "knife", -1, 100, new Object[]{
			"BBB",
			"III",
			"CRC",
			'C', new ItemStack(Items.dye, 1, 3),
			'B', FoodListMF.berries,
			'I', FoodListMF.icing,
			'R', FoodListMF.cake_bf_uniced,
		});
		
		KnowledgeListMF.cheeserollR =
		MineFantasyAPI.addCarpenterRecipe(provisioning, new ItemStack(FoodListMF.cheese_roll), "cheeseroll", chopping, "knife", 1, 30, new Object[]{
			"C",
			"R",
			'C', FoodListMF.cheese_slice,
			'R', FoodListMF.breadroll,
		});
	}
	
	private static void addOreD(String list, String mfList) 
	{
		for(ItemStack stack: OreDictionary.getOres(list))
		{
			OreDictionary.registerOre(mfList, stack);
		}
	}

	private static int getSize(ItemStack food) 
	{
		if(food != null && food.getItem() instanceof ItemFood)
		{
			int feed = ((ItemFood)food.getItem()).func_150905_g(food);
			return Math.max(1, feed-1);
		}
		return 1;
	}

	private static void addMisc()
	{
		//Fletching
		KnowledgeListMF.fletchingR = 
		MineFantasyAPI.addCarpenterRecipe(artisanry, new ItemStack(ComponentListMF.fletching, 8), "arrows", chopping, 4, new Object[]
		{
			"PFP",
			" P ",
			'F', Items.feather,
			'P', Items.paper,
		});
		
		//BOMBS
		KnowledgeListMF.bombCaseCeramicR = 
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.bomb_casing_uncooked, 2), "bombCeramic", basic, 2, new Object[]
		{
			" C ",
			"C C",
			" C ",
			'C', Items.clay_ball,
		});
		KnowledgeListMF.mineCaseCeramicR = 
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.mine_casing_uncooked), "mineCeramic", basic, 2, new Object[]
		{
			" P ",
			"C C",
			" C ",
			
			'P', Blocks.stone_pressure_plate,
			'C', Items.clay_ball,
		});
		KnowledgeListMF.bombCaseCrystalR = 
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.bomb_casing_crystal), "bombCrystal", basic, 10, new Object[]
		{
			" D ",
			"R R",
			" B ",
			'B', Items.glass_bottle,
			'D', ComponentListMF.diamond_shards,
			'R', Items.redstone
		});
		KnowledgeListMF.mineCaseCrystalR = 
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.mine_casing_crystal), "mineCrystal", basic, 10, new Object[]
		{
			" P ",
			"RDR",
			" B ",
			'P', Blocks.heavy_weighted_pressure_plate,
			'B', Items.glass_bottle,
			'D', ComponentListMF.diamond_shards,
			'R', Items.redstone
		});
		Salvage.addSalvage(ComponentListMF.bomb_casing_uncooked, new ItemStack(Items.clay_ball, 2));
		Salvage.addSalvage(ComponentListMF.mine_casing_uncooked, new ItemStack(Items.clay_ball, 3), Blocks.stone_pressure_plate);
		
		Salvage.addSalvage(ComponentListMF.bomb_casing_crystal, Items.glass_bottle, ComponentListMF.diamond_shards, new ItemStack(Items.redstone, 2));
		Salvage.addSalvage(ComponentListMF.mine_casing_crystal, Blocks.heavy_weighted_pressure_plate, Items.glass_bottle, ComponentListMF.diamond_shards, new ItemStack(Items.redstone, 2));
		
		KnowledgeListMF.bombFuseR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.bomb_fuse, 8), "bombs", basic, 4, new Object[]
		{
			"R",
			"C",
			"S",
			'S', ComponentListMF.thread,
			'C', ComponentListMF.coalDust,
			'R', Items.redstone,
		});
		KnowledgeListMF.longFuseR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.bomb_fuse_long), "bombs", basic, 1, new Object[]
		{
			"F",
			"R",
			"F",
			'F', ComponentListMF.bomb_fuse,
			'R', Items.redstone,
		});
		Salvage.addSalvage(ComponentListMF.bomb_fuse_long, new ItemStack(ComponentListMF.bomb_fuse, 2), Items.redstone);
	}
	public static void addCrossbows()
	{
		//CROSSBOWS
		KnowledgeListMF.crossHandleWoodR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.crossbow_handle_wood), "crossShafts", nailHammer, "hammer", 2, 150, new Object[]
		{
			"N N",
			"PP ",
			" P ",
			'P', ComponentListMF.plankRefined,
			'N', ComponentListMF.nail
		});
		KnowledgeListMF.crossStockWoodR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.crossbow_stock_wood), "crossShafts", nailHammer, "hammer", 2, 300, new Object[]
		{
			"NN N",
			"PPPP",
			" PPP",
			'P', ComponentListMF.plankRefined,
			'N', ComponentListMF.nail
		});
		KnowledgeListMF.crossStockIronR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.crossbow_stock_iron), "crossShaftAdvanced", spanner, "spanner", 2, 300, new Object[]
		{
			" BBB",
			"BOGG",
			"SWSS",
			"    ",
			'O', Blocks.obsidian,
			'G', ComponentListMF.tungsten_gears,
			'W', ComponentListMF.crossbow_stock_wood,
			'S', ComponentListMF.iron_strut,
			'B', ComponentListMF.bolt,
		});
		
		KnowledgeListMF.crossHeadLightR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.cross_arms_light), "crossHeads", nailHammer, "hammer", 2, 200, new Object[]
		{
			"PPP",
			"NSN",
			" P ",
			'P', ComponentListMF.plankRefined,
			'N', ComponentListMF.nail,
			'S', Items.string,
		});
		KnowledgeListMF.crossHeadMediumR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.cross_arms_basic), "crossHeads", nailHammer, "hammer", 2, 250, new Object[]
		{
			"NNN",
			"PAP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
			'A', ComponentListMF.cross_arms_light,
		});
		KnowledgeListMF.crossHeadHeavyR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.cross_arms_heavy), "crossHeads", nailHammer, "hammer", 2, 350, new Object[]
		{
			"NNN",
			"PAP",
			'N', ComponentListMF.nail,
			'P', ComponentListMF.plankRefined,
			'A', ComponentListMF.cross_arms_basic,
		});
		KnowledgeListMF.crossHeadAdvancedR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.cross_arms_advanced), "crossHeadAdvanced", nailHammer, "hammer", 2, 350, new Object[]
		{
			"NRN",
			"RGR",
			" A ",
			'G', ComponentListMF.tungsten_gears,
			'N', ComponentListMF.nail,
			'R', ComponentListMF.steel_tube,
			'A', ComponentListMF.cross_arms_basic,
		});
		
		KnowledgeListMF.crossAmmoR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.cross_ammo), "crossAmmo", nailHammer, "hammer", 2, 200, new Object[]
		{
			"NNN",
			"P P",
			"PGP",
			"PPP",
			'G', ComponentListMF.tungsten_gears,
			'P', ComponentListMF.plankRefined,
			'N', ComponentListMF.nail,
		});
		KnowledgeListMF.crossScopeR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.cross_scope), "crossScope", spanner, "spanner", 0, 150, new Object[]
		{
			"BSB",
			"GP ",
			'G', ComponentListMF.tungsten_gears,
			'S', ToolListMF.spyglass,
			'P', ComponentListMF.plankRefined,
			'B', ComponentListMF.bolt,
		});
		Salvage.addSalvage(ComponentListMF.cross_arms_light, new ItemStack(ComponentListMF.nail, 2), Items.string, new ItemStack(ComponentListMF.plankRefined, 4));
		Salvage.addSalvage(ComponentListMF.cross_arms_basic, new ItemStack(ComponentListMF.nail, 3), ComponentListMF.cross_arms_light, new ItemStack(ComponentListMF.plankRefined, 2));
		Salvage.addSalvage(ComponentListMF.cross_arms_heavy, new ItemStack(ComponentListMF.nail, 3), ComponentListMF.cross_arms_basic, new ItemStack(ComponentListMF.plankRefined, 2));
		Salvage.addSalvage(ComponentListMF.cross_arms_advanced, ComponentListMF.tungsten_gears, new ItemStack(ComponentListMF.nail, 2), ComponentListMF.cross_arms_basic, new ItemStack(ComponentListMF.steel_tube, 3));
		
		Salvage.addSalvage(ComponentListMF.cross_scope, ComponentListMF.tungsten_gears, ToolListMF.spyglass, new ItemStack(ComponentListMF.bolt, 2), ComponentListMF.plankRefined);
		Salvage.addSalvage(ComponentListMF.cross_ammo, ComponentListMF.tungsten_gears, new ItemStack(ComponentListMF.nail, 3), new ItemStack(ComponentListMF.plankRefined, 7));
		Salvage.addSalvage(ComponentListMF.crossbow_handle_wood, new ItemStack(ComponentListMF.nail, 2), new ItemStack(ComponentListMF.plankRefined, 3));
		Salvage.addSalvage(ComponentListMF.crossbow_stock_wood, new ItemStack(ComponentListMF.nail, 3), new ItemStack(ComponentListMF.plankRefined, 7));
		Salvage.addSalvage(ComponentListMF.crossbow_stock_iron, new ItemStack(ComponentListMF.tungsten_gears, 2), Blocks.obsidian, new ItemStack(ComponentListMF.bolt, 4), new ItemStack(ComponentListMF.iron_strut, 3), ComponentListMF.crossbow_stock_wood);
	}
	
	private static void addEngineering()
	{
		addCrossbows();
		KnowledgeListMF.bombBenchCraft =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(BlockListMF.bombBench), "bombs", spanner, "spanner", 0, 150, new Object[]
		{
			"BFB",
			"BCB",
			'B', ComponentListMF.bolt,
			'F', ComponentListMF.iron_frame,
			'C', BlockListMF.carpenter,
		});
		KnowledgeListMF.bombPressCraft =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(BlockListMF.bombPress), "bpress", spanner, "spanner", 0, 200, new Object[]
		{
			"BFB",
			"GGL",
			"SPS",
			'S', ComponentListMF.iron_strut,
			'B', ComponentListMF.bolt,
			'F', ComponentListMF.iron_frame,
			'L', Blocks.lever,
			'P', new ItemStack(ToolListMF.spanner, 1, 0),
			'G', ComponentListMF.bronze_gears,
		});
		
		KnowledgeListMF.crossBenchCraft =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(BlockListMF.crossbowBench), "crossbows", spanner, "spanner", 0, 200, new Object[]
		{
			"PSP",
			"NCN",
			'P', ComponentListMF.plank,
			'N', ComponentListMF.nail,
			'S', Items.string,
			'C', BlockListMF.carpenter,
		});
		
		
		KnowledgeListMF.engTannerR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(BlockListMF.engTanner), "engTanner", spanner, "spanner", 0, 300, new Object[]
		{
			"BLB",
			"SPS",
			"GGG",
			"SFS",
			'S', ComponentListMF.iron_strut,
			'B', ComponentListMF.bolt,
			'F', ComponentListMF.iron_frame,
			'L', Blocks.lever,
			'P', new ItemStack(CustomToolListMF.standard_knife, 1, 0),
			'G', ComponentListMF.bronze_gears,
		});
		ItemStack blackPlate = ComponentListMF.plate.createComm("blackSteel");
		KnowledgeListMF.advancedForgeR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(BlockListMF.forge_metal), "advforge", spanner, "spanner", 1, 400, new Object[]
		{
			" T  ",
			"FRRF",
			"PPPP",
			"BBBB",
			'B', ComponentListMF.bolt,
			'F', ComponentListMF.iron_frame,
			'T', ToolListMF.engin_anvil_tools,
			'P', blackPlate,
			'R', Blocks.redstone_block,
		});
		KnowledgeListMF.spyglassR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ToolListMF.spyglass), "spyglass", spanner, "spanner", 0, 300, new Object[]
		{
			" T ",
			"BCB",
			"GPG",
			'C', ComponentListMF.bronze_gears,
			'G', Blocks.glass,
			'B', ComponentListMF.bolt,
			'T', ToolListMF.engin_anvil_tools,
			'P', ComponentListMF.steel_tube,
		});
		
		
		KnowledgeListMF.syringeR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ToolListMF.syringe_empty), "syringe", spanner, "spanner", 0, 200, new Object[]
		{
			"E",
			"T",
			"B",
			"N",
			'E', ToolListMF.engin_anvil_tools,
			'T', ComponentListMF.steel_tube,
			'B', Items.glass_bottle,
			'N', new ItemStack(CustomToolListMF.standard_needle),
		});
		
		KnowledgeListMF.parachuteR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ToolListMF.parachute), "parachute", sewing, "needle", 1, 350, new Object[]
		{
			"TTT",
			"CCC",
			"BEB",
			"BLB",
			'E', ToolListMF.engin_anvil_tools,
			'T', ComponentListMF.thread,
			'B', ComponentListMF.leather_strip,
			'L', Items.leather,
			'C', Blocks.wool,
		});
		KnowledgeListMF.cogShaftR =
		MineFantasyAPI.addCarpenterRecipe(engineering, new ItemStack(ComponentListMF.cogwork_shaft), "cogArmour", spanner, "spanner", 1, 150, new Object[]
		{
			"BPB",
			"SGS",
			"BFB",
			
			'P', Blocks.piston,
			'G', ComponentListMF.tungsten_gears,
			'B', ComponentListMF.bolt,
			'F', ComponentListMF.iron_frame,
			'S', ComponentListMF.iron_strut,
		});
		Salvage.addSalvage(ComponentListMF.cogwork_shaft, new ItemStack(ComponentListMF.iron_strut, 2), new ItemStack(ComponentListMF.bolt, 4), ComponentListMF.iron_frame, Blocks.piston, ComponentListMF.tungsten_gears);
		
		Salvage.addSalvage(BlockListMF.bombBench, new ItemStack(ComponentListMF.bolt, 4), ComponentListMF.iron_frame, BlockListMF.carpenter);
		Salvage.addSalvage(BlockListMF.crossbowBench, new ItemStack(ComponentListMF.nail, 2), new ItemStack(ComponentListMF.plank, 2), Items.string, BlockListMF.carpenter);
		Salvage.addSalvage(BlockListMF.bombPress, new ItemStack(ComponentListMF.iron_strut, 2), new ItemStack(ComponentListMF.bolt, 2), new ItemStack(ComponentListMF.bronze_gears, 2), Blocks.lever, ComponentListMF.iron_frame, ToolListMF.spanner);
		Salvage.addSalvage(BlockListMF.engTanner, new ItemStack(ComponentListMF.iron_strut, 4), new ItemStack(ComponentListMF.bolt, 2), new ItemStack(ComponentListMF.bronze_gears, 3), CustomToolListMF.standard_needle, Blocks.lever, ComponentListMF.iron_frame);
		Salvage.addSalvage(BlockListMF.forge_metal, new ItemStack(ComponentListMF.bolt, 4), blackPlate, blackPlate, blackPlate, blackPlate, new ItemStack(ComponentListMF.iron_frame, 2), new ItemStack(Blocks.redstone_block, 2));
		Salvage.addSalvage(ToolListMF.spyglass, new ItemStack(ComponentListMF.bolt, 2), new ItemStack(Blocks.glass, 2), ComponentListMF.steel_tube, ComponentListMF.bronze_gears);
		Salvage.addSalvage(ToolListMF.syringe_empty, Items.glass_bottle, CustomToolListMF.standard_needle, ComponentListMF.steel_tube);
		Salvage.addSalvage(ToolListMF.parachute, new ItemStack(ComponentListMF.thread, 3), new ItemStack(Blocks.wool, 3), new ItemStack(ComponentListMF.leather_strip, 4), Items.leather);
	}
	
	private static void addNonPrimitiveStone()
	{
		KnowledgeListMF.stoneKnifeR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.knifeStone), "", primitive, "hands", -1, 4, new Object[]
		{
			"R",
			"R",
			"S",
			'R', Blocks.cobblestone,
			'S', ComponentListMF.plank,
		});
		KnowledgeListMF.stoneHammerR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.hammerStone), "", primitive, "hands", -1, 4, new Object[]
		{
			"R",
			"S",
			'R', Blocks.cobblestone,
			'S', ComponentListMF.plank,
		});
		
		KnowledgeListMF.stoneTongsR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.tongsStone), "", primitive, "hands", -1, 4, new Object[]
		{
			"R ",
			"SR",
			'R', Blocks.cobblestone,
			'S', ComponentListMF.plank,
		});
		KnowledgeListMF.boneNeedleR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.needleBone), "", primitive, "hands", -1, 4, new Object[]
		{
			"B",
			'B', Items.bone,
		});
		KnowledgeListMF.pestleR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.pestle), "", primitive, "hands", -1, 4, new Object[]
		{
			"F",
			"F",
			'F', Items.flint,
		});
		Salvage.addSalvage(ToolListMF.knifeStone, new ItemStack(Blocks.cobblestone, 2), ComponentListMF.plank);
		Salvage.addSalvage(ToolListMF.hammerStone, Blocks.cobblestone, ComponentListMF.plank);
		Salvage.addSalvage(ToolListMF.tongsStone, new ItemStack(Blocks.cobblestone, 2), ComponentListMF.plank);
		Salvage.addSalvage(ToolListMF.needleBone, Items.bone);
		Salvage.addSalvage(ToolListMF.pestle, new ItemStack(Items.flint, 2));
	}
	private static void addPrimitive() 
	{
		KnowledgeListMF.stonePickR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.pickStone), "", primitive, "hands", -1, 5, new Object[]
		{
			"RVR",
			" S ",
			" S ",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneAxeR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.axeStone), "", primitive, "hands", -1, 5, new Object[]
		{
			"RV",
			"RS",
			" S",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneSpadeR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.spadeStone), "", primitive, "hands", -1, 5, new Object[]
		{
			"VR",
			" S",
			" S",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneHoeR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.hoeStone), "", primitive, "hands", -1, 5, new Object[]
		{
			"RV",
			" S",
			" S",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneSwordR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.swordStone), "", primitive, "hands", -1, 8, new Object[]
		{
			"R ",
			"R ",
			"SV",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneWarR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.waraxeStone), "", primitive, "hands", -1, 8, new Object[]
		{
			"VRV",
			"RS",
			" S",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneMaceR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.maceStone), "", primitive, "hands", -1, 8, new Object[]
		{
			" V ",
			"RSR",
			" S ",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneSpearR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.spearStone), "", primitive, "hands", -1, 8, new Object[]
		{
			"R",
			"V",
			"S",
			"S",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneKnifeR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.knifeStone), "", primitive, "hands", -1, 4, new Object[]
		{
			"R ",
			"SV",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.stoneHammerR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.hammerStone), "", primitive, "hands", -1, 4, new Object[]
		{
			"R",
			"V",
			"S",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		
		KnowledgeListMF.stoneTongsR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.tongsStone), "", primitive, "hands", -1, 4, new Object[]
		{
			" R",
			"SV",
			'R', ComponentListMF.sharp_rock,
			'V', ComponentListMF.vine,
			'S', Items.stick
		});
		KnowledgeListMF.boneNeedleR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.needleBone), "", primitive, "hands", -1, 4, new Object[]
		{
			"B",
			'B', Items.bone,
		});
		KnowledgeListMF.pestleR =
		MineFantasyAPI.addCarpenterRecipe(null, new ItemStack(ToolListMF.pestle), "", primitive, "hands", -1, 4, new Object[]
		{
			"F",
			"F",
			'F', Items.flint,
		});
		Salvage.addSalvage(ToolListMF.pickStone, new ItemStack(ComponentListMF.sharp_rock, 2), new ItemStack(Items.stick, 2), ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.axeStone, new ItemStack(ComponentListMF.sharp_rock, 2), new ItemStack(Items.stick, 2), ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.spadeStone, ComponentListMF.sharp_rock, new ItemStack(Items.stick, 2), ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.hoeStone, ComponentListMF.sharp_rock, new ItemStack(Items.stick, 2), ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.swordStone, Items.stick, new ItemStack(ComponentListMF.sharp_rock, 2), ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.waraxeStone, new ItemStack(ComponentListMF.sharp_rock, 2), new ItemStack(Items.stick, 2), new ItemStack(ComponentListMF.vine, 2));
		Salvage.addSalvage(ToolListMF.maceStone, new ItemStack(ComponentListMF.sharp_rock, 2), new ItemStack(Items.stick, 2), ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.spearStone, ComponentListMF.sharp_rock, new ItemStack(Items.stick, 2), ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.knifeStone, ComponentListMF.sharp_rock, Items.stick, ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.hammerStone, ComponentListMF.sharp_rock, Items.stick, ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.tongsStone, ComponentListMF.sharp_rock, Items.stick, ComponentListMF.vine);
		Salvage.addSalvage(ToolListMF.needleBone, Items.bone);
		Salvage.addSalvage(ToolListMF.pestle, new ItemStack(Items.flint, 2));
	}
}
