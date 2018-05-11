package com.netcracker.iphs.services.serviceImpl;

import com.netcracker.iphs.resources.Tag;
import com.netcracker.iphs.services.ServiceToSearchForTagsInString;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by Maks on 21.04.2018.
 */
@Service
public class ServiceToSearchForTagsInStringImpl implements ServiceToSearchForTagsInString {

  private List<Tag> tags;

  public ServiceToSearchForTagsInStringImpl() {
    this.tags = Tag.getAllTags();
  }

  public List<Tag> findAllTagsInString(String row) {
    String prepareString = row.toLowerCase();
    List<Tag> result = new ArrayList<Tag>();
    tags.forEach(tag -> {
      if (prepareString.contains(tag.getValue())) {
        result.add(tag);
      }
    });
    return result;
  }
}
