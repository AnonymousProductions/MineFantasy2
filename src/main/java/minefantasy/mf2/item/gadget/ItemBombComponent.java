package minefantasy.mf2.item.gadget;

import minefantasy.mf2.api.crafting.bomb.IBombComponent;
import minefantasy.mf2.item.ItemComponentMF;

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

}
