package minefantasy.mf2.item.archery;

public enum EnumBowType 
{
	/**
	 * Average Bow
	 * 25% less power than basic
	 */
	SHORTBOW(0.75F, 1.0F, 1.0F),
	/**
	 * Metal Bow
	 * 50% faster than basic
	 */
	RECURVE(1.0F, 1.0F, 1.0F),
	/**
	 * Strong bow
	 * Same power as Shortbow
	 * 10% slower than Shortbow
	 * 3.5x more durable
	 */
	COMPOSITE(0.75F, 0.9F, 3.5F),
	/**
	 * Long ranged bow the same as vanilla bows used to be
	 * Full power, 25% less speed than shortbow
	 */
	LONGBOW(1.0F, 0.75F, 1.0F);
	
	public final float power;
	public final float speed;
	public final float durability;
	
	/**
	 * @param pwr The power max 1.0 to avoid complications
	 * @param spd The speed (Higher is faster)
	 * @param dur The durability in relation to material
	 */
	private EnumBowType(float pwr, float spd, float dur)
	{
		power = pwr;
		speed = spd;
		durability = dur;
	}
}
