package vn.hcmute.service;

import java.util.List;
import vn.hcmute.entity.Category_24162113;

public interface ICategoryService_24162113 {
    List<Category_24162113> findAll();

    Category_24162113 findById(int categoryId);
}
