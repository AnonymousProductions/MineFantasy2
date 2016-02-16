package minefantasy.mf2.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.crafting.Salvage;
import minefantasy.mf2.api.crafting.tanning.TanningRecipe;
import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.ItemComponentMF;
import minefantasy.mf2.item.armour.ItemArmourMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CustomArmourListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class BasicRecipesMF
{
	public static void init()
	{
		TempRecipesMF.init();//TODO remove temp recipes
		ForgingRecipes.init();
		CarpenterRecipes.init();
		SmeltingRecipesMF.init();
		SalvageRecipes.init();
		
		ArrayList<CustomMaterial> wood = CustomMaterial.getList("wood");
		Iterator iteratorWood = wood.iterator();
		while(iteratorWood.hasNext())
    	{
    		CustomMaterial customMat = (CustomMaterial) iteratorWood.next();	
    		assembleWoodVariations(customMat);
    	}
		
		
		
		
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
		
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 0, 0), new ItemStack(ComponentListMF.hideSmall, 2), Blocks.wool);
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 0, 1), ComponentListMF.hideLarge, Blocks.wool);
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 0, 2), ComponentListMF.hideMedium, Blocks.wool);
		Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 0, 3), ComponentListMF.hideSmall, Blocks.wool);
		
		KnowledgeListMF.apronRecipe = 
		GameRegistry.addShapedRecipe(new ItemStack(ArmourListMF.leatherapron), new Object[]
		{
			"LCL",
			" L ",
			'L', Items.leather,
			'C', Items.coal,
		});
		Salvage.addSalvage(ArmourListMF.leatherapron, new ItemStack(Items.leather, 3), Items.coal);
		
		KnowledgeListMF.researchTableRecipe = 
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.research), new Object[]
		{
			"B",
			"T",
			'B', ToolListMF.researchBook,
			'T', BlockListMF.carpenter,
		});
		KnowledgeListMF.bSalvageR = 
		GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.salvage_basic), new Object[]
		{
			"SFS",
			"PWP",
			'W', Blocks.crafting_table,
			'S', Blocks.stone,
			'F', Items.flint,
			'P', ((ItemComponentMF)ComponentListMF.plank).construct("OakWood")
		});
		Salvage.addSalvage(BlockListMF.salvage_basic, Items.flint, new ItemStack(Blocks.stone, 2), new ItemStack(((ItemComponentMF)ComponentListMF.plank).construct("OakWood").getItem(), 2), Blocks.crafting_table);
		
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
		Salvage.addSalvage(BlockListMF.forge, new ItemStack(Blocks.stone, 4), Items.coal);
		Salvage.addSalvage(BlockListMF.anvilStone, new ItemStack(Blocks.stone, 6));
		
		
		GameRegistry.addRecipe(new RecipeArmourDyeMF());
		GameRegistry.addRecipe(new RecipeSyringe());
		//Just a way on making the overpowered gunpowder from black powder
		GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder), new Object[]
		{
			new ItemStack(ComponentListMF.blackpowder),
			new ItemStack(ComponentListMF.blackpowder),
			new ItemStack(ComponentListMF.nitre),
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
		
		
		KnowledgeListMF.artBook2R =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_artisanry2), new Object[]
		{
			"T",
			"B",
			'T', ComponentListMF.talisman_greater,
			'B', ToolListMF.skillbook_artisanry,
		});
		KnowledgeListMF.conBook2R =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_construction2), new Object[]
		{
			"T",
			"B",
			'T', ComponentListMF.talisman_greater,
			'B', ToolListMF.skillbook_construction,
		});
		KnowledgeListMF.proBook2R =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_provisioning2), new Object[]
		{
			"T",
			"B",
			'T', ComponentListMF.talisman_greater,
			'B', ToolListMF.skillbook_provisioning,
		});
		KnowledgeListMF.engBook2R =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_engineering2), new Object[]
		{
			"T",
			"B",
			'T', ComponentListMF.talisman_greater,
			'B', ToolListMF.skillbook_engineering,
		});
		KnowledgeListMF.comBook2R =
		GameRegistry.addShapedRecipe(new ItemStack(ToolListMF.skillbook_combat2), new Object[]
		{
			"T",
			"B",
			'T', ComponentListMF.talisman_greater,
			'B', ToolListMF.skillbook_combat,
		});
		GameRegistry.addShapedRecipe(((ItemComponentMF)ComponentListMF.plank).construct("OakWood"), new Object[]
		{
			"S",
			"S",
			'S', Items.stick,
		});
	}
	

	private static void assembleWoodVariations(CustomMaterial material) {
		// TODO 
		if(material.name != "RefinedWood"){
			KnowledgeListMF.refinedPlankR = 
					GameRegistry.addShapedRecipe(((ItemComponentMF)ComponentListMF.plank).construct("RefinedWood"), new Object[]
					{
						"C",
						"P",
						'C', ComponentListMF.plant_oil,
						'P', ((ItemComponentMF)ComponentListMF.plank).construct(material.name),
					});
		
			KnowledgeListMF.tannerRecipe = 
					GameRegistry.addShapedRecipe(new ItemStack(BlockListMF.tanner), new Object[]{
						"PPP",
						"P P",
						"PPP",
						'P', ((ItemComponentMF)ComponentListMF.plank).construct(material.name) ,
					});
			Salvage.addSalvage(BlockListMF.tanner, new ItemStack(((ItemComponentMF)ComponentListMF.plank).construct("OakWood").getItem(), 8));
		}
		
		KnowledgeListMF.plankRecipe =
		new ShapedOreRecipe(new ItemStack(((ItemComponentMF)ComponentListMF.plank).construct(material.name).getItem(),8), new Object[]
		{
			"S",
			"S",
			'S', "PlankWood",//OreDictionary
		});
		GameRegistry.addRecipe(KnowledgeListMF.plankRecipe);
		
		
		KnowledgeListMF.stickRecipe =
		GameRegistry.addShapedRecipe(new ItemStack(Items.stick, 4), new Object[]
		{
			"S",
			"S",
			'S', ((ItemComponentMF)ComponentListMF.plank).construct(material.name),
		});
		
		//((ItemComponentMF)ComponentListMF.plank).construct(material.name);	
	}
}
