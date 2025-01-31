package com.ies.UserMS.dto;


public class DashboardResponse {
    private Long plansCount;
    private Long citizensApprovedCount;
    private Long citizensDeniedCount;
    private Double benefitAmountTotal;

    public Long getPlansCount() {
        return plansCount;
    }

    public void setPlansCount(Long plansCount) {
        this.plansCount = plansCount;
    }

    public Long getCitizensApprovedCount() {
        return citizensApprovedCount;
    }

    public void setCitizensApprovedCount(Long citizensApprovedCount) {
        this.citizensApprovedCount = citizensApprovedCount;
    }

    public Long getCitizensDeniedCount() {
        return citizensDeniedCount;
    }

    public void setCitizensDeniedCount(Long citizensDeniedCount) {
        this.citizensDeniedCount = citizensDeniedCount;
    }

    public Double getBenefitAmountTotal() {
        return benefitAmountTotal;
    }

    public void setBenefitAmountTotal(Double benefitAmountTotal) {
        this.benefitAmountTotal = benefitAmountTotal;
    }
}
