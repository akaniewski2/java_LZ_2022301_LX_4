package pl.arkani.LZ_2022301_LX.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.Tutorial;


@Service
public class TutorialService {



  static List<Tutorial> tutorials = new ArrayList<Tutorial>();
  static long id = 0;

  public List<Tutorial> findAll() {
    return tutorials;
  }

  public List<Tutorial> findByTitleContaining(String title) {
    return tutorials.stream().filter(tutorial -> tutorial.getTitle().contains(title)).toList();
  }

//  public TutorialService() {
//    tutorials = Arrays.asList(
//            new Tutorial("test1","desc1",true) ,
//            new Tutorial("test2","desc2",true) ,
//            new Tutorial("test3","desc3",true) ,
//            new Tutorial("test4","desc4",true)
//            );
//  }

  public Tutorial findById(long id) {
    return tutorials.stream().filter(tutorial -> id == tutorial.getId()).findAny().orElse(null);
  }

  public Tutorial save(Tutorial tutorial) {
    // update Tutorial
    if (tutorial.getId() != 0) {
      long _id = tutorial.getId();

      for (int idx = 0; idx < tutorials.size(); idx++)
        if (_id == tutorials.get(idx).getId()) {
          tutorials.set(idx, tutorial);
          break;
        }

      return tutorial;
    }

    // create new Tutorial
    tutorial.setId(++id);
    tutorials.add(tutorial);
    return tutorial;
  }

  public void deleteById(long id) {
    tutorials.removeIf(tutorial -> id == tutorial.getId());
  }

  public void deleteAll() {
    tutorials.removeAll(tutorials);
  }

  public List<Tutorial> findByPublished(boolean isPublished) {
    return tutorials.stream().filter(tutorial -> isPublished == tutorial.isPublished()).toList();
  }
}