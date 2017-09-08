package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {
    int floors = 0; //number of floors of pyramide
    boolean canBuild = false;
    int numberOfBlocks = 0;//number of pyramide blocks (numbers)
    int step = 1;
    int width;
    int height;

    public int[][] buildPyramid(List<Integer> inputNumbers) {
        Collections.sort(inputNumbers);
        for (int x : inputNumbers) {
            numberOfBlocks = numberOfBlocks + step;
            floors++;
            if (numberOfBlocks == inputNumbers.size()) {
                canBuild = true;
                break;
            }
            step++;
        }

        int[][] intArray;
        if (canBuild == true) {
            height = floors;
            width = floors * 2 - 1;
            int k = 0;
            int counter = 2;
            intArray = new int[height][width];
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < width; z++) {
                    if ((z == (width / 2) - y) || (z == (width / 2) + y) || ((y >= counter) && ((z == width / 2 - y + counter) || z == width / 2 + y - counter))) {
                        intArray[y][z] = inputNumbers.get(k);
                        k++;
                        if (y == counter + 2) {
                            counter = counter + 2;
                        }
                    }
                }
            }
        } else

        {
            throw new CannotBuildPyramidException("cannot build pyramid");
        }
        return intArray;
    }

}



