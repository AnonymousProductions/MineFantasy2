package minefantasy.mf2.api.crafting.anvil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 
 * @author AnonymousProductions
 *
 */
public class ShapelessAnvilRecipes implements IAnvilRecipe
{
    public static final int	globalWidth	= 6;
    public static final int	globalHeight	= 4;

	/** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;

    private final boolean outputHot;
    /** Is a List of ItemStack that composes the recipe. */
    private final List recipeItems;
    
    private final int recipeHammer;
    
    /** The anvil Required */
    private final int anvil;
    
    private final int recipeTime;
    private final float recipeExperiance;
    private final String toolType;
    private final String research;

    public ShapelessAnvilRecipes(ItemStack output, String toolType, float exp, int hammer, int anvi, int time, List components, boolean hot, String research)
    {
    	this.outputHot = hot;
        this.recipeOutput = output;
        this.anvil = anvi;
        this.recipeItems = components;
        this.recipeHammer = hammer;
        this.recipeTime = time;
        this.recipeExperiance = exp;
        this.toolType = toolType;
        this.research = research;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting par1InventoryCrafting)
    {
        ArrayList var2 = new ArrayList(this.recipeItems);

        for (int var3 = 0; var3 <= globalWidth; ++var3)
        {
            for (int var4 = 0; var4 <= globalHeight; ++var4)
            {
                ItemStack inputItem = par1InventoryCrafting.getStackInRowAndColumn(var4, var3);

                if (inputItem != null)
                {
                    boolean var6 = false;
                    Iterator var7 = var2.iterator();

                    while (var7.hasNext())
                    {
                        ItemStack recipeItem = (ItemStack)var7.next();

                        if(inputItem == null)
                        {
                        	return false;
                        }
                        
                        if (inputItem.getItem() == recipeItem.getItem() && (recipeItem.getItemDamage() == OreDictionary.WILDCARD_VALUE || inputItem.getItemDamage() == recipeItem.getItemDamage()))
                        {
                            var6 = true;
                            var2.remove(recipeItem);
                            break;
                        }
                    }

                    if (!var6)
                    {
                        return false;
                    }
                }
            }
        }

        return var2.isEmpty();
    }
    public static int getTemp(ItemStack item)
	{
		NBTTagCompound tag = getNBT(item);
		
		if(tag.hasKey("MFtemp"))return tag.getInteger("MFtemp");
		
		return 0;
	}
	
	private static NBTTagCompound getNBT(ItemStack item)
	{
		if(!item.hasTagCompound())item.setTagCompound(new NBTTagCompound());
		return item.getTagCompound();
	}
	
    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
    {
        return this.recipeOutput.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return this.recipeItems.size();
    }

	@Override
	public int getCraftTime() {
		return this.recipeTime;
	}

	@Override
	public int getRecipeHammer() {
		return this.recipeHammer;
	}

	@Override
	public float getExperiance() {
		return this.recipeExperiance;
	}

	@Override
	public int getAnvil() {
		return this.anvil;
	}

	@Override
	public boolean outputHot() {
		return outputHot;
	}

	@Override
	public String getToolType()
	{
		return toolType;
	}

	@Override
	public String getResearch()
	{
		return research;
	}
}
