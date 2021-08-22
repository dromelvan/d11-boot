package org.d11.boot.application.model.jpa;

import lombok.Data;
import org.d11.boot.application.model.jpa.League;

import javax.persistence.Entity;

/**
 * A Premier League for a specific season.
 */
@Data
@Entity
public class PremierLeague extends League {

}
