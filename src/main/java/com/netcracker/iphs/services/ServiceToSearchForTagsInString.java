package com.netcracker.iphs.services;

import com.netcracker.iphs.resources.Tag;
import java.util.List;

/**
 * Created by Maks on 21.04.2018.
 */
public interface ServiceToSearchForTagsInString {

  public List<Tag> findAllTagsInString(String string);
}
