package minefantasy.mf2.recipe;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SmeltingRecipesMF {

	public static void init() 
	{
		GameRegistry.addSmelting(BlockListMF.anvil[0], new ItemStack(ComponentListMF.ingots[2], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[1], new ItemStack(Items.iron_ingot, 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[2], new ItemStack(ComponentListMF.ingots[4], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[3], new ItemStack(ComponentListMF.ingots[7], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[4], new ItemStack(ComponentListMF.ingots[14], 6), 0.0F);
		GameRegistry.addSmelting(BlockListMF.anvil[5], new ItemStack(ComponentListMF.ingots[12], 6), 0.0F);
		
		
		GameRegistry.addSmelting(BlockListMF.oreCopper, new ItemStack(ComponentListMF.ingots[0]), 0.4F);
		GameRegistry.addSmelting(BlockListMF.oreTin, new ItemStack(ComponentListMF.ingots[1]), 0.5F);
		GameRegistry.addSmelting(BlockListMF.oreSilver, new ItemStack(ComponentListMF.ingots[9]), 0.9F);
		
		GameRegistry.addSmelting(BlockListMF.oreBorax, new ItemStack(ComponentListMF.flux_strong, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreKaolinite, new ItemStack(ComponentListMF.kaolinite), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreNitre, new ItemStack(ComponentListMF.nitre, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreSulfur, new ItemStack(ComponentListMF.sulfur, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreClay, new ItemStack(Items.clay_ball, 4), 0.25F);
		
		GameRegistry.addSmelting(ComponentListMF.ingots[6], new ItemStack(ComponentListMF.ingots[7]), 0.8F);
		
		GameRegistry.addSmelting(ComponentListMF.fireclay_brick, new ItemStack(ComponentListMF.strong_brick), 0.1F);
		GameRegistry.addSmelting(ComponentListMF.bomb_casing_uncooked, new ItemStack(ComponentListMF.bomb_casing), 0F);
		GameRegistry.addSmelting(ComponentListMF.mine_casing_uncooked, new ItemStack(ComponentListMF.mine_casing), 0F);
		
		//ALLOY
		MineFantasyAPI.addRatioAlloy(3, new ItemStack(ComponentListMF.ingots[2], 3), new Object[]{
			ComponentListMF.ingots[0], ComponentListMF.ingots[0], ComponentListMF.ingots[1]
		});
		
		MineFantasyAPI.addRatioAlloy(3, new ItemStack(ComponentListMF.ingots[10]), new Object[]{
			Items.gold_ingot, Items.gold_ingot, ComponentListMF.flux_strong
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
				MineFantasyAPI.addRatioAlloy(2, new ItemStack(ComponentListMF.ingots[6], 2), 1, new Object[]{
					steel, steel, bronze, bronze, Blocks.obsidian
				});
			}
		}
		for(ItemStack ingot: pigs)
		{
			MineFantasyAPI.addRatioAlloy(9, new ItemStack(ComponentListMF.ingots[4]), 1, new Object[]
			{
				ingot
			});
		}
		for(ItemStack steel: blacks)
		{
			MineFantasyAPI.addRatioAlloy(2, new ItemStack(ComponentListMF.ingots[11]), 1, new Object[]
			{
				steel, Items.gold_ingot, Items.redstone, ComponentListMF.flux_strong, Items.blaze_powder
			});
		for(ItemStack silver: silvers)
		{
			MineFantasyAPI.addRatioAlloy(2, new ItemStack(ComponentListMF.ingots[13]), 1, new Object[]
			{
				steel, silver, ComponentListMF.diamond_dust, ComponentListMF.flux_strong, Items.blaze_powder
			});
		}
		}
		MineFantasyAPI.addRatioAlloy(2, new ItemStack(ComponentListMF.ingots[15], 2), 1, new Object[]
		{
			ComponentListMF.ingots[12], BlockListMF.oreMythic, Items.gold_ingot, Items.gold_ingot
		});
		for(ItemStack silver: silvers)
		{
			MineFantasyAPI.addRatioAlloy(2, new ItemStack(ComponentListMF.ingots[16], 2), 1, new Object[]
			{
				ComponentListMF.ingots[14], BlockListMF.oreMythic, silver, silver
			});
		}
		MineFantasyAPI.addBlastFurnaceRecipe(ComponentListMF.ingots[6], new ItemStack(ComponentListMF.ingots[7]));
		MineFantasyAPI.addBlastFurnaceRecipe(ComponentListMF.ingots[11], new ItemStack(ComponentListMF.ingots[12]));
		MineFantasyAPI.addBlastFurnaceRecipe(ComponentListMF.ingots[13], new ItemStack(ComponentListMF.ingots[14]));
	}

}
