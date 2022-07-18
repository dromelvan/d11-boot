package org.d11.boot.cli.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.d11.boot.jms.model.MatchData;

import java.util.List;

/**
 * Aggregation strategy that adds all parsed match datas from a split() list of fixture files into one list for the
 * continuing route.
 */
public class FixturesAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(final Exchange oldExchange, final Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        } else {
            final List<MatchData> oldMatchDatas = oldExchange.getIn().getBody(List.class);
            final List<MatchData> newMatchDatas = newExchange.getIn().getBody(List.class);

            oldMatchDatas.addAll(newMatchDatas);

            return oldExchange;
        }
    }

}
