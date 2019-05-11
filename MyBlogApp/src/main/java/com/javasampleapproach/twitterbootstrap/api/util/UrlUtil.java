/*package com.javasampleapproach.twitterbootstrap.api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlUtil {

  @Value("${socio.seer.media.url}")
  private String mediaUrl;

  private String urlFormat = "%s/%s/%s";

  public String getUrl(String fileLocation, String fileName) {
    return String.format(urlFormat, mediaUrl, fileLocation, fileName);
  }

}
*/