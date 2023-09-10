package org.d11.boot.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * A D11 League for a specific season.
 */
@Data
@Entity
@Table(name = "d11_league")
public class D11League extends League {

}
