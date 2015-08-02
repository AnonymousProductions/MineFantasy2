package minefantasy.mf2.block.refining;

import java.util.Random;

import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityBellows;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author Anonymous Productions
 * 
 * Sources are provided for educational reasons.
 * though small bits of code, or methods can be used in your own creations.
 */
public class BlockBellows extends BlockContainer implements ITileEntityProvider
{
    private Random rand = new Random();
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private final String NAME = "bellows";
    
    public BlockBellows()
    {
        super(Material.wood);
        GameRegistry.registerBlock(this, "MF_Bellows");
        setUnlocalizedName("minefantasy2:"+NAME);
		this.setHardness(1F);
		this.setResistance(0.5F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }
    
    public String getName()
	{
		return NAME;
	}
    
    @Override
    public boolean isOpaqueCube() 
    {
        return false;
    }
    @Override
    public boolean isFullCube() 
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
    	//return BlockListMF.bellows_RI;
    	return 2;
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos,IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
    	 EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
         state = state.withProperty(FACING, enumfacing);
         BlockPos blockpos1 = pos.north();
         BlockPos blockpos2 = pos.south();
         BlockPos blockpos3 = pos.west();
         BlockPos blockpos4 = pos.east();
         boolean flag = this == worldIn.getBlockState(blockpos1).getBlock();
         boolean flag1 = this == worldIn.getBlockState(blockpos2).getBlock();
         boolean flag2 = this == worldIn.getBlockState(blockpos3).getBlock();
         boolean flag3 = this == worldIn.getBlockState(blockpos4).getBlock();

         if (!flag && !flag1 && !flag2 && !flag3)
         {
             worldIn.setBlockState(pos, state, 3);
         }
         else if (enumfacing.getAxis() == EnumFacing.Axis.X && (flag || flag1))
         {
             if (flag)
             {
                 worldIn.setBlockState(blockpos1, state, 3);
             }
             else
             {
                 worldIn.setBlockState(blockpos2, state, 3);
             }

             worldIn.setBlockState(pos, state, 3);
         }
         else if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3))
         {
             if (flag2)
             {
                 worldIn.setBlockState(blockpos3, state, 3);
             }
             else
             {
                 worldIn.setBlockState(blockpos4, state, 3);
             }

             worldIn.setBlockState(pos, state, 3);
         }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float f, float f1, float f2)
    {
    	TileEntityBellows bellows = (TileEntityBellows)world.getTileEntity(pos);
    	if(bellows != null)
    	{
    		bellows.interact(player, 2F);
    	}
    	return true;
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityBellows();
	}
	
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
}
