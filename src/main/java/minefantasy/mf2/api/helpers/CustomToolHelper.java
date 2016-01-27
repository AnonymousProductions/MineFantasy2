package minefantasy.mf2.api.helpers;

import java.util.List;

import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CustomToolHelper 
{
	public static final String slot_main = "main_metal";
	/**
	 * A bit of the new system, gets custom materials for the head
	 */
	public static CustomMaterial getCustomMetalMaterial(ItemStack item)
	{
		if(item == null)return null;
		
		CustomMaterial material = CustomMaterial.getMaterialFor(item, slot_main);
		if(material != null)
		{
			return material;
		}
		return null;
	}
	public static ItemStack construct(Item base, String main)
	{
		ItemStack item = new ItemStack(base);
		CustomMaterial.addMaterial(item, slot_main, main.toLowerCase());
		return item;
	}
	/**
	 * Gets the rarity for a custom item
	 * @param itemRarity is the default id
	 */
	public static EnumRarity getRarity(ItemStack item, int itemRarity)
	{
    	int lvl = itemRarity+1;
    	CustomMaterial material = CustomMaterial.getMaterialFor(item, slot_main);
		if(material != null)
		{
			lvl = material.rarityID+1;
		}
		
		if(item.isItemEnchanted())
		{
			if(lvl == 0)
			{
				lvl++;
			}
			lvl ++;
		}
		if(lvl >= rarity.length)
		{
			lvl = rarity.length-1;
		}
		return rarity[lvl];
	}
	public static EnumRarity poor = EnumHelper.addRarity("Poor", EnumChatFormatting.DARK_GRAY, "poor");
	public static EnumRarity[] rarity = new EnumRarity[]{poor, EnumRarity.common, EnumRarity.uncommon, EnumRarity.rare, EnumRarity.epic};
	/**
	 * Gets the max durability
	 * @param dura is the default dura
	 */
	public static int getMaxDamage(ItemStack stack, int dura)
	{
		CustomMaterial custom = getCustomMetalMaterial(stack);
		if(custom != null)
		{
			dura = (int)(custom.durability * 250);
	
		}
		return ToolHelper.setDuraOnQuality(stack, dura);
	}
	
	/**
	 * Gets the colour for a layer
	 * @param base is default colour
	 */
	public static int getColourFromItemStack(ItemStack item, int layer, int base)
    {
    	if(layer == 0)
    	{
    		CustomMaterial material = CustomMaterial.getMaterialFor(item, slot_main);
    		if(material != null)
    		{
    			return material.getColourInt();
    		}
    	}
    	return base;
    }
	
	public static float getWeightModifier(ItemStack item, float base)
	{
		CustomMaterial custom = getCustomMetalMaterial(item);
    	if(custom != null)
    	{
    		return (custom.density/2.5F) * base;
    	}
		return base;
	}
	
	/**
	 * Gets the material modifier if it exists
	 * @param defaultModifier default if no material exists
	 */
	public static float getMeleeDamage(ItemStack item, float defaultModifier) 
    {
    	CustomMaterial custom = getCustomMetalMaterial(item);
    	if(custom != null)
    	{
    		return custom.sharpness;
    	}
    	return defaultModifier;
	}
	public static float getBowDamage(ItemStack item, float defaultModifier) 
    {
    	return getBaseDamage(item, defaultModifier)/2F;
	}
	/**
	 * The total damage of a bow and arrow
	 */
	public static float getBaseDamage(ItemStack item, float defaultModifier)
	{
		CustomMaterial custom = getCustomMetalMaterial(item);
    	if(custom != null)
    	{
    		return getBaseDamage(custom.sharpness*custom.flexibility);
    	}
		return getBaseDamage(defaultModifier);
	}
	/**
	 * The damage a bow and arrow should do (same as a sword)
	 */
	public static float getBaseDamage(float modifier)
	{
		return 4F + modifier;
	}
	public static float getArrowDamage(ItemStack item, float designMod, float defaultDamage) 
    {
		return getBaseDamage(item, defaultDamage)/2F * designMod;
	}
	
	public static float getEfficiency(ItemStack item, float value, float mod) 
    {
    	CustomMaterial custom = getCustomMetalMaterial(item);
    	if(custom != null)
    	{
    		value = 2.0F + (custom.sharpness*2F);//Efficiency starts at 2 and each point of sharpness adds 2
    	}
    	return ToolHelper.modifyDigOnQuality(item, value) * mod;
	}
	
	public static int getCrafterTier(ItemStack item, int value) 
    {
    	CustomMaterial custom = getCustomMetalMaterial(item);
    	if(custom != null)
    	{
    		return custom.crafterTier;
    	}
    	return value;
	}
	
	public static int getHarvestLevel(ItemStack item, int value) 
    {
		if(value <= 0)
    	{
    		return value;//If its not effective
    	}
		
    	CustomMaterial custom = getCustomMetalMaterial(item);
    	if(custom != null)
    	{
    		if(custom.tier == 0)return 1;
    		if(custom.tier <= 2)return 2;
    		return Math.max(custom.tier, 2);
    	}
    	return value;
	}
	
	@SideOnly(Side.CLIENT)
	public static void addInformation(ItemStack item, List list) 
	{
		/*
		CustomMaterial base = getCustomHeadMaterial(item);
    	if(base != null)
    	{
    		String matName = StatCollector.translateToLocalFormatted("item.mod_head.name", StatCollector.translateToLocal("material."+base.name.toLowerCase() + ".name"));
    		list.add(EnumChatFormatting.GOLD + matName);
    	}
    	*/
	}
	
	@SideOnly(Side.CLIENT)
	public static String getLocalisedName(ItemStack item, String unlocalName)
	{
		CustomMaterial base = getCustomMetalMaterial(item);
		String name = "any";
    	if(base != null)
    	{
    		name = base.name.toLowerCase();
    	}
    	return StatCollector.translateToLocalFormatted(unlocalName, StatCollector.translateToLocal("material."+name + ".name"));
	}
	/**
	 * Checks if two items' materials match
	 */
	public static boolean doesMatchForRecipe(ItemStack recipeItem, ItemStack inputItem) 
	{
		CustomMaterial recipeMat = CustomToolHelper.getCustomMetalMaterial(recipeItem);
        CustomMaterial inputMat = CustomToolHelper.getCustomMetalMaterial(inputItem);
        
        if(recipeMat == null && inputMat == null)
        {
        	return true;
        }
        if (inputMat == null && recipeMat != null || inputMat != null && recipeMat == null)
        {
            return false;
        }
        if(recipeMat != inputMat)
		{
        	return false;
    	}
		return true;
	}
	public static void addComponentString(ItemStack tool, List list, CustomMaterial base) 
	{
    	if(base != null)
    	{
    		float mass = base.density;
    		list.add(EnumChatFormatting.GOLD + base.getMaterialString());
    		list.add(CustomMaterial.getWeightString(mass));
    		int maxTemp = base.getHeatableStats()[0];
    		int beyondMax = base.getHeatableStats()[1];
    		list.add(StatCollector.translateToLocalFormatted("materialtype.workable.name", maxTemp, beyondMax));
    	}
	}
	
}
