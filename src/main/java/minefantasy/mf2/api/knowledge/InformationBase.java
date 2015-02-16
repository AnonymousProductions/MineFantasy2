package minefantasy.mf2.api.knowledge;

import java.util.ArrayList;

import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
import minefantasy.mf2.api.knowledge.client.EntryPage;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.IStatStringFormat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InformationBase
{
	public int ID = 0;
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
    private boolean isPerk;
    private final String idName;
    private final int baseLevelCost;

    public InformationBase(String name, int x, int y, int cost, Item icon, InformationBase parent)
    {
        this(name, x, y, cost, new ItemStack(icon), parent);
    }
    public InformationBase(String name, int x, int y, int cost, Block icon, InformationBase parent)
    {
        this(name, x, y, cost, new ItemStack(icon), parent);
    }

    public InformationBase(String name, int x, int y, int cost, ItemStack icon, InformationBase parent)
    {
    	this.baseLevelCost = cost;
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
    public InformationBase setPerk()
    {
        this.isPerk = true;
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
        InformationList.nameMap.put(idName, this);
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
    public boolean getPerk()
    {
        return this.isPerk;
    }

	public boolean trigger(EntityPlayer user, boolean makeEffect)
	{
		boolean success = ResearchLogic.tryUnlock(user, this);
		if(success && makeEffect && !user.worldObj.isRemote)
		{
			user.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("knowledge.unlocked") + ": " + StatCollector.translateToLocal(getName())));
			user.worldObj.playSoundAtEntity(user, "minefantasy2:updateResearch", 1.0F, 1.0F);
		}
		return success;
	}

	public String getUnlocalisedName()
	{
		return idName;
	}
	
	public int getCost()
	{
		return baseLevelCost;
	}
	
	@SideOnly(Side.CLIENT)
	private ArrayList<EntryPage> pages = new ArrayList<EntryPage>();
	@SideOnly(Side.CLIENT)
	public void addPages(EntryPage... info)
	{
		for(EntryPage page: info)
		{
			pages.add(page);
		}
	}
	@SideOnly(Side.CLIENT)
	public ArrayList<EntryPage> getPages()
	{
		return pages;
	}
}