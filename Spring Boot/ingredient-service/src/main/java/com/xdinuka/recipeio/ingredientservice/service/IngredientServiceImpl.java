package com.xdinuka.recipeio.ingredientservice.service;

import com.xdinuka.recipeio.ingredientservice.model.Ingredient;
import com.xdinuka.recipeio.ingredientservice.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{

    final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> fetchAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient saveOrUpdate(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> fetchById(Integer id) {
        return ingredientRepository.findById(id);
    }

}
