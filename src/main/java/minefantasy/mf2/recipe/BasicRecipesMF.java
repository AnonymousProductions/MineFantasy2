package minefantasy.mf2.recipe;

import java.util.HashMap;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.crafting.tanning.TanningRecipe;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ArmourListMF;
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
		
		
		KnowledgeListMF.hideHelmR = 
		GameRegistry.addShapedRecipe(ArmourListMF.armour(ArmourListMF.leather, 0, 0), new Object[]
		{
			"H",
			"C",
			"H",
			'H', ComponentListMF.hideSmall,
			'C', Blocks.wool,
		});
		KnowledgeListMF.hideChestR = 
		GameRegistry.addShapedRecipe(ArmourListMF.armour(ArmourListMF.leather, 0, 1), new Object[]
		{
			"H",
			"C",
			'H', ComponentListMF.hideLarge,
			'C', Blocks.wool,
		});
		KnowledgeListMF.hideLegsR = 
		GameRegistry.addShapedRecipe(ArmourListMF.armour(ArmourListMF.leather, 0, 2), new Object[]
		{
			"H",
			"C",
			'H', ComponentListMF.hideMedium,
			'C', Blocks.wool,
		});
		KnowledgeListMF.hideBootsR = 
		GameRegistry.addShapedRecipe(ArmourListMF.armour(ArmourListMF.leather, 0, 3), new Object[]
		{
			"H",
			"C",
			'H', ComponentListMF.hideSmall,
			'C', Blocks.wool,
		});
		KnowledgeListMF.apronRecipe = 
		GameRegistry.addShapedRecipe(new ItemStack(ArmourListMF.leatherapron), new Object[]
		{
			"LCL",
			" L ",
			'L', Items.leather,
			'C', Items.coal,
		});
		
		KnowledgeListMF.researchTableRecipe = 
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.research), new Object[]
		{
			"B",
			"T",
			'B', ToolListMF.researchBook,
			'T', BlockListMF.carpenter,
		});
		
		KnowledgeListMF.plantOilR = 
		GameRegistry.addShapedRecipe(new ItemStack(ComponentListMF.plant_oil, 4), new Object[]
		{
			" B ",
			"BFB",
			" B ",
			'F', Items.wheat_seeds,
			'B', FoodListMF.jug_empty
		});
		KnowledgeListMF.waterJugR = 
		GameRegistry.addShapedRecipe(new ItemStack(FoodListMF.jug_water, 4), new Object[]
		{
			" B ",
			"BWB",
			" B ",
			'W', Items.water_bucket,
			'B', FoodListMF.jug_empty
		});
		KnowledgeListMF.sugarRecipe = 
		GameRegistry.addShapedRecipe(new ItemStack(FoodListMF.sugarpot), new Object[]
		{
			"S",
			"S",
			"B",
			'S', Items.sugar,
			'B', ComponentListMF.clay_pot,
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.sugar, 2), new Object[]
		{
			FoodListMF.sugarpot,
		});
		KnowledgeListMF.milkJugR = 
		GameRegistry.addShapedRecipe(new ItemStack(FoodListMF.jug_milk, 4), new Object[]
		{
			" B ",
			"BMB",
			" B ",
			'M', Items.milk_bucket,
			'B', FoodListMF.jug_empty
		});
		GameRegistry.addShapedRecipe(new ItemStack(Items.milk_bucket), new Object[]
		{
			" B ",
			"BMB",
			" B ",
			'M', Items.bucket,
			'B', FoodListMF.jug_milk
		});
		GameRegistry.addShapedRecipe(new ItemStack(Items.water_bucket), new Object[]
		{
			" B ",
			"BMB",
			" B ",
			'M', Items.bucket,
			'B', FoodListMF.jug_water
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
		GameRegistry.addRecipe(new RecipeSyringe());
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
			BaseMaterialMF material = BaseMaterialMF.getMaterial(BlockListMF.metalBlocks[id]);
			
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
		GameRegistry.addShapedRecipe(new ItemStack(ComponentListMF.fireclay, 4), new Object[]
		{
			" C ",
			"CDC",
			" C ",
			
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
		TanningRecipe.addRecipe(Items.leather, mat.craftTimeModifier*2F, -1, "shears", new ItemStack(ComponentListMF.leather_strip, 4));
		
		KnowledgeListMF.artBookR =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_artisanry), new Object[]
		{
			"T",
			"D",
			"B",
			'T', ComponentListMF.talisman_lesser,
			'D', new ItemStack(Items.dye, 1, 1),
			'B', Items.book,
		});
		KnowledgeListMF.conBookR =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_construction), new Object[]
		{
			"T",
			"D",
			"B",
			'T', ComponentListMF.talisman_lesser,
			'D', new ItemStack(Items.dye, 1, 14),
			'B', Items.book,
		});
		KnowledgeListMF.proBookR =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_provisioning), new Object[]
		{
			"T",
			"D",
			"B",
			'T', ComponentListMF.talisman_lesser,
			'D', new ItemStack(Items.dye, 1, 2),
			'B', Items.book,
		});
		KnowledgeListMF.engBookR =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_engineering), new Object[]
		{
			"T",
			"D",
			"B",
			'T', ComponentListMF.talisman_lesser,
			'D', new ItemStack(Items.dye, 1, 12),
			'B', Items.book,
		});
		KnowledgeListMF.comBookR =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_combat), new Object[]
		{
			"T",
			"D",
			"B",
			'T', ComponentListMF.talisman_lesser,
			'D', new ItemStack(Items.dye, 1, 5),
			'B', Items.book,
		});
	}
}
