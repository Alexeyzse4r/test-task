package com.haulmont.testtask;

import javax.persistence.*;

@Entity
@Table(name = "client")
class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long client_id;
    @Column(name = "name_client")
    private String name_client;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "passport")
    private String passport;

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}

@Entity
@Table(name = "offer")
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long offer_id;
    @Column(name = "offer_client_id")
    private Long offer_client_id;
    @Column(name = "offer_credit_id")
    private Long offer_credit_id;
    @Column(name = "summ")
    private Long summ;
    @Column(name = "mounth_num")
    private Long mounth_num;
    @Column(name = "summ_every_mounth")
    private Long summ_every_mounth;
    @Column(name = "summ_itog")
    private Long summ_itog;

    public Long getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Long offer_id) {
        this.offer_id = offer_id;
    }

    public Long getOffer_client_id() {
        return offer_client_id;
    }

    public void setOffer_client_id(Long offer_client_id) {
        this.offer_client_id = offer_client_id;
    }

    public Long getOffer_credit_id() {
        return offer_credit_id;
    }

    public void setOffer_credit_id(Long offer_credit_id) {
        this.offer_credit_id = offer_credit_id;
    }

    public Long getSumm() {
        return summ;
    }

    public void setSumm(Long summ) {
        this.summ = summ;
    }

    public Long getMounth_num() {
        return mounth_num;
    }

    public void setMounth_num(Long mounth_num) {
        this.mounth_num = mounth_num;
    }

    public Long getSumm_every_mounth() {
        return summ_every_mounth;
    }

    public void setSumm_every_mounth(Long summ_every_mounth) {
        this.summ_every_mounth = summ_every_mounth;
    }

    public Long getSumm_itog() {
        return summ_itog;
    }

    public void setSumm_itog(Long summ_itog) {
        this.summ_itog = summ_itog;
    }
}

@Entity
@Table(name = "credit")
class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private Long credit_id;
    @Column(name = "limit_credit")
    private Long limit_credit;
    @Column(name = "rate")
    private Long rate;

    public Long getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(Long credit_id) {
        this.credit_id = credit_id;
    }

    public Long getLimit_credit() {
        return limit_credit;
    }

    public void setLimit_credit(Long limit_credit) {
        this.limit_credit = limit_credit;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }
}



