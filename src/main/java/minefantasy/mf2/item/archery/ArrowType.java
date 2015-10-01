package minefantasy.mf2.item.archery;

import java.util.HashMap;

public class ArrowType
{
	public static HashMap<String, ArrowType>arrowMap = new HashMap<String, ArrowType>(); 
	public final float weightModifier;
	public final float damageModifier;
	public final float velocity;
	public final String name;
	
	public static ArrowType	NORMAL		= new ArrowType("normal", 1.00F, 1.00F, 1.00F);
	public static ArrowType	BODKIN		= new ArrowType("bodkin", 1.50F, 0.65F, 0.50F);
	public static ArrowType	BROADHEAD	= new ArrowType("broad",  0.80F, 1.20F, 2.00F);
	public static ArrowType EXPLOSIVE   = new ArrowType("explosive", 1.0F, 1.50F, 0.25F);

	public ArrowType(String name, float velocity, float weightModifier, float damageModifier)
	{
		this.name = name;
		this.velocity = velocity;
		this.weightModifier = weightModifier;
		this.damageModifier = damageModifier;
		arrowMap.put(name, this);
	}
	
}
