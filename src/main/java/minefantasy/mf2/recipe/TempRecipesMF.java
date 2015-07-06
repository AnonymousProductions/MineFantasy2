package minefantasy.mf2.recipe;

import java.util.ArrayList;

import minefantasy.mf2.api.crafting.tanning.TanningRecipe;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class TempRecipesMF
{
	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(Items.bucket, 1), new Object[]
		{
			"I I",
			" I ",
			'I', ComponentListMF.ingots[1],
		});
		//RESOURCES
		
		ArrayList<ItemStack> steels = OreDictionary.getOres("ingotSteel");
		ArrayList<ItemStack> bronzes = OreDictionary.getOres("ingotBronze");
		ArrayList<ItemStack> silvers = OreDictionary.getOres("ingotSilver");
		ArrayList<ItemStack> blacks = OreDictionary.getOres("ingotBlackSteel");
		
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
		}
		GameRegistry.addSmelting(FoodListMF.horse_raw, new ItemStack(FoodListMF.horse_cooked), 0.2F);
		GameRegistry.addSmelting(FoodListMF.wolf_raw, new ItemStack(FoodListMF.wolf_cooked), 0.2F);
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.cheese_wheel), new Object[]
		{
			"CCC",
			'C', Items.milk_bucket
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.cobblestone), new Object[]
		{
			"C",
			'C', BlockListMF.cobble_brick
		});
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.cobblestone), new Object[]
		{
			"C",
			'C', BlockListMF.cobble_pavement
		});
		KnowledgeListMF.stoneBricksR.add(
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.cobble_brick, 4), new Object[]
		{
			"C C",
			"   ",
			"C C",
			'C', Blocks.cobblestone
		}));
		KnowledgeListMF.stoneBricksR.add(
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.cobble_pavement, 4), new Object[]
		{
			"CC",
			"CC",
			'C', Blocks.cobblestone
		}));
		
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.reinforced_stone), new Object[]
		{
			"C",
			'C', BlockListMF.reinforced_stone_bricks
		});
		KnowledgeListMF.stoneBricksR.add(
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.reinforced_stone_bricks, 4), new Object[]
		{
			"CC",
			"CC",
			'C', BlockListMF.reinforced_stone
		}));
		
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.dirt), new Object[]
		{
			"C",
			'C', BlockListMF.mud_brick
		});
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.dirt), new Object[]
		{
			"C",
			'C', BlockListMF.mud_pavement
		});
		KnowledgeListMF.stoneBricksR.add(
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.mud_brick, 4), new Object[]
		{
			"C C",
			"   ",
			"C C",
			'C', Blocks.dirt
		}));
		KnowledgeListMF.stoneBricksR.add(
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.mud_pavement, 4), new Object[]
		{
			"CC",
			"CC",
			'C', Blocks.dirt
		}));
		
		KnowledgeListMF.framedGlassR =
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.framed_glass), new Object[]
		{
			"PGP",
			'P', ComponentListMF.plank,
			'G', Blocks.glass
		});
		KnowledgeListMF.windowR =
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.window), new Object[]
		{
			" P ",
			"PGP",
			" P ",
			'P', ComponentListMF.plank,
			'G', Blocks.glass
		});
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.framed_pane, 16), new Object[]
		{
			"GGG",
			"GGG",
			'G', BlockListMF.framed_glass
		});
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.window_pane, 16), new Object[]
		{
			"GGG",
			"GGG",
			'G', BlockListMF.window
		});
		KnowledgeListMF.thatchR =
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.thatch, 1), new Object[]
		{
			"HH",
			"HH",
			'H', new ItemStack(Blocks.tallgrass, 1, 1)
		});
		KnowledgeListMF.clayWallR =
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.clayWall, 4), new Object[]
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
