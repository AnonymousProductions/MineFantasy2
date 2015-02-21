package minefantasy.mf2.block.list;

import minefantasy.mf2.block.basic.*;
import minefantasy.mf2.block.crafting.*;
import minefantasy.mf2.block.food.*;
import minefantasy.mf2.block.refining.*;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;

public class BlockListMF
{
	public static final BaseMaterialMF[] metalBlocks = new BaseMaterialMF[]
	{
		BaseMaterialMF.copper,
		BaseMaterialMF.tin,
		BaseMaterialMF.silver,
		BaseMaterialMF.pigiron,
		
		BaseMaterialMF.bronze,
		BaseMaterialMF.steel,
		//BaseMaterialMF.dragonforge,
		BaseMaterialMF.redsteel,
		BaseMaterialMF.blacksteel,
		BaseMaterialMF.bluesteel,
		
		BaseMaterialMF.adamantium,
		//BaseMaterialMF.deepiron,
		BaseMaterialMF.mithril,
		BaseMaterialMF.ignotumite,
		//BaseMaterialMF.deepsteel,
		BaseMaterialMF.mithium,
		
		BaseMaterialMF.enderforge,
	};
	public static final BaseMaterialMF[] specialMetalBlocks = new BaseMaterialMF[]
	{
		BaseMaterialMF.bronze,
		BaseMaterialMF.iron,
		BaseMaterialMF.steel,
		BaseMaterialMF.dragonforge,
		BaseMaterialMF.redsteel,
		BaseMaterialMF.blacksteel,
		BaseMaterialMF.bluesteel,
	};
	public static final BaseMaterialMF[] anvils = new BaseMaterialMF[]
	{
		//BaseMaterialMF.stone,
		BaseMaterialMF.bronze,
		BaseMaterialMF.iron,
		BaseMaterialMF.steel,
		BaseMaterialMF.blacksteel,
		BaseMaterialMF.bluesteel,
		BaseMaterialMF.redsteel,
	};
	public static Block oreCopper = new BlockOreMF("oreCopper", 0, -1).setHardness(2.0F).setResistance(3.0F);
	public static Block oreTin = new BlockOreMF("oreTin", 0).setHardness(2.5F).setResistance(4.0F);
	public static Block oreSilver = new BlockOreMF("oreSilver", 2).setHardness(3.0F).setResistance(5.0F);
	public static Block oreMythic = new BlockMythicOre("oreMythic").setHardness(10.0F).setResistance(100.0F);
	
	public static Block oreKaolinite = new BlockOreMF("oreKaolinite", 1, 0, ComponentListMF.kaolinite, 1, 1, 1).setHardness(3.0F).setResistance(5.0F);
	public static Block oreNitre = new BlockOreMF("oreNitre", 2, 0, ComponentListMF.nitre, 1, 2, 1).setHardness(3.0F).setResistance(5.0F);
	public static Block oreSulfur = new BlockOreMF("oreSulfur", 2, 0, ComponentListMF.sulfur, 1, 4, 2).setHardness(3.0F).setResistance(2.0F);
	public static Block oreBorax = new BlockOreMF("oreBorax", 2, 0, ComponentListMF.flux_strong, 1, 4, 4).setHardness(3.0F).setResistance(2.0F);
	public static Block oreClay = new BlockOreMF("oreClay", 0, 0, Items.clay_ball, 1, 4, 1, Material.ground).setHardness(0.5F).setStepSound(Block.soundTypeGravel);
	
	public static Block mud_brick = new BasicBlockMF("mud_brick", Material.ground).setHardness(1.0F).setResistance(0.5F).setStepSound(Block.soundTypePiston);
	public static Block mud_pavement = new BasicBlockMF("mud_pavement", Material.ground).setHardness(0.5F).setStepSound(Block.soundTypePiston);
	
	public static Block cobble_brick = new BasicBlockMF("cobble_brick", Material.rock).setHardness(2.5F).setResistance(12.0F).setStepSound(Block.soundTypePiston);
	public static Block cobble_pavement = new BasicBlockMF("cobble_pavement", Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston);
	
	public static Block window = new BasicBlockMF("window", Material.glass).setHardness(0.9F).setResistance(0.1F).setStepSound(Block.soundTypeGlass);
	public static Block framed_glass = new BasicBlockMF("framed_glass", Material.glass).setHardness(0.6F).setResistance(0.2F).setStepSound(Block.soundTypeGlass);
	public static Block framed_pane = new BlockPaneMF("framed_pane", "framed_glass", "framed_glass_pane", Material.glass, true).setHardness(0.6F).setResistance(0.1F).setStepSound(Block.soundTypeGlass);
	public static Block window_pane = new BlockPaneMF("window_pane", "window", "framed_glass_pane", Material.glass, true).setHardness(0.9F).setResistance(0.2F).setStepSound(Block.soundTypeGlass);
	
	public static Block thatch = new BasicBlockMF("thatch", Material.leaves).setHardness(1.0F).setStepSound(Block.soundTypeGrass);
	
	public static Block limestone_cobblestone = new BasicBlockMF("limestone_cobblestone", Material.rock).setHardness(0.8F).setResistance(4.0F).setStepSound(Block.soundTypePiston);
	public static Block limestone = new BasicBlockMF("limestone", Material.rock, limestone_cobblestone).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone);
	
	public static Block firebricks = new BasicBlockMF("firebricks", Material.rock).setHardness(5.0F).setResistance(15.0F).setStepSound(Block.soundTypePiston);
	public static Block clayWall = new BasicBlockMF("clayWall", Material.wood).setHardness(1.0F).setResistance(1.0F).setStepSound(Block.soundTypeWood);
	
	public static BlockMetalBarsMF[] bars = new BlockMetalBarsMF[specialMetalBlocks.length];
	public static BlockMetalMF[] storage = new BlockMetalMF[metalBlocks.length];
	public static BlockAnvilMF[] anvil = new BlockAnvilMF[anvils.length];
	public static BlockCarpenter carpenter = new BlockCarpenter();
	public static BlockBombBench bombBench = new BlockBombBench();
	
	public static Block cheese_wheel = new BlockCakeMF("cheese", FoodListMF.cheese_slice);
	
	public static Block cake_vanilla = new BlockCakeMF("cake_vanilla", FoodListMF.cake_slice);
	public static Block cake_carrot = new BlockCakeMF("cake_carrot", FoodListMF.carrotcake_slice);
	public static Block cake_chocolate = new BlockCakeMF("cake_chocolate", FoodListMF.choccake_slice);
	public static Block cake_bf = new BlockCakeMF("cake_bf", FoodListMF.bfcake_slice);
	
	public static Block pie_meat = new BlockPie("pie_meat", FoodListMF.meatpie_slice);
	
	public static Block pie_apple = new BlockPie("pie_apple", FoodListMF.pieslice_apple);
	public static Block pie_berry = new BlockPie("pie_berry", FoodListMF.pieslice_berry);
	
	public static Block pie_shepards = new BlockPie("pie_shepards", FoodListMF.pieslice_shepards);
	
	public static Block berryBush = new BlockBerryBush("berries");
	public static Block blast_chamber = new BlockBFC();
	public static Block blast_heater = new BlockBFH(false);
	public static Block blast_heater_active = new BlockBFH(true).setLightLevel(10F);
	
	public static Block crucible = new BlockCrucible(false);
	public static Block crucible_active = new BlockCrucible(true).setLightLevel(12F);
	
	public static void init()
	{
		for(int a = 0; a < specialMetalBlocks.length; a++)
		{
			BaseMaterialMF material = specialMetalBlocks[a];
			if(material != null)
			{
				bars[a] = new BlockMetalBarsMF(material);
			}
		}
		for(int a = 0; a < metalBlocks.length; a++)
		{
			BaseMaterialMF material = metalBlocks[a];
			if(material != null)
			{
				storage[a] = new BlockMetalMF(material);
			}
		}
		for(int a = 0; a < anvils.length; a++)
		{
			BaseMaterialMF material = anvils[a];
			if(material != null)
			{
				anvil[a] = new BlockAnvilMF(material);
			}
		}
	}
	
	public static int anvil_RI = 100;
	public static int carpenter_RI = 101;
	public static int bomb_RI = 102;
}
