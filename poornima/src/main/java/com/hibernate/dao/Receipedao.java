package com.hibernate.dao;

import java.util.List;

import com.entitiy.Receipe;

public interface Receipedao {
    
    void AddRecipe();
    
    void  findReceipeByName();
    
    List<Receipe> getAllRecipes();
    
    void updateRecipe();
    
    void deleteRecipe();
    

}
