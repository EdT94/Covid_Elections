package covid_Elections;
import java.util.ArrayList;
public class BallotBox {

	public static int counter = 0;
	private String adress;
	private ArrayList<Citizen> allowedCitizens;
	private int number = 1;
	private int numOfCitizens;
	private int numOfVoted;

	public BallotBox(String adress) {
		this.adress = adress;
		this.allowedCitizens = new ArrayList<Citizen>();
		this.setNumber(number + counter);
		this.numOfCitizens = 0;
		this.numOfVoted = 0;
		counter++;
	}

	public BallotBox() {
		this.setNumber(number + counter);
		counter++;
		this.allowedCitizens = new ArrayList<Citizen>();
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public String getThisClass() {
		return "BallotBox";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getVotingPercentage() {
		double num=(100/numOfCitizens)*numOfVoted;
		if(numOfVoted==numOfCitizens)
			return "there is 100% voting";
		return "there is "+num+"% voting";
	}

	@Override
	public String toString() {
		return "Ballot box number: " + this.getNumber() + " , located in '" + this.getAdress() + "' street";
	}

	public int getNumOfVoted() {
		return numOfVoted;
	}

	public void plusNumOfVoted() {
		this.numOfVoted = numOfVoted + 1;
	}

	public int getNumOfCitizens() {
		return numOfCitizens;
	}

	public void setNumOfVoted(int number) {
		this.numOfVoted =number;
	}

	public ArrayList<Citizen> getAllowedCitizens() {
		return allowedCitizens;
	}

	public boolean equals(BallotBox other) {
		if(this.getAdress().equals(other.getAdress()))
			return true;
		return false;
	}
	
	public void setAllowedCitizen(Citizen citizen) {
	allowedCitizens.add(citizen);
	numOfCitizens++;
	}

	
}
