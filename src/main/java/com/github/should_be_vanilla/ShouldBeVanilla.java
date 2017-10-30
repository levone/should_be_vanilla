package com.github.should_be_vanilla;


import org.slf4j.Logger;
import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.recipe.RecipeRegistry;
import org.spongepowered.api.item.recipe.crafting.Ingredient;
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe;
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "should_be_vanilla", name = "Should be Vanilla", version = "1.0")
public class ShouldBeVanilla {
	
	@Inject
	private Logger logger;
	
	public Logger getLogger() {
		return logger;
	}
	
	
	@Listener
	public void onInitialize(GamePreInitializationEvent event) {
		this.getLogger().info("Creating and registering recipees.");
		
		// Create the required ingredients.
		Ingredient Ileather = Ingredient.builder().with(ItemTypes.LEATHER).build();
		Ingredient IironIngot = Ingredient.builder().with(ItemTypes.IRON_INGOT).build();
		Ingredient Istring = Ingredient.builder().with(ItemTypes.STRING).build();
		
		// Create the saddle ItemStack item.
		ItemStack Ssaddle = ItemStack.builder().itemType(ItemTypes.SADDLE).build();
		
		// Aisle style recipe string.
		String saddleRecipeString[] = {"lll", 
									   "s s",
									   " i "};
		
		// Create the recipe
		ShapedCraftingRecipe saddleRecipeShaped = ShapedCraftingRecipe.builder().aisle(saddleRecipeString)
													.where('l', Ileather).where('s', Istring).where('i', IironIngot)
													.result(Ssaddle).build("minecraft:saddle", this);
		
		// Register the new recipe. GameRegistry->RecipeRegistry->register().
		GameRegistry gr = Sponge.getRegistry();
		RecipeRegistry rr = gr.getCraftingRecipeRegistry();
		rr.register(saddleRecipeShaped);
	}
}

