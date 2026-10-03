package vn.hcmute.dao;

import java.util.List;
import vn.hcmute.dto.VideoInfo_24162113;
import vn.hcmute.entity.Video_24162113;

public interface IVideoDAO_24162113 {
    List<VideoInfo_24162113> findPage(int page, int size);

    long count();

    List<VideoInfo_24162113> findByCategory(int categoryId, int page, int size);

    long countByCategory(int categoryId);

    VideoInfo_24162113 findInfoById(String videoId);

    Video_24162113 findById(String videoId);

    void save(Video_24162113 video);

    void delete(String videoId);

    void increaseViews(String videoId);
}
