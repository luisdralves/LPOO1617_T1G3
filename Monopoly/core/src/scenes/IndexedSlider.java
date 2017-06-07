package scenes;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.lpoo1617t1g3.Monopoly;

/**
 * Created by up201405308 on 07/06/2017.
 */

public class IndexedSlider extends Slider {
    public int id;

    public IndexedSlider(int min, int max, int id) {
        super(min, max, 10, false, Monopoly.sldStyle);
        this.id = id;
    }

    public void set(int min, int max) {
        IndexedSlider i2 = new IndexedSlider(min, max, this.id);
        i2.addListener(getListeners().get(0));
    }
}
