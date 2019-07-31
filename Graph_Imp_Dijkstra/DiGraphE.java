package DiGraph_A5;

public class DiGraphE {
	long idNum = 0;
	String sLabel = "";
	String dLabel = "";
	long weight = 1;
	String eLabel = "";
	public DiGraphE(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.weight = weight;
		this.eLabel = eLabel;
	}
	public DiGraphE(long idNum, String sLabel, String dLabel, String eLabel) {
		this.idNum = idNum;
		this.sLabel = sLabel;
		this.eLabel = eLabel;
		this.eLabel = eLabel;
	}
	public DiGraphE(long idNum, String sLabel, String dLabel, long weight) {
		this.idNum = idNum;
		this.sLabel = sLabel;
		this.eLabel = dLabel;
		this.weight = weight;
	}
	public DiGraphE(long idNum, String sLabel, String dLabel) {
		this.idNum = idNum;
		this.sLabel = sLabel;
		this.eLabel = dLabel;
	}
}