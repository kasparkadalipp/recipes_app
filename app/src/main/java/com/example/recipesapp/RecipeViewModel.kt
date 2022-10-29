package com.example.recipesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.recipesapp.room.LocalRecipeDb

class RecipeViewModel(val app: Application) : AndroidViewModel(app) {
    var localDb = LocalRecipeDb.getInstance(app)

    var recipeArray: Array<Recipe> = arrayOf()

    fun refresh() {
        // Reload dataset from DB, put it in in-memory list
        recipeArray = localDb.getRecipeDao().loadRecipes().map {
            Recipe(
                it.id,
                it.title,
                it.thumbnail_url,
                ImageUtils.defaultThumbnail
            )
        }.toTypedArray()
    }
}