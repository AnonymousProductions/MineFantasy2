package minefantasy.mf2.item.tool;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Anonymous Productions
 */
public class ItemShearsMF extends ItemShears implements IToolMaterial, IToolMF
{
	private ToolMaterial toolMaterial;
	private int tier;
    public ItemShearsMF(String name, ToolMaterial material, int rarity, int tier)
    {
        super();
        this.tier = tier;
        itemRarity = rarity;
        toolMaterial = material;
        setCreativeTab(CreativeTabMF.tabTool);
        this.setMaxDamage(material.getMaxUses());
        setTextureName("minefantasy2:Tool/"+name);
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
	public int getMaxDamage(ItemStack stack)
	{
    	if(ToolMaterialMF.isUnbreakable(toolMaterial))
		{
    		ToolMaterialMF.setUnbreakable(stack);
		}
		return ToolHelper.setDuraOnQuality(stack, super.getMaxDamage());
	}
    @Override
	public float getEfficiency(ItemStack item) 
	{
		return ToolHelper.modifyDigOnQuality(item, toolMaterial.getEfficiencyOnProperMaterial());
	}

	@Override
	public int getTier(ItemStack item) 
	{
		return tier;
	}

	@Override
	public String getToolType(ItemStack item)
	{
		return "shears";
	}
}
