package minefantasy.mf2.item.tool.advanced;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.weapon.IDamageType;
import minefantasy.mf2.farming.FarmingHelper;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import minefantasy.mf2.item.tool.crafting.ItemSaw;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Multimap;

/**
 * @author Anonymous Productions
 */
public class ItemScythe extends Item implements IToolMaterial, IDamageType
{
	private Random rand = new Random();
	private ToolMaterial toolMaterial;
	private String Name;
	/**
	 */
    public ItemScythe(String name, ToolMaterial material, int rarity)
    {
    	this.toolMaterial = material;
    	this.setFull3D();
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabToolAdvanced);
        this.hitDamage = 2.0F + material.getDamageVsEntity();
        setUnlocalizedName("minefantasy2:Tool/Advanced/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        Name=name;
    }
    
    public String getName(){
    	return Name;
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
	public ToolMaterial getMaterial()
	{
		return toolMaterial;
	}
	
	private boolean cutGrass(World world, BlockPos pos, int r, EntityPlayer entity, boolean leaf) 
	{
    	boolean flag = false;
    	ItemStack item = entity.getHeldItem();
    	if(item == null)return false;
    	
		for(int x2 = -r; x2 <= r; x2 ++)
		{
			for(int y2 = -r; y2 <= r; y2 ++)
			{
				for(int z2 = -r; z2 <= r; z2 ++)
				{
					
					BlockPos expandedpos = pos.add(x2,y2,z2);
					
					Block block = world.getBlockState(expandedpos).getBlock();
					int meta = block.getMetaFromState(world.getBlockState(expandedpos));
					if(block != null)
					{
						Material m = block.getMaterial();
						if(canCutMaterial(m, block.getBlockHardness(world, pos), leaf))
						{
							if(getDistance(expandedpos.getX(),expandedpos.getY(),expandedpos.getZ(), pos.getX(),pos.getY(),pos.getZ()) < r*1)
							{
								flag = true;
								
								List<ItemStack> items = block.getDrops(world, expandedpos, block.getStateFromMeta(meta), 0);
								world.setBlockToAir(expandedpos);
								world.playAuxSFXAtEntity(entity, 2001, expandedpos, Block.getIdFromBlock(block) + (block.getMetaFromState(world.getBlockState(expandedpos)) << 16));
								tryBreakFarmland(world, expandedpos.subtract(new Vec3i(0,1,0)));
								if(!entity.capabilities.isCreativeMode)
								{
									ItemSaw.tirePlayer(entity, 1F);
									for (ItemStack drop : items)
						            {
						                if (world.rand.nextFloat() <= 1.0F)
						                {
						                    dropBlockAsItem_do(world, expandedpos, drop);
						                }
						            }
								}
								item.damageItem(1, entity);
							}
						}
					}
				}
			}
		}
		return flag;
	}
	
	private void tryBreakFarmland(World world, BlockPos pos)
	{
		Block base = world.getBlockState(pos).getBlock();
		
		if(base != null && base == Blocks.farmland && FarmingHelper.didHarvestRuinBlock(world, true))
		{
			world.setBlockState(pos, Blocks.dirt.getDefaultState());
		}
	}

	protected void dropBlockAsItem_do(World world, BlockPos pos, ItemStack drop)
    {
        if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
            float var6 = 0.7F;
            double var7 = world.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
            double var9 = world.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
            double var11 = world.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
            EntityItem var13 = new EntityItem(world, pos.getX() + var7, pos.getY() + var9, pos.getZ() + var11, drop);
            var13.setDefaultPickupDelay();
            world.spawnEntityInWorld(var13);
        }
    }
	
	private boolean canCutMaterial(Material m, float str, boolean leaf)
	{
    	if(!leaf)
    	{
	    	if(str <= 0.0F)
	    	{
				return
				   m == Material.vine
				|| m == Material.plants
				|| m == Material.grass
				;}
	    	else return false;
    	}
    	return m == Material.leaves || m == Material.vine;
	}

	public double getDistance(double x, double y, double z, int posX, int posY, int posZ)
    {
        double var7 = posX - x;
        double var9 = posY - y;
        double var11 = posZ - z;
        return MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
    }
	
	@Override
	public Multimap getAttributeModifiers(ItemStack stack)
    {
        Multimap multimap = super.getAttributeModifiers(stack);
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(this.itemModifierUUID, "Tool modifier", this.hitDamage, 0));
        return multimap;
    }
	
	@Override
    public boolean onItemUse(ItemStack hoe, EntityPlayer player, World world, BlockPos pos,EnumFacing facing, float pitch, float yaw, float pan)
    {
        if (!player.canPlayerEdit(pos, facing, hoe) || !ItemSaw.canAcceptCost(player))
        {
            return false;
        }
        else
        {
            Block block = world.getBlockState(pos).getBlock();

            if(block != null)
            {
            	Material m = block.getMaterial();
            	float hard = block.getBlockHardness(world, pos);
            	if(this.canCutMaterial(m, hard, false))
            	{
            		if(cutGrass(world, pos, 5, player, false))
            		{
            			player.swingItem();
            		}
            	}
            	else
            	if(this.canCutMaterial(m, hard, true))
            	{
            		if(cutGrass(world, pos, 3, player, true))
            		{
            			player.swingItem();
            		}
            	}
            }
        }
        return false;
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
	public float getPenetrationLevel(Object implement)
	{
		return 0F;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
