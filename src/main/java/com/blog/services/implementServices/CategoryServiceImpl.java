package com.blog.services.implementServices;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.blog.entities.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.repository.CategoryRepo;
import com.blog.services.CategoryServices;

@Service
public class CategoryServiceImpl implements CategoryServices {
    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto catdto) {
        Category cat = this.modelMapper.map(catdto, Category.class);
        Category addcat = this.catRepo.save(cat);
        return this.modelMapper.map(addcat, CategoryDto.class);
    }

    @Override
    public CategoryDto upadateCategory(CategoryDto catdto, Integer catid) {
        Category cat = this.catRepo.findById(catid)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", catid));
        cat.setTitle(catdto.getTitle());
        cat.setDescription(catdto.getDescription());
        Category updatecat = this.catRepo.save(cat);
        return this.modelMapper.map(updatecat, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer catid) {
        Category cat = this.catRepo.findById(catid)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", catid));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory(Integer pageNo,Integer pageSize,String sortBy,String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();
        Pageable page=PageRequest.of(pageNo, pageSize,sort);
        Page <Category> pagePosts=this.catRepo.findAll( page);
        List<Category> categories = pagePosts.getContent();
        List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
        return catDtos;
    }

    @Override
    public void deleteCategory(Integer catid) {
        Category cat = this.catRepo.findById(catid)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", catid));
        this.catRepo.delete(cat);

    }

}
