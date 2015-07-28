package minefantasy.mf2.block.refining;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFC;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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

public class BlockBFC extends BlockContainer 
{
	private Random rand = new Random();
	
	private final String NAME = "blastfurnchamber";
	
	public BlockBFC() 
	{
		super(Material.anvil);
        GameRegistry.registerBlock(this, "MF_BlastChamber");
        setUnlocalizedName("minefantasy2:"+NAME);
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(8F);
		this.setResistance(10F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
	}
	
	public String getName()
	{
		return NAME;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityBlastFC();
	}
	
	private TileEntityBlastFC getTile(IBlockAccess world, BlockPos pos)
	{
		return (TileEntityBlastFC)world.getTileEntity(pos);
	}
	
	@Override
	public void onNeighborBlockChange(World world, BlockPos pos,IBlockState state, Block neighbour)
    {
		TileEntityBlastFC tile = getTile(world, pos);
		if(tile != null)tile.updateBuild();
    }
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
		TileEntityBlastFC tile = getTile(world, pos);

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

            world.func_147453_f(pos, state);
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
		TileEntityBlastFC tile = getTile(world, pos);
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
	public void registerBlockIcons(IIconRegister reg)
	{
		sideTex = reg.registerIcon("minefantasy2:processor/blast_chamber_side");
		bottomTex = reg.registerIcon("minefantasy2:processor/blast_chamber_top");
	}

}
