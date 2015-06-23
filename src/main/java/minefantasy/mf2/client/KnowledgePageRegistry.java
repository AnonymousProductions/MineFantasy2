package minefantasy.mf2.client;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import minefantasy.mf2.api.knowledge.client.*;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.recipe.BasicRecipesMF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KnowledgePageRegistry
{
	public static void registerPages()
	{
		KnowledgeListMF.commodities.addPages(new EntryPageText("knowledge.commodities.paragraph_1"));
		KnowledgeListMF.commodities.addPages(new EntryPageText("knowledge.commodities.flux"), new EntryPageRecipeAnvil(KnowledgeListMF.fluxR));
		KnowledgeListMF.commodities.addPages(new EntryPageText("knowledge.commodities.nail"), new EntryPageRecipeAnvil(KnowledgeListMF.nailR));
		KnowledgeListMF.commodities.addPages(new EntryPageText("knowledge.commodities.rivet"), new EntryPageRecipeAnvil(KnowledgeListMF.rivetR));
		KnowledgeListMF.commodities.addPages(new EntryPageText("knowledge.commodities.leatherstrip"), new EntryPageRecipeCarpenter(KnowledgeListMF.lStripsR));
		KnowledgeListMF.commodities.addPages(new EntryPageText("knowledge.commodities.thread"), new EntryPageRecipeCarpenter(KnowledgeListMF.threadR), new EntryPageRecipeCarpenter(KnowledgeListMF.stringR));
		
		KnowledgeListMF.ores.addPages(new EntryPageText("knowledge.ores.paragraph_1"));
		KnowledgeListMF.ores.addPages(assembleOreDesc("copper", BlockListMF.oreCopper, ComponentListMF.ingots[0]));
		KnowledgeListMF.ores.addPages(assembleOreDesc("tin", BlockListMF.oreTin, ComponentListMF.ingots[1]));
		KnowledgeListMF.ores.addPages(assembleOreDesc("silver", BlockListMF.oreSilver, ComponentListMF.ingots[8]));
		KnowledgeListMF.ores.addPages(assembleOreDesc("mythic", BlockListMF.oreMythic));
		KnowledgeListMF.ores.addPages(new EntryPageText("knowledge.ores.paragraph_2"));
		KnowledgeListMF.ores.addPages(assembleOreDesc("clay", BlockListMF.oreClay));
		KnowledgeListMF.ores.addPages(assembleOreDesc("kaolinite", BlockListMF.oreKaolinite));
		KnowledgeListMF.ores.addPages(assembleOreDesc("limestone", BlockListMF.limestone));
		KnowledgeListMF.ores.addPages(assembleOreDesc("borax", BlockListMF.oreBorax));
		KnowledgeListMF.ores.addPages(assembleOreDesc("nitre", BlockListMF.oreNitre));
		KnowledgeListMF.ores.addPages(assembleOreDesc("sulfur", BlockListMF.oreSulfur));
		
		KnowledgeListMF.chimney.addPages(new EntryPageText("knowledge.chimney.paragraph_1", "knowledge.chimney.paragraph_2"), new EntryPageRecipeCarpenter(KnowledgeListMF.chimneyRecipe), new EntryPageRecipeCarpenter(KnowledgeListMF.wideChimneyRecipe), new EntryPageText("knowledge.chimney.paragraph_3"), new EntryPageRecipeCarpenter(KnowledgeListMF.extractChimneyRecipe));
		KnowledgeListMF.tanning.addPages(new EntryPageText("knowledge.tanning.paragraph_1"), new EntryPageRecipeBase(KnowledgeListMF.tannerRecipe), new EntryPageText("knowledge.tanning.paragraph_2"));
		
		KnowledgeListMF.crucible.addPages(new EntryPageText("knowledge.crucible.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.crucibleRecipe), new EntryPageText("knowledge.crucible.paragraph_2"));
		KnowledgeListMF.crucible2.addPages(new EntryPageText("knowledge.crucible2.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.advCrucibleRecipe));
		
		KnowledgeListMF.smeltCopper.addPages(new EntryPageText("knowledge.smeltCopper.paragraph_1"), new EntryPageSmelting(new ItemStack(BlockListMF.oreCopper), new ItemStack(ComponentListMF.ingots[0])));
		KnowledgeListMF.smeltBronze.addPages(new EntryPageText("knowledge.smeltBronze.paragraph_1"), new EntryPageSmelting(new ItemStack(BlockListMF.oreTin), new ItemStack(ComponentListMF.ingots[1])), new EntryPageCrucible(KnowledgeListMF.bronze));
		KnowledgeListMF.smeltIron.addPages(new EntryPageText("knowledge.smeltIron.paragraph_1"), new EntryPageSmelting(new ItemStack(Blocks.iron_ore), new ItemStack(Items.iron_ingot)));
		KnowledgeListMF.smeltSteel.addPages(new EntryPageText("knowledge.smeltSteel.paragraph_1"), new EntryPageCrucible(KnowledgeListMF.steel));
		KnowledgeListMF.encrusted.addPages(new EntryPageText("knowledge.smeltEncrusted.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.encrustedR));
		
		KnowledgeListMF.forge.addPages(new EntryPageText("knowledge.forge.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.forgeRecipe));
		KnowledgeListMF.anvil.addPages(new EntryPageText("knowledge.anvil.paragraph_1", "knowledge.anvil.paragraph_2", "knowledge.anvil.paragraph_3"), new EntryPageRecipeBase(BasicRecipesMF.recipeMap.get("anvilCrafting")), new EntryPageText( "knowledge.anvil.paragraph_4", "knowledge.anvil.paragraph_5"), new EntryPageImage("textures/gui/knowledge/anvilGuiExample.png", 128, 128, "knowledge.guiSubtitle"), new EntryPageText( "knowledge.anvil.paragraph_6", "knowledge.anvil.paragraph_7"));
	    KnowledgeListMF.smeltDragonforge.addPages(new EntryPageText("knowledge.smeltDragonforge.paragraph_1"), new EntryPageText("knowledge.smeltDragonforge.paragraph_2"), new EntryPageText("knowledge.smeltDragonforge.paragraph_3"));
	    KnowledgeListMF.craftArmourLight.addPages(new EntryPageText("knowledge.craftLight.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.studHelmetR), new EntryPageRecipeAnvil(KnowledgeListMF.studChestR), new EntryPageRecipeAnvil(KnowledgeListMF.studLegsR), new EntryPageRecipeAnvil(KnowledgeListMF.studBootsR));
	    KnowledgeListMF.craftArmourLight.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.scaleHelmR), new EntryPageRecipeAnvil(KnowledgeListMF.scaleChestR), new EntryPageRecipeAnvil(KnowledgeListMF.scaleLegsR), new EntryPageRecipeAnvil(KnowledgeListMF.scaleBootsR));
	    KnowledgeListMF.craftArmourMedium.addPages(new EntryPageText("knowledge.craftMail.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.mailRecipes), new EntryPageRecipeAnvil(KnowledgeListMF.mailHelmetR), new EntryPageRecipeAnvil(KnowledgeListMF.mailChestR), new EntryPageRecipeAnvil(KnowledgeListMF.mailLegsR), new EntryPageRecipeAnvil(KnowledgeListMF.mailBootsR));
	    KnowledgeListMF.craftArmourHeavy.addPages(new EntryPageText("knowledge.craftPlate.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.mailRecipes), new EntryPageRecipeAnvil(KnowledgeListMF.plateRecipes), new EntryPageRecipeCarpenter(KnowledgeListMF.padding), new EntryPageRecipeAnvil(KnowledgeListMF.plateHelmetR), new EntryPageRecipeAnvil(KnowledgeListMF.plateChestR), new EntryPageRecipeAnvil(KnowledgeListMF.plateLegsR), new EntryPageRecipeAnvil(KnowledgeListMF.plateBootsR));
	
	    KnowledgeListMF.blastfurn.addPages(new EntryPageText("knowledge.blastfurn.paragraph_1"), new EntryPageText("knowledge.blastfurn.paragraph_2"), new EntryPageText("knowledge.blastfurn.paragraph_3"), new EntryPageText("knowledge.blastfurn.paragraph_4"));
	    KnowledgeListMF.blastfurn.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.kaoDustR), new EntryPageRecipeBase(KnowledgeListMF.fireclayR), new EntryPageRecipeBase(KnowledgeListMF.fireBrickR), new EntryPageSmelting(new ItemStack(ComponentListMF.fireclay_brick), new ItemStack(ComponentListMF.strong_brick)), new EntryPageRecipeBase(KnowledgeListMF.fireBricksR));
	    KnowledgeListMF.blastfurn.addPages(new EntryPageText("knowledge.blastfurn.paragraph_5"), new EntryPageRecipeAnvil(KnowledgeListMF.blastChamR), new EntryPageRecipeAnvil(KnowledgeListMF.blastHeatR), new EntryPageText("knowledge.blastfurn.paragraph_6"), new EntryPageText("knowledge.blastfurn.paragraph_7"));
	    KnowledgeListMF.blastfurn.addPages(new EntryPageImage("textures/gui/knowledge/blast_example.png", 96, 96, ""));
	    KnowledgeListMF.blastfurn.addPages(new EntryPageText("knowledge.blastfurn.paragraph_8"), new EntryPageText("knowledge.blastfurn.paragraph_9"), new EntryPageText("knowledge.blastfurn.paragraph_10"));
	    KnowledgeListMF.blastfurn.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.ironPrepR), new EntryPageRecipeAnvil(KnowledgeListMF.ironPrepR2));
	    
	    KnowledgeListMF.blackpowder.addPages(new EntryPageText("knowledge.blackpowder.paragraph_1"),new EntryPageText("knowledge.blackpowder.paragraph_2"), new EntryPageRecipeAnvil(KnowledgeListMF.coalDustR), new EntryPageRecipeCarpenter(KnowledgeListMF.blackpowderRec));
	    KnowledgeListMF.advblackpowder.addPages(new EntryPageText("knowledge.advblackpowder.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.advblackpowderRec));
	    KnowledgeListMF.bombs.addPages(new EntryPageText("knowledge.bombs.paragraph_1", "knowledge.bombs.paragraph_2"), new EntryPageRecipeAnvil(KnowledgeListMF.bombBenchCraft), new EntryPageText("knowledge.bombs.paragraph_3"), new EntryPageImage("textures/gui/knowledge/bombGuiExample.png", 128, 128, "knowledge.guiSubtitle"), new EntryPageText("knowledge.bombs.paragraph_4", "knowledge.bombs.paragraph_5", "knowledge.bombs.paragraph_6"), new EntryPageText("knowledge.bombs.paragraph_7, knowledge.bombs.paragraph_8"));
	    KnowledgeListMF.shrapnel.addPages(new EntryPageText("knowledge.shrapnel.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.shrapnelR));
	    KnowledgeListMF.firebomb.addPages(new EntryPageText("knowledge.firebomb.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.magmaRefinedR));
	    KnowledgeListMF.bombCeramic.addPages(new EntryPageText("knowledge.bombCeramic.paragraph_1", "knowledge.bombCeramic.paragraph_2"), new EntryPageRecipeCarpenter(KnowledgeListMF.bombCaseCeramicR), new EntryPageSmelting(ComponentListMF.bomb_casing_uncooked, ComponentListMF.bomb_casing));
	    KnowledgeListMF.bombIron.addPages(new EntryPageText("knowledge.bombIron.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.bombCaseIronR));
	    KnowledgeListMF.bombObsidian.addPages(new EntryPageText("knowledge.bombObsidian.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.bombCaseObsidianR));
	    KnowledgeListMF.bombCrystal.addPages(new EntryPageText("knowledge.bombCrystal.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.bombCaseCrystalR));
	    
	    KnowledgeListMF.mineCeramic.addPages(new EntryPageText("knowledge.mineCeramic.paragraph_1", "knowledge.mineCeramic.paragraph_2"), new EntryPageRecipeCarpenter(KnowledgeListMF.mineCaseCeramicR), new EntryPageSmelting(ComponentListMF.mine_casing_uncooked, ComponentListMF.mine_casing));
	    KnowledgeListMF.mineIron.addPages(new EntryPageText("knowledge.mineIron.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.mineCaseIronR));
	    KnowledgeListMF.mineObsidian.addPages(new EntryPageText("knowledge.mineObsidian.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.mineCaseObsidianR));
	    KnowledgeListMF.mineCrystal.addPages(new EntryPageText("knowledge.mineCrystal.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.mineCaseCrystalR));
	    
	    KnowledgeListMF.bombFuse.addPages(new EntryPageText("knowledge.bombFuse.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.bombFuseR), new EntryPageText("knowledge.bombFuse.paragraph_2"), new EntryPageRecipeCarpenter(KnowledgeListMF.longFuseR));
	    
	    KnowledgeListMF.stickybomb.addPages(new EntryPageText("knowledge.stickybomb.paragraph_1", "knowledge.stickybomb.paragraph_2"));
	    
	    KnowledgeListMF.craftOrnateWeapons.addPages(new EntryPageText("knowledge.ornateweps.paragraph_1"));
	    
	    KnowledgeListMF.repair_basic.addPages(new EntryPageText("knowledge.repair_basic.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.repairBasicR), new EntryPageText("knowledge.repair_basic.paragraph_2"));
	    KnowledgeListMF.repair_advanced.addPages(new EntryPageText("knowledge.repair_advanced.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.repairAdvancedR));
	    KnowledgeListMF.repair_ornate.addPages(new EntryPageText("knowledge.repair_ornate.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.repairOrnateR));
	    
	    for(int a = 0; a < KnowledgeListMF.ornateWepsR.size(); a++)
	    {
	    	KnowledgeListMF.craftOrnateWeapons.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.ornateWepsR.get(a)));
	    }
	    KnowledgeListMF.craftAdvOrnateWeapons.addPages(new EntryPageText("knowledge.advornateweps.paragraph_1"));
	    for(int a = 0; a < KnowledgeListMF.advOrnateWepsR.size(); a++)
	    {
	    	KnowledgeListMF.craftAdvOrnateWeapons.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.advOrnateWepsR.get(a)));
	    }
	    
	    KnowledgeListMF.craftTools.addPages(new EntryPageText("knowledge.basictools.paragraph_1"));
	    KnowledgeListMF.craftTools.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.pickR));
	    KnowledgeListMF.craftTools.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.axeR));
	    KnowledgeListMF.craftTools.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.spadeR));
	    KnowledgeListMF.craftTools.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.hoeR));
	    KnowledgeListMF.craftTools.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.shearsR));
	    
	    KnowledgeListMF.craftAdvTools.addPages(new EntryPageText("knowledge.advancedtools.paragraph_1"));
	    KnowledgeListMF.craftAdvTools.addPages(new EntryPageText("knowledge.handpick.info"), new EntryPageRecipeAnvil(KnowledgeListMF.handpickR));
	    KnowledgeListMF.craftAdvTools.addPages(new EntryPageText("knowledge.hvypick.info"), new EntryPageRecipeAnvil(KnowledgeListMF.hvyPickR));
	    KnowledgeListMF.craftAdvTools.addPages(new EntryPageText("knowledge.trow.info"), new EntryPageRecipeAnvil(KnowledgeListMF.trowR));
	    KnowledgeListMF.craftAdvTools.addPages(new EntryPageText("knowledge.hvyshovel.info"), new EntryPageRecipeAnvil(KnowledgeListMF.hvyShovelR));
	    KnowledgeListMF.craftAdvTools.addPages(new EntryPageText("knowledge.scythe.info"), new EntryPageRecipeAnvil(KnowledgeListMF.scytheR));
	    KnowledgeListMF.craftAdvTools.addPages(new EntryPageText("knowledge.mattock.info"));
	    
	    KnowledgeListMF.craftCrafters.addPages(new EntryPageText("knowledge.craftertools.paragraph_1"));
	    KnowledgeListMF.craftCrafters.addPages(new EntryPageText("knowledge.hammer.info"), new EntryPageRecipeAnvil(KnowledgeListMF.hammerR));
	    KnowledgeListMF.craftCrafters.addPages(new EntryPageText("knowledge.hvyhammer.info"), new EntryPageRecipeAnvil(KnowledgeListMF.hvyHammerR));
	    KnowledgeListMF.craftCrafters.addPages(new EntryPageText("knowledge.tongs.info"), new EntryPageRecipeAnvil(KnowledgeListMF.tongsR));
	    KnowledgeListMF.craftCrafters.addPages(new EntryPageText("knowledge.knife.info"), new EntryPageRecipeAnvil(KnowledgeListMF.knifeR));
	    KnowledgeListMF.craftCrafters.addPages(new EntryPageText("knowledge.needle.info"), new EntryPageRecipeAnvil(KnowledgeListMF.needleR));
	    
	    KnowledgeListMF.craftWeapons.addPages(new EntryPageText("knowledge.weapons.paragraph_1"));
	    KnowledgeListMF.craftWeapons.addPages(new EntryPageText("knowledge.dagger.info"), new EntryPageRecipeAnvil(KnowledgeListMF.daggerR));
	    KnowledgeListMF.craftWeapons.addPages(new EntryPageText("knowledge.sword.info"), new EntryPageRecipeAnvil(KnowledgeListMF.swordR));
	    KnowledgeListMF.craftWeapons.addPages(new EntryPageText("knowledge.waraxe.info"), new EntryPageRecipeAnvil(KnowledgeListMF.waraxeR));
	    KnowledgeListMF.craftWeapons.addPages(new EntryPageText("knowledge.mace.info"), new EntryPageRecipeAnvil(KnowledgeListMF.maceR));
	    KnowledgeListMF.craftWeapons.addPages(new EntryPageText("knowledge.spear.info"), new EntryPageRecipeAnvil(KnowledgeListMF.spearR));
	    KnowledgeListMF.craftWeapons.addPages(new EntryPageText("knowledge.bow.info"), new EntryPageRecipeAnvil(KnowledgeListMF.bowR));
	    
	    KnowledgeListMF.craftAdvWeapons.addPages(new EntryPageText("knowledge.hvyweapons.paragraph_1"));
	    KnowledgeListMF.craftAdvWeapons.addPages(new EntryPageText("knowledge.katana.info"), new EntryPageRecipeAnvil(KnowledgeListMF.katanaR));
	    KnowledgeListMF.craftAdvWeapons.addPages(new EntryPageText("knowledge.greatsword.info"), new EntryPageRecipeAnvil(KnowledgeListMF.gswordR));
	    KnowledgeListMF.craftAdvWeapons.addPages(new EntryPageText("knowledge.battleaxe.info"), new EntryPageRecipeAnvil(KnowledgeListMF.battleaxeR));
	    KnowledgeListMF.craftAdvWeapons.addPages(new EntryPageText("knowledge.warhammer.info"), new EntryPageRecipeAnvil(KnowledgeListMF.whammerR));
	    KnowledgeListMF.craftAdvWeapons.addPages(new EntryPageText("knowledge.halbeard.info"), new EntryPageRecipeAnvil(KnowledgeListMF.halbeardR));
	    KnowledgeListMF.craftAdvWeapons.addPages(new EntryPageText("knowledge.lance.info"), new EntryPageRecipeAnvil(KnowledgeListMF.lanceR));
	    
	    KnowledgeListMF.arrows.addPages(new EntryPageText("knowledge.arrows.paragraph_1"));
	    KnowledgeListMF.arrows.addPages(new EntryPageRecipeCarpenter(KnowledgeListMF.fletchingR));
	    KnowledgeListMF.arrows.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.arrowheadR));
	    KnowledgeListMF.arrows.addPages(new EntryPageRecipeCarpenter(KnowledgeListMF.arrowR));
	    KnowledgeListMF.arrowsBodkin.addPages(new EntryPageText("knowledge.arrowsBodkin.paragraph_1"));
	    KnowledgeListMF.arrowsBodkin.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.bodkinheadR));
	    KnowledgeListMF.arrowsBroad.addPages(new EntryPageText("knowledge.arrowsBroad.paragraph_1"));
	    KnowledgeListMF.arrowsBroad.addPages(new EntryPageRecipeAnvil(KnowledgeListMF.broadheadR));
	    
	    KnowledgeListMF.smeltBlackSteel.addPages(new EntryPageText("knowledge.smeltBlackSteel.paragraph_1"), new EntryPageCrucible(KnowledgeListMF.black));
	    KnowledgeListMF.smeltRedSteel.addPages(new EntryPageText("knowledge.smeltRedSteel.paragraph_1"), new EntryPageCrucible(KnowledgeListMF.red));
	    KnowledgeListMF.smeltBlueSteel.addPages(new EntryPageText("knowledge.smeltBlueSteel.paragraph_1"), new EntryPageCrucible(KnowledgeListMF.blue));
	    KnowledgeListMF.smeltAdamant.addPages(new EntryPageText("knowledge.smeltAdamantium.paragraph_1"), new EntryPageCrucible(KnowledgeListMF.adamantium));
	    KnowledgeListMF.smeltMithril.addPages(new EntryPageText("knowledge.smeltMithril.paragraph_1"), new EntryPageCrucible(KnowledgeListMF.mithril));
	}
	
	private static EntryPage[] assembleOreDesc(String orename, Block ore, Item ingot)
	{
		return new EntryPage[]{new EntryPageImage("textures/gui/knowledge/ores/"+orename+".png", 96, 96, ore.getUnlocalizedName()+".name"), new EntryPageText("knowledge.ores."+orename + ".paragraph_1"), new EntryPageSmelting(new ItemStack(ore), new ItemStack(ingot))};
	}
	private static EntryPage[] assembleOreDesc(String orename, Block ore)
	{
		return new EntryPage[]{new EntryPageImage("textures/gui/knowledge/ores/"+orename+".png", 96, 96, ore.getUnlocalizedName()+".name"), new EntryPageText("knowledge.minerals."+orename + ".paragraph_1")};
	}
}
