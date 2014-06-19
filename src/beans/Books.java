package beans;

public class Books {
	
	private int id;
	private String name;	// 图书名称
	private String author;	// 图书作者
	private Double price;	// 图书价格
	
	private Integer count;
	
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
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPrice() {
		return price;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCount() {
		return count;
	}
	public void incrCount(){
		this.count += 1;
	}
	public void decrCount(){
		if(this.count>=1){
			this.count -= 1;
		}
	}
	public Double getAllPrice(){ 	// 获取当前图书的汇总信息   单价*数目
		return this.count * this.price;
	}
	
	public Boolean equals(Books bo){	// 重写了判断相等的条件
		if(bo.getId() == this.getId()){
			return true;
		} else {
			return false;
		}
		
	}
	
}
