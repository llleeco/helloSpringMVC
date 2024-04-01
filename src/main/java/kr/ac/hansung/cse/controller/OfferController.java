package kr.ac.hansung.cse.controller;

import kr.ac.hansung.cse.model.Offer;
import kr.ac.hansung.cse.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OfferController {

    //컨트롤러는 서비스에 의존하고 있음 -> 서비스 의존성 주입
    //                              데이터 베이스 접근
    // Controller -> Service -> Dao
    @Autowired //의존성 주입
    private OfferService offerService;

    @GetMapping("/offers")
    public String showOffers(Model model) {
        List<Offer> offers = offerService.getAllOffers(); //서비스 레이어에 getAllOffers 메소드 호출
        model.addAttribute("id_offers", offers); //가져온결과를 모델에 저장 , id: id_offers

        return "offers";//view 이름
    }

    @GetMapping("/createoffer")
    public String createOffer(Model model) {

        model.addAttribute("offer", new Offer());

        return "createoffer";
    }

    @PostMapping("/docreate")
    public String doCreate(Model model, @Valid Offer offer, BindingResult result) {

        // System.out.println(offer);
        if(result.hasErrors()) {
            System.out.println("== Form data does not validated ==");

            List<ObjectError> errors = result.getAllErrors();

            for(ObjectError error:errors) {
                System.out.println(error.getDefaultMessage());
            }

            return "createoffer";
        }

        // Controller -> Service -> Dao
        offerService.insert(offer);

        return "offercreated";
    }
}
