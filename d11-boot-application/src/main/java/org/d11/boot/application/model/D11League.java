package org.d11.boot.application.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A D11 League for a specific season.
 */
@Entity
@Table(name = "d11_league")
public class D11League extends League {

}
