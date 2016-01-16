package minefantasy.mf2.item.list;

import minefantasy.mf2.item.tool.ItemAxeMF;
import minefantasy.mf2.item.tool.ItemHoeMF;
import minefantasy.mf2.item.tool.ItemPickMF;
import minefantasy.mf2.item.tool.ItemShearsMF;
import minefantasy.mf2.item.tool.ItemSpadeMF;
import minefantasy.mf2.item.tool.advanced.ItemHandpick;
import minefantasy.mf2.item.tool.advanced.ItemHvyPick;
import minefantasy.mf2.item.tool.advanced.ItemHvyShovel;
import minefantasy.mf2.item.tool.advanced.ItemScythe;
import minefantasy.mf2.item.tool.advanced.ItemTrowMF;
import minefantasy.mf2.item.tool.crafting.ItemHammer;
import minefantasy.mf2.item.tool.crafting.ItemKnifeMF;
import minefantasy.mf2.item.tool.crafting.ItemNeedle;
import minefantasy.mf2.item.tool.crafting.ItemSaw;
import minefantasy.mf2.item.tool.crafting.ItemTongs;
import minefantasy.mf2.item.weapon.ItemBattleaxeMF;
import minefantasy.mf2.item.weapon.ItemDagger;
import minefantasy.mf2.item.weapon.ItemGreatswordMF;
import minefantasy.mf2.item.weapon.ItemMaceMF;
import minefantasy.mf2.item.weapon.ItemSwordMF;
import minefantasy.mf2.item.weapon.ItemWaraxeMF;
import minefantasy.mf2.item.weapon.ItemWarhammerMF;
import minefantasy.mf2.item.weapon.ItemWeaponMF;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class CustomToolListMF
{
	//DWARVEN\\
	public static Item dwarven_pick;
	public static Item dwarven_axe;
	public static Item dwarven_spade;
	public static Item dwarven_handpick;
	public static Item dwarven_hvypick;
	public static Item dwarven_hvyshovel;
	public static Item dwarven_trow;
	public static Item dwarven_shears;
	public static Item dwarven_hoe;
	public static Item dwarven_scythe;
	
	public static ItemWeaponMF dwarven_waraxe;
	public static ItemWeaponMF dwarven_mace;
	public static ItemWeaponMF dwarven_sword;
	public static ItemWeaponMF dwarven_dagger;
	public static ItemWeaponMF dwarven_battleaxe;
	public static ItemWeaponMF dwarven_warhammer;
	public static ItemWeaponMF dwarven_greatsword;
	
	//GNOMISH\\
	public static Item gnomish_hammer;
	public static Item gnomish_knife;
	public static Item gnomish_needle;
	public static Item gnomish_saw;
	public static Item gnomish_tongs;
	
	public static void init() 
	{
		String design = "dwarven";//Faster digs, Stronger/heavier weapons
		//Dwarf Tools
		dwarven_pick = new ItemPickMF(design+"_pick", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
		dwarven_axe = new ItemAxeMF(design+"_axe", ToolMaterial.IRON, 0).setCustom();
		dwarven_spade = new ItemSpadeMF(design+"_spade", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
		dwarven_hoe = new ItemHoeMF(design+"_hoe", ToolMaterial.IRON, 0).setCustom();
		dwarven_shears = new ItemShearsMF(design+"_shears", ToolMaterial.IRON, 0, 1).setCustom();
		
		//Advanced Dwarf Tools
		dwarven_handpick = new ItemHandpick(design+"_handpick", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
		dwarven_hvypick = new ItemHvyPick(design+"_hvypick", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
		dwarven_trow = new ItemTrowMF(design+"_trow", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
		dwarven_hvyshovel = new ItemHvyShovel(design+"_hvyshovel", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
		dwarven_scythe = new ItemScythe(design+"_scythe", ToolMaterial.IRON, 0).setCustom();
		
		//Dwarf Weapons (Heavier but more brutal)
		dwarven_dagger = new ItemDagger(design+"_dagger", ToolMaterial.IRON, 0, 2F).setCustom().modifyBaseDamage(1F);
		dwarven_sword = new ItemSwordMF(design+"_sword", ToolMaterial.IRON, 0, 2F).setCustom().modifyBaseDamage(1F);
		dwarven_waraxe = new ItemWaraxeMF(design+"_waraxe", ToolMaterial.IRON, 0, 2F).setCustom().modifyBaseDamage(2F);
		dwarven_mace = new ItemMaceMF(design+"_mace", ToolMaterial.IRON, 0, 2F).setCustom().modifyBaseDamage(1F);
		//Heavy Dwarf Weapons (Heavier but more brutal)
		dwarven_greatsword = new ItemGreatswordMF(design+"_greatsword", ToolMaterial.IRON, 0, 2F).setCustom().modifyBaseDamage(1F);
		dwarven_battleaxe = new ItemBattleaxeMF(design+"_battleaxe", ToolMaterial.IRON, 0,  2F).setCustom().modifyBaseDamage(2F);
		dwarven_warhammer = new ItemWarhammerMF(design+"_warhammer", ToolMaterial.IRON, 0, 2F).setCustom().modifyBaseDamage(1F);
		
		
		design = "gnomish";
		//I did not set weight (for knife) or efficiencies
		//I'm assuming the weight value for the knife is now covered by the density value in material now?
		//Gnomish Tools
		gnomish_hammer = new ItemHammer(design+"_hammer", ToolMaterial.IRON, false, 0).setCustom().setEfficiencyMod(1.5F);
		gnomish_knife = new ItemKnifeMF(design+"_knife", ToolMaterial.IRON, 0, 1F).setCustom().setEfficiencyMod(1.5F);
		gnomish_needle = new ItemNeedle(design+"_needle", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
		gnomish_saw = new ItemSaw(design+"_saw", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
		gnomish_tongs = new ItemTongs(design+"_tongs", ToolMaterial.IRON, 0).setCustom().setEfficiencyMod(1.5F);
	}
}
