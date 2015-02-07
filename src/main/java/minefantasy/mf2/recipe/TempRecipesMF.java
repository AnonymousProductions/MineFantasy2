package minefantasy.mf2.recipe;

import java.util.ArrayList;

import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ToolMaterialMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class TempRecipesMF
{
	public static void init()
	{
		GameRegistry.addSmelting(BlockListMF.anvil[1], new ItemStack(ComponentListMF.ingots[2], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[2], new ItemStack(Items.iron_ingot, 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[3], new ItemStack(ComponentListMF.ingots[4], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[4], new ItemStack(ComponentListMF.ingots[7], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[5], new ItemStack(ComponentListMF.ingots[14], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[6], new ItemStack(ComponentListMF.ingots[12], 6), 0.0F);
		
		GameRegistry.addRecipe(new ItemStack(Items.leather, 1), new Object[]
		{
			"H",
			'H', ComponentListMF.rawhideSmall,
		});
		GameRegistry.addRecipe(new ItemStack(Items.leather, 2), new Object[]
		{
			"H",
			'H', ComponentListMF.rawhideMedium,
		});
		GameRegistry.addRecipe(new ItemStack(Items.leather, 3), new Object[]
		{
			"H",
			'H', ComponentListMF.rawhideLarge,
		});
		GameRegistry.addRecipe(new ItemStack(Items.bucket, 1), new Object[]
		{
			"I I",
			" I ",
			'I', ComponentListMF.ingots[1],
		});
		//RESOURCES
		GameRegistry.addRecipe(new ItemStack(ComponentListMF.ingots[2], 3), new Object[]
		{
			"CTC",
			'C', ComponentListMF.ingots[0],
			'T', ComponentListMF.ingots[1]
		});
		
		GameRegistry.addRecipe(new ItemStack(ComponentListMF.ingots[3], 2), new Object[]
		{
			"IFI",
			"FCF",
			"IFI",
			'I', Blocks.iron_ore,
			'F', ComponentListMF.flux,
			'C', Blocks.coal_block,
		});
		GameRegistry.addRecipe(new ItemStack(ComponentListMF.ingots[3], 3), new Object[]
		{
			"IFI",
			"FCF",
			"IFI",
			'I', Blocks.iron_ore,
			'F', ComponentListMF.flux_strong,
			'C', Blocks.coal_block,
		});
		
		ArrayList<ItemStack> steels = OreDictionary.getOres("ingotSteel");
		ArrayList<ItemStack> bronzes = OreDictionary.getOres("ingotBronze");
		ArrayList<ItemStack> silvers = OreDictionary.getOres("ingotSilver");
		ArrayList<ItemStack> blacks = OreDictionary.getOres("ingotBlackSteel");
		
		GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.ingots[10]), new Object[]
		{
			Items.gold_ingot, Items.gold_ingot, ComponentListMF.flux_strong
		});
		GameRegistry.addRecipe(new ItemStack(Blocks.golden_rail, 64), new Object[]
		{
			"I I",
			"ISI",
			"IRI",
			'I', ComponentListMF.ingots[10],
			'S', Items.stick,
			'R', Items.redstone
		});
		
		for(ItemStack steel: steels)
		{
			GameRegistry.addRecipe(new ItemStack(FoodListMF.pie_tray), new Object[]
			{
				"III",
				'I', steel,
			});
			GameRegistry.addRecipe(new ItemStack(FoodListMF.cake_tin), new Object[]
			{
				" I ",
				"I I",
				" I ",
				'I', steel,
			});
			GameRegistry.addRecipe(new ItemStack(Blocks.rail, 64), new Object[]
			{
				"I I",
				"ISI",
				"I I",
				'I', steel,
				'S', Items.stick,
			});
			
			for(ItemStack bronze: bronzes)
			GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.ingots[6], 2), new Object[]
			{
				steel, steel, bronze, bronze, Blocks.obsidian
			});
		}
		for(ItemStack steel: blacks)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.ingots[11]), new Object[]
			{
				steel, Items.gold_ingot, Items.redstone, ComponentListMF.flux_strong, Items.blaze_powder
			});
		for(ItemStack silver: silvers)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.ingots[13]), new Object[]
			{
				steel, silver, ComponentListMF.diamond_dust, ComponentListMF.flux_strong, Items.blaze_powder
			});
		}
		}
		GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.ingots[15], 2), new Object[]
		{
			ComponentListMF.ingots[12], BlockListMF.oreMythic, Items.gold_ingot, Items.gold_ingot
		});
		for(ItemStack silver: silvers)
		{
		GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.ingots[16], 2), new Object[]
		{
			ComponentListMF.ingots[14], BlockListMF.oreMythic, silver, silver
		});
		}
		GameRegistry.addSmelting(ComponentListMF.ingots[6], new ItemStack(ComponentListMF.ingots[7]), 0.8F);
		GameRegistry.addSmelting(ComponentListMF.ingots[3], new ItemStack(ComponentListMF.ingots[4]), 0.8F);
		
		GameRegistry.addSmelting(ComponentListMF.ingots[11], new ItemStack(ComponentListMF.ingots[12]), 1.2F);
		GameRegistry.addSmelting(ComponentListMF.ingots[13], new ItemStack(ComponentListMF.ingots[14]), 1.2F);
		
		GameRegistry.addSmelting(FoodListMF.horse_raw, new ItemStack(FoodListMF.horse_cooked), 0.2F);
		GameRegistry.addSmelting(FoodListMF.wolf_raw, new ItemStack(FoodListMF.wolf_cooked), 0.2F);
		GameRegistry.addSmelting(Items.milk_bucket, new ItemStack(BlockListMF.cheese_wheel), 0F);
		
		GameRegistry.addRecipe(new ItemStack(Blocks.cobblestone), new Object[]
		{
			"C",
			'C', BlockListMF.cobble_brick
		});
		GameRegistry.addRecipe(new ItemStack(Blocks.cobblestone), new Object[]
		{
			"C",
			'C', BlockListMF.cobble_pavement
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.cobble_brick, 4), new Object[]
		{
			"C C",
			"   ",
			"C C",
			'C', Blocks.cobblestone
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.cobble_pavement, 4), new Object[]
		{
			"CC",
			"CC",
			'C', Blocks.cobblestone
		});
		
		GameRegistry.addRecipe(new ItemStack(Blocks.dirt), new Object[]
		{
			"C",
			'C', BlockListMF.mud_brick
		});
		GameRegistry.addRecipe(new ItemStack(Blocks.dirt), new Object[]
		{
			"C",
			'C', BlockListMF.mud_pavement
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.mud_brick, 4), new Object[]
		{
			"C C",
			"   ",
			"C C",
			'C', Blocks.dirt
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.mud_pavement, 4), new Object[]
		{
			"CC",
			"CC",
			'C', Blocks.dirt
		});
		
		GameRegistry.addSmelting(ComponentListMF.fireclay_brick, new ItemStack(ComponentListMF.strong_brick), 0.1F);
		GameRegistry.addRecipe(new ItemStack(BlockListMF.firebricks, 1), new Object[]
		{
			"CC",
			"CC",
			'C', ComponentListMF.strong_brick
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.framed_glass), new Object[]
		{
			"PGP",
			'P', ComponentListMF.plank,
			'G', Blocks.glass
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.window), new Object[]
		{
			" P ",
			"PGP",
			" P ",
			'P', ComponentListMF.plank,
			'G', Blocks.glass
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.framed_pane, 16), new Object[]
		{
			"GGG",
			"GGG",
			'G', BlockListMF.framed_glass
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.window_pane, 16), new Object[]
		{
			"GGG",
			"GGG",
			'G', BlockListMF.window
		});
		GameRegistry.addRecipe(new ItemStack(BlockListMF.thatch, 1), new Object[]
		{
			"HH",
			"HH",
			'H', Blocks.tallgrass
		});
		GameRegistry.addSmelting(BlockListMF.limestone_cobblestone, new ItemStack(BlockListMF.limestone), 0.1F);
		GameRegistry.addRecipe(new ItemStack(BlockListMF.clayWall, 4), new Object[]
		{
			" P ",
			"PCP",
			" P ",
			'P', ComponentListMF.plank,
			'C', Blocks.clay
		});
		addFood();
		
	}

	private static void addFood()
	{
		GameRegistry.addSmelting(FoodListMF.sweetroll_raw, new ItemStack(FoodListMF.sweetroll_uniced), 0F);
		GameRegistry.addSmelting(FoodListMF.cake_raw, new ItemStack(FoodListMF.cake_uniced), 0F);
		GameRegistry.addSmelting(FoodListMF.cake_carrot_raw, new ItemStack(FoodListMF.cake_carrot_uniced), 0F);
		GameRegistry.addSmelting(FoodListMF.cake_choc_raw, new ItemStack(FoodListMF.cake_choc_uniced), 0F);
		GameRegistry.addSmelting(FoodListMF.cake_bf_raw, new ItemStack(FoodListMF.cake_bf_uniced), 0F);
		GameRegistry.addSmelting(FoodListMF.pie_meat_uncooked, new ItemStack(FoodListMF.pie_meat_cooked), 0F);
		GameRegistry.addSmelting(FoodListMF.pie_shepard_uncooked, new ItemStack(FoodListMF.pie_shepard_cooked), 0F);
		GameRegistry.addSmelting(FoodListMF.pie_apple_uncooked, new ItemStack(FoodListMF.pie_apple_cooked), 0F);
		GameRegistry.addSmelting(FoodListMF.pie_berry_uncooked, new ItemStack(FoodListMF.pie_berry_cooked), 0F);

		GameRegistry.addShapelessRecipe(new ItemStack(BlockListMF.pie_meat), new Object[]
		{
			FoodListMF.pie_meat_cooked
		});
		GameRegistry.addShapelessRecipe(new ItemStack(BlockListMF.pie_shepards), new Object[]
		{
			FoodListMF.pie_shepard_cooked
		});
		GameRegistry.addShapelessRecipe(new ItemStack(BlockListMF.pie_apple), new Object[]
		{
			FoodListMF.pie_apple_cooked
		});
		GameRegistry.addShapelessRecipe(new ItemStack(BlockListMF.pie_berry), new Object[]
		{
			FoodListMF.pie_berry_cooked
		});
	}
}
