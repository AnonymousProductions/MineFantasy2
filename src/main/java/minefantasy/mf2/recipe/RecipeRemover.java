package minefantasy.mf2.recipe;

import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

public class RecipeRemover 
{
	public static void removeRecipes() 
	{
		MFLogUtil.log("MineFantasy: Removing replaced recipes...");
		for(int a = 0; a < CraftingManager.getInstance().getRecipeList().size(); a ++)
		{
			IRecipe rec = (IRecipe) CraftingManager.getInstance().getRecipeList().get(a);
			if(rec.getRecipeOutput() != null && rec.getRecipeOutput().isItemEqual(new ItemStack(Items.stick)))
			{
				CraftingManager.getInstance().getRecipeList().remove(a);
			}
		}
	}
}
