package minefantasy.mf2.knowledge;

import java.util.Iterator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.mechanics.PlayerTagData;
import minefantasy.mf2.network.packet.KnowledgePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.world.WorldServer;

public class ResearchLogic
{
	public static boolean tryUnlock(EntityPlayer player, InformationBase base)
    {
		if(base.startedUnlocked)
		{
			return false;
		}
		NBTTagCompound nbt = getNBT(player);
		if(!nbt.hasKey("Research_" + base.getUniqueName()))
		{
			nbt.setBoolean("Research_" + base.getUniqueName(), true);
			return true;
		}
		return false;
    }
	
	public static boolean hasInfoUnlocked(EntityPlayer player, InformationBase base)
    {
		if(base.startedUnlocked)
		{
			return true;
		}
		NBTTagCompound nbt = getNBT(player);
		if(nbt.hasKey("Research_" + base.getUniqueName()))
		{
			return nbt.getBoolean("Research_" + base.getUniqueName());
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
    public int func_150874_c(EntityPlayer player, InformationBase base)
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
    
    public static void preparePacket(EntityPlayer player)
    {
    	if(!player.worldObj.isRemote)
		{
			((WorldServer)player.worldObj).getEntityTracker().func_151248_b(player, new KnowledgePacket(player).generatePacket());
		}
    }
}
