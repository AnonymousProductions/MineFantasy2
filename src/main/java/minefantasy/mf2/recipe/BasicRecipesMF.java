package minefantasy.mf2.recipe;

import java.util.HashMap;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BasicRecipesMF
{
	public static void init()
	{
		TempRecipesMF.init();//TODO remove temp recipes
		ForgingRecipes.init();
		CarpenterRecipes.init();
		
		GameRegistry.addRecipe(new ItemStack(ToolListMF.researchBook), new Object[]
		{
			"H",
			"B",
			"B",
			'H', ToolListMF.hammers[2],
			'B', Items.book,
		});

		GameRegistry.addRecipe(new RecipeArmourDyeMF());
		GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.blackpowder, 4), new Object[]
		{
			new ItemStack(ComponentListMF.coalDust),
			new ItemStack(ComponentListMF.coalDust),
			new ItemStack(ComponentListMF.sulfur),
			new ItemStack(ComponentListMF.nitre),
		});
		//Just a way on making the overpowered gunpowder from black powder
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder), new Object[]
		{
			new ItemStack(ComponentListMF.blackpowder),
			new ItemStack(ComponentListMF.blackpowder),
			new ItemStack(ComponentListMF.nitre),
			//new ItemStack(ComponentListMF.flux_strong),
		});
		GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.sharp_rock), new Object[]
		{
			Blocks.dirt,
		});
		
		GameRegistry.addRecipe(new ItemStack(ComponentListMF.plank), new Object[]
		{
			"S",
			"S",
			'S', Items.stick,
		});
		GameRegistry.addRecipe(new ItemStack(Items.stick, 4), new Object[]
		{
			"S",
			"S",
			'S', ComponentListMF.plank,
		});
		
		MineFantasyAPI.removeAllRecipes(Items.pumpkin_pie);
		
		GameRegistry.addSmelting(BlockListMF.oreCopper, new ItemStack(ComponentListMF.ingots[0]), 0.4F);
		GameRegistry.addSmelting(BlockListMF.oreTin, new ItemStack(ComponentListMF.ingots[1]), 0.5F);
		GameRegistry.addSmelting(BlockListMF.oreSilver, new ItemStack(ComponentListMF.ingots[9]), 0.9F);
		
		GameRegistry.addSmelting(BlockListMF.oreBorax, new ItemStack(ComponentListMF.flux_strong, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreKaolinite, new ItemStack(ComponentListMF.kaolinite), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreNitre, new ItemStack(ComponentListMF.nitre, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreSulfur, new ItemStack(ComponentListMF.sulfur, 4), 0.25F);
		GameRegistry.addSmelting(BlockListMF.oreClay, new ItemStack(Items.clay_ball, 4), 0.25F);
		
		GameRegistry.addSmelting(ComponentListMF.bomb_casing_uncooked, new ItemStack(ComponentListMF.bomb_casing), 0F);
		GameRegistry.addSmelting(ComponentListMF.mine_casing_uncooked, new ItemStack(ComponentListMF.mine_casing), 0F);
		
		for(int id = 0; id < BlockListMF.metalBlocks.length; id ++)
		{
			BaseMaterialMF material = BlockListMF.metalBlocks[id];
			
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				GameRegistry.addRecipe(new ItemStack(BlockListMF.storage[id]), new Object[]
				{
					"III",
					"III",
					"III",
					'I', ingot
				});
				GameRegistry.addShapelessRecipe(new ItemStack(ingot.getItem(), 9), new Object[]
				{
					BlockListMF.storage[id]
				});
			}
		}
		IRecipe[] anvilRecs = new IRecipe[BlockListMF.anvils.length];
		for(int id = 1; id < BlockListMF.anvils.length; id ++)
		{
			BaseMaterialMF material = BlockListMF.anvils[id];
			
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				IRecipe recipe = 
				GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.anvil[id]), new Object[]
				{
					" II",
					"III",
					" I ",
					'I', ingot
				});
				anvilRecs[id] = recipe;
			}
		}
		recipeMap.put("anvilCrafting", anvilRecs);
		
		GameRegistry.addRecipe(new ItemStack(BlockListMF.bombBench), new Object[]
		{
			"ICI",
			"III",
			"III",
			'C', BlockListMF.carpenter,
			'I', Items.iron_ingot,
		});
	}
	public static final HashMap<String, IRecipe[]>recipeMap = new HashMap<String, IRecipe[]>();
}
