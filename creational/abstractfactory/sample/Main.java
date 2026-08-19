package creational.abstractfactory.sample;

import creational.abstractfactory.sample.interfaces.*;
import creational.abstractfactory.sample.factories.*;

public class Main {

    public static void main(String[] args) {
        IUIFactory darkUIFactory = UIFactory.getUIFactory(UIType.DARK);
        IButton dbutton = darkUIFactory.renderButton();
        ICheckBox dcheckBox = darkUIFactory.renderCheckBox();
        dbutton.render();
        dcheckBox.render();

        IUIFactory lightUIFactory = UIFactory.getUIFactory(UIType.LIGHT);
        IButton lbutton = lightUIFactory.renderButton();
        ICheckBox lcheckBox = lightUIFactory.renderCheckBox();

        lbutton.render();
        lcheckBox.render();
    }
}
