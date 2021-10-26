package backendReto.service;

import backendReto.model.Category;
import backendReto.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> obtenerCategory() {
        return categoryRepository.obtenerCategory();
    }

    public Optional<Category> obtenerCategoryId(int id) {
        return categoryRepository.obtenerCategoryId(id);
    }

    public Category crearCategory(Category category) {
        if (category.getId() == null) {
            return categoryRepository.crearCategory(category);
        }else{
            Optional<Category> cat = categoryRepository.obtenerCategoryId(category.getId());
            if (cat.isEmpty()) {
                return categoryRepository.crearCategory(category);
            } else {
                return category;
            }
        }
    }

    public Category actuallizarCategory(Category category) {
        if (category.getId() != null) {
            Optional<Category> g = categoryRepository.obtenerCategoryId(category.getId());
            if (!g.isEmpty()) {
                if (category.getDescription() != null) {
                    g.get().setDescription(category.getDescription());
                }
                if (category.getName() != null) {
                    g.get().setName(category.getName());
                }
                return categoryRepository.crearCategory(g.get());
            }
        }
        return category;
    }

    public boolean eliminarCategory(int Id) {
        Boolean d = obtenerCategoryId(Id).map(category -> {
            categoryRepository.eliminarCategory(category);
            return true;
        }).orElse(false);
        return d;
    }
}
