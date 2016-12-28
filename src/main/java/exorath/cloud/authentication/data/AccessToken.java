/*
 * Copyright 2016 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package exorath.cloud.authentication.data;

import java.util.Date;
import java.util.Objects;

/**
 * Temporary session id for the browser, stored in cookies.
 * Created by Toon Sevrin on 12/14/2016.
 */
public class AccessToken {

  public static final int ID_LENGTH = 128;
  private static final long EXPIRE_LENGTH = 86400 * 7; //1 week
  private String accessip;
  private String id;
  private Date expiry;

  public AccessToken() {
  }

  public AccessToken(String id, String accessip) {
    this.id = id;
    this.accessip = accessip;
    this.expiry = new Date(System.currentTimeMillis() + EXPIRE_LENGTH);
  }

  public String getIP() {
    return accessip;
  }

  public void setIP(String accessip) {
    this.accessip = accessip;
  }

  public String getId() {
    return id;
  }

  public boolean isExpired() {
    return getExpiry().getTime() <= System.currentTimeMillis();
  }

  public boolean isAllowed(String accessip) {
    return Objects.equals(this.accessip, accessip);
  }

  public Date getExpiry() {
    return expiry;
  }
}
