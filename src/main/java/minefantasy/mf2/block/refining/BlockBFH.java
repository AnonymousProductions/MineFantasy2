package minefantasy.mf2.block.refining;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBFH extends BlockContainer 
{
	private static boolean keepInventory;
	private Random rand = new Random();

	public boolean isActive;
	private static String NAME = "blastfurnheater";
	
	public BlockBFH(boolean isActive)
	{
		super(Material.anvil);
		this.isActive = isActive;
        GameRegistry.registerBlock(this, isActive ? "MF_BlastHeaterActive" : "MF_BlastHeater");
        setUnlocalizedName("minefantasy2:"+NAME);
        this.setDefaultState(this.blockState.getBaseState());
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(10F);
		this.setResistance(10F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
	}
	
	public String getName()
	{
		return NAME;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos,IBlockState state, Random rand)
    {
        if (isActive && rand.nextInt(20) == 0)
        {
            world.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), "fire.fire", 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
        }
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
		return new TileEntityBlastFH();
	}
	
	private TileEntityBlastFH getTile(IBlockAccess world, BlockPos pos)
	{
		return (TileEntityBlastFH)world.getTileEntity(pos);
	}
	
	@Override
	public void onNeighborBlockChange(World world, BlockPos pos,IBlockState state, Block neighbour)
    {
		TileEntityBlastFH tile = getTile(world, pos);
		if(tile != null)tile.updateBuild();
    }
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
		if(keepInventory)return;
		
		TileEntityBlastFH tile = getTile(world, pos);

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

            world.func_147453_f(pos, state.getBlock());
        }

        super.breakBlock(world, pos, state);
    }
	
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer user, EnumFacing side, float xOffset, float yOffset, float zOffset)
    {
		if(!ResearchLogic.hasInfoUnlocked(user, KnowledgeListMF.blastfurn))
        {
			if(world.isRemote)
		    	user.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("knowledge.unknownUse")));
			return false;
        }
		TileEntityBlastFH tile = getTile(world, pos);
    	if(tile != null)
    	{
    		if(!world.isRemote)
    		{
    			user.openGui(MineFantasyII.instance, 0, world, pos.getX(),pos.getY(),pos.getZ());
    		}
    	}
        return true;
    }
	
	public static void updateFurnaceBlockState(boolean b, World world, BlockPos pos,IBlockState state)
    {
        int l = world.getBlockState(pos).getBlock().getMetaFromState(state);
        TileEntity tileentity = world.getTileEntity(pos);
        keepInventory = true;

        if (b)
        {
            world.setBlockState(pos, BlockListMF.blast_heater_active.getDefaultState());
        }
        else
        {
            world.setBlockState(pos, BlockListMF.blast_heater.getDefaultState());
        }

        keepInventory = false;
        world.setBlockState(pos, state.getBlock().getStateFromMeta(l), 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
        }
    }
	
		//sideTex = isActive ? reg.registerIcon("minefantasy2:processor/blast_heater_active") : reg.registerIcon("minefantasy2:processor/blast_heater");
		//bottomTex = reg.registerIcon("minefantasy2:processor/blast_chamber_top");

	
	@Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World world, BlockPos pos)
    {
        return Item.getItemFromBlock(BlockListMF.blast_heater);
    }

}
