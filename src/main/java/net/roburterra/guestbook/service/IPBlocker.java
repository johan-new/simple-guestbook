package net.roburterra.guestbook.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 *
 *
 */

@Service
public class IPBlocker {

    //default 3600000*12 which is 12 hours
    private static Long banDuration;

    private final Log log = LogFactory.getLog(getClass());

    private Map<String, Long> blockedAddresses;

    IPBlocker(){
        blockedAddresses = new HashMap<>();
        banDuration = 3600000L*12;
        log.debug(getClass().getSimpleName() + "\n" + "Ban duration: " + banDuration + " milliseconds");
    }

    /**
     *  Added some sleep to handle flooding requests
     */
    public boolean isBlocked(String ipaddress) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        if (blockedAddresses.containsKey(ipaddress)) {
            return !banDurationFinnished(ipaddress);
        } else {
            log.debug("*** Banning address " + ipaddress);
            blockedAddresses.put(ipaddress,System.currentTimeMillis());
            return false;
        }
    }

    private boolean banDurationFinnished(String ipaddress) {
        if (System.currentTimeMillis()-blockedAddresses.get(ipaddress) >= banDuration) {
            log.debug("Removing ban of " + ipaddress);
            blockedAddresses.remove(ipaddress);
            return true;
        }
        return false;
    }
}
