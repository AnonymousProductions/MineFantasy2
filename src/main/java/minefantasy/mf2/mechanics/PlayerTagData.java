package minefantasy.mf2.mechanics;

import minefantasy.mf2.MineFantasyII;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerTagData
{
	private static String TECH_TIER_DATA = "MF_Tech_Tier";
	private static String MYTHIC_TIER_DATA = "MF_Mythic_Tier";
	/**
	 * Sets the player's tech tier if it's not lower (For when crafting items and such)
	 */
	public static void trySetTechTier(EntityPlayer user, int lvl)
	{
		if(user.worldObj.isRemote)return;
		
		NBTTagCompound nbt = getPersistedData(user);
		int prevLvl = nbt.hasKey(TECH_TIER_DATA) ? nbt.getInteger(TECH_TIER_DATA) : 0;
		
		if(lvl > prevLvl)
		{
			nbt.setInteger(TECH_TIER_DATA, lvl);
			MineFantasyII.debugMsg("Set User Tech tier to " + lvl);
		}
	}
	/**
	 * Gets the "tech tier" of the player, made to determine the tier of material their progressed to
	 */
	public static int getTechTier(EntityPlayer user)
	{
		NBTTagCompound nbt = getPersistedData(user);
		
		return nbt.hasKey(TECH_TIER_DATA) ? nbt.getInteger(TECH_TIER_DATA) : 0;
	}
	
	/**
	 * Sets the player's mythic tier if it's not lower (For when crafting items and such)
	 */
	public static void trySetMythicTier(EntityPlayer user, int lvl)
	{
		if(user.worldObj.isRemote)return;
		
		NBTTagCompound nbt = getPersistedData(user);
		int prevLvl = nbt.hasKey(MYTHIC_TIER_DATA) ? nbt.getInteger(MYTHIC_TIER_DATA) : 0;
		
		if(lvl > prevLvl)
		{
			nbt.setInteger(MYTHIC_TIER_DATA, lvl);
			MineFantasyII.debugMsg("Set User Mythic tier to " + lvl);
		}
	}
	/**
	 * Gets the "mythic tier" of the player, made to determine the tier of material their progressed to
	 */
	public static int getMythicTier(EntityPlayer user)
	{
		NBTTagCompound nbt = getPersistedData(user);
		
		return nbt.hasKey(MYTHIC_TIER_DATA) ? nbt.getInteger(MYTHIC_TIER_DATA) : 0;
	}
	
	/**
	 * Saves a custom integer
	 */
	public static void setPersistedVariable(EntityPlayer user, String name, int lvl)
	{
		NBTTagCompound nbt = getPersistedData(user);
		nbt.setInteger(name, lvl);
	}
	/**
	 * Gets a custom integer saved
	 */
	public static int getPersistedVariable(EntityPlayer user, String name)
	{
		NBTTagCompound nbt = getPersistedData(user);
		return nbt.hasKey(name) ? nbt.getInteger(name) : 0;
	}
	
	
	public static NBTTagCompound getPersistedData(EntityPlayer user)
	{
		if(!user.getEntityData().hasKey(user.PERSISTED_NBT_TAG))
		{
			user.getEntityData().setTag(user.PERSISTED_NBT_TAG, new NBTTagCompound());
		}
		return (NBTTagCompound) (user.getEntityData().getTag(user.PERSISTED_NBT_TAG));
	}
}
