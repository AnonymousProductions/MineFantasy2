package minefantasy.mf2.block.basic;

import java.util.Random;

import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockOreMF extends Block 
{
	public int rarity;
	private int xp;
	private Item drop;
	private int dropMin;
	private int dropMax;
	public String Name;
	
	public BlockOreMF(String name, int harvestLevel)
	{
		this(name, harvestLevel, 0);
	}
	public BlockOreMF(String name, int harvestLevel, int rarity)
	{
		this(name, harvestLevel, rarity, null, 1, 1, 0);
	}
	public BlockOreMF(String name, int harvestLevel, int rarity, Item drop, int min, int max, int xp)
	{
		this(name, harvestLevel, rarity, drop, min, max, xp, Material.rock);
	}
	public BlockOreMF(String name, int harvestLevel, int rarity, Item drop, int min, int max, int xp, Material material)
	{
		super(material);
		this.xp = xp;
		this.drop = drop;
		this.rarity = rarity;
		this.dropMin=min;
		this.dropMax=max;
		Name=name;
		this.setStepSound(Block.soundTypePiston);
		GameRegistry.registerBlock(this, ItemOreblockMF.class, name);
		setUnlocalizedName("minefantasy2:ores/"+name);
		this.setDefaultState(this.blockState.getBaseState());
		//setBlockTextureName("minefantasy2:ores/"+name);
		
		if(material == Material.rock)
		{
			this.setHarvestLevel("pickaxe", harvestLevel);
		}
		this.setCreativeTab(CreativeTabMF.tabOres);
	}
	
	@Override
	//getItemDropped(IBlockState state, Random rand, int fortune)
	public Item getItemDropped(IBlockState state, Random rand, int i)
    {
		return drop != null ? drop : Item.getItemFromBlock(this);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
	@Override
    public int quantityDropped(Random rand)
    {
        return MathHelper.getRandomIntegerInRange(rand, dropMin, dropMax);
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand)
    {
    	
    	if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getDefaultState(), rand, fortune))
        {
            int j = rand.nextInt(fortune + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }

            return this.quantityDropped(rand) * (j + 1);
        }
        else
        {
            return this.quantityDropped(rand);
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
    public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int i)
    {
        super.dropBlockAsItemWithChance(world, pos, state, f, i);
    }

    private Random rand = new Random();
    
    @Override
    public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
    {
        if (xp > 0 && this.getItemDropped(this.getDefaultState(), rand, fortune) != Item.getItemFromBlock(this))
        {
        	return MathHelper.getRandomIntegerInRange(rand, xp, xp*2);
        }
        return 0;
    }
}
