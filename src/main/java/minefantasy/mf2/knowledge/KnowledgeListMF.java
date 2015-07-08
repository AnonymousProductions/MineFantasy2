package minefantasy.mf2.knowledge;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
import minefantasy.mf2.api.crafting.carpenter.ICarpenterRecipe;
import minefantasy.mf2.api.knowledge.InformationBase;
import minefantasy.mf2.api.knowledge.InformationList;
import minefantasy.mf2.api.knowledge.InformationPage;
import minefantasy.mf2.api.refine.Alloy;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;

public class KnowledgeListMF
{
	public static InformationPage artisanry = InformationList.artisanry;
	public static InformationPage construction = InformationList.construction;
	public static InformationPage engineering = InformationList.engineering;
	public static InformationPage provisioning = InformationList.provisioning;
	public static InformationPage mastery = InformationList.mastery;
	
	public static InformationBase carpenter = (new InformationBase("carpenter", 		        0, -3,  0, BlockListMF.carpenter, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase gettingStarted = (new InformationBase("gettingStarted", 		0, 0,  0, Items.book, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase research = (new InformationBase("research", 				    1, 1, 0, ToolListMF.researchBook, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase scroll = (new InformationBase("scroll", 						3, 1,  0, ToolListMF.research_scroll, research)).registerStat().setUnlocked();
	public static InformationBase ores = (new InformationBase("ores",  						    1, -2, 0, BlockListMF.oreCopper, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase chimney = (new InformationBase("chimney",  				    0, 2, 0, BlockListMF.chimney_stone, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase tanning = (new InformationBase("tanning", 					0, -2 ,0, Items.leather, (InformationBase)null)).registerStat().setUnlocked().setSpecial();
	public static InformationBase commodities = (new InformationBase("commodities",				-1 ,-2 ,0, ComponentListMF.plank, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase craftCrafters = (new InformationBase("craftCrafters", 		-1, 1, 0, ToolListMF.hammers[3], (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase stamina = (new InformationBase("stamina", 					-3, 1, 0, Items.feather, craftCrafters)).registerStat().setUnlocked();
	public static InformationBase combat = (new InformationBase("combat", 						-5, 2, 0, Items.iron_sword, stamina)).registerStat().setUnlocked();
    	
	public static InformationBase crucible = (new InformationBase("crucible", 					4, 0,  5, BlockListMF.crucible, (InformationBase)null)).registerStat().setPage(artisanry).setUnlocked().setSpecial();
	public static InformationBase crucible2 = (new InformationBase("crucible2",  				6, 0, 10, BlockListMF.crucibleadv_active, crucible)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 40);
	
	public static InformationBase smeltCopper = (new InformationBase("smeltCopper",  			1, 0, 0, ComponentListMF.ingots[0], (InformationBase)null)).registerStat().setPage(artisanry).setUnlocked();
	public static InformationBase smeltBronze = (new InformationBase("smeltBronze",  			1, 2,  5, ComponentListMF.ingots[2], crucible)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 5);
    public static InformationBase smeltIron = (new InformationBase("smeltIron",  			    1, 4,  5, Items.iron_ingot, smeltBronze)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 15);
    public static InformationBase blastfurn = (new InformationBase("blastfurn",  				2, 5, 15, BlockListMF.blast_heater_active, smeltIron)).registerStat().setPage(artisanry).setSpecial().addSkill(SkillList.metallurgy, 30);
    public static InformationBase smeltSteel = (new InformationBase("smeltSteel",  				4, 5, 10, ComponentListMF.ingots[4], blastfurn)).registerStat().setPage(artisanry).setUnlocked().addSkill(SkillList.metallurgy, 30);
    public static InformationBase encrusted = (new InformationBase("smeltEncrusted",  			6, 5, 10, ComponentListMF.diamond_shards, smeltSteel)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 35);
    public static InformationBase smeltBlackSteel = (new InformationBase("smeltBlackSteel",		4, 7, 10, ComponentListMF.ingots[7], smeltSteel)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 50);
    public static InformationBase smeltDragonforge = (new InformationBase("smeltDragonforge",	6, 7, 50, ToolListMF.swords[7], smeltBlackSteel)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 60);
    public static InformationBase smeltRedSteel = (new InformationBase("smeltRedSteel", 		3, 9, 15, ComponentListMF.ingots[10], smeltBlackSteel)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 65);
    public static InformationBase smeltBlueSteel = (new InformationBase("smeltBlueSteel", 		5, 9, 15, ComponentListMF.ingots[12], smeltBlackSteel)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 65);
    public static InformationBase smeltMithril = (new InformationBase("smeltMithril", 			5, 11, 30, ComponentListMF.ingots[14], smeltBlueSteel)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 75);
    public static InformationBase smeltAdamant = (new InformationBase("smeltAdamantium", 			3, 11, 30, ComponentListMF.ingots[13], smeltRedSteel)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 75);
    
    public static InformationBase bellows = (new InformationBase("bellows", 					 0, -1,0, BlockListMF.bellows, (InformationBase)null)).registerStat().setPage(artisanry).setUnlocked();
    public static InformationBase forge = (new InformationBase("forge", 						 0, 0, 0, BlockListMF.forge, (InformationBase)null)).registerStat().setPage(artisanry).setUnlocked();
    public static InformationBase anvil = (new InformationBase("anvil", 						-1, 0, 0, BlockListMF.anvil[1], forge)).registerStat().setPage(artisanry).setUnlocked().setSpecial();
    public static InformationBase craftTools = (new InformationBase("craftTools", 				-3, 2, 0, ToolListMF.picks[3], anvil)).registerStat().setPage(artisanry).setUnlocked();
    public static InformationBase craftAdvTools = (new InformationBase("craftAdvTools", 		-5, 2, 10, ToolListMF.hvypicks[2], craftTools)).registerStat().setPage(artisanry).setUnlocked();
    public static InformationBase craftWeapons = (new InformationBase("craftWeapons", 			-3, 1, 5, ToolListMF.swords[4], anvil)).registerStat().setPage(artisanry).setUnlocked();
    public static InformationBase craftAdvWeapons = (new InformationBase("craftAdvWeapons",     -5, 1, 10, ToolListMF.battleaxes[3], craftWeapons)).registerStat().setPage(artisanry).setUnlocked();
    public static InformationBase craftOrnateWeapons = (new InformationBase("craftOrnateWeapons",  			-3, -1, 10, ToolListMF.swords[3], craftWeapons)).registerStat().setPage(artisanry);
    public static InformationBase craftAdvOrnateWeapons = (new InformationBase("craftAdvOrnateWeapons", 	-5, -1, 25, ToolListMF.battleaxes[2], craftOrnateWeapons)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 0);
    public static InformationBase craftArmourLight = (new InformationBase("craftArmourLight", 			-3, 3, 5, ArmourListMF.armour(ArmourListMF.leather, 2, 1), anvil)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 0);
    public static InformationBase craftArmourMedium = (new InformationBase("craftArmourMedium", 		-4, 3, 10, ArmourListMF.armour(ArmourListMF.chainmail, 3, 1), craftArmourLight)).registerStat().setPage(artisanry);
    public static InformationBase craftArmourHeavy = (new InformationBase("craftArmourHeavy",       	-5, 3, 10, ArmourListMF.armour(ArmourListMF.fieldplate, 3, 1), craftArmourMedium)).registerStat().setPage(artisanry).addSkill(SkillList.metallurgy, 10);
    public static InformationBase arrows = (new InformationBase("arrows", 		  		   		 -3, 4, 10, ToolListMF.arrows[4], anvil)).registerStat().setPage(artisanry).setUnlocked();
    public static InformationBase arrowsBodkin = (new InformationBase("arrowsBodkin", 		  	 -4, 5, 15, ToolListMF.bodkinArrows[3], arrows)).registerStat().setPage(artisanry);
    public static InformationBase arrowsBroad = (new InformationBase("arrowsBroad", 		  	 -5, 5, 20, ToolListMF.broadArrows[3], arrows)).registerStat().setPage(artisanry);
    
    public static InformationBase blackpowder = (new InformationBase("blackpowder", 			0, 0, 10, ComponentListMF.blackpowder, (InformationBase)null)).registerStat().setPage(engineering);
    public static InformationBase advblackpowder = (new InformationBase("advblackpowder", 		2, -2, 30, ComponentListMF.blackpowder_advanced, blackpowder)).registerStat().setPage(engineering);
    public static InformationBase bombs = (new InformationBase("bombs", 						0, 2, 20, ToolListMF.bomb_custom, blackpowder)).registerStat().setPage(engineering).setSpecial().setUnlocked();
    public static InformationBase bombFuse = (new InformationBase("bombFuse", 		 		    2, 2, 0, ComponentListMF.bomb_fuse, bombs)).registerStat().setUnlocked().setPage(engineering);
    public static InformationBase shrapnel = (new InformationBase("shrapnel", 				    0, 4, 30, ComponentListMF.shrapnel, bombs)).registerStat().setPage(engineering);
    public static InformationBase firebomb = (new InformationBase("firebomb", 		  		    0, 6, 50, Items.blaze_powder, shrapnel)).registerStat().setPage(engineering);
    public static InformationBase stickybomb = (new InformationBase("stickybomb", 			   -2, 2, 50, Items.slime_ball, bombs)).registerStat().setPage(engineering);
    public static InformationBase bombCeramic = (new InformationBase("bombCeramic", 		    2, 3, 0, ComponentListMF.bomb_casing, bombs)).registerStat().setUnlocked().setPage(engineering);
    public static InformationBase bombIron = (new InformationBase("bombIron", 				    4, 5, 20, ComponentListMF.bomb_casing_iron, bombCeramic)).registerStat().setPage(engineering);
    public static InformationBase bombObsidian = (new InformationBase("bombObsidian", 		    4, 7, 35, ComponentListMF.bomb_casing_obsidian, bombIron)).registerStat().setPage(engineering);
    public static InformationBase bombCrystal = (new InformationBase("bombCrystal", 		    2, 9, 50, ComponentListMF.bomb_casing_crystal, bombObsidian)).registerStat().setPage(engineering);
    public static InformationBase mineCeramic = (new InformationBase("mineCeramic", 		   -2, 3, 10,  ComponentListMF.mine_casing, bombCeramic)).registerStat().setPage(engineering);
    public static InformationBase mineIron = (new InformationBase("mineIron", 				   -4, 5, 30, ComponentListMF.mine_casing_iron, mineCeramic)).registerStat().setPage(engineering);
    public static InformationBase mineObsidian = (new InformationBase("mineObsidian", 		   -4, 7, 40, ComponentListMF.mine_casing_obsidian, mineIron)).registerStat().setPage(engineering);
    public static InformationBase mineCrystal = (new InformationBase("mineCrystal", 		   -2, 9, 65, ComponentListMF.mine_casing_crystal, mineObsidian)).registerStat().setPage(engineering);
    
    public static InformationBase repair_basic = (new InformationBase("repair_basic",  		   8, 0, 5, BlockListMF.repair_basic, (InformationBase)null)).registerStat().setPage(artisanry);
    public static InformationBase repair_advanced = (new InformationBase("repair_advanced",    10, 0, 20, BlockListMF.repair_advanced, repair_basic)).registerStat().setPage(artisanry);
    public static InformationBase repair_ornate = (new InformationBase("repair_ornate",        12, 2, 35, BlockListMF.repair_ornate, repair_advanced)).registerStat().setPage(artisanry);
    
    public static InformationBase refined_planks = (new InformationBase("refined_planks",      -1, 0, 0, BlockListMF.refined_planks, (InformationBase)null)).registerStat().setPage(construction).setUnlocked();
    public static InformationBase reinforced_stone = (new InformationBase("reinforced_stone",     1, 0, 0, BlockListMF.reinforced_stone, (InformationBase)null)).registerStat().setPage(construction).setUnlocked();
    public static InformationBase clay_wall = (new InformationBase("clay_wall",     0, -1, 0, BlockListMF.clayWall, (InformationBase)null)).registerStat().setPage(construction).setUnlocked();
    public static InformationBase glass = (new InformationBase("glass",             0,  1, 0, BlockListMF.framed_glass, (InformationBase)null)).registerStat().setPage(construction).setUnlocked();
    public static InformationBase brickworks = (new InformationBase("brickworks",   3,  0, 0, BlockListMF.cobble_brick, reinforced_stone)).registerStat().setPage(construction).setUnlocked();
    public static InformationBase bars = (new InformationBase("bars",               0,  3, 0, BlockListMF.bars[2], glass)).registerStat().setPage(construction).setUnlocked();
    public static InformationBase thatch = (new InformationBase("thatch",           0,  -3, 0, BlockListMF.thatch, clay_wall)).registerStat().setPage(construction).setUnlocked();
    
    public static InformationBase cookingutensil = (new InformationBase("cookingutensil",-1, 0, 0, FoodListMF.pie_tray, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
    
    public static InformationBase generic_meat = (new InformationBase("generic_meat", 0,  -1, 0, FoodListMF.generic_meat_uncooked, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
    public static InformationBase stew = (new InformationBase("stew",               0,  -3, 0, FoodListMF.stew, generic_meat)).registerStat().setPage(provisioning).setUnlocked();
    public static InformationBase jerky = (new InformationBase("jerky",             0,  -5, 10, FoodListMF.jerky, stew)).registerStat().setPage(provisioning);
    public static InformationBase sandwitch = (new InformationBase("sandwitch",     1,  -7, 15, FoodListMF.sandwitch_meat, jerky)).registerStat().setPage(provisioning);
    public static InformationBase meatpie = (new InformationBase("meatpie",   		-1,  -7, 20, BlockListMF.pie_meat, jerky)).registerStat().setPage(provisioning);
    public static InformationBase shepardpie = (new InformationBase("shepardpie",  	-2,  -9, 25, BlockListMF.pie_shepards, meatpie)).registerStat().setPage(provisioning);
    public static InformationBase bread = (new InformationBase("bread",             1,   0, 0, FoodListMF.breadroll, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
    public static InformationBase oats = (new InformationBase("oats",               3,   0, 0, FoodListMF.oats, bread)).registerStat().setPage(provisioning).setUnlocked();
    
    public static InformationBase berry = (new InformationBase("berry",            	 0,  1, 0, FoodListMF.berries, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
    public static InformationBase icing = (new InformationBase("icing",             -1,  2, 0, FoodListMF.icing, berry)).registerStat().setPage(provisioning).setUnlocked();
    public static InformationBase sweetroll = (new InformationBase("sweetroll",      0,  3, 10, FoodListMF.sweetroll, berry)).registerStat().setPage(provisioning);
    public static InformationBase cake = (new InformationBase("cake",	 	         0,  5, 15, BlockListMF.cake_vanilla, sweetroll)).registerStat().setPage(provisioning);
    public static InformationBase carrotcake = (new InformationBase("carrotcake",	-1,  7, 10, BlockListMF.cake_carrot, cake)).registerStat().setPage(provisioning);
    public static InformationBase chococake = (new InformationBase("chococake",	 	 1,  7, 20, BlockListMF.cake_chocolate, cake)).registerStat().setPage(provisioning);
    public static InformationBase bfcake = (new InformationBase("bfcake",	 	     1,  9, 25, BlockListMF.cake_bf, chococake)).registerStat().setPage(provisioning);
    public static InformationBase berrypie = (new InformationBase("berrypie",	 	 2,  1, 10, BlockListMF.pie_berry, berry)).registerStat().setPage(provisioning);
    public static InformationBase applepie = (new InformationBase("applepie",	 	 4,  1, 10, BlockListMF.pie_apple, berrypie)).registerStat().setPage(provisioning);
    
    public static InformationBase cheese = (new InformationBase("cheese",	 		 1, -1, 0, BlockListMF.cheese_wheel, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
    public static InformationBase cheeseroll = (new InformationBase("cheeseroll",	 3, -1, 10, FoodListMF.cheese_roll, cheese)).registerStat().setPage(provisioning);
    
    public static InformationBase bandage = (new InformationBase("bandage",	        -3, 0, 10,  ToolListMF.bandage_wool, (InformationBase)null)).registerStat().setPage(provisioning);
    public static InformationBase bandageadv = (new InformationBase("bandageadv",	-5, -1, 20, ToolListMF.bandage_tough, bandage)).registerStat().setPage(provisioning);
    
    
    public static void init()
	{
	}
    public static ICarpenterRecipe refinedPlankBlockR;
    public static IRecipe clayWallR, framedGlassR, windowR, thatchR, thatchStairR;
    public static IAnvilRecipe framedStoneR;
    public static final ArrayList<IAnvilRecipe> barsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IRecipe> stoneBricksR = new ArrayList<IRecipe>();
    
    public static IRecipe plantOilR, plankRecipe, refinedPlankR, stickRecipe, stoneHammerR, stoneTongsR, boneNeedleR, stoneKnifeR, malletR, spoonR;
    public static IAnvilRecipe rivetR, nailR, fluxR;
    public static ICarpenterRecipe lStripsR, threadR, stringR;
    
    public static Alloy[] reStone, bronze, steel, black, red, blue, mithril, adamantium;
    public static IRecipe carpenterRecipe, tannerRecipe, stoneAnvilRecipe, forgeRecipe;
    public static IAnvilRecipe coalDustR, kaoDustR, encrustedR, steelR, obsidianDustR, diamondR;
    public static IRecipe fireclayR, fireBrickR, fireBricksR;
    public static ICarpenterRecipe strongRackR, bellowsRecipe, crucibleRecipe, advCrucibleRecipe, chimneyRecipe, wideChimneyRecipe, extractChimneyRecipe;
    
    public static ICarpenterRecipe reHelmetR, reChestR, reLegsR, reBootsR;
    public static IAnvilRecipe studHelmetR, studChestR, studLegsR, studBootsR, scaleHelmR, scaleChestR, scaleLegsR, scaleBootsR;
    public static final ArrayList<IAnvilRecipe> mailRecipes = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> mailHelmetR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> mailChestR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> mailLegsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> mailBootsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateRecipes = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateHelmetR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateChestR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateLegsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateBootsR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> ornateWepsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> advOrnateWepsR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> pickR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> axeR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> spadeR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> hoeR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> shearsR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> daggerR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> swordR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> waraxeR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> maceR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> spearR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> bowR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> katanaR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> gswordR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> whammerR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> battleaxeR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> halbeardR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> lanceR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> trowR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> hvyShovelR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> handpickR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> hvyPickR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> scytheR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> matockR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> hammerR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> tongsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> hvyHammerR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> needleR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> sawsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> knifeR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> arrowheadR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> bodkinheadR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> broadheadR = new ArrayList<IAnvilRecipe>();
    public static ICarpenterRecipe fletchingR;
    public static final ArrayList<ICarpenterRecipe> arrowR = new ArrayList<ICarpenterRecipe>();
    
    public static IAnvilRecipe ironPrepR, ironPrepR2;
    public static IAnvilRecipe blastChamR, blastHeatR;
    
    public static ICarpenterRecipe padding[] = new ICarpenterRecipe[4];
    public static ICarpenterRecipe repairBasicR, repairAdvancedR, repairOrnateR;
    public static ICarpenterRecipe blackpowderRec, advblackpowderRec;
    public static IAnvilRecipe bombBenchCraft;
    public static ICarpenterRecipe bombFuseR, longFuseR;
    public static ICarpenterRecipe magmaRefinedR;
    public static IAnvilRecipe shrapnelR;
    
    public static ICarpenterRecipe bombCaseCeramicR, mineCaseCeramicR, bombCaseCrystalR, mineCaseCrystalR;
    public static IAnvilRecipe bombCaseIronR, mineCaseIronR, bombCaseObsidianR, mineCaseObsidianR;
    
    public static final ArrayList<ICarpenterRecipe> meatRecipes = new ArrayList<ICarpenterRecipe>();
    public static ICarpenterRecipe meatStripR, meatHunkR, minceR;
    public static IRecipe cheeseR, meatpieOut, shepardOut, berryOut, appleOut, caketinRecipe, pietrayRecipe;
    public static ICarpenterRecipe flourRecipe, rollRecipe, oatsRecipe, icingRecipe, stewRecipe, jerkyRecipe, meatPieRecipe, sandwitchRecipe, shepardRecipe, sweetrollRecipe, iceSR;
    public static ICarpenterRecipe berryR, appleR, cheeserollR, cakeR, carrotCakeR, chocoCakeR, bfCakeR, cakeI, carrotCakeI, chocoCakeI, bfCakeI;

    public static ICarpenterRecipe bandageR, badBandageR, goodBandageR;
}
