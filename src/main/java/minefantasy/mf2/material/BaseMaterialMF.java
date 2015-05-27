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
	public static BaseMaterialMF linen =  addArmourSpecificMaterial("Linen",   	        0, 10 ,          0.1F,        0,        1.00F, 0);
	public static BaseMaterialMF wool =  addArmourSpecificMaterial("Wool"  ,   	        1, 15 ,          0.15F,       0,        1.00F, 5);
	
	public static BaseMaterialMF hide =  addArmourSpecificMaterial("Hide",   	     	   0, 20 ,          0.2F,        0,        1.00F, 0);
	public static BaseMaterialMF rough = addArmourSpecificMaterial("RoughLeather",    	   0, 35 ,          0.3F,        1,        1.00F, 0);
	public static BaseMaterialMF reinforced = addArmourSpecificMaterial("StrongLeather",   1, 250,          1.8F,        1,        1.00F, 5);
	public static BaseMaterialMF studded = addArmourSpecificMaterial("StudLeather",        1, 500,          2.4F,        5,        1.20F, 15);
	public static BaseMaterialMF scaled = addArmourSpecificMaterial("ScaleLeather",        2, 1000,         2.7F,        8,        1.50F, 25);
	public static BaseMaterialMF dragonscale = addArmourSpecificMaterial("Dragonscale",    3, 3000,         8.0F,        20,       1.20F, 85).setRarity(2);
	
	//                                                   name    		    Tier dura,    harvest   sharpness   enchant   weight
	public static BaseMaterialMF stone =   addMaterial("Stone",		          0, 60 ,     0,     0.1F, 0.0F,    0,        2.00F, 0).setForgeStats(0, 0, 0.75F);
	public static BaseMaterialMF tin  =    addMaterial("Tin",   	          0, 100,     0,        0.2F,       5 ,       0.80F, 0);
	public static BaseMaterialMF pigiron = addMaterial("PigIron",             0, 250,     0,        1.5F,       3 ,       1.00F, 0);
	public static BaseMaterialMF silver =  addMaterial("Silver", 		     -1, 500,     0,        0.0F,       10,       0.70F, 0).setForgeStats(1, 1, 3F);
	public static BaseMaterialMF gold =    addMaterial("Gold", 	     	     -1, 150,     0,        0.0F,       25,       1.50F, 0).setForgeStats(1, 1, 3F);
	public static BaseMaterialMF goldPure =    addMaterial("PureGold", 	     -1, 50 ,     0,        0.0F,       50,       2.00F, 0).setRarity(1);
	public static BaseMaterialMF ornate =  addMaterial("Ornate", 		     -1, 500,     0,        0.0F,       30,       1.00F, 30).setRarity(1).setForgeStats(1, 1, 4F);
	
	
	//Basic / Common Materials (0-2) Levels 0-50
	public static BaseMaterialMF copper		 = addMaterial("Copper",		  0, 250,   1,     1.5F, 1.0F,    5,        1.00F, 0).setForgeStats(0, 0, 1.0F); //lvl 0
	public static BaseMaterialMF bronze    	 = addMaterial("Bronze", 	      1, 500,  	2,        1.5F,       5 ,       1.00F, 5).setForgeStats(1, 1, 2.5F); //lvl 5
	public static BaseMaterialMF iron   	 = addMaterial("Iron",            2, 600,  	2,        2.0F,       5 ,       1.00F, 15).setForgeStats(2, 2, 2.0F); //lvl 15
	public static BaseMaterialMF steel       = addMaterial("Steel",           3, 1000, 	2,        2.5F,       10,       1.00F, 25).setForgeStats(3, 3, 2.5F); //lvl 25
	public static BaseMaterialMF encrusted   = addMaterial("Encrusted",       3, 2500, 	3,        3.0F,       18,       1.00F, 35).setForgeStats(3, 3, 5.0F); //lvl 30
	
	//Advanced Materials (3 - 4) Levels 50-75
	public static BaseMaterialMF blacksteel  = addMaterial("BlackSteel",      4, 2500, 	4,        4.0F,       12,       1.00F, 40).setForgeStats(4, 4, 4.0F);//lvl 40
	public static BaseMaterialMF dragonforge = addMaterial("Dragonforge",     4, 3000, 	4,        5.0F,       14,       1.00F, 55).setForgeStats(4, 4, 8.0F).setRarity(1).setResistances(100F, 0F);//lvl 55
	public static BaseMaterialMF redsteel    = addMaterial("RedSteel",        5, 4000, 	5,        6.0F,       1 ,       1.15F, 65).setForgeStats(5, 5, 6.5F).setResistances(20F, 0F);//lvl 65
	public static BaseMaterialMF bluesteel   = addMaterial("BlueSteel",       5, 3000, 	5,        5.0F,       20,       0.75F, 65).setForgeStats(5, 5, 4.5F).setResistances(0F, 20F);//lvl 65
	
	//Mythic Materials (5) Levels 75-100
	public static BaseMaterialMF adamantium  = addMaterial("Adamantium",      6, 6500, 	6,        8.0F,       10 ,      1.25F, 90).setForgeStats(6, 5, 9.0F).setRarity(1).setResistances(35F, 0F);//lvl 90
	public static BaseMaterialMF mithril     = addMaterial("Mithril",         6, 4500, 	6,        7.0F,       30,       0.50F, 90).setForgeStats(6, 5, 6.0F).setRarity(1).setResistances(0F, 35F);//lvl 90
	
	//Masterwork Materials (6) Level 100
	public static BaseMaterialMF ignotumite  = addMaterial("Ignotumite",      7, 10000, 7,        14.0F,      20 ,      2.00F, 100).setForgeStats(7, 5, 15.0F).setRarity(2).setResistances(50F, 0F);//High damage, heavy, fire resist lvl 100
	public static BaseMaterialMF mithium     = addMaterial("Mithium",         7, 10000, 7,  	  10.0F,      40,       0.25F, 100).setForgeStats(7, 5, 15.0F).setRarity(2).setResistances(0F, 50F);//Low damage, light, magic resist lvl 100
	public static BaseMaterialMF enderforge  = addMaterial("Ender",           7, 10000, 7,  	  12.0F,      20,       1.00F, 100).setForgeStats(7, 5, 15.0F).setRarity(2).setResistances(25F, 25F);//Middle                         lvl 100
	
	
	//MISC
	public static BaseMaterialMF weakblacksteel  = addMaterial("BlackSteelWeak",      -1, 250, 	4,        2.0F,       0,       1.00F, 40);
	public static BaseMaterialMF weakredsteel    = addMaterial("RedSteelWeak",        -1, 400, 	5,        3.0F,       0,       1.1F, 65);
	public static BaseMaterialMF weakbluesteel   = addMaterial("BlueSteelWeak",       -1, 300, 	5,        2.5F,       0,       0.9F, 65);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CLASS START~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/**
	 * This method auto-calculates the Armour Rating to scale the damage
	 */
	public static BaseMaterialMF addArmourSpecificMaterial(String name, int tier, int durability, float AC, int enchantment, float weight, int lvl)
	{
		return addMaterial(name, tier, durability, -1, AC, -1, enchantment, weight, lvl);
	}
	
	public static BaseMaterialMF addMaterial(String name, int tier, int durability, int harvestLevel, float sharpness, int enchantment, float weight, int lvl)
	{
		float AC = ((sharpness+swordDam)/ratioScale - 1.0F);//With mail: swords(dam+4), do 2 damage, -1 because 1.0 means no armour bonus, as it scales from there
		float initAc = AC;
		
		AC = Math.round(AC*(100F/ACrounding));
		AC = AC/(100F/ACrounding);
		
		if(initAc != AC)
		{
			MFLogUtil.logDebug("Auto-Calculated ArmourRating for tier: " + name + ", modified to " + AC);
		}
		
		return addMaterial(name, tier, durability, harvestLevel, AC, sharpness, enchantment, weight, lvl);
	}
	
	public static BaseMaterialMF addMaterial(String name, int tier, int durability, int harvestLevel, float hardness, float sharpness, int enchantment, float weight, int lvl)
	{
		return register(new BaseMaterialMF(name, tier, durability, harvestLevel, hardness, sharpness, enchantment, weight, lvl));
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
	
	/**
	 * The required level to craft
	 */
	public int requiredLevel;
	
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
	
	public BaseMaterialMF(String name, int tier, int durability, int harvestLevel, float hardness, float sharpness, int enchantment, float weight, int lvl)
	{
		this.requiredLevel = lvl;
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
