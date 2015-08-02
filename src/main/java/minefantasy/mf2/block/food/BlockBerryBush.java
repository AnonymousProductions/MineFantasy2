package minefantasy.mf2.block.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minefantasy.mf2.item.food.FoodListMF;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBerryBush extends BlockBush implements IShearable
{
    private Random rand = new Random();
	int[] field_150128_a;
    @SideOnly(Side.CLIENT)
    protected int field_150127_b;
    
    public static final PropertyInteger GROWTH = PropertyInteger.create("growth", 0, 1);
    public String Name;
    
    public BlockBerryBush(String name)
    {
        super(Material.leaves);
        GameRegistry.registerBlock(this, ItemBlockBerries.class, name);
        setUnlocalizedName("minefantasy2:" + name);
		Name=name;
		this.setDefaultState(this.blockState.getBaseState().withProperty(GROWTH, Integer.valueOf(0)));
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.3F);
        float offset = 1/16F;
        this.setBlockBounds(offset, 0F, offset, 1.0F-offset, 1.0F-(offset*2), 1.0F-offset);
        this.setLightOpacity(1);
        this.setStepSound(soundTypeGrass);
    }
    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
    	int meta = getMetaFromState(state);
    	if(meta >= 1 && rand.nextInt(120) == 0)
    	{
    		world.setBlockState(pos, getStateFromMeta(0), 2);
    	}
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(GROWTH)).intValue();
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(GROWTH, Integer.valueOf(meta));
    }
    
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {GROWTH});
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
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world,BlockPos pos, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, getMetaFromState(world.getBlockState(pos)) & 3));
        return ret;
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer user, EnumFacing side, float xOffset, float yOffset, float zOffset)
    {
    	int meta = getMetaFromState(state);
    	if(meta == 0 )
    	{
    		if(world.isRemote)return true;
    		
    		
    		world.setBlockState(pos, getStateFromMeta(1), 2);
    		
    		ItemStack itemstack = rand.nextInt(10) == 0 ? new ItemStack(FoodListMF.berriesJuicy) : new ItemStack(FoodListMF.berries, 1);
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
    		
    		return true;
    	}
    	return false;
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
    	return new ArrayList<ItemStack>(){};
    }
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
    	return true;
    }
    @Override
    public int getRenderType()
    {
        return 0;
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
    	return 0;
    }

}