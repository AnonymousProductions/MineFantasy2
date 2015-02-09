package minefantasy.mf2.item;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.item.archery.ItemArrowMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.InformationList;
import minefantasy.mf2.knowledge.ResearchLogic;
import minefantasy.mf2.mechanics.PlayerTagData;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
/**
 * @author Anonymous Productions
 */
public class ItemComponentMF extends Item 
{
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
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer user)
	{
    	if(this == ComponentListMF.ingots[2])
    	{
    		InformationList.smeltBronze.trigger(user, true);
    	}
    	if(this == ComponentListMF.ingots[3])
    	{
    		InformationList.smeltSteel.trigger(user, true);
    	}
    	if(this == ComponentListMF.fireclay_brick)
    	{
    		InformationList.blastfurn.trigger(user, true);
    	}
    	return item;
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
			if(ingot == ComponentListMF.ingots[8])
			{
				add(list, Items.gold_ingot);
			}
			add(list, ingot);
		}
		add(list, ComponentListMF.plank);

		add(list, ComponentListMF.rawhideSmall);
		add(list, ComponentListMF.rawhideMedium);
		add(list, ComponentListMF.rawhideLarge);
		add(list, ComponentListMF.hideSmall);
		add(list, ComponentListMF.hideMedium);
		add(list, ComponentListMF.hideLarge);
		add(list, ComponentListMF.padding);
		
		add(list, ComponentListMF.flux);
		add(list, ComponentListMF.flux_strong);
		add(list, ComponentListMF.coke);
		add(list, ComponentListMF.diamond_shards);
		add(list, ComponentListMF.diamond_dust);
		add(list, ComponentListMF.fletching);
		
		add(list, ComponentListMF.coalDust);
		add(list, ComponentListMF.sulfur);
		add(list, ComponentListMF.nitre);
		add(list, ComponentListMF.blackpowder);
		add(list, ComponentListMF.bomb_fuse);
		add(list, ComponentListMF.shrapnel);
		add(list, ComponentListMF.bomb_casing_uncooked);
		add(list, ComponentListMF.bomb_casing);
		add(list, ComponentListMF.mine_casing_uncooked);
		add(list, ComponentListMF.mine_casing);
		add(list, ComponentListMF.bomb_casing_iron);
		add(list, ComponentListMF.mine_casing_iron);
		add(list, ComponentListMF.bomb_casing_obsidian);
		add(list, ComponentListMF.mine_casing_obsidian);
		
		add(list, ComponentListMF.clay_brick);
		add(list, ComponentListMF.kaolinite);
		add(list, ComponentListMF.kaolinite_dust);
		add(list, ComponentListMF.fireclay);
		add(list, ComponentListMF.fireclay_brick);
		add(list, ComponentListMF.strong_brick);
		add(list, ComponentListMF.dragon_heart);
		
		add(list, ComponentListMF.vine);
		add(list, ComponentListMF.sharp_rock);
    }
    private void add(List list, Item item)
    {
    	list.add(new ItemStack(item));
    }
}
