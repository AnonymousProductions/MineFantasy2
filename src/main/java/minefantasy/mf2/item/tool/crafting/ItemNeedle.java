package minefantasy.mf2.item.tool.crafting;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.tier.IToolMaterial;
import minefantasy.mf2.api.tool.IToolMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * @author Anonymous Productions
 */
public class ItemNeedle extends ItemTool implements IToolMaterial, IToolMF
{
	private ToolMaterial material;
	private int tier;
	private String Name;
    public ItemNeedle(String name, ToolMaterial material, int rarity, int tier)
    {
        super(0F, material, Sets.newHashSet(new Block[] {}));
        this.tier=tier;
        this.material = material;
        itemRarity = rarity;
        setCreativeTab(CreativeTabMF.tabCraftTool);
        
        setUnlocalizedName("minefantasy2:Tool/Crafting/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		Name = name;
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
    	
    }
    
    public String getName(){
    	return Name;
    }
    
    @Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap map = HashMultimap.create();
		map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(this.itemModifierUUID, "Weapon modifier", 0.5D, 0));

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
		return "needle";
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
}
