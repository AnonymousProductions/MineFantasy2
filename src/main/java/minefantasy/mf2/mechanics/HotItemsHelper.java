package minefantasy.mf2.mechanics;

import java.util.HashMap;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.Loader;
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
	public static final String HI_NBT_KEY_MAX_TEMP = HI_NBT_KEY + "MaxTemp";
	public static final String HI_NBT_KEY_MIN_TEMP = HI_NBT_KEY + "MinTemp";
	public static final String HI_NBT_KEY_DEF_TEMP = HI_NBT_KEY + "DefTemp";
	public static final String HI_NBT_KEY_CURR_TEMP = HI_NBT_KEY + "CurrTemp";
	
	private static int m_defaultCurrTemp;
	private static int m_defaultMinTemp;
	private static int m_defaultMaxTemp;
	
	private static HashMap<Object[], Integer[]> m_temps;
	
	static {
		m_defaultCurrTemp = 0;
		m_defaultMinTemp = 0;
		m_defaultMaxTemp = 0;
		m_temps = Maps.newHashMap();
	}
	
	public static final void setDefaults(int max, int min, int curr)
	{
		m_defaultMaxTemp = max;
		m_defaultMinTemp = min;
		m_defaultCurrTemp = curr;
	}
	
	public static final void setDefaultMaxTemp(int max)
	{
		setDefaults(max, m_defaultMinTemp, m_defaultCurrTemp);
	}
	
	public static final void setDefaultMinTemp(int min)
	{
		setDefaults(m_defaultMaxTemp, min, m_defaultCurrTemp);
	}
	
	public static final void setDefaultCurrTemp(int curr)
	{
		setDefaults(m_defaultMaxTemp, m_defaultMinTemp, curr);
	}
	
	public static final boolean isItemRegistered(String unloc, Integer dam)
	{
		return m_temps.containsKey(new Object[] {unloc, dam});
	}
	
	public static final boolean isItemRegistered(ItemStack stack)
	{
		return isItemRegistered(stack.getItem().getUnlocalizedName(), stack.getItemDamage());
	}
	
	public static final void addHotItem(Object[][] keys, Integer[][] temps)
	{	
		for(int i = 0; i < keys.length; i++)
		{
			if(keys[i].length == 2 && temps.length == 3)
			{
				if(!((keys[i][0] instanceof String && keys[i][1] instanceof Integer) && (temps[i][0] instanceof Integer && temps[i][1] instanceof Integer && temps[i][2] instanceof Integer)))
				return;
					
				m_temps.put(keys[i], temps[i]);
			}
		}
	}
	
	public static final Integer[] getTemps(String unlocName, int dam)
	{
		Object[] key = new Object[] {unlocName, dam};
		
		if(m_temps.containsKey(key))
		{
			return m_temps.get(key);
		}
		
		return new Integer[] {m_defaultMaxTemp, m_defaultMinTemp, m_defaultCurrTemp};
	}
	
	public static final Integer[] getTemps(ItemStack stack)
	{
		return getTemps(stack.getItem().getUnlocalizedName(), stack.getItemDamage());
	}
	
	public static final ItemStack initHotItem(ItemStack stack)
	{
		NBTTagCompound temp = new NBTTagCompound();
		
		temp.setInteger(HI_NBT_KEY_MAX_TEMP, getTemps(stack.getItem().getUnlocalizedName(), stack.getItemDamage())[0]);
		temp.setInteger(HI_NBT_KEY_MIN_TEMP, getTemps(stack.getItem().getUnlocalizedName(), stack.getItemDamage())[1]);
		temp.setInteger(HI_NBT_KEY_DEF_TEMP, getTemps(stack.getItem().getUnlocalizedName(), stack.getItemDamage())[2]);
		temp.setInteger(HI_NBT_KEY_CURR_TEMP, getTemps(stack.getItem().getUnlocalizedName(), stack.getItemDamage())[2]);
		
		stack.stackTagCompound.setTag(HI_NBT_KEY_MAIN, temp);
		
		ItemStack res = stack;
		return res;
	}
	
	public final static NBTTagCompound getHICompound(ItemStack stack)
	{
		return stack.stackTagCompound.getCompoundTag(HI_NBT_KEY_MAIN);
	}
	
	public static final boolean isHotItem(ItemStack stack)
	{
		if(!(getHICompound(stack) == null))
		{
			NBTTagCompound temp = getHICompound(stack);
			
			if(temp.hasKey(HI_NBT_KEY_MAX_TEMP) && temp.hasKey(HI_NBT_KEY_MAX_TEMP) && temp.hasKey(HI_NBT_KEY_CURR_TEMP))
				return true;
		}
		
		return false;
	}
	
	public static final int getMaxTemp(ItemStack stack)
	{
		return getHICompound(stack).getInteger(HI_NBT_KEY_MAX_TEMP);
	}
	
	public static final int getMinTemp(ItemStack stack)
	{
		return getHICompound(stack).getInteger(HI_NBT_KEY_MIN_TEMP);
	}
	
	public static final int getDefTemp(ItemStack stack)
	{
		return getHICompound(stack).getInteger(HI_NBT_KEY_DEF_TEMP);
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
