package jOS.System.Egg.androidI;

import android.service.dreams.DreamService;


public class NyandroidDream extends DreamService {

    private Nyandroid.Board mBoard;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setInteractive(false);// changed
        setFullscreen(true);
        mBoard = new Nyandroid.Board(this, null);
        setContentView(mBoard);
    }
}
