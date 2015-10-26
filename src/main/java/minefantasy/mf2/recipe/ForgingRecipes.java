package minefantasy.mf2.recipe;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
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
			BaseMaterialMF material = ToolListMF.mats[id];
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
					}
				}
			}
		}
		//WEAPONS
		for(int id = 0; id < ToolListMF.weaponMats.length; id ++)
		{
			int time;
			Item tool;
			BaseMaterialMF material = ToolListMF.weaponMats[id];
			
			if(canCraftMaterial(material))
			{
				//ARROWS
				time = 10;
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
							'L', getStrips(material),
							'S', getHaft(material),
							'I', ingot,
						}));
					}
					
					//ARROWS
					time = 10;
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
					}
					time = 15;
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
			if(KnowledgeListMF.encrustedR == null)
			{
				KnowledgeListMF.encrustedR = recipe;
			}
		}
		
		material = BaseMaterialMF.dragonforge;
		time = 5;
		
		//HUNKS
		time = 1;
		for(int id = 0; id < ComponentListMF.hunkMats.length; id ++)
		{
			material = ComponentListMF.hunkMats[id];
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
			material = ArmourListMF.mats[id];
			
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
			material = ArmourListMF.mats[id];
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
			}
		}
				
				
		//PLATE ARMOUR
		for(int id = 1; id < ArmourListMF.mats.length; id ++)
		{
			material = ArmourListMF.mats[id];
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
		for(ItemStack silver : OreDictionary.getOres("ingotSilver"))
		{
			addOrnate(silver);
		}
		time=5;
		material = BaseMaterialMF.bronze;
		for(ItemStack hunk : OreDictionary.getOres("hunkIron"))
		{
			KnowledgeListMF.nailR = 
			MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.nail, 8), "", true, "hammer", -1, -1, (int)(time*material.craftTimeModifier), new Object[]{
				"HH",
				"H ",
				"H ",
				'H', hunk,
			});
			KnowledgeListMF.rivetR = 
			MineFantasyAPI.addAnvilRecipe(null, new ItemStack(ComponentListMF.rivet, 4), "", true, "hammer", -1, -1, (int)(time*material.craftTimeModifier), new Object[]{
				"H H",
				" H ",
				" H ",
				'H', hunk,
			});
		}
		
		IAnvilRecipe[] anvilRecs = new IAnvilRecipe[BlockListMF.anvils.length];
		for(int id = 0; id < BlockListMF.anvils.length; id ++)
		{
			time = 20;
			material = BlockListMF.anvils[id];
			
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
			}
		}
		recipeMap.put("anvilCrafting", anvilRecs);
		
		for(ItemStack hunk: OreDictionary.getOres("hunkBronze"))
		{
			time = 4;
			KnowledgeListMF.framedStoneR =
			MineFantasyAPI.addAnvilRecipe(construction, new ItemStack(BlockListMF.reinforced_stone_framed), "smelt"+material.name, false, "hammer", material.hammerTier-1, material.anvilTier-1, (int)(time*material.craftTimeModifier), new Object[]
			{
				" N ",
				"NSN",
				" N ",
				'N', hunk,
				'S', BlockListMF.reinforced_stone,
			});
		}
		
		for(int id = 0; id < BlockListMF.specialMetalBlocks.length; id ++)
		{
			time = 2;
			material = BlockListMF.specialMetalBlocks[id];
			
			for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
			{
				KnowledgeListMF.barsR.add(
				MineFantasyAPI.addAnvilRecipe(construction, new ItemStack(BlockListMF.bars[id]), "smelt"+material.name, false, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I I",
					"I I",
					'I', ingot,
				}));
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
		addEngineering();
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
		}
		else
		{
			KnowledgeListMF.plateRecipes.add(
			MineFantasyAPI.addAnvilRecipe(artisanry, new ItemStack(ComponentListMF.plates[id-1]), "smelt"+material.name, true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"II",
				'I', ingot
			}));	
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
	}
	
	public static Object getHaft(BaseMaterialMF mat)
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
	
	private static Object getStrips(BaseMaterialMF material)
	{
		return ComponentListMF.leather_strip;
	}
	private static Object getLeather(BaseMaterialMF material)
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
			time = 35;
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
			time = 40;
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
			time = 40;
			KnowledgeListMF.stubeR = 
			MineFantasyAPI.addAnvilRecipe(engineering, new ItemStack(ComponentListMF.steel_tube), "ecomponents", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"TR R",
				"SSSS",
				'T', ToolListMF.engin_anvil_tools,
				'R', ComponentListMF.rivet,
				'S', steel,
			});
			time = 10;
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
			time = 35;
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
		
		//BOMBS
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
		
		time = 25;
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
		
		for(ItemStack hunk: OreDictionary.getOres("hunkBlackSteel"))
		{
			material = BaseMaterialMF.blacksteel;
			
			time = 15;
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
	}
	
	
	public static final HashMap<String, IAnvilRecipe[]>recipeMap = new HashMap<String, IAnvilRecipe[]>();
}
