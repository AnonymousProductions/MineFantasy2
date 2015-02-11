package minefantasy.mf2.item.tool.crafting;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.api.weapon.IDamageType;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Anonymous Productions
 */
public class ItemHammer extends ItemTool implements IToolMaterial, IToolMF, IDamageType
{
	private ToolMaterial material;
	private int tier;
	private boolean heavy;
    public ItemHammer(String name, ToolMaterial material, int tier, boolean heavy, int rarity)
    {
        super(heavy ? 3.0F:2.0F, material, Sets.newHashSet(new Block[] {}));
        this.heavy = heavy;
        this.material = material;
        this.tier = tier;
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabCraftTool);
        
        setTextureName("minefantasy2:Tool/Crafting/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
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
	public float getEfficiency(ItemStack item) 
	{
		return ToolHelper.modifyDigOnQuality(item, material.getEfficiencyOnProperMaterial());
	}

	@Override
	public int getTier(ItemStack item) 
	{
		return tier;
	}

	@Override
	public String getToolType(ItemStack item)
	{
		return heavy ? "hvyHammer" : "hammer";
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
		return new float[]{0,1};
	}
	
	@Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
		if(this != ToolListMF.hammerStone)
		{
			return;
		}
		list.add(new ItemStack(ToolListMF.hammerStone));
		addSet(list, ToolListMF.hammers);
		addSet(list, ToolListMF.hvyHammers);
		
		list.add(new ItemStack(ToolListMF.tongsStone));
		addSet(list, ToolListMF.tongs);
		
		addSet(list, ToolListMF.saws);
		
		list.add(new ItemStack(ToolListMF.knifeStone));
		addSet(list, ToolListMF.knives);
		
		list.add(new ItemStack(ToolListMF.needleBone));
		addSet(list, ToolListMF.needles);
    }

	private void addSet(List list, Item[] items) 
	{
		for(Item item:items)
		{
			list.add(new ItemStack(item));
		}
	}
	@Override
	public float getPenetrationLevel(Object implement)
	{
		return 0F;
	}
}
