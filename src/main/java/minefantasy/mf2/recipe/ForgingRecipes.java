package minefantasy.mf2.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import cpw.mods.fml.common.registry.GameRegistry;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.crafting.Salvage;
import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.api.rpg.Skill;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.config.ConfigCrafting;
import minefantasy.mf2.item.ItemComponentMF;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.material.BaseMaterialMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ForgingRecipes
{
	private static final Skill artisanry = SkillList.artisanry;
	private static final Skill engineering = SkillList.engineering;
	private static final Skill construction = SkillList.construction;
	public static void init()
	{
		for(int id = 0; id < ToolListMF.mats.length; id ++)
		{
			int time;
			BaseMaterialMF material = BaseMaterialMF.getMaterial(ToolListMF.mats[id]);
			if(canCraftMaterial(material))
			{
				//PICKS
				time = 15;
				Item tool;
				tool = ToolListMF.picks[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					String smeltMat = "smelt"+material.name;
					KnowledgeListMF.pickR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L I",
						"SSI",
						"L I",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 3), new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
					}
				}
				
				//AXES
				time = 12;
				tool = ToolListMF.axes[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.axeR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"LII",
						"SSI",
						"L  ",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 3), new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
					}
				}
				
				//SPADES
				time = 8;
				tool = ToolListMF.spades[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.spadeR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L  ",
						"SSI",
						"L  ",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, ingot.getItem(), new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
					}
				}
				
				//HOES
				time = 15;
				tool = ToolListMF.hoes[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.hoeR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L I",
						"SSI",
						"L  ",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 2), new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
					}
				}
				
				//SHEARS
				time = 12;
				tool = ToolListMF.shears[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.shearsR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						" I ",
						"SLI",
						" S ",
						'L', getLeather(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 2), new ItemStack(getHaft(material), 2), getStrips(material));
					}
				}
			
				//HAMMERS
				time = 10;
				tool = ToolListMF.hammers[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.hammerR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, -1, -1, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I",
						"L",
						"S",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, ingot.getItem(), getHaft(material), getStrips(material));
					}
				}
				
				//TONGS
				time = 10;
				tool = ToolListMF.tongs[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.tongsR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I ",
						" I",
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 2));
					}
				}
			
				//SAWS
				time = 20;
				tool = ToolListMF.saws[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.sawsR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"SIIII",
						"SIII ",
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 7), new ItemStack(getHaft(material), 2));
					}
				}
			
				//KNIVES
				time = 12;
				tool = ToolListMF.knives[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.knifeR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						" I",
						" I",
						"LS",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 2), getHaft(material), getStrips(material));
					}
				}
			
				//NEEDLES
				time = 5;
				tool = ToolListMF.needles[id];
				for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
				{
					KnowledgeListMF.needleR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I",
						"I",
						"I",
						"I",
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 4));
					}
				}
				
				if(id > 0)
				{
					//HVYPICKS
					time = 30;
					tool = ToolListMF.hvypicks[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.hvyPickR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"LRI ",
							"SSII",
							"LRII",
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
							'R', ComponentListMF.rivet,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 5), new ItemStack(ComponentListMF.rivet, 2), new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
						}
					}
					//HVYSHOVELS
					time = 20;
					tool = ToolListMF.hvyshovels[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.hvyShovelR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"LRII",
							"SSII",
							"LRII",
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
							'R', ComponentListMF.rivet,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 6), new ItemStack(ComponentListMF.rivet, 2), new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
						}
					}
					//HANDPICKS
					time = 12;
					tool = ToolListMF.handpicks[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.handpickR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"LI ",
							"SIR",
							"L  ",
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 2), ComponentListMF.rivet, getHaft(material), new ItemStack(getStrips(material), 2));
						}
					}
					//TROWS
					time = 15;
					tool = ToolListMF.trows[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.trowR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"L  ",
							"SIR",
							"L  ",
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, ingot.getItem(), ComponentListMF.rivet, getHaft(material), new ItemStack(getStrips(material), 2));
						}
					}
					//HVYHAMMERS
					time = 25;
					tool = ToolListMF.hvyHammers[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.hvyHammerR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, -1, -1, (int)(time*material.craftTimeModifier), new Object[]
						{
							" II",
							"RLI",
							" S ", 
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', ComponentListMF.plank,
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 3), ComponentListMF.rivet, getHaft(material), getStrips(material));
						}
					}
					//SCYTHES
					time = 30;
					tool = ToolListMF.scythes[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.scytheR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"  I ",
							"   I",
							" LRI",
							"SSSI",
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 4), ComponentListMF.rivet, new ItemStack(getHaft(material), 3), getStrips(material));
						}
					}
				}
			}
		}
		//WEAPONS
		for(int id = 0; id < ToolListMF.weaponMats.length; id ++)
		{
			int time;
			Item tool;
			BaseMaterialMF material = BaseMaterialMF.getMaterial(ToolListMF.weaponMats[id]);
			
			if(canCraftMaterial(material))
			{
				//BOLTS
				time = 2;
				tool = ToolListMF.bolts[id];
				for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
				{
					KnowledgeListMF.crossBoltR.add(
					MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(tool), "crossbows", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I",
						"F",
						
						'F', ComponentListMF.fletching,
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF)
					{
						Salvage.addSalvage(tool, ingot.getItem(), ComponentListMF.fletching);
					}
				}
				
				//ARROWS
				time = 5;
				tool = ComponentListMF.arrowheads[id];
				for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
				{
					KnowledgeListMF.arrowheadR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool, 4), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I ",
						"II",
						"I ",
						
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF)
					{
						Salvage.addSalvage(tool, ingot.getItem());
					}
				}
				
				if(material != BaseMaterialMF.ornate)
			{
				
				//DAGGERS
				time = 12;
				tool = ToolListMF.daggers[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.daggerR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L  ",
						"SII",
						"L  ",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 2), getHaft(material), new ItemStack(getStrips(material), 2));
					}
				}
				
				//SWORDS
				time = 25;
				tool = ToolListMF.swords[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.swordR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"LI  ",
						"SIII",
						"LI  ",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 5), getHaft(material), new ItemStack(getStrips(material), 2));
					}
				}
				
				//AXES
				time = 20;
				tool = ToolListMF.waraxes[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.waraxeR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"LII",
						"SSI",
						"L I",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 4), new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
					}
				}
				
				//MACES
				time = 18;
				tool = ToolListMF.maces[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.maceR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L II",
						"SSII",
						"L   ",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 4), new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
					}
				}
				
				//SPEARS
				time = 20;
				tool = ToolListMF.spears[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.spearR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						" L I ",
						"SSSSI",
						" L I ",
						'L', getStrips(material),
						'S', getHaft(material),
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 3), new ItemStack(getHaft(material), 4), new ItemStack(getStrips(material), 2));
					}
				}
				//BOWS
				time = 30;
				tool = ToolListMF.bows[id];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					KnowledgeListMF.bowR.add(
					MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"ISSSI",
						" ILI ",
						
						'L', getStrips(material),
						'S', Items.string,
						'I', ingot,
					}));
					if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
					{
						Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 4), new ItemStack(Items.string, 3), getStrips(material));
					}
				}
				
				if(id > 0)
				{
					//HALBEARDS
					time = 30;
					tool = ToolListMF.halbeards[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.halbeardR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							" LRII",
							"SSSSI",
							" LRI ",
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 4), new ItemStack(ComponentListMF.rivet, 2), new ItemStack(getHaft(material), 4), new ItemStack(getStrips(material), 2));
						}
					}
					
					//GREATSWORDS
					time = 35;
					tool = ToolListMF.greatswords[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.gswordR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"LIR  ",
							"SIIII",
							"LIR  ",
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 6), new ItemStack(ComponentListMF.rivet, 2), getHaft(material), new ItemStack(getStrips(material), 2));
						}
					}
					
					//KATANAS
					time = 40;
					tool = ToolListMF.katanas[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.katanaR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"LR   I",
							"SIIII ",
							"LI    ",
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 6), ComponentListMF.rivet, getHaft(material), new ItemStack(getStrips(material), 2));
						}
					}
					
					//BATTLEAXES
					time = 30;
					tool = ToolListMF.battleaxes[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.battleaxeR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"LRII",
							"SSSI",
							"LRII",
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 5), new ItemStack(ComponentListMF.rivet, 2), new ItemStack(getHaft(material), 3), new ItemStack(getStrips(material), 2));
						}
					}
					
					//WARHAMMERS
					time = 28;
					tool = ToolListMF.warhammers[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.whammerR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"L RII",
							"SSSII",
							"L R I",
							'R', ComponentListMF.rivet,
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 5), new ItemStack(ComponentListMF.rivet, 2), new ItemStack(getHaft(material), 3), new ItemStack(getStrips(material), 2));
						}
					}
					//LANCES
					time = 50;
					tool = ToolListMF.lances[id-1];
					for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
					{
						KnowledgeListMF.lanceR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "smelt"+material.name, true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"IR    ",
							"SIIIII",
							"IR    ",
							'R', ComponentListMF.rivet,
							'S', getHaft(material),
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
						{
							Salvage.addSalvage(tool, new ItemStack(ingot.getItem(), 7), new ItemStack(ComponentListMF.rivet, 2),getHaft(material));
						}
					}
					
					//ARROWS
					time = 8;
					tool = ComponentListMF.bodkinheads[id-1];
					for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
					{
						KnowledgeListMF.bodkinheadR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool, 4), "arrowsBodkin", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"I  ",
							" II",
							"I  ",
							
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF)
						{
							Salvage.addSalvage(tool, ingot.getItem());
						}
					}
					time = 8;
					tool = ComponentListMF.broadheads[id-1];
					for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
					{
						KnowledgeListMF.broadheadR.add(
						MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool, 4), "arrowsBroad", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
						{
							"I ",
							" I",
							" I",
							"I ",
							
							'I', ingot,
						}));
						if(ingot.getItem() instanceof ItemComponentMF)
						{
							Salvage.addSalvage(tool, ingot.getItem());
						}
					}
				}
			}
			}
		}
		
		
		//MISC
		
		BaseMaterialMF material;
		int time;
		time = 1;
		material = BaseMaterialMF.encrusted;
		
		KnowledgeListMF.obsidianHunkR = 
		MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.obsidian_rock, 4), "", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"D",
			'D', Blocks.obsidian,
		});
		KnowledgeListMF.diamondR = 
		MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.diamond_shards), "", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"D",
			'D', Items.diamond,
		});
		
		time = 3;
		for(ItemStack ingot: OreDictionary.getOres("ingotSteel"))
		{
			IAnvilRecipe recipe = 
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.ingots[5]), "smeltEncrusted", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"D",
				"I",
				'D', ComponentListMF.diamond_shards,
				'I', ingot,
			});
			if(ingot.getItem() instanceof ItemComponentMF)
			{
				Salvage.addSalvage(ComponentListMF.ingots[5], ComponentListMF.ingots[4], ComponentListMF.diamond_shards);
			}
			if(KnowledgeListMF.encrustedR == null)
			{
				KnowledgeListMF.encrustedR = recipe;
			}
		}
		
		//HUNKS
		time = 1;
		for(int id = 0; id < ComponentListMF.hunkMats.length; id ++)
		{
			material = BaseMaterialMF.getMaterial(ComponentListMF.hunkMats[id]);
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				KnowledgeListMF.hunkR.add(
				MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.hunks[id], 4), "", true, -1, -1, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I", 'I', ingot
				}));
				KnowledgeListMF.ingotR.add(
				MineFantasyAPI.addAnvilRecipe(null, ingot, "", true, -1, -1, (int)(time*material.craftTimeModifier), new Object[]
				{
					"II",
					"II",
					'I', ComponentListMF.hunks[id]
				}));
			}
		}
		material = BaseMaterialMF.pigiron;
		for(ItemStack ore: OreDictionary.getOres("ingotPigIron"))
		{
			KnowledgeListMF.steelR = 
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.ingots[4], 1), "smeltSteel", true, 1, 1, 5, new Object[]
			{
				"H",
				'H', ore
			});
		}
		KnowledgeListMF.fluxR =
		MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.flux, 4), "", false, -1, -1, 2, new Object[]
		{
			"H",
			'H',BlockListMF.limestone
		});
		
		
		
		//ARMOUR STUFF//
		
		//Armour Components
		for(int id = 0; id < ArmourListMF.mats.length; id ++)
		{
			material = BaseMaterialMF.getMaterial(ArmourListMF.mats[id]);
			
			if(canCraftMaterial(material))
			{
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				if(id > 0)
				{
					addPlateRecipe(material, ingot, id, 5);
				}
			}
			for(ItemStack hunk: OreDictionary.getOres("hunk"+material.name))
			{
				addMailRecipe(material, hunk, id, 5);
			}
			}
		}
		//MAIL ARMOUR
		for(int id = 0; id < ArmourListMF.mats.length; id ++)
		{
			material = BaseMaterialMF.getMaterial(ArmourListMF.mats[id]);
			if(canCraftMaterial(material))
			{
			//HELMET
			time = 10;
			KnowledgeListMF.mailHelmetR.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.chainmail, id, 0), "craftArmourMedium", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RMR",
				"MPM",
				"RMR",
				'R', ComponentListMF.rivet,
				'M', ComponentListMF.chainmeshes[id],
				'P', ArmourListMF.armour(ArmourListMF.leather, 1, 0),
			}));
			
			//CHEST
			time = 20;
			KnowledgeListMF.mailChestR.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.chainmail, id, 1), "craftArmourMedium", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RM MR",
				"RMPMR",
				"RM MR",
				'R', ComponentListMF.rivet,
				'M', ComponentListMF.chainmeshes[id],
				'P', ArmourListMF.armour(ArmourListMF.leather, 1, 1),
			}));
			
			//HELMET
			time = 15;
			KnowledgeListMF.mailLegsR.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.chainmail, id, 2), "craftArmourMedium", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RMPMR",
				"RM MR",
				'R', ComponentListMF.rivet,
				'M', ComponentListMF.chainmeshes[id],
				'P', ArmourListMF.armour(ArmourListMF.leather, 1, 2)
			}));
			
			//BOOTS
			time = 8;
			KnowledgeListMF.mailBootsR.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.chainmail, id, 3), "craftArmourMedium", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"R R",
				"MPM",
				'R', ComponentListMF.rivet,
				'M', ComponentListMF.chainmeshes[id],
				'P', ArmourListMF.armour(ArmourListMF.leather, 1, 3)
			}));
			
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.chainmail, id, 0), ArmourListMF.armour(ArmourListMF.leather, 1, 0), new ItemStack(ComponentListMF.rivet, 4), new ItemStack(ComponentListMF.chainmeshes[id], 4));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.chainmail, id, 1), ArmourListMF.armour(ArmourListMF.leather, 1, 1), new ItemStack(ComponentListMF.rivet, 6), new ItemStack(ComponentListMF.chainmeshes[id], 6));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.chainmail, id, 2), ArmourListMF.armour(ArmourListMF.leather, 1, 2), new ItemStack(ComponentListMF.rivet, 4), new ItemStack(ComponentListMF.chainmeshes[id], 4));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.chainmail, id, 3), ArmourListMF.armour(ArmourListMF.leather, 1, 3), new ItemStack(ComponentListMF.rivet, 2), new ItemStack(ComponentListMF.chainmeshes[id], 2));
			}
		}
				
				
		//PLATE ARMOUR
		for(int id = 1; id < ArmourListMF.mats.length; id ++)
		{
			material = BaseMaterialMF.getMaterial(ArmourListMF.mats[id]);
			if(canCraftMaterial(material))
			{
			//HELMET
			time = 20;
			KnowledgeListMF.plateHelmetR.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.fieldplate, id, 0), "craftArmourHeavy", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RMR",
				"PHP",
				"RMR",
				'M', ComponentListMF.chainmeshes[id],
				'R', ComponentListMF.rivet,
				'P', ComponentListMF.plates[id-1],
				'H', ArmourListMF.armourItem(ArmourListMF.leather, 5, 0)
			}));
			
			//CHEST
			time = 40;
			KnowledgeListMF.plateChestR.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.fieldplate, id, 1), "craftArmourHeavy", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RPMPR",
				"RMCMR",
				" RPR ",
				'M', ComponentListMF.chainmeshes[id],
				'R', ComponentListMF.rivet,
				'P', ComponentListMF.plates[id-1],
				'C', ArmourListMF.armourItem(ArmourListMF.leather, 5, 1)
			}));
			
			//LEGS
			time = 30;
			KnowledgeListMF.plateLegsR.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.fieldplate, id, 2), "craftArmourHeavy", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RPLPR",
				"RM MR",
				'M', ComponentListMF.chainmeshes[id],
				'R', ComponentListMF.rivet,
				'P', ComponentListMF.plates[id-1],
				'L', ArmourListMF.armourItem(ArmourListMF.leather, 5, 2)
			}));
			
			//BOOTS
			time = 15;
			KnowledgeListMF.plateBootsR.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.fieldplate, id, 3), "craftArmourHeavy", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" P ",
				"RBR",
				" M ",
				'M', ComponentListMF.chainmeshes[id],
				'R', ComponentListMF.rivet,
				'P', ComponentListMF.plates[id-1],
				'B', ArmourListMF.armourItem(ArmourListMF.leather, 5, 3)
			}));
			
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.fieldplate, id, 0), ArmourListMF.armour(ArmourListMF.leather, 5, 0), new ItemStack(ComponentListMF.rivet, 4), new ItemStack(ComponentListMF.chainmeshes[id], 2), new ItemStack(ComponentListMF.plates[id-1], 2));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.fieldplate, id, 1), ArmourListMF.armour(ArmourListMF.leather, 5, 1), new ItemStack(ComponentListMF.rivet, 6), new ItemStack(ComponentListMF.chainmeshes[id], 3), new ItemStack(ComponentListMF.plates[id-1], 3));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.fieldplate, id, 2), ArmourListMF.armour(ArmourListMF.leather, 5, 2), new ItemStack(ComponentListMF.rivet, 4), new ItemStack(ComponentListMF.chainmeshes[id], 2), new ItemStack(ComponentListMF.plates[id-1], 2));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.fieldplate, id, 3), ArmourListMF.armour(ArmourListMF.leather, 5, 3), new ItemStack(ComponentListMF.rivet, 2), new ItemStack(ComponentListMF.chainmeshes[id], 1), new ItemStack(ComponentListMF.plates[id-1], 1));
			}
			//ADV LEATHER
			
			//STUDDED
			material = BaseMaterialMF.iron;
			//HELMET
			time = 10;
			KnowledgeListMF.studHelmetR = 
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 3, 0), "craftArmourLight", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', ComponentListMF.rivet,
				'A', ArmourListMF.armourItem(ArmourListMF.leather, 2, 0),
			});
			//CHEST
			time = 20;
			KnowledgeListMF.studChestR = 
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 3, 1), "craftArmourLight", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', ComponentListMF.rivet,
				'A', ArmourListMF.armourItem(ArmourListMF.leather, 2, 1),
			});
			//LEGS
			time = 15;
			KnowledgeListMF.studLegsR = 
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 3, 2), "craftArmourLight", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', ComponentListMF.rivet,
				'A', ArmourListMF.armourItem(ArmourListMF.leather, 2, 2),
			});
			//BOOTS
			time = 6;
			KnowledgeListMF.studBootsR = 
			MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 3, 3), "craftArmourLight", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', ComponentListMF.rivet,
				'A', ArmourListMF.armourItem(ArmourListMF.leather, 2, 3),
			});
			
			//SCALED
			material = BaseMaterialMF.steel;
			for(ItemStack ingot: OreDictionary.getOres("hunkSteel"))
			{
				//HELMET
				time = 10;
				KnowledgeListMF.scaleHelmR = 
				MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 4, 0), "craftArmourLight", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"RIR",
					"IAI",
					"RIR",
					'R', ComponentListMF.rivet,
					'I', ingot,
					'A', ArmourListMF.armourItem(ArmourListMF.leather, 3, 0),
				});
				//CHEST
				time = 20;
				KnowledgeListMF.scaleChestR = 
				MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 4, 1), "craftArmourLight", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"RIR",
					"IAI",
					"RIR",
					'R', ComponentListMF.rivet,
					'I', ingot,
					'A', ArmourListMF.armourItem(ArmourListMF.leather, 3, 1),
				});
				//LEGS
				time = 15;
				KnowledgeListMF.scaleLegsR = 
				MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 4, 2), "craftArmourLight", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"RIR",
					"IAI",
					"RIR",
					'R', ComponentListMF.rivet,
					'I', ingot,
					'A', ArmourListMF.armourItem(ArmourListMF.leather, 3, 2),
				});
				//BOOTS
				time = 6;
				KnowledgeListMF.scaleBootsR = 
				MineFantasyAPI.addAnvilRecipe(artisanry, ArmourListMF.armour(ArmourListMF.leather, 4, 3), "craftArmourLight", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"RIR",
					"IAI",
					"RIR",
					'R', ComponentListMF.rivet,
					'I', ingot,
					'A', ArmourListMF.armourItem(ArmourListMF.leather, 3, 3),
				});
			}
			
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 3, 0), ArmourListMF.armour(ArmourListMF.leather, 2, 0), new ItemStack(ComponentListMF.rivet, 4));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 3, 1), ArmourListMF.armour(ArmourListMF.leather, 2, 1), new ItemStack(ComponentListMF.rivet, 4));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 3, 2), ArmourListMF.armour(ArmourListMF.leather, 2, 2), new ItemStack(ComponentListMF.rivet, 4));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 3, 3), ArmourListMF.armour(ArmourListMF.leather, 2, 3), new ItemStack(ComponentListMF.rivet, 4));
			
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 4, 0), ArmourListMF.armour(ArmourListMF.leather, 3, 0), new ItemStack(ComponentListMF.ingots[4], 4), new ItemStack(ComponentListMF.rivet, 4));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 4, 1), ArmourListMF.armour(ArmourListMF.leather, 3, 1), new ItemStack(ComponentListMF.ingots[4], 4), new ItemStack(ComponentListMF.rivet, 4));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 4, 2), ArmourListMF.armour(ArmourListMF.leather, 3, 2), new ItemStack(ComponentListMF.ingots[4], 4), new ItemStack(ComponentListMF.rivet, 4));
			Salvage.addSalvage(ArmourListMF.armourItem(ArmourListMF.leather, 4, 3), ArmourListMF.armour(ArmourListMF.leather, 3, 3), new ItemStack(ComponentListMF.ingots[4], 4), new ItemStack(ComponentListMF.rivet, 4));
		}
		
		time = 2;
		material = BaseMaterialMF.iron;
		if(ConfigCrafting.allowIronResmelt)
		{
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.iron_prep), "blastfurn", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
				"IFI",
				'I', Items.iron_ingot,
				'F', ComponentListMF.flux,
			});
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.iron_prep, 2), "blastfurn", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
				"IFI",
				'I', Items.iron_ingot,
				'F', ComponentListMF.flux_strong,
			});
		}
		KnowledgeListMF.coalPrepR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.coal_prep), "coke", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"RCR",
			"CFC",
			"RCR",
			'R', Items.redstone,
			'C', new ItemStack(Items.coal, 1, 1),
			'F', ComponentListMF.flux_strong,
		});
		GameRegistry.addSmelting(ComponentListMF.coal_prep, new ItemStack(ComponentListMF.coke), 1F);
		
		KnowledgeListMF.ironPrepR = 
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.iron_prep), "blastfurn", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"IFI",
			'I', Blocks.iron_ore,
			'F', ComponentListMF.flux,
		});
		KnowledgeListMF.ironPrepR2 = 
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.iron_prep, 2), "blastfurn", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"IFI",
			'I', Blocks.iron_ore,
			'F', ComponentListMF.flux_strong,
		});
		
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.iron_prep), "blastfurn", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"IFI",
			'I', ComponentListMF.oreIron,
			'F', ComponentListMF.flux,
		});
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.iron_prep, 2), "blastfurn", false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"IFI",
			'I', ComponentListMF.oreIron,
			'F', ComponentListMF.flux_strong,
		});
		time=15;
		KnowledgeListMF.blastChamR = 
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(BlockListMF.blast_chamber), "blastfurn", false, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"RP PR",
			"RP PR",
			"RP PR",
			"RP PR",
			'R', ComponentListMF.rivet,
			'P', ComponentListMF.plates[1],
		});
		
		time=30;
		KnowledgeListMF.blastHeatR = 
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(BlockListMF.blast_heater), "blastfurn", false, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"RP PR",
			"RP PR",
			"RP PR",
			"RPFPR",
			'R', ComponentListMF.rivet,
			'P', ComponentListMF.plates[1],
			'F', Blocks.furnace,
		});
		
		Salvage.addSalvage(BlockListMF.blast_heater, new ItemStack(ComponentListMF.rivet, 8), new ItemStack(ComponentListMF.plates[1], 8), Blocks.furnace);
		Salvage.addSalvage(BlockListMF.blast_chamber, new ItemStack(ComponentListMF.rivet, 8), new ItemStack(ComponentListMF.plates[1], 8));
		for(ItemStack silver : OreDictionary.getOres("ingotSilver"))
		{
			addOrnate(silver);
		}
		addNails("Copper", 1);
		addNails("Tin", 1);
		addNails("Bronze", 2);
		addNails("Iron", 4);
		addNails("Steel", 8);
		
		IAnvilRecipe[] anvilRecs = new IAnvilRecipe[BlockListMF.anvils.length];
		for(int id = 0; id < BlockListMF.anvils.length; id ++)
		{
			time = 20;
			material = BaseMaterialMF.getMaterial(BlockListMF.anvils[id]);
			
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				IAnvilRecipe recipe = 
				MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(BlockListMF.anvil[id]), "smelt"+material.name, false, "hammer", material.hammerTier-1, material.anvilTier-1, (int)(time*material.craftTimeModifier), new Object[]
				{
					" II",
					"III",
					" I ",
					'I', ingot,
				});
				anvilRecs[id] = recipe;
				
				if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
				{
					Salvage.addSalvage(BlockListMF.anvil[id], new ItemStack(ingot.getItem(), 6));
				}
			}
		}
		recipeMap.put("anvilCrafting", anvilRecs);
		
		for(ItemStack hunk: OreDictionary.getOres("hunkBronze"))
		{
			time = 4;
			material = BaseMaterialMF.bronze;
			KnowledgeListMF.framedStoneR =
			MineFantasyAPI.addAnvilRecipe(construction, new ItemStack(BlockListMF.reinforced_stone_framed), "decorated_stone", false, "hammer", material.hammerTier-1, material.anvilTier-1, (int)(time*material.craftTimeModifier), new Object[]
			{
				" N ",
				"NSN",
				" N ",
				'N', hunk,
				'S', BlockListMF.reinforced_stone,
			});
			if(hunk.getItem() instanceof ItemComponentMF)
			{
				Salvage.addSalvage(BlockListMF.reinforced_stone_framed, new ItemStack(hunk.getItem(), 4), BlockListMF.reinforced_stone);
			}
		}
		for(ItemStack hunk: OreDictionary.getOres("hunkIron"))
		{
			time = 4;
			material = BaseMaterialMF.iron;
			KnowledgeListMF.iframedStoneR =
			MineFantasyAPI.addAnvilRecipe(construction, new ItemStack(BlockListMF.reinforced_stone_framediron), "decorated_stone", false, "hammer", material.hammerTier-1, material.anvilTier-1, (int)(time*material.craftTimeModifier), new Object[]
			{
				" N ",
				"NSN",
				" N ",
				'N', hunk,
				'S', BlockListMF.reinforced_stone,
			});
			if(hunk.getItem() instanceof ItemComponentMF)
			{
				Salvage.addSalvage(BlockListMF.reinforced_stone_framediron, new ItemStack(hunk.getItem(), 4), BlockListMF.reinforced_stone);
			}
		}
		
		for(int id = 0; id < BlockListMF.specialMetalBlocks.length; id ++)
		{
			time = 2;
			material = BaseMaterialMF.getMaterial(BlockListMF.specialMetalBlocks[id]);
			
			for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
			{
				KnowledgeListMF.barsR.add(
				MineFantasyAPI.addAnvilRecipe(construction, new ItemStack(BlockListMF.bars[id]), "smelt"+material.name, false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I I",
					"I I",
					'I', ingot,
				}));
				if(ingot.getItem() instanceof ItemComponentMF)
				{
					Salvage.addSalvage(BlockListMF.bars[id], new ItemStack(ingot.getItem(), 4));
				}
			}
		}
		KnowledgeListMF.talismanRecipe.add(
		MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.talisman_lesser), "", true, "hammer", -1, -1, 20, new Object[]
		{
			"LGL",
			"GIG",
			" G ",
			'L', new ItemStack(Items.dye, 1, 4),
			'I', Items.iron_ingot,
			'G', Items.gold_ingot,
		}));
		for(ItemStack silver: OreDictionary.getOres("ingotSilver"))
		{
			KnowledgeListMF.talismanRecipe.add(
			MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.talisman_lesser), "", true, "hammer", -1, -1, 20, new Object[]
			{
				"LSL",
				"SIS",
				" S ",
				'L', new ItemStack(Items.dye, 1, 4),
				'I', Items.iron_ingot,
				'S', silver,
			}));
		}
		KnowledgeListMF.greatTalismanRecipe =
		MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.talisman_greater), "", true, "hammer", 1, 1, 50, new Object[]
		{
			"GSG",
			"DTD",
			"GDG",
			'G', Items.gold_ingot,
			'D', Items.diamond,
			'T', ComponentListMF.talisman_lesser,
			'S', Items.nether_star,
		});
		
		material = BaseMaterialMF.adamantium;
		KnowledgeListMF.haftGold =
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.gold_rod), "smeltIgnotumite", true, "hammer", material.hammerTier, material.anvilTier, (int)(10*material.craftTimeModifier), new Object[]
		{
			" N ",
			"NPN",
			" N ",
			'N', ComponentListMF.hunks[9],
			'P', ComponentListMF.plankRefined,
		});
		material = BaseMaterialMF.mithril;
		KnowledgeListMF.haftSilver =
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.silver_rod), "smeltMithium", true, "hammer", material.hammerTier, material.anvilTier, (int)(10*material.craftTimeModifier), new Object[]
		{
			" N ",
			"NPN",
			" N ",
			'N', ComponentListMF.hunks[8],
			'P', ComponentListMF.plankRefined,
		});
		material = BaseMaterialMF.mithril;
		KnowledgeListMF.haftObsidian =
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.obsidian_rod), "smeltEnder", true, "hammer", material.hammerTier, material.anvilTier, (int)(10*material.craftTimeModifier), new Object[]
		{
			"DND",
			"NPN",
			"DND",
			'D', ComponentListMF.obsidian_rock,
			'N', ComponentListMF.hunks[7],
			'P', ComponentListMF.plankRefined,
		});
		
		Salvage.addSalvage(ComponentListMF.silver_rod, new ItemStack(ComponentListMF.hunks[8], 4), ComponentListMF.plankRefined);
		Salvage.addSalvage(ComponentListMF.gold_rod, new ItemStack(ComponentListMF.hunks[9], 4), ComponentListMF.plankRefined);
		Salvage.addSalvage(ComponentListMF.obsidian_rod, new ItemStack(ComponentListMF.hunks[7], 4), ComponentListMF.plankRefined, ComponentListMF.obsidian_rock);
		
		addEngineering();
		addConstruction();
	}

	private static void addMailRecipe(BaseMaterialMF material, ItemStack hunk, int id, int time) 
	{
		if(material == BaseMaterialMF.ignotumite)
		{
			KnowledgeListMF.mailIgno=
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.chainmeshes[id]), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"SIS",
				"I I",
				"SIS",
				'S', ComponentListMF.hunks[9],
				'I', hunk
			});
			Salvage.addSalvage(ComponentListMF.chainmeshes[id], new ItemStack(hunk.getItem(), 4), new ItemStack(ComponentListMF.hunks[9], 4));
		}
		else if(material == BaseMaterialMF.mithium)
		{
			KnowledgeListMF.mailMithi=
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.chainmeshes[id]), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"SIS",
				"I I",
				"SIS",
				'S', ComponentListMF.hunks[8],
				'I', hunk
			});
			Salvage.addSalvage(ComponentListMF.chainmeshes[id], new ItemStack(hunk.getItem(), 4), new ItemStack(ComponentListMF.hunks[8], 4));
		}
		else if(material == BaseMaterialMF.enderforge)
		{
			KnowledgeListMF.mailEnder=
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.chainmeshes[id]), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"SIS",
				"IOI",
				"SIS",
				'O', ComponentListMF.obsidian_rock,
				'S', ComponentListMF.hunks[7],
				'I', hunk
			});
			Salvage.addSalvage(ComponentListMF.chainmeshes[id], new ItemStack(hunk.getItem(), 4), new ItemStack(ComponentListMF.hunks[7], 4), ComponentListMF.obsidian_rock);
		}
		else
		{
			KnowledgeListMF.mailRecipes.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.chainmeshes[id]), "smelt"+material.name, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"I I",
				" I ",
				'I', hunk
			}));
			if(hunk.getItem() instanceof ItemComponentMF)
			{
				Salvage.addSalvage(ComponentListMF.chainmeshes[id], new ItemStack(hunk.getItem(), 4));
			}
		}
	}

	private static void addPlateRecipe(BaseMaterialMF material, ItemStack ingot, int id, int time) 
	{
		if(material == BaseMaterialMF.ignotumite)
		{
			KnowledgeListMF.plateIgno=
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.plates[id-1]), "smelt"+material.name, true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"SS",
				"II",
				"SS",
				'S', ComponentListMF.hunks[9],
				'I', ingot
			});	
			Salvage.addSalvage(ComponentListMF.plates[id-1], new ItemStack(ingot.getItem(), 2), new ItemStack(ComponentListMF.hunks[9], 4));
		}
		else if(material == BaseMaterialMF.mithium)
		{
			KnowledgeListMF.plateMithi=
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.plates[id-1]), "smelt"+material.name, true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"SS",
				"II",
				"SS",
				'S', ComponentListMF.hunks[8],
				'I', ingot
			});	
			Salvage.addSalvage(ComponentListMF.plates[id-1], new ItemStack(ingot.getItem(), 2), new ItemStack(ComponentListMF.hunks[8], 4));
		}
		else if(material == BaseMaterialMF.enderforge)
		{
			KnowledgeListMF.plateEnder=
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.plates[id-1]), "smelt"+material.name, true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" SS ",
				"OIIO",
				" SS ",
				'O', ComponentListMF.obsidian_rock,
				'S', ComponentListMF.hunks[7],
				'I', ingot
			});	
			Salvage.addSalvage(ComponentListMF.plates[id-1], new ItemStack(ingot.getItem(), 2), new ItemStack(ComponentListMF.hunks[7], 4));
		}
		else
		{
			KnowledgeListMF.plateRecipes.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.plates[id-1]), "smelt"+material.name, true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"II",
				'I', ingot
			}));	
			if(ingot.getItem() instanceof ItemComponentMF || ingot.getItem() == Items.iron_ingot)
			{
				Salvage.addSalvage(ComponentListMF.plates[id-1], new ItemStack(ingot.getItem(), 2));
			}
		}
	}

	private static boolean canCraftMaterial(BaseMaterialMF material) 
	{
		return material != BaseMaterialMF.dragonforge;
	}

	private static void addOrnate(ItemStack silver)
	{
		//DAGGERS
		int time = 12;
		int id = 3;
		Item tool = ToolListMF.daggers[id];
		BaseMaterialMF material = BaseMaterialMF.ornate;
		ItemStack lapis = new ItemStack(Items.dye, 1, 4);
		Item gold = Items.gold_ingot;
		
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LJ ",
			"SGI",
			"L  ",
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, silver.getItem(), gold, lapis, getHaft(material), new ItemStack(getStrips(material), 2));
		}
		
		//SWORDS
		time = 25;
		tool = ToolListMF.swords[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LG  ",
			"SJII",
			"LG  ",
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 2), new ItemStack(gold, 2), lapis, getHaft(material), new ItemStack(getStrips(material), 2));
		}
		
		//AXES
		time = 20;
		tool = ToolListMF.waraxes[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LII",
			"SSJ",
			"L G",
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 2), gold, lapis, new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
		}
		
		//MACES
		time = 18;
		tool = ToolListMF.maces[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"L II",
			"SSGJ",
			"L   ",
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 2), gold, lapis, new ItemStack(getHaft(material), 2), new ItemStack(getStrips(material), 2));
		}
		
		//SPEARS
		time = 20;
		tool = ToolListMF.spears[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" L I  ",
			"SSSJIG",
			" L I  ",
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 3), gold, lapis, new ItemStack(getHaft(material), 3), new ItemStack(getStrips(material), 2));
		}
		//BOWS
		time = 30;
		tool = ToolListMF.bows[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"GSSSG",
			" ILI ",
			"  J  ",
			
			'L', getStrips(material),
			'S', Items.string,
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 2), new ItemStack(gold, 2), lapis, new ItemStack(Items.string, 3), getStrips(material));
		}
		
		//HALBEARDS
		time = 30;
		tool = ToolListMF.halbeards[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftAdvOrnateWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" LRII ",
			"SSSJIG",
			" LRI  ",
			'R', ComponentListMF.rivet,
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 4), gold, lapis, new ItemStack(getHaft(material), 3), new ItemStack(getStrips(material), 2), new ItemStack(ComponentListMF.rivet, 2));
		}
		
		//GREATSWORDS
		time = 35;
		tool = ToolListMF.greatswords[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftAdvOrnateWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LGR  ",
			"SJIII",
			"LGR  ",
			'R', ComponentListMF.rivet,
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 3), new ItemStack(gold, 2), lapis, getHaft(material), new ItemStack(getStrips(material), 2), new ItemStack(ComponentListMF.rivet, 2));
		}
		
		//KATANAS
		time = 40;
		tool = ToolListMF.katanas[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftAdvOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LR   I",
			"SJIII ",
			"LG    ",
			'R', ComponentListMF.rivet,
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 4), gold, lapis, getHaft(material), new ItemStack(getStrips(material), 2), ComponentListMF.rivet);
		}
		
		//BATTLEAXES
		time = 30;
		tool = ToolListMF.battleaxes[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftAdvOrnateWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LRII ",
			"SSSGJ",
			"LRII ",
			'R', ComponentListMF.rivet,
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 4), gold, lapis, new ItemStack(getHaft(material), 3), new ItemStack(getStrips(material), 2), new ItemStack(ComponentListMF.rivet, 2));
		}
		
		//WARHAMMERS
		time = 28;
		tool = ToolListMF.warhammers[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftAdvOrnateWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"L RII",
			"SSSIJ",
			"L R G",
			'R', ComponentListMF.rivet,
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 3), gold, lapis, new ItemStack(getHaft(material), 3), new ItemStack(getStrips(material), 2), new ItemStack(ComponentListMF.rivet, 2));
		}
		
		//LANCES
		time = 50;
		tool = ToolListMF.lances[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(tool), "craftAdvOrnateWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"GR    ",
			"SJIIII",
			"GR    ",
			'R', ComponentListMF.rivet,
			'L', getStrips(material),
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		if(silver.getItem() instanceof ItemComponentMF)
		{
			Salvage.addSalvage(tool, new ItemStack(silver.getItem(), 4), new ItemStack(gold, 2), lapis, getHaft(material), new ItemStack(getStrips(material), 2), new ItemStack(ComponentListMF.rivet, 2));
		}
	}
	
	public static Item getHaft(BaseMaterialMF mat)
	{
		if(mat == BaseMaterialMF.ignotumite)
		{
			return ComponentListMF.gold_rod;
		}
		if(mat == BaseMaterialMF.mithium)
		{
			return ComponentListMF.silver_rod;
		}
		if(mat == BaseMaterialMF.enderforge)
		{
			return ComponentListMF.obsidian_rod;
		}
		return ComponentListMF.plank;
	}
	
	private static Item getStrips(BaseMaterialMF material)
	{
		return ComponentListMF.leather_strip;
	}
	private static Item getLeather(BaseMaterialMF material)
	{
		return Items.leather;
	}
	
	private static void addEngineering() 
	{	
		BaseMaterialMF material = BaseMaterialMF.steel;
		int time = 10;
		for(ItemStack steel: OreDictionary.getOres("hunkSteel"))
		{
			time = 25;
			KnowledgeListMF.spannerRecipe = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ToolListMF.spanner), "etools", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"  S ",
				"  SS",
				"LS  ",
				"IL  ",
				'I', Items.iron_ingot,
				'S', steel,
				'L', getStrips(material),
			});
			time = 15;
			KnowledgeListMF.eatoolsR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ToolListMF.engin_anvil_tools), "etools", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"SSLL",
				"LLSS",
				'S', steel,
				'L', getStrips(material),
			});
			time = 5;
			KnowledgeListMF.iframeR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.iron_frame), "ecomponents", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RRR",
				"ISI",
				"STS",
				"ISI",
				'T', ToolListMF.engin_anvil_tools,
				'R', ComponentListMF.rivet,
				'I', ComponentListMF.hunks[3],
				'S', steel,
			});
			time = 8;
			KnowledgeListMF.istrutR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.iron_strut), "ecomponents", true, "hvyhammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RTR",
				"SIS",
				"SIS",
				'T', ToolListMF.engin_anvil_tools,
				'R', ComponentListMF.rivet,
				'I', Items.iron_ingot,
				'S', steel,
			});
			time = 8;
			KnowledgeListMF.stubeR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.steel_tube), "ecomponents", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"TR R",
				"SSSS",
				'T', ToolListMF.engin_anvil_tools,
				'R', ComponentListMF.rivet,
				'S', steel,
			});
			time = 4;
			KnowledgeListMF.boltR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.bolt, 2), "etools", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" T ",
				"SIS",
				" S ",
				" S ",
				'T', ToolListMF.engin_anvil_tools,
				'I', Items.iron_ingot,
				'S', steel,
			});
		}
		for(ItemStack steel: OreDictionary.getOres("ingotSteel"))
		{
			time = 35;
			KnowledgeListMF.climbPickbR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ToolListMF.climbing_pick_basic), "climber", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"L SR",
				"IISR",
				"L T ",
				'R', ComponentListMF.rivet,
				'T', ToolListMF.engin_anvil_tools,
				'I', Items.iron_ingot,
				'S', steel,
				'L', getStrips(material),
			});
		}
		for(ItemStack nugget: OreDictionary.getOres("hunkBronze"))
		{
			time = 5;
			KnowledgeListMF.bgearR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.bronze_gears), "ecomponents", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" T ",
				" B ",
				"BIB",
				" B ",
				'T', ToolListMF.engin_anvil_tools,
				'I', Items.iron_ingot,
				'B', nugget,
			});
		}
		for(ItemStack nugget: OreDictionary.getOres("hunkTungsten"))
		{
			time = 8;
			material = BaseMaterialMF.tungsten;
			KnowledgeListMF.tgearR =
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.tungsten_gears), "tungsten", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" T ",
				" W ",
				"WGW",
				" W ",
				'T', ToolListMF.engin_anvil_tools,
				'W', nugget,
				'G', ComponentListMF.bronze_gears,
			});
		}
		for(ItemStack copper: OreDictionary.getOres("ingotCopper"))
		{
			for(ItemStack steel: OreDictionary.getOres("ingotSteel"))
			{
			time = 15;
			material = BaseMaterialMF.compositeAlloy;
			KnowledgeListMF.compPlateR =
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.ingotCompositeAlloy), "cogArmour", true, "hvyhammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" T ",
				" S ",
				"RWR",
				" C ",
				
				'R', ComponentListMF.rivet,
				'T', ToolListMF.engin_anvil_tools,
				'C', copper,
				'W', ComponentListMF.hunks[17],
				'S', steel,
			});
			}
		}
		material = BaseMaterialMF.iron;
		
		time = 5;
		KnowledgeListMF.bombCaseIronR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.bomb_casing_iron, 2), "bombIron", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" T ",
			" I ",
			"IRI",
			" I ",
			'T', ToolListMF.engin_anvil_tools,
			'I', ComponentListMF.hunks[3],
			'R', ComponentListMF.rivet,
		});
		KnowledgeListMF.mineCaseIronR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.mine_casing_iron, 2), "bombIron", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"  T  ",
			"  P  ",
			" IRI ",
			"IR RI",
			'T', ToolListMF.engin_anvil_tools,
			'P', Blocks.heavy_weighted_pressure_plate,
			'I', ComponentListMF.hunks[3],
			'R', ComponentListMF.rivet,
		});
		
		time = 5;
		KnowledgeListMF.bombarrowR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.bomb_casing_arrow), "bombarrow", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"   IR",
			"FPITI",
			"   IR",
			'T', ToolListMF.engin_anvil_tools,
			'I', ComponentListMF.hunks[3],
			'R', Items.redstone,
			'P', ComponentListMF.plankRefined,
			'F', ComponentListMF.fletching,
		});
		KnowledgeListMF.bombBoltR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.bomb_casing_bolt), "bombarrow", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"  IR",
			"FITI",
			"  IR",
			'T', ToolListMF.engin_anvil_tools,
			'I', ComponentListMF.hunks[3],
			'R', Items.redstone,
			'F', ComponentListMF.fletching,
		});
		
		for(ItemStack hunk: OreDictionary.getOres("hunkBlackSteel"))
		{
			material = BaseMaterialMF.blacksteel;
			
			time = 5;
			KnowledgeListMF.bombCaseObsidianR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.bomb_casing_obsidian, 2), "bombObsidian", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" T ",
				"RIR",
				"IOI",
				"RIR",
				'T', ToolListMF.engin_anvil_tools,
				'O', Blocks.obsidian,
				'I', hunk,
				'R', ComponentListMF.rivet,
			});
			KnowledgeListMF.mineCaseObsidianR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.mine_casing_obsidian, 2), "mineObsidian", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"  T  ",
				"  P  ",
				" IRI ",
				"IRORI",
				'T', ToolListMF.engin_anvil_tools,
				'O', Blocks.obsidian,
				'P', Blocks.heavy_weighted_pressure_plate,
				'I', hunk,
				'R', ComponentListMF.rivet,
			});
		}
		time = 15;
		material = BaseMaterialMF.steel;
		KnowledgeListMF.crossBayonetR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.cross_bayonet), "crossBayonet", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"R R I",
			"PIII ",
			'P', ComponentListMF.plankRefined,
			'I', ComponentListMF.hunks[3],
			'R', ComponentListMF.rivet,
		});
				
		
		time = 80;
		material = BaseMaterialMF.blacksteel;
		KnowledgeListMF.blkspannerR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ToolListMF.spanner_blk), "etools", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"  SO",
			" TSS",
			"LS  ",
			"IL  ",
			'O', Blocks.obsidian,
			'I', Items.gold_ingot,
			'S', ComponentListMF.hunks[7],
			'L', getStrips(material),
			'T', ToolListMF.engin_anvil_tools,
		});
		Salvage.addSalvage(ToolListMF.engin_anvil_tools, new ItemStack(ComponentListMF.hunks[5], 4), new ItemStack(ComponentListMF.leather_strip, 4));
		Salvage.addSalvage(ComponentListMF.iron_frame, new ItemStack(ComponentListMF.hunks[5], 4), new ItemStack(ComponentListMF.hunks[3], 4), new ItemStack(ComponentListMF.rivet, 3));
		Salvage.addSalvage(ComponentListMF.iron_strut, new ItemStack(ComponentListMF.hunks[5], 4), new ItemStack(Items.iron_ingot, 2), new ItemStack(ComponentListMF.rivet, 2));
		Salvage.addSalvage(ComponentListMF.steel_tube, new ItemStack(ComponentListMF.hunks[5], 4), new ItemStack(ComponentListMF.rivet, 2));
		Salvage.addSalvage(ToolListMF.climbing_pick_basic, new ItemStack(Items.iron_ingot, 2), new ItemStack(ComponentListMF.ingots[4], 2), new ItemStack(ComponentListMF.rivet, 2), new ItemStack(ComponentListMF.leather_strip, 2));
		Salvage.addSalvage(ComponentListMF.bronze_gears, new ItemStack(ComponentListMF.hunks[2], 4), Items.iron_ingot);
		Salvage.addSalvage(ComponentListMF.tungsten_gears, ComponentListMF.bronze_gears, new ItemStack(ComponentListMF.hunks[17], 4));
		Salvage.addSalvage(ComponentListMF.ingotCompositeAlloy, ComponentListMF.ingots[0], ComponentListMF.ingots[4], ComponentListMF.hunks[17], new ItemStack(ComponentListMF.rivet, 2));
		
		Salvage.addSalvage(ComponentListMF.bomb_casing_iron, new ItemStack(ComponentListMF.hunks[3], 2));
		Salvage.addSalvage(ComponentListMF.mine_casing_iron, new ItemStack(ComponentListMF.hunks[3], 2), ComponentListMF.rivet, Items.iron_ingot);
		Salvage.addSalvage(ComponentListMF.bomb_casing_arrow, new ItemStack(ComponentListMF.hunks[3], 4), new ItemStack(Items.redstone, 2), ComponentListMF.plankRefined, ComponentListMF.fletching);
		Salvage.addSalvage(ComponentListMF.bomb_casing_bolt, new ItemStack(ComponentListMF.hunks[3], 4), new ItemStack(Items.redstone, 2), ComponentListMF.fletching);
		Salvage.addSalvage(ComponentListMF.bomb_casing_obsidian, new ItemStack(ComponentListMF.hunks[7], 2), new ItemStack(ComponentListMF.rivet, 2));
		Salvage.addSalvage(ComponentListMF.mine_casing_obsidian, new ItemStack(ComponentListMF.hunks[7], 2), Items.iron_ingot, ComponentListMF.rivet);
		
		Salvage.addSalvage(ComponentListMF.cross_bayonet, new ItemStack(ComponentListMF.hunks[3], 4), ComponentListMF.plankRefined, new ItemStack(ComponentListMF.rivet, 2));
		Salvage.addSalvage(ToolListMF.spanner, new ItemStack(ComponentListMF.hunks[5], 4), Items.iron_ingot, new ItemStack(ComponentListMF.leather_strip, 2));
		Salvage.addSalvage(ToolListMF.spanner_blk, new ItemStack(ComponentListMF.hunks[7], 4), Items.gold_ingot, Blocks.obsidian, new ItemStack(ComponentListMF.leather_strip, 2));
	
		material = BaseMaterialMF.cogworks;
		time = 25;
		KnowledgeListMF.cogFrameHelmetR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ArmourListMF.cogwork_frame_helmet), "cogArmour", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"RSFSR",
			"RA AR",
			'R', ComponentListMF.rivet,
			'S', ComponentListMF.iron_strut,
			'A', ComponentListMF.cogwork_shaft,
			'F', ComponentListMF.iron_frame,
		});
		time = 40;
		KnowledgeListMF.cogFrameChestR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ArmourListMF.cogwork_frame_chest), "cogArmour", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"RS SR",
			"RAAAR",
			"RSASR",
			" RFR ",
			
			'R', ComponentListMF.rivet,
			'S', ComponentListMF.iron_strut,
			'A', ComponentListMF.cogwork_shaft,
			'F', ComponentListMF.iron_frame,
		});
		time = 30;
		KnowledgeListMF.cogFrameLegsR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ArmourListMF.cogwork_frame_legs), "cogArmour", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"RAFAR",
			"RS SR",
			"RS SR",
			
			'R', ComponentListMF.rivet,
			'S', ComponentListMF.iron_strut,
			'A', ComponentListMF.cogwork_shaft,
			'F', ComponentListMF.iron_frame,
		});
		time = 20;
		KnowledgeListMF.cogFrameBootsR = 
		MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ArmourListMF.cogwork_frame_boots), "cogArmour", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"RA AR",
			"RS SR",
			
			'R', ComponentListMF.rivet,
			'S', ComponentListMF.iron_strut,
			'A', ComponentListMF.cogwork_shaft,
		});
		Salvage.addSalvage(ArmourListMF.cogwork_frame_helmet, new ItemStack(ComponentListMF.rivet, 4), new ItemStack(ComponentListMF.iron_strut, 2), new ItemStack(ComponentListMF.cogwork_shaft, 2), ComponentListMF.iron_frame);
		Salvage.addSalvage(ArmourListMF.cogwork_frame_chest, new ItemStack(ComponentListMF.rivet, 8), new ItemStack(ComponentListMF.iron_strut, 4), new ItemStack(ComponentListMF.cogwork_shaft, 4), ComponentListMF.iron_frame);
		Salvage.addSalvage(ArmourListMF.cogwork_frame_legs, new ItemStack(ComponentListMF.rivet, 6), new ItemStack(ComponentListMF.iron_strut, 4), new ItemStack(ComponentListMF.cogwork_shaft, 2), ComponentListMF.iron_frame);
		Salvage.addSalvage(ArmourListMF.cogwork_frame_boots, new ItemStack(ComponentListMF.rivet, 4), new ItemStack(ComponentListMF.iron_strut, 2), new ItemStack(ComponentListMF.cogwork_shaft, 2));
		
		ArrayList<CustomMaterial> metal = CustomMaterial.getList("metal");
		Iterator iteratorMetal = metal.iterator();
		while(iteratorMetal.hasNext())
    	{
    		CustomMaterial customMat = (CustomMaterial) iteratorMetal.next();
    		
    		for(ItemStack ingot: OreDictionary.getOres("ingot"+customMat.name))
    		{
    			//TOTAL: 32Ingot, 20Rivet
    			IAnvilRecipe helm = 
    			MineFantasyAPI.addAnvilRecipe(engineering, ArmourListMF.cogwork_armour_helmet.construct(customMat.name), "cogArmour", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
    				"RRR",
					"III",
					"IFI",
					"III",
					
					'I', ingot,
					'R', ComponentListMF.rivet,
					'F', new ItemStack(ArmourListMF.cogwork_frame_helmet, 1, 0),
				});
    			IAnvilRecipe chest =
    			MineFantasyAPI.addAnvilRecipe(engineering, ArmourListMF.cogwork_armour_chest.construct(customMat.name), "cogArmour", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
    				"RRR",
					"III",
					"IFI",
					"III",
					
					'I', ingot,
					'R', ComponentListMF.rivet,
					'F', new ItemStack(ArmourListMF.cogwork_frame_chest, 1, 0),
				});
    			IAnvilRecipe legs = 
    			MineFantasyAPI.addAnvilRecipe(engineering, ArmourListMF.cogwork_armour_legs.construct(customMat.name), "cogArmour", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
    				"RRR",
					"III",
					"IFI",
					"III",
					
					'I', ingot,
					'R', ComponentListMF.rivet,
					'F', new ItemStack(ArmourListMF.cogwork_frame_legs, 1, 0),
				});
    			IAnvilRecipe boots = 
    			MineFantasyAPI.addAnvilRecipe(engineering, ArmourListMF.cogwork_armour_boots.construct(customMat.name), "cogArmour", false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
    				"RRR",
					"III",
					"IFI",
					"III",
					
					'I', ingot,
					'R', ComponentListMF.rivet,
					'F', new ItemStack(ArmourListMF.cogwork_frame_boots, 1, 0),
				});
    			
    			if(ingot.getItem() == ComponentListMF.ingots[4])
    			{
    				KnowledgeListMF.cogArmourHelmetR = helm;
    				KnowledgeListMF.cogArmourChestR = chest;
    				KnowledgeListMF.cogArmourLegsR = legs;
    				KnowledgeListMF.cogArmourBootsR = boots;
    			}
    		}
    	}
	}
	private static void addConstruction() 
	{
		BaseMaterialMF material = BaseMaterialMF.tin;
		int time = 10;
		for(ItemStack tin: OreDictionary.getOres("ingotTin"))
		{
			KnowledgeListMF.brushRecipe = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ToolListMF.paint_brush), "paint_brush", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"W",
				"I",
				"P",
				
				'W', Blocks.wool,
				'I', tin,
				'P', ComponentListMF.plankRefined,
			});
		}
		
		Salvage.addSalvage(ToolListMF.paint_brush, Blocks.wool, ComponentListMF.ingots[1], ComponentListMF.plankRefined);
	}
	
	private static void addNails(String name, int count) 
	{
		int time=5;
		BaseMaterialMF material = BaseMaterialMF.bronze;
		for(ItemStack hunk : OreDictionary.getOres("hunk" + name))
		{
			KnowledgeListMF.nailR.add(
			MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.nail, count*2), "", true, "hammer", -1, -1, (int)(time*material.craftTimeModifier), new Object[]{
				"HH",
				"H ",
				"H ",
				'H', hunk,
			}));
			KnowledgeListMF.rivetR.add(
			MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.rivet, count), "", true, "hammer", -1, -1, (int)(time*material.craftTimeModifier), new Object[]{
				"H H",
				" H ",
				" H ",
				'H', hunk,
			}));
		}
	}
	
	
	public static final HashMap<String, IAnvilRecipe[]>recipeMap = new HashMap<String, IAnvilRecipe[]>();
}
