package minefantasy.mf2.knowledge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.IStatStringFormat;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class InformationBase
{
	public int ID;
	private static int nextID = 0;
	public boolean startedUnlocked = false;
    public final int displayColumn;
    public final int displayRow;
    public final InformationBase parentInfo;
    private final String description;
    @SideOnly(Side.CLIENT)
    private IStatStringFormat statStringFormatter;
    public final ItemStack theItemStack;
    private boolean isSpecial;
    private final String idName;
    private final String modID;

    public InformationBase(String name, int x, int y, Item icon, InformationBase parent, String modid)
    {
        this(name, x, y, new ItemStack(icon), parent, modid);
    }
    public InformationBase(String name, int x, int y, Block icon, InformationBase parent, String modid)
    {
        this(name, x, y, new ItemStack(icon), parent, modid);
    }

    public InformationBase(String name, int x, int y, ItemStack icon, InformationBase parent, String modid)
    {
    	ID = nextID;
    	++nextID;
    	this.modID = modid;
    	this.idName = name;
        this.theItemStack = icon;
        this.description = "knowledge." + idName + ".desc";
        this.displayColumn = x;
        this.displayRow = y;

        if (x < InformationList.minDisplayColumn)
        {
            InformationList.minDisplayColumn = x;
        }

        if (y < InformationList.minDisplayRow)
        {
            InformationList.minDisplayRow = y;
        }

        if (x > InformationList.maxDisplayColumn)
        {
            InformationList.maxDisplayColumn = x;
        }

        if (y > InformationList.maxDisplayRow)
        {
            InformationList.maxDisplayRow = y;
        }

        this.parentInfo = parent;
    }
    
    public InformationBase setUnlocked()
    {
    	startedUnlocked = true;
    	return this;
    }
    public InformationBase setPage(InformationPage page)
    {
    	page.addInfo(this);
    	return this;
    }

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    public InformationBase setSpecial()
    {
        this.isSpecial = true;
        return this;
    }

    /**
     * Register the stat into StatList.
     */
    public InformationBase registerStat()
    {
    	ID = nextID;
    	nextID++;
        InformationList.knowledgeList.add(this);
        return this;
    }

/*
    public IChatComponent func_150951_e()
    {
        IChatComponent ichatcomponent = super.func_150951_e();
        ichatcomponent.getChatStyle().setColor(this.getSpecial() ? EnumChatFormatting.DARK_PURPLE : EnumChatFormatting.GREEN);
        return ichatcomponent;
    }

    public InformationBase func_150953_b(Class p_150953_1_)
    {
        return (InformationBase)super.func_150953_b(p_150953_1_);
    }
*/
    /**
     * Returns the fully description of the achievement - ready to be displayed on screen.
     */
    @SideOnly(Side.CLIENT)
    public String getDescription()
    {
        return this.statStringFormatter != null ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.description)) : StatCollector.translateToLocal(this.description);
    }
    @SideOnly(Side.CLIENT)
    public String getName()
    {
        return this.statStringFormatter != null ? this.statStringFormatter.formatString(StatCollector.translateToLocal("knowledge."+this.idName)) : StatCollector.translateToLocal("knowledge."+this.idName);
    }

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    public boolean getSpecial()
    {
        return this.isSpecial;
    }

	public void trigger(EntityPlayer user, boolean makeEffect)
	{
		if(ResearchLogic.tryUnlock(user, this) && makeEffect)
		{
			user.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("knowledge.unlocked") + ": " + StatCollector.translateToLocal(getName())));
			user.playSound("minefantasy2:updateResearch", 1.0F, 1.0F);
		}
	}

	public String getUniqueName()
	{
		return modID +  "_" + idName;
	}
}