package Nanali.service;

import Nanali.domain.cody.Category;
import Nanali.domain.cody.cloth.Garment;
import Nanali.domain.cody.cloth.Outfit;
import Nanali.domain.cody.cloth.detail.Detail;
import Nanali.repository.DetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DetailService {

    private final DetailRepository detailRepository;
    private final S3FileService s3FileService;

    @Transactional()
    public Long save(MultipartFile DetailImg, Category category, Outfit outfit) {

        String imgName = "";
        String imgUrl = "";
        Map<String, String> result = s3FileService.upload(DetailImg, "garment");
        String s3FileName = result.get("s3FileName");
        String s3Url = result.get("s3Url");
        imgName = s3FileName;
        imgUrl = s3Url;

        Detail detail = Detail.builder()
                .imgName(imgName).imgUrl(imgUrl).category(category).outfit(outfit).build();

        Detail saved = detailRepository.save(detail);
        return saved.getId();
    }

    public Detail findOne(Long id) {
        Detail detail = detailRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("failed to find detail. id: %s", id)));
        return detail;
    }

    public Detail findOuter(Long id) {
        Detail outer = detailRepository.findByOutfitIdAndCategory(id, Category.OUTER);
        return outer;
    }

    public Detail findTop(Long id) {
        Detail outer = detailRepository.findByOutfitIdAndCategory(id, Category.TOP);
        return outer;
    }

    public Detail findPants(Long id) {
        Detail outer = detailRepository.findByOutfitIdAndCategory(id, Category.PANTS);
        return outer;
    }

    public Detail findShoes(Long id) {
        Detail outer = detailRepository.findByOutfitIdAndCategory(id, Category.SHOES);
        return outer;
    }

}
