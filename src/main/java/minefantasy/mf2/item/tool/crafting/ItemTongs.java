package minefantasy.mf2.item.tool.crafting;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.heating.TongsHelper;
import minefantasy.mf2.api.helpers.GuiHelper;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemTongs extends ItemTool implements IToolMaterial
{
	private ToolMaterial material;
    public ItemTongs(String name, ToolMaterial material, int rarity)
    {
        super(0F, material, Sets.newHashSet(new Block[] {}));
        this.material = material;
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabCraftTool);
        setTextureName("minefantasy2:Tool/Crafting/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
    	
    }
    @Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap map = HashMultimap.create();
		map.clear();

        return map;
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
	public int getMaxDamage(ItemStack stack)
	{
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean fullInfo) {
		super.addInformation(stack, player, list, fullInfo);

		ItemStack held = TongsHelper.getHeldItem(stack);
		if (held != null) {
			list.add("");
			list.add(held.getItem().getItemStackDisplayName(held));
			held.getItem().addInformation(held, player, list, fullInfo);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass) 
	{
		ItemStack item = TongsHelper.getHeldItem(stack);

		if (renderPass == 0 && item != null) {
			return item.getItem().getIcon(item, renderPass);
		}
		return itemIcon;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

		if (movingobjectposition == null) {
			return item;
		} else {
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!world.canMineBlock(player, i, j, k)) {
					return item;
				}

				if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, item)) {
					return item;
				}

				if (TongsHelper.isWaterSource(world, i, j, k) && TongsHelper.getHeldItem(item) != null) {
					ItemStack drop = TongsHelper.getHeldItem(item).copy(), cooled = drop;
					
					if (TongsHelper.isCoolableItem(drop)) 
					{
						cooled = TongsHelper.getHotItem(drop);
						cooled.stackSize = drop.stackSize;

						player.playSound("random.splash", 1F, 1F);
						player.playSound("random.fizz", 2F, 0.5F);

						for (int a = 0; a < 5; a++) {
							world.spawnParticle("largesmoke", i + 0.5F, j + 1, k + 0.5F, 0, 0.065F, 0);
						}
					}
					if (cooled != null && !world.isRemote) 
					{
						if(world.isAirBlock(i, j+1, k))
						{
							EntityItem entity = new EntityItem(world, i+0.5, j+1, k+0.5, cooled);
							entity.delayBeforeCanPickup = 20;
							entity.motionX = entity.motionY = entity.motionZ = 0F;
							world.spawnEntityInWorld(entity);
						}
						else
						{
							player.entityDropItem(cooled, 0);
						}
					}

					return TongsHelper.clearHeldItem(item, player);
				}
			}

			return item;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() 
	{
		return true;
	}

	@Override
	public int getColorFromItemStack(ItemStack item, int renderPass)
	{
		if (renderPass == 1) 
		{
			return  GuiHelper.getColourForRGB(255, 255, 255);
		}

		ItemStack held = TongsHelper.getHeldItem(item);
		if (held != null)
		{
			return held.getItem().getColorFromItemStack(held, 0);
		}

		return GuiHelper.getColourForRGB(255, 255, 255);
	}
}
