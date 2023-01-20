import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import socket.CarSocketConnection;
import socket.ICarControlSubscriber;
import socket.ICarMessage;

public class Controller {

    public TextField hostTextField;

    private CarSocketConnection carSocket = null;

    public void moveCarButtonClicked(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();

        String direction = source.getId();

        carSocket.sendMessage(direction);
    }

    public void connectClicked(ActionEvent actionEvent) {
        carSocket = new CarSocketConnection(hostTextField.getText(), 2612);

        ICarControlSubscriber sub = new ICarControlSubscriber() {
            @Override
            public void messageReceived(ICarMessage msg) {
                System.out.println(msg.getMessage());
            }
        };

        carSocket.addSubscriber(sub);
    }
}
