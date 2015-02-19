package minefantasy.mf2.item.gadget;

public enum EnumCasingType
{
	CERAMIC("ceramic",  1.0F, 1.0F, 1.0F),
	IRON("iron",        1.5F, (4.0F/5.5F), 2.0F),
	OBSIDIAN("obsidian",2.0F, (2.0F/5.5F), 1.5F),
	CRYSTAL("crystal",  1.5F, (6.0F/5.5F), 0.75F);
	
	public String name;
	public float damageModifier;
	public float rangeModifier;
	public float weightModifier;
	
	private EnumCasingType(String name, float damage, float range, float weight)
	{
		this.name = name;
		this.damageModifier = damage;
		this.rangeModifier = range;
		this.weightModifier = weight;
	}
	
	public static EnumCasingType getType(byte i)
	{
		if(i == 1)
		{
			return IRON;
		}
		if(i == 2)
		{
			return OBSIDIAN;
		}
		if(i == 3)
		{
			return CRYSTAL;
		}
		return CERAMIC;
	}
}
