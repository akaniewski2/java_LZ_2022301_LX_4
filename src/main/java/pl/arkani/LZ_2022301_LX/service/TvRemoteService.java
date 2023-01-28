package pl.arkani.LZ_2022301_LX.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.TvRemote;
import pl.arkani.LZ_2022301_LX.repo.TvRemoteRepo;

@Service
public class TvRemoteService {


    private TvRemoteRepo tvRemoteRepo;
    @Autowired
    public TvRemoteService(TvRemoteRepo tvRemoteRepo) {
        this.tvRemoteRepo = tvRemoteRepo;
    }



    public TvRemote save(TvRemote e) {
        return tvRemoteRepo.save(e);

    }
}
