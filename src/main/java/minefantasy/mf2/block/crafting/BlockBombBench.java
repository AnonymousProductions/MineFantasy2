package minefantasy.mf2.block.crafting;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityBombBench;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBombBench extends BlockContainer
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	private static String NAME = "bombBench";
	public BlockBombBench()
    {
        super(Material.wood);
        //this.setBlockTextureName("minectaft:stone");
        GameRegistry.registerBlock(this, "MF_BombCrafter");
        setUnlocalizedName("minefantasy2:" + NAME);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(5F);
		this.setResistance(2F);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabMF.tabUtil);
    }
    
    public String getName()
    {
    	return NAME;
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
    public void onBlockPlacedBy(World world, BlockPos pos,IBlockState state, EntityLivingBase user, ItemStack item)
    {
        int direction = MathHelper.floor_double(user.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        this.getDefaultState().withProperty(FACING, user.getHorizontalFacing());
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer user, EnumFacing side, float xOffset, float yOffset, float zOffset)
    {
    	if(!ResearchLogic.hasInfoUnlocked(user, KnowledgeListMF.bombs))
        {
			if(world.isRemote)
		    	user.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("knowledge.unknownUse")));
			return false;
        }
    	TileEntityBombBench tile = getTile(world, pos);
    	if(tile != null && (world.isAirBlock(pos.add(0, 1, 0)) || !world.isSideSolid(pos.add(0, 1, 0), EnumFacing.DOWN)))
    	{
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
    	if(ResearchLogic.hasInfoUnlocked(user, KnowledgeListMF.bombs))
        {
        	TileEntityBombBench tile = getTile(world, pos);
        	if(tile != null)
        	{
        		tile.tryCraft(user);
        	}
        }
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityBombBench();
	}

	private TileEntityBombBench getTile(World world, BlockPos pos)
	{
		return (TileEntityBombBench)world.getTileEntity(pos);
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
		TileEntityBombBench tile = getTile(world, pos);

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
                        EntityItem entityitem = new EntityItem(world, pos.getX() + f,pos.getY() + f1, pos.getZ() + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

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

            world.func_147453_f(pos, state.getBlock());
        }

        super.breakBlock(world, pos, state);
    }
	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public IIcon getIcon(int side, int meta)
//	{
//		if(side == 1)
//		{
//			return Blocks.crafting_table.getIcon(1, meta);
//		}
//		return Blocks.anvil.getIcon(0, 0);
//	}
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
    
	@Override
	public int getRenderType()
	{
		return BlockListMF.bomb_RI;
	}
	private Random rand = new Random();
}