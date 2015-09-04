package minefantasy.mf2.knowledge;

import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class KnowledgeCostRegistry
{
	public static void init()
	{
		//ARTISANRY
		KnowledgeListMF.smeltBronze.setItems(new ItemStack(ComponentListMF.ingots[0], 4), new ItemStack(ComponentListMF.ingots[1], 2), new ItemStack(Items.coal, 8));
		KnowledgeListMF.smeltIron.setItems(new ItemStack(Items.iron_ingot, 8), new ItemStack(Items.coal, 12));
		KnowledgeListMF.blastfurn.setItems(new ItemStack(Items.iron_ingot, 16), new ItemStack(Blocks.stone, 32), new ItemStack(Items.coal, 32), new ItemStack(ComponentListMF.kaolinite, 8));
		KnowledgeListMF.encrusted.setItems(new ItemStack(Items.diamond, 4), new ItemStack(ComponentListMF.ingots[4], 8));
		KnowledgeListMF.smeltBlackSteel.setItems(new ItemStack(Blocks.obsidian, 8), new ItemStack(ComponentListMF.ingots[2], 16), new ItemStack(ComponentListMF.ingots[4], 16), new ItemStack(Items.coal, 16));
		KnowledgeListMF.smeltDragonforge.setItems(new ItemStack(ComponentListMF.ingots[7], 16), new ItemStack(ComponentListMF.dragon_heart), new ItemStack(Items.coal, 16));
		KnowledgeListMF.smeltRedSteel.setItems(new ItemStack(Items.gold_ingot, 16), new ItemStack(ComponentListMF.ingots[7], 8), new ItemStack(Items.coal, 16));
		KnowledgeListMF.smeltBlueSteel.setItems(new ItemStack(ComponentListMF.ingots[8], 16), new ItemStack(ComponentListMF.ingots[7], 8), new ItemStack(Items.coal, 16));
		KnowledgeListMF.smeltAdamant.setItems(new ItemStack(Items.gold_ingot, 16), new ItemStack(ComponentListMF.ingots[10], 12), new ItemStack(Items.coal, 64), new ItemStack(BlockListMF.oreMythic));
		KnowledgeListMF.smeltMithril.setItems(new ItemStack(ComponentListMF.ingots[8], 16), new ItemStack(ComponentListMF.ingots[12], 12), new ItemStack(Items.coal, 64), new ItemStack(BlockListMF.oreMythic));
	
		KnowledgeListMF.smeltMaster.setItems(new ItemStack(ComponentListMF.ingots[13], 4), new ItemStack(ComponentListMF.ingots[14], 4), new ItemStack(Blocks.obsidian, 32), new ItemStack(BlockListMF.oreMythic, 4));
		KnowledgeListMF.smeltIgnotumite.setItems(new ItemStack(Items.gold_ingot, 16), new ItemStack(ComponentListMF.ingots[13], 4), new ItemStack(ComponentListMF.talisman_greater));
		KnowledgeListMF.smeltMithium.setItems(new ItemStack(ComponentListMF.ingots[8], 16), new ItemStack(ComponentListMF.ingots[14], 4), new ItemStack(ComponentListMF.talisman_greater));
		KnowledgeListMF.smeltEnderforge.setItems(new ItemStack(ComponentListMF.ingots[13], 2), new ItemStack(ComponentListMF.ingots[14], 2), new ItemStack(Items.ender_pearl, 16), new ItemStack(ComponentListMF.talisman_greater));
		
		KnowledgeListMF.crucible2.setItems(new ItemStack(ComponentListMF.kaolinite, 16), new ItemStack(Items.clay_ball, 32), new ItemStack(Blocks.stone, 32), new ItemStack(Items.coal, 32));
		KnowledgeListMF.repair_basic.setItems(new ItemStack(Items.iron_ingot, 16), new ItemStack(Items.string, 8), new ItemStack(ComponentListMF.ingots[2], 16), new ItemStack(Items.leather, 16));
		KnowledgeListMF.repair_advanced.setItems(new ItemStack(ComponentListMF.ingots[4], 16), new ItemStack(Items.slime_ball, 4), new ItemStack(BlockListMF.repair_basic, 4), new ItemStack(Items.leather, 16));
		KnowledgeListMF.repair_ornate.setItems(new ItemStack(ComponentListMF.ingots[8], 8), new ItemStack(Items.gold_ingot, 8), new ItemStack(BlockListMF.repair_advanced, 4), new ItemStack(Items.leather, 16));
		
		KnowledgeListMF.craftArmourLight.setItems(new ItemStack(Items.leather, 8));
		KnowledgeListMF.craftArmourMedium.setItems(new ItemStack(Items.leather, 16), new ItemStack(ComponentListMF.ingots[2], 4));
		KnowledgeListMF.craftArmourHeavy.setItems(new ItemStack(Items.leather, 16), new ItemStack(Blocks.wool, 8), new ItemStack(Items.iron_ingot, 16));
		
		KnowledgeListMF.craftOrnateWeapons.setItems(new ItemStack(Items.gold_ingot, 4), new ItemStack(ComponentListMF.ingots[8], 4));
		KnowledgeListMF.craftAdvOrnateWeapons.setItems(new ItemStack(Items.gold_ingot, 8), new ItemStack(ComponentListMF.ingots[8], 8));
		
		KnowledgeListMF.arrowsBodkin.setItems(new ItemStack(Items.feather, 8), new ItemStack(ComponentListMF.plank, 16));
		KnowledgeListMF.arrowsBroad.setItems(new ItemStack(Items.feather, 12), new ItemStack(ComponentListMF.plank, 32));
		
		//ENGINEERING
		KnowledgeListMF.blackpowder.setItems(new ItemStack(ComponentListMF.sulfur, 8), new ItemStack(ComponentListMF.nitre, 16), new ItemStack(Items.coal, 16), new ItemStack(Items.gunpowder, 16));
		KnowledgeListMF.advblackpowder.setItems(new ItemStack(ComponentListMF.blackpowder, 64), new ItemStack(Items.redstone, 32), new ItemStack(Items.glowstone_dust, 16));
		
		KnowledgeListMF.stickybomb.setItems(new ItemStack(Items.slime_ball, 16));
		KnowledgeListMF.shrapnel.setItems(new ItemStack(ComponentListMF.blackpowder, 16), new ItemStack(Items.flint, 16));
		KnowledgeListMF.firebomb.setItems(new ItemStack(ComponentListMF.dragon_heart), new ItemStack(ComponentListMF.blackpowder, 24), new ItemStack(Items.magma_cream, 16));
		
		KnowledgeListMF.mineCeramic.setItems(new ItemStack(ComponentListMF.blackpowder, 16), new ItemStack(Items.clay_ball, 16));
		KnowledgeListMF.bombIron.setItems(new ItemStack(ComponentListMF.blackpowder, 16), new ItemStack(Items.iron_ingot, 16));
		KnowledgeListMF.mineIron.setItems(new ItemStack(ComponentListMF.blackpowder, 16), new ItemStack(Items.iron_ingot, 16));
		KnowledgeListMF.bombObsidian.setItems(new ItemStack(ComponentListMF.blackpowder, 32), new ItemStack(Blocks.obsidian, 16));
		KnowledgeListMF.mineObsidian.setItems(new ItemStack(ComponentListMF.blackpowder, 32), new ItemStack(Blocks.obsidian, 16));
		KnowledgeListMF.bombCrystal.setItems(new ItemStack(ComponentListMF.blackpowder, 32), new ItemStack(Blocks.glass, 16), new ItemStack(Items.diamond, 4));
		KnowledgeListMF.mineCrystal.setItems(new ItemStack(ComponentListMF.blackpowder, 32), new ItemStack(Blocks.glass, 16), new ItemStack(Items.diamond, 4));
	
		//PROVISIONING
		KnowledgeListMF.bandageadv.setItems(new ItemStack(ToolListMF.bandage_wool, 8), new ItemStack(Items.leather, 16), new ItemStack(Blocks.wool, 12));
		
		KnowledgeListMF.sweetroll.setItems(new ItemStack(Items.wheat, 16), new ItemStack(Items.sugar, 16), new ItemStack(FoodListMF.berries, 4));
		KnowledgeListMF.cake.setItems(new ItemStack(Items.wheat, 32), new ItemStack(Items.sugar, 32), new ItemStack(Items.egg, 2));
		KnowledgeListMF.carrotcake.setItems(new ItemStack(Items.wheat, 64), new ItemStack(Items.sugar, 32), new ItemStack(Items.carrot, 16), new ItemStack(Items.egg, 4));
		KnowledgeListMF.chococake.setItems(new ItemStack(Items.wheat, 64), new ItemStack(Items.sugar, 64), new ItemStack(Items.egg, 4));
		KnowledgeListMF.bfcake.setItems(new ItemStack(Items.wheat, 128), new ItemStack(Items.sugar, 128), new ItemStack(Items.egg, 8), new ItemStack(FoodListMF.berries, 32), new ItemStack(FoodListMF.berriesJuicy, 4));
		
		KnowledgeListMF.berrypie.setItems(new ItemStack(Items.wheat, 32), new ItemStack(Items.sugar, 8), new ItemStack(FoodListMF.berries, 16));
		KnowledgeListMF.applepie.setItems(new ItemStack(Items.wheat, 48), new ItemStack(Items.sugar, 12), new ItemStack(Items.apple, 16));
		KnowledgeListMF.meatpie.setItems(new ItemStack(Items.wheat, 32), new ItemStack(FoodListMF.generic_meat_mince_cooked, 16));
		KnowledgeListMF.shepardpie.setItems(new ItemStack(Items.wheat, 64), new ItemStack(FoodListMF.generic_meat_mince_cooked, 32), new ItemStack(Items.potato, 32));
		
		KnowledgeListMF.jerky.setItems(new ItemStack(FoodListMF.generic_meat_strip_cooked, 4), new ItemStack(FoodListMF.salt, 4));
		KnowledgeListMF.sandwitch.setItems(new ItemStack(FoodListMF.generic_meat_cooked, 8), new ItemStack(FoodListMF.breadroll, 4));
		KnowledgeListMF.cheeseroll.setItems(new ItemStack(FoodListMF.cheese_slice, 8), new ItemStack(FoodListMF.breadroll, 8));
	}
}
