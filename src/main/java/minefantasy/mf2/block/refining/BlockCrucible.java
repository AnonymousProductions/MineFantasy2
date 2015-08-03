package minefantasy.mf2.block.refining;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityCrucible;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrucible extends BlockContainer implements ITileEntityProvider
{
	private Random rand = new Random();
	public final boolean isActive;
	private static boolean keepInventory;
	public int tier;
	public String type;
	public String Name;
	
	public BlockCrucible(String tex, int tier, boolean isActive) 
	{
		super(Material.rock);
		this.tier = tier;
		this.type = tex;
		Name = tex;
		this.isActive = isActive;
        GameRegistry.registerBlock(this, "MF_Crucible"+tex + (isActive ? "Active" : ""));
        setUnlocalizedName("crucible."+tex);
        this.setDefaultState(this.blockState.getBaseState());
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(8F);
		this.setResistance(8F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
	}

	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
		if(!isActive)
		{
			super.getSubBlocks(item, tab, list);
		}
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityCrucible();
	}
	
	private static TileEntityCrucible getTile(IBlockAccess world, BlockPos pos)
	{
		return (TileEntityCrucible)world.getTileEntity(pos);
	}
	
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
		if(keepInventory)return;
		
		TileEntityCrucible tile = getTile(world, pos);

        if (tile != null)
        {
            for (int i1 = 0; i1 < tile.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = tile.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = this.rand .nextFloat() * 0.8F + 0.1F;
                    float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int j1 = this.rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(world, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (float)this.rand.nextGaussian() * f3;
                        entityitem.motionY = (float)this.rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }

            world.notifyNeighborsOfStateChange(pos, state.getBlock());//unsure of conversion
            //.func_147453_f(pos, state.getBlock());
        }

        super.breakBlock(world,pos, state);
    }
	
	public static void updateFurnaceBlockState(boolean state, World world, BlockPos pos)
    {
        int l = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
        TileEntityCrucible tileentity = getTile(world, pos);
        keepInventory = true;
        Block block = world.getBlockState(pos).getBlock();
        
        if(block != null && block instanceof BlockCrucible)
        {
        	int blocktier = ((BlockCrucible)block).tier;
	        if (state)
	        {
	            world.setBlockState(pos, getActiveBlock(blocktier).getDefaultState());
	        }
	        else
	        {
	            world.setBlockState(pos, getInactiveBlock(blocktier).getDefaultState());
	        }
        }

        keepInventory = false;
        world.setBlockState(pos, world.getBlockState(pos).getBlock().getStateFromMeta(l), 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
        }
    }
	
	private static Block getActiveBlock(int tier)
	{
		if(tier == 1)
		{
			return BlockListMF.crucibleadv_active;
		}
		return BlockListMF.crucible_active;
	}

	private static Block getInactiveBlock(int tier) 
	{
		if(tier == 1)
		{
			return BlockListMF.crucibleadv;
		}
		return BlockListMF.crucible;
	}
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer user, EnumFacing side, float xOffset, float yOffset, float zOffset)
    {
		TileEntityCrucible tile = getTile(world, pos);
    	if(tile != null)
    	{
    		if(!world.isRemote)
    		{
    			user.openGui(MineFantasyII.instance, 0, world, pos.getX(),pos.getY(),pos.getZ());
    		}
    	}
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos)
    {
        return Item.getItemFromBlock(getInactiveBlock(tier));
    }
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fort)
    {
        return Item.getItemFromBlock(getInactiveBlock(tier));
    }
}
