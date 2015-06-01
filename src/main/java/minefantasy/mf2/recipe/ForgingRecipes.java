package minefantasy.mf2.recipe;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.crafting.anvil.IAnvilRecipe;
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
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

public class ForgingRecipes
{
	public static void init()
	{
		for(int id = 0; id < ToolListMF.mats.length; id ++)
		{
			int time;
			BaseMaterialMF material = ToolListMF.mats[id];
			
			//PICKS
			time = 15;
			Item tool;
			tool = ToolListMF.picks[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				String smeltMat = "smelt"+material.name;
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftTools", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L I",
					"SSI",
					"L I",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//AXES
			time = 12;
			tool = ToolListMF.axes[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftTools", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"LII",
					"SSI",
					"L  ",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//SPADES
			time = 8;
			tool = ToolListMF.spades[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftTools", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L  ",
					"SSI",
					"L  ",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//HOES
			time = 15;
			tool = ToolListMF.hoes[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftTools", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L I",
					"SSI",
					"L  ",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//SHEARS
			time = 12;
			tool = ToolListMF.shears[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftTools", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"SLI",
					" S ",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
		
			//HAMMERS
			time = 10;
			tool = ToolListMF.hammers[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftCrafters", true, material.hammerTier-1, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I",
					"L",
					"S",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//TONGS
			time = 10;
			tool = ToolListMF.tongs[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftCrafters", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I ",
					" I",
					'I', ingot,
				});
			}
		
			//SAWS
			time = 20;
			tool = ToolListMF.saws[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftCrafters", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"SIIII",
					"SIII ",
					'S', getHaft(material),
					'I', ingot,
				});
			}
		
			//KNIVES
			time = 12;
			tool = ToolListMF.knives[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftCrafters", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I",
					" I",
					"LS",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
		
			//NEEDLES
			time = 5;
			tool = ToolListMF.needles[id];
			for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftCrafters", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I",
					"I",
					"I",
					"I",
					'I', ingot,
				});
			}
			
			if(id > 0)
			{
				//HVYPICKS
				time = 30;
				tool = ToolListMF.hvypicks[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvTools", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L I ",
						"SSII",
						"L II",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				//HVYSHOVELS
				time = 20;
				tool = ToolListMF.hvyshovels[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvTools", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L II",
						"SSII",
						"L II",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				//HANDPICKS
				time = 12;
				tool = ToolListMF.handpicks[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvTools", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"LI",
						"SI",
						"L ",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				//TROWS
				time = 15;
				tool = ToolListMF.trows[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvTools", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L ",
						"SI",
						"L ",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				//HVYHAMMERS
				time = 25;
				tool = ToolListMF.hvyHammers[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "hvyHammer", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"II",
						"LI",
						"S ", 
						'L', Items.leather,
						'S', ComponentListMF.plank,
						'I', ingot,
					});
				}
				//SCYTHES
				time = 30;
				tool = ToolListMF.scythes[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvTools", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"  I ",
						"   I",
						" L I",
						"SSSI",
						
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
			}
		}
		//WEAPONS
		for(int id = 0; id < ToolListMF.weaponMats.length; id ++)
		{
			int time;
			Item tool;
			BaseMaterialMF material = ToolListMF.weaponMats[id];
			
			
			//ARROWS
			time = 10;
			tool = ComponentListMF.arrowheads[id];
			for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool, 4), "arrows", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I ",
					"II",
					"I ",
					
					'I', ingot,
				});
			}
			
			if(material != BaseMaterialMF.ornate)
		{
			
			//DAGGERS
			time = 12;
			tool = ToolListMF.daggers[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L  ",
					"SII",
					"L  ",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//SWORDS
			time = 25;
			tool = ToolListMF.swords[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"LI  ",
					"SIII",
					"LI  ",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//AXES
			time = 20;
			tool = ToolListMF.waraxes[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"LII",
					"SSI",
					"L I",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//MACES
			time = 18;
			tool = ToolListMF.maces[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"L II",
					"SSII",
					"L   ",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			
			//SPEARS
			time = 20;
			tool = ToolListMF.spears[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" L I ",
					"SSSSI",
					" L I ",
					'L', Items.leather,
					'S', getHaft(material),
					'I', ingot,
				});
			}
			//BOWS
			time = 30;
			tool = ToolListMF.bows[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"ISSSI",
					" ILI ",
					
					'L', Items.leather,
					'S', Items.string,
					'I', ingot,
				});
			}
			
			if(id > 0)
			{
				//HALBEARDS
				time = 30;
				tool = ToolListMF.halbeards[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						" L II",
						"SSSSI",
						" L I ",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				
				//GREATSWORDS
				time = 35;
				tool = ToolListMF.greatswords[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"LI   ",
						"SIIII",
						"LI   ",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				
				//KATANAS
				time = 40;
				tool = ToolListMF.katanas[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L    I",
						"SIIII ",
						"LI    ",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				
				//BATTLEAXES
				time = 30;
				tool = ToolListMF.battleaxes[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L II",
						"SSSI",
						"L II",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				
				//WARHAMMERS
				time = 28;
				tool = ToolListMF.warhammers[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"L  II",
						"SSSII",
						"L   I",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				//LANCES
				time = 50;
				tool = ToolListMF.lances[id-1];
				for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I     ",
						"SIIIII",
						"I     ",
						'L', Items.leather,
						'S', getHaft(material),
						'I', ingot,
					});
				}
				
				//ARROWS
				time = 10;
				tool = ComponentListMF.bodkinheads[id-1];
				for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool, 4), "arrowsBodkin", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I  ",
						" II",
						"I  ",
						
						'I', ingot,
					});
				}
				time = 15;
				tool = ComponentListMF.broadheads[id-1];
				for(ItemStack ingot: OreDictionary.getOres("hunk"+material.name))
				{
					MineFantasyAPI.addAnvilRecipe(new ItemStack(tool, 4), "arrowsBroad", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"I ",
						" I",
						" I",
						"I ",
						
						'I', ingot,
					});
				}
			}
		}
		}
		
		
		//MISC
		
		BaseMaterialMF material;
		int time;
		time = 1;
		material = BaseMaterialMF.encrusted;
		
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.diamond_shards), false, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"D",
			'D', Items.diamond,
		});
		
		time = 3;
		for(ItemStack ingot: OreDictionary.getOres("ingotSteel"))
		{
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.ingots[5]), "encrusted", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"D",
				"I",
				'D', ComponentListMF.diamond_shards,
				'I', ingot,
			});
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
				IAnvilRecipe recipe =
				MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.hunks[id], 4), "", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"I", 'I', ingot
				});
				MineFantasyAPI.addAnvilRecipe(ingot, true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					"II",
					"II",
					'I', ComponentListMF.hunks[id]
				});
			}
		}
		for(ItemStack ore: OreDictionary.getOres("ingotPigIron"))
		{
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.ingots[4], 1), "smeltSteel", true, 1, 1, 5, new Object[]
			{
				"H",
				'H', ore
			});
		}
		KnowledgeListMF.coalDustR = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.coalDust, 4), "", false, -1, -1, 2, new Object[]
		{
			"H",
			'H', Items.coal
		});
		KnowledgeListMF.shrapnelR = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.shrapnel, 4), "shrapnel", false, 2, 2, 10, new Object[]
		{
			"H",
			'H', Items.flint
		});
		KnowledgeListMF.kaoDustR =
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.kaolinite_dust, 8), "", false, -1, -1, 4, new Object[]
		{
			"H",
			'H', ComponentListMF.kaolinite
		});
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.flux, 4), false, -1, -1, 2, new Object[]
		{
			"H",
			'H',BlockListMF.limestone
		});
		
		
		
		//ARMOUR STUFF//
		
		//Armour Components
		for(int id = 0; id < ArmourListMF.mats.length; id ++)
		{
			material = ArmourListMF.mats[id];
			for(ItemStack ingot: OreDictionary.getOres("ingot"+material.name))
			{
				time = 5;
				if(id > 0)
				{
					KnowledgeListMF.plateRecipes.add(
					MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.plates[id-1]), "", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
					{
						"II",
						'I', ingot
					}));
				}
			}
			for(ItemStack hunk: OreDictionary.getOres("hunk"+material.name))
			{
				time = 5;
				MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.chainmeshes[id]), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"I I",
					" I ",
					'I', hunk
				});
			}
			
		}
		//MAIL ARMOUR
		for(int id = 0; id < ArmourListMF.mats.length; id ++)
		{
			material = ArmourListMF.mats[id];
			
			//HELMET
			time = 10;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.chainmail, id, 0), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"MMM",
				"MPM",
				'M', ComponentListMF.chainmeshes[id],
				'P', Items.leather,
			});
			
			//CHEST
			time = 20;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.chainmail, id, 1), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"MMPMM",
				" MPM ",
				" MPM ",
				'M', ComponentListMF.chainmeshes[id],
				'P', Items.leather,
			});
			
			//HELMET
			time = 15;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.chainmail, id, 2), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"PPP",
				"MMM",
				"M M",
				"M M",
				'M', ComponentListMF.chainmeshes[id],
				'P', Items.leather,
			});
			
			//BOOTS
			time = 8;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.chainmail, id, 3), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" M M ",
				"M P M",
				'M', ComponentListMF.chainmeshes[id],
				'P', Items.leather,
			});
		}
				
				
		//PLATE ARMOUR
		for(int id = 1; id < ArmourListMF.mats.length; id ++)
		{
			material = ArmourListMF.mats[id];
			
			//HELMET
			time = 20;
			KnowledgeListMF.plateHelmetR.add(
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.fieldplate, id, 0), "craftArmourAdv", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" M ",
				"MPM",
				"P P",
				" M ",
				'M', ComponentListMF.plates[id-1],
				'P', ComponentListMF.padding
			}));
			
			//CHEST
			time = 40;
			KnowledgeListMF.plateChestR.add(
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.fieldplate, id, 1), "craftArmourAdv", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"M  M",
				"MPPM",
				"MPPM",
				" PP ",
				'M', ComponentListMF.plates[id-1],
				'P', ComponentListMF.padding
			}));
			
			//LEGS
			time = 30;
			KnowledgeListMF.plateLegsR.add(
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.fieldplate, id, 2), "craftArmourAdv", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"  P  ",
				"MP PM",
				"MP PM",
				'M', ComponentListMF.plates[id-1],
				'P', ComponentListMF.padding
			}));
			
			//BOOTS
			time = 15;
			KnowledgeListMF.plateBootsR.add(
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.fieldplate, id, 3), "craftArmourAdv", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"MP PM",
				'M', ComponentListMF.plates[id-1],
				'P', ComponentListMF.padding
			}));
			
			//ADV LEATHER
			
			//STUDDED
			material = BaseMaterialMF.iron;
			//HELMET
			time = 10;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 2, 0), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', Items.iron_ingot,
				'A', ArmourListMF.armour(ArmourListMF.leather, 1, 0),
			});
			//CHEST
			time = 20;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 2, 1), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', Items.iron_ingot,
				'A', ArmourListMF.armour(ArmourListMF.leather, 1, 1),
			});
			//LEGS
			time = 15;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 2, 2), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', Items.iron_ingot,
				'A', ArmourListMF.armour(ArmourListMF.leather, 1, 2),
			});
			//BOOTS
			time = 6;
			MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 2, 3), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				" I ",
				"IAI",
				" I ",
				'I', Items.iron_ingot,
				'A', ArmourListMF.armour(ArmourListMF.leather, 1, 3),
			});
			
			//SCALED
			material = BaseMaterialMF.steel;
			for(ItemStack ingot: OreDictionary.getOres("ingotSteel"))
			{
				//HELMET
				time = 10;
				MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 3, 0), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"IAI",
					" I ",
					'I', ingot,
					'A', ArmourListMF.armour(ArmourListMF.leather, 1, 0),
				});
				//CHEST
				time = 20;
				MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 3, 1), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"IAI",
					" I ",
					'I', ingot,
					'A', ArmourListMF.armour(ArmourListMF.leather, 1, 1),
				});
				//LEGS
				time = 15;
				MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 3, 2), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"IAI",
					" I ",
					'I', ingot,
					'A', ArmourListMF.armour(ArmourListMF.leather, 1, 2),
				});
				//BOOTS
				time = 6;
				MineFantasyAPI.addAnvilRecipe(ArmourListMF.armour(ArmourListMF.leather, 3, 3), "craftArmour", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
				{
					" I ",
					"IAI",
					" I ",
					'I', ingot,
					'A', ArmourListMF.armour(ArmourListMF.leather, 1, 3),
				});
			}
		}
		
		//BOMBS
		material = BaseMaterialMF.iron;
		
		time = 3;
		KnowledgeListMF.bombCaseIronR = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.bomb_casing_iron, 2), "bombIron", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" I ",
			"IRI",
			" I ",
			'I', ComponentListMF.hunks[3],
			'R', Items.redstone,
		});
		KnowledgeListMF.mineCaseIronR = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.mine_casing_iron, 2), "bombIron", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"  P  ",
			" IRI ",
			"I R I",
			'P', Blocks.heavy_weighted_pressure_plate,
			'I', ComponentListMF.hunks[3],
			'R', Items.redstone,
		});
		
		time = 30;
		KnowledgeListMF.bombBenchCraft = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(BlockListMF.bombBench), "bombs", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" I ",
			"ICI",
			"PIP",
			'I', Items.iron_ingot,
			'C', BlockListMF.carpenter,
			'P', ComponentListMF.plank
		});
		
		for(ItemStack hunk: OreDictionary.getOres("hunkBlackSteel"))
		{
			material = BaseMaterialMF.blacksteel;
			
			time = 10;
			KnowledgeListMF.bombCaseObsidianR = 
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.bomb_casing_obsidian, 2), "bombObsidian", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"RIR",
				"IOI",
				"RIR",
				'O', Blocks.obsidian,
				'I', hunk,
				'R', Items.redstone,
			});
			KnowledgeListMF.mineCaseObsidianR = 
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.mine_casing_obsidian, 2), "mineObsidian", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
			{
				"  P  ",
				" IRI ",
				"IRORI",
				'O', Blocks.obsidian,
				'P', Blocks.heavy_weighted_pressure_plate,
				'I', hunk,
				'R', Items.redstone,
			});
		}
		time = 2;
		material = BaseMaterialMF.iron;
		if(ConfigCrafting.allowIronResmelt)
		{
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.iron_prep), "blastfurn", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
				"IFI",
				'I', Items.iron_ingot,
				'F', ComponentListMF.flux,
			});
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.iron_prep, 2), "blastfurn", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
				"IFI",
				'I', Items.iron_ingot,
				'F', ComponentListMF.flux_strong,
			});
		}
		
		KnowledgeListMF.ironPrepR = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.iron_prep), "blastfurn", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"IFI",
			'I', Blocks.iron_ore,
			'F', ComponentListMF.flux,
		});
		KnowledgeListMF.ironPrepR2 = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.iron_prep, 2), "blastfurn", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"IFI",
			'I', Blocks.iron_ore,
			'F', ComponentListMF.flux_strong,
		});
		time=15;
		KnowledgeListMF.blastChamR = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(BlockListMF.blast_chamber), "blastfurn", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"II II",
			"I   I",
			"I   I",
			"II II",
			'I', Items.iron_ingot,
		});
		time=30;
		KnowledgeListMF.blastHeatR = 
		MineFantasyAPI.addAnvilRecipe(new ItemStack(BlockListMF.blast_heater), "blastfurn", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
			"II II",
			"I   I",
			"IIFII",
			'I', Items.iron_ingot,
			'F', Blocks.furnace,
		});
		for(ItemStack silver : OreDictionary.getOres("ingotSilver"))
		{
			addOrnate(silver);
		}
		time=5;
		material = BaseMaterialMF.silver;
		for(ItemStack hunk : OreDictionary.getOres("hunkSilver"))
		{
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.silver_rod), "", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
				" H ",
				"HPH",
				" H ",
				'H', hunk,
				'P', ComponentListMF.plank,
			});
		}
		material = BaseMaterialMF.gold;
		for(ItemStack hunk : OreDictionary.getOres("hunkGold"))
		{
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.gold_rod), "", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
				" H ",
				"HPH",
				" H ",
				'H', hunk,
				'P', ComponentListMF.plank,
			});
		}
		material = BaseMaterialMF.blacksteel;
		for(ItemStack hunk : OreDictionary.getOres("hunkBlackSteel"))
		{
			MineFantasyAPI.addAnvilRecipe(new ItemStack(ComponentListMF.obsidian_rod), "", true, "hammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]{
				" O ",
				" H ",
				"HPH",
				" H ",
				'O', Blocks.obsidian,
				'H', hunk,
				'P', ComponentListMF.plank,
			});
		}
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
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LJ ",
			"SGI",
			"L  ",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//SWORDS
		time = 25;
		tool = ToolListMF.swords[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LG  ",
			"SJII",
			"LG  ",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//AXES
		time = 20;
		tool = ToolListMF.waraxes[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LII",
			"SSJ",
			"L G",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//MACES
		time = 18;
		tool = ToolListMF.maces[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"L II",
			"SSGJ",
			"L   ",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//SPEARS
		time = 20;
		tool = ToolListMF.spears[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" L I  ",
			"SSSJIG",
			" L I  ",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		//BOWS
		time = 30;
		tool = ToolListMF.bows[id];
		KnowledgeListMF.ornateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"ISSSI",
			" GLG ",
			"  J  ",
			
			'L', Items.leather,
			'S', Items.string,
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//HALBEARDS
		time = 30;
		tool = ToolListMF.halbeards[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvOrnateWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			" L II ",
			"SSSJIG",
			" L I  ",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//GREATSWORDS
		time = 35;
		tool = ToolListMF.greatswords[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvOrnateWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"LG   ",
			"SJIII",
			"LG   ",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//KATANAS
		time = 40;
		tool = ToolListMF.katanas[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvOrnateWeapons", true, material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"L    I",
			"SJIII ",
			"LG    ",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//BATTLEAXES
		time = 30;
		tool = ToolListMF.battleaxes[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvOrnateWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"L II ",
			"SSSGJ",
			"L II ",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		
		//WARHAMMERS
		time = 28;
		tool = ToolListMF.warhammers[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"L  II",
			"SSSIJ",
			"L   G",
			'L', Items.leather,
			'S', getHaft(material),
			'G', gold,
			'I', silver,
			'J', lapis
		}));
		//LANCES
		time = 50;
		tool = ToolListMF.lances[id-1];
		KnowledgeListMF.advOrnateWepsR.add(
		MineFantasyAPI.addAnvilRecipe(new ItemStack(tool), "craftAdvWeapons", true, "hvyHammer", material.hammerTier, material.anvilTier, (int)(time*material.craftTimeModifier), new Object[]
		{
			"G     ",
			"SJIIII",
			"G     ",
			'L', Items.leather,
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
}
