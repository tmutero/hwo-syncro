package zw.hitrac.hwosync.registry.common;

import java.util.List;

public class ProviderCollection {

  private List<Provider> providers;

  public ProviderCollection (List<Provider> providers) {
    this.providers = providers;
  }


  public List<Provider> getProviders () {
    return providers;
  }

  public void setProviders (List<Provider> providers) {
    this.providers = providers;
  }

}
