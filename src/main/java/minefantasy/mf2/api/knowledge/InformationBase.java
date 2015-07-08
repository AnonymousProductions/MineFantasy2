package minefantasy.mf2.api.knowledge;

import java.util.ArrayList;

import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
import minefantasy.mf2.api.knowledge.client.EntryPage;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.Skill;
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
	private int talismanCost, greatTaliCost;
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
    private ArrayList<SkillRequirement> skills = new ArrayList<SkillRequirement>();
    public String[] requirements = null;

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
        talismanCost = 1;
        this.parentInfo = parent;
    }
    
    public InformationBase addSkill(Skill skill, int level)
    {
    	if(RPGElements.isSystemActive)
    	{
    		skills.add(new SkillRequirement(skill, level));
    	}
    	return this;
    }
    
    public InformationBase setTalismanCount(int count)
    {
    	this.talismanCost = count;
    	return this;
    }
    public InformationBase setGreatTalismanCount(int count)
    {
    	this.greatTaliCost = count;
    	return this;
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
        String text = this.statStringFormatter != null ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.description)) : StatCollector.translateToLocal(this.description);
        
        if(RPGElements.isSystemActive)
    	{
    		String[] requirements = getRequiredSkills();
    		if(requirements != null && requirements.length > 0)
    		{
    			text += "\n\n";
    			for(String s : requirements)
    			{
    				text += s + "\n";
    			}
    		}
    	}
        if(allowTalisman)
        {
        	if(talismanCost > 0)
        	{
        		text += "\n\n";
        		text += StatCollector.translateToLocal("cost.talisman") + talismanCost;
        	}
        	if(greatTaliCost > 0)
        	{
        		text += "\n\n";
        		text += StatCollector.translateToLocal("cost.talismanGreat") + greatTaliCost;
        	}
        }
        
        return text;
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
    public static boolean allowTalisman = true;
	public boolean trigger(EntityPlayer user, boolean makeEffect)
	{
		if(allowTalisman && !hasTalismans(user))
		{
			return false;
		}
		if(RPGElements.isSystemActive)
		{
			if(!hasSkillsUnlocked(user))
			{
				return false;
			}
		}
		boolean success = ResearchLogic.tryUnlock(user, this);
		if(success && makeEffect && !user.worldObj.isRemote)
		{
			user.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("knowledge.unlocked") + ": " + StatCollector.translateToLocal(getName())));
			user.worldObj.playSoundAtEntity(user, "minefantasy2:updateResearch", 1.0F, 1.0F);
		}
		return success;
	}

	public static Item talismanItem, greatTaliItem;
	private boolean hasTalismans(EntityPlayer user)
	{
		if(user.capabilities.isCreativeMode)
		{
			return true;
		}
		if(talismanCost > 0 && !hasItems(user, talismanItem, talismanCost))
		{
			return false;
		}
		if(greatTaliCost > 0 && !user.inventory.consumeInventoryItem(greatTaliItem))
		{
			return false;
		}
		return true;
	}
	private boolean hasItems(EntityPlayer user, Item item, int cost)
	{
		int total = 0;
		for(int a = 0; a < user.inventory.getSizeInventory(); a++)
		{
			ItemStack slot = user.inventory.getStackInSlot(a);
			if(slot != null && slot.getItem() == item)
			{
				total += slot.stackSize;
			}
		}
		if(total >= cost)
		{
			for(int a = 0; a < cost; a++)
			{
				user.inventory.consumeInventoryItem(item);
			}
			return true;
		}
		return false;
	}
	private boolean hasSkillsUnlocked(EntityPlayer player)
	{
		for(int id = 0; id < skills.size(); id++)
		{
			SkillRequirement requirement = skills.get(id);
			if(!requirement.isAvailable(player))
			{
				return false;
			}
		}
		return true;
	}
	public String getUnlocalisedName()
	{
		return idName;
	}
	
	public int getCost()
	{
		return baseLevelCost;
	}
	
	private ArrayList<EntryPage> pages = new ArrayList<EntryPage>();
	public void addPages(EntryPage... info)
	{
		for(EntryPage page: info)
		{
			pages.add(page);
		}
	}
	public ArrayList<EntryPage> getPages()
	{
		return pages;
	}
	
	public String[] getRequiredSkills()
	{
		if(this.requirements == null)
		{
			requirements = new String[skills.size()];
			for(int id = 0; id < skills.size(); id++)
			{
				SkillRequirement requirement = skills.get(id);
				requirements[id] = StatCollector.translateToLocalFormatted("rpg.required", requirement.level, requirement.skill.getDisplayName());
			}
		}
		return requirements;
	}
}
class SkillRequirement
{
	protected Skill skill;
	protected int level;
	SkillRequirement(Skill skill, int level)
	{
		this.skill = skill;
		this.level = level;
	}
	public boolean isAvailable(EntityPlayer player)
	{
		return RPGElements.hasLevel(player, skill, level);
	}
}
