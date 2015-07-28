package minefantasy.mf2.block.food;

import net.minecraft.item.Item;

public class BlockPie extends BlockCakeMF
{

	private String NAME;
	
	public BlockPie(String name, Item slice)
	{
		super(name, slice);
		NAME=name;
		this.height = 1F/16F*6F;
	}
	
	public String getName()
	{
		return NAME;
	}

}
