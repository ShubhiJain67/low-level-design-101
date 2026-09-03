package creational.abstractfactory.sample;

import creational.abstractfactory.sample.interfaces.*;
import creational.abstractfactory.sample.factories.*;

public class Main {
    public static void main(String[] args) {
        UIType theme = UIType.LIGHT;
        IUIFactory uiFactory = UIFactory.getUIFactory(theme);
        renderApp(uiFactory);
        
    }

    private static void renderApp(IUIFactory uiFactory){
        IButton button = uiFactory.renderButton();
        ICheckBox checkbox = uiFactory.renderCheckBox();

        button.render();
        checkbox.render();
    }
}
