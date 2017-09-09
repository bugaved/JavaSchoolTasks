package com.tsystems.javaschool.tasks.zones;

import java.util.List;

public class RouteChecker {

    public boolean checkRoute(List<Zone> zoneState, List<Integer> requestedZoneIds) {
        boolean cr = true;
        boolean connected ;
        int conn = 0;
        if ((zoneState != null) && (requestedZoneIds != null)) {
            int[] x = new int[zoneState.size()];
            for (int k = 0; k < x.length; k++) {
                x[k] = zoneState.get(k).getId();
            }
            for (int p = 0; p < zoneState.size(); p++) {
                connected = false;
                for (int q = 0; q < zoneState.size(); q++) {
                    if (zoneState.get(p).getNeighbours().contains(zoneState.get(q).getId())) {
                        for (int qq = 0; qq < x.length; qq++) {
                            if ((x[qq] == zoneState.get(p).getId()) && (connected == false)) {
                                x[qq] = zoneState.get(q).getId();
                                connected = true;
                                conn = zoneState.get(q).getId();
                            }
                            if ((x[qq] == zoneState.get(q).getId()) && (connected == true)) {
                                x[qq] = conn;
                            }
                        }
                    }
                }
            }

            for (int xx = 1; xx < requestedZoneIds.size(); xx++) {
                if (x[requestedZoneIds.get(xx - 1)-1] != x[requestedZoneIds.get(xx)-1]) {
                    cr = false;
                }
            }
        }
        return cr;
    }

}

