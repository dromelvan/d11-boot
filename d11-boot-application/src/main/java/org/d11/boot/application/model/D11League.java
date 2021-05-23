package org.d11.boot.application.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A D11 League for a specific season.
 */
@Data
@Entity
@Table(name = "d11_league")
public class D11League extends League {

}
