package Nanali.service;

import Nanali.domain.Member.Member;
import Nanali.domain.Member.Style;
import Nanali.domain.cody.LikeClothes.LikeOutfit;
import Nanali.domain.cody.LikeClothes.LikeStatus;
import Nanali.domain.cody.cloth.Outfit;
import Nanali.domain.cody.cloth.Sex;
import Nanali.dtos.outfit.OutfitDto;
import Nanali.dtos.weather.OutfitWeatherRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SpringBootTest
class LikeOutfitServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    LikeOutfitService likeOutfitService;

    @Autowired
    OutfitService outfitService;

    @Test
    public void likeOutfitTest() {
        Member member = new Member("loginId", "password", "nickname", "email", true, 20, Style.CASUAL);
        memberService.save(member);


        byte[] fileContent = "Test file content".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("testfile.txt", "testfile.txt", "text/plain", fileContent);

        OutfitWeatherRequest outfitWeatherRequest1 = new OutfitWeatherRequest(0D, 10D, 0D, 1D, 0D, 30D);
        OutfitWeatherRequest outfitWeatherRequest2 = new OutfitWeatherRequest(10D, 20D, 0D, 1D, 0D, 50D);
        OutfitWeatherRequest outfitWeatherRequest3 = new OutfitWeatherRequest(10D, 25D, 0D, 2D, 20D, 100D);
        OutfitWeatherRequest outfitWeatherRequest4 = new OutfitWeatherRequest(10D, 20D, 0D, 3D, 70D, 100D);
        OutfitWeatherRequest outfitWeatherRequest5 = new OutfitWeatherRequest(10D, 14D, 0D, 3D, 80D, 100D);
        OutfitWeatherRequest outfitWeatherRequest6 = new OutfitWeatherRequest(15D, 20D, 0D, 2D, 0D, 50D);
        OutfitWeatherRequest outfitWeatherRequest7 = new OutfitWeatherRequest(15D, 20D, 0D, 2D, 0D, 30D);

        Outfit outfit1 = outfitService.save(multipartFile, Style.CASUAL, Sex.BOTH, outfitWeatherRequest1);
        Outfit outfit2 = outfitService.save(multipartFile, Style.CASUAL, Sex.BOTH, outfitWeatherRequest2);
        Outfit outfit3 = outfitService.save(multipartFile, Style.CASUAL, Sex.BOTH, outfitWeatherRequest3);
        Outfit outfit4 = outfitService.save(multipartFile, Style.CASUAL, Sex.BOTH, outfitWeatherRequest4);
        Outfit outfit5 = outfitService.save(multipartFile, Style.CASUAL, Sex.BOTH, outfitWeatherRequest5);
        Outfit outfit6 = outfitService.save(multipartFile, Style.CASUAL, Sex.BOTH, outfitWeatherRequest6);
        Outfit outfit7 = outfitService.save(multipartFile, Style.CASUAL, Sex.BOTH, outfitWeatherRequest7);

        LikeOutfit likeOutfit1 = new LikeOutfit(member, outfit1, LikeStatus.LIKE);
        LikeOutfit likeOutfit2 = new LikeOutfit(member, outfit2, LikeStatus.LIKE);
        LikeOutfit likeOutfit3 = new LikeOutfit(member, outfit3, LikeStatus.LIKE);
        LikeOutfit likeOutfit4 = new LikeOutfit(member, outfit4, LikeStatus.LIKE);
        LikeOutfit likeOutfit5 = new LikeOutfit(member, outfit5, LikeStatus.LIKE);
        LikeOutfit likeOutfit6 = new LikeOutfit(member, outfit6, LikeStatus.LIKE);
        LikeOutfit likeOutfit7 = new LikeOutfit(member, outfit7, LikeStatus.LIKE);

        likeOutfitService.save(likeOutfit1);
        likeOutfitService.save(likeOutfit2);
        likeOutfitService.save(likeOutfit3);
        likeOutfitService.save(likeOutfit4);
        likeOutfitService.save(likeOutfit5);
        likeOutfitService.save(likeOutfit6);
        likeOutfitService.save(likeOutfit7);

        List<OutfitDto> allByMember1 = likeOutfitService.findAllByMember(member);

        for (OutfitDto outfit : allByMember1) {
            System.out.println("likeOutfit = " + outfit);
        }

        System.out.println("===============================");

        likeOutfitService.changeLikeOutfit(likeOutfit1.getId());
        likeOutfitService.changeLikeOutfit(likeOutfit2.getId());
        likeOutfitService.changeLikeOutfit(likeOutfit3.getId());
        likeOutfitService.changeLikeOutfit(likeOutfit4.getId());
        likeOutfitService.changeLikeOutfit(likeOutfit5.getId());

        List<OutfitDto> allByMember2 = likeOutfitService.findAllByMember(member);

        // 6, 7만 떠야함

        for (OutfitDto outfit : allByMember2) {
            System.out.println("likeOutfit = " + outfit);
        }

        System.out.println("===============================");

        likeOutfitService.changeLikeOutfit(likeOutfit1.getId());
        likeOutfitService.changeLikeOutfit(likeOutfit2.getId());

        List<OutfitDto> allByMember3 = likeOutfitService.findAllByMember(member);

        // 1, 2, 6, 7만 떠야함

        for (OutfitDto outfit : allByMember3) {
            System.out.println("likeOutfit = " + outfit);
        }
    }
}