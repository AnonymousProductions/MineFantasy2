package minefantasy.mf2.block.crafting;

import java.util.Random;

import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityTanningRack;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTanningRack extends BlockContainer
{
	public int tier;
	public String tex;
	
	private static String NAME = "tanner";
	
	public BlockTanningRack(int tier, String tex) 
	{
		super(Material.wood);
		
		this.tier=tier;
		this.tex=tex;
		String name = NAME+tex;
		NAME = name;
		setUnlocalizedName("minefantasy2:" +name);
		GameRegistry.registerBlock(this, name);
		this.setHardness(1F + 0.5F*tier);
		this.setResistance(1F);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabMF.tabUtil);
	}
	
	public String getName()
	{
		return NAME;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityTanningRack(tier, tex);
	}
	
	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase user, ItemStack stack)
    {
    	int dir = MathHelper.floor_double(user.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        world.setBlockState(pos, getStateFromMeta(dir), 2);
    }
	
	public TileEntityTanningRack getTile(World  world, BlockPos pos)
	{
		return (TileEntityTanningRack)world.getTileEntity(pos);
	}

	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer user, EnumFacing side, float xOffset, float yOffset, float zOffset)
    {
        {
        	TileEntityTanningRack tile = getTile(world, pos);
        	if(tile != null)
        	{
        		return tile.interact(user, false);
        	}
            return true;
        }
    }
    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer user)
    {
        {
        	TileEntityTanningRack tile = getTile(world, pos);
        	if(tile != null)
        	{
        		tile.interact(user, true);
        	}
        }
    }
    
    public Random rand = new Random();
    @Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
    	TileEntityTanningRack tile = getTile(world,pos);

        if (tile != null)
        {
            ItemStack itemstack = tile.items[0];

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
                    EntityItem entityitem = new EntityItem(world, pos.getX()+f, pos.getY()+f1, pos.getZ()+f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

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

            world.func_147453_f(pos, state.getBlock());
        }

        super.breakBlock(world, pos,state);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	return Blocks.planks.getIcon(side,  0);
    }
    
    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
	public int getRenderType()
	{
		return BlockListMF.tanner_RI;
	}
}
