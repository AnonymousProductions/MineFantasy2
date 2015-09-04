package minefantasy.mf2.item.tool;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Anonymous Productions
 */
public class ItemPickMF extends ItemPickaxe implements IToolMaterial
{
    public ItemPickMF(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabTool);
        
        setTextureName("minefantasy2:Tool/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }
    
    private int itemRarity;
    @Override
	public EnumRarity getRarity(ItemStack item)
	{
		int lvl = itemRarity +1;
		
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
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		return ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, block, meta));
	}
	@Override
    public void addInformation(ItemStack item, EntityPlayer user, List list, boolean extra) 
    {
        super.addInformation(item, user, list, extra);
    }
	
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if(!world.isRemote)return item;
		
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

		if (movingobjectposition == null) 
		{
			return item;
		} else 
		{
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!world.canMineBlock(player, i, j, k)) 
				{
					return item;
				}

				if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, item)) {
					return item;
				}

				Block block = world.getBlock(i, j, k);
				int blockTier = block.getHarvestLevel(world.getBlockMetadata(i, j, k));
				
				if(blockTier > this.toolMaterial.getHarvestLevel())
				{
					String msg = StatCollector.translateToLocalFormatted("prospect.cannotmine", this.toolMaterial.getHarvestLevel(), blockTier);	
					player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + msg));
				}
				else
				{
					String msg = StatCollector.translateToLocalFormatted("prospect.canmine", this.toolMaterial.getHarvestLevel(), blockTier);	
					player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + msg));
				}
			}

			return item;
		}
	}

	private boolean canMineBlock(World world, int i, int j, int k) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}
}
