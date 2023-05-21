import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// visualize a square in the tictactoe board
public class CellPane extends HBox
{
   private static final Image xImage = new Image("hit.gif",40,40,false,false);
   private static final Image oImage = new Image("water.gif",40,40,false,false);
   private static final Image sunk = new Image("sunk.gif",40,40,false,false);
   private static final Image ship = new Image("ship.gif",40,40,false,false);
   private int row; // cell knows it's row/col location
   private int col;
   private ImageView imageView;
   private boolean user;
   
   public CellPane(CellStatus ch, int r, int c, boolean u)
   {
      this.setStyle("-fx-border-color: blue;"
               +"-fx-border-width: 5;");
      this.setPrefSize(50,50);
      row = r;
      col = c;
      user = u;
      setCell(ch);
   }
   public int getRow()
   {
      return row;
   }
   public int getCol()
   {
      return col;
   }
   public void setCell(CellStatus ch)
   {
     if(user)
     {
       if (ch == CellStatus.SUB)
       {
          imageView = new ImageView(ship);
          this.getChildren().add(imageView);
       }
       else if (ch == CellStatus.AIRCRAFT_CARRIER)
       {
         imageView = new ImageView(ship);
         this.getChildren().add(imageView);
       }
       else if (ch == CellStatus.BATTLESHIP)
       {
         imageView = new ImageView(ship);
         this.getChildren().add(imageView);
       }
       else if (ch == CellStatus.DESTROYER)
       {
         imageView = new ImageView(ship);
         this.getChildren().add(imageView);
       }
       else if (ch == CellStatus.CRUISER)
       {
         imageView = new ImageView(ship);
         this.getChildren().add(imageView);
       }
       else
         imageView = null;
     }
     else
       imageView = null;
       
     if (ch == CellStatus.SUB_HIT)
     {
        imageView = new ImageView(xImage);
        this.getChildren().add(imageView);
     }
     if(ch == CellStatus.SUB_SUNK)
     {
        imageView = new ImageView(sunk);
        this.getChildren().add(imageView);
     }
     if (ch == CellStatus.AIRCRAFT_CARRIER_HIT)
     {
       imageView = new ImageView(xImage);
       this.getChildren().add(imageView);
     }
     if (ch == CellStatus.AIRCRAFT_CARRIER_SUNK)
     {
       imageView = new ImageView(sunk);
       this.getChildren().add(imageView);
     }
     if (ch == CellStatus.BATTLESHIP_HIT)
     {
       imageView = new ImageView(xImage);
       this.getChildren().add(imageView);
     }
     if (ch == CellStatus.BATTLESHIP_SUNK)
     {
       imageView = new ImageView(sunk);
       this.getChildren().add(imageView);
     }
     if (ch == CellStatus.DESTROYER_HIT)
     {
       imageView = new ImageView(xImage);
       this.getChildren().add(imageView);
     }
     if (ch == CellStatus.DESTROYER_SUNK)
     {
       imageView = new ImageView(sunk);
       this.getChildren().add(imageView);
     }
     if (ch == CellStatus.CRUISER_HIT)
     {
       imageView = new ImageView(xImage);
       this.getChildren().add(imageView);
     }
     if (ch == CellStatus.CRUISER_SUNK)
     {
       imageView = new ImageView(sunk);
       this.getChildren().add(imageView);
     }
     if (ch == CellStatus.NOTHING_HIT)
     {
       imageView = new ImageView(oImage);
       this.getChildren().add(imageView);
     }
     
      
       
       
  
   }
   



}
