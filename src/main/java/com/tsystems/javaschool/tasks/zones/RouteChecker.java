package com.tsystems.javaschool.tasks.zones;

import java.util.ArrayList;
import java.util.List;

public class RouteChecker {

    public boolean checkRoute(List<Zone> zoneState, List<Integer> requestedZoneIds) {

        List<List<Integer>> tempList = getAllPermutations(requestedZoneIds);

        return finalCheckRoute(zoneState, tempList);

    }

    private List<List<Integer>> getAllPermutations(List<Integer> requestedZoneIds) {

        List<List<Integer>> permutations = new ArrayList<>();

        permutations.add(new ArrayList<>());

        for (int i = 0; i < requestedZoneIds.size(); i++) {

            List<List<Integer>> current = new ArrayList<>();

            for (List<Integer> permutation : permutations) {
                for (int j = 0, n = permutation.size() + 1; j < n; j++) {
                    List<Integer> temp = new ArrayList<Integer>(permutation);
                    temp.add(j, requestedZoneIds.get(i));
                    current.add(temp);
                }
            }
            permutations = new ArrayList<>(current);
        }

        return permutations;

    }

    private Zone findZoneById(List<Zone> zoneState, Integer id) {

        Zone resultZone = null;

        for (Zone zone : zoneState) {
            if (zone.getId() == id) {
                resultZone = zone;
            }
        }
        return resultZone;
    }

    private boolean finalCheckRoute(List<Zone> zoneState, List<List<Integer>> inputList) {

        boolean connected = false;

        for (List<Integer> variant : inputList) {

            for (int i = 0; i < variant.size() - 1; i++) {

                Zone source = findZoneById(zoneState, variant.get(i));
                Zone target = findZoneById(zoneState, variant.get(i + 1));

                if (source.getNeighbours().contains(target.getId()) || target.getNeighbours().contains(source.getId())) {
                    connected = true;
                } else {
                    connected = false;
                    break;
                }
            }

            if (connected) {
                break;
            }
        }
        return connected;
    }
}
