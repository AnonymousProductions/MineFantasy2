package minefantasy.mf2.item.list;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.mining.RandomDigs;
import minefantasy.mf2.api.mining.RandomOre;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.AdvancedFuelHandlerMF;
import minefantasy.mf2.item.FuelHandlerMF;
import minefantasy.mf2.item.ItemComponentMF;
import minefantasy.mf2.item.ItemHide;
import minefantasy.mf2.item.gadget.ItemBombComponent;
import minefantasy.mf2.item.heatable.ItemHeated;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
/**
 * @author Anonymous Productions
 */
public class ComponentListMF 
{
	public static final BaseMaterialMF[] ingotMats = new BaseMaterialMF[]
	{
		BaseMaterialMF.copper,
		BaseMaterialMF.tin,
		BaseMaterialMF.bronze,
		BaseMaterialMF.pigiron,
		BaseMaterialMF.steel,
		BaseMaterialMF.encrusted,
		BaseMaterialMF.weakblacksteel,
		BaseMaterialMF.blacksteel,
		//BaseMaterialMF.dragonforge,
		BaseMaterialMF.silver,
		//BaseMaterialMF.goldPure,
		BaseMaterialMF.weakredsteel,
		BaseMaterialMF.redsteel,
		BaseMaterialMF.weakbluesteel,
		BaseMaterialMF.bluesteel,
		BaseMaterialMF.adamantium,
		BaseMaterialMF.mithril,
		BaseMaterialMF.ignotumite,
		BaseMaterialMF.mithium,
		BaseMaterialMF.enderforge,
	};
	public static final BaseMaterialMF[] hunkMats = new BaseMaterialMF[]
	{
		BaseMaterialMF.copper,
		BaseMaterialMF.tin,
		BaseMaterialMF.bronze,
		BaseMaterialMF.iron,
		BaseMaterialMF.pigiron,
		BaseMaterialMF.steel,
		BaseMaterialMF.encrusted,
		BaseMaterialMF.blacksteel,
		//BaseMaterialMF.dragonforge,
		BaseMaterialMF.silver,
		BaseMaterialMF.gold,
		BaseMaterialMF.redsteel,
		BaseMaterialMF.bluesteel,
		BaseMaterialMF.adamantium,
		BaseMaterialMF.mithril,
		BaseMaterialMF.ignotumite,
		BaseMaterialMF.mithium,
		BaseMaterialMF.enderforge,
	};
	
	public static ItemComponentMF[] ingots = new ItemComponentMF[ingotMats.length];
	public static ItemComponentMF[] hunks = new ItemComponentMF[hunkMats.length];
	public static ItemComponentMF[] chainmeshes = new ItemComponentMF[ArmourListMF.mats.length];
	public static ItemComponentMF[] plates = new ItemComponentMF[ArmourListMF.mats.length-1];
	public static Item plank = new ItemComponentMF("plank", 0);
	//public static Item vine = new ItemComponentMF("vine", -1);
	//public static Item sharp_rock = new ItemComponentMF("sharp_rock", -1);
	public static ItemComponentMF[] arrowheads = new ItemComponentMF[ToolListMF.weaponMats.length];
	public static ItemComponentMF[] bodkinheads = new ItemComponentMF[ToolListMF.weaponMats.length-1];
	public static ItemComponentMF[] broadheads = new ItemComponentMF[ToolListMF.weaponMats.length-1];
	
	public static Item flux = new ItemComponentMF("flux", 0);
	public static Item flux_strong = new ItemComponentMF("flux_strong", 0);
	
	public static Item coalDust = new ItemComponentMF("coalDust", 0);
	public static Item nitre = new ItemComponentMF("nitre", 0);
	public static Item sulfur = new ItemComponentMF("sulfur", 0);
	public static Item iron_prep = new ItemComponentMF("iron_prep", 0);
	public static Item blackpowder = new ItemBombComponent("blackpowder", 0, "powder", 0);
	public static Item blackpowder_advanced = new ItemBombComponent("blackpowder_advanced", 1, "powder", 1);
	public static Item fletching = new ItemComponentMF("fletching", 0);
	public static Item shrapnel = new ItemBombComponent("shrapnel", 0, "filling", 1);
	public static Item magma_cream_refined = new ItemBombComponent("magma_cream_refined", 1, "filling", 2);
	public static Item bomb_fuse = new ItemBombComponent("bomb_fuse", 0, "fuse", 0);
	public static Item bomb_fuse_long = new ItemBombComponent("bomb_fuse_long", 0, "fuse", 1);
	public static Item bomb_casing_uncooked = new ItemComponentMF("bomb_casing_uncooked", 0);
	public static Item bomb_casing = new ItemBombComponent("bomb_casing", 0, "bombcase", 0);
	public static Item mine_casing_uncooked = new ItemComponentMF("mine_casing_uncooked", 0);
	public static Item mine_casing = new ItemBombComponent("mine_casing", 0, "minecase", 0);
	public static Item bomb_casing_iron = new ItemBombComponent("bomb_casing_iron", 0, "bombcase", 1);
	public static Item mine_casing_iron = new ItemBombComponent("mine_casing_iron", 0, "minecase", 1);
	public static Item bomb_casing_obsidian = new ItemBombComponent("bomb_casing_obsidian", 1, "bombcase", 2);
	public static Item mine_casing_obsidian = new ItemBombComponent("mine_casing_obsidian", 1, "minecase", 2);
	public static Item bomb_casing_crystal = new ItemBombComponent("bomb_casing_crystal", 1, "bombcase", 3);
	public static Item mine_casing_crystal = new ItemBombComponent("mine_casing_crystal", 1, "minecase", 3);
	
	public static Item coke = new ItemComponentMF("coke", 0);
	public static Item diamond_shards = new ItemComponentMF("diamond_shards", 0);
	
	public static Item clay_brick = new ItemComponentMF("clay_brick", 0);
	public static Item kaolinite = new ItemComponentMF("kaolinite", 0);
	public static Item kaolinite_dust = new ItemComponentMF("kaolinite_dust", 0);
	public static Item fireclay = new ItemComponentMF("fireclay", 0);
	public static Item fireclay_brick = new ItemComponentMF("fireclay_brick", 0);
	public static Item strong_brick = new ItemComponentMF("strong_brick", 0);
	
	public static Item hideSmall = new ItemComponentMF("hideSmall", 0);
	public static Item hideMedium = new ItemComponentMF("hideMedium", 0);
	public static Item hideLarge = new ItemComponentMF("hideLarge", 0);
	public static Item rawhideSmall = new ItemHide("rawhideSmall", hideSmall, 1.0F);
	public static Item rawhideMedium = new ItemHide("rawhideMedium", hideMedium, 1.5F);
	public static Item rawhideLarge = new ItemHide("rawhideLarge", hideLarge, 3.0F);
	
	public static Item dragon_heart = new ItemComponentMF("dragon_heart", 1);
	
	public static Item silver_rod = new ItemComponentMF("silver_rod", 0);
	public static Item gold_rod = new ItemComponentMF("gold_rod", 0);
	public static Item obsidian_rod = new ItemComponentMF("obsidian_rod", 1);
	public static Item leather_strip = new ItemComponentMF("leather_strip", 0);
	public static Item nail = new ItemComponentMF("nail", 0);
	public static Item rivet = new ItemComponentMF("rivet", 0);
	
	public static Item hotItem = new ItemHeated();
	
	public static void init() 
	{
		GameRegistry.registerFuelHandler(new FuelHandlerMF());
		MineFantasyAPI.registerFuelHandler(new AdvancedFuelHandlerMF());
		OreDictionary.registerOre("ingotIron", Items.iron_ingot);
		OreDictionary.registerOre("ingotGold", Items.gold_ingot);
		Items.potionitem.setMaxStackSize(16);
		Items.iron_ingot.setTextureName("minefantasy2:component/ingotWroughtIron");
		Blocks.iron_block.setBlockTextureName("minefantasy2:metal/iron_block");
		Blocks.iron_bars.setBlockTextureName("minefantasy2:metal/iron_bars");
		for(int a = 0; a < ingotMats.length; a ++)
		{
			BaseMaterialMF mat = ingotMats[a];
			String name = mat.name;
			int rarity = mat.rarity;
			
			ingots[a] = new ItemComponentMF("ingot"+name, rarity);
			OreDictionary.registerOre("ingot"+name, ingots[a]);
			MineFantasyAPI.addHeatableItems("ingot"+name, mat.workableTemp, mat.unstableTemp, mat.unstableTemp*2);
			
			if(name.equalsIgnoreCase("PigIron"))
			{
				MineFantasyAPI.addHeatableItems("ingotRefinedIron", mat.workableTemp, mat.unstableTemp, mat.unstableTemp*2);
				OreDictionary.registerOre("ingotRefinedIron", ingots[a]);
			}
		}
		
		for(int a = 0; a < hunkMats.length; a ++)
		{
			BaseMaterialMF mat = hunkMats[a];
			String name = mat.name;
			int rarity = mat.rarity;
			
			hunks[a] = new ItemComponentMF("hunk"+name, rarity);
			OreDictionary.registerOre("hunk"+name, hunks[a]);
			MineFantasyAPI.addHeatableItems("hunk"+name, mat.workableTemp, mat.unstableTemp, mat.unstableTemp*2);
		}
		
		for(int a = 0; a < ArmourListMF.mats.length; a ++)
		{
			BaseMaterialMF baseMat = ArmourListMF.mats[a];
			
			String name=baseMat.name;
			int rarity = baseMat.rarity;
			
			chainmeshes[a] = new ItemComponentMF("chainmesh"+name, rarity);
			OreDictionary.registerOre("chainmesh"+name, chainmeshes[a]);
			if(a > 0)
			{
				plates[a-1] = new ItemComponentMF("plate"+name, rarity);
				OreDictionary.registerOre("plate"+name, plates[a-1]);
			}
		}
		for(int a = 0; a < ToolListMF.weaponMats.length; a ++)
		{
			BaseMaterialMF mat = ToolListMF.weaponMats[a];
			String name = mat.name.toLowerCase();
			int rarity = mat.rarity;
			
			arrowheads[a] = new ItemComponentMF(name+"_arrow_head", rarity);
			OreDictionary.registerOre(name+"_arrow_head", arrowheads[a]);
			if(a != 0)
			{
				bodkinheads[a-1] = new ItemComponentMF(name+"_bodkin_head", rarity);
				OreDictionary.registerOre(name+"_bodkin_head", bodkinheads[a-1]);
				
				broadheads[a-1] = new ItemComponentMF(name+"_broad_head", rarity);
				OreDictionary.registerOre(name+"_broad_head", broadheads[a-1]);
			}
		}
		addRandomDrops();
		initFuels();
		MineFantasyAPI.addHeatableItem(rivet, 100, 500, 1000);
	}

	private static void initFuels() 
	{
		MineFantasyAPI.addForgeFuel(new ItemStack(Items.coal, 1, 0), 900, 120);//	120* , 45s
		MineFantasyAPI.addForgeFuel(new ItemStack(Items.coal, 1, 1), 1200, 160);//	160* , 1m
		MineFantasyAPI.addForgeFuel(Items.blaze_powder, 200, 300, true);//			300* , 10s
		MineFantasyAPI.addForgeFuel(Items.blaze_rod,    300, 300, true);//			300* , 15s
		MineFantasyAPI.addForgeFuel(Items.fire_charge,  1200, 350,true);//			350* , 1m
		MineFantasyAPI.addForgeFuel(Items.lava_bucket,  2400, 500, true);//			500* , 2m
		MineFantasyAPI.addForgeFuel(Items.magma_cream,  2400, 400);//				400* , 2m
		
		MineFantasyAPI.addForgeFuel(ComponentListMF.coalDust, 200, 120);//				120* , 10s
		MineFantasyAPI.addForgeFuel(ComponentListMF.coke, 1200, 250);//					250* , 1m
		MineFantasyAPI.addForgeFuel(ComponentListMF.magma_cream_refined, 2400, 200);//	500* , 2m
	}

	private static void addRandomDrops()
	{
		RandomOre.addOre(new ItemStack(kaolinite),    1.5F,  Blocks.stone,         	  -1, 32,128, false);
		RandomOre.addOre(new ItemStack(flux),       	2F,  Blocks.stone,         	  -1, 0, 128, false);
		RandomOre.addOre(new ItemStack(flux),       	10F, BlockListMF.limestone,   -1, 0, 256, true);
		RandomOre.addOre(new ItemStack(flux_strong),    0.5F,Blocks.stone,         	   2, 0, 128, false);
		RandomOre.addOre(new ItemStack(Items.coal), 	1F,  Blocks.stone, 			  -1, 0, 128, false);
		RandomOre.addOre(new ItemStack(sulfur),     	2F,  Blocks.stone, 			  -1, 0, 16,  false);
		RandomOre.addOre(new ItemStack(nitre),      	3F,  Blocks.stone, 			  -1, 0, 64,  false);
		RandomOre.addOre(new ItemStack(Items.redstone), 5F,  Blocks.stone, 			   2, 0, 16,  false);
		RandomOre.addOre(new ItemStack(Items.flint),    1F,  Blocks.stone, 			  -1, 0, 64,  false);
		RandomOre.addOre(new ItemStack(diamond_shards), 0.1F,Blocks.stone, 		       2, 0, 16,  false);
		RandomOre.addOre(new ItemStack(Items.quartz),   0.5F,Blocks.stone, 		       3, 0, 16,  false);
		
		RandomOre.addOre(new ItemStack(sulfur), 			 	10F,Blocks.netherrack, 	 -1, 0, 512,  	false);
		RandomOre.addOre(new ItemStack(Items.glowstone_dust),	5F,Blocks.netherrack, 	 -1, 0, 512,  	false);
		RandomOre.addOre(new ItemStack(Items.quartz), 			5F, Blocks.netherrack, 	 -1, 0, 512,  	false);
		RandomOre.addOre(new ItemStack(Items.blaze_powder), 	5F, Blocks.netherrack,   -1, 0, 512,  	false);
		RandomOre.addOre(new ItemStack(Items.nether_wart), 		1F, Blocks.netherrack,   -1, 0, 512,  	false);
		RandomOre.addOre(new ItemStack(Items.nether_star), 	0.001F, Blocks.netherrack,   -1, 0, 512,false);
		
		RandomDigs.addOre(new ItemStack(Blocks.skull, 1, 1), 0.1F, Blocks.soul_sand,3, 0, 256,  false);
		RandomDigs.addOre(new ItemStack(Items.bone),   		 5F, Blocks.dirt,  -1, 0, 256,  false);
		RandomDigs.addOre(new ItemStack(Items.rotten_flesh), 2F, Blocks.dirt,  -1, 0, 256,  false);
		RandomDigs.addOre(new ItemStack(Items.coal, 1, 1),   1F, Blocks.dirt,  -1, 32, 64,  false);
		
		RandomDigs.addOre(new ItemStack(Items.melon_seeds),   5F, Blocks.grass,  -1, 0, 256,  false);
		RandomDigs.addOre(new ItemStack(Items.pumpkin_seeds), 8F, Blocks.grass,  -1, 0, 256,  false);
	}
}
