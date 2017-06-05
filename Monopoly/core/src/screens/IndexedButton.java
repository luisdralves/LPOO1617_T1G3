package screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.lpoo1617t1g3.Monopoly;

public class IndexedButton extends TextButton {
    public int id;

    public IndexedButton(int id) {
        super("", Monopoly.btnStyleInvisible);
        if (id <= 39 && id >= 29)
            this.id = 39 - id;
        else if (id < 29 && id >= 11 && id % 2 == 0)
            this.id = (id + 51) / 2;
        else if (id < 29 && id >= 11 && id % 2 == 1)
            this.id = (49 - id) / 2;
        else if (id < 11 && id >= 0)
            this.id = id + 20;
    }
}
