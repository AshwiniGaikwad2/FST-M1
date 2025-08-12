package activities;

public class Activity1 {
	
	public static void main(String[] args) {
        CAR toyota = new CAR();
        toyota.make = 2014;
        toyota.color = "Black";
        toyota.transmission = "Manual";
    
        //Using Car class method
        toyota.displayCharacterstics();
        toyota.accelerate();
        toyota.brake();
    }

}


