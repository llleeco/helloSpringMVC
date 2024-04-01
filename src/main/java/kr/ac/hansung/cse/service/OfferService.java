package kr.ac.hansung.cse.service;

import kr.ac.hansung.cse.dao.OfferDao;
import kr.ac.hansung.cse.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    //service -> dao(데이터 액세스 레이어)
    @Autowired
    private OfferDao offerDao;

    public List<Offer> getAllOffers() {
        return offerDao.getOffers();
    }

    public void insert(Offer offer) {
        offerDao.insert(offer);
    }
}
