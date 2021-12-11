package df.cn.edu.zafu;

public class BookInfo {

	public BookInfo() {
		super();
	}

	public BookInfo(String name, String author, int pages, double price) {
		super();
		this.name = name;
		this.author = author;
		this.pages = pages;
		this.price = price;
	}
	
	public BookInfo(int id, String name, String author, int pages, double price) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.pages = pages;
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookInfo [id=" + id + ", name=" + name + ", author=" + author + ", pages=" + pages + ", price=" + price
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pages;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookInfo other = (BookInfo) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pages != other.pages)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	private int id;
	private String name; // 书名 
	private String author;// 作者
	private int pages;// 页数
	private double price; // 价格
	
}
