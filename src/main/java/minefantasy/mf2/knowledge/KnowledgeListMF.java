package minefantasy.mf2.knowledge;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
import minefantasy.mf2.api.crafting.carpenter.ICarpenterRecipe;
import minefantasy.mf2.api.knowledge.InformationBase;
import minefantasy.mf2.api.knowledge.InformationList;
import minefantasy.mf2.api.knowledge.InformationPage;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;

public class KnowledgeListMF
{
	public static InformationPage smithing = InformationList.smithing;
	public static InformationPage construction = InformationList.construction;
	public static InformationPage engineering = InformationList.engineering;
	public static InformationPage cooking = InformationList.cooking;
	public static InformationPage mastery = InformationList.mastery;
	
	public static InformationBase gettingStarted = (new InformationBase("gettingStarted", 		0, 0,  0, Items.book, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase ores = (new InformationBase("ores",  						   -2, -2, 0, BlockListMF.oreCopper, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase chimney = (new InformationBase("chimney",  				   -1, -2, 0, BlockListMF.chimney_stone, (InformationBase)null)).registerStat().setUnlocked();
	public static InformationBase research1 = (new InformationBase("research1", 				2, 1, 50, ToolListMF.researchBook, (InformationBase)null)).registerStat().setPerk();
	public static InformationBase research2 = (new InformationBase("research2", 				4, 2, 80, Items.enchanted_book, research1)).registerStat().setPerk();
	
	public static InformationBase tanning = (new InformationBase("tanning", 					2, -2,0, BlockListMF.tanner, (InformationBase)null)).registerStat().setUnlocked().setSpecial();
    public static InformationBase crucible = (new InformationBase("crucible", 					1, 0, 5, BlockListMF.crucible, (InformationBase)null)).registerStat().setPage(smithing).setUnlocked().setSpecial();
	
    public static InformationBase smeltBronze = (new InformationBase("smeltBronze",  			4, 0,  5, ComponentListMF.ingots[2], crucible)).registerStat().setPage(smithing).setUnlocked();
    
    public static InformationBase blastfurn = (new InformationBase("blastfurn",  				2, 3, 15, BlockListMF.blast_heater_active, crucible)).registerStat().setPage(smithing).setSpecial().addSkill(SkillList.metallurgy, 20);
    public static InformationBase smeltSteel = (new InformationBase("smeltSteel",  				4, 3, 10, ComponentListMF.ingots[4], blastfurn)).registerStat().setPage(smithing).setUnlocked().addSkill(SkillList.metallurgy, 20);
    public static InformationBase encrusted = (new InformationBase("encrusted",  				5, 2, 10, ComponentListMF.diamond_shards, smeltSteel)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 30);
    
    //public static InformationBase smeltSmart2 = (new InformationBase("smeltSmart2", 			4, 5, 20, Items.coal, smeltSteel)).registerStat().setPage(smithing).setPerk();
    public static InformationBase crucible2 = (new InformationBase("crucible2",  				5, 6, 10, BlockListMF.crucibleadv_active, blastfurn)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 40);
    public static InformationBase smeltBlackSteel = (new InformationBase("smeltBlackSteel",		7, 6, 10, ComponentListMF.ingots[7], crucible2)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 50);
    public static InformationBase smeltDragonforge = (new InformationBase("smeltDragonforge",	8, 5, 50, ToolListMF.swords[7], smeltBlackSteel)).registerStat().setPage(smithing);
    
    public static InformationBase crucible3 = (new InformationBase("crucible3",  				5, 8, 20, Blocks.furnace, crucible2)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 65);
    public static InformationBase smeltRedSteel = (new InformationBase("smeltRedSteel", 		3, 9, 15, ComponentListMF.ingots[10], crucible3)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 65);
    public static InformationBase smeltBlueSteel = (new InformationBase("smeltBlueSteel", 		7, 9, 15, ComponentListMF.ingots[12], crucible3)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 65);
    
	//public static InformationBase smeltSmart3 = (new InformationBase("smeltSmart3", 			5, 11, 20, Blocks.fire, crucible3)).setPage(smithing).registerStat();
	//public static InformationBase smeltSmart4 = (new InformationBase("smeltSmart4", 			7, 11, 50, Blocks.lava, smeltSmart3)).registerStat().setPage(smithing).setPerk();
	
    public static InformationBase mythic = (new InformationBase("mythic",  						5, 13, 30, Blocks.furnace, crucible3)).registerStat().setPage(smithing).setSpecial().addSkill(SkillList.metallurgy, 75);
    public static InformationBase smeltMithril = (new InformationBase("smeltMithril", 			4, 15, 30, ComponentListMF.ingots[14], mythic)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 75);
    public static InformationBase smeltAdamant = (new InformationBase("smeltAdamant", 			6, 15, 30, ComponentListMF.ingots[13], mythic)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 75);
    
    public static InformationBase anvil = (new InformationBase("anvil", 						-1, 0, 5, Blocks.anvil, (InformationBase)null)).registerStat().setPage(smithing).setUnlocked().setSpecial();
    public static InformationBase craftCrafters = (new InformationBase("craftCrafters", 		-1, -2, 5, ToolListMF.hammers[3], anvil)).registerStat().setPage(smithing).setUnlocked();
    public static InformationBase hvyHammer = (new InformationBase("hvyHammer", 				-1, -4, 5, ToolListMF.hvyHammers[2], craftCrafters)).registerStat().setPage(smithing);
    public static InformationBase craftTools = (new InformationBase("craftTools", 				-3, 2, 5, ToolListMF.picks[3], anvil)).registerStat().setPage(smithing);
    public static InformationBase craftAdvTools = (new InformationBase("craftAdvTools", 		-5, 2, 10, ToolListMF.hvypicks[2], craftTools)).registerStat().setPage(smithing);
    public static InformationBase craftWeapons = (new InformationBase("craftWeapons", 			-3, 1, 5, ToolListMF.swords[4], anvil)).registerStat().setPage(smithing);
    public static InformationBase craftAdvWeapons = (new InformationBase("craftAdvWeapons",     -5, 1, 10, ToolListMF.battleaxes[3], craftWeapons)).registerStat().setPage(smithing);
    public static InformationBase craftOrnateWeapons = (new InformationBase("craftOrnateWeapons",  -3, -1, 10, ToolListMF.swords[3], craftWeapons)).registerStat().setPage(smithing);
    public static InformationBase craftAdvOrnateWeapons = (new InformationBase("craftAdvOrnateWeapons", -5, -1, 25, ToolListMF.battleaxes[2], craftOrnateWeapons)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 25);
    public static InformationBase craftArmour = (new InformationBase("craftArmour", 			-3, 3, 10, ArmourListMF.armour(ArmourListMF.chainmail, 3, 1), anvil)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 30);
    public static InformationBase craftArmourAdv = (new InformationBase("craftArmourAdv",       -5, 3, 20, ArmourListMF.armour(ArmourListMF.fieldplate, 3, 1), craftArmour)).registerStat().setPage(smithing).addSkill(SkillList.metallurgy, 30);
    public static InformationBase arrows = (new InformationBase("arrows", 		  		   		 -1, 4, 10, ToolListMF.arrows[4], anvil)).registerStat().setPage(smithing);
    public static InformationBase arrowsBodkin = (new InformationBase("arrowsBodkin", 		  	 -2, 5, 15, ToolListMF.bodkinArrows[3], arrows)).registerStat().setPage(smithing);
    public static InformationBase arrowsBroad = (new InformationBase("arrowsBroad", 		  	 -2, 6, 20, ToolListMF.broadArrows[3], arrows)).registerStat().setPage(smithing);
    
    public static InformationBase blackpowder = (new InformationBase("blackpowder", 			0, 0, 10, ComponentListMF.blackpowder, (InformationBase)null)).registerStat().setPage(engineering);
    public static InformationBase advblackpowder = (new InformationBase("advblackpowder", 			2, -2, 30, ComponentListMF.blackpowder_advanced, blackpowder)).registerStat().setPage(engineering);
    public static InformationBase bombs = (new InformationBase("bombs", 						0, 2, 20, ToolListMF.bomb_custom, blackpowder)).registerStat().setPage(engineering).setSpecial();
    public static InformationBase bombFuse = (new InformationBase("bombFuse", 		 		    2, 2, 0, ComponentListMF.bomb_fuse, bombs)).registerStat().setUnlocked().setPage(engineering);
    public static InformationBase bombCeramic = (new InformationBase("bombCeramic", 		   -1, 3, 0, ComponentListMF.bomb_casing, bombs)).registerStat().setUnlocked().setPage(engineering);
    public static InformationBase bombIron = (new InformationBase("bombIron", 				   -1, 5, 20, ComponentListMF.bomb_casing_iron, bombCeramic)).registerStat().setPage(engineering);
    public static InformationBase bombObsidian = (new InformationBase("bombObsidian", 		   -1, 7, 35, ComponentListMF.bomb_casing_obsidian, bombIron)).registerStat().setPage(engineering);
    public static InformationBase mineCeramic = (new InformationBase("mineCeramic", 		   -2, 3, 10,  ComponentListMF.mine_casing, bombCeramic)).registerStat().setPage(engineering);
    public static InformationBase mineIron = (new InformationBase("mineIron", 				   -2, 5, 30, ComponentListMF.mine_casing_iron, mineCeramic)).registerStat().setPage(engineering);
    public static InformationBase mineObsidian = (new InformationBase("mineObsidian", 		   -2, 7, 40, ComponentListMF.mine_casing_obsidian, mineIron)).registerStat().setPage(engineering);
    public static InformationBase shrapnel = (new InformationBase("shrapnel", 				    1, 3, 30, ComponentListMF.shrapnel, bombs)).registerStat().setPage(engineering);
    public static InformationBase firebomb = (new InformationBase("firebomb", 		  		    1, 5, 50, Items.blaze_powder, shrapnel)).registerStat().setPage(engineering);
    public static InformationBase stickybomb = (new InformationBase("stickybomb", 			   -4, 2, 50, Items.slime_ball, bombs)).registerStat().setPage(engineering);
    public static InformationBase bombCrystal = (new InformationBase("bombCrystal", 		   -1, 9, 50, ComponentListMF.bomb_casing_crystal, bombObsidian)).registerStat().setPage(engineering);
    public static InformationBase mineCrystal = (new InformationBase("mineCrystal", 		   -2, 9, 65, ComponentListMF.mine_casing_crystal, mineObsidian)).registerStat().setPage(engineering);
    
    public static InformationBase repair_basic = (new InformationBase("repair_basic",  		   2, -2, 5, BlockListMF.repair_basic, (InformationBase)null)).registerStat().setPage(smithing);
    public static InformationBase repair_advanced = (new InformationBase("repair_advanced",    4, -2, 20, BlockListMF.repair_advanced, repair_basic)).registerStat().setPage(smithing);
    public static InformationBase repair_ornate = (new InformationBase("repair_ornate",    6, -3, 35, BlockListMF.repair_ornate, repair_advanced)).registerStat().setPage(smithing);
    
    public static void init()
	{
	}
    
    public static IRecipe tannerRecipe;
    public static IAnvilRecipe coalDustR, kaoDustR;
    public static IRecipe fireclayR, fireBrickR, fireBricksR;
    public static ICarpenterRecipe crucibleRecipe, advCrucibleRecipe, chimneyRecipe, wideChimneyRecipe, extractChimneyRecipe;
    
    public static final ArrayList<IAnvilRecipe> plateRecipes = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateHelmetR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateChestR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateLegsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> plateBootsR = new ArrayList<IAnvilRecipe>();
    
    public static final ArrayList<IAnvilRecipe> ornateWepsR = new ArrayList<IAnvilRecipe>();
    public static final ArrayList<IAnvilRecipe> advOrnateWepsR = new ArrayList<IAnvilRecipe>();
    
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
}
