package minefantasy.mf2.material;

import minefantasy.mf2.api.material.CustomMaterial;

public class WoodMaterial extends CustomMaterial
{
	public WoodMaterial(String name, int tier, float hardness, float flexibility, float sharpness, float weight) 
	{
		super(name, "wood", tier, hardness, flexibility, sharpness, weight);
	}
	
	public static CustomMaterial getOrAddWood(String name, int tier, float hardness, float flexibility, float sharpness, float weight, int red, int green, int blue)
	{
		if(getMaterial(name) != null)
		{
			return CustomMaterial.getMaterial(name);
		}
		return new WoodMaterial(name, tier, hardness, flexibility, sharpness, weight).setColour(red, green, blue).register();
	}
	
	public static void init()
	{
		MetalMaterial.init();
		LeatherMaterial.init();
		
		getOrAddWood("OakWood", 		0, 1.0F, 1.0F, 0.5F, 1.0F, 		149, 119,  70);
		getOrAddWood("AcaciaWood", 		0, 1.0F, 1.0F, 0.5F, 1.1F, 		173,  93,  50);
		getOrAddWood("BirchWood", 		0, 1.0F, 1.0F, 0.5F, 0.8F, 		200, 183, 122);
		getOrAddWood("JungleWood", 		0, 1.0F, 1.0F, 0.5F, 1.2F, 		159, 113,  74);
		getOrAddWood("SpruceWood", 		0, 1.0F, 1.0F, 0.5F, 0.9F, 		102,  79,  47);
		
		getOrAddWood("RefinedWood", 	1, 2.0F, 1.0F, 0.5F, 1.1F, 		95,  40,   24);
		getOrAddWood("IronbarkWood", 	2, 3.0F, 1.5F, 1.0F, 1.4F, 		202, 92,   29);
		getOrAddWood("EbonyWood", 		3, 4.0F, 1.5F, 1.0F, 1.5F, 		33,  20,   38);
	}
}
