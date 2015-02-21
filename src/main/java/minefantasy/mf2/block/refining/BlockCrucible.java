package minefantasy.mf2.block.refining;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityCrucible;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCrucible extends BlockContainer 
{
	private Random rand = new Random();
	public final boolean isActive;
	private static boolean keepInventory;
	
	public BlockCrucible(boolean isActive) 
	{
		super(Material.rock);
		this.isActive = isActive;
        GameRegistry.registerBlock(this, "MF_Crucible" + (isActive ? "Active" : ""));
		setBlockName("crucible");
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
	
	private TileEntityCrucible getTile(IBlockAccess world, int x, int y, int z)
	{
		return (TileEntityCrucible)world.getTileEntity(x, y, z);
	}
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
		if(keepInventory)return;
		
		TileEntityCrucible tile = getTile(world, x, y, z);

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
                        EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

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

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, meta);
    }
	
	public static void updateFurnaceBlockState(boolean state, World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        keepInventory = true;

        if (state)
        {
            world.setBlock(x, y, z, BlockListMF.crucible_active);
        }
        else
        {
            world.setBlock(x, y, z, BlockListMF.crucible);
        }

        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? topTex : sideTex;
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset, float yOffset, float zOffset)
    {
		TileEntityCrucible tile = getTile(world, x, y, z);
    	if(tile != null)
    	{
    		if(!world.isRemote)
    		{
    			user.openGui(MineFantasyII.instance, 0, world, x, y, z);
    		}
    	}
        return true;
    }
	private IIcon sideTex, topTex;
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		topTex = isActive ? reg.registerIcon("minefantasy2:basic/crucible_top_active") : reg.registerIcon("minefantasy2:basic/crucible_top");
		sideTex = reg.registerIcon("minefantasy2:basic/crucible_side");
	}
	@Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(BlockListMF.crucible);
    }
}
