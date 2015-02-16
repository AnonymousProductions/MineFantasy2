package minefantasy.mf2.client;

import net.minecraft.item.crafting.IRecipe;
import minefantasy.mf2.api.knowledge.client.*;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.recipe.BasicRecipesMF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KnowledgePageRegistry
{
	public static void registerPages()
	{
    	KnowledgeListMF.anvil.addPages(new EntryPageText("knowledge.anvil.paragraph_1", "knowledge.anvil.paragraph_2", "knowledge.anvil.paragraph_3"), new EntryPageRecipeBase(BasicRecipesMF.recipeMap.get("anvilCrafting")), new EntryPageText( "knowledge.anvil.paragraph_4", "knowledge.anvil.paragraph_5"), new EntryPageImage("textures/gui/knowledge/anvilGuiExample.png", 64, 64, "knowledge.guiSubtitle"), new EntryPageText( "knowledge.anvil.paragraph_6", "knowledge.anvil.paragraph_7"));
	    KnowledgeListMF.smeltDragonforge.addPages(new EntryPageText("knowledge.smeltDragonforge.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.blackSteelNuggetR), new EntryPageRecipeAnvil(KnowledgeListMF.dragonforgeIngotRecipe));
	    KnowledgeListMF.craftArmourAdv.addPages(new EntryPageText("knowledge.craftPlate.paragraph_1"), new EntryPageRecipeAnvil(KnowledgeListMF.plateRecipes), new EntryPageRecipeAnvil(KnowledgeListMF.plateHelmetR), new EntryPageRecipeAnvil(KnowledgeListMF.plateChestR), new EntryPageRecipeAnvil(KnowledgeListMF.plateLegsR), new EntryPageRecipeAnvil(KnowledgeListMF.plateBootsR));
	}
}
