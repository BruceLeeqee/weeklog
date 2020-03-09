package cn.eden.shop.domain;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

public class Good {
    private String id;  //商品id
    private String name; //商品名称
    private String title; //商品标题
    private String imgUrl;   //商品主图
    private String etail; //商品详情
    private BigDecimal newPrice; //商品新价
    private BigDecimal oldPrice;  //商品原价
    private Long goodsStock;     //商品库存

    public Good(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setEtail(String etail) {
        this.etail = etail;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setGoodsStock(Long goodsStock) {
        this.goodsStock = goodsStock;
    }
}
