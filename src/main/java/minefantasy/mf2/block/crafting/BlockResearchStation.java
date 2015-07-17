package minefantasy.mf2.block.crafting;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityResearch;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockResearchStation extends BlockContainer
{
    private int tier = 0;

    public BlockResearchStation()
    {
        super(Material.wood);
        
        GameRegistry.registerBlock(this, "MF_Research");
		setBlockName("researchStation");
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
        int direction = MathHelper.floor_double(user.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset, float yOffset, float zOffset)
    {
    	TileEntityResearch tile = getTile(world, x, y, z);
    	if(tile != null && (world.isAirBlock(x, y+1, z) || !world.isSideSolid(x, y+1, z, ForgeDirection.DOWN)))
    	{
    		if(!(side == 1 && tile.interact(user)))
    		{
    			user.openGui(MineFantasyII.instance, 0, world, x, y, z);
    		}
    	}
        return true;
    }
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer user)
    {
    	TileEntityResearch tile = getTile(world, x, y, z);
    	if(tile != null && (world.isAirBlock(x, y+1, z) || !world.isSideSolid(x, y+1, z, ForgeDirection.DOWN)))
    	{
			tile.interact(user);
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityResearch();
	}

	private TileEntityResearch getTile(World world, int x, int y, int z)
	{
		return (TileEntityResearch)world.getTileEntity(x, y, z);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        super.breakBlock(world, x, y, z, block, meta);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return Blocks.crafting_table.getIcon(side, meta);
	}
	@Override
	public int getRenderType()
	{
		return BlockListMF.research_RI;
	}
	private Random rand = new Random();
}