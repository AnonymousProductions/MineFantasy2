package minefantasy.mf2.material;

import java.util.HashMap;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.armour.ArmourMaterialMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 *	This is used to create both tool and armour materials. Variables needed: Durability, protection, sharpness, enchantment, weight, harvestLvl
 */
public class BaseMaterialMF
{
	public static HashMap<String, BaseMaterialMF> materialMap = new HashMap<String, BaseMaterialMF>();
	
	/**
	 * This scales armour so a sword hitting full mail of equal tier has this as a result
	 * Used so armour can scale up with weapon damage
	 */
	private static final float ratioScale = 2.0F;
	/**
	 * The base damage for swords used with players. (Swords are 4+dam, adding 1 dam for player base hit dam)
	 */
	private static final float swordDam = 5F;
	
	private static float ACrounding = 10F; //round to nearest 10
	/*
	 * To get variables: X = sword damage, Y = Armour Ratio(+1 of value), Z = Damage
	 * 
	 * X = Z * Y
	 * Y = X / Z
	 * Z = X / Y
	 */
	//Rounding off to nearest 0.5 makes about an 0.03 difference, but the AR is cleaner
	//Hardness isn't added, it calculats armour itself to match sharpness 
	// 					                                            name   		      dura,         AC           enchant   weight
	public static BaseMaterialMF linen =  addArmourSpecificMaterial("Linen",   	        0, 10 ,          0.1F,        0,        1.00F);
	public static BaseMaterialMF wool =  addArmourSpecificMaterial("Wool"  ,   	        1, 15 ,          0.15F,       0,        1.00F);
	
	public static BaseMaterialMF hide =  addArmourSpecificMaterial("Hide",   	        0, 20 ,          0.2F,        0,        1.00F);
	public static BaseMaterialMF rough = addArmourSpecificMaterial("RoughLeather",      0, 35 ,          0.3F,        1,        1.00F);
	public static BaseMaterialMF reinforced = addArmourSpecificMaterial("StrongLeather",   1, 250,          1.8F,        1,        1.00F);
	public static BaseMaterialMF studded = addArmourSpecificMaterial("StudLeather",         1, 500,          2.4F,        5,        1.20F);
	public static BaseMaterialMF scaled = addArmourSpecificMaterial("ScaleLeather",           2, 1000,         2.7F,        8,        1.50F);
	public static BaseMaterialMF dragonscale = addArmourSpecificMaterial("Dragonscale", 3, 3000,         8.0F,        20,       1.20F).setRarity(2);
	
	//                                                   name    		    Tier dura,    harvest   sharpness   enchant   weight
	public static BaseMaterialMF stone =   addMaterial("Stone",		          0, 60 ,     0,     0.1F, 0.0F,    0,        2.00F).setForgeStats(0, 0, 0.75F);;
	public static BaseMaterialMF tin  =    addMaterial("Tin",   	          0, 100,     0,        0.2F,       5 ,       0.80F);
	public static BaseMaterialMF pigiron = addMaterial("PigIron",             0, 250,     0,        1.5F,       3 ,       1.00F);
	//public static BaseMaterialMF nickel =  addMaterial("Nickel", 		   -1, 300,     0,        1.0F,       6 ,       1.00F);
	
	public static BaseMaterialMF silver =  addMaterial("Silver", 		     -1, 300,     0,        0.5F,       10,       0.70F).setForgeStats(1, 1, 3F);
	public static BaseMaterialMF gold =    addMaterial("Gold", 	     	     -1, 150,     0,        0.0F,       25,       1.50F).setForgeStats(1, 1, 3F);
	public static BaseMaterialMF goldPure =    addMaterial("PureGold", 	     -1, 50 ,     0,        0.0F,       50,       2.00F).setRarity(1);
	public static BaseMaterialMF ornate =  addMaterial("Ornate", 		     -1, 500,     0,        1.0F,       30,       1.00F).setRarity(1).setForgeStats(1, 1, 4F);
	
	
	public static BaseMaterialMF copper		 = addMaterial("Copper",		  0, 250,   1,     1.5F, 1.0F,    5,        1.00F).setForgeStats(0, 0, 1.0F);
	//Tier 1
	public static BaseMaterialMF bronze    	 = addMaterial("Bronze", 	      1, 750,  	2,        1.5F,       8 ,       0.85F).setForgeStats(1, 1, 1.5F);
	public static BaseMaterialMF iron   	 = addMaterial("Iron",            1, 500,  	2,        2.0F,       5 ,       1.00F).setForgeStats(1, 1, 2.0F);
	//Tier 2
	public static BaseMaterialMF steel       = addMaterial("Steel",           2, 1000, 	2,        2.5F,       10,       1.00F).setForgeStats(2, 2, 2.5F);
	public static BaseMaterialMF encrusted   = addMaterial("Encrusted",       2, 2000, 	3,        3.0F,       12,       1.00F).setForgeStats(2, 2, 5.0F);
	//Tier 3
	public static BaseMaterialMF blacksteel  = addMaterial("BlackSteel",      3, 2500, 	4,        4.0F,       12,       1.00F).setForgeStats(3, 3, 4.0F);
	public static BaseMaterialMF dragonforge = addMaterial("Dragonforge",     3, 3000, 	4,        5.0F,       14,       1.00F).setForgeStats(3, 3, 8.0F).setRarity(1).setResistances(100F, 0F);
	//Tier 4
	public static BaseMaterialMF redsteel    = addMaterial("RedSteel",        4, 4000, 	5,        6.0F,       1 ,       1.15F).setForgeStats(4, 4, 6.5F).setResistances(20F, 0F);
	public static BaseMaterialMF bluesteel   = addMaterial("BlueSteel",       4, 3000, 	5,        5.0F,       20,       0.75F).setForgeStats(4, 4, 4.5F).setResistances(0F, 20F);
	//Tier 5
	public static BaseMaterialMF adamantium  = addMaterial("Adamantium",      5, 6500, 	6,        8.0F,       10 ,      1.25F).setForgeStats(5, 4, 9.0F).setRarity(1).setResistances(35F, 0F);
	public static BaseMaterialMF mithril     = addMaterial("Mithril",         5, 4500, 	6,        7.0F,       30,       0.50F).setForgeStats(5, 4, 6.0F).setRarity(1).setResistances(0F, 35F);
	//Tier 6
	public static BaseMaterialMF ignotumite  = addMaterial("Ignotumite",      6, 10000, 7,        10.0F,      20 ,      1.25F).setForgeStats(6, 4, 15.0F).setRarity(2).setResistances(50F, 0F);
	public static BaseMaterialMF mithium     = addMaterial("Mithium",         6, 10000, 7,  	  9.0F,       40,       0.50F).setForgeStats(6, 4, 15.0F).setRarity(2).setResistances(0F, 50F);
	//Tier 7
	public static BaseMaterialMF enderforge  = addMaterial("Ender",           7, 10000, 7,  	  12.0F,      40,       1.00F).setForgeStats(7, 4, 20.0F).setRarity(3).setResistances(25F, 85F);
	
	
	//MISC
	public static BaseMaterialMF weakblacksteel  = addMaterial("BlackSteelWeak",      -1, 250, 	4,        2.0F,       0,       1.00F);
	public static BaseMaterialMF weakredsteel    = addMaterial("RedSteelWeak",        -1, 400, 	5,        3.0F,       0,       1.1F);
	public static BaseMaterialMF weakbluesteel   = addMaterial("BlueSteelWeak",       -1, 300, 	5,        2.5F,       0,       0.9F);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CLASS START~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/**
	 * This method auto-calculates the Armour Rating to scale the damage
	 */
	public static BaseMaterialMF addArmourSpecificMaterial(String name, int tier, int durability, float AC, int enchantment, float weight)
	{
		return addMaterial(name, tier, durability, -1, AC, -1, enchantment, weight);
	}
	
	public static BaseMaterialMF addMaterial(String name, int tier, int durability, int harvestLevel, float sharpness, int enchantment, float weight)
	{
		float AC = ((sharpness+swordDam)/ratioScale - 1.0F);//With splint: swords(dam+4), do 2 damage, -1 because 1.0 means no armour bonus, as it scales from there
		float initAc = AC;
		
		AC = Math.round(AC*(100F/ACrounding));
		AC = AC/(100F/ACrounding);
		
		if(initAc != AC)
		{
			MFLogUtil.logDebug("Auto-Calculated ArmourRating for tier: " + name + ", modified to " + AC);
		}
		
		return addMaterial(name, tier, durability, harvestLevel, AC, sharpness, enchantment, weight);
	}
	
	public static BaseMaterialMF addMaterial(String name, int tier, int durability, int harvestLevel, float hardness, float sharpness, int enchantment, float weight)
	{
		return register(new BaseMaterialMF(name, tier, durability, harvestLevel, hardness, sharpness, enchantment, weight));
	}
	
	public static BaseMaterialMF register(BaseMaterialMF material)
	{
		materialMap.put(material.name, material);
		return material;
	}
	
	public String name;
	
	/**
	 * This variable determines max uses
	 */
	public int durability;
	/**
	 * This variable determines the protection
	 * : Hardness and sharpness are normally relevant to each other (so armour vs weapon equal tier has the same after-calculation damage)
	 */
	public float hardness;
	/**
	 * This variable determines damage and dig speed
	 * : Hardness and sharpness are normally relevant to each other (so armour vs weapon equal tier has the same after-calculation damage)
	 */
	public float sharpness;
	/**
	 * This variable determines enchantement
	 */
	public int enchantment;
	public float weight;
	
	//TIERING
	/**
	 * The tier the material is on
	 */
	public int tier;
	public int harvestLevel;
	
	//FORGING
	public int hammerTier;
	public int anvilTier;
	public float craftTimeModifier = 2.0F;
	
	//RESISTANCES
	public float fireResistance;
	public float arcaneResistance;
	public boolean isMythic = false;
	
	//SPECIALS
	private ArmourMaterialMF armourConversion;
	private ToolMaterial toolConversion;
	public int rarity;
	
	public BaseMaterialMF(String name, int tier, int durability, int harvestLevel, float hardness, float sharpness, int enchantment, float weight)
	{
		this.name = name;
		this.tier = tier;
		this.durability = durability;
		this.hardness = hardness;
		this.sharpness = sharpness;
		this.enchantment = enchantment;
		this.weight = weight;
		this.harvestLevel = harvestLevel;
	}
	
	protected BaseMaterialMF setForgeStats(int hammer, int anvil, float timer)
	{
		hammerTier = hammer;
		anvilTier = anvil;
		craftTimeModifier = (timer*2F) + 2.0F;
		return this;
	}
	protected BaseMaterialMF setResistances(float fire, float arcane)
	{
		fireResistance = fire;
		arcaneResistance = arcane;
		return this;
	}
	protected BaseMaterialMF setRarity(int value)
	{
		rarity = value;
		return this;
	}
	protected BaseMaterialMF setMythic()
	{
		isMythic = true;
		return this;
	}
	private ArmourMaterialMF convertToMFArmour()
	{
		return new ArmourMaterialMF(name, durability,  hardness,  enchantment,  weight).setFireResistance(fireResistance).setMagicResistance(arcaneResistance).setMythic(isMythic);
	}
	
	private ToolMaterial convertToTool()
	{
		return EnumHelper.addToolMaterial(name, harvestLevel, durability, 2.0F+(sharpness*2F), sharpness, enchantment);
	}
	
	private static ArmourMaterialMF getMFArmourMaterial(String name)
	{
		BaseMaterialMF material = materialMap.get(name);
		return material != null ? getMFArmourMaterial(material) : null;
	}
	public ArmourMaterialMF getArmourConversion()
	{
		if(armourConversion == null)
		{
			armourConversion = getMFArmourMaterial(this);
		}
		return armourConversion;
	}
	public static ArmourMaterialMF getMFArmourMaterial(BaseMaterialMF material)
	{
		return material.convertToMFArmour();
	}
	
	public static ToolMaterial getToolMaterial(String name)
	{
		BaseMaterialMF material = materialMap.get(name);
		return material != null ? getToolMaterial(material) : null;
	}
	public ToolMaterial getToolConversion()
	{
		if(toolConversion == null)
		{
			toolConversion = getToolMaterial(this);
		}
		return toolConversion;
	}
	
	public static ToolMaterial getToolMaterial(BaseMaterialMF material)
	{
		return material.convertToTool();
	}
	
	public static BaseMaterialMF getMaterial(String name)
	{
		return materialMap.get(name);
	}
}
