package behavioral.mediator.sample;

public class Main {

    public static void main(String[] args) {
        IChatMediator chatRoom = new ChatRoom();

        User shubhi = new ChatUser(chatRoom, "Shubhi");
        User prateek = new ChatUser(chatRoom, "Prateek");
        User rama = new ChatUser(chatRoom, "Rama");
        User rajiv = new ChatUser(chatRoom, "Rajiv");

        chatRoom.addUser(shubhi);
        chatRoom.addUser(prateek);
        chatRoom.addUser(rama);
        chatRoom.addUser(rajiv);

        shubhi.send("Hello everyone!");
    }
}
