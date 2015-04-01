package minefantasy.mf2.mechanics;

import java.util.HashMap;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.Loader;
import minefantasy.mf2.api.item.HotItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * 
 * @author tim4242
 *
 */
public class HotItemsHelper {
	
	public static final String HI_NBT_KEY = "MineFantasyII_HotItem_";
	public static final String HI_NBT_KEY_MAIN = HI_NBT_KEY + "Main";
	public static final String HI_NBT_KEY_CURR_TEMP = HI_NBT_KEY + "CurrTemp";
	
	private static int m_burnTemp = 0;
	
	/**
	 * 
	 * Sets the burn temperature externally.
	 * 
	 * @param temp 
	 */
	public static void setBurnTemp(int temp)
	{
		m_burnTemp = temp;
	}
	
	/**
	 * 
	 * @return The current burn temperature, as set in the config.
	 */
	public static final int getBurnTemp()
	{
		return m_burnTemp;
	}
	
	
	/**
	 * Helper method to get the NBTtag of a Hot item.
	 */
	public final static NBTTagCompound getHICompound(ItemStack stack)
	{
		return stack.stackTagCompound.getCompoundTag(HI_NBT_KEY_MAIN);
	}
	
	/**
	 * 
	 * @param stack
	 * @return If the stack contains a hot item
	 */
	public static final boolean isHotItem(ItemStack stack)
	{
		if(stack.getItem() instanceof HotItem)
		{
			return true;
		}
		
		return false;
	}
	
	public static final int getMaxTemp(ItemStack stack)
	{
		if(isHotItem(stack))
		return ((HotItem) stack.getItem()).getMaxTemp();
		
		return 0;
	}
	
	public static final int getMinTemp(ItemStack stack)
	{
		if(isHotItem(stack))
			return ((HotItem) stack.getItem()).getMinTemp();
			
			return 0;
	}
	
	public static final int getDefTemp(ItemStack stack)
	{
		if(isHotItem(stack))
			return ((HotItem) stack.getItem()).getDefaultTemp();
			
			return 0;
	}
	
	public static final int getCurrTemp(ItemStack stack)
	{
		return getHICompound(stack).getInteger(HI_NBT_KEY_CURR_TEMP);
	}
	
	public static final boolean isMaxTemp(ItemStack stack)
	{
		if(!isHotItem(stack))
			return false;
		
		if(getMaxTemp(stack) <= getCurrTemp(stack))
			return true;
		
		return false;
	}
	
	public static final boolean isMinTemp(ItemStack stack)
	{
		if(!isHotItem(stack))
			return false;
		
		if(getMinTemp(stack) <= getCurrTemp(stack))
			return true;
		
		return false;
	}
	
	public static final boolean isDefTemp(ItemStack stack)
	{
		if(!isHotItem(stack))
			return false;
		
		if(getDefTemp(stack) == getCurrTemp(stack))
			return true;
		
		return false;
	}
	
	public static final boolean isTooHigh(ItemStack stack)
	{
		return (getCurrTemp(stack) > getMaxTemp(stack));
	}
	
	public static final boolean isTooLow(ItemStack stack)
	{
		return (getCurrTemp(stack) > getMaxTemp(stack));
	}
	
	public static final boolean isOutsideLims(ItemStack stack)
	{
		return (isTooHigh(stack) || isTooLow(stack));
	}
	
	public static final boolean isInsideLims(ItemStack stack)
	{
		return !isOutsideLims(stack);
	}
	
	public static final boolean setTemp(ItemStack stack, int temp)
	{
		if(isHotItem(stack))
			return false;
		
		if((getMaxTemp(stack) <= temp) && (getMinTemp(stack) >= temp))
		{
			stack.stackTagCompound.getCompoundTag(HI_NBT_KEY_CURR_TEMP).setInteger(HI_NBT_KEY_CURR_TEMP, temp);
			return true;
		}
		
		return false;
	}
	
	public static final boolean addTemp(ItemStack stack, int temp)
	{
		return setTemp(stack, getCurrTemp(stack) + temp);
	}
	
	public static final boolean removeTemp(ItemStack stack, int temp)
	{
		return setTemp(stack, getCurrTemp(stack) - temp);
	}
}
