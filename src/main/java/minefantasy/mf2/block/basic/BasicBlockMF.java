package minefantasy.mf2.block.basic;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicBlockMF extends Block 
{
	private Object drop;
	private String NAME;
	
	public BasicBlockMF(String name, Material material)
	{
		this(name, material, null);
	}
	public BasicBlockMF(String name, Material material, Object drop)
	{
		super(material);
		
		GameRegistry.registerBlock(this, name);
		setUnlocalizedName(MineFantasyII.MODID +"_" +name);
		//setBlockTextureName("minefantasy2:basic/"+name);
		 this.setDefaultState(this.blockState.getBaseState());
		NAME = name;
		if(material == Material.rock)
		{
			this.setHarvestLevel("pickaxe", 0);
		}
		this.drop = drop;
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public String getName()
	{
		return NAME;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube()
	{
		return this.blockMaterial != Material.glass;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int i)
    {
		if(drop != null)
		{
			if(drop instanceof Item)
			{
				return (Item) drop;
			}
			if(drop instanceof Block)
			{
				return Item.getItemFromBlock((Block) drop);
			}
		}
		return Item.getItemFromBlock(this);
    }
}
