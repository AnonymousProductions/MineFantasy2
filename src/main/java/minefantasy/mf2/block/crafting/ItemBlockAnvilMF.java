package minefantasy.mf2.block.crafting;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockAnvilMF extends ItemBlock
{
	private BlockAnvilMF anvil;
	public ItemBlockAnvilMF(Block block)
	{
		super(block);
		this.anvil = (BlockAnvilMF) block;
	}

	@Override
	public void addInformation(ItemStack item, EntityPlayer user, List list, boolean extra)
	{
		list.add(StatCollector.translateToLocal("attribute.mfcrafttier.name") + ": " + anvil.getTier());
		super.addInformation(item, user, list, extra);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
