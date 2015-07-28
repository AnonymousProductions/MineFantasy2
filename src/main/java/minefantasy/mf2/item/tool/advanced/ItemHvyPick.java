package minefantasy.mf2.item.tool.advanced;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.config.ConfigTools;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import minefantasy.mf2.item.tool.crafting.ItemSaw;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemHvyPick extends ItemPickaxe implements IToolMaterial
{
	private Random rand = new Random();
	/**
	 * A Heavy pick is designed more for quarry use, it is significantly slower than a normal pick, has 2 less harvest levels
	 * but has 2x the durability.
	 * 
	 * These picks mine a 3x3 area of blocks, though with their lessened harvest, most ores will be avoided
	 * They however have a reduced drop chance
	 */
    public ItemHvyPick(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabToolAdvanced);
        
        setUnlocalizedName("minefantasy2:Tool/Advanced/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		
		this.setHarvestLevel("pickaxe", material.getHarvestLevel());
		this.setMaxDamage(material.getMaxUses()*2);
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
		return ToolHelper.modifyDigOnQuality(stack, super.getDigSpeed(stack, state)) / 10F;
	}
	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, Block block, BlockPos pos, EntityLivingBase user)
	{
		if(!world.isRemote && ForgeHooks.isToolEffective(world, pos,item) && ItemSaw.canAcceptCost(user))
		{
			for(int x1 = -1; x1 <= 1; x1 ++)
			{
				for(int y1 = -1; y1 <= 1; y1 ++)
				{
					for(int z1 = -1; z1 <= 1; z1 ++)
					{
						EnumFacing FD = user.getHorizontalFacing();
						BlockPos newpos = pos.add(x1 + FD.getFrontOffsetX(),y1 + FD.getFrontOffsetY(), z1 + FD.getFrontOffsetZ());
						
						if(!(x1+FD.getFrontOffsetX()== 0 && y1+FD.getFrontOffsetY() == 0 &&  z1+FD.getFrontOffsetZ() == 0))
						{
							Block newblock = world.getBlockState(newpos).getBlock();
							int m = block.getMetaFromState(world.getBlockState(newpos));
							
							if(newblock != null && user instanceof EntityPlayer && ForgeHooks.canHarvestBlock(newblock, (EntityPlayer) user, world, newpos) && ForgeHooks.isToolEffective(world,newpos,item))
							{
								if(rand.nextFloat()*100F < (100F - ConfigTools.hvyDropChance))
								{
									newblock.dropBlockAsItem(world, newpos, block.getStateFromMeta(m), EnchantmentHelper.getFortuneModifier(user));
								}
								world.setBlockToAir(newpos);
								item.damageItem(1, user);
								ItemSaw.tirePlayer(user, 1F);
							}
						}
					}	
				}	
			}
		}
		return super.onBlockDestroyed(item, world, block, pos, user);
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
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
