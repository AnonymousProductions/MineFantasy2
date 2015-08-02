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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Sets;

/**
 * @author Anonymous Productions
 */
public class ItemBasicCraftTool extends ItemTool implements IToolMaterial, IToolMF, IDamageType
{
	private int tier;
	private String toolType;
	private String Name;
    public ItemBasicCraftTool(String name, String type, int tier, int uses)
    {
        super(1.0F, ToolMaterial.WOOD, Sets.newHashSet(new Block[] {}));
        this.tier = tier;
        setCreativeTab(CreativeTabMF.tabCraftTool);
        
        toolType = type;
        setUnlocalizedName("minefantasy2:Tool/Crafting/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		setMaxDamage(uses);
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
	public float getEfficiency(ItemStack item) 
	{
		return 1.0F;
	}

	@Override
	public int getTier(ItemStack item) 
	{
		return tier;
	}

	@Override
	public String getToolType(ItemStack item)
	{
		return toolType;
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
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
