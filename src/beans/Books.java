package beans;

public class Books {
	
	private int id;
	private String name;	// ͼ������
	private String author;	// ͼ������
	private Double price;	// ͼ��۸�
	
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
	public Double getAllPrice(){ 	// ��ȡ��ǰͼ��Ļ�����Ϣ   ����*��Ŀ
		return this.count * this.price;
	}
	
	public Boolean equals(Books bo){	// ��д���ж���ȵ�����
		if(bo.getId() == this.getId()){
			return true;
		} else {
			return false;
		}
		
	}
	
}
