import javafx.application.Application; 
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import java.io.*;
public class GUI extends Application
{
   private GridPane user;
   private GridPane computer;
   private Game game;
   private BorderPane mainPane;
   private VBox statusPane;  // place for messages
   private Text status;
   private Text title;
   private Button exit, newGame;
   private HBox buttonPane; 
   @Override
   public void start(Stage primaryStage)throws IOException
   {
      user = new GridPane();
      computer = new GridPane();
      mainPane = new BorderPane();
      game = new Game();
      statusPane = new VBox();
      drawBoard();
      mainPane.setLeft(computer);
      mainPane.setRight(user);
      mainPane.setCenter(new Text("Made by Evan Rohan"));
      primaryStage.setTitle("BATTLESHIP"); 
      statusPane.setAlignment(Pos.CENTER);
      status = new Text("");
      title = new Text("BATTLESHIP");
      title.setFont(Font.font("Arial",24));
      status.setFont(Font.font("Arial",16));
      statusPane.getChildren().add(title);
      statusPane.getChildren().add(status);
      buttonPane = new HBox(10);
      buttonPane.setAlignment(Pos.CENTER);
      exit = new Button("Exit");
      exit.setOnAction( new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)
         {
            Platform.exit();
         }
       });
      buttonPane.getChildren().add(exit);
      
      newGame = new Button("New Game");
      newGame.setOnAction( new EventHandler<ActionEvent>()
      {
         public void handle(ActionEvent e)
         {
            game = new Game();
            drawBoard();
            status.setText("");
         }
       });
      buttonPane.getChildren().add(newGame);

      mainPane.setBottom(buttonPane);
      mainPane.setTop(statusPane);
      Scene scene = new Scene(mainPane);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
      // event handler for user clicking on a square
   public void handleClick(MouseEvent e)
   {  
      CellPane cp = (CellPane)(e.getSource());
      game.makePlayerMove(new Move(cp.getRow()+1,cp.getCol()+1));
      game.makeComputerMove();
         drawBoard();
         if (game.computerDefeated() || game.playerDefeated())
         {
            for (Node pane: computer.getChildren())
               pane.setOnMouseClicked(null);
            if(game.computerDefeated())
               status.setText("you win!");
            if(game.playerDefeated())
               status.setText("you lost!");
         }
           
   }  
   public void drawBoard()
   {
      computer.getChildren().clear();
      user.getChildren().clear();
      for(int r = 0; r<10; r++)
      {
         for(int c = 0; c<10; c++)
         {
            CellPane cp = new CellPane(game.getComputerStatus(r,c),r,c,false);
            if(game.getComputer().moveAvailable(new Move(r+1,c+1)))
               cp.setOnMouseClicked(this::handleClick);
            computer.add(cp,c,r);
         }
      }
      
      for(int r1 = 0; r1<10; r1++)
      {
         for(int c1 = 0; c1<10; c1++)
         {
            CellPane cp = new CellPane(game.getUserStatus(r1,c1),r1,c1,true);
            user.add(cp,c1,r1);
         }
      }
   }
   public static void main(String[]args)
   {
     launch(args);
   }
}