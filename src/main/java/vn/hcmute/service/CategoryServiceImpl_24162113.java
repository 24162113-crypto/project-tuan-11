package vn.hcmute.service;

import java.util.List;
import vn.hcmute.dao.CategoryDAOImpl_24162113;
import vn.hcmute.dao.ICategoryDAO_24162113;
import vn.hcmute.entity.Category_24162113;

public class CategoryServiceImpl_24162113 implements ICategoryService_24162113 {
    private final ICategoryDAO_24162113 categoryDAO = new CategoryDAOImpl_24162113();

    @Override
    public List<Category_24162113> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category_24162113 findById(int categoryId) {
        return categoryDAO.findById(categoryId);
    }
}
