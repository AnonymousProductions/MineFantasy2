package minefantasy.mf2.item.gadget;

import java.util.List;

import minefantasy.mf2.api.crafting.bomb.IBombComponent;
import minefantasy.mf2.item.ItemComponentMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBombComponent extends ItemComponentMF implements IBombComponent
{
	private byte tier;
	private String type;
	public ItemBombComponent(String name, String type, int tier)
	{
		this(name, 0, type, tier);
	}
	public ItemBombComponent(String name, int rarity, String type, int tier)
	{
		super(name, rarity);
		this.type = type;
		this.tier = (byte)tier;
	}

	@Override
	public String getComponentType()
	{
		return type;
	}

	@Override
	public byte getTier()
	{
		return tier;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }

}
