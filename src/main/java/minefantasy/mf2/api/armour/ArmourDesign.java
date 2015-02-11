package minefantasy.mf2.api.armour;

import java.util.HashMap;


public class ArmourDesign 
{
	//Weight: Above 50kg slows down and increases stamina decay, and classes as heavy armour
	//Bulk: Above 0.0 means it slows stamina regen
	public static HashMap<String, ArmourDesign>designs = new HashMap<String, ArmourDesign>();
	//                                                            Reg         Name        Prot     Dura    Weight   Bulk
	public static final ArmourDesign CLOTH = new ArmourDesign(   "clothing", "Clothing",  1.0F,    1.0F,    0F,     0.0F).calibrateTraits(0.5F, 1.0F);
	public static final ArmourDesign LEATHER = new ArmourDesign( "leather",  "Leather",   1.0F,    1.0F,   10F,     0.0F).calibrateTraits(1.0F, 0.5F);
	public static final ArmourDesign SOLID = new ArmourDesign(   "default",  "Basic",     1.0F,    1.0F,   40F,     0.5F);//Basic Armour
	public static final ArmourDesign MAIL = new ArmourDesign(    "mail",     "Mail",      1.0F,    1.0F,   35F,     0.2F).calibrateTraits(1.0F, 0.8F);
	public static final ArmourDesign PLATE = new ArmourDesign(   "plate",    "Plate",     1.5F,    2.0F,   60F,     1.0F).calibrateTraits(1.0F, 1.0F);//All round
	
	private String name;
	private float durability;
	private float armourWeight;
	
	private float baseProtection;
	
	private float cuttingModifier = 1.0F;
	private float bluntModifier = 1.0F;
	
	private float bulk;
	
	/**
	 * 
	 * @param register The string that it is registered under (For config)
	 * @param name The name of the Design
	 * @param prot The protection against basic damage (1.0F = normal protection for tier)
	 * @param dura The durability modifier
	 * @param weight the weight in Kg of the suit
	 * @param bulk the bulk of the suit (1.0 = plate, 0.5 = splint, etc)
	 */
	public ArmourDesign(String register, String name, float prot, float dura, float weight, float bulk)
	{
		designs.put(register, this);
		this.name=name;
		baseProtection = prot;
		
		durability = dura;
		armourWeight = weight;
		
		this.bulk = bulk;
	}
	
	/**
	 * These traits only apply when armours are coded to take them
	 */
	public ArmourDesign calibrateTraits(float cutting, float blunt)
	{
		cuttingModifier = cutting;
		bluntModifier = blunt;
		
		return this;
	}
	
	public String getName()
	{
		return name;
	}
	public float getDurability()
	{
		return durability;
	}
	public float getWeight()
	{
		return armourWeight;
	}
	public float getRating()
	{
		return baseProtection;
	}
	public float[] getProtectiveTraits()
	{
		return new float[]{cuttingModifier, bluntModifier};
	}
	public float getBulk()
	{
		return bulk;
	}
}
