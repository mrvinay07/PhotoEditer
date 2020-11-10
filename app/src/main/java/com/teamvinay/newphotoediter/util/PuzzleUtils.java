package com.teamvinay.newphotoediter.util;

import java.util.ArrayList;
import java.util.List;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.PuzzleLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.slant.OneSlantLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.slant.SlantLayoutHelper;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.slant.ThreeSlantLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.slant.TwoSlantLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.EightStraightLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.FiveStraightLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.FourStraightLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.NineStraightLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.OneStraightLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.SevenStraightLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.SixStraightLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.StraightLayoutHelper;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.ThreeStraightLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight.TwoStraightLayout;

public class PuzzleUtils {
    private static final String TAG = "PuzzleUtils";

    private PuzzleUtils() {
    }

    public static PuzzleLayout getPuzzleLayout(int i, int i2, int i3) {
        if (i == 0) {
            switch (i2) {
                case 1:
                    return (PuzzleLayout) new OneSlantLayout(i3);
                case 2:
                    return (PuzzleLayout) new TwoSlantLayout(i3);
                case 3:
                    return (PuzzleLayout) new ThreeSlantLayout(i3);
                default:
                    return (PuzzleLayout) new OneSlantLayout(i3);
            }
        } else {
            switch (i2) {
                case 1:
                    return (PuzzleLayout) new OneStraightLayout(i3);
                case 2:
                    return (PuzzleLayout) new TwoStraightLayout(i3);
                case 3:
                    return (PuzzleLayout) new ThreeStraightLayout(i3);
                case 4:
                    return (PuzzleLayout) new FourStraightLayout(i3);
                case 5:
                    return (PuzzleLayout) new FiveStraightLayout(i3);
                case 6:
                    return (PuzzleLayout) new SixStraightLayout(i3);
                case 7:
                    return (PuzzleLayout) new SevenStraightLayout(i3);
                case 8:
                    return (PuzzleLayout) new EightStraightLayout(i3);
                case 9:
                    return (PuzzleLayout) new NineStraightLayout(i3);
                default:
                    return (PuzzleLayout) new OneStraightLayout(i3);
            }
        }
    }

    public static List<PuzzleLayout> getAllPuzzleLayouts() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(SlantLayoutHelper.getAllThemeLayout(2));
        arrayList.addAll(SlantLayoutHelper.getAllThemeLayout(3));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(2));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(3));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(4));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(5));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(6));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(7));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(8));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(9));
        return arrayList;
    }

    public static List<PuzzleLayout> getPuzzleLayouts(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(SlantLayoutHelper.getAllThemeLayout(i));
        arrayList.addAll(StraightLayoutHelper.getAllThemeLayout(i));
        return arrayList;
    }
}