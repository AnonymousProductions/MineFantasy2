package minefantasy.mf2.item;

import java.util.Random;

import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.Skill;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemResearchScroll extends ItemComponentMF
{
	private final int pointsWorth;
	public ItemResearchScroll(String name, int pts, int tier)
	{
		super(name, tier);
		this.pointsWorth = pts;
		setTextureName("minefantasy2:Other/"+name);
		this.setCreativeTab(CreativeTabMF.tabGadget);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
	{
		ResearchLogic.modifyKnowledgePoints(user, pointsWorth);
		if(RPGElements.isSystemActive)
		{
			for(int a = 0; a < 1 + rand.nextInt(pointsWorth); a++)
			{
				Skill skill = RPGElements.getRandomSkill();
				skill.addXP(user, skill.getLvlXP(RPGElements.getLevel(user, skill), user));
			}
		}
		if(!user.capabilities.isCreativeMode)
		{
			--item.stackSize;
		}
		if(!world.isRemote)
		{
			world.playSoundAtEntity(user, "minefantasy2:updateResearch", 2.0F, 0.9F);
			user.addChatMessage(new ChatComponentText(StatCollector.translateToLocalFormatted("knowledge.gainPts", ""+pointsWorth)));
		}
		return item;
	}
	private static Random rand = new Random();
	public static ItemStack getRandomDrop()
	{
		Item drop = ToolListMF.research_scroll;
		if(rand.nextFloat() < 0.05F)
		{
			drop = ToolListMF.research_scroll_rare;//5% drop rate
		}
		else if(rand.nextFloat() < 0.25F)
		{
			drop = ToolListMF.research_scroll_uncommon;//25% drop rate
		}
		return new ItemStack(drop, 1);
	}
}
