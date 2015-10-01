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
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class KnowledgeListMF
{
	public static InformationPage artisanry = InformationList.artisanry;
	public static InformationPage construction = InformationList.construction;
	public static InformationPage engineering = InformationList.engineering;
	public static InformationPage provisioning = InformationList.provisioning;
	public static InformationPage mastery = InformationList.mastery;
	
	//BASICS -FREE
	public static InformationBase carpenter, gettingStarted, research, talisman, ores, chimney, tanning, commodities, craftCrafters, stamina, combat, craftArmourBasic;
	public static InformationBase crucible, crucible2, smeltCopper, smeltBronze, smeltIron, blastfurn, smeltPig, smeltSteel, encrusted, smeltBlackSteel, smeltDragonforge, smeltBlueSteel, smeltRedSteel, smeltMithril, smeltAdamant, smeltMaster, smeltMithium, smeltIgnotumite, smeltEnderforge;
    public static InformationBase bellows, trough, forge, anvil, apron, craftTools, craftAdvTools, craftWeapons, craftAdvWeapons, arrows, craftOrnateWeapons, craftAdvOrnateWeapons, craftArmourLight, craftArmourMedium, craftArmourHeavy, arrowsBodkin, arrowsBroad, repair_basic, repair_advanced, repair_ornate;
	public static InformationBase etools, ecomponents, climber, spyglass, parachute, syringe, engTanner, advforge, blackpowder, advblackpowder, bombs, bpress, bombarrow, bombFuse, shrapnel, firebomb, stickybomb, bombCeramic, bombIron, bombObsidian, bombCrystal, mineCeramic, mineIron, mineObsidian, mineCrystal;
    public static InformationBase refined_planks, reinforced_stone, clay_wall, glass, brickworks, bars, thatch;
    
    public static InformationBase cookingutensil, generic_meat, stew, jerky, sandwitch, meatpie, shepardpie, bread, oats, salt, berry, icing, sweetroll, cake, carrotcake, chococake, bfcake, applepie, berrypie, cheese, cheeseroll, bandage, bandageadv;
    
    public static void init()
	{
    	carpenter = (new InformationBase("carpenter", 		        0, -3,  0, BlockListMF.carpenter, (InformationBase)null)).registerStat().setUnlocked();
    	gettingStarted = (new InformationBase("gettingStarted", 		0, 0,  0, Items.book, (InformationBase)null)).registerStat().setUnlocked();
    	research = (new InformationBase("research", 				    1, 1, 0, ToolListMF.researchBook, (InformationBase)null)).registerStat().setUnlocked();
    	talisman = (new InformationBase("talisman", 				    4, 2,  0, ComponentListMF.talisman_lesser, research)).registerStat().setUnlocked();
    	ores = (new InformationBase("ores",  						    1, -2, 0, BlockListMF.oreCopper, (InformationBase)null)).registerStat().setUnlocked();
    	chimney = (new InformationBase("chimney",  				    0, 2, 0, BlockListMF.chimney_stone, (InformationBase)null)).registerStat().setUnlocked();
    	tanning = (new InformationBase("tanning", 					0, -2 ,0, Items.leather, (InformationBase)null)).registerStat().setUnlocked().setSpecial();
    	commodities = (new InformationBase("commodities",				-1 ,-2 ,0, ComponentListMF.plank, (InformationBase)null)).registerStat().setUnlocked();
    	craftCrafters = (new InformationBase("craftCrafters", 		-1, 1, 0, ToolListMF.hammers[3], (InformationBase)null)).registerStat().setUnlocked();
    	stamina = (new InformationBase("stamina", 					-3, 1, 0, Items.feather, craftCrafters)).registerStat().setUnlocked();
    	combat = (new InformationBase("combat", 						-5, 2, 0, Items.iron_sword, stamina)).registerStat().setUnlocked();
    	craftArmourBasic = (new InformationBase("craftArmourBasic",   -5, 0, 5, ArmourListMF.armour(ArmourListMF.leather, 0, 1), combat)).registerStat().setUnlocked();
    	
    	//ARTISANRY -From Not very to the most Expensive
    	crucible = (new InformationBase("crucible", 					4, 0,  5, BlockListMF.crucible, (InformationBase)null)).registerStat().setPage(artisanry).setUnlocked().setSpecial();
    	crucible2 = (new InformationBase("crucible2",  				6, 0, 10, BlockListMF.crucibleadv_active, crucible)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 40);
    	smeltCopper = (new InformationBase("smeltCopper",  			1, 0, 0, ComponentListMF.ingots[0], (InformationBase)null)).registerStat().setPage(artisanry).setUnlocked();
    	smeltBronze = (new InformationBase("smeltBronze",  			1, 2,  5, ComponentListMF.ingots[2], crucible)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 5);
        smeltIron = (new InformationBase("smeltIron",  			    1, 4,  5, Items.iron_ingot, smeltBronze)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 10);
        blastfurn = (new InformationBase("blastfurn",  				2, 5, 15, BlockListMF.blast_heater_active, smeltIron)).registerStat().setPage(artisanry).setSpecial().addSkill(SkillList.artisanry, 25);
        smeltPig = (new InformationBase("smeltPig",  					3, 3, 0, ComponentListMF.ingots[3], blastfurn)).registerStat().setPage(artisanry).setUnlocked().addSkill(SkillList.artisanry, 25);
        smeltSteel = (new InformationBase("smeltSteel",  				4, 5, 0, ComponentListMF.ingots[4], smeltPig)).registerStat().setPage(artisanry).setUnlocked().addSkill(SkillList.artisanry, 25);
        encrusted = (new InformationBase("smeltEncrusted",  			6, 5, 10, ComponentListMF.diamond_shards, smeltSteel)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 35);
        smeltBlackSteel = (new InformationBase("smeltBlackSteel",		4, 7, 10, ComponentListMF.ingots[7], smeltSteel)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 50);
        smeltDragonforge = (new InformationBase("smeltDragonforge",	6, 7, 15, ToolListMF.swords[7], smeltBlackSteel)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 60);
        smeltRedSteel = (new InformationBase("smeltRedSteel", 		3, 9, 10, ComponentListMF.ingots[10], smeltBlackSteel)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 65);
        smeltBlueSteel = (new InformationBase("smeltBlueSteel", 		5, 9, 10, ComponentListMF.ingots[12], smeltBlackSteel)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 65);
        smeltMithril = (new InformationBase("smeltMithril", 			5, 11, 30, ComponentListMF.ingots[14], smeltBlueSteel)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 75);
        smeltAdamant = (new InformationBase("smeltAdamantium", 		3, 11, 30, ComponentListMF.ingots[13], smeltRedSteel)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 75);
        
        smeltMaster = (new InformationBase("smeltMaster", 			4, 13, 50,  BlockListMF.oreMythic, (InformationBase)null)).registerStat().setPage(artisanry).setSpecial().addSkill(SkillList.artisanry, 100);
        smeltIgnotumite = (new InformationBase("smeltIgnotumite", 	2, 15, 100, ComponentListMF.ingots[15], smeltMaster)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 100);
        smeltMithium = (new InformationBase("smeltMithium", 			6, 15, 100, ComponentListMF.ingots[16], smeltMaster)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 100);
        smeltEnderforge = (new InformationBase("smeltEnderforge", 	4, 16, 100, ComponentListMF.ingots[17], smeltMaster)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 100);
        
        bellows = (new InformationBase("bellows", 					 0, -1,0, BlockListMF.bellows, (InformationBase)null)).registerStat().setPage(artisanry).setUnlocked();
        trough = (new InformationBase("trough", 					 	 0, -2,0, BlockListMF.trough_wood, bellows)).registerStat().setPage(artisanry).setUnlocked();
        forge = (new InformationBase("forge", 						 0, 0, 0, BlockListMF.forge, (InformationBase)null)).registerStat().setPage(artisanry).setUnlocked();
        anvil = (new InformationBase("anvil", 						-1, 0, 0, BlockListMF.anvil[1], forge)).registerStat().setPage(artisanry).setUnlocked().setSpecial();
        apron = (new InformationBase("apron", 						-1, -1, 0, ArmourListMF.leatherapron, anvil)).registerStat().setPage(artisanry).setUnlocked();
        craftTools = (new InformationBase("craftTools", 				-3, 2, 0, ToolListMF.picks[3], anvil)).registerStat().setPage(artisanry).setUnlocked();
        craftAdvTools = (new InformationBase("craftAdvTools", 		-5, 2, 0, ToolListMF.hvypicks[2], craftTools)).registerStat().setPage(artisanry).setUnlocked();
        craftWeapons = (new InformationBase("craftWeapons", 			-3, 1, 5, ToolListMF.swords[4], anvil)).registerStat().setPage(artisanry).setUnlocked();
        craftAdvWeapons = (new InformationBase("craftAdvWeapons",     -5, 1, 0, ToolListMF.battleaxes[3], craftWeapons)).registerStat().setPage(artisanry).setUnlocked();
        arrows = (new InformationBase("arrows", 		  		   		 -3, 4, 0, ToolListMF.arrows[4], anvil)).registerStat().setPage(artisanry).setUnlocked();
        
        craftOrnateWeapons = (new InformationBase("craftOrnateWeapons",  			-3, -1, 10, ToolListMF.swords[3], craftWeapons)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 25);
        craftAdvOrnateWeapons = (new InformationBase("craftAdvOrnateWeapons", 	-5, -1, 15, ToolListMF.battleaxes[2], craftOrnateWeapons)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 30);
        craftArmourLight = (new InformationBase("craftArmourLight", 			-3, 3, 5, ArmourListMF.armour(ArmourListMF.leather, 2, 1), anvil)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 0);
        craftArmourMedium = (new InformationBase("craftArmourMedium", 		-4, 3, 10, ArmourListMF.armour(ArmourListMF.chainmail, 3, 1), craftArmourLight)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 0);
        craftArmourHeavy = (new InformationBase("craftArmourHeavy",       	-5, 3, 10, ArmourListMF.armour(ArmourListMF.fieldplate, 3, 1), craftArmourMedium)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 10);
        arrowsBodkin = (new InformationBase("arrowsBodkin", 		  	 -4, 5, 10, ToolListMF.bodkinArrows[3], arrows)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 10);
        arrowsBroad = (new InformationBase("arrowsBroad", 		  	 -5, 5, 10, ToolListMF.broadArrows[3], arrows)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 20);
        
        //ENGINEERING - Highly Expensive
        etools = (new InformationBase("etools", 					    3, 0, 0, ToolListMF.spanner, (InformationBase)null)).registerStat().setPage(engineering).setUnlocked();
        ecomponents = (new InformationBase("ecomponents",             5, 0, 0, ComponentListMF.bolt, etools)).registerStat().setPage(engineering).setUnlocked();
        climber = (new InformationBase("climber",             		7, 0, 10, ToolListMF.climbing_pick_basic, ecomponents)).registerStat().setPage(engineering).setUnlocked();
        spyglass = (new InformationBase("spyglass",             		8, 1, 10, new ItemStack(ToolListMF.spyglass, 1, 2), climber)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 5);
        parachute = (new InformationBase("parachute",             	9, 2, 20, ToolListMF.parachute, climber)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 20);
        syringe = (new InformationBase("syringe",             	    5, -2, 20, ToolListMF.syringe_empty, ecomponents)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 25);
        engTanner = (new InformationBase("engTanner",             	5,  2, 30, BlockListMF.engTanner, ecomponents)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 40).addSkill(SkillList.artisanry, 25);
        advforge = (new InformationBase("advforge",             		7,  3, 30, BlockListMF.forge_metal, engTanner)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 75).addSkill(SkillList.artisanry, 50);
        
        blackpowder = (new InformationBase("blackpowder", 			0, 0, 10, ComponentListMF.blackpowder, (InformationBase)null)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 20);
        advblackpowder = (new InformationBase("advblackpowder", 		2, -2, 30, ComponentListMF.blackpowder_advanced, blackpowder)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 50);
        bombs = (new InformationBase("bombs", 						0, 2, 20, ToolListMF.bomb_custom, blackpowder)).registerStat().setPage(engineering).setSpecial().setUnlocked();
        bpress = (new InformationBase("bpress", 					   -1, 1, 40, BlockListMF.bombPress, bombs)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 30);
        bombarrow = (new InformationBase("bombarrow", 			    1, 1, 40, ToolListMF.exploding_arrow, bombs)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 40);
        bombFuse = (new InformationBase("bombFuse", 		 		    2, 2, 0, ComponentListMF.bomb_fuse, bombs)).registerStat().setUnlocked().setPage(engineering);
        shrapnel = (new InformationBase("shrapnel", 				    0, 4, 20, ComponentListMF.shrapnel, bombs)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 25);
        firebomb = (new InformationBase("firebomb", 		  		    0, 6, 30, Items.blaze_powder, shrapnel)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 85);
        stickybomb = (new InformationBase("stickybomb", 			   -2, 2, 30, Items.slime_ball, bombs)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 50);
        bombCeramic = (new InformationBase("bombCeramic", 		    2, 3, 0, ComponentListMF.bomb_casing, bombs)).registerStat().setUnlocked().setPage(engineering);
        bombIron = (new InformationBase("bombIron", 				    4, 5, 10, ComponentListMF.bomb_casing_iron, bombCeramic)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 20);
        bombObsidian = (new InformationBase("bombObsidian", 		    4, 7, 15, ComponentListMF.bomb_casing_obsidian, bombIron)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 35);
        bombCrystal = (new InformationBase("bombCrystal", 		    2, 9, 20, ComponentListMF.bomb_casing_crystal, bombObsidian)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 80);
        mineCeramic = (new InformationBase("mineCeramic", 		   -2, 3, 5,  ComponentListMF.mine_casing, bombCeramic)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 5);
        mineIron = (new InformationBase("mineIron", 				   -4, 5, 10, ComponentListMF.mine_casing_iron, mineCeramic)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 20);
        mineObsidian = (new InformationBase("mineObsidian", 		   -4, 7, 15, ComponentListMF.mine_casing_obsidian, mineIron)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 35);
        mineCrystal = (new InformationBase("mineCrystal", 		   -2, 9, 20, ComponentListMF.mine_casing_crystal, mineObsidian)).registerStat().setPage(engineering).addSkill(SkillList.engineering, 80);
        
        repair_basic = (new InformationBase("repair_basic",  		   8, 0, 5, BlockListMF.repair_basic, (InformationBase)null)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 10);
        repair_advanced = (new InformationBase("repair_advanced",    10, 0, 15, BlockListMF.repair_advanced, repair_basic)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 25);
        repair_ornate = (new InformationBase("repair_ornate",        12, 2, 20, BlockListMF.repair_ornate, repair_advanced)).registerStat().setPage(artisanry).addSkill(SkillList.artisanry, 50);
        
        refined_planks = (new InformationBase("refined_planks",      -1, 0, 0, BlockListMF.refined_planks, (InformationBase)null)).registerStat().setPage(construction).setUnlocked();
        reinforced_stone = (new InformationBase("reinforced_stone",     1, 0, 0, BlockListMF.reinforced_stone, (InformationBase)null)).registerStat().setPage(construction).setUnlocked();
        clay_wall = (new InformationBase("clay_wall",     0, -1, 0, BlockListMF.clayWall, (InformationBase)null)).registerStat().setPage(construction).setUnlocked();
        glass = (new InformationBase("glass",             0,  1, 0, BlockListMF.framed_glass, (InformationBase)null)).registerStat().setPage(construction).setUnlocked();
        brickworks = (new InformationBase("brickworks",   3,  0, 0, BlockListMF.cobble_brick, reinforced_stone)).registerStat().setPage(construction).setUnlocked();
        bars = (new InformationBase("bars",               0,  3, 0, BlockListMF.bars[2], glass)).registerStat().setPage(construction).setUnlocked();
        thatch = (new InformationBase("thatch",           0,  -3, 0, BlockListMF.thatch, clay_wall)).registerStat().setPage(construction).setUnlocked();
        
        //COOKING -The Cheapest
        cookingutensil = (new InformationBase("cookingutensil",-1, 0, 0, FoodListMF.pie_tray, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
        
        generic_meat = (new InformationBase("generic_meat", 0,  -1, 0, FoodListMF.generic_meat_uncooked, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
        stew = (new InformationBase("stew",               0,  -3, 0, FoodListMF.stew, generic_meat)).registerStat().setPage(provisioning).setUnlocked();
        jerky = (new InformationBase("jerky",             0,  -5, 5, FoodListMF.jerky, stew)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 10);
        sandwitch = (new InformationBase("sandwitch",     1,  -7, 15, FoodListMF.sandwitch_meat, jerky)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 15);
        meatpie = (new InformationBase("meatpie",   		-1,  -7, 20, BlockListMF.pie_meat, jerky)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 35);
        shepardpie = (new InformationBase("shepardpie",  	-2,  -9, 25, BlockListMF.pie_shepards, meatpie)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 80);
        bread = (new InformationBase("bread",             1,   0, 0, FoodListMF.breadroll, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
        oats = (new InformationBase("oats",               3,   0, 0, FoodListMF.oats, bread)).registerStat().setPage(provisioning).setUnlocked();
        
        salt = (new InformationBase("salt",            	-2,  -2, 0, FoodListMF.salt, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
        berry = (new InformationBase("berry",            	 0,  1, 0, FoodListMF.berries, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
        icing = (new InformationBase("icing",             -1,  2, 0, FoodListMF.icing, berry)).registerStat().setPage(provisioning).setUnlocked();
        sweetroll = (new InformationBase("sweetroll",      0,  3, 5, FoodListMF.sweetroll, berry)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 15);
        cake = (new InformationBase("cake",	 	         0,  5, 15, BlockListMF.cake_vanilla, sweetroll)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 25);
        carrotcake = (new InformationBase("carrotcake",	-1,  7, 10, BlockListMF.cake_carrot, cake)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 40);
        chococake = (new InformationBase("chococake",	 	 1,  7, 20, BlockListMF.cake_chocolate, cake)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 40);
        bfcake = (new InformationBase("bfcake",	 	     1,  9, 25, BlockListMF.cake_bf, chococake)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 90);
        berrypie = (new InformationBase("berrypie",	 	 2,  1, 10, BlockListMF.pie_berry, berry)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 20);
        applepie = (new InformationBase("applepie",	 	 4,  1, 10, BlockListMF.pie_apple, berrypie)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 30);
        
        cheese = (new InformationBase("cheese",	 		 1, -1, 0, BlockListMF.cheese_wheel, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
        cheeseroll = (new InformationBase("cheeseroll",	 3, -1, 10, FoodListMF.cheese_roll, cheese)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 15);
        
        bandage = (new InformationBase("bandage",	        -3, 0,  0,  ToolListMF.bandage_wool, (InformationBase)null)).registerStat().setPage(provisioning).setUnlocked();
        bandageadv = (new InformationBase("bandageadv",	-5, -1, 10, ToolListMF.bandage_tough, bandage)).registerStat().setPage(provisioning).addSkill(SkillList.provisioning, 40);
	}
    public static IRecipe hideHelmR, hideChestR, hideLegsR, hideBootsR;
    public static final ArrayList<IAnvilRecipe> hunkR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> ingotR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> talismanRecipe = new ArrayList<IAnvilRecipe>();
    public static IAnvilRecipe greatTalismanRecipe;
    
    public static ICarpenterRecipe refinedPlankBlockR;
    public static IRecipe clayWallR, framedGlassR, windowR, thatchR, thatchStairR;
    public static IAnvilRecipe framedStoneR;
    public static final ArrayList<IAnvilRecipe> barsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IRecipe> stoneBricksR = new ArrayList<IRecipe>();
    
    public static IRecipe plantOilR, plankRecipe, refinedPlankR, stickRecipe, stoneHammerR, stoneTongsR, boneNeedleR, stoneKnifeR, malletR, spoonR;
    public static IAnvilRecipe rivetR, nailR, fluxR;
    public static ICarpenterRecipe lStripsR, threadR, stringR, dryrocksR;
    
    public static Alloy[] reStone, bronze, steel, black, red, blue, mithril, adamantium, ignotumite, mithium, enderforge;
    public static IRecipe carpenterRecipe, tannerRecipe, stoneAnvilRecipe, forgeRecipe, apronRecipe;
    public static IAnvilRecipe coalDustR, kaoDustR, encrustedR, steelR, obsidianDustR, diamondR;
    public static IRecipe fireclayR, fireBrickR, fireBricksR;
    public static ICarpenterRecipe strongRackR, woodTroughRecipe, rockTroughRecipe, strongwoodTroughRecipe, bellowsRecipe, crucibleRecipe, advCrucibleRecipe, chimneyRecipe, wideChimneyRecipe, extractChimneyRecipe;
    
    public static IAnvilRecipe haftGold, haftSilver, haftObsidian, mailIgno, plateIgno, mailMithi, plateMithi, mailEnder, plateEnder;
    
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
    public static ICarpenterRecipe spyglassR, blackpowderRec, advblackpowderRec, bombBenchCraft, bombPressCraft, advancedForgeR, engTannerR;
    public static ICarpenterRecipe bombFuseR, longFuseR;
    public static ICarpenterRecipe magmaRefinedR;
    public static IAnvilRecipe shrapnelR;
    
    public static ICarpenterRecipe bombCaseCeramicR, mineCaseCeramicR, bombCaseCrystalR, mineCaseCrystalR;
    public static IAnvilRecipe bombCaseIronR, mineCaseIronR, bombCaseObsidianR, mineCaseObsidianR;
    
    public static final ArrayList<ICarpenterRecipe> meatRecipes = new ArrayList<ICarpenterRecipe>();
    public static ICarpenterRecipe meatStripR, meatHunkR, minceR;
    public static IRecipe researchTableRecipe, refinedBowlR, meatpieOut, shepardOut, berryOut, appleOut, caketinRecipe, pietrayRecipe;
    public static ICarpenterRecipe pastryRecipe, doughRecipe, breadRecipe, curdRecipe, flourRecipe, oatsRecipe, icingRecipe, stewRecipe, jerkyRecipe, meatPieRecipe, sandwitchRecipe, shepardRecipe, sweetrollRecipe, iceSR;
    public static ICarpenterRecipe berryR, appleR, cheeserollR, cakeR, carrotCakeR, chocoCakeR, bfCakeR, cakeI, carrotCakeI, chocoCakeI, bfCakeI;

    public static ICarpenterRecipe syringeR, parachuteR, bandageR, badBandageR, goodBandageR;
    
    public static IAnvilRecipe spannerRecipe, blkspannerR, climbPickbR, iframeR, boltR, istrutR, bgearR, stubeR, eatoolsR, bombarrowR;
}
