package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	private static List<Prices> prices = new List<>();
	private static Stack<Share> srcStack = new Stack<>();
	private static Queue<Share> srcQueue = new Queue<>();
	private static Stack<Share> tempStack = new Stack<>();
	private static Queue<Share> tempQueue = new Queue<>();
	private static double capitalGain = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			readSharesFile("src\\shares.txt");
			readPricesFile("src\\dailyPrice.txt");
			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color:#efebce");
			Label title = new Label("Welcome to Stock Store");
			title.setStyle("-fx-font-size:25px;-fx-text-fill:#bb8588");
			title.setPadding(new Insets(0, 0, 0, 150));
			root.setTop(title);

			VBox menu = new VBox(40);
			menu.setPadding(new Insets(60, 0, 0, 0));
			Button getStack = new Button("Old Sheres");
			getStack.setMaxWidth(Double.MAX_VALUE);
			Button getQueue = new Button("New Shares");
			getQueue.setMaxWidth(Double.MAX_VALUE);
			
			menu.getChildren().addAll(getStack, getQueue);

			GridPane gp1 = new GridPane();
			gp1.setPadding(new Insets(55,55,55,100));
			Label need = new Label("Sell OR Buy");
			//need.setStyle("-fx-text-fill:#efebce   ");
			ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList("Sell", "Buy"));
			cb.setStyle("-fx-color:white   ");
			Label amo = new Label("Amount");
			//amo.setStyle("-fx-text-fill:#efebce   ");
			TextField tf1 = new TextField();
			
			Label company = new Label("Company");
			//company.setStyle("-fx-text-fill:#d6ce93   ");
			ChoiceBox<String> companycb = new ChoiceBox<String>(FXCollections.observableArrayList("X", "Y", "Z"));
			companycb.setStyle("-fx-color:white   ");
			Button done1 = new Button("Done");
			//lightgray 
			done1.setStyle("-fx-color:#baa587   ");
			Button write1 = new Button("Write it to file");
			write1.setStyle("-fx-color:#baa587  ");
			Label old=new Label("Old Sheres First");
			gp1.add(need, 0, 0);
			gp1.add(cb, 1, 0);
			gp1.add(old, 2, 0);
			gp1.add(amo, 0, 1);
			gp1.add(tf1, 1, 1);
			gp1.add(company, 0, 2);
			gp1.add(companycb, 1, 2);
			gp1.add(done1, 1, 3);
			gp1.add(write1, 2, 3);

			gp1.setHgap(30);
			gp1.setVgap(20);
			GridPane gp2 = new GridPane();
			gp2.setPadding(new Insets(55,55,55,100));
			Label need2 = new Label("Sell OR Buy");
			//need2.setStyle("-fx-text-fill:lightgray   ");
			ChoiceBox<String> cb2 = new ChoiceBox<String>(FXCollections.observableArrayList("Sell", "Buy"));
			cb2.setStyle("-fx-color:white   ");
			Label amo2 = new Label("Amount");
			//amo2.setStyle("-fx-text-fill:lightgray   ");
			TextField tf2 = new TextField();

			Label company2 = new Label("Company");
			//company2.setStyle("-fx-text-fill:lightgray   ");
			ChoiceBox<String> companycb2 = new ChoiceBox<String>(FXCollections.observableArrayList("X", "Y", "Z"));
			companycb2.setStyle("-fx-color:white   ");
			Button done2 = new Button("Done");
			done2.setStyle("-fx-color:#baa587  ");
			Button write2 = new Button("Write it to file");
			write2.setStyle("-fx-color:#baa587   ");
			Label newS=new Label("New Sheres First");
			gp2.add(need2, 0, 0);
			gp2.add(cb2, 1, 0);
			gp2.add(newS, 2, 0);
			gp2.add(amo2, 0, 1);
			gp2.add(tf2, 1, 1);
			gp2.add(company2, 0, 2);
			gp2.add(companycb2, 1, 2);
			gp2.add(done2, 1, 3);
			gp2.add(write2, 2, 3);
			gp2.setHgap(30);
			gp2.setVgap(20);
			write1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						FileWriter myWriter = new FileWriter("src//shares.txt");
						Stack<Share> printStack=new Stack<>();
						while(!srcStack.isEmpty())
							printStack.push(srcStack.pop());
						
						while(!printStack.isEmpty()) {
							Share line=printStack.pop();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy");
							//	formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

								//Date date = formatter.parse("22-09-2018");
								String formattedDate = formatter.format(line.getDate());
							myWriter.write(line.getNumber()+", "+line.getPrice()+", "+line.getCompany()+", "+formattedDate+"\n");
						}
						myWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
			write2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						FileWriter myWriter = new FileWriter("src//shares.txt");
						while(!srcQueue.isEmpty()) {
							Share line=srcQueue.dequeue();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy");
							//	formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

								//Date date = formatter.parse("22-09-2018");
								String formattedDate = formatter.format(line.getDate());
							myWriter.write(line.getNumber()+", "+line.getPrice()+", "+line.getCompany()+", "+formattedDate+"\n");
						}
						myWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
			root.setCenter(gp1);
			getStack.setStyle("-fx-color:#a3a380 ");
			
			getQueue.setStyle("-fx-color:#d8a48f");
			getStack.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getStack.setStyle("-fx-color:#a3a380 ");
					getQueue.setStyle("-fx-color:#d8a48f");
					root.setCenter(gp1);
				}

			});
			getQueue.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getStack.setStyle("-fx-color:#d8a48f");
					getQueue.setStyle("-fx-color:#a3a380 ");
					root.setCenter(gp2);
				}

			});

			root.setLeft(menu);

			root.setPadding(new Insets(50));

			Label total = new Label("Capital Gain = " + capitalGain);
			total.setStyle("-fx-font-size:15px;");
			total.setPadding(new Insets(0, 0, 50, 400));
			root.setBottom(total);
			done1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {

					double price = 0;
					int num = Integer.parseInt(tf1.getText());
					String com = companycb.getValue();
					Node<Prices> temp = prices.first;
					while (temp != null) {
						if (temp.data.getCompany().compareTo(com) == 0) {
							price = temp.data.getPrice();

							break;
						}
						temp = temp.next;
					}
					if (cb.getValue().compareTo("Buy") == 0) {

						Date date = new Date();

						Share newOne = new Share(num, price, com, date);
						srcStack.push(newOne);

					} else {
						// .list.first;
						//srcStack.print();
						

						while (!srcStack.isEmpty()) {
						//	System.out.println(i+""+temp2);

							Share temp2 = srcStack.pop();

							if (num == 0 && !srcStack.isEmpty()) {
								
								while (!srcStack.isEmpty()) {
									tempStack.push(temp2);
									temp2=srcStack.pop();
								}
								tempStack.push(temp2);
								break;
							} else if (temp2.getCompany().compareTo(com) == 0) {
								

								double diff = price - temp2.getPrice();

								
								int tot =0;
								

								// tempQueue.print();
								if (num < temp2.getNumber()) {
									
									 tot = temp2.getNumber() - num;
									 capitalGain  =capitalGain+( num * diff);
										
									
									temp2.setNumber(tot);
									tempStack.push(temp2);
									
									num=0;
								} else {
									
									num -= temp2.getNumber();
									capitalGain  =capitalGain+( temp2.getNumber() * diff);
									tot=num-temp2.getNumber();
									
									
								}
								

								total.setText("Capital Gain = " + capitalGain);
							} else {
								
								tempStack.push(temp2);
							}
						
						
							 
						}
					
						tempStack.print();
						while (!tempStack.isEmpty()) {
							srcStack.push(tempStack.pop());
							
						}
						
						srcStack.print();
						
					}
				}

			});
			done2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					double price = 0;
					int num = Integer.parseInt(tf2.getText());
					String com = companycb2.getValue();
					Node<Prices> temp = prices.first;
					while (temp != null) {
						if (temp.data.getCompany().compareTo(com) == 0) {
							price = temp.data.getPrice();

							break;
						}
						temp = temp.next;
					}
					if (cb2.getValue().compareTo("Buy") == 0) {

						Date date = new Date();

						Share newOne = new Share(num, price, com, date);
						srcQueue.enqueue(newOne);

					} else {
						// .list.first;
						Share temp2 = srcQueue.dequeue();
						while (!srcQueue.isEmpty()) {
							
							if (num == 0 && !srcQueue.isEmpty()) {

								while (!srcQueue.isEmpty()) {
									tempQueue.enqueue(temp2);
									temp2=srcQueue.dequeue();
								}
								tempQueue.enqueue(temp2);
								break;
							} else if (temp2.getCompany().compareTo(com) == 0) {
								
								double diff = price - temp2.getPrice();

								
								int tot =0;
								
								// tempQueue.print();
								if (num < temp2.getNumber()) {
									
									 tot = temp2.getNumber() - num;
									 capitalGain  =capitalGain+( num * diff);
									
									temp2.setNumber(tot);
									tempQueue.enqueue(temp2);
									
									num=0;
								} else {
									num -= temp2.getNumber();
									capitalGain  =capitalGain+( temp2.getNumber() * diff);
									tot=num-temp2.getNumber();
									
									
								}
								

								
								total.setText("Capital Gain = " + capitalGain);
							} else {

								tempQueue.enqueue(temp2);

							}
							temp2=srcQueue.dequeue();

						}
						
						
						while (!tempQueue.isEmpty()) {
							srcQueue.enqueue(tempQueue.dequeue());
							
						}
						
					}
				}

			});
			Scene scene = new Scene(root, 700, 470);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void readSharesFile(String path) throws NumberFormatException, ParseException {
		File file = new File(path);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNext()) {
				String[] line = in.nextLine().split(",");
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Share shares = new Share(Integer.parseInt(line[0]), Double.parseDouble(line[1]), line[2].trim(),
						format.parse(line[3]));
				srcStack.push(shares);
				srcQueue.enqueue(shares);
			}

			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readPricesFile(String path) {
		File file = new File(path);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNext()) {
				String[] line = in.nextLine().split(",");
				prices.addLast(new Prices(line[0], Double.parseDouble(line[1])));
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
