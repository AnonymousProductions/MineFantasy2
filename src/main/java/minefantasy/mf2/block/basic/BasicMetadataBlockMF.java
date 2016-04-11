package minefantasy.mf2.block.basic;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * This is suppose to be a generic class for creating blocks with subtypes, but its not working;
 * as items they are fine, but placing the block in the world does not work.  Leaving this for 
 * not, but its not used.
 * 
 * @author BlackJar72
 *
 */
public class BasicMetadataBlockMF extends BasicBlockMF {
	private final String NAME;
	private final int NUMBER;
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	
	public BasicMetadataBlockMF(String name, Material material, Class itemBlock, int number)
	{
		super(name, material, null);
		NUMBER = number;
		NAME = name;
		GameRegistry.registerBlock(this, itemBlock, NAME);
	}
	
	
	public BasicMetadataBlockMF(String name, Material material, Class itemBlock, int number, Object drop)
	{
		super(material);
		NUMBER = number;
		NAME = name;
		
		setBlockName(NAME);
		
		if(material == Material.rock)
		{
			this.setHarvestLevel("pickaxe", 0);
		}
		setItemDropped(drop);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
		for(int i = 0; i < NUMBER; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iReg) {
		icons = new IIcon[NUMBER];		
		for(int i=0; i < NUMBER; i++) {
			icons[i] = iReg.registerIcon("minefantasy2:basic/" + NAME + i);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p1, int meta) {
		return icons[meta];
	}
	
	
	private void setItemDropped(Object item)
    {
		if(item == null) {
			drop = Item.getItemFromBlock(this); 
		} else {
			if(drop instanceof Item)
			{
				drop = item;
			} else if(drop instanceof Block) {
				drop = Item.getItemFromBlock((Block) drop);
			} else {
				// failsafe
				drop = Item.getItemFromBlock(this);
			}
		} 
    }
	
	@Override
	public Item getItemDropped(int meta, Random rand, int i)
    {
		return (Item)drop;
    }
	
	@Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
