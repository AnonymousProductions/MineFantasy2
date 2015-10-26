package minefantasy.mf2.config;

import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.mechanics.CombatMechanics;

public class ConfigMobs extends ConfigurationBaseMF
{
	public static final String BASIC = "Basic Entities";
	public static int entityID;
	public static final String MOB_DRAGON = "Dragon";
	//public static int dragonID;
	public static int youngdragonHP, dragonHP, diredragonHP, elderdragonHP, ancientdragonHP;
	public static int youngdragonMD, dragonMD, diredragonMD, elderdragonMD, ancientdragonMD;
	public static int youngdragonFD, dragonFD, diredragonFD, elderdragonFD, ancientdragonFD;
	public static int youngdragonFT, dragonFT, diredragonFT, elderdragonFT, ancientdragonFT;
	public static float dragonChance;
	public static int dragonInterval;
	public static boolean dragonKillNPC;
	public static boolean dragonGriefFire;
	public static boolean dragonGriefGeneral;
	public static boolean dragonMSG;
	
	public static final String MOB_MINOTAUR = "Minotaur";
	//public static int dragonID;
	public static int minotaurHP;
	public static int minotaurMD;
	public static int minotaurGD;
	public static int minotaurBD, minotaurDC, minotaurGC, minotaurTC;
	public static int minotaurSpawnrate, minotaurSpawnrateNether;
	
	@Override
	protected void loadConfig()
	{
		entityID =  Integer.parseInt(config.get(BASIC, "1: Basic Entity ID", -1, "Where MF Entities start (Each entity adds 1) set to -1 for auto-assign").getString());
		
		//DragonBreed
		//dragonID =  Integer.parseInt(config.get(MOB_DRAGON, "1A: Dragon ID", 200, "The ID For dragons").getString());
		
		youngdragonHP =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Aa: Health", 60, "Young Dragon Stats").getString());
		youngdragonMD =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Ab: Bite dmg", 4).getString());
		youngdragonFD =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Ab: Fire dmg", 2).getString());
		youngdragonFT =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Ac: Fire time", 10).getString());
		
		dragonHP =  Integer.parseInt(config.get(MOB_DRAGON,		 	"2Ba: Health", 100, "Adult Dragon Stats").getString());
		dragonMD =  Integer.parseInt(config.get(MOB_DRAGON, 		"2Bb: Bite dmg", 7).getString());
		dragonFD =  Integer.parseInt(config.get(MOB_DRAGON, 		"2Bb: Fire dmg", 5).getString());
		dragonFT =  Integer.parseInt(config.get(MOB_DRAGON, 		"2Bc: Fire time", 40).getString());
		
		diredragonHP =  Integer.parseInt(config.get(MOB_DRAGON,  	"2Ca: Health", 200, "Dire Dragon Stats").getString());
		diredragonMD =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Cb: Bite dmg", 8).getString());
		diredragonFD =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Cb: Fire dmg", 8).getString());
		diredragonFT =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Cc: Fire time", 40).getString());
		
		elderdragonHP =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Da: Health", 500, "Elder Dragon Stats").getString());
		elderdragonMD =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Db: Bite dmg", 14).getString());
		elderdragonFD =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Db: Fire dmg", 10).getString());
		elderdragonFT =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Dc: Fire time", 50).getString());
		
		ancientdragonHP =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Ea: Health", 1000, "Ancient Dragon Stats").getString());
		ancientdragonMD =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Eb: Bite dmg", 20).getString());
		ancientdragonFD =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Eb: Fire dmg", 10).getString());
		ancientdragonFT =  Integer.parseInt(config.get(MOB_DRAGON, 	"2Ec: Fire time", 100).getString());
		
		
		dragonInterval =  Integer.parseInt(config.get(MOB_DRAGON, "3A: Dragon Spawn Interval", 6000, "How many ticks between visits (6000 means 4 times a day), there is a chance for a dragon each time").getString());		
		dragonChance =  Float.parseFloat(config.get(MOB_DRAGON, "3B: Spawn Chance", 5F, "A Percent (0-100) chance that a dragon spawns at set times").getString());
		
		dragonKillNPC =  Boolean.parseBoolean(config.get(MOB_DRAGON, "4A: Kill NPC Grief", true, "Should dragons kill NPCs (including villages as well as animals/mobs)... Not as determined though").getString());
		dragonGriefFire =  Boolean.parseBoolean(config.get(MOB_DRAGON, "4B: Fire Grief", true, "Should fire breath start fires").getString());
		dragonGriefGeneral =  Boolean.parseBoolean(config.get(MOB_DRAGON, "4C: General Block Grief", true, "Should glass be broken by fire breath AND stomping").getString());
		dragonMSG =  Boolean.parseBoolean(config.get(MOB_DRAGON, "4D: Spawn Message", false, "Will players get a message when dragons enter/leave").getString());
	
		minotaurHP =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"5Aa: Health", 50, "Minotaur Stats").getString());
		minotaurMD =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"5Ab: Pound dmg", 6).getString());
		minotaurGD =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"5Ac: Gore dmg", 6).getString());
		minotaurBD =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"5Ad: Beserk dmg", 8).getString());
		minotaurDC =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"5Ae: Disarm chance (When blocking Gore)", 5).getString());
		minotaurGC =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"5Af: Beserk Grab Chance", 20).getString());
		minotaurTC =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"5Ag: Beserk Throw Chance", 20).getString());
		
		minotaurSpawnrate  =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"6A: Spawnrate", 5, "Minotaur Spawn Rate").getString());
		minotaurSpawnrateNether  =  Integer.parseInt(config.get(MOB_MINOTAUR, 	"6B: Nether Spawnrate", 25, "Minotaur Spawn Rate (Nether)").getString());
	}

}
