package minefantasy.mf2.api.crafting.anvil;

import minefantasy.mf2.api.rpg.Skill;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author AnonymousProductions
 *
 */
public interface IAnvilRecipe
{
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(InventoryCrafting var1);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(InventoryCrafting var1);
    int getCraftTime();
    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();
    
    int getRecipeHammer();
    
    float getExperiance();
    
    int getAnvil();
    
    boolean outputHot();
    
    String getToolType();
    
    String getResearch();
    
    ItemStack getRecipeOutput();
    
    Skill getSkill();
}
