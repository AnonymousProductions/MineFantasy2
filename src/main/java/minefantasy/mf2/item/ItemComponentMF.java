package minefantasy.mf2.item;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
/**
 * @author Anonymous Productions
 */
public class ItemComponentMF extends Item 
{
	public ItemComponentMF(int rarity)
	{
		itemRarity = rarity;
	}
	public ItemComponentMF(String name)
	{
		this(name, 0);
	}
	public ItemComponentMF(String name, int rarity)
	{
		super();
		itemRarity = rarity;
		setTextureName("minefantasy2:component/"+name);
		this.setCreativeTab(CreativeTabMF.tabMaterialsMF);
		GameRegistry.registerItem(this, "MF_Com_"+name, MineFantasyII.MODID);
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
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
    	if(this.getCreativeTab() != CreativeTabMF.tabMaterialsMF)
    	{
    		super.getSubItems(item, tab, list);
    		return;
    	}
		if(this != ComponentListMF.plank)
		{
			return;
		}
		
		for(Item ingot: ComponentListMF.ingots)
		{
			if(ingot == ComponentListMF.ingots[3])
			{
				add(list, Items.iron_ingot);
			}
			if(ingot == ComponentListMF.ingots[7])
			{
				add(list, Items.gold_ingot);
			}
			add(list, ingot);
		}
		add(list, ComponentListMF.plank);
		add(list, ComponentListMF.plankRefined);
		add(list, ComponentListMF.nail);
		add(list, ComponentListMF.rivet);
		add(list, ComponentListMF.thread);
		
		add(list, ComponentListMF.leather_strip);
		add(list, ComponentListMF.rawhideSmall);
		add(list, ComponentListMF.rawhideMedium);
		add(list, ComponentListMF.rawhideLarge);
		add(list, ComponentListMF.hideSmall);
		add(list, ComponentListMF.hideMedium);
		add(list, ComponentListMF.hideLarge);
		
		add(list, ComponentListMF.oreCopper);
		add(list, ComponentListMF.oreTin);
		add(list, ComponentListMF.oreIron);
		add(list, ComponentListMF.oreSilver);
		add(list, ComponentListMF.oreGold);
		
		add(list, ComponentListMF.flux);
		add(list, ComponentListMF.flux_strong);
		add(list, ComponentListMF.coke);
		add(list, ComponentListMF.diamond_shards);
		add(list, ComponentListMF.fletching);
		add(list, ComponentListMF.plant_oil);
		
		add(list, ComponentListMF.coalDust);
		add(list, ComponentListMF.iron_prep);
		add(list, ComponentListMF.obsidian_dust);
		add(list, ComponentListMF.sulfur);
		add(list, ComponentListMF.nitre);
		add(list, ComponentListMF.blackpowder);
		add(list, ComponentListMF.blackpowder_advanced);
		add(list, ComponentListMF.bomb_fuse);
		add(list, ComponentListMF.bomb_fuse_long);
		add(list, ComponentListMF.shrapnel);
		add(list, ComponentListMF.magma_cream_refined);
		add(list, ComponentListMF.bomb_casing_uncooked);
		add(list, ComponentListMF.bomb_casing);
		add(list, ComponentListMF.mine_casing_uncooked);
		add(list, ComponentListMF.mine_casing);
		add(list, ComponentListMF.bomb_casing_iron);
		add(list, ComponentListMF.mine_casing_iron);
		add(list, ComponentListMF.bomb_casing_obsidian);
		add(list, ComponentListMF.mine_casing_obsidian);
		add(list, ComponentListMF.bomb_casing_crystal);
		add(list, ComponentListMF.mine_casing_crystal);
		
		add(list, ComponentListMF.clay_brick);
		add(list, ComponentListMF.kaolinite);
		add(list, ComponentListMF.kaolinite_dust);
		add(list, ComponentListMF.fireclay);
		add(list, ComponentListMF.fireclay_brick);
		add(list, ComponentListMF.strong_brick);
		add(list, ComponentListMF.dragon_heart);
		
		add(list, ComponentListMF.silver_rod);
		add(list, ComponentListMF.gold_rod);
		add(list, ComponentListMF.obsidian_rod);
		
    }
    private void add(List list, Item item)
    {
    	list.add(new ItemStack(item));
    }
}
