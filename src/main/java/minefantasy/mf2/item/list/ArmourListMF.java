package minefantasy.mf2.item.list;

import minefantasy.mf2.api.armour.ArmourDesign;
import minefantasy.mf2.api.armour.ArmourMaterialMF;
import minefantasy.mf2.api.crafting.exotic.SpecialForging;
import minefantasy.mf2.item.armour.ItemArmourMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * @author Anonymous Productions
 */
public class ArmourListMF
{
	public static ArmourMaterialMF LEATHER = new ArmourMaterialMF("Leather",         5,   0.30F,  18,  1.00F);
	public static ArmourMaterialMF APRON = new ArmourMaterialMF("Apron",             6,   0.30F,  0,   1.00F);
	
	public static final BaseMaterialMF[] leathermats = new BaseMaterialMF[]
	{
		BaseMaterialMF.hide,
		BaseMaterialMF.reinforced,
		BaseMaterialMF.studded,
		BaseMaterialMF.scaled,
	};
	
	public static final BaseMaterialMF[] mats = new BaseMaterialMF[]
	{
		BaseMaterialMF.copper,
		BaseMaterialMF.bronze,
		BaseMaterialMF.iron,
		BaseMaterialMF.steel,
		BaseMaterialMF.encrusted,
		BaseMaterialMF.blacksteel,
		BaseMaterialMF.dragonforge,
		BaseMaterialMF.redsteel,
		BaseMaterialMF.bluesteel,
		BaseMaterialMF.adamantium,
		BaseMaterialMF.mithril,
		BaseMaterialMF.ignotumite,
		BaseMaterialMF.mithium,
		BaseMaterialMF.enderforge,
	};
	
	public static ItemArmourMF[] leather = new ItemArmourMF[leathermats.length*4];
	
	public static ItemArmourMF[] chainmail = new ItemArmourMF[mats.length*4];
	public static ItemArmourMF[] fieldplate = new ItemArmourMF[mats.length*4 - 4];
	
	public static void init() 
	{
		for(int a = 0; a < leathermats.length; a ++)
		{
			BaseMaterialMF baseMat = leathermats[a];
			String matName = baseMat.name;
			int rarity = baseMat.rarity;
			int id = a*4;
			float bulk = baseMat.weight;
			
			leather[id+0] = new ItemArmourMF(matName.toLowerCase()+"_helmet", baseMat, ArmourDesign.LEATHER, 0, matName.toLowerCase()+"_layer_1", rarity, bulk);
			leather[id+1] = new ItemArmourMF(matName.toLowerCase()+"_chest", baseMat, ArmourDesign.LEATHER, 1, matName.toLowerCase()+"_layer_1", rarity, bulk);
			leather[id+2] = new ItemArmourMF(matName.toLowerCase()+"_legs", baseMat, ArmourDesign.LEATHER, 2, matName.toLowerCase()+"_layer_2", rarity, bulk);
			leather[id+3] = new ItemArmourMF(matName.toLowerCase()+"_boots", baseMat, ArmourDesign.LEATHER, 3, matName.toLowerCase()+"_layer_1", rarity, bulk);
		}
		for(int a = 0; a < mats.length; a ++)
		{
			BaseMaterialMF baseMat = mats[a];
			ArmourMaterialMF mat = baseMat.getArmourConversion();
			
			String matName=baseMat.name;
			int rarity = baseMat.rarity;
			int id = a*4;
			float bulk = baseMat.weight;
			
			chainmail[id+0] = new ItemArmourMF(matName.toLowerCase()+"_mail_helmet", baseMat, ArmourDesign.MAIL, 0, matName.toLowerCase()+"_mail_layer_1", rarity);
			chainmail[id+1] = new ItemArmourMF(matName.toLowerCase()+"_mail_chest", baseMat, ArmourDesign.MAIL, 1, matName.toLowerCase()+"_mail_layer_1", rarity);
			chainmail[id+2] = new ItemArmourMF(matName.toLowerCase()+"_mail_legs", baseMat, ArmourDesign.MAIL, 2, matName.toLowerCase()+"_mail_layer_2", rarity);
			chainmail[id+3] = new ItemArmourMF(matName.toLowerCase()+"_mail_boots", baseMat, ArmourDesign.MAIL, 3, matName.toLowerCase()+"_mail_layer_1", rarity);
			
			if(a > 0)
			{
				int id2 = (a-1)*4;
				fieldplate[id2+0] = new ItemArmourMF(matName.toLowerCase()+"_plate_helmet", baseMat, ArmourDesign.PLATE, 0, matName.toLowerCase()+"_plate_layer_1", rarity);
				fieldplate[id2+1] = new ItemArmourMF(matName.toLowerCase()+"_plate_chest", baseMat, ArmourDesign.PLATE, 1, matName.toLowerCase()+"_plate_layer_1", rarity);
				fieldplate[id2+2] = new ItemArmourMF(matName.toLowerCase()+"_plate_legs", baseMat, ArmourDesign.PLATE, 2, matName.toLowerCase()+"_plate_layer_2", rarity);
				fieldplate[id2+3] = new ItemArmourMF(matName.toLowerCase()+"_plate_boots", baseMat, ArmourDesign.PLATE, 3, matName.toLowerCase()+"_plate_layer_1", rarity);
			}
		}
		for(int a = 0; a < 4; a++)
		{
			SpecialForging.addDragonforgeCraft(chainmail[5*4+a], chainmail[6*4+a]);
			SpecialForging.addDragonforgeCraft(fieldplate[4*4+a], fieldplate[5*4+a]);
		}
	}
	
	/**
	 * Gets a piece of armour
	 * @param pool the armour item (Mail, Plate, Etc)
	 * @param tier the tier of metal
	 * @param piece the piece (0=Helmet, 1=Chest, 2=Legs, 3=Boots)
	 */
	public static ItemStack armour(ItemArmourMF[] pool, int tier, int piece)
	{
		if(pool == fieldplate)
		{
			tier-=1;
		}
		int slot = tier*4+piece;
		if(slot >= pool.length)
		{
			slot = pool.length-1;
		}
		return new ItemStack(pool[slot]);
	}
	
	
	public static boolean isUnbreakable(BaseMaterialMF material, EntityLivingBase user) 
	{
		if(material == BaseMaterialMF.enderforge || material == BaseMaterialMF.ignotumite || material == BaseMaterialMF.mithium)
		{
			return true;
		}
		return false;
	}
}
