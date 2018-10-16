package zw.hitrac.hwosync.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zw.hitrac.hwosync.service.RegistrySyncService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {
    @Autowired
    private RegistrySyncService registrySyncService;


    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //Setup the scheduler at 15:50 everyday
    @Scheduled(cron = "0 53 11 * * ? ")
    public void reportCurrentTime() {
        log.info("Sync has started {}", dateFormat.format(new Date()));
        registrySyncService.syncAll();
        log.info("Sync has finished {}", dateFormat.format(new Date()));
    }
}

