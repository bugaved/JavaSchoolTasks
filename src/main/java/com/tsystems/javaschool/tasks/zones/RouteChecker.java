package com.tsystems.javaschool.tasks.zones;

import java.util.List;

public class RouteChecker {


    /**
     * Checks whether required zones are connected with each other.
     * By connected we mean that there is a path from any zone to any zone from the requested list.
     * <p>
     * Each zone from initial state may contain a list of it's neighbours. The link is defined as unidirectional,
     * but can be used as bidirectional.
     * For instance, if zone A is connected with B either:
     * - A has link to B
     * - OR B has a link to A
     * - OR both of them have a link to each other
     *
     * @param zoneState        current list of all available zones
     * @param requestedZoneIds zone IDs from request
     * @return true of zones are connected, false otherwise
     */
    public boolean checkRoute(List<Zone> zoneState, List<Integer> requestedZoneIds) {
        boolean cr = true;
        if ((zoneState != null) && (requestedZoneIds != null)) {
            int[] x = new int[requestedZoneIds.size()];
            for (int k = 0; k < x.length; k++) {
                x[k] = requestedZoneIds.get(k);
            }
            for (int p = 0; p < zoneState.size(); p++)
                for (int q = 0; q < zoneState.size(); q++) {
                    if (zoneState.get(q).getNeighbours().contains(zoneState.get(p).getId())) {
                        for (int qq =0; qq<x.length; qq++) {
                            if (x[qq] == zoneState.get(q).getId()) {
                                x[qq] = zoneState.get(p).getId();
                            }
                        }
                    }
                }
            for (int pp = 1; pp < x.length; pp++) {
                if (x[pp-1] != x[pp]) {
                    cr = false;
                }
            }

        }
        return cr;
    }

}
