package com.alfred.ros.zeroconf;

import java.util.HashMap;

public class DiscoveredService extends com.github.rosjava.zeroconf_jmdns_suite.jmdns.DiscoveredService {
    public HashMap<String, String> properties = new HashMap<String, String>();
}
