package minefantasy.mf2.block.basic;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ConstructionBlockMF extends Block {

	public static final String[] m_names = new String[] {"", "_cobblestone", "_brick", "_pavement"};
	public Block[] stairblocks = new Block[4];
	public ConstructionBlockMF(String unlocName)
	{
		super(Material.rock);
		
		this.setUnlocalizedName(unlocName);
		
		this.setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock((Block)this, unlocName);
		// Is ItemConstBlock.class needed?
		//GameRegistry.registerBlock((Block)this, ItemConstBlock.class, unlocName);
		for(int i = 0; i < 4; i ++)
		{
			GameRegistry.registerBlock(stairblocks[i] = new StairsConstBlock(unlocName + m_names[i] + "_stair", getStateFromMeta(i)).setHardness(1.5F).setResistance(10F),  unlocName + m_names[i] + "_stair");
		}
		
		setHardness(1.5F);
		setResistance(10F);
		setHarvestLevel("pickaxe", 0);
		addConstructRecipes();
		
	}
	
	
	@Override
	public Block setHardness(float level)
    {
		if(stairblocks != null)
		{
			for(Block stairblock : stairblocks)
			stairblock.setHardness(level);
		}
        return super.setHardness(level);
    }
	@Override
	public Block setResistance(float level)
    {
		if(stairblocks != null)
		{
			for(Block stairblock : stairblocks)
			stairblock.setResistance(level);
		}
        return super.setResistance(level);
    }
	
	//public Item getItemDropped(int meta, Random rand, int i) { return null; };
	
	public void addConstructRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(this, 1, 1), new ItemStack(this, 1, 0), 0);
		
		GameRegistry.addRecipe(new ItemStack(this, 4, 3), new Object[] 
		{
			"XX", 
			"XX",
			'X', new ItemStack(this, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(this, 4, 2), new Object[] 
		{
			"X X",
			"   ",
			"X X",
			'X', new ItemStack(this, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(this, 1, 1), new Object[] 
		{
			"X", 
			'X', new ItemStack(this, 1, 2)
		});
		GameRegistry.addRecipe(new ItemStack(this, 1, 1), new Object[] 
		{
			"X", 
			'X', new ItemStack(this, 1, 3)
		});
		for(int i = 0; i < 4; i++)
		GameRegistry.addRecipe(new ItemStack(stairblocks[i], 4), new Object[] 
		{
			"  X",
			" XX",
			"XXX",
			'X', new ItemStack(this, 1, i)
		});
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		if(getMetaFromState(state) == 0)
			return 1;
		
		return getMetaFromState(state);
	}
	
//	@Override
//	public void registerBlockIcons(IIconRegister register)
//	{
//		m_icons[0] = register.registerIcon(MineFantasyII.MODID + ":" + "basic/" + getUnlocalizedName().substring(5));
//		m_icons[1] = register.registerIcon(MineFantasyII.MODID + ":" + "basic/" + getUnlocalizedName().substring(5) + "_cobblestone");
//		m_icons[2] = register.registerIcon(MineFantasyII.MODID + ":" + "basic/" + getUnlocalizedName().substring(5) + "_brick");
//		m_icons[3] = register.registerIcon(MineFantasyII.MODID + ":" + "basic/" + getUnlocalizedName().substring(5) + "_pavement");
//	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + m_names[itemstack.getItemDamage()];
	}
	
	public String getName(ItemStack itemstack)
	{
		return getUnlocalizedName(itemstack).substring(5);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		
		for(int i = 0; i < 4; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	

	public static class StairsConstBlock extends BlockStairs
	{

		 public static String Name;
		public StairsConstBlock(String unlocalizedName, IBlockState modelState)
		{
			//IBlockState modelState
			super(modelState);
			this.setUnlocalizedName(unlocalizedName);
		    this.setCreativeTab(CreativeTabs.tabBlock);
		    this.setLightOpacity(0);//They seem to render shadows funny
		    Name= unlocalizedName;
		}
		
		public StairsConstBlock(String unlocalizedName, Block baseBlock) 
		{
		    this(unlocalizedName, baseBlock.getStateFromMeta(0));
		}

		public Block register(String name) 
		{
			GameRegistry.registerBlock(this, name);
			return this;
		}
		
	}
											// ItemBlockWithMetadata
	public static class ItemConstBlock extends ItemBlock
	{

		public ItemConstBlock(Block block) {
			super(block);
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
		
		
		@Override
		public int getMetadata(int d)
		{
			return d;
		}
	}
	
}
