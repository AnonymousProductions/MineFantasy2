package minefantasy.mf2.api.knowledge;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.helpers.PlayerTagData;
import minefantasy.mf2.network.packet.KnowledgePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResearchLogic
{
	public static boolean canPurchase(EntityPlayer player, InformationBase base)
    {
		if(base.isPreUnlocked() || !canUnlockInfo(player, base))
		{
			return false;
		}
		NBTTagCompound nbt = getNBT(player);
		if(!nbt.hasKey("Research_" + base.getUnlocalisedName()))
		{
			return true;
		}
		return false;
    }
	public static boolean tryUnlock(EntityPlayer player, InformationBase base)
    {
		if(base.isPreUnlocked() || !canUnlockInfo(player, base))
		{
			return false;
		}
		NBTTagCompound nbt = getNBT(player);
		if(!nbt.hasKey("Research_" + base.getUnlocalisedName()))
		{
			nbt.setBoolean("Research_" + base.getUnlocalisedName(), true);
			return true;
		}
		return false;
    }
	
	public static boolean hasInfoUnlocked(EntityPlayer player, InformationBase base)
    {
		if(base.isPreUnlocked())return base.parentInfo == null || hasInfoUnlocked(player, base.parentInfo);
		
		NBTTagCompound nbt = getNBT(player);
		String basename = base.getUnlocalisedName();
		if(nbt.hasKey("Research_" + basename))
		{
			return nbt.getBoolean("Research_" + basename);
		}
        return false;
    }
	
	public static boolean hasInfoUnlocked(EntityPlayer player, String basename)
    {
		InformationBase base = InformationList.nameMap.get(basename);
		if(base != null)
		{
			return hasInfoUnlocked(player, base);
		}
        return true;
    }
	
	public static boolean hasInfoUnlocked(EntityPlayer player, String[] basenames)
    {
		for(String basename : basenames)
		{
			InformationBase base = InformationList.nameMap.get(basename);
			if(base != null)
			{
				if(!hasInfoUnlocked(player, base))
				{
					return false;
				}
			}
		}
        return true;
    }

    private static NBTTagCompound getNBT(EntityPlayer player)
	{
    	NBTTagCompound persistant = PlayerTagData.getPersistedData(player);
    	if(!persistant.hasKey(KnowledgeNBT))
    	{
    		NBTTagCompound tag = new NBTTagCompound();
    		tag.setInteger("Layer", knowledgelyr);
    		persistant.setTag(KnowledgeNBT, tag);
    	}
    	NBTTagCompound load = persistant.getCompoundTag(KnowledgeNBT);
    	if(load.getInteger("Layer") != knowledgelyr)
    	{
    	   persistant.removeTag(KnowledgeNBT);
    		
    		NBTTagCompound tag = new NBTTagCompound();
    		tag.setInteger("Layer", knowledgelyr);
    		persistant.setTag(KnowledgeNBT, tag);
    	}
		return load;
	}

	/**
     * Returns true if the parent has been unlocked, or there is no parent
     */
    public static boolean canUnlockInfo(EntityPlayer player, InformationBase base)
    {
        return base.parentInfo == null || hasInfoUnlocked(player, base.parentInfo);
    }
    
    @SideOnly(Side.CLIENT)
    public static int func_150874_c(EntityPlayer player, InformationBase base)
    {
        if (hasInfoUnlocked(player, base))
        {
            return 0;
        }
        else
        {
            int i = 0;

            for (InformationBase knowledge1 = base.parentInfo; knowledge1 != null && !hasInfoUnlocked(player, knowledge1); ++i)
            {
                knowledge1 = knowledge1.parentInfo;
            }
            return i;
        }
    }
    
    public static final String KnowledgeNBT = "Knowledge";
    public static int knowledgelyr = 0;
    public static void syncData(EntityPlayer player)
    {
    	if(!player.worldObj.isRemote)
		{
			((WorldServer)player.worldObj).getEntityTracker().func_151248_b(player, new KnowledgePacket(player).generatePacket());
		}
    }
}
