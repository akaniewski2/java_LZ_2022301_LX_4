package pl.arkani.LZ_2022301_LX.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.TvChannel;
import pl.arkani.LZ_2022301_LX.repo.TvChannelRepo;
@Service
public class TvChannelService {


    private TvChannelRepo tvChannelRepo;
    @Autowired
    public TvChannelService(TvChannelRepo tvChannelRepo) {
        this.tvChannelRepo = tvChannelRepo;
    }



    public TvChannel save(TvChannel e) {
        return tvChannelRepo.save(e);

    }
}
