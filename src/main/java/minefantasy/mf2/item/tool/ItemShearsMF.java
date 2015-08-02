package minefantasy.mf2.item.tool;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Anonymous Productions
 */
public class ItemShearsMF extends ItemShears implements IToolMaterial, IToolMF
{
	private ToolMaterial toolMaterial;
	private int tier;
	private String Name;
	
    public ItemShearsMF(String name, ToolMaterial material, int rarity, int tier)
    {
        super();
        this.tier = tier;
        itemRarity = rarity;
        toolMaterial = material;
        setCreativeTab(CreativeTabMF.tabTool);
        this.setMaxDamage(material.getMaxUses());
        setUnlocalizedName("minefantasy2:Tool/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
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
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
