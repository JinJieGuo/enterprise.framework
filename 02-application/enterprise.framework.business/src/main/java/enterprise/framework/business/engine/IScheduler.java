package enterprise.framework.business.engine;

import enterprise.framework.business.auth.IAuthManager;

public interface IScheduler {
    IAuthManager authManager();
}
