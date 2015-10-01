package minefantasy.mf2.item.tool;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.tool.ILighter;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.item.Item;

public class ItemLighterMF extends Item implements ILighter
{
	private float chance;
	public ItemLighterMF(String name, float chance, int uses)
	{
		setTextureName("minefantasy2:Tool/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
		setMaxDamage(uses);
		this.chance = chance;
		this.setCreativeTab(CreativeTabMF.tabGadget);
	}
	@Override
	public boolean canLight()
	{
		return true;
	}
	@Override
	public double getChance() 
	{
		return chance/100D;
	}
}
