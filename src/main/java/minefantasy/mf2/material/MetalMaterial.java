package minefantasy.mf2.material;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.item.list.ComponentListMF;

public class MetalMaterial extends CustomMaterial
{
	public MetalMaterial(String name, int tier, float hardness, float durability, float flexibility, float sharpness, float resistance, float density) 
	{
		super(name, "metal", tier, hardness, durability, flexibility, resistance, sharpness, density);
		setArmourStats(1.0F, flexibility, 1F/flexibility);//Harder materials absorb blunt less but resist cutting and piercing more
	}
	
	/**
	 * Adds a new metal
	 * @param name The name, and register for the metal
	 * @param tier What tier the metal is
	 * @param hardness How hard the material is to break
	 * @param flexibility How well the metal can bend
	 * @param sharpness How well the metal can be sharpened
	 * @param resistance The resistance to elements such as fire and corrosion, right now its just 0-100 for armour resistance against elements
	 * @param density The overall mass (Kg) for an ingot
	 */
	public static CustomMaterial getOrAddMetal(String name, int tier, float durability, float flexibility, float sharpness, float resistance, float density, int red, int green, int blue)
	{
		if(getMaterial(name) != null)
		{
			return CustomMaterial.getMaterial(name);
		}
		float hardness = ((sharpness+5F)/2F)-1F;
		return new MetalMaterial(name, tier, hardness, durability, flexibility, sharpness, resistance, density).setColour(red, green, blue).register();
	}
	public static CustomMaterial getOrAddMetal(String name, int tier, float hardness, float durability, float flexibility, float sharpness, float resistance, float density, int red, int green, int blue)
	{
		if(getMaterial(name) != null)
		{
			return CustomMaterial.getMaterial(name);
		}
		return new MetalMaterial(name, tier, hardness, durability, flexibility, sharpness, resistance, density).setColour(red, green, blue).register();
	}
	
	public static void init()
	{
		/* BaseMaterialMF
		 *  WOOD(0, 59, 2.0F, 0.0F, 15),
        	STONE(1, 131, 4.0F, 1.0F, 5),
       		IRON(2, 250, 6.0F, 2.0F, 14),
        	EMERALD(3, 1561, 8.0F, 3.0F, 10),
        	GOLD(0, 32, 12.0F, 0.0F, 22);
		 */
		//Weak Mats    Name          	T  Dur     Flx 	 Shp   	Rst		 Wg
		getOrAddMetal("Gold", 		    0, 2/5F,   1.0F, 0.0F,  10F, 	5.0F, 		243, 222,  49);
		getOrAddMetal("Silver", 	    0, 2/5F,   1.0F, 0.0F,  10F, 	5.0F, 		155, 206, 205);
		getOrAddMetal("Tin", 		    0, 2/5F,   0.5F, 0.0F,  10F, 	2.0F, 		164, 177, 177).setRarity(-1);
		getOrAddMetal("Copper", 		0, 4/5F,   1.0F, 0.0F,  50F, 	3.0F, 		255, 132,  66);
		
		//Standard Mats  Name          	T  Dur     Flx 	 Shp   	Rst		 Wg
		getOrAddMetal("Bronze", 		0, 1.5F,   1.0F, 1.5F,  20F, 	3.0F, 		207, 165, 118).setCrafterTiers(1);
		getOrAddMetal("Iron", 			1, 2.0F,   1.0F, 2.0F,  20F,	2.5F, 		120,  95,  95).setCrafterTiers(2).setArmourStats(1.0F, 1.0F, 1.0F);//Standard
		getOrAddMetal("RefinedIron", 	1, 2.0F,   0.8F, 2.0F,  20F,	2.5F, 		120, 100, 110).setCrafterTiers(2).setArmourStats(1.1F, 0.8F, 1.1F);//Less Blunt for more cutting/piercing
		getOrAddMetal("Steel", 			2, 3.0F,   1.0F, 2.5F,  30F,	2.5F, 		168, 168, 168).setCrafterTiers(3);
		getOrAddMetal("Encrusted", 		3, 8.0F,   1.0F, 3.0F,  35F,	3.0F, 		64, 255, 255).setCrafterTiers(3).setArmourStats(1.0F, 1.0F, 1.2F);//Better Piercing
		getOrAddMetal("Obsidian", 	    3, 8.0F,   1.0F, 3.0F,  35F,	3.0F, 		90 , 45 , 90).setCrafterTiers(3).setArmourStats(1.0F, 1.2F, 1.0F);//Better Blunt
		getOrAddMetal("Tungsten",       3, 8.0F,   0.8F, 3.0F,  35F,	4.0F, 		66 , 86 , 65).setCrafterTiers(3).setArmourStats(1.2F, 1.0F, 1.0F);//Better Cutting
		getOrAddMetal("BlackSteel",     4, 6.0F,   1.0F, 4.0F,  50F,	3.0F, 		80 , 80 , 80).setCrafterTiers(4).setArmourStats(1.0F, 1.0F, 1.0F);//Standard
		getOrAddMetal("BlueSteel",      5, 7.0F,   1.2F, 5.0F,  65F,	2.0F, 		28,   31, 159).setCrafterTiers(5).setArmourStats(1.0F, 1.2F, 0.8F);//Better Blunt and Resist
		getOrAddMetal("RedSteel",       5, 8.0F,   0.8F, 6.0F,  50F,	4.0F, 		219,  56,  56).setCrafterTiers(5).setArmourStats(0.9F, 0.9F, 1.2F);//Better AC and Piercing
		
		
		//Mythic Mats  Name          	T  Dur     Flx 	 Shp   	Rst		 Wg
		getOrAddMetal("Mithril",        6, 10.0F,  0.8F, 7.0F,  90F,	1.5F, 		250, 180, 250).setCrafterTiers(6).setRarity(1);
		getOrAddMetal("Adamantium",     6, 12.0F,  0.8F, 8.0F,  80F,	4.5F, 		100, 200, 100).setCrafterTiers(6).setRarity(1);
		getOrAddMetal("Mithium",  	    7, 4.0F,   0.8F, 10.0F, 95F,	1.0F, 		60 , 200, 255).setCrafterTiers(7).setRarity(2);
		getOrAddMetal("Ignotumite",     7, 4.0F,   0.8F, 14.0F, 85F,	5.0F, 		20 , 255, 100).setCrafterTiers(7).setRarity(2);
		getOrAddMetal("Ender",          7, 4.0F,   0.8F, 12.0F, 90F,	4.0F, 		255, 63 , 243).setCrafterTiers(7).setRarity(2);
		
		//Non MF
		getOrAddMetal("StainlessSteel", 2, 5.0F,   1.0F, 2.5F,  40F,	2.5F, 		188, 188, 198).setCrafterTiers(3);
		getOrAddMetal("Titanium",       5, 10.0F,  1.0F, 4.0F,  50F,	1.0F, 		190, 190, 160).setCrafterTiers(3);
		getOrAddMetal("Thaumium", 		3, 4.0F,   1.0F, 3.0F,  65F,	2.5F, 		115, 65 , 183).setCrafterTiers(3);
		getOrAddMetal("Void", 		    3, 10.0F,  2.0F, 4.0F,  75F,	1.0F, 		75 , 45 , 75).setCrafterTiers(3);
		
		getOrAddMetal("Manasteel", 		1, 3.0F,   1.0F, 2.0F,  40F,	2.5F, 		88 , 195, 231).setArmourStats(1.0F, 1.2F, 1.0F).setCrafterTiers(2);//More Resistant, Stronger than iron
		getOrAddMetal("ElvenElementium",2, 4.0F,   1.0F, 2.5F,  40F,	2.5F, 		235, 108, 235).setArmourStats(1.1F, 1.0F, 1.1F).setCrafterTiers(2);//Similar to Manasteel, Slightly stronger
		getOrAddMetal("Terrasteel", 	3, 6.0F,   1.0F, 4.0F,  40F,	2.5F, 		88 , 250, 150).setArmourStats(1.0F, 0.9F, 1.0F).setCrafterTiers(3);
				
		//Alloy
		getOrAddMetal("CompositeAlloy", 5,  4.5F, 10.0F,  1.0F, 2.5F,  75F,	6.0F, 		65, 75 ,65).setRarity(1).setCrafterTiers(3);//Powerful All-Rounder
	}
	
	public static void addHeatables()
	{
		MineFantasyAPI.addHeatableItem(ComponentListMF.rivet, 100, 500, 1000);
		MineFantasyAPI.addHeatableItems("ingotCompositeAlloy", 180, 500, 1000);
		
		MineFantasyAPI.addHeatableItems("ingotThaumium", 150, 500, 1000);
		MineFantasyAPI.addHeatableItems("ingotTitanium", 200, 500, 1000);
		MineFantasyAPI.addHeatableItems("ingotStainlessSteel", 150, 500, 1000);
		MineFantasyAPI.addHeatableItems("ingotNickel", 120, 500, 1000);
		MineFantasyAPI.addHeatableItems("ingotThaumium", 150, 500, 1000);
		MineFantasyAPI.addHeatableItems("ingotVoid", 180, 600, 1000);
		
		MineFantasyAPI.addHeatableItems("ingotManasteel",  120, 500, 1000);
		MineFantasyAPI.addHeatableItems("ingotElementium", 130, 500, 1000);
		MineFantasyAPI.addHeatableItems("ingotTerrasteel", 160, 500, 1000);
	}
}
