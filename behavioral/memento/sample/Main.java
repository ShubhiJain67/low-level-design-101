package behavioral.memento.sample;

public class Main {

    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
        History history = new History();

        editor.setText("Hello");
        System.out.println(editor.getText());
        history.save(editor.save());
        
        editor.setText("Hello World");
        System.out.println(editor.getText());
        history.save(editor.save());
        
        editor.setText("Hello World!");
        System.out.println(editor.getText());
        
        editor.restore(history.undo());
        System.out.println(editor.getText());
        
        editor.restore(history.undo());
        System.out.println(editor.getText());
    }
}