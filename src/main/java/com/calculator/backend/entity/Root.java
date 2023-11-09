package com.calculator.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roots", uniqueConstraints =
    @UniqueConstraint(name = "U", columnNames = {"value", "equation_id"}))
public class Root {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "root_id")
    private UUID id;
    @Column(name = "value")
    private Double value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equation_id")
    private Equation equation;
}
