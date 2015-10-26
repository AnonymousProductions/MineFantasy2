package minefantasy.mf2.item.gadget;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.api.crafting.bomb.IBombComponent;
import minefantasy.mf2.item.ItemComponentMF;

public class ItemBombComponent extends ItemComponentMF implements IBombComponent
{
	private byte tier;
	private String type;
	public ItemBombComponent(String name, String type, int tier)
	{
		this(name, 0, type, tier);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer user, List list, boolean fullInfo)
	{
		list.add(EnumChatFormatting.GOLD +   StatCollector.translateToLocal("bomb.component.name"));
		list.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("bomb.component." + type));
	}
	public ItemBombComponent(String name, int rarity, String type, int tier)
	{
		super(name, rarity);
		this.type = type;
		this.tier = (byte)tier;
	}

	@Override
	public String getComponentType()
	{
		return type;
	}

	@Override
	public byte getTier()
	{
		return tier;
	}

}
