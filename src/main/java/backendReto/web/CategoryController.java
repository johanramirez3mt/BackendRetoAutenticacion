package backendReto.web;


import backendReto.model.Category;
import backendReto.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/Category")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> getCategory() {
        return categoryService.obtenerCategory();
    }

    @GetMapping("/(id)")
    public Optional<Category> getCategory(@PathVariable("id") int id) {
        return categoryService.obtenerCategoryId(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Category postCategory(@RequestBody Category category){
        return categoryService.crearCategory(category);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Category putCategory(@RequestBody Category category){
        return categoryService.actuallizarCategory(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteCategory(@PathVariable("id") int id) {
        return categoryService.eliminarCategory(id);
    }
}
