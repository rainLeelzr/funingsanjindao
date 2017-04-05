package com.funing.commonfn.model;

public class Notice implements Entity {
	
	private static final long serialVersionUID = 1L;
	
	/**  */
	protected Integer id;
	
	/**  */
	protected Integer itemType;
	
	/**  */
	protected java.math.BigDecimal quantity;
	
	/**  */
	protected java.util.Date tranTimes;
	
	/**  */
	protected Integer userId;
	
	/**  */
	protected Integer way;
	
 	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getItemType() {
		return itemType;
	}
	
	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}
	
	public java.math.BigDecimal getQuantity() {
		return quantity;
	}
	
	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	public java.util.Date getTranTimes() {
		return tranTimes;
	}
	
	public void setTranTimes(java.util.Date tranTimes) {
		this.tranTimes = tranTimes;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getWay() {
		return way;
	}
	
	public void setWay(Integer way) {
		this.way = way;
	}
	
 	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id = ").append(id).append(", ");
		builder.append("itemType = ").append(itemType).append(", ");
		builder.append("quantity = ").append(quantity).append(", ");
		builder.append("tranTimes = ").append(tranTimes).append(", ");
		builder.append("userId = ").append(userId).append(", ");
		builder.append("way = ").append(way);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Notice other = (Notice) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}