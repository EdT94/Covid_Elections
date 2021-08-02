package covid_Elections;


public class Citizen {
	private String name;
	private int id;
	private int birthYear;
	private BallotBox ballotBox;
	private boolean isIsolate;

	Citizen(String name, int id, int birthYear, BallotBox ballotBox, boolean isIsolate) {
		this.name = name;
		this.id = id;
		this.birthYear = birthYear;
		this.ballotBox = ballotBox;
		this.isIsolate = isIsolate;
	}

	public Citizen() {
	}

	public boolean isIsolate() {
		return isIsolate;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) throws Exception {
		int c = 0;
		int num = id;
		while (num != 0) {
			num = num / 10;
			c++;
		}
		if (c != 9) {
			throw new Exception("Id must be 9 digits");
		}

		this.id = id;
	}

	public void setBirthYear(int birthYear) throws Exception {
		if (birthYear > 2002 && birthYear <= 2020) {
			throw new Exception("Age must be 18 at least");
		} else if (birthYear > 2020) {
			throw new Exception("Invalid year");
		}
		this.birthYear = birthYear;
	}

	public void setIsolate(boolean isIsolate) {
		this.isIsolate = isIsolate;
	}

	public BallotBox getBallotBox() {
		return this.ballotBox;
	}

	public void setBallotBox(BallotBox ballotBox) {
		this.ballotBox = ballotBox;
	}
	

	@Override
	public String toString() {
		return "The " + (this.getBirthYear() <= 2002 && this.getBirthYear() >= 1999 ? " Soldier '" : " Citizen '")
				+ name + "', " + "id: " + id + " ," + "birthYear: " + this.getBirthYear() + ", bellongs to "
				+ this.ballotBox.toString() + ", " + (isIsolate ? "is in isolation" : "is not in isolation");

	}

	public boolean equals(Citizen other) {
		if (this.getId() == other.getId()) {
			return true;
		}
		return false;
	}
}
