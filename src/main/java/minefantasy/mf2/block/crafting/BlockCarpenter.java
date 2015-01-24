package minefantasy.mf2.block.crafting;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.block.tileentity.TileEntityCarpenterMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCarpenter extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    public int CarpenterRenderSide;
    private int tier = 0;

    public BlockCarpenter()
    {
        super(Material.wood);
        
        setBlockBounds(0F, 0F, 0F, 1F, 1F-(2/16F), 1F);
        this.setBlockTextureName("minectaft:oak_planks");
        GameRegistry.registerBlock(this, "MF_CarpenterBench");
		setBlockName("carpenterBench");
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(5F);
		this.setResistance(2F);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabMF.tabUtil);
    }
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase user, ItemStack item)
    {
        int direction = MathHelper.floor_double((double)(user.rotationYaw * 4.0F / 360.0F) + 0.5D);

        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset, float yOffset, float zOffset)
    {
        {
        	TileEntityCarpenterMF tile = getTile(world, x, y, z);
        	if(tile != null && (world.isAirBlock(x, y+1, z) || !world.isSideSolid(x, y+1, z, ForgeDirection.DOWN)))
        	{
        		if(side != 1 || !tile.tryCraft(user) && !world.isRemote)
        		{
        			user.openGui(MineFantasyII.instance, 0, world, x, y, z);
        		}
        	}
            return true;
        }
    }
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer user)
    {
        {
        	TileEntityCarpenterMF tile = getTile(world, x, y, z);
        	if(tile != null)
        	{
        		tile.tryCraft(user);
        	}
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
    	int xdim = 8;
    	int ydim = 8;
    	int zdim = 14;
    	
    	float oX = 0.5F - (xdim / 16F);
    	float oY = 0.5F - (ydim / 16F);
        int direction = world.getBlockMetadata(x, y, z);

        if (direction != 2 && direction != 0)
        {
            this.setBlockBounds(oY, 0.0F, oX, 1F-oY, zdim/16F, 1F-oX);
        }
        else
        {
            this.setBlockBounds(oX, 0.0F, oY, 1F-oX, zdim/16F, 1F-oY);
        }
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityCarpenterMF(tier);
	}

	private TileEntityCarpenterMF getTile(World world, int x, int y, int z)
	{
		return (TileEntityCarpenterMF)world.getTileEntity(x, y, z);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
		TileEntityCarpenterMF tile = getTile(world, x, y, z);

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
                        EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, meta);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return Blocks.crafting_table.getIcon(side, meta);
	}
	private Random rand = new Random();
}