package minefantasy.mf2.item;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import minefantasy.mf2.api.knowledge.InformationBase;
import minefantasy.mf2.api.knowledge.InformationList;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.Skill;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSkillBook extends ItemComponentMF
{
	private Skill skill;
	private String name;
	public ItemSkillBook(String name, Skill skill)
	{
		super(name, 1);
		setMaxStackSize(1);
		setTextureName("minefantasy2:Other/"+name);
		this.setCreativeTab(CreativeTabMF.tabGadget);
		this.skill = skill;
		this.name=name;
	}
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer user, List list, boolean fullInfo)
	{
		list.add(StatCollector.translateToLocalFormatted("item."+name+".desc", 1));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
	{
		boolean used = false;
		if(skill != null)
		{
			int lvl = RPGElements.getLevel(user, skill);
			if(lvl < skill.getMaxLevel())
			{
				skill.addXP(user, skill.getLvlXP(lvl, user));
				used = true;
			}
		}
		if(used)
		{
			user.worldObj.playSoundEffect(user.posX, user.posY, user.posZ, "minefantasy2:updateResearch", 1.0F, 1.0F);
			if(!user.capabilities.isCreativeMode)
			{
				--item.stackSize;
			}
		}
		return item;
	}
}
