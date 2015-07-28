package minefantasy.mf2.block.food;

import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.item.food.ItemFoodMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCakeMF extends Block
{    
    private Item cakeSlice;
    private static int maxSlices = 8;
    
    public static final PropertyInteger SLICES = PropertyInteger.create("slices", 0, maxSlices);
    protected float height = 0.5F;

    private String NAME;
    
    public BlockCakeMF(String name, Item slice)
    {
        super(Material.cake);
        GameRegistry.registerBlock(this, ItemBlockCake.class, name);
        setUnlocalizedName("minefantasy2:food/"+name);
		//setBlockTextureName("minefantasy2:food/"+name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SLICES, Integer.valueOf(0)));
        this.setTickRandomly(true);
        setCreativeTab(CreativeTabMF.tabFood);
        cakeSlice = slice;
        NAME = name;
    }
    
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        int slices = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
        float border = 0.0625F;
        float size = (slices / (float)maxSlices) * (1.0F-(border*2));
        this.setBlockBounds(border + size, 0.0F, border, 1.0F-border, height, 1.0F - border);
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Override
    public void setBlockBoundsForItemRender()
    {
        float f = 0.0625F;
        float f1 = height;
        this.setBlockBounds(f, 0.0F, f, 1.0F - f, f1, 1.0F - f);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
    {
    	float border = 0.0625F;
        float slices = (float)(1 + ((Integer)state.getValue(SLICES)).intValue() * 2) / 16.0F;
        float size = 0.5F;
        return new AxisAlignedBB((double)((float)pos.getX() + slices), (double)pos.getY(), (double)((float)pos.getZ() + border), (double)((float)(pos.getX() + 1) - border), (double)((float)pos.getY() + size), (double)((float)(pos.getZ() + 1) - border));
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos)
    {
    	return this.getCollisionBoundingBox(world, pos, world.getBlockState(pos));
    }
    
    @Override
    public boolean isFullCube()
    {
        return false;
    }
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }
    
    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer user, EnumFacing side, float xOffset, float yOffset, float zOffset)
    {
    	if(ToolHelper.getCrafterTool(user.getHeldItem()).equalsIgnoreCase("knife"))
    	{
    		this.cutSlice(world, pos,state, user);
    		return true;
    	}
    	return false;
    }

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer user)
    {
    	if(ToolHelper.getCrafterTool(user.getHeldItem()).equalsIgnoreCase("knife"))
    	{
    		this.cutSlice(world, pos,world.getBlockState(pos), user);
    	}
    }

    private void cutSlice(World world, BlockPos pos,IBlockState state, EntityPlayer user)
    {
        if(cakeSlice != null)
        {
        	ItemStack slice = new ItemStack(cakeSlice);
        	if(!user.inventory.addItemStackToInventory(slice))
        	{
        		user.entityDropItem(slice, 1.0F);
        	}
        }
        
        int l = ((Integer)state.getValue(SLICES)).intValue() + 1;

        if (l >= maxSlices)
        {
            world.setBlockToAir(pos);
        }
        else
        {
            world.setBlockState(pos, state.withProperty(SLICES,Integer.valueOf(l)), 3);
        }
        
        if(user.getHeldItem() != null)
        {
        	user.getHeldItem().damageItem(1, user);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return !super.canPlaceBlockAt(world, pos) ? false : this.canBlockStay(world, pos);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    @Override
    public void onNeighborBlockChange(World world, BlockPos pos,IBlockState state, Block block)
    {
        if (!this.canBlockStay(world, pos))
        {
        	ItemStack item = new ItemStack(this, 1, damageDropped(state));
        	EntityItem drop = new EntityItem(world, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D, item);
        	world.spawnEntityInWorld(drop);
            world.setBlockToAir(pos);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    private boolean canBlockStay(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()).getBlock().getMaterial().isSolid();
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(SLICES, Integer.valueOf(meta));
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(SLICES)).intValue();
    }
    
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {SLICES});
    }
    
    public int getComparatorInputOverride(World worldIn, BlockPos pos)
    {
        return (7 - ((Integer)worldIn.getBlockState(pos).getValue(SLICES)).intValue()) * 2;
    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }
    
    
    
//    @SideOnly(Side.CLIENT)
//    @Override
//    public Item getItem(World worldIn, BlockPos pos)
//    {
//        return FoodListMF.cake;
//    }

    @Override
    public int damageDropped(IBlockState state)
    {
    	return getMetaFromState(state);
    }

	public int getRarity()
	{
		if(cakeSlice instanceof ItemFoodMF)
		{
			return ((ItemFoodMF)cakeSlice).itemRarity;
		}
		return 0;
	}
	
    
    public String getName()
	{
		return NAME;
	}


    
}