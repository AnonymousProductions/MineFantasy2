package minefantasy.mf2.item.gadget;

public enum EnumCasingType
{
	CERAMIC("ceramic", 1.0F, 1.0F, 1.0F),
	IRON("iron",       1.5F, 0.8F, 2.0F);
	
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
		return CERAMIC;
	}
}
