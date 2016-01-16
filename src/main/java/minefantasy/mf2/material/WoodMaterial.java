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
		//TIER 1
		getOrAddWood("OakWood", 		0, 1.0F, 0.2F, 1.0F, 0.5F, 1.0F, 1F, 		149, 119,  70);
		getOrAddWood("AcaciaWood", 		0, 1.0F, 0.2F, 1.0F, 0.5F, 1.1F, 1F, 		173,  93,  50);
		getOrAddWood("BirchWood", 		0, 1.0F, 0.2F, 1.0F, 0.5F, 0.8F, 0.75F,	200, 183, 122);
		getOrAddWood("JungleWood", 		0, 1.0F, 0.2F, 1.0F, 0.5F, 1.2F, 1.25F,	159, 113,  74);
		getOrAddWood("SpruceWood", 		0, 1.0F, 0.2F, 1.0F, 0.5F, 0.9F, 1F,		102,  79,  47);
		
		//TIER 2
		getOrAddWood("RefinedWood", 	1, 2.0F, 0.4F, 1.0F, 0.5F, 1.1F, 	2F,		95,  40,   24);
		getOrAddWood("IronbarkWood", 	2, 3.0F, 1.0F, 1.5F, 1.0F, 1.4F, 	3F,		202, 92,   29);
		getOrAddWood("EbonyWood", 		3, 4.0F, 1.5F, 1.5F, 1.0F, 1.5F, 	3F, 	33,  20,   38);
	}
}
