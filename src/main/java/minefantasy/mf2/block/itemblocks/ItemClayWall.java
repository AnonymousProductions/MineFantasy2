package minefantasy.mf2.block.itemblocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Supposed to be the BlockItem for the BasicMetadataBlockMF for clay wall, which is not working,
 * but leaving both for now.
 * 
 * @author BlackJar72
 *
 */

public class ItemClayWall extends ItemBlock {

	public ItemClayWall(Block block) {
		super(block);
		setHasSubtypes(true);
		GameRegistry.registerItem(this, block.getUnlocalizedName(), "minefantasy2");
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item) {
		return getUnlocalizedName() + item.getItemDamage();
	}
}
