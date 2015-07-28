package minefantasy.mf2.item.food;

import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
/**
 * @author Anonymous Productions
 */
public class ItemUnfinishedFood extends Item 
{
	
	private String NAME = "";
	
	public ItemUnfinishedFood(String Name)
	{
		this(Name, 0);
	}
	public ItemUnfinishedFood(String Name, int rarity)
	{
		super();
		final String name = Name;
    	NAME = Name;
		setMaxStackSize(1);
		itemRarity = rarity;
		setUnlocalizedName("minefantasy2:food/unfinished/"+ name);
		GameRegistry.registerItem(this, "MF_UFood"+name, MineFantasyII.MODID);
		
		//setTextureName("minefantasy2:food/unfinished/"+name);
		this.setCreativeTab(CreativeTabMF.tabFood);
	}
	

	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
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
    public void onCrafted(EntityPlayer user, ItemStack item)
	{
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
