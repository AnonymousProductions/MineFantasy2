package minefantasy.mf2.api.armour;

import java.util.HashMap;

import minefantasy.mf2.api.helpers.ArmourCalculator;


public class ArmourDesign 
{
	//Weight: Above 50kg slows down and increases stamina decay, and classes as heavy armour
	//Bulk: Above 0.0 means it slows stamina regen
	public static HashMap<String, ArmourDesign>designs = new HashMap<String, ArmourDesign>();
	//                                                            Reg         Name        Prot     Dura    Weight   Bulk                  Ctg    Bnt  Png
	public static final ArmourDesign CLOTH = new ArmourDesign(   "clothing", "Clothing",  1.0F,    1.0F,    0F,     0.25F).calibrateTraits(0.5F, 1.0F,  0.5F);//Weak
	public static final ArmourDesign LEATHER = new ArmourDesign( "leather",  "Leather",   1.0F,    1.0F,   10F,     0.25F).calibrateTraits(0.75F, 1.0F, 0.50F);//Slightly better blunt
	public static final ArmourDesign PADDING = new ArmourDesign( "padding",  "Padding",   1.0F,    1.0F,   12F,     1.00F).calibrateTraits(0.4F, 1.0F, 0.25F);//Blunt resistent
	public static final ArmourDesign SOLID = new ArmourDesign(   "default",  "Basic",     1.0F,    1.0F,   40F,     1.00F);//Basic Armour
	public static final ArmourDesign MAIL = new ArmourDesign(    "mail",     "Mail",      1.0F,    2.0F,   35F,     0.75F).calibrateTraits(1.0F, 0.5F, 0.85F);//Vulnerable to blunt
	public static final ArmourDesign PLATE = new ArmourDesign(   "plate",    "Plate",     1.5F,    1.5F,   60F,     1.00F).calibrateTraits(1.0F, 1.0F, 0.5F);//Vulnerable to piercing
	
	private String name;
	private float durability;
	private float armourWeight;
	
	private float baseProtection;
	
	private float cuttingModifier = 1.0F;
	private float piercingModifier = 1.0F;
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
	public ArmourDesign calibrateTraits(float cutting, float blunt, float pierce)
	{
		cuttingModifier = cutting;
		bluntModifier = blunt;
		piercingModifier = pierce;
		
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
		return new float[]{cuttingModifier, bluntModifier, piercingModifier};
	}
	public float getBulk()
	{
		return bulk;
	}
}
