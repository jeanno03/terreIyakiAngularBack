package terreIyaki.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NonNull;

@Entity
public class Payment {

	private Long id;
	private @NonNull Date registeredDate;
	private @NonNull float amount;

	private MyOrder myOrder;

	private Historisation historisation;

	private PaymentOption paymentOption;

	public Payment() {
		super();
	}

	public Payment(Date registeredDate, float amount) {
		super();
		this.registeredDate = registeredDate;
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Transient
	public Long getTheId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@ManyToOne
	@JoinColumn(name = "my_order_id")
	public MyOrder getMyOrder() {
		return myOrder;
	}

	public void setMyOrder(MyOrder myOrder) {
		this.myOrder = myOrder;
	}

	@JsonIgnore
	@OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
	public Historisation getHistorisation() {
		return historisation;
	}

	public void setHistorisation(Historisation historisation) {
		this.historisation = historisation;
	}

	@ManyToOne
	@JoinColumn(name = "payment_option_id")
	public PaymentOption getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(PaymentOption paymentOption) {
		this.paymentOption = paymentOption;
	}

	@Override
	public String toString() {
		return "\nPayment [id=" + id + ", registeredDate=" + registeredDate + ", amount=" + amount + "]";
	}

}
