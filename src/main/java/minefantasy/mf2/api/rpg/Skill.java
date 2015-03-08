package minefantasy.mf2.api.rpg;

import minefantasy.mf2.api.MineFantasyAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;

public class Skill 
{
	public final String skillName;
	public Skill(String name)
	{
		this.skillName = name;
	}
	public int getMaxLevel()
	{
		return 100;
	}
	public int getStartLevel()
	{
		return 1;
	}
	public Skill register()
	{
		RPGElements.addSkill(this);
		return this;
	}
	
	/**
	 * Gets how much xp needed to level up
	 */
	public int getLvlXP(int level, EntityPlayer user)
	{
		if(level >= getMaxLevel())
		{
			return -1;
		}
		
		float rise = 0.2F * RPGElements.levelUpModifier;//20% rize each level
		return (int) Math.floor(100F * (1.0F + (rise*(level-1))));
	}
	
	/**
	 * Adds xp to the skill, negative values take xp away
	 */
	public void addXP(EntityPlayer player, int xp)
	{
		xp = (int)(RPGElements.levelSpeedModifier * xp);
		NBTTagCompound skill = RPGElements.getSkill(player, skillName);
		if(skill == null)return;
		
		int value = skill.getInteger("xp");
		int max = skill.getInteger("xpMax");
		
		if(max <= 0)
		{
			return;
		}
		value += xp;
		skill.setInteger("xp", value);
		
		if(value >= max)
		{
			value -= max;
			int level = skill.getInteger("level") + 1;
			skill.setInteger("level", level);
			skill.setInteger("xp", value);
			skill.setInteger("xpMax", getLvlXP(level, player));
			levelUp(player, level);
		}
		else
		{
			if(value < 0)
			{
				int level = skill.getInteger("level") - 1;
				if(level < 1)return;
				
				skill.setInteger("level", level);
				
				int newMax = getLvlXP(level, player);
				skill.setInteger("xp", value+newMax);
				skill.setInteger("xpMax", newMax);
			}
		}
	}
	private void levelUp(EntityPlayer player, int newlvl) 
	{
		if(!player.worldObj.isRemote)
		{
			MineFantasyAPI.debugMsg("Level up detected for " + skillName);
			MinecraftForge.EVENT_BUS.post(new LevelupEvent(player, this, newlvl));
		}
	}
	/**
	 * This is a universal way to tell a skill to do something
	 * @param functionID a string representing what to do, depends on the skill
	 */
	public void callFunction(EntityPlayer user, String functionID)
	{
	}
	
	public void init(NBTTagCompound tag, EntityPlayer player) 
	{
		int start = getStartLevel();
		tag.setInteger("level", start);
		tag.setInteger("xp", 0);
		tag.setInteger("xpMax", getLvlXP(start, player));
	}
	public void sync(NBTTagCompound tag, EntityPlayer player) 
	{
		if(!player.worldObj.isRemote)
		{
			MineFantasyAPI.debugMsg("Sync skill: " + skillName);
			MinecraftForge.EVENT_BUS.post(new SyncSkillEvent(player, this));
		}
	}
	
	public String getDisplayName()
	{
		return StatCollector.translateToLocal("skill."+skillName+".name");
	}
}
