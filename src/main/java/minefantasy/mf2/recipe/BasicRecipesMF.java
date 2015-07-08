package minefantasy.mf2.recipe;

import java.util.HashMap;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.crafting.tanning.TanningRecipe;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
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
		SmeltingRecipesMF.init();
		
		KnowledgeListMF.plantOilR = 
		GameRegistry.addShapedRecipe(new ItemStack(ComponentListMF.plant_oil, 4), new Object[]
		{
			" B ",
			"BFB",
			" B ",
			'F', Items.wheat_seeds,
			'B', Items.glass_bottle,
		});
		KnowledgeListMF.refinedPlankR = 
		GameRegistry.addShapedRecipe(new ItemStack(ComponentListMF.plankRefined), new Object[]
		{
			"C",
			"P",
			'C', ComponentListMF.plant_oil,
			'P', ComponentListMF.plank,
		});
		
		KnowledgeListMF.tannerRecipe = 
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.tanner), new Object[]{
			"PPP",
			"P P",
			"PPP",
			'P', ComponentListMF.plank,
		});
		
		KnowledgeListMF.stoneAnvilRecipe = 
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.anvilStone), new Object[]
		{
			"SS ",
			"SSS",
			" S ",
			'S', Blocks.stone
		});
		KnowledgeListMF.forgeRecipe = 
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.forge), new Object[]
		{
			"S S",
			"SCS",
			'C', Items.coal,
			'S', Blocks.stone
		});
		
		GameRegistry.addRecipe(new RecipeArmourDyeMF());
		//Just a way on making the overpowered gunpowder from black powder
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder), new Object[]
		{
			new ItemStack(ComponentListMF.blackpowder),
			new ItemStack(ComponentListMF.blackpowder),
			new ItemStack(ComponentListMF.nitre),
			//new ItemStack(ComponentListMF.flux_strong),
		});
		
		KnowledgeListMF.plankRecipe =
		GameRegistry.addShapedRecipe(new ItemStack(ComponentListMF.plank), new Object[]
		{
			"S",
			"S",
			'S', Items.stick,
		});
		KnowledgeListMF.stickRecipe =
		GameRegistry.addShapedRecipe(new ItemStack(Items.stick, 4), new Object[]
		{
			"S",
			"S",
			'S', ComponentListMF.plank,
		});
		
		MineFantasyAPI.removeAllRecipes(Items.pumpkin_pie);
		
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
		
		KnowledgeListMF.stoneHammerR = 
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.hammerStone), new Object[]{
			"C",
			"P",
			'C', Blocks.cobblestone,
			'P', ComponentListMF.plank,
		});
		KnowledgeListMF.stoneTongsR = 
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.tongsStone), new Object[]{
			"C ",
			"PC",
			'C', Blocks.cobblestone,
			'P', ComponentListMF.plank,
		});
		KnowledgeListMF.boneNeedleR = 
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.needleBone), new Object[]{
			"B",
			"B",
			'B', Items.bone
		});
		KnowledgeListMF.stoneKnifeR = 
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.knifeStone), new Object[]{
			"S",
			"S",
			"P",
			'S', Blocks.cobblestone,
			'P', ComponentListMF.plank,
		});
		
		MineFantasyAPI.addBlastFurnaceRecipe(ComponentListMF.iron_prep, new ItemStack(ComponentListMF.ingots[3]));
		
		KnowledgeListMF.fireclayR = 
		GameRegistry.addShapedRecipe(new ItemStack(ComponentListMF.fireclay), new Object[]
		{
			"D",
			"C",
			
			'D', ComponentListMF.kaolinite_dust,
			'C', Items.clay_ball
		});
		KnowledgeListMF.fireBrickR = 
		GameRegistry.addShapedRecipe(new ItemStack(ComponentListMF.fireclay_brick) , new Object[]
		{
			"C",
			
			'C', ComponentListMF.fireclay
		});
		KnowledgeListMF.fireBricksR = 
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.firebricks) , new Object[]
		{
			"BB",
			"BB",
			
			'B', ComponentListMF.strong_brick
		});
		BaseMaterialMF mat = BaseMaterialMF.iron;
		
		GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.hideSmall), new Object[]{
			ComponentListMF.rawhideSmall, ComponentListMF.flux
		});
		GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.hideMedium), new Object[]{
			ComponentListMF.rawhideMedium, ComponentListMF.flux
		});
		GameRegistry.addShapelessRecipe(new ItemStack(ComponentListMF.hideLarge), new Object[]{
			ComponentListMF.rawhideLarge, ComponentListMF.flux
		});
		
		TanningRecipe.addRecipe(ComponentListMF.hideSmall, mat.craftTimeModifier*5F, -1, new ItemStack(Items.leather));
		TanningRecipe.addRecipe(ComponentListMF.hideMedium, mat.craftTimeModifier*8F, -1, new ItemStack(Items.leather, 3));
		TanningRecipe.addRecipe(ComponentListMF.hideLarge, mat.craftTimeModifier*12F, -1, new ItemStack(Items.leather, 5));
	}
}
