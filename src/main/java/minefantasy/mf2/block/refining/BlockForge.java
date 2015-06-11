package minefantasy.mf2.block.refining;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.heating.ForgeFuel;
import minefantasy.mf2.api.heating.ForgeItemHandler;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.api.tool.ILighter;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityForge;
import minefantasy.mf2.item.list.ComponentListMF;
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
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockForge extends BlockContainer 
{
	private Random rand = new Random();
	public final boolean isActive;
	private static boolean keepInventory;
	public int tier;
	public String type;
	
	public BlockForge(String tex, int tier, boolean isActive) 
	{
		super(Material.rock);
		this.tier = tier;
		this.type = tex;
		this.isActive = isActive;
		//setBlockBounds(0F, 0F, 0F, 1F, 12F/16F, 1F);
        GameRegistry.registerBlock(this, "MF_Forge"+tex + (isActive ? "Active" : ""));
		setBlockName("forge."+tex);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(5F);
		this.setResistance(8F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
        setBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);
        this.setLightOpacity(0);
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
		return new TileEntityForge();
	}
	
	private static TileEntityForge getTile(IBlockAccess world, int x, int y, int z)
	{
		return (TileEntityForge)world.getTileEntity(x, y, z);
	}
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
		if(keepInventory)return;
		
		TileEntityForge tile = getTile(world, x, y, z);

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
        TileEntityForge tileentity = getTile(world, x, y, z);
        keepInventory = true;
        Block block = world.getBlock(x, y, z);
        
        if(block != null && block instanceof BlockForge)
        {
        	int blocktier = ((BlockForge)block).tier;
	        if (state)
	        {
	            world.setBlock(x, y, z, getActiveBlock(blocktier));
	        }
	        else
	        {
	            world.setBlock(x, y, z, getInactiveBlock(blocktier));
	        }
        }

        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }
	
	private static Block getActiveBlock(int tier)
	{
		return BlockListMF.forge_active;
	}

	private static Block getInactiveBlock(int tier) 
	{
		return BlockListMF.forge;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return BlockListMF.crucible.getIcon(side, meta);
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset, float yOffset, float zOffset)
    {
		ItemStack held = user.getHeldItem();
		TileEntityForge tile = getTile(world, x, y, z);
    	if(tile != null)
    	{
    		if(held != null)
    		{
    			if(!isActive && held.getItem() instanceof ILighter || held.getItem() instanceof ItemFlintAndSteel)
    			{
    				if(held.getItem() instanceof ILighter)
    				{
    					ILighter custom = (ILighter)held.getItem();
    					if(custom.canLight() && rand.nextDouble() >= custom.getChance())
    					{
    						tile.fireUpForge();
    					}
    				}
    				else if(held.getItem() instanceof ItemFlintAndSteel)
    				{
    					tile.fireUpForge();
    				}
    				return true;
    			}
    			ForgeFuel stats = ForgeItemHandler.getStats(held);
    			if(stats != null && tile.addFuel(stats, true))
    			{
    				if(user.getHeldItem().stackSize == 1)
    				{
    					user.setCurrentItemOrArmor(0, null);
    				}
    				else
    				{
    					user.getHeldItem().stackSize --;
    				}
    				return true;
    			}
    			if(!world.isRemote && ResearchLogic.hasInfoUnlocked(user, KnowledgeListMF.smeltDragonforge) && held.getItem() == ComponentListMF.dragon_heart && tile.temperature >= 200F)
    			{
    				if(user.getHeldItem().stackSize == 1)
    				{
    					user.setCurrentItemOrArmor(0, null);
    				}
    				else
    				{
    					user.getHeldItem().stackSize --;
    				}
    				tile.dragonHeartPower = 1.0F;
    				return true;
    			}
    		}
    		if(!world.isRemote)
    		{
    			user.openGui(MineFantasyII.instance, 0, world, x, y, z);
    		}
    	}
        return true;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
	}
	@Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(getInactiveBlock(tier));
    }
	@Override
	public Item getItemDropped(int meta, Random rand, int fort)
    {
        return Item.getItemFromBlock(getInactiveBlock(tier));
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
	@Override
	public int getRenderType()
	{
		return BlockListMF.forge_RI;
	}
}
