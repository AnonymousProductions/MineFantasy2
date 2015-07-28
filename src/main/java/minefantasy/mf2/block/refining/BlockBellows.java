package minefantasy.mf2.block.refining;

import java.util.Random;

import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityBellows;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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
public class BlockBellows extends BlockContainer 
{
    private Random rand = new Random();

    private final String NAME = "bellows";
    
    public BlockBellows()
    {
        super(Material.wood);
        GameRegistry.registerBlock(this, "MF_Bellows");
        setUnlocalizedName("minefantasy2:"+NAME);
		this.setHardness(1F);
		this.setResistance(0.5F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
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
    @Override
    public IIcon getIcon(int side, int meta)
    {
    	return Blocks.planks.getIcon(side, 0);
    }
    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
    	return BlockListMF.bellows_RI;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos,IBlockState state, EntityLivingBase user, ItemStack item)
    {
        int direction = MathHelper.floor_double((double)(user.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        world.setBlockState(pos, getStateFromMeta(direction), 2);
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
}
