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
		KnowledgeListMF.ores.addPages(new EntryPageText("knowledge.ores.paragraph_1"));
		KnowledgeListMF.ores.addPages(assembleOreDesc("copper", BlockListMF.oreCopper, ComponentListMF.ingots[0]));
		KnowledgeListMF.ores.addPages(assembleOreDesc("tin", BlockListMF.oreTin, ComponentListMF.ingots[1]));
		KnowledgeListMF.ores.addPages(assembleOreDesc("silver", BlockListMF.oreSilver, ComponentListMF.ingots[9]));
		KnowledgeListMF.ores.addPages(assembleOreDesc("mythic", BlockListMF.oreMythic));
		KnowledgeListMF.ores.addPages(new EntryPageText("knowledge.ores.paragraph_2"));
		KnowledgeListMF.ores.addPages(assembleOreDesc("clay", BlockListMF.oreClay));
		KnowledgeListMF.ores.addPages(assembleOreDesc("kaolinite", BlockListMF.oreKaolinite));
		KnowledgeListMF.ores.addPages(assembleOreDesc("limestone", BlockListMF.limestone));
		KnowledgeListMF.ores.addPages(assembleOreDesc("borax", BlockListMF.oreBorax));
		KnowledgeListMF.ores.addPages(assembleOreDesc("nitre", BlockListMF.oreNitre));
		KnowledgeListMF.ores.addPages(assembleOreDesc("sulfur", BlockListMF.oreSulfur));
		
		KnowledgeListMF.crucible.addPages(new EntryPageText("knowledge.crucible.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.crucibleRecipe));
		KnowledgeListMF.crucible2.addPages(new EntryPageText("knowledge.crucible2.paragraph_1"), new EntryPageRecipeCarpenter(KnowledgeListMF.advCrucibleRecipe));
		KnowledgeListMF.smeltBronze.addPages(new EntryPageText("knowledge.smeltBronze.paragraph_1"), new EntryPageSmelting(new ItemStack(BlockListMF.oreCopper), new ItemStack(ComponentListMF.ingots[0])), new EntryPageSmelting(new ItemStack(BlockListMF.oreTin), new ItemStack(ComponentListMF.ingots[1])));
    	KnowledgeListMF.anvil.addPages(new EntryPageText("knowledge.anvil.paragraph_1", "knowledge.anvil.paragraph_2", "knowledge.anvil.paragraph_3"), new EntryPageRecipeBase(BasicRecipesMF.recipeMap.get("anvilCrafting")), new EntryPageText( "knowledge.anvil.paragraph_4", "knowledge.anvil.paragraph_5"), new EntryPageImage("textures/gui/knowledge/anvilGuiExample.png", 128, 128, "knowledge.guiSubtitle"), new EntryPageText( "knowledge.anvil.paragraph_6", "knowledge.anvil.paragraph_7"));
	    KnowledgeListMF.smeltDragonforge.addPages(new EntryPageText("knowledge.smeltDragonforge.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.blackSteelNuggetR), new EntryPageRecipeAnvil(KnowledgeListMF.dragonforgeIngotRecipe));
	    KnowledgeListMF.craftArmourAdv.addPages(new EntryPageText("knowledge.craftPlate.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.plateRecipes), new EntryPageRecipeAnvil(KnowledgeListMF.plateHelmetR), new EntryPageRecipeAnvil(KnowledgeListMF.plateChestR), new EntryPageRecipeAnvil(KnowledgeListMF.plateLegsR), new EntryPageRecipeAnvil(KnowledgeListMF.plateBootsR));
	
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
