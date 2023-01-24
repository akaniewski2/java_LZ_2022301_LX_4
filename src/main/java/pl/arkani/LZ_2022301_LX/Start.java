package pl.arkani.LZ_2022301_LX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.arkani.LZ_2022301_LX.model.TvChannel;
import pl.arkani.LZ_2022301_LX.repo.TvChannelRepo;

@Component
public class Start {

    private TvChannelRepo tvChannelRepo;

    @Autowired
    public Start(TvChannelRepo tvChannelRepo){
        this.tvChannelRepo=tvChannelRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {

//      --zapis
//        TvChannel tvChannel = new TvChannel();
//        tvChannel.setName("TVP 1");
//        tvChannel.setActive(0);
//        tvChannel.setCode("1153139325,1153122495");
//        tvChannel.setTag("ogolny,film");
//        tvChannel.setPrior(0);
//        tvChannelRepo.save(tvChannel);
//      --odczyt

//        tvChannel = new TvChannel(0,"TVP 2",null,0,"1153155135,1153122495","ogolny,film",0);
//        tvChannelRepo.save(tvChannel);
//
//        Iterable<TvChannel> all = tvChannelRepo.findAll();
//        all.forEach(System.out::println);
//
//        all = tvChannelRepo.findByName("TVP1");
//        all.forEach(System.out::println);

    }

}
