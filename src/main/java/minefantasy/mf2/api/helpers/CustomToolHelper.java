package minefantasy.mf2.api.helpers;

import java.util.List;

import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CustomToolHelper 
{
	public static final String slot_main = "main_metal";
	/**
	 * A bit of the new system, gets custom materials for the head
	 */
	public static CustomMaterial getCustomHeadMaterial(ItemStack item)
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
		if(lvl >= ToolListMF.rarity.length)
		{
			lvl = ToolListMF.rarity.length-1;
		}
		return ToolListMF.rarity[lvl];
	}
	/**
	 * Gets the max durability
	 * @param dura is the default dura
	 */
	public static int getMaxDamage(ItemStack stack, int dura)
	{
		CustomMaterial custom = getCustomHeadMaterial(stack);
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
		CustomMaterial custom = getCustomHeadMaterial(item);
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
    	CustomMaterial custom = getCustomHeadMaterial(item);
    	if(custom != null)
    	{
    		return custom.sharpness;
    	}
    	return defaultModifier;
	}
	
	public static float getEfficiency(ItemStack item, float value, float mod) 
    {
    	CustomMaterial custom = getCustomHeadMaterial(item);
    	if(custom != null)
    	{
    		value = 2.0F + (custom.sharpness*2F);//Efficiency starts at 2 and each point of sharpness adds 2
    	}
    	return ToolHelper.modifyDigOnQuality(item, value) * mod;
	}
	
	public static int getCrafterTier(ItemStack item, int value) 
    {
    	CustomMaterial custom = getCustomHeadMaterial(item);
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
		
    	CustomMaterial custom = getCustomHeadMaterial(item);
    	if(custom != null)
    	{
    		return custom.tier;
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
		CustomMaterial base = getCustomHeadMaterial(item);
    	if(base != null)
    	{
    		return StatCollector.translateToLocalFormatted(unlocalName, StatCollector.translateToLocal("material."+base.name.toLowerCase() + ".name"));
    	}
		return StatCollector.translateToLocal(unlocalName);
	}
}
