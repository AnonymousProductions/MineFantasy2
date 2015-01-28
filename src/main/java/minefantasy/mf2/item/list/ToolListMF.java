package minefantasy.mf2.item.list;

import java.awt.Color;

import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.ItemBandage;
import minefantasy.mf2.item.ItemBucketMF;
import minefantasy.mf2.item.ItemMilkBucketMF;
import minefantasy.mf2.item.archery.ArrowType;
import minefantasy.mf2.item.archery.EnumBowType;
import minefantasy.mf2.item.archery.ItemArrowMF;
import minefantasy.mf2.item.archery.ItemBowMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.gadget.*;
import minefantasy.mf2.item.tool.*;
import minefantasy.mf2.item.tool.advanced.*;
import minefantasy.mf2.item.tool.crafting.*;
import minefantasy.mf2.item.weapon.*;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Anonymous Productions
 */
public class ToolListMF 
{
	public static EnumRarity poor = EnumHelper.addRarity("Poor", EnumChatFormatting.DARK_GRAY, "poor");
	public static EnumRarity unique = EnumHelper.addRarity("Unique", EnumChatFormatting.DARK_GREEN, "unique");
	public static EnumRarity rare = EnumHelper.addRarity("Rare", EnumChatFormatting.DARK_BLUE, "rare");
	
	public static EnumRarity[] rarity = new EnumRarity[]{ToolListMF.poor, EnumRarity.common, EnumRarity.uncommon, EnumRarity.rare, EnumRarity.epic};

	
	public static final BaseMaterialMF[] mats = new BaseMaterialMF[]
	{
		BaseMaterialMF.copper,
		BaseMaterialMF.bronze,
		BaseMaterialMF.iron,
		BaseMaterialMF.steel,
		BaseMaterialMF.encrusted,
		BaseMaterialMF.blacksteel,
		BaseMaterialMF.dragonforge,
		BaseMaterialMF.adamantium,
		BaseMaterialMF.redsteel,
		BaseMaterialMF.bluesteel,
		BaseMaterialMF.mithril,
		BaseMaterialMF.ignotumite,
		BaseMaterialMF.mithium,
		BaseMaterialMF.enderforge,
	};
			
	public static final BaseMaterialMF[] weaponMats = new BaseMaterialMF[]
	{
		BaseMaterialMF.copper,
		BaseMaterialMF.bronze,
		BaseMaterialMF.iron,
		BaseMaterialMF.ornate,
		BaseMaterialMF.steel,
		BaseMaterialMF.encrusted,
		BaseMaterialMF.blacksteel,
		BaseMaterialMF.dragonforge,
		BaseMaterialMF.adamantium,
		BaseMaterialMF.redsteel,
		BaseMaterialMF.bluesteel,
		BaseMaterialMF.mithril,
		BaseMaterialMF.ignotumite,
		BaseMaterialMF.mithium,
		BaseMaterialMF.enderforge,
	};
			
			
			
	public static ItemPickMF[] picks = new ItemPickMF[mats.length];
	public static ItemAxeMF[] axes = new ItemAxeMF[mats.length];
	public static ItemSpadeMF[] spades = new ItemSpadeMF[mats.length];
	public static ItemShearsMF[] shears = new ItemShearsMF[mats.length];
	public static ItemHoeMF[] hoes = new ItemHoeMF[mats.length];
	public static ItemKnifeMF[] knives = new ItemKnifeMF[mats.length];
	public static ItemHammer[] hammers = new ItemHammer[mats.length];
	public static ItemTongs[] tongs = new ItemTongs[mats.length];
	public static ItemNeedle[] needles = new ItemNeedle[mats.length];
	public static ItemSaw[] saws = new ItemSaw[mats.length];
	public static ItemBasicCraftTool malletWood = new ItemBasicCraftTool("malletWood", "mallet", 0, 250);
	public static ItemBasicCraftTool spoonWood = new ItemBasicCraftTool("spoonWood", "spoon", 0, 250);
	
	public static ItemSwordMF[] swords = new ItemSwordMF[weaponMats.length];
	public static ItemWaraxeMF[] waraxes = new ItemWaraxeMF[weaponMats.length];
	public static ItemMaceMF[] maces = new ItemMaceMF[weaponMats.length];
	public static ItemDagger[] daggers = new ItemDagger[weaponMats.length];
	public static Item swordTraining = new ItemSwordMF("training_sword", ToolMaterialMF.TRAINING, -1, 0.8F);
	public static Item waraxeTraining = new ItemWaraxeMF("training_waraxe", ToolMaterialMF.TRAINING, -1, 0.8F);
	public static Item maceTraining = new ItemMaceMF("training_mace", ToolMaterialMF.TRAINING, -1, 0.8F);
	public static Item spearTraining = new ItemSpearMF("training_spear", ToolMaterialMF.TRAINING, -1, 0.8F);
	public static ItemArrowMF[] arrows = new ItemArrowMF[weaponMats.length];
	public static ItemArrowMF[] bodkinArrows = new ItemArrowMF[weaponMats.length-1];
	public static ItemArrowMF[] broadArrows = new ItemArrowMF[weaponMats.length-1];
	public static ItemBowMF[] bows = new ItemBowMF[weaponMats.length];
	public static ItemSpearMF[] spears = new ItemSpearMF[weaponMats.length];
	
	public static ItemBattleaxeMF[] battleaxes = new ItemBattleaxeMF[weaponMats.length-1];
	public static ItemWarhammerMF[] warhammers = new ItemWarhammerMF[weaponMats.length-1];
	public static ItemGreatswordMF[] greatswords = new ItemGreatswordMF[weaponMats.length-1];
	public static ItemKatanaMF[] katanas = new ItemKatanaMF[weaponMats.length-1];
	public static ItemHalbeardMF[] halbeards = new ItemHalbeardMF[weaponMats.length-1];
	public static ItemLance[] lances = new ItemLance[weaponMats.length-1];
	
	public static ItemHammer[] hvyHammers = new ItemHammer[mats.length-1];
	public static ItemHvyPick[] hvypicks = new ItemHvyPick[mats.length-1];
	public static ItemHandpick[] handpicks = new ItemHandpick[mats.length-1];
	public static ItemTrowMF[] trows = new ItemTrowMF[mats.length-1];
	public static ItemScythe[] scythes = new ItemScythe[mats.length-1];
	public static ItemHvyShovel[] hvyshovels = new ItemHvyShovel[mats.length-1];
	public static ItemMattock[] mattocks = new ItemMattock[mats.length-1];
	
	public static Item knifeStone = new ItemKnifeMF("stone_knife", ToolMaterialMF.STONE, -1, 3.5F);
	public static Item hammerStone = new ItemHammer("stone_hammer", ToolMaterialMF.STONE, 0, false, -1);
	public static Item tongsStone = new ItemTongs("stone_tongs", ToolMaterialMF.STONE, -1);
	public static Item needleBone = new ItemNeedle("bone_needle", ToolMaterialMF.STONE, -1);
	
	public static Item pickStone = new ItemPickMF("stone_pick", ToolMaterialMF.STONE, -1);
	public static Item axeStone = new ItemAxeMF("stone_axe", ToolMaterialMF.STONE, -1);
	public static Item spadeStone = new ItemSpadeMF("stone_spade", ToolMaterialMF.STONE, -1);
	public static Item hoeStone = new ItemHoeMF("stone_hoe", ToolMaterialMF.STONE, -1);
	
	public static Item swordStone = new ItemSwordMF("stone_sword", ToolMaterialMF.STONE, -1, 3.5F);
	public static Item maceStone = new ItemMaceMF("stone_mace", ToolMaterialMF.STONE, -1, 3.5F);
	public static Item waraxeStone = new ItemWaraxeMF("stone_waraxe", ToolMaterialMF.STONE, -1, 3.5F);
	public static Item spearStone = new ItemSpearMF("stone_spear", ToolMaterialMF.STONE, -1, 3.5F);
	
	public static Item bandage_crude = new ItemBandage("bandage_crude", 5F);
	public static Item bandage_wool = new ItemBandage("bandage_wool", 8F);
	public static Item bandage_tough = new ItemBandage("bandage_tough", 12F);
	
	public static Item bucketwood_empty = new ItemBucketMF("bucketwood_empty", Blocks.air).setMaxStackSize(16);
	public static Item bucketwood_water = new ItemBucketMF("bucketwood_water", Blocks.flowing_water);
	public static Item bucketwood_milk = new ItemMilkBucketMF("bucketwood_milk");
	
	public static ItemBomb bomb_shrapnel = new ItemBomb("bomb_shrapnel");
	public static ItemMine mine_shrapnel = new ItemMine("mine_shrapnel");
	public static void init() 
	{
		BlockListMF.init();
		ArmourListMF.init();
		FoodListMF.init();
		for(int a = 0; a < mats.length; a ++)
		{
			BaseMaterialMF baseMat = mats[a];
			
			ToolMaterial mat = baseMat.getToolConversion();
			String matName = baseMat.name.toLowerCase();
			int rarity = baseMat.rarity;
			float weight = baseMat.weight;
			int tier = baseMat.tier;
			
			picks[a] = new ItemPickMF(matName+"_pick", mat, rarity);
			axes[a] = new ItemAxeMF(matName+"_axe", mat, rarity);
			spades[a] = new ItemSpadeMF(matName+"_spade", mat, rarity);
			hoes[a] = new ItemHoeMF(matName+"_hoe", mat, rarity);
			shears[a] = new ItemShearsMF(matName+"_shears", mat, rarity);
			knives[a] = new ItemKnifeMF(matName+"_knife", mat, rarity, weight);
			hammers[a] = new ItemHammer(matName+"_hammer", mat, tier, false, rarity);
			tongs[a] = new ItemTongs(matName+"_tongs", mat, rarity);
			needles[a] = new ItemNeedle(matName+"_needle", mat, rarity);
			saws[a] = new ItemSaw(matName+"_saw", mat, rarity);
			
			if(a > 0)
			{
				hvyHammers[a-1] = new ItemHammer(matName+"_hvyHammer", mat, tier, true, rarity);
				hvypicks[a-1] = new ItemHvyPick(matName+"_hvypick", mat, rarity);
				handpicks[a-1] = new ItemHandpick(matName+"_handpick", mat, rarity);
				trows[a-1] = new ItemTrowMF(matName+"_trow", mat, rarity);
				scythes[a-1] = new ItemScythe(matName+"_scythe", mat, rarity);
				hvyshovels[a-1] = new ItemHvyShovel(matName+"_hvyShovel", mat, rarity);
				mattocks[a-1] = new ItemMattock(matName+"_mattock", mat, rarity);
			}
		}
		
		
		for(int a = 0; a < weaponMats.length; a ++)
		{
			BaseMaterialMF baseMat = weaponMats[a];
			
			ToolMaterial mat = baseMat.getToolConversion();
			String matName = baseMat.name.toLowerCase();
			int rarity = baseMat.rarity;
			float weight = baseMat.weight;
			int tier = baseMat.tier;
			
			swords[a] = new ItemSwordMF(matName+"_sword", mat, rarity, weight);
			waraxes[a] = new ItemWaraxeMF(matName+"_waraxe", mat, rarity, weight);
			maces[a] = new ItemMaceMF(matName+"_mace", mat, rarity, weight);
			daggers[a] = new ItemDagger(matName+"_dagger", mat, rarity, weight);
			spears[a] = new ItemSpearMF(matName+"_spear", mat, rarity, weight);
			
			arrows[a] = (ItemArrowMF) new ItemArrowMF(matName, rarity, mat);
			bows[a] = new ItemBowMF(matName+"_bow", mat, EnumBowType.RECURVE, rarity);
			
			if(a > 0)
			{
				battleaxes[a-1] = new ItemBattleaxeMF(matName+"_battleaxe", mat, rarity, weight);
				warhammers[a-1] = new ItemWarhammerMF(matName+"_warhammer", mat, rarity, weight);
				greatswords[a-1] = new ItemGreatswordMF(matName+"_greatsword", mat, rarity, weight);
				katanas[a-1] = new ItemKatanaMF(matName+"_katana", mat, rarity, weight);
				halbeards[a-1] = new ItemHalbeardMF(matName+"_halbeard", mat, rarity, weight);
				lances[a-1] = new ItemLance(matName+"_lance", mat, rarity, weight);
				
				bodkinArrows[a-1] = (ItemArrowMF) new ItemArrowMF(matName, rarity, mat, ArrowType.BODKIN);
				broadArrows[a-1] = (ItemArrowMF) new ItemArrowMF(matName, rarity, mat, ArrowType.BROADHEAD);
			}
		}
		
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(hammers[2]), 5, 5, 5));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(tongs[1]), 2, 2, 5));
		
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(swords[3]), 1, 1, 1));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(waraxes[3]), 1, 1, 1));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(maces[3]), 1, 1, 1));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(daggers[3]), 1, 1, 1));
	}
	
}
