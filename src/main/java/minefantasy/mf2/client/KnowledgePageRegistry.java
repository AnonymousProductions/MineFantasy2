package minefantasy.mf2.client;

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
    	KnowledgeListMF.anvil.addPages(new EntryPageText("knowledge.anvil.paragraph_1", "knowledge.anvil.paragraph_2", "knowledge.anvil.paragraph_3"), new EntryPageCraft(BasicRecipesMF.recipeMap.get("anvilCrafting")), new EntryPageImage("textures/gui/knowledge/anvilGuiExample.png", 64, 64, ""));
	    	
	}
}
