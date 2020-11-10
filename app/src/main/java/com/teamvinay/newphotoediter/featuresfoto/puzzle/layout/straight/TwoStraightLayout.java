package com.teamvinay.newphotoediter.featuresfoto.puzzle.layout.straight;

import android.util.Log;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.Line;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.PuzzleLayout;
import com.teamvinay.newphotoediter.featuresfoto.puzzle.straight.StraightPuzzleLayout;

public class TwoStraightLayout {/*extends NumberStraightLayout {
    private float mRadio = 0.5f;

    public int getThemeCount() {
        return 6;
    }

    public TwoStraightLayout() {
    }

    public TwoStraightLayout(StraightPuzzleLayout straightPuzzleLayout, boolean z) {
        super(straightPuzzleLayout, z);
    }

    public TwoStraightLayout(int i) {
        super(i);
    }

    public TwoStraightLayout(float f, int i) {
        super(i);
        if (this.mRadio > 1.0f) {
            Log.e("NumberStraightLayout", "CrossLayout: the radio can not greater than 1f");
            this.mRadio = 1.0f;
        }
        this.mRadio = f;
    }

    public void layout() {
        switch (this.theme) {
            case 0:
                addLine(0, Line.Direction.HORIZONTAL, this.mRadio);
                return;
            case 1:
                addLine(0, Line.Direction.VERTICAL, this.mRadio);
                return;
            case 2:
                addLine(0, Line.Direction.HORIZONTAL, 0.33333334f);
                return;
            case 3:
                addLine(0, Line.Direction.HORIZONTAL, 0.6666667f);
                return;
            case 4:
                addLine(0, Line.Direction.VERTICAL, 0.33333334f);
                return;
            case 5:
                addLine(0, Line.Direction.VERTICAL, 0.6666667f);
                return;
            default:
                addLine(0, Line.Direction.HORIZONTAL, this.mRadio);
                return;
        }
    }

    public PuzzleLayout clone(PuzzleLayout puzzleLayout) {
        return new TwoStraightLayout((StraightPuzzleLayout) puzzleLayout, true);
    }*/
}