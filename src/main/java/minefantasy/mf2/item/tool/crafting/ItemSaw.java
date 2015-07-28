package minefantasy.mf2.item.tool.crafting;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.api.weapon.IDamageType;
import minefantasy.mf2.config.ConfigTools;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.google.common.collect.Multimap;

/**
 * @author Anonymous Productions
 */
public class ItemSaw extends ItemAxe implements IToolMaterial, IDamageType, IToolMF
{
	private Random rand = new Random();
	/**
	 */
    public ItemSaw(String name, ToolMaterial material, int rarity)
    {
        super(material);
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabCraftTool);
        this.hitDamage = (2.0F + material.getDamageVsEntity())/2F;
        setUnlocalizedName("minefantasy2:Tool/Crafting/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		
		this.setHarvestLevel("axe", material.getHarvestLevel());
    }
    
    private int itemRarity;
	private double hitDamage;
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
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
    	
    }
    
    @Override
	public ToolMaterial getMaterial()
	{
		return toolMaterial;
	}
    
	@Override
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		return super.getDigSpeed(stack, state) / 10F;
	}

	public boolean onBlockDestroyedOld(ItemStack item, World world, Block block, BlockPos pos, EntityLivingBase user)
	{
		if(user instanceof EntityPlayer && canAcceptCost(user))
		{
			breakChain(world, pos, item, block, user, 32, block, block.getMetaFromState(world.getBlockState(pos)));
		}
		return super.onBlockDestroyed(item, world, block, pos, user);
	}
	
	private void breakChain(World world, BlockPos pos, ItemStack item, Block block, EntityLivingBase user, int maxLogs, Block orient, int orientM) 
	{
		if(maxLogs > 0 && isLog(world, pos, orient, orientM))
		{
			Block newblock = world.getBlockState(pos).getBlock();
			breakSurrounding(item, world, newblock, pos, user);
			newblock.dropBlockAsItem(world, pos, world.getBlockState(pos), EnchantmentHelper.getFortuneModifier(user));
			world.setBlockToAir(pos);
			item.damageItem(1, user);
			
			maxLogs --;
			for(int x1 = -1; x1 <= 1; x1 ++)
			{
				for(int y1 = -1; y1 <= 1; y1 ++)
				{
					for(int z1 = -1; z1 <= 1; z1 ++)
					{
						breakChain(world, pos.add(x1, y1, z1), item, newblock, user, maxLogs, orient, orientM);
					}
				}
			}
			if(user instanceof EntityPlayer)
			{
				tirePlayer(user, 2.0F);
			}
		}
	}

	public static boolean canAcceptCost(EntityLivingBase user)
	{
		return canAcceptCost(user, 0.1F);
	}
	public static boolean canAcceptCost(EntityLivingBase user, float cost)
	{
		if(user instanceof EntityPlayer && StaminaBar.isSystemActive)
		{
			return StaminaBar.isPercentStamAvailable(user, cost, true);
		}
		return true;
	}
	
	public static void tirePlayer(EntityLivingBase user, float points)
	{
		if(user instanceof EntityPlayer && StaminaBar.isSystemActive)
		{
			StaminaBar.modifyStaminaValue(user, -StaminaBar.getBaseDecayModifier(user, true, true)*points);
			StaminaBar.ModifyIdleTime(user, 5F*points);
		}
	}

	private boolean isLog(World world, BlockPos pos, Block orient, int orientM) 
	{
		Block block = world.getBlockState(pos).getBlock();
		int meta = block.getMetaFromState(world.getBlockState(pos));
		
			return block == orient && block.getMaterial() == Material.wood;
	}

	public void breakSurrounding(ItemStack item, World world, Block block, BlockPos pos, EntityLivingBase user)
	{
		if(!world.isRemote && ForgeHooks.isToolEffective(world,pos,item))
		{
			for(int x1 = -2; x1 <= 2; x1 ++)
			{
				for(int y1 = -2; y1 <= 2; y1 ++)
				{
					for(int z1 = -2; z1 <= 2; z1 ++)
					{
						EnumFacing FD = user.getHorizontalFacing();
						int blockX = pos.getX()+x1 + FD.getFrontOffsetX();
						int blockY = pos.getY()+y1 + FD.getFrontOffsetY();
						int blockZ = pos.getZ()+z1 + FD.getFrontOffsetZ();
						BlockPos newpos = new BlockPos(blockX,blockY,blockZ);
						
						if(!(x1+FD.getFrontOffsetX() == 0 && y1+FD.getFrontOffsetY() == 0 &&  z1+FD.getFrontOffsetZ() == 0))
						{
							Block newblock = world.getBlockState(newpos).getBlock();
							int m = block.getMetaFromState(world.getBlockState(newpos));
							
							if(item.getItemDamage() < item.getMaxDamage() && newblock != null && user instanceof EntityPlayer && newblock.getMaterial() == Material.leaves)
							{
								if(rand.nextFloat()*100F < (100F - ConfigTools.hvyDropChance))
								{
									newblock.dropBlockAsItem(world, newpos, block.getStateFromMeta(m), EnchantmentHelper.getFortuneModifier(user));
								}
								world.setBlockToAir(newpos);
								item.damageItem(1, user);
							}
						}
					}	
				}	
			}
		}
	}
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(this.itemModifierUUID, "Tool modifier", this.hitDamage, 0));
        return multimap;
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
	public float[] getDamageRatio(Object implement) 
	{
		return new float[]{1,0};
	}

	@Override
	public float getEfficiency(ItemStack item)
	{
		return toolMaterial.getEfficiencyOnProperMaterial();
	}

	@Override
	public int getTier(ItemStack item)
	{
		return toolMaterial.getHarvestLevel();
	}

	@Override
	public String getToolType(ItemStack item)
	{
		return "saw";
	}
	@Override
	public float getPenetrationLevel(Object implement)
	{
		return 0.5F;
	}
}
