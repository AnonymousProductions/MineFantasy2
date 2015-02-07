package minefantasy.mf2.knowledge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatFileWriter;

public class KnowledgeFileWriter
{
	private final StatFileWriter statBase;
	public KnowledgeFileWriter(StatFileWriter writer)
	{
		statBase = writer;
	}
			
	public boolean hasInfoUnlocked(InformationBase base)
    {
        return MineFantasyII.isDebug() || base.startedUnlocked || statBase.writeStat(base) > 0;
    }

    /**
     * Returns true if the parent has been unlocked, or there is no parent
     */
    public boolean canUnlockInfo(InformationBase p_77442_1_)
    {
        return p_77442_1_.parentInfo == null || this.hasInfoUnlocked(p_77442_1_.parentInfo);
    }
    
    @SideOnly(Side.CLIENT)
    public int func_150874_c(InformationBase p_150874_1_)
    {
        if (this.hasInfoUnlocked(p_150874_1_))
        {
            return 0;
        }
        else
        {
            int i = 0;

            for (InformationBase achievement1 = p_150874_1_.parentInfo; achievement1 != null && !this.hasInfoUnlocked(achievement1); ++i)
            {
                achievement1 = achievement1.parentInfo;
            }

            return i;
        }
    }
}
