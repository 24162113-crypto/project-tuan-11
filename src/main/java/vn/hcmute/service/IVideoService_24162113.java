package vn.hcmute.service;

import java.util.List;
import java.util.Map;
import vn.hcmute.dto.CategoryBlock_24162113;
import vn.hcmute.dto.PageResult_24162113;
import vn.hcmute.dto.VideoInfo_24162113;
import vn.hcmute.entity.Video_24162113;

public interface IVideoService_24162113 {
    PageResult_24162113<VideoInfo_24162113> findPage(int page, int size);

    long count();

    List<CategoryBlock_24162113> buildBlocks(Map<Integer, Integer> requestedPages, int size);

    VideoInfo_24162113 viewDetail(String videoId);

    Video_24162113 findById(String videoId);

    void save(Video_24162113 video);

    void delete(String videoId);
}
