package com.iteye.wwwcomy.poi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization {
	private String id;
	private String unionloanName;
	private String date;
	private String principalAll;
	@JsonProperty(value = "unionloan_principal_all")
	private String unionloanPrincipalAll;
	@JsonProperty(value = "principal_payable")
	private String principalPayable;
	@JsonProperty(value = "unionloan_principal_payable")
	private String unionloanPrincipalPayable;
	@JsonProperty(value = "unionloan_principal")
	private String unionloanPrincipal;
	@JsonProperty(value = "unionloan_principal_jxj")
	private String unionloanPrincipalJxj;
	@JsonProperty(value = "unionloan_principal_jxjdd")
	private String unionloanPrincipalJxjdd;
	@JsonProperty(value = "principal_payable_increase")
	private String principalPayableIncrease;
	@JsonProperty(value = "customer_jxj")
	private String customerJxj;
	@JsonProperty(value = "amount_jxj")
	private String amountJxj;
	@JsonProperty(value = "avg_amount_jxj")
	private String avgAmountJxj;
	@JsonProperty(value = "use_customer_jxj")
	private String useCustomerJxj;
	@JsonProperty(value = "customer_jxjdd")
	private String customerJxjdd;
	@JsonProperty(value = "amount_jxjdd")
	private String amountJxjdd;
	@JsonProperty(value = "avg_amount_jxjdd")
	private String avgAmountJxjdd;
	@JsonProperty(value = "use_customer_jxjdd")
	private String useCustomerJxjdd;
	@JsonProperty(value = "prehandoutrate")
	private String prehandoutRate;
	@JsonProperty(value = "unionloanrate")
	private String unionloanRate;
	@JsonProperty(value = "pass_rate")
	private String passRate;
	@JsonProperty(value = "refuse_reason")
	private String refuseReason;
	@JsonProperty(value = "refuse_rate")
	private String refuseRate;
	@JsonProperty(value = "apply_customer")
	private String applyCustomer;
	@JsonProperty(value = "pass_customer")
	private String passCustomer;
	@JsonProperty(value = "m1+")
	private String m1Plus;

	@JsonProperty(access = Access.READ_ONLY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty(value = "unionloanname")
	public String getUnionloanName() {
		return unionloanName;
	}

	public void setUnionloanName(String unionloanName) {
		this.unionloanName = unionloanName;
	}

	@JsonProperty(value = "date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@JsonProperty(value = "principal_all")
	public String getPrincipalAll() {
		return principalAll;
	}

	public void setPrincipalAll(String principalAll) {
		this.principalAll = principalAll;
	}

	@JsonProperty(value = "unionloan_principal_all")
	public String getUnionloanPrincipalAll() {
		return unionloanPrincipalAll;
	}

	public void setUnionloanPrincipalAll(String unionloan_principalAll) {
		this.unionloanPrincipalAll = unionloan_principalAll;
	}

	@JsonProperty(value = "principal_payable")
	public String getPrincipalPayable() {
		return principalPayable;
	}

	public void setPrincipalPayable(String principalPayable) {
		this.principalPayable = principalPayable;
	}

	@JsonProperty(value = "unionloan_principal_payable")
	public String getUnionloanPrincipalPayable() {
		return unionloanPrincipalPayable;
	}

	public void setUnionloanPrincipalPayable(String unionloanPrincipalPayable) {
		this.unionloanPrincipalPayable = unionloanPrincipalPayable;
	}

	@JsonProperty(value = "unionloan_principal")
	public String getUnionloanPrincipal() {
		return unionloanPrincipal;
	}

	public void setUnionloanPrincipal(String unionloanPrincipal) {
		this.unionloanPrincipal = unionloanPrincipal;
	}

	@JsonProperty(value = "unionloan_principal_jxj")
	public String getUnionloanPrincipalJxj() {
		return unionloanPrincipalJxj;
	}

	public void setUnionloanPrincipalJxj(String unionloanPrincipalJxj) {
		this.unionloanPrincipalJxj = unionloanPrincipalJxj;
	}

	@JsonProperty(value = "unionloan_principal_jxjdd")
	public String getUnionloanPrincipalJxjdd() {
		return unionloanPrincipalJxjdd;
	}

	public void setUnionloanPrincipalJxjdd(String unionloanPrincipalJxjdd) {
		this.unionloanPrincipalJxjdd = unionloanPrincipalJxjdd;
	}

	@JsonProperty(value = "principal_payable_increase")
	public String getPrincipalPayableIncrease() {
		return principalPayableIncrease;
	}

	public void setPrincipalPayableIncrease(String principalPayableIncrease) {
		this.principalPayableIncrease = principalPayableIncrease;
	}

	@JsonProperty(value = "customer_jxj")
	public String getCustomerJxj() {
		return customerJxj;
	}

	public void setCustomerJxj(String customerJxj) {
		this.customerJxj = customerJxj;
	}

	@JsonProperty(value = "amount_jxj")
	public String getAmountJxj() {
		return amountJxj;
	}

	public void setAmountJxj(String amountJxj) {
		this.amountJxj = amountJxj;
	}

	@JsonProperty(value = "avg_amount_jxj")
	public String getAvgAmountJxj() {
		return avgAmountJxj;
	}

	public void setAvgAmountJxj(String avgAmountJxj) {
		this.avgAmountJxj = avgAmountJxj;
	}

	@JsonProperty(value = "use_customer_jxj")
	public String getUseCustomerJxj() {
		return useCustomerJxj;
	}

	public void setUseCustomerJxj(String useCustomerJxj) {
		this.useCustomerJxj = useCustomerJxj;
	}

	@JsonProperty(value = "customer_jxjdd")
	public String getCustomerJxjdd() {
		return customerJxjdd;
	}

	public void setCustomerJxjdd(String customerJxjdd) {
		this.customerJxjdd = customerJxjdd;
	}

	@JsonProperty(value = "amount_jxjdd")
	public String getAmountJxjdd() {
		return amountJxjdd;
	}

	public void setAmountJxjdd(String amountJxjdd) {
		this.amountJxjdd = amountJxjdd;
	}

	@JsonProperty(value = "avg_amount_jxjdd")
	public String getAvgAmountJxjdd() {
		return avgAmountJxjdd;
	}

	public void setAvgAmountJxjdd(String avgAmountJxjdd) {
		this.avgAmountJxjdd = avgAmountJxjdd;
	}

	@JsonProperty(value = "use_customer_jxjdd")
	public String getUseCustomerJxjdd() {
		return useCustomerJxjdd;
	}

	public void setUseCustomerJxjdd(String useCustomerJxjdd) {
		this.useCustomerJxjdd = useCustomerJxjdd;
	}

	@JsonProperty(value = "prehandoutrate")
	public String getPrehandoutRate() {
		return prehandoutRate;
	}

	public void setPrehandoutRate(String prehandoutRate) {
		this.prehandoutRate = prehandoutRate;
	}

	@JsonProperty(value = "unionloanrate")
	public String getUnionloanRate() {
		return unionloanRate;
	}

	public void setUnionloanRate(String unionloanRate) {
		this.unionloanRate = unionloanRate;
	}

	@JsonProperty(value = "pass_rate")
	public String getPassRate() {
		return passRate;
	}

	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	@JsonProperty(value = "refuse_reason")
	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	@JsonProperty(value = "refuse_rate")
	public String getRefuseRate() {
		return refuseRate;
	}

	public void setRefuseRate(String refuseRate) {
		this.refuseRate = refuseRate;
	}

	@JsonProperty(value = "apply_customer")
	public String getApplyCustomer() {
		return applyCustomer;
	}

	public void setApplyCustomer(String applyCustomer) {
		this.applyCustomer = applyCustomer;
	}

	@JsonProperty(value = "pass_customer")
	public String getPassCustomer() {
		return passCustomer;
	}

	public void setPassCustomer(String passCustomer) {
		this.passCustomer = passCustomer;
	}

	@JsonProperty(value = "m1+")
	public String getM1Plus() {
		return m1Plus;
	}

	public void setM1Plus(String m1Plus) {
		this.m1Plus = m1Plus;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", unionloanName=" + unionloanName + ", date=" + date + ", principalAll="
				+ principalAll + ", unionloanPrincipalAll=" + unionloanPrincipalAll + ", principalPayable="
				+ principalPayable + ", unionloanPrincipalPayable=" + unionloanPrincipalPayable
				+ ", unionloanPrincipal=" + unionloanPrincipal + ", unionloanPrincipalJxj=" + unionloanPrincipalJxj
				+ ", unionloanPrincipalJxjdd=" + unionloanPrincipalJxjdd + ", principalPayableIncrease="
				+ principalPayableIncrease + ", customerJxj=" + customerJxj + ", amountJxj=" + amountJxj
				+ ", avgAmountJxj=" + avgAmountJxj + ", useCustomerJxj=" + useCustomerJxj + ", customerJxjdd="
				+ customerJxjdd + ", amountJxjdd=" + amountJxjdd + ", avgAmountJxjdd=" + avgAmountJxjdd
				+ ", useCustomerJxjdd=" + useCustomerJxjdd + ", prehandoutRate=" + prehandoutRate + ", unionloanRate="
				+ unionloanRate + ", passRate=" + passRate + ", refuseReason=" + refuseReason + ", refuseRate="
				+ refuseRate + ", applyCustomer=" + applyCustomer + ", passCustomer=" + passCustomer + ", m1Plus="
				+ m1Plus + "]";
	}

}
