package vn.hcmute.dao;

import java.util.List;
import vn.hcmute.entity.Category_24162113;

public interface ICategoryDAO_24162113 {
    List<Category_24162113> findAll();

    List<Category_24162113> findAllActive();

    Category_24162113 findById(int categoryId);
}
