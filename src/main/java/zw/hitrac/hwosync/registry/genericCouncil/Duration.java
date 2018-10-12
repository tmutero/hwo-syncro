/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this templates file, choose Tools | Templates
 * and open the templates in the editor.
 */
package zw.hitrac.hwosync.registry.genericCouncil;

import java.util.Date;

public class Duration {
  private Date startDate;
  private Date endDate;
  private Long id;
  private Short active;

  public Date getStartDate () {
    return startDate;
  }

  public void setStartDate (Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate () {
    return endDate;
  }

  public void setEndDate (Date endDate) {
    this.endDate = endDate;
  }


  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public Short getActive () {
    return active;
  }

  public void setActive (Short active) {
    this.active = active;
  }


}
