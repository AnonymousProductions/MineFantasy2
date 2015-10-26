package minefantasy.mf2.entity.mob;

import java.util.ArrayList;
import java.util.Random;

import minefantasy.mf2.config.ConfigMobs;

public class DragonBreed 
{
	private static final ArrayList<DragonBreed>[]breeds = new ArrayList[]{new ArrayList<DragonBreed>(), new ArrayList<DragonBreed>(), new ArrayList<DragonBreed>(), new ArrayList<DragonBreed>(), new ArrayList<DragonBreed>()};
	
	/* Simple Breeds
	 * GREEN: Melee Based
	 * Red: Fire Based
	 * White: Ferocious Melee
	 * 
	 * Dire Breeds (Alternative to adults)
	 * BRONZE: Fire Based
	 * SILVER: Melee Based
	 * 
	 * Young dragons come first, in three breeds, reaching adulthood: Some become dire dragons turning bronze and silver
	 * The dire dragons can become elders, ancient dragons are created from only the best dragons with skills of both dire breeds.
	 */
	public static DragonBreed YOUNGGREEN = new DragonBreed(0, "dragon_young", "dragonGreenYoung", "Fire").setCombat(ConfigMobs.youngdragonHP,   0.0F, 0F, ConfigMobs.youngdragonMD, 35,   300).setProjectile(ConfigMobs.youngdragonFD, ConfigMobs.youngdragonFT, 1200, 0.05F).register();
	public static DragonBreed YOUNGRED = new DragonBreed(0, "dragon_young", "dragonRedYoung", "Fire").setCombat(ConfigMobs.youngdragonHP,       0.0F, 0F, ConfigMobs.youngdragonMD, 40,   300).setProjectile(ConfigMobs.youngdragonFD, ConfigMobs.youngdragonFT, 1000, 0.06F).register();
	public static DragonBreed YOUNGWHITE = new DragonBreed(0, "dragon_young", "dragonWhiteYoung", "Fire").setCombat(ConfigMobs.youngdragonHP,   0.0F, 0F, ConfigMobs.youngdragonMD, 30,   400).setProjectile(ConfigMobs.youngdragonFD, ConfigMobs.youngdragonFT, 1300, 0.02F).register();
	
	public static DragonBreed GREEN = new DragonBreed(1, "dragon", "dragonGreen", "Fire").setCombat(ConfigMobs.dragonHP, 	  2.0F, 0F,   ConfigMobs.dragonMD, 35, 200).setProjectile(ConfigMobs.dragonFD, ConfigMobs.dragonFT, 600, 0.075F).register();
	public static DragonBreed RED = new DragonBreed(1, "dragon", "dragonRed", "Fire").setCombat(ConfigMobs.dragonHP, 		  2.0F, 0F,   ConfigMobs.dragonMD, 40, 200).setProjectile(ConfigMobs.dragonFD, ConfigMobs.dragonFT, 500, 0.1F).register();
	public static DragonBreed WHITE = new DragonBreed(1, "dragon", "dragonWhite", "Fire").setCombat(ConfigMobs.youngdragonHP, 2.0F, 0F,   ConfigMobs.dragonMD, 30, 350).setProjectile(ConfigMobs.dragonFD, ConfigMobs.dragonFT, 700, 0.05F).register();
	
	public static DragonBreed BRONZE = new DragonBreed(2, "dragon_dire", "dragonBronze", "Fire").setCombat(ConfigMobs.diredragonHP, 2.0F, 1F,   ConfigMobs.diredragonMD, 40, 200).setProjectile(ConfigMobs.diredragonFD, ConfigMobs.diredragonFT, 400, 0.25F).register();
	public static DragonBreed SILVER = new DragonBreed(2, "dragon_dire", "dragonSilver", "Fire").setCombat(ConfigMobs.diredragonHP, 2.0F, 1F,   ConfigMobs.diredragonMD, 30, 300).setProjectile(ConfigMobs.diredragonFD, ConfigMobs.diredragonFT, 500, 0.1F).register();
	
	public static DragonBreed ELDERBRONZE = new DragonBreed(3, "dragon_elder", "dragonBronzeElder", "Fire").setCombat(ConfigMobs.elderdragonHP, 2.0F, 2F, ConfigMobs.elderdragonMD, 40, 200).setProjectile(ConfigMobs.elderdragonFD, ConfigMobs.elderdragonFT, 400, 0.35F).register();
	public static DragonBreed ELDERSILVER = new DragonBreed(3, "dragon_elder", "dragonSilverElder", "Fire").setCombat(ConfigMobs.diredragonHP,  2.0F, 2F, ConfigMobs.elderdragonMD, 30, 300).setProjectile(ConfigMobs.elderdragonFD, ConfigMobs.elderdragonFT, 500, 0.1F).register();
	
	public static DragonBreed ANCIENT = new DragonBreed(4, "dragon_ancient", "dragonAncient", "Fire").setCombat(ConfigMobs.ancientdragonHP, 2.0F, 5F, ConfigMobs.ancientdragonMD, 30, 200).setProjectile(ConfigMobs.ancientdragonFD, ConfigMobs.ancientdragonFT, 400, 1.0F).register();
	
	
	public int tier = 0;
	public String name = "dragon";
	public String tex = "dragonGreen";
	public String subspecies = "Fire";
	public float health = 200; 
	public float regenRate = 0;
	public float meleeDamage = 8;
	public float fireDamage = 5;
	public float pyro = 0.1F;
	public int disengageChance = 200;
	public int fireTimer = 50;
	public int coolTimer = 200;
	public int meleeSpeed = 30;
	public float DT = 2.0F;
	
	public DragonBreed(int tier, String name, String tex, String subspecies)
	{
		this.tier = tier;
		this.name = name;
		this.tex = tex;
		this.subspecies = subspecies;
	}
	public DragonBreed setCombat(float health, float DT, float regenRate, float meleeDamage, int meleeSpeed, int disengageChance)
	{
		this.health = health;
		this.regenRate = regenRate;
		this.meleeDamage = meleeDamage;
		this.meleeSpeed = meleeSpeed;
		this.disengageChance = disengageChance;
		this.DT = DT;
		return this;
	}
	public DragonBreed setProjectile(float fireDamage, int fireTimer, int coolTimer, float pyro)
	{
		this.fireDamage = fireDamage;
		this.fireTimer = fireTimer;
		this.coolTimer = coolTimer;
		this.pyro = pyro;
		return this;
	}
	public DragonBreed register()
	{
		breeds[tier].add(this);
		return this;
	}
	
	public static DragonBreed getBreed(int tier, int id)
	{
		DragonBreed breed = breeds[tier].get(id);
		return breed != null ? breed : breeds[tier].get(0);
	}
	private static Random rand = new Random();
	public static int getRandomDragon(int tier)
	{
		 ArrayList<DragonBreed> pool = breeds[tier];
		 if(pool.size() <= 1)
		 {
			 return 0;
		 }
		 return rand.nextInt(pool.size());
	}
}
