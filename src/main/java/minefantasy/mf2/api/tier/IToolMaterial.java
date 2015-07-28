package minefantasy.mf2.api.tier;

import net.minecraft.block.Block;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public interface IToolMaterial 
{
	/**
	 * Gets the material for outside reference
	 */
	public ToolMaterial getMaterial();

	float getDigSpeed(ItemStack stack, net.minecraft.block.state.IBlockState state);

}
