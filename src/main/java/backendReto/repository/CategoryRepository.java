package backendReto.repository;

import backendReto.model.Category;
import backendReto.repository.crud.CategoryCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    CategoryCrudRepository categoryCrudRepository;

    public List<Category> obtenerCategory(){
        return (List<Category>)categoryCrudRepository.findAll();
    }

    public Optional<Category> obtenerCategoryId(int id) {
        return categoryCrudRepository.findById(id);
    }

    public Category crearCategory(Category category) {
        return categoryCrudRepository.save(category);
    }

    public void eliminarCategory(Category category) {
        categoryCrudRepository.delete(category);
    }
}
