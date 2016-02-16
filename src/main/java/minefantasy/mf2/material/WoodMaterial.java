package minefantasy.mf2.material;

import minefantasy.mf2.api.material.CustomMaterial;

public class WoodMaterial extends CustomMaterial
{
	public WoodMaterial(String name, int tier, float hardness, float durability, float flexibility, float sharpness, float resistance, float density) 
	{
		super(name, "wood", tier, hardness, durability, flexibility, sharpness, resistance, density);
	}
	
	public static CustomMaterial getOrAddWood(String name, int tier, float hardness, float durability, float flexibility, float sharpness, float resistance, float density, int red, int green, int blue)
	{
		if(getMaterial(name) != null)
		{
			return CustomMaterial.getMaterial(name);
		}
		return new WoodMaterial(name, tier, hardness, durability, flexibility, sharpness, resistance, density).setColour(red, green, blue).register();
	}

	public static void init()
	{
		MetalMaterial.init();
		LeatherMaterial.init();
		//Hardness-How well the wood withstands a beating. Proportional to old durability (divided by 1000)
		//Durability- How much force it takes to break the wood. Needed to change to make bow ratings work since CustomMaterial is a fixed constructor
		//Flexibility- how well the wood can bend
		//Sharpness- I left alone
		//Resistance- Score of how well the wood withstands rot and weathering
		//Density- The density of the wood
		
		
		//TIER 1
		getOrAddWood("OakWood", 		0,  7.14F, 120.0F, 13.99F, 0.5F, 4,  765, 	  149, 119,  70);
		getOrAddWood("SpruceWood", 		0,  2.18F,  66.0F, 10.76F, 0.5F, 2,  435,	  102,  79,  47);
		getOrAddWood("BirchWood", 		0,  5.61F, 114.5F, 13.86F, 0.5F, 1,  690,	  200, 183, 122);
		getOrAddWood("JungleWood", 		0,  4.74F,  97.1F, 12.28F, 0.5F, 5,  655,	  159, 113,  74);
		getOrAddWood("AcaciaWood", 		0,  5.18F,  87.0F, 10.37F, 0.5F, 2,  610,     173,  93,  50);
		getOrAddWood("DarkOakWood",     0, 12.92F, 125.6F, 13.52F, 0.5F, 5, 1000,      62,  41,  18);
		//TIER 2
		
		getOrAddWood("IronbarkWood", 	2,  9.60F, 123.8F, 11.80F, 1.0F, 5,  870,	  202,  92,  29);
		getOrAddWood("EbonyWood", 		3, 13.08F, 150.5F, 16.30F, 1.0F, 5,  987, 	   50,  46,  40);
		getOrAddWood("YewWood", 		3,  7.12F, 104.8F,  9.31F, 1.0F, 5,  705, 	  195,  138,  54);//Temp color, depends on the yew texture
		
		//Dunno what to do with this since it's not  real wood
		getOrAddWood("RefinedWood", 	1,   2.0F, 0.4F, 1.0F, 0.5F, 1.1F, 	2F,		   95,  40,  24);
		
		/*
		//OTHERS. playing around with, mainly forestry
		getOrAddWood("PineWood",		0,   1,    1,    1,    1,    1,      1,       189, 147,  63);
		getOrAddWood("CherryWood",      0,   1,    1,    1,    1,    1,      1,       162, 116,  47);
		getOrAddWood("PapayaWood",      0,   1,    1,    1,    1,    1,      1,       214, 194,  91);
		getOrAddWood("CitrusWood",      0,   1,    1,    1,    1,    1,      1,       142, 153,  27);
		getOrAddWood("PoplarWood",      0,   1,    1,    1,    1,    1,      1,       198, 198, 107);
		getOrAddWood("MapleWood",       0,   1,    1,    1,    1,    1,      1,       158,  99,  39);
		getOrAddWood("MahoeWood",       0,   1,    1,    1,    1,    1,      1,       111, 138, 158);
		getOrAddWood("GreenheartWood",  0,   1,    1,    1,    1,    1,      1,        65, 103,  77);
		getOrAddWood("PalmWood",        0,   1,    1,    1,    1,    1,      1,       195, 114,  56);
		getOrAddWood("LimeWood",        0,   1,    1,    1,    1,    1,      1,       190, 147,  96);
		getOrAddWood("BalsaWood",       0,   1,    1,    1,    1,    1,      1,       159, 152, 143);
		getOrAddWood("WalnutWood",      0,   1,    1,    1,    1,    1,      1,        85,  69,  55);
		getOrAddWood("KapokWood",		0,   1,    1,    1,    1,    1,      1,        99,  95,  45);
		getOrAddWood("ChestnutWood",	0,   1,    1,    1,    1,    1,      1,       156, 147,  74);
		getOrAddWood("BaobabWood",		0,   1,    1,    1,    1,    1,      1,       116, 126,  84);
		getOrAddWood("LarchWood",		0,   1,    1,    1,    1,    1,      1,       200, 143, 122);
		getOrAddWood("WillowWood",		0,   1,    1,    1,    1,    1,      1,       149, 152,  74);
		getOrAddWood("TeakWood",		0,   1,    1,    1,    1,    1,      1,       111, 107,  91);
		getOrAddWood("SequoiaWood",		0,   1,    1,    1,    1,    1,      1,       123,  81,  75);
		getOrAddWood("MahoganyWood",	0,   1,    1,    1,    1,    1,      1,        95,  56,  49);
		getOrAddWood("WengeWood",		0,   1,    1,    1,    1,    1,      1,        76,  72,  62);
		getOrAddWood("RedwoodWood",		0,   1,    1,    1,    1,    1,      1,       148, 104,  55);
		getOrAddWood("FirWood",		    0,   1,    1,    1,    1,    1,      1,       114, 111,  63);
		getOrAddWood("PurpleHeartWood",	0,   1,    1,    1,    1,    1,      1,        55,  25,  49);
		getOrAddWood("SilverwoodWood",	0,   1,    1,    1,    1,    1,      1,       224, 220, 208);
		getOrAddWood("GreatwoodWood",	0,   1,    1,    1,    1,    1,      1,        37,  25,  23);
		*/
	}
}
