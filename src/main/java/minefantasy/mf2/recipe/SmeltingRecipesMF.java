package minefantasy.mf2.recipe;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.refine.Alloy;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.ItemComponentMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SmeltingRecipesMF {

	public static void init() 
	{
		KnowledgeListMF.reStone =
		MineFantasyAPI.addRatioAlloy(3, new ItemStack(BlockListMF.reinforced_stone), 1, new Object[]
		{
			Blocks.stone, ComponentListMF.kaolinite_dust, ComponentListMF.obsidian_dust
		});
		
		refineRawOre(ComponentListMF.oreCopper, ComponentListMF.ingots[0]);
		refineRawOre(ComponentListMF.oreTin, ComponentListMF.ingots[1]);
		refineRawOre(ComponentListMF.oreIron, Items.iron_ingot);
		refineRawOre(ComponentListMF.oreSilver, ComponentListMF.ingots[8]);
		refineRawOre(ComponentListMF.oreGold, Items.gold_ingot);
		
		MineFantasyAPI.addRatioAlloy(3, new ItemStack(ComponentListMF.ingots[2], 3), 0, new Object[]
		{
			ComponentListMF.oreCopper, ComponentListMF.oreCopper, ComponentListMF.oreTin
		});
		
		GameRegistry.addSmelting(BlockListMF.anvil[0], new ItemStack(ComponentListMF.ingots[2], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[1], new ItemStack(Items.iron_ingot, 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[2], new ItemStack(ComponentListMF.ingots[4], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[3], new ItemStack(ComponentListMF.ingots[7], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[4], new ItemStack(ComponentListMF.ingots[12], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[5], new ItemStack(ComponentListMF.ingots[10], 6), 0.0F);
		
		
		GameRegistry.addSmelting(BlockListMF.oreCopper, new ItemStack(ComponentListMF.ingots[0]), 0.4F);
		GameRegistry.addSmelting(BlockListMF.oreTin, new ItemStack(ComponentListMF.ingots[1]), 0.5F);
		GameRegistry.addSmelting(BlockListMF.oreSilver, new ItemStack(ComponentListMF.ingots[8]), 0.9F);
		
		GameRegistry.addSmelting(BlockListMF.oreBorax, new ItemStack(ComponentListMF.flux_strong, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreKaolinite, new ItemStack(ComponentListMF.kaolinite), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreNitre, new ItemStack(ComponentListMF.nitre, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreSulfur, new ItemStack(ComponentListMF.sulfur, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreClay, new ItemStack(Items.clay_ball, 4), 0.25F);
		
		GameRegistry.addSmelting(ComponentListMF.fireclay_brick, new ItemStack(ComponentListMF.strong_brick), 0.1F);
		GameRegistry.addSmelting(ComponentListMF.bomb_casing_uncooked, new ItemStack(ComponentListMF.bomb_casing), 0F);
		GameRegistry.addSmelting(ComponentListMF.mine_casing_uncooked, new ItemStack(ComponentListMF.mine_casing), 0F);
		
		//ALLOY
		KnowledgeListMF.bronze = 
		MineFantasyAPI.addRatioAlloy(3, new ItemStack(ComponentListMF.ingots[2], 3), new Object[]{
			ComponentListMF.ingots[0], ComponentListMF.ingots[0], ComponentListMF.ingots[1]
		});
		
		ArrayList<ItemStack> pigs = OreDictionary.getOres("ingotPigIron");
		ArrayList<ItemStack> steels = OreDictionary.getOres("ingotSteel");
		ArrayList<ItemStack> bronzes = OreDictionary.getOres("ingotBronze");
		ArrayList<ItemStack> silvers = OreDictionary.getOres("ingotSilver");
		ArrayList<ItemStack> blacks = OreDictionary.getOres("ingotBlackSteel");
		
		for(ItemStack steel: steels)
		{
			for(ItemStack bronze: bronzes)
			{
				Alloy[] alloy =
				MineFantasyAPI.addRatioAlloy(1, new ItemStack(ComponentListMF.ingots[6], 2), 1, new Object[]{
					steel, steel, bronze, bronze, ComponentListMF.obsidian_dust
				});
				if(KnowledgeListMF.black == null)
				{
					KnowledgeListMF.black = alloy;
				}
			}
		}
		for(ItemStack ingot: pigs)
		{
			Alloy[] alloy = 
			MineFantasyAPI.addRatioAlloy(9, new ItemStack(ComponentListMF.ingots[4]), 1, new Object[]
			{
				ingot
			});
			if(KnowledgeListMF.steel == null)
			{
				KnowledgeListMF.steel = alloy;
			}
		}
		for(ItemStack steel: blacks)
		{
			Alloy[] alloy = 
			MineFantasyAPI.addRatioAlloy(1, new ItemStack(ComponentListMF.ingots[9]), 1, new Object[]
			{
				steel, Items.gold_ingot, Items.redstone, ComponentListMF.flux_strong, Items.blaze_powder
			});
			if(KnowledgeListMF.red == null)
			{
				KnowledgeListMF.red = alloy;
			}
			for(ItemStack silver: silvers)
			{
				alloy = 
				MineFantasyAPI.addRatioAlloy(1, new ItemStack(ComponentListMF.ingots[11]), 1, new Object[]
				{
					steel, silver, new ItemStack(Items.dye, 1, 4), ComponentListMF.flux_strong, Items.blaze_powder
				});
				if(KnowledgeListMF.blue == null)
				{
					KnowledgeListMF.blue = alloy;
				}
			}
		}
		KnowledgeListMF.adamantium = 
		MineFantasyAPI.addRatioAlloy(2, new ItemStack(ComponentListMF.ingots[13], 2), 1, new Object[]
		{
			ComponentListMF.ingots[10], BlockListMF.oreMythic, Items.gold_ingot, Items.gold_ingot
		});
		for(ItemStack silver: silvers)
		{
			Alloy[] alloy = 
			MineFantasyAPI.addRatioAlloy(2, new ItemStack(ComponentListMF.ingots[14], 2), 1, new Object[]
			{
				ComponentListMF.ingots[12], BlockListMF.oreMythic, silver, silver
			});
			if(KnowledgeListMF.mithril == null)
			{
				KnowledgeListMF.mithril = alloy;
			}
		}
		MineFantasyAPI.addBlastFurnaceRecipe(ComponentListMF.ingots[6], new ItemStack(ComponentListMF.ingots[7]));
		MineFantasyAPI.addBlastFurnaceRecipe(ComponentListMF.ingots[9], new ItemStack(ComponentListMF.ingots[10]));
		MineFantasyAPI.addBlastFurnaceRecipe(ComponentListMF.ingots[11], new ItemStack(ComponentListMF.ingots[12]));
	}

	private static void refineRawOre(Item ore, Item ingot)
	{
		GameRegistry.addSmelting(ore, new ItemStack(ingot), 0F);
	}

}
