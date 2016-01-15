package minefantasy.mf2.material;

import minefantasy.mf2.api.material.CustomMaterial;

public class MetalMaterial extends CustomMaterial
{
	public MetalMaterial(String name, int tier, float hardness, float flexibility, float sharpness, float weight) 
	{
		super(name, "metal", tier, hardness, flexibility, sharpness, weight);
	}
	
	public static CustomMaterial getOrAddWood(String name, int tier, float hardness, float flexibility, float sharpness, float weight, int red, int green, int blue)
	{
		if(getMaterial(name) != null)
		{
			return CustomMaterial.getMaterial(name);
		}
		return new MetalMaterial(name, tier, hardness, flexibility, sharpness, weight).setColour(red, green, blue).register();
	}
	
	public static void init()
	{
		/*
		 *  WOOD(0, 59, 2.0F, 0.0F, 15),
        	STONE(1, 131, 4.0F, 1.0F, 5),
       		IRON(2, 250, 6.0F, 2.0F, 14),
        	EMERALD(3, 1561, 8.0F, 3.0F, 10),
        	GOLD(0, 32, 12.0F, 0.0F, 22);
		 */
		//            Name          T  Hds     Flx Shp    Wgt
		getOrAddWood("Copper", 		0, 0.5F,  1.5F, 0.0F,  2.0F, 		255, 132,  66);
		getOrAddWood("Tin", 		0, 0.4F,  1.0F, 0.0F,  2.0F, 		164, 177, 177);
		getOrAddWood("Bronze", 		1, 0.8F,  1.0F, 1.5F,  2.5F, 		207, 165, 118);
		getOrAddWood("Iron", 		2, 1.0F,  1.0F, 2.0F,  2.5F, 		131, 120, 112);
		getOrAddWood("PigIron", 	2, 1.2F,  0.2F, 1.8F,  2.5F, 		88 ,  71,  67);
		getOrAddWood("Steel", 		3, 1.5F,  1.5F, 2.5F,  2.5F, 		168, 168, 168);
		getOrAddWood("Thaumium", 	3, 2.0F,  2.0F, 3.0F,  1.0F, 		115, 65 , 183);
		getOrAddWood("BlackSteel", 	4, 2.0F,  1.0F, 4.0F,  3.0F, 		27 ,  27,  27);
		getOrAddWood("BlueSteel", 	5, 2.5F,  1.8F, 4.0F,  1.5F, 		28,   31, 159);
		getOrAddWood("RedSteel", 	5, 3.0F,  0.8F, 5.0F,  3.0F, 		219,  56,  56);
		getOrAddWood("Mithril", 	6, 4.0F,  2.0F, 6.0F,  1.0F, 		255, 108, 255);
		getOrAddWood("Adamantium", 	6, 6.0F,  0.5F, 7.0F,  3.0F, 		49 , 108,  52);
		getOrAddWood("Mithium", 	7, 10.0F, 3.0F, 8.0F,  0.5F, 		88 , 195, 231);
		getOrAddWood("Enderforge", 	7, 10.0F, 1.0F, 9.0F,  2.0F, 		255, 63 , 243);
		getOrAddWood("Ignotumite", 	7, 10.0F, 0.2F, 10F , 4.0F, 		71 , 228, 109);
		
		getOrAddWood("Gold", 		0, 0.2F,  1.0F, 0.0F,  4.0F, 		243, 222,  49);
		getOrAddWood("Silver", 		0, 0.2F,  1.0F, 0.0F,  2.0F, 		155, 206, 205);
	}
}
