package lab02;

public class Furnit {
	private int qty;
	private double price;
	private String id, note;

	public Furnit() {
		id = "";
		price = 0;
		qty = 0;
		note = "";
	}

	public Furnit(String id) {
		this.id = id;
		price = 0;
		qty = 0;
		note = "";
	}

	public Furnit(String id, double price) {
		this.id = id;
		this.price = price;
		qty = 0;
		note = "";
	}

	public Furnit(String id, double price, int qty) {
		this.id = id;
		this.price = price;
		this.qty = qty;
		note = "";
	}

	public Furnit(String id, double price, int qty, String note) {
		this.id = id;
		this.price = price;
		this.qty = qty;
		this.note = note;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void modQty(int qty) throws QtyException {
		if(this.qty + qty > 0 && this.qty + qty < 9999)
			this.qty += qty;
		else
			throw new QtyException();
	}

	public int getQty() {
		return qty;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

}

class QtyException extends Exception {
	public QtyException() {
		super();
	}
}