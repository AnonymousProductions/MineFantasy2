package minefantasy.mf2.api.knowledge;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.mechanics.PlayerTagData;
import minefantasy.mf2.network.packet.KnowledgePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ResearchLogic
{
	public static boolean tryUnlock(EntityPlayer player, InformationBase base)
    {
		if(base.startedUnlocked || !canUnlockInfo(player, base))
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
	
	public static void modifyKnowledgePoints(EntityPlayer player, int mod)
	{
		int pts = getKnowledgePoints(player)+mod;
		if(pts < 0)pts = 0;
		setKnowledgePoints(player, pts);
	}
	public static void setKnowledgePoints(EntityPlayer player, int pts)
	{
		NBTTagCompound nbt = getNBT(player);
		nbt.setInteger("KnowledgePts", pts);
	}
	public static int getKnowledgePoints(EntityPlayer player)
	{
		NBTTagCompound nbt = getNBT(player);
		if(nbt.hasKey("KnowledgePts"))
		{
			return nbt.getInteger("KnowledgePts");
		}
		return 0;
	}
	
	public static int getCost(EntityPlayer player, InformationBase base)
	{
		int baseCost = base.getCost();
		
		if(!base.getUnlocalisedName().equalsIgnoreCase("research2"))//to avoid research1 from lowering research2's cost
		{
			if(ResearchLogic.hasInfoUnlocked(player, "research2"))
			{
				baseCost = (int)((double)baseCost * 0.75F);
			}
			else if(ResearchLogic.hasInfoUnlocked(player, "research1"))
			{
				baseCost = (int)((double)baseCost * 0.9F);
			}
		}
		return baseCost;
	}

	public static boolean hasInfoUnlocked(EntityPlayer player, InformationBase base)
    {
		return base.startedUnlocked || hasInfoUnlocked(player, base.getUnlocalisedName());
    }
	
	public static boolean hasInfoUnlocked(EntityPlayer player, String basename)
    {
		NBTTagCompound nbt = getNBT(player);
		if(nbt.hasKey("Research_" + basename))
		{
			return nbt.getBoolean("Research_" + basename);
		}
        return false;
    }

    private static NBTTagCompound getNBT(EntityPlayer player)
	{
    	NBTTagCompound persistant = PlayerTagData.getPersistedData(player);
    	if(!persistant.hasKey("Knowledge"))
    	{
    		persistant.setTag("Knowledge", new NBTTagCompound());
    	}
		return persistant.getCompoundTag("Knowledge");
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
    
    public static void syncData(EntityPlayer player)
    {
    	if(!player.worldObj.isRemote)
		{
			((WorldServer)player.worldObj).getEntityTracker().func_151248_b(player, new KnowledgePacket(player).generatePacket());
		}
    }
}
