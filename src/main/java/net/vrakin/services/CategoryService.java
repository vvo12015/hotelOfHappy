package net.vrakin.services;

import net.vrakin.model.Category;
import net.vrakin.repository.CategoryRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ParentService<Category, Long> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Long getId(Category entity) {
        return entity.getCategoryId();
    }

    @Override
    public CrudRepository<Category, Long> getRepository() {
        return this.categoryRepository;
    }
    
}
