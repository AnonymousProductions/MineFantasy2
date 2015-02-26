package minefantasy.mf2.block.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.MineFantasyII;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ConstructionBlockMF extends Block {

	public IIcon[] m_icons = new IIcon[4];
	public static final String[] m_names = new String[] {};
	
	
	public ConstructionBlockMF(String unlocName)
	{
		super(Material.rock);
		
		this.setBlockName(unlocName);
		
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		GameRegistry.registerBlock(this, ItemConstBlock.class, this.getUnlocalizedName());
		
		GameRegistry.registerBlock(new StairsConstBlock(unlocName + "_stair", this), unlocName + "_stair");
		
		addConstructRecipes(Item.getItemFromBlock(this));
		
	}
	
	public Item getItemDropped(int meta, Random rand, int i) { return null; };
	
	public static final void addConstructRecipes(Item item)
	{
		GameRegistry.addSmelting(new ItemStack(item, 1, 1), new ItemStack(item, 1, 0), 0);
		GameRegistry.addSmelting(new ItemStack(item, 1, 0), new ItemStack(item, 1, 1), 0);
		
		GameRegistry.addRecipe(new ItemStack(item, 4, 2), new Object[] {"XX ", "XX ", 'X', new ItemStack(item, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(item, 4, 2), new Object[] {"XX ", "XX ", 'X', new ItemStack(item, 1, 1)});
		
		GameRegistry.addRecipe(new ItemStack(item.getItemFromBlock(GameRegistry.findBlock(MineFantasyII.MODID, item.getUnlocalizedName().substring(5) + "_stair"))), new Object[] {"X  ", "XX ", "XXX", 'X', new ItemStack(item, 1, 0)});
	}
	
	public void registerBlockIcons(IIconRegister register)
	{
		m_icons[0] = register.registerIcon(MineFantasyII.MODID + ":" + "basic/" + getUnlocalizedName().substring(5));
		m_icons[1] = register.registerIcon(MineFantasyII.MODID + ":" + "basic/" + getUnlocalizedName().substring(5) + "_cobblestone");
		m_icons[2] = register.registerIcon(MineFantasyII.MODID + ":" + "basic/" + getUnlocalizedName().substring(5) + "_brick");
		m_icons[3] = register.registerIcon(MineFantasyII.MODID + ":" + "basic/" + getUnlocalizedName().substring(5) + "_pavement");
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + m_names[itemstack.getItemDamage()];
	}
	
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		
		for(int i = 0; i < 4; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	public IIcon getIcon(int side, int meta)
	{
		switch(meta)
		{
		case 0:
			return m_icons[0];
			
		case 1:
			return m_icons[1];
			
		case 2:
			return m_icons[2];
			
		case 3:
			return m_icons[3];
			
		default:
			return blockIcon;
		}
	}
	
	public static class StairsConstBlock extends BlockStairs
	{

		public StairsConstBlock(String unlocalizedName, Block baseBlock, int metaOfBaseBlock)
		{
			super(baseBlock, metaOfBaseBlock);
			this.setBlockName(unlocalizedName);
		    this.setCreativeTab(CreativeTabs.tabBlock);
		    this.setLightOpacity(0);//They seem to render shadows funny
		}
		
		public StairsConstBlock(String unlocalizedName, Block baseBlock) 
		{
		    this(unlocalizedName, baseBlock, 0);
		}

		public Block register(String name) 
		{
			GameRegistry.registerBlock(this, name);
			return this;
		}
		
	}
	
	public static class ItemConstBlock extends ItemBlockWithMetadata
	{

		public ItemConstBlock(Block block) {
			super(block, block);
		}
		
		@Override
		public String getUnlocalizedName(ItemStack stack) {
		    switch (stack.getItemDamage()) {
		    case 1:
		        return this.getUnlocalizedName() + "_cobblestone";
		    case 2:
		        return this.getUnlocalizedName() + "_brick";
		    case 3:
		    	return this.getUnlocalizedName() + "_pavement";
		    default:
		        return this.getUnlocalizedName();
		    }
		}
		
	}
	
}
