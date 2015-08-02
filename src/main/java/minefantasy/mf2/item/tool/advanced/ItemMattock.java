package minefantasy.mf2.item.tool.advanced;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemMattock extends ItemPickaxe implements IToolMaterial
{
    private String Name;
	public ItemMattock(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabToolAdvanced);
        
        setUnlocalizedName("minefantasy2:Tool/Advanced/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setHarvestLevel("pickaxe", Math.max(0, material.getHarvestLevel()-2));
		Name = name;
    }
    
    public String getName(){
    	return Name;
    }
    
    private int itemRarity;
    @Override
	public EnumRarity getRarity(ItemStack item)
	{
		int lvl = itemRarity+1;
		
		if(item.isItemEnchanted())
		{
			if(lvl == 0)
			{
				lvl++;
			}
			lvl ++;
		}
		if(lvl >= ToolListMF.rarity.length)
		{
			lvl = ToolListMF.rarity.length-1;
		}
		return ToolListMF.rarity[lvl];
	}
    @Override
	public ToolMaterial getMaterial()
	{
		return toolMaterial;
	}
	@Override
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		return ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, state));
	}
	@Override
    public void addInformation(ItemStack item, EntityPlayer user, List list, boolean extra) 
    {
        super.addInformation(item, user, list, extra);
    }
	
	@Override
	public int getMaxDamage(ItemStack stack)
	{
    	if(ToolMaterialMF.isUnbreakable(toolMaterial))
		{
    		ToolMaterialMF.setUnbreakable(stack);
		}
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}
	@Override
    public boolean canHarvestBlock(Block block, ItemStack stack)
    {
    	return super.canHarvestBlock(block, stack) || Items.iron_shovel.canHarvestBlock(block, stack);
    }
	@Override
	public boolean onBlockStartBreak(ItemStack item, BlockPos pos, EntityPlayer user)
	{
		World world = user.worldObj;
		if(item.hasTagCompound())
		{
			int id = item.getTagCompound().hasKey("MF_Mattock_ID") ? item.getTagCompound().getInteger("MF_Mattock_ID") : 0;
			int meta = item.getTagCompound().hasKey("MF_Mattock_Meta") ? item.getTagCompound().getInteger("MF_Mattock_Meta") : 0;
			
			//if(player.capabilities.isCreativeMode || consumeItem(player, id, meta))
			Block block2 = Block.getBlockById(id);
			if(block2 != null)
			{
				MFLogUtil.logDebug("Set Block: " + id + " : " + meta);
				world.setBlockState(pos, block2.getDefaultState());
				world.setBlockState(pos, block2.getStateFromMeta(meta), 2);
				return true;
			}
		}
		return super.onBlockStartBreak(item, pos, user);
	}
	private void setBlock(ItemStack mattock, int id, int meta)
	{
		if(!mattock.hasTagCompound())mattock.setTagCompound(new NBTTagCompound());
		mattock.getTagCompound().setInteger("MF_Mattock_ID", id);
		mattock.getTagCompound().setInteger("MF_Mattock_Meta", meta);
		MFLogUtil.logDebug("set Mattock Tile: " + id + " : " + meta);
	}
	@Override
    public boolean onItemUse(ItemStack mattock, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float pitch, float yaw, float pan)
    {
        if (!player.canPlayerEdit(pos, facing, mattock))
        {
            return false;
        }
        else
        {
            Block block = world.getBlockState(pos).getBlock();

            if(block != null)
            {
            	int id = Block.getIdFromBlock(block);
            	int meta = block.getMetaFromState(world.getBlockState(pos));
        		setBlock(mattock, id, meta);
            }
        }
        return false;
    }
	private boolean consumeItem(EntityPlayer player, int id, int meta)
	{
		Block block = Block.getBlockById(id);
		if(block == null)return false;
		
		for(int a = 0; a < player.inventory.getSizeInventory(); a++)
		{
			ItemStack item = player.inventory.getStackInSlot(a);
			
			if(item != null && item.isItemEqual(new ItemStack(block, 1, meta)))
			{
				item.stackSize--;
				return true;
			}
		}
		return false;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
