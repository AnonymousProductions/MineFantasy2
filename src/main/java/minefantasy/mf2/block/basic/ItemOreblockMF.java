package minefantasy.mf2.block.basic;

import java.util.List;

import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemOreblockMF extends ItemBlock 
{
	private BlockOreMF ore;
	public ItemOreblockMF(Block block) 
	{
		super(block);
		ore = (BlockOreMF) block;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		int lvl = ore.rarity+1;
		
		if(item.isItemEnchanted())
		{
			if(lvl == 0)
			{
				lvl++;
			}
			lvl ++;
		}
		if(lvl >= ToolListMF.rarity.length)
		{
			lvl = ToolListMF.rarity.length-1;
		}
		return ToolListMF.rarity[lvl];
	}
}
