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
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class InformationBase extends StatBase
{
	public boolean startedUnlocked = false;
    /** Is the column (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed. */
    public final int displayColumn;
    /** Is the row (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed. */
    public final int displayRow;
    /** Holds the parent achievement, that must be taken before this achievement is avaiable. */
    public final InformationBase parentInfo;
    /** Holds the description of the achievement, ready to be formatted and/or displayed. */
    private final String achievementDescription;
    /**
     * Holds a string formatter for the achievement, some of then needs extra dynamic info - like the key used to open
     * the inventory.
     */
    @SideOnly(Side.CLIENT)
    private IStatStringFormat statStringFormatter;
    /** Holds the ItemStack that will be used to draw the achievement into the GUI. */
    public final ItemStack theItemStack;
    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    private boolean isSpecial;

    public InformationBase(String p_i45300_1_, String p_i45300_2_, int p_i45300_3_, int p_i45300_4_, Item p_i45300_5_, InformationBase p_i45300_6_)
    {
        this(p_i45300_1_, p_i45300_2_, p_i45300_3_, p_i45300_4_, new ItemStack(p_i45300_5_), p_i45300_6_);
    }

    public InformationBase(String p_i45301_1_, String p_i45301_2_, int p_i45301_3_, int p_i45301_4_, Block p_i45301_5_, InformationBase p_i45301_6_)
    {
        this(p_i45301_1_, p_i45301_2_, p_i45301_3_, p_i45301_4_, new ItemStack(p_i45301_5_), p_i45301_6_);
    }

    public InformationBase(String p_i45302_1_, String p_i45302_2_, int p_i45302_3_, int p_i45302_4_, ItemStack p_i45302_5_, InformationBase p_i45302_6_)
    {
        super(p_i45302_1_, new ChatComponentTranslation("knowledge." + p_i45302_2_, new Object[0]));
        this.theItemStack = p_i45302_5_;
        this.achievementDescription = "knowledge." + p_i45302_2_ + ".desc";
        this.displayColumn = p_i45302_3_;
        this.displayRow = p_i45302_4_;

        if (p_i45302_3_ < InformationList.minDisplayColumn)
        {
            InformationList.minDisplayColumn = p_i45302_3_;
        }

        if (p_i45302_4_ < InformationList.minDisplayRow)
        {
            InformationList.minDisplayRow = p_i45302_4_;
        }

        if (p_i45302_3_ > InformationList.maxDisplayColumn)
        {
            InformationList.maxDisplayColumn = p_i45302_3_;
        }

        if (p_i45302_4_ > InformationList.maxDisplayRow)
        {
            InformationList.maxDisplayRow = p_i45302_4_;
        }

        this.parentInfo = p_i45302_6_;
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
     * Initializes the current stat as independent (i.e., lacking prerequisites for being updated) and returns the
     * current instance.
     */
    public InformationBase initIndependentStat()
    {
        this.isIndependent = true;
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
        super.registerStat();
        InformationList.achievementList.add(this);
        return this;
    }

    /**
     * Returns whether or not the StatBase-derived class is a statistic (running counter) or an achievement (one-shot).
     */
    public boolean isAchievement()
    {
        return false;
    }

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

    /**
     * Returns the fully description of the achievement - ready to be displayed on screen.
     */
    @SideOnly(Side.CLIENT)
    public String getDescription()
    {
        return this.statStringFormatter != null ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.achievementDescription)) : StatCollector.translateToLocal(this.achievementDescription);
    }

    /**
     * Defines a string formatter for the achievement.
     */
    @SideOnly(Side.CLIENT)
    public InformationBase setStatStringFormatter(IStatStringFormat p_75988_1_)
    {
        this.statStringFormatter = p_75988_1_;
        return this;
    }

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    public boolean getSpecial()
    {
        return this.isSpecial;
    }

	public void trigger(EntityPlayer user, boolean playSound)
	{
		user.addStat(this, 1);
		if(playSound && Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getStatFileWriter().writeStat(this) <= 0)
		{
			user.playSound("minefantasy2:updateResearch", 1.0F, 1.0F);
		}
	}
}