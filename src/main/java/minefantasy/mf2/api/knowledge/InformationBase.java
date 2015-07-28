package minefantasy.mf2.api.knowledge;

import java.util.ArrayList;

import minefantasy.mf2.api.knowledge.client.EntryPage;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.Skill;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.IStatStringFormat;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InformationBase
{
	public static final float talismanPower = 20F;//20m taken
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
    private ArrayList<SkillRequirement> skills = new ArrayList<SkillRequirement>();
    public String[] requirements = null;
    private ItemStack[] requiredItems;
    private int minutes = 10;

    public InformationBase(String name, int x, int y, int time, Item icon, InformationBase parent)
    {
        this(name, x, y, time, new ItemStack(icon), parent);
    }
    public InformationBase(String name, int x, int y, int time, Block icon, InformationBase parent)
    {
        this(name, x, y, time, new ItemStack(icon), parent);
    }

    public InformationBase(String name, int x, int y, int time, ItemStack icon, InformationBase parent)
    {
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
        this.minutes = time;
    }
    
    public InformationBase addSkill(Skill skill, int level)
    {
    	if(RPGElements.isSystemActive)
    	{
    		skills.add(new SkillRequirement(skill, level));
    	}
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
    public InformationBase setItems(ItemStack...items)
    {
    	this.requiredItems = items;
    	return this;
    }
    public ItemStack[] getItems()
    {
    	return requiredItems;
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
	public boolean onPurchase(EntityPlayer user)
	{
		if(!this.hasAllItems(user))
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
		
		boolean success = ResearchLogic.canPurchase(user, this);
		if(success && !user.worldObj.isRemote)
		{
			this.consumeItems(user);
			user.worldObj.playSoundAtEntity(user, "minefantasy2:updateResearch", 1.0F, 1.0F);
		}
		
		ItemStack item = new ItemStack(scroll, 1, ID);
		if(!user.inventory.addItemStackToInventory(item))
		{
			user.entityDropItem(item, 0F);
		}
		
		return true;
	}
	public static Item scroll;

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
	
	public boolean hasAllItems(EntityPlayer user)
	{
		if(user.capabilities.isCreativeMode || this.requiredItems == null)
		{
			return true;
		}
		for(ItemStack item: requiredItems)
		{
			if(!hasItem(user, item.getItem(), item.stackSize))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean hasItem(EntityPlayer user, Item item, int cost)
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
		return total >= cost;
	}
	public void consumeItems(EntityPlayer user)
	{
		if(user.capabilities.isCreativeMode || this.requiredItems == null)
		{
			return;
		}
		for(ItemStack item: requiredItems)
		{
			for(int a = 0; a < item.stackSize; a++)
			{
				user.inventory.consumeInventoryItem(item.getItem());
			}
		}
	}
	public int getTime() 
	{
		return minutes;
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
