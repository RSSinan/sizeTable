package com.realsolutions.qualitydeskapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Steps")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "Value")
    private float value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SizeId", nullable = false)
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OrderId", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MeasurePointId", nullable = false)
    private MeasurePoint measurePoint;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MeasurePoint getMeasurePoint() {
        return measurePoint;
    }

    public void setMeasurePoint(MeasurePoint measurePoint) {
        this.measurePoint = measurePoint;
    }
}
