package minefantasy.mf2.block.crafting;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.heating.TongsHelper;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.tool.crafting.ItemTongs;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
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

public class BlockAnvilMF extends BlockContainer implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    public int anvilRenderSide;
    public BaseMaterialMF material;
    private int tier;
    public String Name;
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    
    public BlockAnvilMF(BaseMaterialMF material)
    {
        super(Material.anvil);
        String name = material.name;
        this.material = material;
        float height = 1.0F / 16F * 13F;
        setBlockBounds(0F, 0F, 0F, 1F, height, 1F);
        
        //this.setBlockTextureName("minefantasy2:metal/"+name.toLowerCase()+"_block");
        name = "anvil"+name;
        Name=name;
        this.tier = material.tier;
        GameRegistry.registerBlock(this, ItemBlockAnvilMF.class, name);
        		//"minefantasy2:metal/" +"_block"
        setUnlocalizedName(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(material.hardness+1 / 2F);
		this.setResistance(material.hardness+1);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabMF.tabUtil);
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

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase user, ItemStack item)
    {
    	super.onBlockPlacedBy(world, pos, state, user,item);
    	int dir = user.getHorizontalFacing().getHorizontalIndex();

        world.setBlockState(pos, getStateFromMeta(dir), 2);
        
 
        
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer user, EnumFacing side, float xOffset, float yOffset, float zOffset)
    {
    	ItemStack held = user.getHeldItem();
    	TileEntityAnvilMF tile = getTile(world, pos);
    	if(tile != null)
    	{
    		if(side == EnumFacing.NORTH && held != null && held.getItem() instanceof ItemTongs)
    		{
    			ItemStack result = tile.getStackInSlot(tile.getSizeInventory()-1);
    			if(result != null && result.getItem() == ComponentListMF.hotItem)
    			{
    				if(TongsHelper.getHeldItem(held) == null && TongsHelper.trySetHeldItem(held, result))
    				{
    					tile.setInventorySlotContents(tile.getSizeInventory()-1, null);
    					return true;
    				}
    			}
    		}
    		if(side != EnumFacing.NORTH || !tile.tryCraft(user) && !world.isRemote)
    		{
    			user.openGui(MineFantasyII.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
    		}
    	}
        return true;
    }
    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer user)
    {
        {
        	TileEntityAnvilMF tile = getTile(world, pos);
        	if(tile != null)
        	{
        		tile.tryCraft(user);
        	}
        }
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityAnvilMF(tier, material.name);
	}

	private TileEntityAnvilMF getTile(World world, BlockPos pos)
	{
		return (TileEntityAnvilMF)world.getTileEntity(pos);
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
		TileEntityAnvilMF tile = getTile(world, pos);

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
            }
            world.notifyNeighborsOfStateChange(pos, state.getBlock());//unsure of conversion
            //.func_147453_f(pos, state.getBlock());
        }

        super.breakBlock(world, pos,state);
    }
	public int getTier()
	{
		return tier;
	}
	
	@Override
	public int getRenderType()
	{
		//return BlockListMF.bomb_RI;  //102 is not a valid value??
		return 2; //doesn’t render anything in the block layers,
				//but has an associated TileEntitySpecialRenderer 
				//which does draw something, eg BlockChest.
		//return 3;
	}
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
    	return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
	private Random rand = new Random();
}