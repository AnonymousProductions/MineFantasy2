package minefantasy.mf2.block.crafting;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockRepairKit extends ItemBlock 
{
	private BlockRepairKit kit;
	public ItemBlockRepairKit(Block base) 
	{
		super(base);
		this.setMaxStackSize(8);
		this.kit = (BlockRepairKit)base;
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer user, List list, boolean extra)
	{
		list.add(StatCollector.translateToLocal("attribute.kit.repairRate.name") + ": " + kit.repairLevel*100F);
		list.add(StatCollector.translateToLocal("attribute.kit.repairChance.name") + ": " + kit.successRate*100F);
		list.add(StatCollector.translateToLocal("attribute.kit.breakChance.name") + ": " + kit.breakChance*100F);
		super.addInformation(item, user, list, extra);
	}
}
