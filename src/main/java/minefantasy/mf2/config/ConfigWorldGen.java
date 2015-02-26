package minefantasy.mf2.config;

import minefantasy.mf2.mechanics.worldGen.WorldGenMFBase;

public class ConfigWorldGen extends ConfigurationBaseMF
{
	public static String copper = "[Ore Gen] Copper Ore";
	public static float copperRarity;
	public static int copperFrequencyMin;
	public static int copperFrequencyMax;
	public static int copperLayerMin;
	public static int copperLayerMax;
	public static int copperSize;
	
	
	public static String tin = "[Ore Gen] Tin Ore";
	public static float tinRarity;
	public static int tinFrequencyMin;
	public static int tinFrequencyMax;
	public static int tinLayerMin;
	public static int tinLayerMax;
	public static int tinSize;
	
	
	public static String silver = "[Ore Gen] Silver Ore";
	public static float silverRarity;
	public static int silverFrequencyMin;
	public static int silverFrequencyMax;
	public static int silverLayerMin;
	public static int silverLayerMax;
	public static int silverSize;
	
	
	public static String mythic = "[Ore Gen] Mythic Ore";
	public static float mythicRarity;
	public static int mythicFrequencyMin;
	public static int mythicFrequencyMax;
	public static int mythicLayerMin;
	public static int mythicLayerMax;
	public static int mythicSize;
	
	
	public static String kaolinite = "[Mineral Gen] Kaolinite Deposit";
	public static float kaoliniteRarity;
	public static int kaoliniteFrequencyMin;
	public static int kaoliniteFrequencyMax;
	public static int kaoliniteLayerMin;
	public static int kaoliniteLayerMax;
	public static int kaoliniteSize;
	
	public static String clay = "[Mineral Gen] Clay Deposit";
	public static float clayRarity;
	public static int clayFrequencyMin;
	public static int clayFrequencyMax;
	public static int clayLayerMin;
	public static int clayLayerMax;
	public static int claySize;
	
	public static String nitre = "[Mineral Gen] Nitre Deposit";
	public static float nitreRarity;
	public static int nitreFrequencyMin;
	public static int nitreFrequencyMax;
	public static int nitreLayerMin;
	public static int nitreLayerMax;
	public static int nitreSize;
	
	public static String sulfur = "[Mineral Gen] Sulfur Deposit";
	public static float sulfurRarity;
	public static int sulfurFrequencyMin;
	public static int sulfurFrequencyMax;
	public static int sulfurLayerMin;
	public static int sulfurLayerMax;
	public static int sulfurSize;
	
	public static String borax = "[Mineral Gen] Borax Deposit";
	public static float boraxRarity;
	public static int boraxFrequencyMin;
	public static int boraxFrequencyMax;
	public static int boraxLayerMin;
	public static int boraxLayerMax;
	public static int boraxSize;
	
	
	public static String limestone = "[Rock Gen] Limestone";
	public static float limestoneRarity;
	public static int limestoneFrequencyMax;
	public static int limestoneFrequencyMin;
	public static int limestoneLayerMax;
	public static int limestoneLayerMin;
	
	public static String berry = "[Plant Gen] Berry Bush";
	public static float berryRarity;
	public static float berryMinTemp;
	public static float berryMaxTemp;
	public static float berryMinRain;
	public static float berryMaxRain;
	
	
	@Override
	protected void loadConfig()
	{
		WorldGenMFBase.generatorLayer = config.get("##World Gen Layer##","Generation layer", "MineFantasy2", "This is what 'Layer' generation is: You shouldn't ever need to change this, it recognises new chunks itself but change this if you want a second run of world gen. Only change this if you want world gen to generate twice").getString();
		
		copperRarity = Float.parseFloat(config.get(copper, "Copper Rarity", 1.0F, "The chance for copper to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any copper").getString());
		copperFrequencyMin = Integer.parseInt(config.get(copper, "Copper Frequency Min", 5, "Copper will try spawn between this and max veins per chunk").getString());
		copperFrequencyMax = Integer.parseInt(config.get(copper, "Copper Frequency Max", 5, "Copper will try spawn between min and this veins per chunk").getString());
		copperLayerMin = Integer.parseInt(config.get(copper, "Copper Layer Min", 48, "Copper veins spawn above this layer").getString());
		copperLayerMax = Integer.parseInt(config.get(copper, "Copper Layer Max", 96, "Copper veins spawn below this layer").getString());
		copperSize = Integer.parseInt(config.get(copper, "Copper Size", 8, "How many blocks consist of the vein").getString());
		
		tinRarity = Float.parseFloat(config.get(tin, "Tin Rarity", 1.0F, "The chance for copper to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any copper").getString());
		tinFrequencyMin = Integer.parseInt(config.get(tin, "Tin Frequency Min", 2, "Tin will try spawn between this and max veins per chunk").getString());
		tinFrequencyMax = Integer.parseInt(config.get(tin, "Tin Frequency Max", 3, "Tin will try spawn between min and this veins per chunk").getString());
		tinLayerMin = Integer.parseInt(config.get(tin, "Tin Layer Min", 48, "Tin veins spawn above this layer").getString());
		tinLayerMax = Integer.parseInt(config.get(tin, "Tin Layer Max", 96, "Tin veins spawn below this layer").getString());
		tinSize = Integer.parseInt(config.get(tin, "Tin Size", 8, "How many blocks consist of the vein").getString());
		
		silverRarity = Float.parseFloat(config.get(silver, "Silver Rarity", 1.0F, "The chance for silver to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any ").getString());
		silverFrequencyMin = Integer.parseInt(config.get(silver, "Silver Frequency Min", 2, "Silver will try spawn between this and max veins per chunk").getString());
		silverFrequencyMax = Integer.parseInt(config.get(silver, "Silver Frequency Max", 3, "Silver will try spawn between min and this veins per chunk").getString());
		silverLayerMin = Integer.parseInt(config.get(silver, "Silver Layer Min", 0, "Silver veins spawn above this layer").getString());
		silverLayerMax = Integer.parseInt(config.get(silver, "Silver Layer Max", 32, "Silver veins spawn below this layer").getString());
		silverSize = Integer.parseInt(config.get(silver, "Silver Size", 8, "How many blocks consist of the vein").getString());
		
		mythicRarity = Float.parseFloat(config.get(mythic, "Mythic Rarity", 0.05, "The chance for mythic to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any mythic").getString());
		mythicFrequencyMin = Integer.parseInt(config.get(mythic, "Mythic Frequency Min", 2, "Mythic will try spawn between this and max veins per chunk").getString());
		mythicFrequencyMax = Integer.parseInt(config.get(mythic, "Mythic Frequency Max", 5, "Mythic will try spawn between min and this veins per chunk").getString());
		mythicLayerMin = Integer.parseInt(config.get(mythic, "Mythic Layer Min", 4, "Mythic veins spawn above this layer").getString());
		mythicLayerMax = Integer.parseInt(config.get(mythic, "Mythic Layer Max", 6, "Mythic veins spawn below this layer").getString());
		mythicSize = Integer.parseInt(config.get(mythic, "Mythic Size", 8, "How many blocks consist of the vein").getString());
		
		
		kaoliniteRarity = Float.parseFloat(config.get(kaolinite, "Kaolinite Rarity", 0.25F, "The chance for kaolinite to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any kaolinite").getString());
		kaoliniteFrequencyMin = Integer.parseInt(config.get(kaolinite, "Kaolinite Frequency Min", 1, "Kaolinite will try spawn between this and max deposits per chunk").getString());
		kaoliniteFrequencyMax = Integer.parseInt(config.get(kaolinite, "Kaolinite Frequency Max", 1, "Kaolinite will try spawn between min and this deposits per chunk").getString());
		kaoliniteLayerMin = Integer.parseInt(config.get(kaolinite, "Kaolinite Layer Min", 48, "Kaolinite deposits spawn above this layer").getString());
		kaoliniteLayerMax = Integer.parseInt(config.get(kaolinite, "Kaolinite Layer Max", 72, "Kaolinite deposits spawn below this layer").getString());
		kaoliniteSize = Integer.parseInt(config.get(kaolinite, "Kaolinite Size", 16, "How many blocks consist of the deposit").getString());
		
		clayRarity = Float.parseFloat(config.get(clay, "Clay Rarity", 0.15F, "The chance for clay to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any clay").getString());
		clayFrequencyMin = Integer.parseInt(config.get(clay, "Clay Frequency Min", 1, "Clay will try spawn between this and max deposits per chunk").getString());
		clayFrequencyMax = Integer.parseInt(config.get(clay, "Clay Frequency Max", 1, "Clay will try spawn between min and this deposits per chunk").getString());
		clayLayerMin = Integer.parseInt(config.get(clay, "Clay Layer Min", 60, "Clay deposits spawn above this layer").getString());
		clayLayerMax = Integer.parseInt(config.get(clay, "Clay Layer Max", 68, "Clay deposits spawn below this layer").getString());
		claySize = Integer.parseInt(config.get(clay, "Clay Size", 32, "How many blocks consist of the deposit").getString());
		
		nitreRarity = Float.parseFloat(config.get(nitre, "Nitre Rarity", 1.0F, "The chance for nitre to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any nitre").getString());
		nitreFrequencyMin = Integer.parseInt(config.get(nitre, "Nitre Frequency Min", 5, "Nitre will try spawn between this and max deposits per chunk").getString());
		nitreFrequencyMax = Integer.parseInt(config.get(nitre, "Nitre Frequency Max", 10, "Nitre will try spawn between min and this deposits per chunk").getString());
		nitreLayerMin = Integer.parseInt(config.get(nitre, "Nitre Layer Min", 16, "Nitre deposits spawn above this layer").getString());
		nitreLayerMax = Integer.parseInt(config.get(nitre, "Nitre Layer Max", 64, "Nitre deposits spawn below this layer").getString());
		nitreSize = Integer.parseInt(config.get(nitre, "Nitre Size", 12, "How many blocks consist of the deposit").getString());
		
		sulfurRarity = Float.parseFloat(config.get(sulfur, "Sulfur Rarity", 1.0F, "The chance for sulfur to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any sulfur").getString());
		sulfurFrequencyMin = Integer.parseInt(config.get(sulfur, "Sulfur Frequency Min", 5, "Sulfur will try spawn between this and max deposits per chunk").getString());
		sulfurFrequencyMax = Integer.parseInt(config.get(sulfur, "Sulfur Frequency Max", 10, "Sulfur will try spawn between min and this deposits per chunk").getString());
		sulfurLayerMin = Integer.parseInt(config.get(sulfur, "Sulfur Layer Min", 0, "Sulfur deposits spawn above this layer").getString());
		sulfurLayerMax = Integer.parseInt(config.get(sulfur, "Sulfur Layer Max", 16, "Sulfur deposits spawn below this layer").getString());
		sulfurSize = Integer.parseInt(config.get(sulfur, "Sulfur Size", 4, "How many blocks consist of the deposit").getString());
		
		boraxRarity = Float.parseFloat(config.get(borax, "Borax Rarity", 0.1F, "The chance for borax to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any borax").getString());
		boraxFrequencyMin = Integer.parseInt(config.get(borax, "Borax Frequency Min", 5, "Borax will try spawn between this and max deposits per chunk").getString());
		boraxFrequencyMax = Integer.parseInt(config.get(borax, "Borax Frequency Max", 10, "Borax will try spawn between min and this deposits per chunk").getString());
		boraxLayerMin = Integer.parseInt(config.get(borax, "Borax Layer Min", 48, "Borax deposits spawn above this layer").getString());
		boraxLayerMax = Integer.parseInt(config.get(borax, "Borax Layer Max", 96, "Borax deposits spawn below this layer").getString());
		boraxSize = Integer.parseInt(config.get(borax, "Borax Size", 8, "How many blocks consist of the deposit").getString());
		
		limestoneRarity = Float.parseFloat(config.get(limestone, "Limestone Rarity", 0.05F, "The chance for limestone to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any limestone").getString());
		limestoneFrequencyMin = Integer.parseInt(config.get(limestone, "Limestone Frequency Min", 1, "Limestone will try spawn between this and max deposits per chunk").getString());
		limestoneFrequencyMax = Integer.parseInt(config.get(limestone, "Limestone Frequency Max", 1, "Limestone will try spawn between min and this deposits per chunk").getString());
		limestoneLayerMin = Integer.parseInt(config.get(limestone, "Limestone Layer Min", 48, "Limestone deposits spawn above this layer").getString());
		limestoneLayerMax = Integer.parseInt(config.get(limestone, "Limestone Layer Max", 96, "Limestone deposits spawn below this layer").getString());
		
		berryRarity = Float.parseFloat(config.get(berry, "Berry Bush Rarity", 0.15F, "The chance for berry bushes to spawn in a chunk. (0=never, 1.0=always), this means some chunks may not have any berries").getString());
		berryMinTemp = Float.parseFloat(config.get(berry, "Berry Bush Spawn Temp Min", 0.2F, "The minimal biome temperature berries can spawn").getString());
		berryMaxTemp = Float.parseFloat(config.get(berry, "Berry Bush Spawn Temp Max", 1.0F, "The maximum biome temperature berries can spawn").getString());
		berryMinRain = Float.parseFloat(config.get(berry, "Berry Bush Spawn Rain Min", 0.3F, "The minimal biome rainfall berries can spawn").getString());
		berryMaxRain = Float.parseFloat(config.get(berry, "Berry Bush Spawn Rain Max", 1.0F, "The maximum biome rainfall berries can spawn").getString());
	}

}
