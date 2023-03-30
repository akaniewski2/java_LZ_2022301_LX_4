package pl.arkani.LZ_2022301_LX.service;

import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.GoodsRating;

@Service
public class GoodsRatingValidationService {
    public String validateGoods(GoodsRating goodsRating) {
        String message = "";
        if (goodsRating.getCountry() ==null ) {

                message = "# NULLLLLL " ;

        }
        return message;
    }
}