package minefantasy.mf2.block.refining;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityChimney;
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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockChimney extends BlockContainer 
{
	private Random rand = new Random();
	public IIcon bottomTex;
	public IIcon sideTex;
	public int size;
	private boolean isWide;
	/**
	 * Weather it can absorb smoke indirectly (not directly above a source)
	 */
	public boolean isIndirect;
	private String chimneyType;
	public BlockChimney(String type, boolean wide, boolean indirect, int size) 
	{
		super(Material.rock);
		this.isIndirect = indirect;
		isWide = wide;
		this.chimneyType = type;
		this.size = size;
		if(wide)
		{
			this.setLightOpacity(255);
		}
		else
		{
			this.setBlockBounds(1F/4F, 0, 1F/4F, 3F/4F, 1F, 3F/4F);
		}
        GameRegistry.registerBlock(this, "MF_Chimney_" + type + (isWide ? "_Wide" : "_Thin"));
		setBlockName("chimney."+type + (isWide ? ".wide" : ""));
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(5F);
		this.setResistance(10F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityChimney();
	}
	
	private TileEntityChimney getTile(IBlockAccess world, int x, int y, int z)
	{
		return (TileEntityChimney)world.getTileEntity(x, y, z);
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return isWide;
	}
	@Override
	public boolean renderAsNormalBlock()
	{
		return isWide;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if(side == 1 || side == 0)
		{
			return bottomTex;
		}
		return sideTex;
	}
	
	/*
	 * 
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
		TileEntityChimney chimney = (TileEntityChimney)world.getTileEntity(x, y, z);
		if(chimney != null && chimney.maskBlock != null && chimney.maskBlock != Blocks.air)
		{
			return chimney.maskBlock.getIcon(side, chimney.maskMeta);
		}
		return getIcon(side, world.getBlockMetadata(x, y, z));
    }
    
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset, float yOffset, float zOffset)
    {
		int m = world.getBlockMetadata(x, y, z);
		TileEntityChimney chimney = getTile(world, x, y, z);
		
		if(chimney != null)
		{
			ItemStack held = user.getHeldItem();
			if(!world.isRemote && held != null && held.getItem() instanceof ItemBlock)
			{
				ItemBlock item = (ItemBlock)held.getItem();
				if(item.field_150939_a.isNormalCube())
				{
					chimney.maskBlock = item.field_150939_a;
					chimney.maskMeta = item.getMetadata(held.getItemDamage());
					chimney.sync();
					world.setBlock(x, y, z, BlockListMF.chimney_wide, m, 2);
					return true;
				}
			}
		}
        return false;
    }
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
		if(!isWide)
		{
			super.getSubBlocks(item, tab, list);
		}
    }
	*/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		sideTex = reg.registerIcon("minefantasy2:chimney/chimney_"+chimneyType+"_side" + (isWide ? "Wide" : ""));
		bottomTex = reg.registerIcon("minefantasy2:chimney/chimney_"+chimneyType+"_top" + (isWide ? "Wide" : ""));
	}

}
