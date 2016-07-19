package com.qcloud.component.goods.model;

import java.util.Date;
import java.math.BigDecimal;

public class MerchandiseEvaluation {

    private long   id;

    // 商品id
    private long   merchandiseId;

    // 评价内容
    private String content;

    // 星级(1-5星，星值=(1-5)*10)
    private int    star;

    // 评价时间
    private Date   time;

    // 审核状态(0:未处理,1:未通过,2:已通过)
    private int    status;

    // 规格
    private String specifications;

    // 评价人
    private long   userId;

    private int    anonymous;

    // 评价图片多个带,
    private String images;

    // 追加评论
    private String addContent;

    // 客服回复
    private String replyContent;

    public MerchandiseEvaluation() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setMerchandiseId(long merchandiseId) {

        this.merchandiseId = merchandiseId;
    }

    public long getMerchandiseId() {

        return merchandiseId;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getContent() {

        return content;
    }

    public void setStar(int star) {

        this.star = star;
    }

    public int getStar() {

        return star;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public Date getTime() {

        return time;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    public int getStatus() {

        return status;
    }

    public void setSpecifications(String specifications) {

        this.specifications = specifications;
    }

    public String getSpecifications() {

        return specifications;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public long getUserId() {

        return userId;
    }

    public int getAnonymous() {

        return anonymous;
    }

    public void setAnonymous(int anonymous) {

        this.anonymous = anonymous;
    }

    public String getImages() {

        return images;
    }

    public void setImages(String images) {

        this.images = images;
    }

    public String getAddContent() {

        return addContent;
    }

    public void setAddContent(String addContent) {

        this.addContent = addContent;
    }

    public String getReplyContent() {

        return replyContent;
    }

    public void setReplyContent(String replyContent) {

        this.replyContent = replyContent;
    }
}
