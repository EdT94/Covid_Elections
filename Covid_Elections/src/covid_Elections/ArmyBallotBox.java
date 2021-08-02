package covid_Elections;

public class ArmyBallotBox extends BallotBox {

	public ArmyBallotBox() {
	}

	public ArmyBallotBox(String adress) {
		super(adress);
	}

	public String getThisClass() {
		return "ArmyBallotBox";
	}

	public boolean addCitizen(Citizen soldier) {
		if (soldier.isIsolate() || !(soldier instanceof Citizen))
			return false;

		if (soldier.getBirthYear() > 2002 || soldier.getBirthYear() < 1999)
			return false;
		return true;
	}

	public String toString() {
		return "Army " + super.toString();
	}
}
