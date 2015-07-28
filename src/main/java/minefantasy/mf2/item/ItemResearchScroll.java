package minefantasy.mf2.item;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.knowledge.InformationBase;
import minefantasy.mf2.api.knowledge.InformationList;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemResearchScroll extends ItemComponentMF
{
	private boolean isComplete;
	public ItemResearchScroll(String name, boolean complete)
	{
		super(name, 0);
		setMaxStackSize(1);
		this.isComplete = complete;
		setUnlocalizedName("minefantasy2:Other/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setCreativeTab(CreativeTabMF.tabGadget);
		setHasSubtypes(true);
		setMaxDamage(0);
		
		if(!isComplete)
		{
			InformationBase.scroll = this;
		}
	}
	
	@Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
    }
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer user, List list, boolean fullInfo)
	{
		if(item.getItemDamage() >= InformationList.knowledgeList.size())
		{
			return;
		}
		InformationBase info = InformationList.knowledgeList.get(item.getItemDamage());
		if(info != null)
		{
			list.add(info.getName());
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
	{
		if(!isComplete)
		{
			return item;
		}
		boolean used = false;
		if(item.getItemDamage() >= InformationList.knowledgeList.size())
		{
			return item;
		}
		InformationBase info = InformationList.knowledgeList.get(item.getItemDamage());
		if(info != null)
		{
			if(ResearchLogic.hasInfoUnlocked(user, info))
			{
				user.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("knowledge.known")));
			}
			else
			{
				used = ResearchLogic.tryUnlock(user, info);
			}
		}
		if(used)
		{
			if(!user.worldObj.isRemote)
			{
				user.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("knowledge.unlocked") + ": " + StatCollector.translateToLocal(info.getName())));
				user.worldObj.playSoundAtEntity(user, "minefantasy2:updateResearch", 1.0F, 1.0F);
			}
			if(!user.capabilities.isCreativeMode)
			{
				--item.stackSize;
			}
		}
		return item;
	}
}
